package com.airbnb.server.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "firstName", length = 20)
    private String firstName;

    @Column(name = "lastName", length = 20)
    private String lastName;

    @Column(length = 50, unique = true)
    private String email;

    @Column(length = 100)
    private String password;

    @Column(name = "phoneNumber", length = 15)
    private String phoneNumber;

    @Column(length = 10)
    private String role = "user";

    @Column(name = "isDeleted")
    private Integer isDeleted = 0;

    @CreationTimestamp
    @Column(name = "createdTimestamp", updatable = false)
    private LocalDateTime createdTimestamp;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Integer getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Integer isDeleted) { this.isDeleted = isDeleted; }

    public LocalDateTime getCreatedTimestamp() { return createdTimestamp; }
    public void setCreatedTimestamp(LocalDateTime createdTimestamp) { this.createdTimestamp = createdTimestamp; }
}
