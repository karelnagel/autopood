package com.example.autopood.controllers;

import com.example.autopood.models.Kuulutus;
import com.example.autopood.models.User;
import com.example.autopood.poed.Pood;
import com.example.autopood.poed.Vaurioajoneuvo;
import com.example.autopood.repositorities.UserRepository;
import com.github.messenger4j.exceptions.MessengerApiException;
import com.github.messenger4j.exceptions.MessengerIOException;
import com.github.messenger4j.send.MessengerSendClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final MessengerSendClient sendClient;

    Refresh(UserRepository userRepository, MessengerSendClient sendClient)
    {
        this.userRepository = userRepository;
        this.sendClient = sendClient;
    }

    @Scheduled(fixedRate = 60000)
    @GetMapping("/refresh")
    public void getTest()
    {
        System.out.println("Refresh");
        List<Pood> poodideList = new ArrayList<Pood>();
//
//        var mobile = new Mobile();
        var vaurioajoneuvo = new Vaurioajoneuvo();
        poodideList.add(vaurioajoneuvo);
//        poodideList.add(mobile);

        //Refresh all shops
        for (Pood pood : poodideList)
        {
            var list = pood.refresh();
            for (Kuulutus kuulutus : list)
            {
                //Kontrolli kas keegi tahab

                sendMessage(kuulutus.toString());
            }
        }
    }

    @GetMapping("/message")
    public void sendMessage(@RequestParam String message)
    {
        Iterable<User> users = userRepository.findAll();
        for (User user : users)
        {
            try
            {
                System.out.println(user.getId());
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

