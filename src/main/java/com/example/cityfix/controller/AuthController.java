package com.example.cityfix.controller;

import com.example.cityfix.dto.request.LoginRequest;
import com.example.cityfix.dto.request.SignupRequest;
import com.example.cityfix.dto.response.LoginResponse;
import com.example.cityfix.dto.response.UserResponseDTO;
import com.example.cityfix.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public UserResponseDTO signup(@Valid @RequestBody SignupRequest request) {

        return userService.signup(request);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request
    ) {
        return userService.login(request);
    }
}
