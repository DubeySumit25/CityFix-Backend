package com.example.cityfix.controller;

import com.example.cityfix.dto.request.ComplaintRequestDTO;
import com.example.cityfix.dto.response.ComplaintResponseDTO;
import com.example.cityfix.service.ComplaintService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService complaintService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ComplaintResponseDTO createComplaint(
            @RequestPart("data") @Valid ComplaintRequestDTO dto,
            @RequestPart("image") MultipartFile image
    ) {
        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        String email = authentication.getName();

        return complaintService.createComplaint(email, dto, image);
    }

    @GetMapping("/my")
    public List<ComplaintResponseDTO> myComplaints() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }

        String email = authentication.getName();

        return complaintService.getMyComplaints(email);
    }

    @GetMapping("/my/{id}")
    public ComplaintResponseDTO myComplaintById(
            @PathVariable Long id
    ) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return complaintService.getMyComplaintById(id, email);
    }
}