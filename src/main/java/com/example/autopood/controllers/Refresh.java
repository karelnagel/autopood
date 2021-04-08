package com.example.autopood.controllers;

import com.example.autopood.models.Kuulutus;
import com.example.autopood.models.User;
import com.example.autopood.poed.AbstractPood;
import com.example.autopood.poed.Auto24;
import com.example.autopood.poed.Nettiauto;
import com.example.autopood.poed.Vaurioajoneuvo;
import com.example.autopood.repositorities.KuulutusRepository;
import com.example.autopood.repositorities.PoodRepository;
import com.example.autopood.repositorities.UserRepository;
import com.github.messenger4j.exceptions.MessengerApiException;
import com.github.messenger4j.exceptions.MessengerIOException;
import com.github.messenger4j.send.MessengerSendClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Refresh
{
    private static final Logger logger = LoggerFactory.getLogger(CallBackHandler.class);
    private UserRepository userRepository;
    private PoodRepository poodRepository;
    private KuulutusRepository kuulutusRepository;
    private final MessengerSendClient sendClient;

    Refresh(@Autowired UserRepository userRepository, @Autowired MessengerSendClient sendClient, @Autowired PoodRepository poodRepository, @Autowired KuulutusRepository kuulutusRepository)
    {
        this.userRepository = userRepository;
        this.sendClient = sendClient;
        this.poodRepository = poodRepository;
        this.kuulutusRepository = kuulutusRepository;
    }

    @Scheduled(fixedRate = 300000)
    @GetMapping("/refresh")
    public void getTest()
    {
        System.out.println("");
        System.out.println("Global refresh");
        List<AbstractPood> poodideList = new ArrayList<AbstractPood>();
/*
        poodideList.add(new Vaurioajoneuvo(poodRepository, kuulutusRepository));
        poodideList.add(new Auto24(poodRepository, kuulutusRepository));
        poodideList.add(new Nettiauto(poodRepository, kuulutusRepository));

        //Refresh all shops
        for (AbstractPood pood : poodideList)
        {
            var list = pood.refresh();
            for (Kuulutus kuulutus : list)
            {
                //Kontrolli kas keegi tahab
                System.out.println(kuulutus.toString());
                sendMessage(kuulutus.toMessenger());
            }
        }
        
 */
    }

    @GetMapping("/message")
    public void sendMessage(@RequestParam String message)
    {
        Iterable<User> users = userRepository.findAll();
        for (User user : users)
        {
            try
            {
                sendClient.sendTextMessage(user.getId(), message);
            } catch (MessengerApiException e)
            {
                e.printStackTrace();
            } catch (MessengerIOException e)
            {
                e.printStackTrace();
            }
        }
    }
}

