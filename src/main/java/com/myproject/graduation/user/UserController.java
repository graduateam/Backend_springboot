package com.myproject.graduation.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@Tag(name = "User")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "회원 가입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user data")
    })
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreateRequest userCreate) {
        User user = new User();
        user.setName(userCreate.getName());
        user.setEmail(userCreate.getEmail());
        user.setPassword(userCreate.getPassword());
        User createdUser = userService.createUser(user);

        return ResponseEntity.ok(createdUser);
    }

    @Operation(summary = "email로 유저 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(
            @Parameter(description = "Email of the user to retrieve") @PathVariable String email) {
        User user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "유저 정보 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @Parameter(description = "ID of the user to update") @PathVariable Long id,
            @RequestBody UserUpdateRequest userUpdate,
            // Authentication 객체 추가
            Authentication authentication) throws AccessDeniedException {
        // 현재 로그인한 유저 인증
        if (authentication == null) {
            throw new AccessDeniedException("인증을 필요로 합니다.");
        }
        String currentUsername = authentication.getName();
        User currentUser = userService.findByEmail(currentUsername);
        if (currentUser == null || !currentUser.getId().equals(id)) {
            throw new AccessDeniedException("본인의 정보만 수정할 수 있습니다.");
        }

        // 기존 유저 데이터 조회
        User user = userService.findById(id);
        if (user == null) {
            throw new OpenApiResourceNotFoundException("User not found with id : " + id);
        }

        // 업데이트할 필드만 수정
        if (userUpdate.getName() != null) {
            user.setName(userUpdate.getName());
        }

        if (userUpdate.getEmail() != null) {
            user.setEmail(userUpdate.getEmail());
        }

        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);

    }

    @Operation(summary = "비밀번호 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password updated successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(
            @Parameter(description = "ID of the user to update") @PathVariable Long id,
            @Valid @RequestBody PasswordUpdateRequest passwordUpdate,
            Authentication authentication) throws AccessDeniedException {
        // 현재 로그인한 유저 인증
        if (authentication == null) {
            throw new AccessDeniedException("인증을 필요로 합니다.");
        }

        String currentUsername = authentication.getName();
        User currentUser = userService.findByEmail(currentUsername);
        if (currentUser == null || !currentUser.getId().equals(id)) {
            throw new AccessDeniedException("본인의 정보만 수정할 수 있습니다.");
        }

        userService.updatePassword(id, passwordUpdate.getNewPassword());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "유저 탈퇴", description = "Deletes a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "탈퇴하고자 하는 유저의 ID") @PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}