package com.example.proxy.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HeartbeatService {
    private final RestTemplate restTemplate;
    private final String receiverUrl = "http://heartbeat:8080/latido";

    @Value("${CONTAINER_NAME:unknown}")
    private String containerName;

    public HeartbeatService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedRate = 5000)
    public void HeartbeatMessage() {
        String hostName = containerName;
        restTemplate.postForObject(receiverUrl, hostName, String.class);
    }
}
