package com.example.chatting_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "room_participants")
public class RoomParticipants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")  // 외래 키 설정
    private Users user;  // manager_user_id가 참조하는 User 엔티티

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")  // 외래 키 설정
    private Room room;  // manager_user_id가 참조하는 User 엔티티

    @CreatedDate
    private LocalDateTime joined_at;

    public RoomParticipants(Users user, Room room){
        this.user = user;
        this.room = room;
    }

}
