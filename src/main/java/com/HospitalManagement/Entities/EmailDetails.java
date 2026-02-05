// File Created by Arun on Friday (06/02/26)

// Importing the necessary packages
package com.HospitalManagement.Entities;

import jakarta.persistence.*;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

// Creation of Entity File for mail
@Entity
@Table(name = "emailDetails")
public class EmailDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String receiver;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String message;

    // Creation of Default Constructor
    public EmailDetails() {
        System.out.println("Default mail constructor has been called");
    }

    // Creation of Parameterized constructor
    public EmailDetails(String receiver, String subject, String message) {
        this.receiver = receiver;
        this.subject = subject;
        this.message = message;
    }

    // Creation of Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
