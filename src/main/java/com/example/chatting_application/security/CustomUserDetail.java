package com.example.chatting_application.security;

import com.example.chatting_application.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetail implements UserDetails {

    private Users users;

    public CustomUserDetail(Users user){
        this.users = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return users.getEmail();
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }
}
