package com.example.chatting_application.controller;

import com.example.chatting_application.dto.ChatListDTO;
import com.example.chatting_application.service.UserService;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ChatRoomController {

    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(ChatRoomController.class);

    public ChatRoomController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/chatList")
    public String showChatList(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<ChatListDTO> chatList = userService.getChatList(auth.getName());
        model.addAttribute("chatList", chatList);
        return "chatList";
    }

}
