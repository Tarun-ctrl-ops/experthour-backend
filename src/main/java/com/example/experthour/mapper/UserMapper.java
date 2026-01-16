package com.example.experthour.mapper;

import com.example.experthour.user.User;
import com.example.experthour.dto.user.UserResponseDto;

public class UserMapper {

    public static UserResponseDto toResponseDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()   // role is already String
        );
    }
}


