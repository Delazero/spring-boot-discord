package br.com.delazari.discord.service;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SampleService {

    @Autowired
    private CircuitBreakerFactory cbFactory;

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleService.class);

    private RestTemplate restTemplate = new RestTemplate();

    public String getSampleList() {
        CircuitBreaker cBreaker = cbFactory.create("circuitbreaker");
        String url = "https://jsonplaceholder.typicode.com/albums";
        String falseUrl = "http://localhost:1234/not-real";
        return cBreaker.run(() -> restTemplate.getForObject(falseUrl, String.class), throwable -> getDefault());
    }

    private String getDefault() {
        try {
            return new String(Files
                    .readAllBytes(Paths.get(getClass().getClassLoader().getResource("fallback-list.json").toURI())));
        } catch (Exception e) {
            LOGGER.error("ERRO AO LER O ARQUIVO", e);
        }
        return null;
    }
}
