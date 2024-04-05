package com.example.evaluationReactBackend.services;

import com.example.evaluationReactBackend.dto.responses.UserResponse;
import com.example.evaluationReactBackend.entities.User;
import com.example.evaluationReactBackend.exceptions.EmailAlreadyExistException;
import com.example.evaluationReactBackend.exceptions.EntityNotFoundException;
import com.example.evaluationReactBackend.mappers.UserMapper;
import com.example.evaluationReactBackend.repositories.UserRepository;
import com.example.evaluationReactBackend.services.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistException("Email already exists in the database");
        }
        return userRepository.save(user);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        return UserMapper.INSTANCE.toDto(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper.INSTANCE::toDto) // Utilisation du mapper pour convertir chaque User en UserResponse
                .collect(Collectors.toList());
    }

    @Override
    public void updateUser(User user) {
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + user.getId()));

        Optional<User> userByEmail = userRepository.findByEmail(user.getEmail());
        if (userByEmail.isPresent() && !userByEmail.get().getId().equals(user.getId())) {
            throw new EmailAlreadyExistException("Email already exists in the database");
        }

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("User not found with ID: " + id);
        }
    }
}
