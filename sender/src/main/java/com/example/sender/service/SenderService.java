package com.example.sender.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
public class SenderService {

    private static final Logger logger = LoggerFactory.getLogger(SenderService.class);

    private final RestTemplate restTemplate;

    private final String proxyUrl = "http://proxy:8080/proxy";
    private final String receiverUrl4 = "http://heartbeat:8080/latido";

    public SenderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Método programado para ejecutarse cada 5 segundos.
     * Este método genera un número aleatorio y envía el mismo a múltiples URLs de forma concurrente.
     */
    @Scheduled(fixedRate = 5000)
    public void enviarNumeroAleatorio() {
        int numeroAleatorio = new Random().nextInt(100);
        String latido = "sender";
        logger.info("Enviando número aleatorio: {}", numeroAleatorio);
        enviarAReceptor(proxyUrl, numeroAleatorio);
        enviarAReceptor(receiverUrl4, latido);
    }

    /**
     * Método asíncrono que envía un payload a una URL específica.
     * Utiliza concurrencia para ejecutar esta tarea en un hilo separado y mejorar rendimiento.
     */
    @Async
    public CompletableFuture<String> enviarAReceptor(String url, Object payload) {
        logger.info("Enviando payload a {}: {}", url, payload);
        restTemplate.postForObject(url, payload, String.class);
        logger.info("Completado el envío de payload a {}", url);
        return CompletableFuture.completedFuture("Éxito");
    }
}