package com.myproject.graduation.service;

import com.myproject.graduation.domain.User;
import com.myproject.graduation.domain.VideoData;
import com.myproject.graduation.dto.request.UserInfoRequest;
import com.myproject.graduation.dto.response.VideoDataResponse;
import com.myproject.graduation.repository.VideoDataRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class VideoDataService {

    private final UserService userService;
    private final VideoDataRepository videoDataRepository;
    private final RestTemplate restTemplate;

    @Value("${flask.api.url}") // application.yml에서 Flask API URL 설정
    private String flaskApiUrl;

    public VideoDataService(UserService userService, VideoDataRepository videoDataRepository, RestTemplate restTemplate) {
        this.userService = userService;
        this.videoDataRepository = videoDataRepository;
        this.restTemplate = restTemplate;
    }

    public VideoData processVideoData(Long userId) {
        // 1. 유저 정보 조회
        User user = userService.findById(userId);

        // 2. Flask로 전송할 유저 정보 DTO 생성
        UserInfoRequest userInfoRequest = new UserInfoRequest(user.getId(), user.getName(), user.getEmail());

        // 3. Flask API 호출
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        HttpEntity<UserInfoRequest> requestEntity = new HttpEntity<>(userInfoRequest, headers);

        ResponseEntity<VideoDataResponse> response = restTemplate.exchange(
                flaskApiUrl + "/process-video", // Flask 엔드포인트
                HttpMethod.POST,
                requestEntity,
                VideoDataResponse.class
        );

        // 4. Flask로부터 받은 데이터 처리
        VideoDataResponse videoDataResponse = response.getBody();
        if (videoDataResponse == null) {
            throw new IllegalStateException("Flask로부터 유효한 응답을 받지 못했습니다.");
        }

        // 5. DB에 저장
        VideoData videoData = new VideoData();
        videoData.setUser(user);
        videoData.setProcessedData(videoDataResponse.getProcessedData());
        videoData.setProcessedAt(videoDataResponse.getProcessedAt());
        return videoDataRepository.save(videoData);
    }

    public List<VideoData> getVideoDataByUserId(Long userId) {
        return videoDataRepository.findByUserId(userId);
    }
}