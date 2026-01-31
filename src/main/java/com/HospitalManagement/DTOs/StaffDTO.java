// File Created by Arun on Satuday (31/1/26)

// Importing the necessary packages
package com.HospitalManagement.DTOs;
import java.time.LocalTime;

// Creation of StaffDTO
public class StaffDTO {

    // Creation of class Members
    private String name;
    private String email;
    private LocalTime starTime;
    private LocalTime endTime;

    // Creation of parameterized constructor
    public StaffDTO(String name, String email, LocalTime starTime, LocalTime endTime) {
        this.name = name;
        this.email = email;
        this.starTime = starTime;
        this.endTime = endTime;
    }

    // Creation of Getters and Setters
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

    public LocalTime getStarTime() {
        return starTime;
    }

    public void setStarTime(LocalTime starTime) {
        this.starTime = starTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
