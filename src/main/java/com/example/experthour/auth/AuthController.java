package com.example.experthour.auth;

import com.example.experthour.dto.auth.LoginRequestDto;
import com.example.experthour.dto.auth.LoginResponseDto;
import com.example.experthour.dto.auth.RegisterRequestDto;
import com.example.experthour.mapper.AuthMapper;
import com.example.experthour.user.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto request) {

        User user = authService.authenticate(
                request.getEmail(),
                request.getPassword()
        );

        String token = authService.generateToken(user);

        return new LoginResponseDto(
                token,
                AuthMapper.toAuthUserDto(user)
        );
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequestDto request) {
        authService.register(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );
    }
}

