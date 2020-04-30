package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@RestController
@RequestMapping("/api/spaceship")
@Slf4j
@RequiredArgsConstructor
public class SpaceShipRestController {
    private final RestTemplate restTemplate;
    @GetMapping("/flyaway/{fuel}")
    public void fly(@PathVariable Float fuel)  {
        flyNow(fuel);
    }

    private void flyNow(Float fuel) {
        log.info("FLY BEGIN {}", fuel);
        int work = Math.abs(new Random().nextInt() % 30000000) + 1000000;
        for (int i = 0; i < work; i++) {
            log.debug("Working... {}", i);
        }
        if (fuel < 30)
            throw new RuntimeException("Not good");

        String destination = restTemplate.getForObject("http://localhost:8090/api/destination", String.class);
        log.info("Wooooshh let us fly to {}", destination);
        log.info("FLY END");
    }
}
