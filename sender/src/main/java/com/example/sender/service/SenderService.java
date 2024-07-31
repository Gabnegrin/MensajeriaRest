package com.example.sender.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class SenderService {
    private final RestTemplate restTemplate;
    private final String receiverUrl = "http://receiver1:8080/receive";
    private final String receiverUrl2 = "http://receiver2:8080/receive";
    private final String receiverUrl3 = "http://receiver3:8080/receive";
    private final String receiverUrl4 = "http://heartbeat:8080/latido";

    public SenderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedRate = 5000)
    public void sendRandomNumber() {
        int randomNumber = new Random().nextInt(100);
        String heartbeat = "sender";
        restTemplate.postForObject(receiverUrl, randomNumber, String.class);
        restTemplate.postForObject(receiverUrl2, randomNumber, String.class);
        restTemplate.postForObject(receiverUrl3, randomNumber, String.class);
        restTemplate.postForObject(receiverUrl4, heartbeat, String.class);
        System.out.println("el numero enviado es: " + randomNumber + " ...");
    }
}
