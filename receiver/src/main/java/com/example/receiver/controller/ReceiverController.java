package com.example.receiver.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiverController {
    @PostMapping("/receive")
    public void receiveNumber(@RequestBody int randomNumber) {
        System.out.println("El nuevo numero de subasta es: " + randomNumber);
    }
}
