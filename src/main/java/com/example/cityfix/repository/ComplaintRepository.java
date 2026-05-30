package com.example.cityfix.repository;

import com.example.cityfix.entity.Complaint;
import com.example.cityfix.entity.User;

import com.example.cityfix.enums.ComplaintCategory;
import com.example.cityfix.enums.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository
        extends JpaRepository<Complaint, Long> {

    List<Complaint> findByStatus(ComplaintStatus status);

    List<Complaint> findByUser(User user);

    List<Complaint> findByCategory(ComplaintCategory category);
}