package com.example.chatting_application.exception;

public class UserAlreadyException extends RuntimeException {
    public UserAlreadyException(String message){
        super(message);
    }
}
