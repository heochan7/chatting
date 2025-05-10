package com.example.chatting_application.repository;

import com.example.chatting_application.entity.RoomParticipants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomParticipantsRepository extends JpaRepository<RoomParticipants, Integer> {
}
