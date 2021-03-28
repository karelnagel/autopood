package com.example.autopood.controllers;

import com.example.autopood.models.User;
import com.example.autopood.repositorities.UserRepository;
import com.github.messenger4j.MessengerPlatform;
import com.github.messenger4j.exceptions.MessengerApiException;
import com.github.messenger4j.exceptions.MessengerIOException;
import com.github.messenger4j.exceptions.MessengerVerificationException;
import com.github.messenger4j.receive.MessengerReceiveClient;
import com.github.messenger4j.receive.events.AccountLinkingEvent;
import com.github.messenger4j.receive.handlers.*;
import com.github.messenger4j.send.MessengerSendClient;
import com.github.messenger4j.send.QuickReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/callback")
public class CallBackHandler
{

    private static final Logger logger = LoggerFactory.getLogger(CallBackHandler.class);

    public static final String OPTION_VAATA = "vaata";
    public static final String OPTION_MARK = "mark";
    public static final String OPTION_MUDEL = "mudel";
    public static final String OPTION_MIN_HIND = "min hind";
    public static final String OPTION_MAX_HIND = "max hind";
    public static final String OPTION_MIN_AASTA = "min aasta";
    public static final String OPTION_MAX_AASTA = "max aasta";
    public static final String OPTION_MIN_LABISOIT = "min labisoit";
    public static final String OPTION_MAX_LABISOIT = "max labisoit";

    private final MessengerReceiveClient receiveClient;
    private final MessengerSendClient sendClient;
    private final UserRepository userRepository;

    @Autowired
    public CallBackHandler(@Value("${messenger4j.appSecret}") final String appSecret,
                           @Value("${messenger4j.verifyToken}") final String verifyToken,
                           final MessengerSendClient sendClient, UserRepository userRepository)
    {

        logger.debug("Initializing MessengerReceiveClient - appSecret: {} | verifyToken: {}", appSecret, verifyToken);
        this.receiveClient = MessengerPlatform.newReceiveClientBuilder(appSecret, verifyToken)
                .onTextMessageEvent(newTextMessageEventHandler())
                .onQuickReplyMessageEvent(newQuickReplyMessageEventHandler())
                .onPostbackEvent(newPostbackEventHandler())
                .onAccountLinkingEvent(newAccountLinkingEventHandler())
                .onOptInEvent(newOptInEventHandler())
                .onEchoMessageEvent(newEchoMessageEventHandler())
                .onMessageDeliveredEvent(newMessageDeliveredEventHandler())
                .onMessageReadEvent(newMessageReadEventHandler())
                .fallbackEventHandler(newFallbackEventHandler())
                .build();
        this.sendClient = sendClient;
        this.userRepository = userRepository;
    }

