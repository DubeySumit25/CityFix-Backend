package com.example.cityfix.controller;

import com.example.cityfix.dto.response.ComplaintResponseDTO;
import com.example.cityfix.dto.response.UserResponseDTO;
import com.example.cityfix.enums.ComplaintCategory;
import com.example.cityfix.enums.ComplaintStatus;
import com.example.cityfix.service.ComplaintService;
import com.example.cityfix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/complaints")
@RequiredArgsConstructor
public class AdminController {

    private final ComplaintService complaintService;
    private final UserService userService;

    @GetMapping
    public List<ComplaintResponseDTO> getAll() {

        return complaintService.getAllComplaints();
    }

    @GetMapping("/{id}")
    public ComplaintResponseDTO getById(
            @PathVariable Long id
    ) {

        return complaintService.getById(id);
    }

    @GetMapping("/status")
    public List<ComplaintResponseDTO> getByStatus(
            @RequestParam ComplaintStatus status
    ) {

        return complaintService.getByStatus(status);
    }

    @GetMapping("/category")
    public List<ComplaintResponseDTO> getByCategory(
            @RequestParam ComplaintCategory category
    ) {

        return complaintService.getByCategory(category);
    }

    @PutMapping("/{id}/status")
    public ComplaintResponseDTO updateStatus(
            @PathVariable Long id,
            @RequestParam ComplaintStatus status
    ) {

        return complaintService.updateStatus(id, status);
    }

    @PutMapping("/users/{id}/make-admin")
    public String makeAdmin(@PathVariable Long id) {

        userService.makeAdmin(id);

        return "User promoted to ADMIN";
    }

    @GetMapping("/users")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}
