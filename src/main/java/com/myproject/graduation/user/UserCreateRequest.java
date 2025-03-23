package com.myproject.graduation.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserCreateRequest {

    @Schema(description = "Name of the user", example = "John Doe")
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 10, message = "이름은 2글자 이상 10글자 미만으로 작성해주세요!")
    private String name;

    @Schema(description = "Email of the user", example = "john@example.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Email은 필수 사항입니다.")
    private String email;

    @Schema(description = "Password of the user", example = "password123")
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 미만으로 입력해주세요.")
    private String password;

    // Getters and Setters
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}