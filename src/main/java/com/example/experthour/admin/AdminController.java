package com.example.experthour.admin;

import com.example.experthour.admin.AdminService;
import com.example.experthour.dto.admin.AdminBookingDto;
import com.example.experthour.dto.admin.AdminExpertDto;
import com.example.experthour.dto.admin.AdminUserDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<AdminUserDto> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/experts")
    public List<AdminExpertDto> getAllExperts() {
        return service.getAllExperts();
    }

    @GetMapping("/bookings")
    public List<AdminBookingDto> getAllBookings() {
        return service.getAllBookings();
    }

    @PostMapping("/experts/{id}/approve")
    public AdminExpertDto approveExpert(@PathVariable Long id) {
        return service.approveExpert(id);
    }
}


