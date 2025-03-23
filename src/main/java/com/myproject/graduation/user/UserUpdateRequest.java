package com.myproject.graduation.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UserUpdateRequest {

    @Schema(description = "유저 이름", example = "John Doe")
    @Size(min = 2, max = 10, message = "이름은 2글자 이상 10글자 미만으로 작성해주세요!")
    private String name;

    @Schema(description = "유저 이메일", example = "john@example.com")
    @Email(message = "Email은 필수 사항입니다.")
    private String email;

    @Schema(description = "유저 status", example = "ACTIVE")
    private UserStatus status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
