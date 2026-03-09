// File Created by Arun on Tuesday (3/3/26)

// Importing the necessary packages
package com.HospitalManagement.Entities;
import jakarta.persistence.*;

import java.time.LocalTime;

// Creation of Entity Table
@Entity
@Table(name = "request")
public class request {

    // Creation of Class Members
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String otp;

    @Column(nullable = false)
    private LocalTime startTime;

    // Creation of default constructor
    public request() {
    }

    // Creation of Parameterized constructor
    public request(String name, String email, String otp, LocalTime startTime) {
        this.name = name;
        this.email = email;
        this.otp = otp;
        this.startTime = startTime;
    }

    // Creation of second parameterized constructor
    public request(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Creation of Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
}
