// File Created by Arun on Tuesday (10/02/26)

// Importing the necessary packages
package com.HospitalManagement.Controller;
import com.HospitalManagement.DTOs.AdminDTO;
import com.HospitalManagement.Services.Service_Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
            throw new RuntimeException("Some error has occurred please look into this :"+exception.getMessage());
        }
    }

    // Creation of api for getting all the admins
    @GetMapping("/allAdmin")
    public ResponseEntity<List<AdminDTO>> getAllAdmin(@RequestHeader("Authorization") String token){
            return ResponseEntity.ok(serviceJwt.viewAllAdmins(token));
    }

    // Creation of Api to insert new Admin Data
    @PostMapping("/appointAdmin")
    public ResponseEntity<String> hireAdmin(@RequestHeader("Authorization") String token , @RequestBody AdminDTO adminDTO) {
        return ResponseEntity.ok(serviceJwt.createAdmin(token , adminDTO));
    }

}
