package com.airbnb.server.controller;

import com.airbnb.server.dto.ApiResponse;
import com.airbnb.server.entity.Category;
import com.airbnb.server.entity.Property;
import com.airbnb.server.entity.Review;
import com.airbnb.server.entity.Wishlist;
import com.airbnb.server.repository.CategoryRepository;
import com.airbnb.server.repository.PropertyRepository;
import com.airbnb.server.repository.ReviewRepository;
import com.airbnb.server.repository.WishlistRepository;
import com.airbnb.server.security.CustomUserDetails;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommonController {

    private final CategoryRepository categoryRepository;
    private final ReviewRepository reviewRepository;
    private final WishlistRepository wishlistRepository;
    private final PropertyRepository propertyRepository;

    // --- Category Endpoints ---
    @GetMapping("/category")
    public ApiResponse getCategories() {
        return ApiResponse.success(categoryRepository.findAll());
    }

    @PostMapping("/category")
    public ApiResponse addCategory(@RequestBody Category category) {
        categoryRepository.save(category);
        return ApiResponse.success("Category added");
    }

    // --- Review Endpoints ---
    @GetMapping("/review/{propertyId}")
    public ApiResponse getReviews(@PathVariable Integer propertyId) {
        return ApiResponse.success(reviewRepository.findByPropertyId(propertyId));
    }

    @PostMapping("/review")
    public ApiResponse addReview(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody Review review) {
        review.setUserId(userDetails.getUser().getId());
        reviewRepository.save(review);
        return ApiResponse.success("Review added");
    }

    // --- Wishlist Endpoints ---
    @GetMapping("/wishlist")
    public ApiResponse getWishlist(@AuthenticationPrincipal CustomUserDetails userDetails) {
        List<Wishlist> wishlists = wishlistRepository.findByUserId(userDetails.getUser().getId());
        List<Integer> propertyIds = wishlists.stream()
                .map(Wishlist::getPropertyId)
                .collect(Collectors.toList());
        if (propertyIds.isEmpty()) {
            return ApiResponse.success(Collections.emptyList());
        }
        List<Property> properties = propertyRepository.findAllById(propertyIds);
        return ApiResponse.success(properties);
    }

    @GetMapping("/wishlist/ids")
    public ApiResponse getWishlistIds(@AuthenticationPrincipal CustomUserDetails userDetails) {
        java.util.List<Wishlist> wishlists = wishlistRepository.findByUserId(userDetails.getUser().getId());
        java.util.List<Integer> ids = wishlists.stream().map(Wishlist::getPropertyId).collect(java.util.stream.Collectors.toList());
        return ApiResponse.success(ids);
    }

    @PostMapping("/wishlist/toggle")
    @Transactional
    public ApiResponse toggleWishlist(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody java.util.Map<String, Integer> payload) {
        Integer propertyId = payload.get("propertyId");
        Integer userId = userDetails.getUser().getId();
        
        java.util.List<Wishlist> existing = wishlistRepository.findByUserId(userId).stream()
                .filter(w -> w.getPropertyId().equals(propertyId)).toList();
                
        if (!existing.isEmpty()) {
            wishlistRepository.deleteByUserIdAndPropertyId(userId, propertyId);
            return ApiResponse.success(java.util.Map.of("status", "removed"));
        } else {
            Wishlist w = new Wishlist();
            w.setUserId(userId);
            w.setPropertyId(propertyId);
            wishlistRepository.save(w);
            return ApiResponse.success(java.util.Map.of("status", "added"));
        }
    }
}
