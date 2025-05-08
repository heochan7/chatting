package com.example.chatting_application.repository;

import com.example.chatting_application.dto.ChatListDTO;
import com.example.chatting_application.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query(
            value = "SELECT r.name AS room_name, " +
                    "(SELECT m2.message FROM message m2 WHERE m2.room_id = r.id AND m2.sent_at > rp_me.joined_at ORDER BY m2.sent_at DESC LIMIT 1) AS recent_message, " +
                    "COALESCE((SELECT MAX(m3.sent_at) FROM message m3 WHERE m3.room_id = r.id AND m3.sent_at > rp_me.joined_at), rp_me.joined_at) AS recent_message_time, " +
                    "(SELECT COUNT(*) FROM room_participants rp2 WHERE rp2.room_id = r.id) AS participant_count " +
                    "FROM room r " +
                    "JOIN users u ON u.id = r.manager_user_id " +
                    "JOIN room_participants rp_me ON rp_me.room_id = r.id AND rp_me.user_id = u.id " +
                    "WHERE u.email = :email " +
                    "ORDER BY recent_message_time DESC",
            nativeQuery = true
    )    List<ChatListDTO> findChatList(@Param("email") String email);
}
