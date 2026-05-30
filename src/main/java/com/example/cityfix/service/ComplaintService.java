package com.example.cityfix.service;

import com.example.cityfix.dto.request.ComplaintRequestDTO;
import com.example.cityfix.dto.response.ComplaintResponseDTO;
import com.example.cityfix.entity.Complaint;
import com.example.cityfix.entity.User;
import com.example.cityfix.enums.ComplaintCategory;
import com.example.cityfix.enums.ComplaintStatus;
import com.example.cityfix.exception.ResourceNotFoundException;
import com.example.cityfix.mapper.ComplaintMapper;
import com.example.cityfix.repository.ComplaintRepository;
import com.example.cityfix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;

    public ComplaintResponseDTO createComplaint(
            String email,
            ComplaintRequestDTO dto,
            MultipartFile image
    ) {

        try {

            User user = userRepository.findByEmail(email)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("User not found"));

            String imageUrl = imageService.uploadImage(image);

            Complaint complaint = new Complaint();

            complaint.setTitle(dto.getTitle());
            complaint.setDescription(dto.getDescription());
            complaint.setCategory(dto.getCategory());
            complaint.setStatus(ComplaintStatus.PENDING);
            complaint.setUser(user);
            complaint.setImageUrl(imageUrl);
            complaint.setLocation(dto.getLocation());

            Complaint savedComplaint = complaintRepository.save(complaint);

            return ComplaintMapper.toDTO(savedComplaint);

        } catch (IOException e) {
            throw new RuntimeException("Image upload failed", e);
        }
    }

    public List<ComplaintResponseDTO> getMyComplaints(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return complaintRepository.findByUser(user)
                .stream()
                .map(ComplaintMapper::toDTO)
                .toList();
    }

    public ComplaintResponseDTO getMyComplaintById(Long complaintId, String email) {

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));

        if (!complaint.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized access");
        }

        return ComplaintMapper.toDTO(complaint);
    }

    public List<ComplaintResponseDTO> getAllComplaints() {

        return complaintRepository.findAll()
                .stream()
                .map(ComplaintMapper::toDTO)
                .toList();
    }

    public List<ComplaintResponseDTO> getByStatus(ComplaintStatus status) {

        return complaintRepository.findByStatus(status)
                .stream()
                .map(ComplaintMapper::toDTO)
                .toList();
    }

    public List<ComplaintResponseDTO> getByCategory(ComplaintCategory category) {

        return complaintRepository.findByCategory(category)
                .stream()
                .map(ComplaintMapper::toDTO)
                .toList();
    }

    public ComplaintResponseDTO getById(Long id) {

        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));

        return ComplaintMapper.toDTO(complaint);
    }

    public ComplaintResponseDTO updateStatus(Long id, ComplaintStatus status) {

        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));

        complaint.setStatus(status);

        return ComplaintMapper.toDTO(complaintRepository.save(complaint));
    }
}