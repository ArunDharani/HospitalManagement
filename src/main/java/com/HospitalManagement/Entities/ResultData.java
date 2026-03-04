// File Created by Arun on Tuesday (3/3/26)

// Importing the necessary packages
package com.HospitalManagement.Entities;
import jakarta.persistence.*;

// Creation of 'response' table
@Entity
@Table(name = "response")
public class ResultData {

    // Creation of Class Members
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    // Creation of default constructor
    public ResultData(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Creation of default constructor
    public ResultData() {
    }

    // Creation of Getters and setters
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
}
