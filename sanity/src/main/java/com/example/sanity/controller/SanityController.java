package com.example.sanity.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@RestController
public class SanityController {

    private static final Logger logger = LoggerFactory.getLogger(SanityController.class);

    private final RestTemplate restTemplate;

    private final String proxyUrl = "http://proxy:8080/toReceivers";

    private int maxOffer = 0;

    public SanityController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/check")
    public void receiveNumber(@RequestBody int number) {
        System.out.println("Sanity recibio el siguiente numero para sanitizar: " + number);

        if (number > maxOffer) {
            maxOffer = number;
            enviarAReceptor(proxyUrl, number);
        }
        else {
            System.out.println("La oferta no es mayor a una previa");
        }

    }

    @Async
    public CompletableFuture<String> enviarAReceptor(String url, Object payload) {
        logger.info("Enviando payload a {}: {}", url, payload);
        restTemplate.postForObject(url, payload, String.class);
        logger.info("Completado el envío de payload a {}", url);
        return CompletableFuture.completedFuture("Éxito");
    }
}
