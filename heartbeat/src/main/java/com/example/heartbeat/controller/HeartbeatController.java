package com.example.heartbeat.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartbeatController {
    @PostMapping("/latido")
    public void receiveNumber(@RequestBody String notificacion) {
        System.out.println("El servidor: " + notificacion + " sigue vivo");
    }
}
