package com.myproject.graduation.user;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserUpdateRequest {

    @Schema(description = "유저 이름")
    private String name;

    @Schema(description = "유저 이메일")
    private String email;

    @Schema(description = "유저 비밀번호")
    private String password;

    @Schema(description = "유저 status")
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

    public String getPpassword() {
        return password;
    }

    public void setPpassword(String ppassword) {
        this.password = ppassword;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
