// File Created by Arun on Monday (26/1/26)

// Importing the necessary packages
package com.HospitalManagement.Services;
import com.HospitalManagement.DTOConverter.PatientVisitHistoryConverter;
import com.HospitalManagement.DTOs.PatientVisitHistoryDTO;
import com.HospitalManagement.Entities.Doctor;
import com.HospitalManagement.Entities.Patient;
import com.HospitalManagement.Entities.PatientVisitHistory;
import com.HospitalManagement.RepositoryInterfaces.DoctorRepository;
import com.HospitalManagement.RepositoryInterfaces.PatientRepository;
import com.HospitalManagement.RepositoryInterfaces.PatientVisitHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Creation of 'service layer'
@Service
public class Service_PatientVisitHistory {

    // Creation of instance of PatientVisitHistory Repo
    private final PatientVisitHistoryRepository _visitRepo;

    // Creation of instance of PatientRepo and Doctor Repo
    private final PatientRepository _patientRepo;
    private final DoctorRepository _docRepo;

    // Creation of instance of PatientVisitHistoryConverter
    private final PatientVisitHistoryConverter _converter;

    // Creation of constructor to initialize its value
    @Autowired
    public Service_PatientVisitHistory(PatientVisitHistoryRepository visitRepo , PatientRepository patRepo , DoctorRepository docRepo , PatientVisitHistoryConverter converter) {
        this._visitRepo = visitRepo;
        this._patientRepo = patRepo;
        this._docRepo = docRepo;
        this._converter = converter;
    }

    // Creation of all CRUD operations

        // Obtain all visit
    public List<PatientVisitHistoryDTO> showAllVisit() {
        // Convert all the visit history into DTO
        try {
            // Array to convert entity to DTO
            List<PatientVisitHistoryDTO> result = new ArrayList<PatientVisitHistoryDTO>();

            _visitRepo.findAll().forEach(visit -> {
                result.add(_converter.convertToDTO(visit));
            });

            // returning the result
            return result;
        } catch (Exception exception) {
            throw new RuntimeException("some exception has occurred in the 'showAllVisit' in Service_PatientVisitHistory : \n"+exception.getMessage());
        }
    }

        // Obtain particular visit
    public PatientVisitHistoryDTO showThisVisit(Long id) {
        try {
            Optional<PatientVisitHistory> visit = _visitRepo.findById(id);

            // If exist proceeding further
            if(visit.isPresent()) {
                return _converter.convertToDTO(visit.get());
            } else {
                throw new RuntimeException("No such visit history exist");
            }

        } catch (Exception exception) {
            throw new RuntimeException("Some exception has occurred in 'showThisVisit' in Service_PatientVisitHistory : \n"+exception.getMessage());
        }
    }

        // Create visit record
    public String createRecord(PatientVisitHistoryDTO visitHistory) {
        try {
            Optional<Patient> patient = _patientRepo.findById(visitHistory.getPatientId());
            System.out.println("Patient id : "+visitHistory.getPatientId());
            if (patient.isPresent()) {
                Optional<Doctor> doctor = _docRepo.findById(visitHistory.getDoctorId());
                if (doctor.isPresent()) {

                    // Converting the DTO into Entity and Saving the result
                    PatientVisitHistory visit = new PatientVisitHistory(visitHistory.getVisitDate() , visitHistory.getVisitTime() , patient.get() , doctor.get() , visitHistory.getReason());

                    _visitRepo.save(visit);

                    // returning the result
                    return "No visit history have been saved successfully";

                } else {
                    return "No such doctor record exist in the parent Database";
                }
            } else {
                return "No such patient record exist in parent database";
            }
        } catch (Exception exception) {
            throw new RuntimeException("Some issue has occurred in the 'createRecord' of Service_PatientVisitHistory : \n"+exception.getMessage());
        }
    }

        // Updating visit record
        public String updateData(Long id, PatientVisitHistoryDTO visitHistory) {
            try {
                // First let us check such visit History exists
                Optional<PatientVisitHistory> visit = _visitRepo.findById(id);
                if (visit.isPresent()) {
                    // First let us check such patient details exist
                    Optional<Patient> patient = _patientRepo.findById(visitHistory.getPatientId());
                    if (patient.isPresent()) {
                        // Finally let us check such doctor record exist
                        Optional<Doctor> doctor = _docRepo.findById(visitHistory.getDoctorId());
                        if (doctor.isPresent()) {
                              // Obtaining the record
                              PatientVisitHistory currentVisit = _converter.convertToEntity(visitHistory , "ok");

                              // Now let us update the details
                              PatientVisitHistory newVisit = visit.get();
                              newVisit.setVisitTime(currentVisit.getVisitTime());
                              newVisit.setPatient(patient.get());
                              newVisit.setVisitDate(currentVisit.getVisitDate());
                              newVisit.setDoctor(doctor.get());
                              newVisit.setReason(currentVisit.getReason());

                              // saving the result
                              _visitRepo.save(newVisit);

                              // returning the result
                              return "No data has been updated";
                        } else {
                            throw new RuntimeException("No such doctor record exists");
                        }
                    } else {
                        throw new RuntimeException("No such patient record exists");
                    }
                } else {
                    throw new RuntimeException("No such visit history exist");
                }
            } catch (Exception exception) {
                return "Some exception has occurred in the 'updateData' function in Service_PatientVisitHistory : \n"+exception.getMessage();
            }
        }

        // Deleting record
    public String removeData(Long id) {
        try {
            // Checking that particular id exist
            Optional<PatientVisitHistory> visit = _visitRepo.findById(id);

            if (visit.isPresent()) {
                _visitRepo.deleteById(id);
                return "Data has been removed successfully";
            } else {
                return "No such data exist";
            }
        } catch (Exception exception) {
            return "Some exception has occurred in 'removeData' of Service_PatientVisitHistory"+exception.getMessage();
        }
    }
}
