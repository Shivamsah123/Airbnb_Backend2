package com.airbnb.server.service;

import com.airbnb.server.entity.Booking;
import com.airbnb.server.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public void addBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByUser(Integer userId) {
        return bookingRepository.findByUserIdOrderByCreatedTimestampDesc(userId);
    }

    public void cancelBooking(Integer id, Integer userId) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null && booking.getUserId().equals(userId)) {
            booking.setStatus("cancelled");
            bookingRepository.save(booking);
        }
    }
}
