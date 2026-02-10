// File Created by Arun on Tuesday (10/02/26)

// Importing the necessary packages
package com.HospitalManagement.DTOConverter;
import com.HospitalManagement.DTOs.AdminDTO;
import com.HospitalManagement.Entities.Admin;
import org.springframework.stereotype.Component;

// Creation of Conversion class
@Component
public class AdminDTOConverter {

    // Creation of Default constructor
    public AdminDTOConverter() {
        System.out.println("Calling the constructor of ");
    }

    // Creation of Function to convert Entity to DTO
    public Admin convertToEntity(AdminDTO adminDTO) {
        return new Admin(adminDTO.getName() , adminDTO.getEmail() , adminDTO.getPassword());
    }

    // Creation of Function ot convert DTO to Entity
    public AdminDTO convertToDTO(Admin admin) {
        return new AdminDTO(admin.getId() , admin.getEmail() , admin.getPassword() , admin.getName());
    }
}
