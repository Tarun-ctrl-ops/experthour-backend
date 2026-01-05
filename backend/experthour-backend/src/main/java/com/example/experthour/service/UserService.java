package com.example.experthour.service;

import com.example.experthour.dto.CreateUserRequest;
import com.example.experthour.dto.UpdateUserRequest;
import com.example.experthour.dto.UserResponse;
import com.example.experthour.exception.ResourceNotFoundException;
import com.example.experthour.exception.ValidationException;
import com.example.experthour.model.User;
import com.example.experthour.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public UserResponse getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return toResponse(user);
    }

    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return toResponse(user);
    }

    public UserResponse createUser(CreateUserRequest request) {

        // Business validation
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ValidationException("Email already exists");
        }

        if (!isValidRole(request.getRole())) {
            throw new ValidationException("Invalid role. Allowed values: USER, EXPERT");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .role(request.getRole())
                .skills(request.getSkills())
                .experience(request.getExperience())
                .hourlyRate(request.getHourlyRate())
                .rating(0.0)
                .build();

        return toResponse(userRepository.save(user));
    }

    private boolean isValidRole(String role) {
        return "USER".equals(role) || "EXPERT".equals(role);
    }

    private UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .skills(user.getSkills())
                .experience(user.getExperience())
                .hourlyRate(user.getHourlyRate())
                .rating(user.getRating())
                .build();
    }
    public UserResponse updateUser(UUID id, UpdateUserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!isValidRole(request.getRole())) {
            throw new ValidationException("Invalid role. Allowed values: USER, EXPERT");
        }

        user.setName(request.getName());
        user.setRole(request.getRole());
        user.setSkills(request.getSkills());
        user.setExperience(request.getExperience());
        user.setHourlyRate(request.getHourlyRate());

        return toResponse(userRepository.save(user));
    }

    public void deleteUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userRepository.delete(user);
    }
}