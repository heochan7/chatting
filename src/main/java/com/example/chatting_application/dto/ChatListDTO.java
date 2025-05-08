package com.example.chatting_application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ChatListDTO {
    private String roomName;
    private String recentMessage;
    private Timestamp recentMessageTime;
    private long participantCount;

    public ChatListDTO(String roomName, String recentMessage, Timestamp recentMessageTime, long participantCount) {
        this.roomName = roomName;
        this.recentMessage = recentMessage;
        this.recentMessageTime = recentMessageTime;
        this.participantCount = participantCount;
    }
}
