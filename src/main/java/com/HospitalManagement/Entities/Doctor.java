// File created by Arun on Friday (23/1/26)

package com.HospitalManagement.Entities;
import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String specialist;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    // Creation of Default constructor
    public Doctor() {
        System.out.println("Doctor default constructor has been called");
    }

    // Creation of Parameterized constructor
    public Doctor(String name, String email, String specialist, LocalTime startTime, LocalTime endTime) {
        this.name = name;
        this.email = email;
        this.specialist = specialist;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialist() {
        return specialist;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
