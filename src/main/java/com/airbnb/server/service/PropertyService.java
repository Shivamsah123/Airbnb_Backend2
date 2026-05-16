package com.airbnb.server.service;

import com.airbnb.server.entity.Property;
import com.airbnb.server.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public void addProperty(Property property) {
        propertyRepository.save(property);
    }

    public List<Property> getAllProperties(String search) {
        if (search != null && !search.trim().isEmpty()) {
            return propertyRepository.searchProperties(search);
        }
        return propertyRepository.findAll();
    }

    public Property getPropertyDetails(Integer id) {
        return propertyRepository.findById(id).orElse(null);
    }

    public void deleteProperty(Integer id) {
        propertyRepository.deleteById(id);
    }
}
