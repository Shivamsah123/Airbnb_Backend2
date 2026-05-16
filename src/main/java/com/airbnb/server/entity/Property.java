package com.airbnb.server.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "categoryId")
    private Integer categoryId;

    @Column(length = 100)
    private String title;

    @Column(length = 1000)
    private String details;

    @Column(length = 1000)
    private String address;

    @Column(name = "contactNo", length = 15)
    private String contactNo;

    @Column(name = "ownerName", length = 50)
    private String ownerName;

    @Column(name = "isLakeView")
    private Integer isLakeView = 0;

    @Column(name = "isTV")
    private Integer isTV = 0;

    @Column(name = "isAC")
    private Integer isAC = 0;

    @Column(name = "isWifi")
    private Integer isWifi = 0;

    @Column(name = "isMiniBar")
    private Integer isMiniBar = 0;

    @Column(name = "isBreakfast")
    private Integer isBreakfast = 0;

    @Column(name = "isParking")
    private Integer isParking = 0;

    private Integer guests;
    private Integer bedrooms;
    private Integer beds;
    private Integer bathrooms;
    private Double rent;

    @Column(name = "profileImage", length = 100)
    private String profileImage;

    @CreationTimestamp
    @Column(name = "createdTimestamp", updatable = false)
    private LocalDateTime createdTimestamp;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getContactNo() { return contactNo; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public Integer getIsLakeView() { return isLakeView; }
    public void setIsLakeView(Integer isLakeView) { this.isLakeView = isLakeView; }

    public Integer getIsTV() { return isTV; }
    public void setIsTV(Integer isTV) { this.isTV = isTV; }

    public Integer getIsAC() { return isAC; }
    public void setIsAC(Integer isAC) { this.isAC = isAC; }

    public Integer getIsWifi() { return isWifi; }
    public void setIsWifi(Integer isWifi) { this.isWifi = isWifi; }

    public Integer getIsMiniBar() { return isMiniBar; }
    public void setIsMiniBar(Integer isMiniBar) { this.isMiniBar = isMiniBar; }

    public Integer getIsBreakfast() { return isBreakfast; }
    public void setIsBreakfast(Integer isBreakfast) { this.isBreakfast = isBreakfast; }

    public Integer getIsParking() { return isParking; }
    public void setIsParking(Integer isParking) { this.isParking = isParking; }

    public Integer getGuests() { return guests; }
    public void setGuests(Integer guests) { this.guests = guests; }

    public Integer getBedrooms() { return bedrooms; }
    public void setBedrooms(Integer bedrooms) { this.bedrooms = bedrooms; }

    public Integer getBeds() { return beds; }
    public void setBeds(Integer beds) { this.beds = beds; }

    public Integer getBathrooms() { return bathrooms; }
    public void setBathrooms(Integer bathrooms) { this.bathrooms = bathrooms; }

    public Double getRent() { return rent; }
    public void setRent(Double rent) { this.rent = rent; }

    public String getProfileImage() { return profileImage; }
    public void setProfileImage(String profileImage) { this.profileImage = profileImage; }

    public LocalDateTime getCreatedTimestamp() { return createdTimestamp; }
    public void setCreatedTimestamp(LocalDateTime createdTimestamp) { this.createdTimestamp = createdTimestamp; }
}
