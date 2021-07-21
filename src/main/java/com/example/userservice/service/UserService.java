package com.example.userservice.service;

import com.example.userservice.model.User;

import java.util.List;

public interface UserService {

    public User getUser(String email);

    public void saveUser(User user);

    public void deleteUser(String email);

    public void updateUser(User user);
}
