// File Created by Arun on Wednesday (4/3/26)

// Importing the necessary packages
package com.HospitalManagement.DTOs;

// Creation of intermediateDTO file
public class IntermediateDTO {

    // Creation of class Members
    private String email;
    private String otp;

    // Creation of constructor
    public IntermediateDTO(String email, String otp) {
        this.email = email;
        this.otp = otp;
    }

    // Creation of Getters and Setters
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
