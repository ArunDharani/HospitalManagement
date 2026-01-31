// File Created by Arun on Saturday (31/1/26)

// Importing the necessary packages
package com.HospitalManagement.DTOs;

// Creation of PatientDTO
public class PatientDTO {

    // Creation of class Members
    private String name;
    private String email;
    private String address;
    private int age;

    // Creation of constructor
    public PatientDTO(String name, String email, String address, int age) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.age = age;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
