// File Created by Arun on Tuesday (10/02/26)

// Importing the necessary packages
package com.HospitalManagement.DTOs;

// Creation of 'AdminDTO' class
public class AdminDTO {

    // Creation of class Members
    private Long id;
    private String email;
    private String password;
    private String name;

    // Creation of default constructor
    public AdminDTO() {
        System.out.println("Creation of default AdminDTO");
    }

    // Creation of parameterized constructor
    public AdminDTO(Long id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    // Creation of Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
