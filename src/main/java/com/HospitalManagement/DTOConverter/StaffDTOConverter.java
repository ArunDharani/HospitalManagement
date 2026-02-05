// File Created by Arun on Tuesday (3/1/26)

// Importing the necesary packages
package com.HospitalManagement.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import com.HospitalManagement.DTOs.StaffDTO;
import com.HospitalManagement.Entities.Staff;
import org.springframework.stereotype.Component;

// Creation of Entity to DTO converter
@Component
public class StaffDTOConverter {
    
    // Creation of Constructor
    @Autowired
    public StaffDTOConverter() {
        System.out.println("Creation of constructor of StaffDTOConverter");
    }

    // Creation of Function to convert DTO to Entity
    public Staff convetToEntity(StaffDTO staffDTO) {
        return new Staff(staffDTO.getName() , staffDTO.getEmail() , staffDTO.getStartTime() , staffDTO.getEndTime());
    }

    // Creation of Function to convert Entity to DTO
    public StaffDTO convertToDTO(Staff staff) {
        return new StaffDTO(staff.getId() , staff.getName() , staff.getEmail() , staff.getStartTime() , staff.getEndTime());
    }

}
