package com.airbnb.server.controller;

import com.airbnb.server.dto.ApiResponse;
import com.airbnb.server.dto.LoginRequest;
import com.airbnb.server.dto.LoginResponse;
import com.airbnb.server.dto.RegisterRequest;
import com.airbnb.server.dto.UpdateProfileRequest;
import com.airbnb.server.security.CustomUserDetails;
import com.airbnb.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ApiResponse register(@RequestBody RegisterRequest request) {
        try {
            userService.registerUser(request);
            return ApiResponse.success("Successfully registered");
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = userService.authenticateUser(request);
            return ApiResponse.success(response);
        } catch (Exception e) {
            return ApiResponse.error("Invalid email or password");
        }
    }

    @GetMapping("/profile")
    public ApiResponse getProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        try {
            return ApiResponse.success(userService.getProfile(userDetails.getUser().getId()));
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    @PutMapping("/profile")
    public ApiResponse updateProfile(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody UpdateProfileRequest request) {
        try {
            userService.updateProfile(userDetails.getUser().getId(), request);
            return ApiResponse.success("Profile updated");
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }
}
