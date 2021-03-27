package com.example.autopood.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
public class RefreshTest {
    private static final Logger logger = LoggerFactory.getLogger(CallBackHandler.class);

    @Scheduled(fixedRate = 60000)
    @GetMapping("/refresh")
    public void getTest(){
        System.out.println("Refresh");
    }
}

