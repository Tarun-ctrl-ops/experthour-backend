package com.example.experthour.admin;

import com.example.experthour.booking.Booking;
import com.example.experthour.expert.Expert;
import com.example.experthour.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/experts")
    public List<Expert> getAllExperts() {
        return service.getAllExperts();
    }

    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        return service.getAllBookings();
    }

    @PostMapping("/experts/{id}/approve")
    public Expert approveExpert(@PathVariable Long id) {
        return service.approveExpert(id);
    }
}

