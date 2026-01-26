package com.example.experthour.user;
import com.example.experthour.dto.user.UserResponseDto;
import com.example.experthour.mapper.UserMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User createUser(User user) {
        return repo.save(user);
    }

    public List<UserResponseDto> getAllUsers() {
        return repo.findAll()
                .stream()
                .map(UserMapper::toResponseDto)
                .toList();
    }

    public User getByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }
}
