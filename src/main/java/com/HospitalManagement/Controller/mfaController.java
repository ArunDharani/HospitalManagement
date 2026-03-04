// File Created by Arun on Tuesday (3/3/36)

// Importing the necessary packages
package com.HospitalManagement.Controller;
import com.HospitalManagement.DTOs.IntermediateDTO;
import com.HospitalManagement.Entities.TestData;
import com.HospitalManagement.Services.MultiFactor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Creation of 'mfaController'
@RestController
@RequestMapping("/mfa")
public class mfaController {

    // Creation of mfa instance
    private final MultiFactor multiFactor;

    // Creation of Constructor
    @Autowired
    public mfaController(MultiFactor multiFactor) {
        this.multiFactor = multiFactor;
    }

    // now creation of two postMapping methods
    @PostMapping("/sendotp")
    public void sendOTP(@RequestBody TestData testData){
        multiFactor.sendOTP(testData);
    }

    @PostMapping("/verify")
    public String verifyOTP(@RequestBody IntermediateDTO intermediateDTO){
        multiFactor.verifyOTP(intermediateDTO);
        return "answer";
    }
}
