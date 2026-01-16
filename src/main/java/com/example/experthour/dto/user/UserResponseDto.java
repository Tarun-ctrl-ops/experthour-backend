package com.example.experthour.dto.user;

public class UserResponseDto {

    private Long id;
    private String name;
    private String email;
    private String role;

    public UserResponseDto(Long id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // getters & setters
}


