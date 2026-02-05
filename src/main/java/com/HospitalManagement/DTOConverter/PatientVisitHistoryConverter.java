// File Created by Arun on Wednesday (4/2/26)

// Importing all the necessary packages
package com.HospitalManagement.DTOConverter;
import com.HospitalManagement.DTOs.PatientVisitHistoryDTO;
import com.HospitalManagement.Entities.PatientVisitHistory;
import com.HospitalManagement.Services.Service_Doctor;
import com.HospitalManagement.Services.Service_Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Creating EntityDTO Converter for 'patientVisitHistory'
@Component
public class PatientVisitHistoryConverter {

    // Creation of instance of the patientDTOConverter and DoctorDTOConverter
    private final PatientDTOConverter patientDTOConverter;
    private final DoctorDTOConverter doctorDTOConverter;

    // Creation of instance of patient and doctor services
    private final Service_Doctor serviceDoctor;
    private final Service_Patient servicePatient;

    // Creation of constructor for dependency injection
    @Autowired
    public PatientVisitHistoryConverter(PatientDTOConverter _patientDTOConverter , DoctorDTOConverter _doctorDTOConverter , Service_Patient servicePatient , Service_Doctor serviceDoctor) {
        this.doctorDTOConverter = _doctorDTOConverter;
        this.patientDTOConverter = _patientDTOConverter;
        this.servicePatient = servicePatient;
        this.serviceDoctor = serviceDoctor;
    }

    // Creation of function to convert Entity To DTO
    public PatientVisitHistoryDTO convertToDTO(PatientVisitHistory patientVisitHistory) {
        try {
            return new PatientVisitHistoryDTO(patientVisitHistory.getId(), patientVisitHistory.getVisitDate() , patientVisitHistory.getVisitTime() , patientVisitHistory.getPatient().getId() , patientVisitHistory.getDoctor().getId() , patientVisitHistory.getReason());
        } catch (Exception exception) {
            throw new RuntimeException("Some error has occurred in 'convertToDTO in patientVisit converter : '"+exception.getMessage());
        }
    }

    // Creation of function to convert from DTO to Entity
    public PatientVisitHistory convertToEntity(PatientVisitHistoryDTO patientVisitHistoryDTO) {
        return new PatientVisitHistory(patientVisitHistoryDTO.getVisitDate() , patientVisitHistoryDTO.getVisitTime() , patientDTOConverter.DTOtoEntity(servicePatient.showPatientDetail(patientVisitHistoryDTO.getPatientId())) ,  doctorDTOConverter.convertToEntity(serviceDoctor.showDetail(patientVisitHistoryDTO.getDoctorId())) , patientVisitHistoryDTO.getReason());
    }
}
