// File Created by Arun on Tuesday (3/3/26)

// Importing the necessary packages
package com.HospitalManagement.Entities;
import jakarta.persistence.*;

// Creation of Entity Table
@Entity
@Table(name = "request")
public class TestData {

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

    // Creation of default constructor
    public TestData() {
    }

    // Creation of Parameterized constructor
    public TestData(String name, String email, String otp) {
        this.name = name;
        this.email = email;
        this.otp = otp;
    }

    // Creation of second parameterized constructor
    public TestData(String name, String email) {
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
}
