package com.airbnb.server.repository;

import com.airbnb.server.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByUserIdOrderByCreatedTimestampDesc(Integer userId);
}
