package com.airbnb.server.controller;

import com.airbnb.server.dto.ApiResponse;
import com.airbnb.server.entity.Property;
import com.airbnb.server.security.CustomUserDetails;
import com.airbnb.server.service.FileService;
import com.airbnb.server.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/property")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;
    private final FileService fileService;

    @PostMapping
    public ApiResponse addProperty(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @ModelAttribute Property property,
            @RequestParam("photo") MultipartFile photo
    ) {
        try {
            if(!userDetails.getUser().getRole().equalsIgnoreCase("admin")) {
                 return ApiResponse.error("Only Admins can add properties");
            }
            String fileName = fileService.storeFile(photo);
            property.setProfileImage(fileName);
            property.setUserId(userDetails.getUser().getId());
            
            propertyService.addProperty(property);
            return ApiResponse.success("Property added successfully");
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    @GetMapping
    public ApiResponse getProperties(@RequestParam(required = false) String search) {
        try {
            return ApiResponse.success(propertyService.getAllProperties(search));
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    @GetMapping("/details/{id}")
    public ApiResponse getPropertyDetails(@PathVariable Integer id) {
        try {
            Property property = propertyService.getPropertyDetails(id);
            if (property == null) {
                return ApiResponse.error("Property not found");
            }
            return ApiResponse.success(java.util.Collections.singletonList(property));
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteProperty(@PathVariable Integer id) {
        try {
            propertyService.deleteProperty(id);
            return ApiResponse.success("Property deleted");
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }
}
