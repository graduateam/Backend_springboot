package com.myproject.graduation.service;

import com.myproject.graduation.domain.User;
import com.myproject.graduation.domain.UserStatus;
import com.myproject.graduation.dto.request.UserCreateRequest;
import com.myproject.graduation.dto.request.UserUpdateRequest;
import com.myproject.graduation.repository.UserRepository;
import jakarta.validation.Valid;
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
    public User createUser(@Valid UserCreateRequest userCreate) {
        // 이메일 중복 체크
        if (userRepository.findByEmail(userCreate.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다: " + userCreate.getEmail());
        }

        // UserCreateRequest DTO를 User 엔티티로 변환
        User user = new User();
        user.setName(userCreate.getName());
        user.setEmail(userCreate.getEmail());
        user.setPassword(passwordEncoder.encode(userCreate.getPassword()));
        user.setStatus(UserStatus.ACTIVE); // 회원가입 시 status는 기본적으로 ACTIVE로 설정

        // User 엔티티를 저장
        return userRepository.save(user); // id는 DB에서 자동 생성
    }

    // 이메일로 유저 조회
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("입력하신 email의 계정을 찾을 수 없습니다.\n" + email));
    }

    // ID로 유저 조회
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("입력하신 id의 계정을 찾을 수 없습니다.\n" + id));
    }

    // 유저 업데이트
    public User updateUser(Long id, UserUpdateRequest userUpdate) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("입력하신 id의 계정을 찾을 수 없습니다.\n" + id));
        user.setName(userUpdate.getName());
        user.setEmail(userUpdate.getEmail());
        if (userUpdate.getPassword() != null && !userUpdate.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userUpdate.getPassword()));
        }
        user.setStatus(userUpdate.getStatus());
        return userRepository.save(user);
    }

    // 유저 삭제
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}