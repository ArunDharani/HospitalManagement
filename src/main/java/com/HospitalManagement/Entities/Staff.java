// File Created by Arun on Sunday (25/1/26)

// Importing the necessary packages
package com.HospitalManagement.Entities;

import jakarta.persistence.*;

import java.time.LocalTime;

// Creation of Staff Entity Class
@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    // Creation of default Constructor
    public Staff() {
        System.out.println("Hence the constructor for Staff has been called");
    }

    // Creation of Parameterized construtor
    public Staff(String name , String email , LocalTime startTime , LocalTime endTime) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
