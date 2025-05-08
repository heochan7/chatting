package com.example.chatting_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "room")

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "manager_user_id", referencedColumnName = "id")  // 외래 키 설정
    private Users manager_user;  // manager_user_id가 참조하는 User 엔티티

    private LocalDateTime created_at;

}
