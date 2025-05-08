package com.example.chatting_application.controller;

import com.example.chatting_application.dto.ChatListDTO;
import com.example.chatting_application.dto.RegisterRequest;
import com.example.chatting_application.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String register(@ModelAttribute RegisterRequest registerRequest) {
        try{
            userService.registerUser(registerRequest);
            return "redirect:/login";
        }catch (Exception e){
            return "register";
        }
    }

    // 채팅 리스트
    @GetMapping("/chatList")
    public String showChatList(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<ChatListDTO> chatList = userService.getChatList(auth.getName());
        model.addAttribute("chatList", chatList);
        return "chatList";
    }
}
