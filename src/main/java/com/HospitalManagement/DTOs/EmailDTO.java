// File Created by Arun on Friday (06/02/26)

// Importing the necessary packages
package com.HospitalManagement.DTOs;

// Creation of DTO class for EmailDTO
public class EmailDTO {

    // Creation of class Members
    private Long id;
    private String receiver;
    private String message;
    private String subject;

    // Creation of default constructor
    public EmailDTO() {
        System.out.println("Calling the Default constructor of EmailDTO");
    }

    // Creation of Parameterized constructor
    public EmailDTO(Long id, String receiver, String message, String subject) {
        this.id = id;
        this.receiver = receiver;
        this.message = message;
        this.subject = subject;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
