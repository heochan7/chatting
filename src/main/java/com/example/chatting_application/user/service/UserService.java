package com.example.chatting_application.user.service;

import com.example.chatting_application.user.dto.RegisterRequest;
import com.example.chatting_application.user.entity.User;
import com.example.chatting_application.user.exception.UserAlreadyException;
import com.example.chatting_application.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(RegisterRequest registerRequest) {
        // 비밀번호와 확인 비밀번호가 일치하지 않으면 예외 던지기
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            throw new IllegalArgumentException("비밀번호가 다릅니다.");
        }
        // 이미 존재하는 이메일이 있으면 예외 던지기
        if (!userRepository.findByEmail(registerRequest.getEmail()).isEmpty()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        // User 객체 생성 후 DB에 저장
        User user = new User(registerRequest.getName(), registerRequest.getEmail(), encodedPassword);
        userRepository.save(user);

    }
}