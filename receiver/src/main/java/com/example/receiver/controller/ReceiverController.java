package com.example.receiver.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ReceiverController {

    @PostMapping("/receive")
    @Transactional(timeout = 1) //Timeout de 1 segundo
    public void receiveNumber(@RequestBody int randomNumber) {
        System.out.println("El nuevo numero de subasta es: " + randomNumber);      
    }
}
