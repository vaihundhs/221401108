package com.example.averagecalculator.middleware;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoggerService {

    private static final String LOGGING_API_URL = "http://20.244.56.144/evaluation-service/log";

    public void log(String stack, String level, String packageName, String message) {
        System.out.printf("[%-5s] [%s] [%s] %s%n", level.toUpperCase(), stack, packageName, message);

        Map<String, String> body = new HashMap<>();
        body.put("stack", stack);
        body.put("level", level);
        body.put("package", packageName);
        body.put("message", message);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(LOGGING_API_URL, request, String.class);
            System.out.println("Log API call response: " + response.getStatusCode());
        } catch (Exception e) {
            System.err.println("Log API call failed: " + e.getMessage());
        }
    }
}
