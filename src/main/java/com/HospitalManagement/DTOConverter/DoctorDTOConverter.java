// File Created by Arun on Sunday (Feb - 1)

// Importing all the necessary packages
package com.HospitalManagement.DTOConverter;
import com.HospitalManagement.DTOs.DoctorDTO;
import com.HospitalManagement.Entities.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

// Converter class has been created
@Component
public class DoctorDTOConverter {

    // Creation of Constructor
    @Autowired
    public DoctorDTOConverter() {
        System.out.println("Hence the DoctorDTOConverter constructor has been called");
    }

    // Creation of Function to convert from Doctor Entity to Doctor DTO
    public DoctorDTO convertToDTO(Doctor doctor) {
        return new DoctorDTO(doctor.getId() , doctor.getName() , doctor.getEmail() , doctor.getSpecialist() , doctor.getStartTime() , doctor.getEndTime());
    }

    // Creation of Function to convert from Doctor DTO to Entity
    public Doctor convertToEntity(DoctorDTO doctorDTO) {
        return new Doctor(doctorDTO.getName() , doctorDTO.getEmail() , doctorDTO.getSpecialist() , doctorDTO.getStartTime() , doctorDTO.getEndTime());
    }

}
