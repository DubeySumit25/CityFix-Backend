package com.example.cityfix.service;

import com.example.cityfix.dto.response.LoginResponse;
import com.example.cityfix.repository.UserRepository;
import com.example.cityfix.dto.request.LoginRequest;
import com.example.cityfix.dto.request.SignupRequest;
import com.example.cityfix.dto.response.UserResponseDTO;
import com.example.cityfix.entity.User;
import com.example.cityfix.enums.Role;
import com.example.cityfix.exception.ResourceNotFoundException;
import com.example.cityfix.mapper.UserMapper;
import com.example.cityfix.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


    public UserResponseDTO signup(SignupRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(Role.USER);

        User saved = userRepository.save(user);

        return UserMapper.toDTO(saved);
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return new LoginResponse(
                jwtUtil.generateToken(user.getEmail()),
                user.getRole().name()
        );
    }

    @Transactional
    public void makeAdmin(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole(Role.ADMIN);

        userRepository.save(user);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }
}