package com.myproject.graduation.controller;

import com.myproject.graduation.dto.response.VideoDataResponse;
import com.myproject.graduation.service.VideoDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class APIController {

    private final VideoDataService videoDataService;

    @Autowired
    public APIController(VideoDataService videoDataService) {
        this.videoDataService = videoDataService;
    }

    @PostMapping("/api/connection")
    public String callPythonApi (@RequestBody String text) {
        String apiUrl = "";
        RestTemplate restTemplate = new RestTemplate();

        // return : Flask API response 값
        return restTemplate.postForObject(apiUrl, "{\"text\": \"" + text + "\"}", String.class);
    }

    @GetMapping("/api/video/test-flask-response")
    public ResponseEntity<VideoDataResponse> getTestFlaskVideoResponse() {
        return ResponseEntity.ok(videoDataService.getMockVideoDataResponse());
    }
}
