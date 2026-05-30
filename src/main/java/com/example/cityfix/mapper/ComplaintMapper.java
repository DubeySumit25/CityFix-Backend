package com.example.cityfix.mapper;

import com.example.cityfix.dto.response.ComplaintResponseDTO;
import com.example.cityfix.entity.Complaint;

public class ComplaintMapper {

    public static ComplaintResponseDTO toDTO(Complaint complaint) {

        ComplaintResponseDTO dto = new ComplaintResponseDTO();

        dto.setId(complaint.getId());
        dto.setTitle(complaint.getTitle());
        dto.setDescription(complaint.getDescription());
        dto.setCategory(complaint.getCategory());
        dto.setStatus(complaint.getStatus());
        dto.setLocation(complaint.getLocation());
        dto.setUserId(complaint.getUser().getId());
        dto.setImageUrl(complaint.getImageUrl());

        return dto;
    }
}