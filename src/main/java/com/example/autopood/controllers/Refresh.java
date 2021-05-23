package com.example.autopood.controllers;

import com.example.autopood.components.MessengerSendComponent;
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
    private final MessengerSendComponent sendComponent;

    Refresh(@Autowired UserRepository userRepository, @Autowired MessengerSendComponent sendComponent, @Autowired PoodRepository poodRepository, @Autowired KuulutusRepository kuulutusRepository, @Autowired ParameterRepository parameterRepository)
    {
        this.userRepository = userRepository;
        this.sendComponent = sendComponent;
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
                        sendComponent.sendKuulutus(userId.toString(), kuulutus, parameter);
                    }
                }
                System.out.println(kuulutus.toString());
            }
        }


    }
//
//    @GetMapping("/messageToAll")
//    public void sendMessageToAll(@RequestParam String message)
//    {
//        Iterable<User> users = userRepository.findAll();
//        for (User user : users)
//        {
//            sendComponent.sendTextMessage(user.getId(), message);
//        }
//    }
//
//    @GetMapping("/message")
//    public void sendMessage(@RequestParam String message, @RequestParam Long userId)
//    {
//        System.out.println("Message sent to " + userId);
//        sendComponent.sendTextMessage(userId, message);
//    }
//

}

