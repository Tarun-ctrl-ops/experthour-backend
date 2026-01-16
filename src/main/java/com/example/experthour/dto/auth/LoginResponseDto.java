package com.example.experthour.dto.auth;

public class LoginResponseDto {

    private String token;
    private AuthUserDto user;

    public LoginResponseDto(String token, AuthUserDto user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public AuthUserDto getUser() {
        return user;
    }
}

