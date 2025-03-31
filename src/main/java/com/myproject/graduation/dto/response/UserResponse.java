package com.myproject.graduation.dto.response;

import com.myproject.graduation.domain.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "유저 응답 DTO")
public class UserResponse {

    // Getters
    @Getter
    @Schema(description = "유저 ID", example = "1")
    private Long id;

    @Getter
    @Schema(description = "유저 이름", example = "홍길동")
    private String name;

    @Getter
    @Schema(description = "유저 이메일", example = "user@example.com")
    private String email;

    @Schema(description = "유저 상태", example = "ACTIVE")
    private UserStatus status;

    public UserResponse(Long id, String name, String email, UserStatus status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
    }

}