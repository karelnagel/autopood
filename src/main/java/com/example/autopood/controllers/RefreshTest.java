package com.example.autopood.controllers;

import com.example.autopood.User;
import com.example.autopood.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
public class RefreshTest {
    private static final Logger logger = LoggerFactory.getLogger(CallBackHandler.class);
    private final UserRepository userRepository;

    public RefreshTest(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Scheduled(fixedRate = 60000)
    @GetMapping("/refresh")
    public void getTest(){
        System.out.println("Refresh");
    }
    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setId("asasfa");
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}

