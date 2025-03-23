package com.myproject.graduation.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PasswordUpdateRequest {

    @NotBlank(message = "New password cannot be empty")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 charaters")
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
