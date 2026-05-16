package com.airbnb.server.repository;

import com.airbnb.server.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByPropertyId(Integer propertyId);
}
