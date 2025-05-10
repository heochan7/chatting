package com.example.chatting_application.service;


import com.example.chatting_application.entity.Room;
import com.example.chatting_application.entity.RoomParticipants;
import com.example.chatting_application.entity.Users;
import com.example.chatting_application.repository.RoomParticipantsRepository;
import com.example.chatting_application.repository.RoomRepository;
import com.example.chatting_application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    private RoomRepository chatRoomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomParticipantsRepository roomParticipantsRepository;

    public void createChatRoom(String title, String email){
        Users users = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not found"));

        Room room = chatRoomRepository.save(new Room(title, users));
        roomParticipantsRepository.save(new RoomParticipants(users, room));
    }
}
