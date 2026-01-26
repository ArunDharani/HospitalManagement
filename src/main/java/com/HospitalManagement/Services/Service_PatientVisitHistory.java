// File Created by Arun on Monday (26/1/26)

// Importing the necessary packages
package com.HospitalManagement.Services;
import com.HospitalManagement.DTOs.PatientVisitHistoryDTO;
import com.HospitalManagement.Entities.Doctor;
import com.HospitalManagement.Entities.Patient;
import com.HospitalManagement.Entities.PatientVisitHistory;
import com.HospitalManagement.RepositoryInterfaces.DoctorRepository;
import com.HospitalManagement.RepositoryInterfaces.PatientRepository;
import com.HospitalManagement.RepositoryInterfaces.PatientVisitHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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


    // Creation of constructor to initialize its value
    @Autowired
    public Service_PatientVisitHistory(PatientVisitHistoryRepository visitRepo , PatientRepository patRepo , DoctorRepository docRepo) {
        this._visitRepo = visitRepo;
        this._patientRepo = patRepo;
        this._docRepo = docRepo;
    }

    // Creation of all CRUD operations

        // Obtain all visit
    public List<PatientVisitHistory> showAllVisit() {
        return _visitRepo.findAll();
    }

        // Obtain particular visit
    public Optional<PatientVisitHistory> showThisVisit(Long id) {
        // First let us check such data exist or not
        Optional<PatientVisitHistory> currentData = _visitRepo.findById(id);

        if (currentData.isPresent()) {
            return currentData;
        } else {
            return null;
        }
    }

        // Create visit record
    public PatientVisitHistory createRecord(PatientVisitHistoryDTO visitHistory) {
        try {
            Optional<Patient> patient = _patientRepo.findById(visitHistory.getPatientId());
            if (patient.isPresent()) {
                Optional<Doctor> doctor = _docRepo.findById(visitHistory.getDoctorId());
                if (doctor.isPresent()) {
                    // Creation of instance and saving it
                    PatientVisitHistory newData = new PatientVisitHistory();
                    newData.setVisitDate(visitHistory.getVisitDate());
                    newData.setVisitTime(visitHistory.getVisitTime());
                    newData.setReason(visitHistory.getReason());
                    if (doctor.isPresent()) {
                        newData.setDoctor(doctor.get());
                    }
                    if (patient.isPresent()) {
                        newData.setPatient(patient.get());
                    }

                    // retuning the result
                    return _visitRepo.save(newData);
                } else {
                    System.out.println("No such doctor record exist");
                    return null;
                }
            } else {
                System.out.println("No such patient record exist");
                return null;
            }
        } catch (Exception exception) {
            System.out.println("Some Exception has occurred please look into it : \n"+exception);
            return null;
        }
    }

        // Updating visit record
        public PatientVisitHistory updateData(Long id, PatientVisitHistoryDTO visitHistory) {
            try {
                // Obtaining the current record
                Optional<PatientVisitHistory> Current = _visitRepo.findById(id);
                if ( Current.isPresent()) {
                    Optional<Patient> patient = _patientRepo.findById(visitHistory.getPatientId());
                    if (patient.isPresent()) {
                        Optional<Doctor> doctor = _docRepo.findById(visitHistory.getDoctorId());
                        if (doctor.isPresent()) {
                            PatientVisitHistory newData = Current.get();
                            newData.setVisitDate(visitHistory.getVisitDate());
                            newData.setVisitTime(visitHistory.getVisitTime());
                            newData.setReason(visitHistory.getReason());
                            if (doctor.isPresent()) {
                                newData.setDoctor(doctor.get());
                            }
                            if (patient.isPresent()) {
                                newData.setPatient(patient.get());
                            }

                            // retuning the result
                            return _visitRepo.save(newData);
                        } else {
                            System.out.println("No such doctor record exist");
                            return null;
                        }
                    } else {
                        System.out.println("No such patient record exist");
                        return null;
                    }
                } else {
                    System.out.println("No such record is found");
                    return null;
                }
            } catch (Exception exception) {
                System.out.println("Some Exception has occurred please look into it : \n"+exception);
                return null;
            }
        }

        // Deleting record
    public void removeData(Long id) {
        try {
            Optional<PatientVisitHistory> data = _visitRepo.findById(id);
            if (data.isEmpty()) {
                System.out.println("No such data exist");
            } else {
                _visitRepo.deleteById(id);
            }
        } catch (Exception exception) {
            System.out.println("Some Error has occurred please look into it : "+exception);
        }
    }
}
