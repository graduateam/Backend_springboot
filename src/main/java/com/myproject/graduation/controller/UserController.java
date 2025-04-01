package com.myproject.graduation.controller;

import com.myproject.graduation.domain.User;
import com.myproject.graduation.domain.VideoData;
import com.myproject.graduation.dto.request.UserCreateRequest;
import com.myproject.graduation.dto.response.UserResponse;
import com.myproject.graduation.service.UserService;
import com.myproject.graduation.dto.request.UserUpdateRequest;
import com.myproject.graduation.service.VideoDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final VideoDataService videoDataService;

    public UserController(UserService userService, VideoDataService videoDataService) {
        this.userService = userService;
        this.videoDataService = videoDataService;
    }

    @Operation(summary = "회원 가입", description = "이메일, 비밀번호, 이름을 입력하여 회원가입. ID는 DB에서 자동 생성되며, status는 기본적으로 ACTIVE로 설정됩니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 입력 데이터")
    })
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest userCreate) {
        User user = userService.createUser(userCreate);
        UserResponse response = new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getStatus());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "email로 유저 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 조회 성공"),
            @ApiResponse(responseCode = "404", description = "유저를 찾을 수 없음")
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(
            @Parameter(description = "조회할 유저의 이메일") @PathVariable String email) {
        User user = userService.findByEmail(email);
        UserResponse response = new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getStatus());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "유저 정보 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 정보 수정 성공"),
            @ApiResponse(responseCode = "404", description = "유저를 찾을 수 없음")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @Parameter(description = "수정할 유저의 ID") @PathVariable Long id,
            @Valid @RequestBody UserUpdateRequest userUpdate) {
        User updatedUser = userService.updateUser(id, userUpdate);
        UserResponse response = new UserResponse(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail(), updatedUser.getStatus());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "유저 탈퇴", description = "유저를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "유저 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "유저를 찾을 수 없음")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "탈퇴하고자 하는 유저의 ID") @PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "유저 정보로 영상 처리 요청", description = "유저 정보를 Flask로 전송하고 영상 처리 데이터를 받아 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "영상 처리 성공"),
            @ApiResponse(responseCode = "404", description = "유저를 찾을 수 없음")
    })
    @PostMapping("/{userId}/process-video")
    public ResponseEntity<VideoData> processVideoData(
            @Parameter(description = "영상 처리를 요청할 유저의 ID") @PathVariable Long userId) {
        VideoData videoData = videoDataService.processVideoData(userId);
        return ResponseEntity.ok(videoData);
    }

    @Operation(summary = "유저의 영상 처리 데이터 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "영상 처리 데이터 조회 성공"),
            @ApiResponse(responseCode = "404", description = "유저를 찾을 수 없음")
    })
    @GetMapping("/{userId}/video-data")
    public ResponseEntity<List<VideoData>> getVideoDataByUserId(
            @Parameter(description = "영상 처리 데이터를 조회할 유저의 ID") @PathVariable Long userId) {
        List<VideoData> videoDataList = videoDataService.getVideoDataByUserId(userId);
        return ResponseEntity.ok(videoDataList);
    }


}