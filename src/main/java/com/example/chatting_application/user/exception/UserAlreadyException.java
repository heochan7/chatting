package com.example.chatting_application.user.exception;

public class UserAlreadyException extends RuntimeException {
    public UserAlreadyException(String message){
        super(message);
    }
}
