package com.example.chatting_application.controller;

import com.example.chatting_application.dto.ChatListDTO;
import com.example.chatting_application.service.RoomService;
import com.example.chatting_application.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ChatRoomController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService chatRoomService;

    private static final Logger logger = LoggerFactory.getLogger(ChatRoomController.class);

    public ChatRoomController(){
    }

    @GetMapping("/chatList")
    public String showChatList(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<ChatListDTO> chatList = userService.getChatList(auth.getName());
        model.addAttribute("chatList", chatList);
        return "chatList";
    }


    @GetMapping("/create")
    public String showCreateChatRoom(){
        return "createChatRoom";
    }

    @PostMapping("/create")
    public String createChattingRoom(@RequestParam("chatroom-name")String title){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        chatRoomService.createChatRoom(title, auth.getName());

        return "redirect:/chatList";
    }

}
