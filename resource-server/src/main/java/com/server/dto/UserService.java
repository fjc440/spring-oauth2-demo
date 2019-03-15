package com.server.dto;


import com.server.entity.User;

public interface UserService {
    public User create(String username, String password);
}
