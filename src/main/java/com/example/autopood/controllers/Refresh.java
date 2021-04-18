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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
                        sendMessage(parameter.getName()+"\n"+kuulutus.toMessenger(), userId);
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
}

