// File created by Arun on Tuesday (10/02/26)

// Importing the necessary packages
package com.HospitalManagement.Services;
import com.HospitalManagement.DTOs.AdminDTO;
import com.HospitalManagement.RepositoryInterfaces.AdminRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

// Creation of 'Service_Class' for utilizing Service_Jwt
@Service
public class Service_Jwt {

    // Now let us get the data from the Application.Properties
    @Value("${jwt.expiration}")
    private long expirationTime;

    @Value("${jwt.secret}")
    private String secretKey;

    // Creation of instance of Admin repo
    private final AdminRepository adminRepository;

    // Creation of constructor to utilize
    public Service_Jwt(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // Creation of Function to generate token when AdminDTO is given
    public String generateToken(AdminDTO adminDTO) {
        return Jwts.builder()
                .setSubject(adminDTO.getEmail())
                .setAudience(adminDTO.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }

    // Creation of Function to extract userEmail
    public String extractUserEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Creation of Function to extract userName
    public String extractUserName(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getAudience();
    }


}
