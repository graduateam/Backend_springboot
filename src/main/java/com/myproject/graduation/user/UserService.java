package com.myproject.graduation.user;

import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 유저 생성
    public User createUser(User user) {
        // 이메일 중복 체크
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists : " + user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(UserStatus.ACTIVE); // 기본 상태 설정
        return userRepository.save(user);
    }

    // ID로 유저 조회
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new OpenApiResourceNotFoundException("User not found with id : " + id));
    }

    // 이메일로 유저 조회
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new OpenApiResourceNotFoundException("User not found with email : " + email));
    }

    // 유저 업데이트
    public User updateUser(Long id, User updatedUser) {
        User user = findById(id);
        if (updatedUser.getName() != null) {
            user.setName(updatedUser.getName());
        }

        if (updatedUser.getEmail() != null) {
            // 이메일 중복 체크
            if (!updatedUser.getEmail().equals(user.getEmail()) && userRepository.findByEmail(updatedUser.getEmail()).isPresent()) {
                throw new IllegalArgumentException("Email already exists : " + updatedUser.getEmail());
            }
            user.setEmail(updatedUser.getEmail());
        }

        if (updatedUser.getStatus() != null) {
            user.setStatus(updatedUser.getStatus());
        }

        return userRepository.save(user);
    }

    // 비밀번호 업데이트
    public void updatePassword(Long id, String newPassword) {
        User user = findById(id);
        if (newPassword == null || newPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("New password cannot be empty");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    // 유저 삭제
    public void deleteUser(Long id) {
        User user = findById(id);
        userRepository.deleteById(id);
    }

}