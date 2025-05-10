package com.example.chatting_application.controller;

import com.example.chatting_application.dto.RegisterRequest;
import com.example.chatting_application.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public String showIndex(){
        return "redirect:/login";
    }

    // 로그인
    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    // 회원가입
    @GetMapping("/register")
    public String showRegisterForm(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequest registerRequest) {
        try{
            userService.registerUser(registerRequest);
            return "redirect:/login";
        }catch (Exception e){
            return "register";
        }
    }

}
