package com.airbnb.server.repository;

import com.airbnb.server.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
    @Query("SELECT p FROM Property p WHERE p.title LIKE %:search% OR p.details LIKE %:search% OR p.address LIKE %:search%")
    List<Property> searchProperties(@Param("search") String search);
}
