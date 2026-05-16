package com.airbnb.server.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "propertyId")
    private Integer propertyId;

    @CreationTimestamp
    @Column(name = "createdTimestamp", updatable = false)
    private LocalDateTime createdTimestamp;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getPropertyId() { return propertyId; }
    public void setPropertyId(Integer propertyId) { this.propertyId = propertyId; }

    public LocalDateTime getCreatedTimestamp() { return createdTimestamp; }
    public void setCreatedTimestamp(LocalDateTime createdTimestamp) { this.createdTimestamp = createdTimestamp; }
}
