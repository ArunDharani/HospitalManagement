// File Created by Arun on Wednesday (4/2/26)

// Importing all the necessary packages
package com.HospitalManagement.DTOConverter;
import com.HospitalManagement.DTOs.PatientVisitHistoryDTO;
import com.HospitalManagement.Entities.PatientVisitHistory;
import org.springframework.stereotype.Component;

// Creating EntityDTO Converter for 'patientVisitHistory'
@Component
public class PatientVisitHistoryConverter {

    // Creation of

    // Creation of function to convert Entity To DTO
    public PatientVisitHistoryDTO convertToDTO(PatientVisitHistory patientVisitHistory) {
        return new PatientVisitHistoryDTO(patientVisitHistory.getId() , patientVisitHistory.getVisitDate() ,patientVisitHistory.getVisitTime() ,patientVisitHistory.getPatient().getId() , patientVisitHistory.getDoctor().getId() , patientVisitHistory.getReason());
    }

    // Creation of function to convert from DTO to Entity
    public PatientVisitHistory convertToEntity(PatientVisitHistoryDTO patientVisitHistoryDTO) {
        return new PatientVisitHistory(patientVisitHistoryDTO.getVisitDate() , patientVisitHistoryDTO.getVisitTime() ,patientVisitHistoryDTO.get)
    }
}
