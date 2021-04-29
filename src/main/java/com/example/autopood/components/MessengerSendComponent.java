package com.example.autopood.components;

import com.example.autopood.models.Kuulutus;
import com.example.autopood.models.Parameter;
import com.github.messenger4j.exceptions.MessengerApiException;
import com.github.messenger4j.exceptions.MessengerIOException;
import com.github.messenger4j.send.MessengerSendClient;
import com.github.messenger4j.send.buttons.Button;
import com.github.messenger4j.send.templates.GenericTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessengerSendComponent
{
    private final MessengerSendClient sendClient;
    private String baseUrl = "https://autopood.herokuapp.com/";

    public MessengerSendComponent(MessengerSendClient sendClient)
    {
        this.sendClient = sendClient;

    }

    public void sendKuulutus(String recipientId, Kuulutus kuulutus, Parameter parameter)
    {
        var name = parameter.getName() == null || parameter.getName().equals("") ? parameter.getBrand() + " " + parameter.getModel() + " " + parameter.getId() : parameter.getName();
        if (name.length() > 19)
            name = name.substring(0, 19);
        final List<Button> buttons = Button.newListBuilder()
                .addUrlButton(kuulutus.getPood().getId(), kuulutus.getLink()).toList()
                .addUrlButton(name, baseUrl + "main?userId=" + recipientId + "&paraId=" + parameter.getId())
                .toList()
                .build();

        var subtitle = kuulutus.getPrice() + "$, " + kuulutus.getYear() + " aasta, " + kuulutus.getMileage() + "km läbisõit";

        final GenericTemplate genericTemplate = GenericTemplate.newBuilder()
                .addElements()
                .addElement(kuulutus.getBrand() + kuulutus.getModel())
                .subtitle(subtitle)
                .imageUrl(kuulutus.getPicture()==null ||kuulutus.getPicture().equals("") ? baseUrl+"favicon.png" : kuulutus.getPicture())
                .buttons(buttons)
                .toList()
                .done()
                .build();

        try
        {
            this.sendClient.sendTemplate(recipientId, genericTemplate);
        } catch (MessengerApiException e)
        {
            e.printStackTrace();
        } catch (MessengerIOException e)
        {
            e.printStackTrace();
        }
    }

    public void sendOptions(Long recipientId)
    {
        var buttons = Button.newListBuilder()
                .addUrlButton("Muuda parameetreid", baseUrl + "main?userId=" + recipientId).toList()
                .addUrlButton("Otsi", baseUrl + "main").toList();

        final GenericTemplate genericTemplate = GenericTemplate.newBuilder()
                .addElements()
                .addElement("Mida soovid teha?")
                .buttons(buttons.build())
                .toList()
                .done()
                .build();

        try
        {
            this.sendClient.sendTemplate(recipientId.toString(), genericTemplate);
        } catch (MessengerApiException e)
        {
            e.printStackTrace();
        } catch (MessengerIOException e)
        {
            e.printStackTrace();
        }
    }

    public void sendTextMessage(Long recipientId, String text)
    {
        try
        {
            this.sendClient.sendTextMessage(recipientId.toString(), text);
        } catch (MessengerApiException e)
        {
            e.printStackTrace();
        } catch (MessengerIOException e)
        {
            e.printStackTrace();
        }
    }

//    void sendFirstOptions(Long recipientId) throws MessengerApiException, MessengerIOException
//    {
//
//        var quickReplies = QuickReply.newListBuilder()
//                .addTextQuickReply("Profiil", OPTION_PROFILE).toList()
//                .addTextQuickReply("Uus otsing", OPTION_CREATE_PARAMETER).toList()
//                .addTextQuickReply("Search", OPTION_SEARCH).toList();
//        var parameters = kuulutusParametersRepository.findByUserId(recipientId);
//        for (Parameter parameter : parameters)
//        {
//            var name = parameter.getName() != null && !parameter.getName().equals("") ? parameter.getName() : parameter.getId().toString();
//            quickReplies = quickReplies.addTextQuickReply(name, parameter.getId().toString()).toList();
//        }
//        this.sendClient.sendTextMessage(recipientId.toString(), "Mida soovid teha?", quickReplies.build());
//    }

}
