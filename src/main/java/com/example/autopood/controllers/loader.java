package com.example.autopood.controllers;

import com.example.autopood.models.User;
import com.example.autopood.repositorities.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class loader implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public loader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        var user = new User();
        user.setEesnimi("Otto Bruno");
        user.setPerekonnanimi("Koobakene");
        user.setLastAction("did nothing");
        user.setId("12345");
        this.userRepository.save(user);
    }
}