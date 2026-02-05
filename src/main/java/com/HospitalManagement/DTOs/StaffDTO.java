// File Created by Arun on Satuday (31/1/26)

// Importing the necessary packages
package com.HospitalManagement.DTOs;
import java.time.LocalTime;

// Creation of StaffDTO
public class StaffDTO {

    // Creation of class Members
    private Long id;
    private String name;
    private String email;
    private LocalTime startTime;
    private LocalTime endTime;

    // Creation of default constructor
    public StaffDTO() {
        System.out.println("Creation of default construcotor for StaffDTO");
    }

    // Creation of parameterized constructor
    public StaffDTO(Long id , String name, String email, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
