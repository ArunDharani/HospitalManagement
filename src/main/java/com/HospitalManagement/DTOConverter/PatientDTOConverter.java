// File Created by Arun on Tuesday (3/2/26)

// Importing the necessary packages
package com.HospitalManagement.DTOConverter;

import com.HospitalManagement.DTOs.PatientDTO;
import com.HospitalManagement.Entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Creation of EntityDTO conversion class for Patient Entity
@Component
public class PatientDTOConverter {

    // Creation of constructor
    @Autowired
    public PatientDTOConverter() {
        System.out.println("Default constructor for PatientDTO converter has been called");
    }

    // Creation of function to convert from Entity to DTO
    public Patient DTOtoEntity(PatientDTO patientDTO) {
        return new Patient(patientDTO.getName(), patientDTO.getEmail(), patientDTO.getAddress(), patientDTO.getAge());
    }

    // Creation of function to convert from DTO to Entity
    public PatientDTO EntitytoDTO(Patient patient){
        return new PatientDTO(patient.getId(), patient.getName(), patient.getEmail(), patient.getAddress(), patient.getAge());
    }
}
