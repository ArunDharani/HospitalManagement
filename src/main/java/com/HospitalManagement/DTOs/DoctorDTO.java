// File Created by Arun on Saturday(31/1/26)

// Importing the necessary packages
package com.HospitalManagement.DTOs;
import java.time.LocalTime;

// Creation of Doctor DTO
public class DoctorDTO {

    // Creation of class Members
    private String name;
    private String email;
    private String specialist;
    private LocalTime startTime;
    private LocalTime endTime;

    // Creation of constructor
    public DoctorDTO(String name, String email, String specialist, LocalTime startTime, LocalTime endTime) {
        this.name = name;
        this.email = email;
        this.specialist = specialist;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Creation of Getter and Setter Methods
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

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
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
