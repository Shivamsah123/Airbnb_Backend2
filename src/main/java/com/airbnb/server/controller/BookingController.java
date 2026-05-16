package com.airbnb.server.controller;

import com.airbnb.server.dto.ApiResponse;
import com.airbnb.server.entity.Booking;
import com.airbnb.server.security.CustomUserDetails;
import com.airbnb.server.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ApiResponse addBooking(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody Booking booking) {
        try {
            booking.setUserId(userDetails.getUser().getId());
            bookingService.addBooking(booking);
            return ApiResponse.success("Booking successful");
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    @GetMapping
    public ApiResponse getBookings(@AuthenticationPrincipal CustomUserDetails userDetails) {
        try {
            return ApiResponse.success(bookingService.getBookingsByUser(userDetails.getUser().getId()));
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse cancelBooking(@PathVariable Integer id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        try {
            bookingService.cancelBooking(id, userDetails.getUser().getId());
            return ApiResponse.success("Booking cancelled");
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }
}
