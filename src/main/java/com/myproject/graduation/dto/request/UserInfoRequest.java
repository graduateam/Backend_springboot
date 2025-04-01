package com.myproject.graduation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserInfoRequest {

    @Schema(description = "유저 ID", example = "1")
    private Long id;

    @Schema(description = "유저 이름", example = "홍길동")
    private String name;

    @Schema(description = "유저 이메일", example = "user@example.com")
    private String email;

    public UserInfoRequest(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
