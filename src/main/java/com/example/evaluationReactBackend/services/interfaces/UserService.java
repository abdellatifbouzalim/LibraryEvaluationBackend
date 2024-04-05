package com.example.evaluationReactBackend.services.interfaces;

import com.example.evaluationReactBackend.dto.responses.UserResponse;
import com.example.evaluationReactBackend.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    UserResponse getUserById(Long id);
    List<UserResponse> getAllUsers();
    void updateUser(User user);
    void deleteUser(Long id);
}