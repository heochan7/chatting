package com.example.chatting_application.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
}
