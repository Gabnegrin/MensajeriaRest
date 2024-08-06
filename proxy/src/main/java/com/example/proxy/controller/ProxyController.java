package com.example.proxy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@RestController
public class ProxyController {

    private static final Logger logger = LoggerFactory.getLogger(ProxyController.class);

    private final RestTemplate restTemplate;

    private final String receiverUrl1 = "http://receiver1:8080/receive";
    private final String receiverUrl2 = "http://receiver2:8080/receive";
    private final String receiverUrl3 = "http://receiver3:8080/receive";

    public ProxyController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/proxy")
    public void receiveNumber(@RequestBody int randomNumber) {
        System.out.println("Proxy recibio el siguiente numero: " + randomNumber);

        enviarAReceptor(receiverUrl1, randomNumber);
        enviarAReceptor(receiverUrl2, randomNumber);
        enviarAReceptor(receiverUrl3, randomNumber);
    }

    @Async
    public CompletableFuture<String> enviarAReceptor(String url, Object payload) {
        logger.info("Enviando payload a {}: {}", url, payload);
        restTemplate.postForObject(url, payload, String.class);
        logger.info("Completado el envío de payload a {}", url);
        return CompletableFuture.completedFuture("Éxito");
    }
}
