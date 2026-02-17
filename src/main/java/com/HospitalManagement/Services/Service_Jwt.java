// File created by Arun on Tuesday (10/02/26)

// Importing the necessary packages
package com.HospitalManagement.Services;
import com.HospitalManagement.DTOConverter.AdminDTOConverter;
import com.HospitalManagement.DTOs.AdminDTO;
import com.HospitalManagement.Entities.Admin;
import com.HospitalManagement.RepositoryInterfaces.AdminRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    // Creation of instance of AdminEntityDTO converter
    private final AdminDTOConverter converter;

    // Creation of constructor to utilize
    public Service_Jwt(AdminRepository adminRepository , AdminDTOConverter _converter) {
        this.adminRepository = adminRepository;
        this.converter = _converter;
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

    // Creation of function to save user to see all the admins
    public List<AdminDTO> viewAllAdmins(String token) {
        try {
            // Let us verify the token
            if (token != null && token.startsWith("Bearer ")) {
                String Email = extractUserEmail(token.substring(7));
                if (Email.contains("@admin.com")) {
                    // Creation of array to store the AdminDTO
                    List<AdminDTO> adminsDTO = new ArrayList<>();

                    // Now converting each 'Admin' object to AdminDTO object
                    adminRepository.findAll().forEach(currentAdmin ->  {
                        adminsDTO.add(converter.convertToDTO(currentAdmin));
                    });

                    // Returning the results
                    return adminsDTO;
                } else {
                    throw new RuntimeException("Unauthorized");
                }
            } else {
                throw new RuntimeException("Token is invalid");
            }
        } catch (Exception exception) {
            throw new RuntimeException("Some error has occurred in 'viewAllAdmins' function in Service_Jwt : \n"+exception.getMessage());
        }
    }

    // Creation of Function to create new Admin
    public String createAdmin(String token , AdminDTO adminDTO) {
        try {
            // Let us verify the token
            if (token != null && token.startsWith("Bearer ")) {
                String Email = extractUserEmail(token.substring(7));
                if (Email.contains("@admin.com")) {

                    Admin admin = converter.convertToEntity(adminDTO);

                    // Let us cross-check the email for validation
                    if ( !admin.getEmail().contains("@admin.com")) {
                        throw new RuntimeException("It is not a proper email");
                    }

                    // Let us prevent only unique email is allowed
                    adminRepository.findAll().forEach(admin1 -> {
                        if (admin1.getEmail().equals(adminDTO.getEmail())) {
                            throw new RuntimeException("Already user exists");
                        }
                    });

                    // Saving the data in the database
                    adminRepository.save(admin);


                    // returning the result
                    return "New Admin Data has been included";

                } else {
                    throw new RuntimeException("Unauthorized");
                }
            } else {
                throw new RuntimeException("Token is invalid");
            }
        } catch (Exception exception) {
            throw new RuntimeException("Some error has occurred in 'viewAllAdmins' function in Service_Jwt : \n"+exception.getMessage());
        }
    }


}
