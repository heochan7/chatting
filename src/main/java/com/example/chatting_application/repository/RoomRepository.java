package com.example.chatting_application.repository;

import com.example.chatting_application.dto.ChatListDTO;
import com.example.chatting_application.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query(
            value = "SELECT r.name AS room_name, " +
                    "(SELECT m2.message FROM message m2 WHERE m2.room_id = r.id AND m2.sent_at > rp_me.joined_at ORDER BY m2.sent_at DESC LIMIT 1) AS recent_message, " +
                    "COALESCE((SELECT MAX(m3.sent_at) FROM message m3 WHERE m3.room_id = r.id AND m3.sent_at > rp_me.joined_at), rp_me.joined_at) AS recent_message_time, " +
                    "(SELECT COUNT(*) FROM room_participants rp2 join users u2 on u2.id = rp2.user_id where u2.email = u.email) AS participant_count " +
                    "FROM room_participants rp_me " +
                    "LEFT JOIN room r ON r.id = rp_me.room_id " +
                    "JOIN users u ON u.id = rp_me.user_id " +
                    "WHERE u.email = :email " +
                    "ORDER BY recent_message_time DESC",
            nativeQuery = true
    )    List<ChatListDTO> findChatList(@Param("email") String email);
}
