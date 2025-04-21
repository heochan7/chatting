package com.example.chatting_application.controller;

import com.example.chatting_application.user.dto.RegisterRequest;
import com.example.chatting_application.user.exception.UserAlreadyException;
import com.example.chatting_application.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@Controller
public class AuthController {

    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public String showIndex(){
        return "index";
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
    public String register(@ModelAttribute RegisterRequest registerRequest, Model model) {
        try{
            userService.registerUser(registerRequest);
            System.out.println("asdf");
            return "redirect:/login";
        }catch (Exception e){
            return "register";
        }
    }

    // 채팅 리스트
    @GetMapping("/chatList")
    public String showChatList(){
        return "chatList";
    }
}
