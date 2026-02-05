// File Created by Arun on Friday (06/02/26)

// Importing the necessary packages
package com.HospitalManagement.Controller;

import com.HospitalManagement.DTOs.EmailDTO;
import com.HospitalManagement.Services.Service_Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Creation of controller class
@RestController
@RequestMapping("/api/Email")
public class EmailController {

    // Creation of instance of Service Email
    private final Service_Email serviceEmail;

    // Creation of constructor
    @Autowired
    public EmailController(Service_Email serviceEmail) {
        this.serviceEmail = serviceEmail;
    }

    @PostMapping("/sendMail")
    public ResponseEntity<String> sendMail(@RequestBody EmailDTO email) {
        serviceEmail.sendEmail(email);
        return ResponseEntity.ok("Mail has been sent");
    }
}
