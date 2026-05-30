package com.example.cityfix.dto.response;

import com.example.cityfix.enums.Role;
import lombok.Data;

@Data
public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private Role role;
}