package com.example.experthour.user;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final com.example.experthour.user.UserService service;

    public UserController(com.example.experthour.user.UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<com.example.experthour.user.User> getUsers() {
        return service.getAllUsers();
    }
}