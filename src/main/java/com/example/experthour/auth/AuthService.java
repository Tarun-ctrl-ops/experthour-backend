package com.example.experthour.auth;

import com.example.experthour.common.exception.InvalidCredentialsException;
import com.example.experthour.user.User;
import com.example.experthour.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    public AuthService(UserRepository repo,
                       PasswordEncoder encoder,
                       JwtService jwt) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public User authenticate(String email, String password) {

        User user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!encoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");

        }

        return user;
    }

    public String generateToken(User user) {
        return jwt.generateToken(user.getEmail(), user.getRole());
    }

}

