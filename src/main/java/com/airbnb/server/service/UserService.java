package com.airbnb.server.service;

import com.airbnb.server.dto.LoginRequest;
import com.airbnb.server.dto.LoginResponse;
import com.airbnb.server.dto.RegisterRequest;
import com.airbnb.server.dto.UpdateProfileRequest;
import com.airbnb.server.entity.User;
import com.airbnb.server.repository.UserRepository;
import com.airbnb.server.security.CustomUserDetails;
import com.airbnb.server.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public void registerUser(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Error: Email is already in use!");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhone());
        
        userRepository.save(user);
    }

    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails);

        return new LoginResponse(
                jwt,
                userDetails.getUser().getFirstName(),
                userDetails.getUser().getLastName(),
                userDetails.getUser().getRole()
        );
    }

    public User getProfile(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void updateProfile(Integer userId, UpdateProfileRequest request) {
        User user = getProfile(userId);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhone());
        userRepository.save(user);
    }
}
