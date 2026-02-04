// File Created by Arun on Saturday (31/1/26)

// Importing the necessary packages
package com.HospitalManagement.DTOs;

// Creation of PatientDTO
public class PatientDTO {

    // Creation of class Members
    private Long id;
    private String name;
    private String email;
    private String address;
    private int age;

    // Creation of Default constructor
    public PatientDTO() {
        System.out.println("Called default constructor of PaitentDTO");
    }

    // Creation of constructor
    public PatientDTO(Long id , String name, String email, String address, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.age = age;
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
