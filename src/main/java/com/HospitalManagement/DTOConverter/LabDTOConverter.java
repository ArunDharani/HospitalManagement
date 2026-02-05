// File Created by Arun on (Thursday) Feb - 5 (2026)

// Importing the all necessary packages
package com.HospitalManagement.DTOConverter;

import com.HospitalManagement.DTOs.LabDTO;
import com.HospitalManagement.Entities.Lab;
import com.HospitalManagement.Services.Service_Doctor;
import com.HospitalManagement.Services.Service_Patient;
import com.HospitalManagement.Services.Service_Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Creation of Entity DTO converter for Lab class
@Component
public class LabDTOConverter {
    // Creating instance of Service_staff , Service_patient , Service_doctor
    private final Service_Doctor serviceDoctor;
    private final Service_Patient servicePatient;
    private final Service_Staff serviceStaff;

    // Creating instance of staff_converter , patient_converter , doctor_converter
    private final PatientDTOConverter patientDTOConverter;
    private final DoctorDTOConverter doctorDTOConverter;
    private final StaffDTOConverter staffDTOConverter;

    // Creation of constructor
    @Autowired
    public LabDTOConverter(Service_Patient servicePatient , Service_Staff serviceStaff , Service_Doctor serviceDoctor , PatientDTOConverter patientDTOConverter , DoctorDTOConverter doctorDTOConverter , StaffDTOConverter staffDTOConverter) {
        System.out.println("Creation of Converter for Lab Class");
        this.serviceDoctor = serviceDoctor;
        this.servicePatient = servicePatient;
        this.serviceStaff = serviceStaff;
        this.doctorDTOConverter = doctorDTOConverter;
        this.staffDTOConverter = staffDTOConverter;
        this.patientDTOConverter = patientDTOConverter;
    }

    // Creation of function to convert from Entity to DTO
    public LabDTO  convertToDTO(Lab lab) {
        return new LabDTO(lab.getId() , lab.getPatient().getId(), lab.getStaff().getId() , lab.getDoctor().getId(), lab.getSessionDate() ,lab.getStartTime() , lab.getEndTime() , lab.getTestResult());
    }

    // Creation of function to convert from DTO to Entity
    public Lab convertToEntity(LabDTO labDTO) {
        return new Lab(labDTO.getSessionDate() , labDTO.getStartTime() , labDTO.getEndTime() , labDTO.getTestResult(), patientDTOConverter.DTOtoEntity(servicePatient.showPatientDetail(labDTO.getPatientId())) , doctorDTOConverter.convertToEntity(serviceDoctor.showDetail(labDTO.getDoctorId())) , staffDTOConverter.convetToEntity(serviceStaff.getstaffDetail(labDTO.getStaffId())));
    }
}
