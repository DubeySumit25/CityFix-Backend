package com.example.cityfix.dto.response;

import com.example.cityfix.enums.ComplaintCategory;
import com.example.cityfix.enums.ComplaintStatus;
import lombok.Data;

@Data
public class ComplaintResponseDTO {

    private Long id;
    private String title;
    private String description;
    private ComplaintCategory category;
    private ComplaintStatus status;
    private String location;
    private Long userId;
    private String imageUrl;

}