// File Created by Arun on Monday (09/02/26)

// Importing the necessary packages
package com.HospitalManagement.Entities;
import jakarta.persistence.*;

// Creation of Admin Class

@Entity
@Table(name = "admin")
public class Admin {

    // Creation of Class Members
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // Creation of Default constructor
    public Admin() {
        System.out.println("Calling default constructor from Admin Class");
    }

    // Creation of Parameterized constructor
    public Admin(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
