package com.myproject.graduation.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class APIController {

    @PostMapping("/api/connection")
    public String callPythonApi (@RequestBody String text) {
        String apiUrl = "";
        RestTemplate restTemplate = new RestTemplate();

        // return : Flask API response 값
        return restTemplate.postForObject(apiUrl, "{\"text\": \"" + text + "\"}", String.class);
    }
}
