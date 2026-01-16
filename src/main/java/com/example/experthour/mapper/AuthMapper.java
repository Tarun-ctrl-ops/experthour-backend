package com.example.experthour.mapper;

import com.example.experthour.dto.auth.AuthUserDto;
import com.example.experthour.user.User;

public class AuthMapper {

    public static AuthUserDto toAuthUserDto(User user) {
        return new AuthUserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}

