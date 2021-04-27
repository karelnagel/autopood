package com.example.autopood.controllers;

import com.example.autopood.models.Kuulutus;
import com.example.autopood.models.Parameter;
import com.example.autopood.models.User;
import com.example.autopood.poed.AbstractPood;
import com.example.autopood.poed.Auto24;
import com.example.autopood.poed.Nettiauto;
import com.example.autopood.poed.Vaurioajoneuvo;
import com.example.autopood.repositorities.KuulutusRepository;
import com.example.autopood.repositorities.ParameterRepository;
import com.example.autopood.repositorities.PoodRepository;
import com.example.autopood.repositorities.UserRepository;
import com.github.messenger4j.exceptions.MessengerApiException;
import com.github.messenger4j.exceptions.MessengerIOException;
import com.github.messenger4j.send.MessengerSendClient;
import com.github.messenger4j.send.buttons.Button;
import com.github.messenger4j.send.templates.GenericTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class Refresh
{
    private UserRepository userRepository;
    private PoodRepository poodRepository;
    private ParameterRepository parameterRepository;
    private KuulutusRepository kuulutusRepository;
    private final MessengerSendClient sendClient;

    Refresh(@Autowired UserRepository userRepository, @Autowired MessengerSendClient sendClient, @Autowired PoodRepository poodRepository, @Autowired KuulutusRepository kuulutusRepository, @Autowired ParameterRepository parameterRepository)
    {
        this.userRepository = userRepository;
        this.sendClient = sendClient;
        this.poodRepository = poodRepository;
        this.kuulutusRepository = kuulutusRepository;
        this.parameterRepository = parameterRepository;
    }

    @Scheduled(fixedRate = 300000)
    @GetMapping("/refresh")
    public void refresh()
    {
        System.out.println("");
        System.out.println("Global refresh");
        List<AbstractPood> poodideList = new ArrayList<AbstractPood>();

        poodideList.add(new Vaurioajoneuvo(poodRepository, kuulutusRepository));
        poodideList.add(new Auto24(poodRepository, kuulutusRepository));
        poodideList.add(new Nettiauto(poodRepository, kuulutusRepository));

        var allParameters = parameterRepository.findAll();

        for (AbstractPood pood : poodideList)
        {
            var list = pood.refresh();
            for (Kuulutus kuulutus : list)
            {
                for (Parameter parameter : allParameters)
                {
                    if (parameter.kasKuulutusSobib(kuulutus))
                    {
                        var userId = parameter.getUser().getId();
                        try
                        {
                            sendKuulutus(userId.toString(),kuulutus,parameter);
                        } catch (MessengerApiException e)
                        {
                            e.printStackTrace();
                        } catch (MessengerIOException e)
                        {
                            e.printStackTrace();
                        } catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println(kuulutus.toString());
            }
        }


    }

    @GetMapping("/messageToAll")
    public void sendMessageToAll(@RequestParam String message)
    {
        Iterable<User> users = userRepository.findAll();
        for (User user : users)
        {
            try
            {
                sendClient.sendTextMessage(user.getId().toString(), message);
            } catch (MessengerApiException e)
            {
                e.printStackTrace();
            } catch (MessengerIOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/message")
    public void sendMessage(@RequestParam String message, @RequestParam Long userId)
    {
        try
        {
            System.out.println("Message sent to " + userId);
            sendClient.sendTextMessage(userId.toString(), message);
        } catch (MessengerApiException e)
        {
            e.printStackTrace();
        } catch (MessengerIOException e)
        {
            e.printStackTrace();
        }
    }

    private void sendKuulutus(String recipientId, Kuulutus kuulutus, Parameter parameter) throws MessengerApiException, MessengerIOException, IOException
    {
        var baseUrl = "https://autopood.herokuapp.com/";

        var name = parameter.getName() == null || parameter.getName().equals("") ? parameter.getBrand() + " " + parameter.getModel() + " " + parameter.getId() : parameter.getName();
        if (name.length()>19)
            name=name.substring(0,19);
        final List<Button> buttons = Button.newListBuilder()
                .addUrlButton(kuulutus.getPood().getId(), kuulutus.getLink()).toList()
                .addUrlButton(name, baseUrl + "main?userId=" + recipientId + "&paraId=" + parameter.getId())
                .toList()
                .build();


        final GenericTemplate genericTemplate = GenericTemplate.newBuilder()
                .addElements()
                .addElement(kuulutus.getBrand() + kuulutus.getModel())
                .subtitle(kuulutus.getYear()+" "+kuulutus.getMileage())
                .itemUrl(kuulutus.getLink())
                .imageUrl(kuulutus.getPicture())
                .buttons(buttons)
                .toList()
                .done()
                .build();

        this.sendClient.sendTemplate(recipientId, genericTemplate);
    }
}

