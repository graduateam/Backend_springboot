package com.myproject.graduation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "회원가입 요청 DTO")
public class UserCreateRequest {

    // id와 status는 유저가 입력하지 않음 (id는 DB에서 자동 생성, status는 기본값 ACTIVE로 설정)
    // Getters and Setters
    @Schema(description = "유저 이름", example = "홍길동")
    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    @Size(min = 2, max = 5, message = "이름은 2자 이상 5자 이하여야 합니다.")
    private String name;

    @Schema(description = "유저 이메일 (아이디로 사용)", example = "user@example.com")
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "유효한 이메일 형식이어야 합니다.")
    private String email;

    @Schema(description = "유저 비밀번호", example = "password123")
    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하여야 합니다.")
    private String password;

}