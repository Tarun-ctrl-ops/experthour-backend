package com.example.experthour.auth;

import com.example.experthour.user.User;
import com.example.experthour.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    public AuthService(UserRepository repo, PasswordEncoder encoder, JwtService jwt) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public AuthResponse signup(SignupRequest req) {
        User user = new User(req.name, req.email, encoder.encode(req.password), "USER");
        repo.save(user);
        return new AuthResponse(jwt.generateToken(user.getEmail()), user.getRole());
    }

    public AuthResponse login(LoginRequest req) {
        User user = repo.findByEmail(req.email).orElseThrow();
        if (!encoder.matches(req.password, user.getPassword())) throw new RuntimeException("Invalid credentials");
        return new AuthResponse(jwt.generateToken(user.getEmail()), user.getRole());
    }
}