    //For messenger verification
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> verifyWebhook(@RequestParam("hub.mode") final String mode,
                                                @RequestParam("hub.verify_token") final String verifyToken,
                                                @RequestParam("hub.challenge") final String challenge)
    {

        logger.debug("Received Webhook verification request - mode: {} | verifyToken: {} | challenge: {}", mode,
                verifyToken, challenge);
        try
        {
            return ResponseEntity.ok(this.receiveClient.verifyWebhook(mode, verifyToken, challenge));
        } catch (MessengerVerificationException e)
        {
            logger.warn("Webhook verification failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    //For incoming messages
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> handleCallback(@RequestBody final String payload,
                                               @RequestHeader("X-Hub-Signature") final String signature)
    {
        logger.debug("Received Messenger Platform callback - payload: {} | signature: {}", payload, signature);
        try
        {
            this.receiveClient.processCallbackPayload(payload, signature);
            logger.debug("Processed callback payload successfully");
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (MessengerVerificationException e)
        {
            logger.warn("Processing of callback payload failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    private TextMessageEventHandler newTextMessageEventHandler()
    {
        return event ->
        {
            logger.debug("Received TextMessageEvent: {}", event);

            final String messageId = event.getMid();
            final String messageText = event.getText();
            final String senderId = event.getSender().getId();
            final Date timestamp = event.getTimestamp();

            logger.info("Received message '{}' with text '{}' from user '{}' at '{}'",
                    messageId, messageText, senderId, timestamp);

            try
            {
                boolean userExists = userRepository.existsById(senderId);
                if (!userExists)
                {
                    User user = new User();
                    user.setId(senderId);
                    userRepository.save(user);
                    sendTextMessage(senderId, "Tere tulemast!");
                    sendTextMessage(senderId, "Vali auto parameetrid");
                    sendOptions(senderId);

                } else
                {
                    User user = userRepository.findById(senderId).get();
                    if (user.getLastAction().isEmpty() || user.getLastAction() == null)
                    {
                        sendOptions(senderId);
                    } else
                    {
                        try
                        {
                            switch (user.getLastAction())
                            {
                                case OPTION_MARK:
                                    user.setBrand(messageText);
                                    break;
                                case OPTION_MUDEL:
                                    user.setModel(messageText);
                                    break;
                                case OPTION_MIN_HIND:
                                    user.setMinPrice(Integer.parseInt(messageText));
                                    break;
                                case OPTION_MAX_HIND:
                                    user.setMaxPrice(Integer.parseInt(messageText));
                                    break;
                                case OPTION_MIN_AASTA:
                                    user.setMinYear(Integer.parseInt(messageText));
                                    break;
                                case OPTION_MAX_AASTA:
                                    user.setMaxYear(Integer.parseInt(messageText));
                                    break;
                                case OPTION_MIN_LABISOIT:
                                    user.setMinMilage(Integer.parseInt(messageText));
                                    break;
                                case OPTION_MAX_LABISOIT:
                                    user.setMaxMilage(Integer.parseInt(messageText));
                                    break;
                            }
                            sendTextMessage(senderId, user.getLastAction() + " muudetud " + messageText);
                            user.setLastAction("");
                            userRepository.save(user);
                            sendOptions(senderId);
                        }
                        catch (NumberFormatException nfe)
                        {
                            sendTextMessage(senderId, "Pole sobiv number");
                        }
                    }
                }
            } catch (MessengerApiException | MessengerIOException e)
            {
                handleSendException(e);
            }
        };
    }

    private QuickReplyMessageEventHandler newQuickReplyMessageEventHandler()
    {
        return event ->
        {
            logger.debug("Received QuickReplyMessageEvent: {}", event);

            final String senderId = event.getSender().getId();
            final String messageId = event.getMid();
            final String quickReplyPayload = event.getQuickReply().getPayload();
            logger.info("Received quick reply for message '{}' with payload '{}'", messageId, quickReplyPayload);
            if (userRepository.existsById(senderId))
            {
                User user = userRepository.findById(senderId).get();
                if (quickReplyPayload.equals(OPTION_VAATA))
                {
                    sendTextMessage(senderId, user.toString());
                    try
                    {
                        sendOptions(senderId);
                    } catch (MessengerApiException e)
                    {
                        e.printStackTrace();
                    } catch (MessengerIOException e)
                    {
                        e.printStackTrace();
                    }
                } else
                {
                    user.setLastAction(quickReplyPayload);
                    userRepository.save(user);
                    sendTextMessage(senderId, "Kirjuta " + quickReplyPayload);
                }
            }
        };
    }


    private void sendTextMessage(String recipientId, String text)
    {
        try
        {
            this.sendClient.sendTextMessage(recipientId, text);
        } catch (MessengerApiException e)
        {
            e.printStackTrace();
        } catch (MessengerIOException e)
        {
            e.printStackTrace();
        }
    }

    void sendOptions(String recipientId) throws MessengerApiException, MessengerIOException
    {
        final List<QuickReply> quickReplies = QuickReply.newListBuilder()
                .addTextQuickReply("Vaata praegust", OPTION_VAATA).toList()
                .addTextQuickReply("Mark", OPTION_MARK).toList()
                .addTextQuickReply("Mudel", OPTION_MUDEL).toList()
                .addTextQuickReply("Min hind", OPTION_MIN_HIND).toList()
                .addTextQuickReply("Max hind", OPTION_MAX_HIND).toList()
                .addTextQuickReply("Min aasta", OPTION_MIN_AASTA).toList()
                .addTextQuickReply("Max aasta", OPTION_MAX_AASTA).toList()
                .addTextQuickReply("Min läbisõit", OPTION_MIN_LABISOIT).toList()
                .addTextQuickReply("Max läbisõit", OPTION_MAX_LABISOIT).toList()
                .build();

        this.sendClient.sendTextMessage(recipientId, "Mida tahad teha?", quickReplies);
    }

    private void handleSendException(Exception e)
    {
        logger.error("Message could not be sent. An unexpected error occurred.", e);
    }


    //Not needed right now but might be in future

    private PostbackEventHandler newPostbackEventHandler()
    {
        return event ->
        {
            logger.debug("Received PostbackEvent: {}", event);

            final String senderId = event.getSender().getId();
            final String recipientId = event.getRecipient().getId();
            final String payload = event.getPayload();
            final Date timestamp = event.getTimestamp();

            logger.info("Received postback for user '{}' and page '{}' with payload '{}' at '{}'",
                    senderId, recipientId, payload, timestamp);

            sendTextMessage(senderId, "Postback called");
        };
    }

    private AccountLinkingEventHandler newAccountLinkingEventHandler()
    {
        return event ->
        {
            logger.debug("Received AccountLinkingEvent: {}", event);

            final String senderId = event.getSender().getId();
            final AccountLinkingEvent.AccountLinkingStatus accountLinkingStatus = event.getStatus();
            final String authorizationCode = event.getAuthorizationCode();

            logger.info("Received account linking event for user '{}' with status '{}' and auth code '{}'",
                    senderId, accountLinkingStatus, authorizationCode);
        };
    }

    private OptInEventHandler newOptInEventHandler()
    {
        return event ->
        {
            logger.debug("Received OptInEvent: {}", event);

            final String senderId = event.getSender().getId();
            final String recipientId = event.getRecipient().getId();
            final String passThroughParam = event.getRef();
            final Date timestamp = event.getTimestamp();

            logger.info("Received authentication for user '{}' and page '{}' with pass through param '{}' at '{}'",
                    senderId, recipientId, passThroughParam, timestamp);

            sendTextMessage(senderId, "Authentication successful");
        };
    }

    private EchoMessageEventHandler newEchoMessageEventHandler()
    {
        return event ->
        {
            logger.debug("Received EchoMessageEvent: {}", event);

            final String messageId = event.getMid();
            final String recipientId = event.getRecipient().getId();
            final String senderId = event.getSender().getId();
            final Date timestamp = event.getTimestamp();

            logger.info("Received echo for message '{}' that has been sent to recipient '{}' by sender '{}' at '{}'",
                    messageId, recipientId, senderId, timestamp);
        };
    }

    private MessageDeliveredEventHandler newMessageDeliveredEventHandler()
    {
        return event ->
        {
            logger.debug("Received MessageDeliveredEvent: {}", event);

            final List<String> messageIds = event.getMids();
            final Date watermark = event.getWatermark();
            final String senderId = event.getSender().getId();

            if (messageIds != null)
            {
                messageIds.forEach(messageId ->
                {
                    logger.info("Received delivery confirmation for message '{}'", messageId);
                });
            }

            logger.info("All messages before '{}' were delivered to user '{}'", watermark, senderId);
        };
    }

    private MessageReadEventHandler newMessageReadEventHandler()
    {
        return event ->
        {
            logger.debug("Received MessageReadEvent: {}", event);

            final Date watermark = event.getWatermark();
            final String senderId = event.getSender().getId();

            logger.info("All messages before '{}' were read by user '{}'", watermark, senderId);
        };
    }

    private FallbackEventHandler newFallbackEventHandler()
    {
        return event ->
        {
            logger.debug("Received FallbackEvent: {}", event);

            final String senderId = event.getSender().getId();
            logger.info("Received unsupported message from user '{}'", senderId);
        };
    }
}
