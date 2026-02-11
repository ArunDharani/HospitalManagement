// File Created by Arun on Tuesday (10/02/26)

// Importing the necessary packages
package com.HospitalManagement.Controller;
import com.HospitalManagement.DTOs.AdminDTO;
import com.HospitalManagement.Services.Service_Jwt;
import org.springframework.web.bind.annotation.*;

// Creation of controller
@RestController
@RequestMapping("/auth")
public class AuthController {

    // Creation of instance of jwtService
    private final Service_Jwt serviceJwt;

    // Creation of constructor
    public AuthController(Service_Jwt serviceJwt) {
        this.serviceJwt = serviceJwt;
    }

    // Creation of token
    @PostMapping("/test")
    public String getToken(@RequestBody AdminDTO adminDTO) {
        try {
            return serviceJwt.generateToken(adminDTO);
        } catch (Exception e) {
            throw new RuntimeException("Cause : "+e.getCause()+"\nMessage : "+e.getMessage()+"\nPath : "+e.getStackTrace());
        }
    }

    // Testing Api Method
    @GetMapping("/obtain")
    public String getData(@RequestHeader("Authorization") String token) {
        try {
            if (token != null && token.startsWith("Bearer ")) {
                return serviceJwt.extractUserEmail(token.substring(7));
            } else {
                return "record type is invalid";
            }
        } catch (Exception exception) {
            throw new RuntimeException("Some error has occured please look into this :"+exception.getMessage());
        }
    }

    // My random comments from daughter branch
}
