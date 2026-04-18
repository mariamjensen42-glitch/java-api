package com.example.aiadventure.service;

import com.example.aiadventure.model.User;

public interface UserService {

    User createUser(User user);

    User getUserById(Long id);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

}