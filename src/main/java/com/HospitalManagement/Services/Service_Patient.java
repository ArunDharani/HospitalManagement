// File Created by Arun on Sunday (25/1/26)

// Importing the necessary packages
package com.HospitalManagement.Services;
import com.HospitalManagement.DTOConverter.PatientDTOConverter;
import com.HospitalManagement.DTOs.PatientDTO;
import com.HospitalManagement.Entities.Patient;
import com.HospitalManagement.RepositoryInterfaces.PatientRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Creation of 'Service Class' with 'Patient'
@Service
public class Service_Patient {

    // Creation of service instance
    private final PatientRepository _patientRepo;

    // Creation of PatientDTO Converter instance
    private final PatientDTOConverter _converter;

    // Creation of constructor
    @Autowired
    public Service_Patient(PatientRepository _patRepo , PatientDTOConverter converter) {
        this._converter = converter;
        this._patientRepo = _patRepo;
    }

    // Creation of new Patient Details in the database
    public ResponseEntity<@NotNull  String> admitPatient(PatientDTO _patient){
        try {
            // Creation of new Patient instance
            Patient newPatient = _converter.DTOtoEntity(_patient);

            // Cross-Checking email validation and uniqueness
            if ( !newPatient.getEmail().contains(".com") || !newPatient.getEmail().contains(String.valueOf('@'))) {
                throw new RuntimeException("Provide valid email");
            }

            _patientRepo.findAll().forEach(patient -> {
                if (patient.getEmail().equals(newPatient.getEmail())) {
                    throw new RuntimeException("Patient already exists");
                }
            });

            // saving the result
            _patientRepo.save(newPatient);

            // Returning the result
            return ResponseEntity.ok("New patient details has been created successfully");

        } catch (Exception exception) {
            return ResponseEntity.ok("Some exception has occurred while creating new patient record in 'admitPatient' function in Service_patient : \n"+exception.getMessage());
        }
    }

    // Obtaining all available patient
    public List<PatientDTO> showAllPatient() {
        try {
            // Creation of array to store all the DTO
            List<PatientDTO> _allPatients = new ArrayList<>();

            // Now converting all patient Entity to patientDTO
            _patientRepo.findAll().forEach(patient -> _allPatients.add(_converter.EntitytoDTO(patient)));

            // returning the result
            return _allPatients;

        } catch (Exception exception) {
            throw new RuntimeException("Some exception has occurred while obtaining all the patient details in 'showAllPatient' function in Service Patient : \n"+exception.getMessage());
        }
    }

    // Obtaining only specific patient
    public PatientDTO showPatientDetail(Long id) {
        try {
            // Let us check that particular data exist
            Optional<Patient> patient = _patientRepo.findById(id);

            if (patient.isPresent()) {
                return _converter.EntitytoDTO(patient.get());
            } else {
                throw new RuntimeException("No such patient record exist as per Patient Database + ");
            }
        } catch (Exception exception) {
            throw new RuntimeException("Some Error has occurred in the function 'showPatientDetail' function in Service Patient : \n"+exception.getMessage());
        }
    }

    // Updating the existing patient record
    public ResponseEntity<@NotNull String> updatePatientRecord(Long id , PatientDTO _patient) {
        try {
            // First let us check that particular data exist or not
            Optional<Patient> existPatient = _patientRepo.findById(id);

            if (existPatient.isPresent()) {

                // Cross-Checking email validation and uniqueness
                if ( !existPatient.get().getEmail().contains(".com") || !existPatient.get().getEmail().contains(String.valueOf('@'))) {
                    throw new RuntimeException("Provide valid email");
                }

                _patientRepo.findAll().forEach(patient -> {
                    if (patient.getEmail().equals(_patient.getEmail())) {
                        throw new RuntimeException("Patient already exists");
                    }
                });

                // Now let us update the data
                Patient updatedPatient = existPatient.get();
                updatedPatient.setName(_patient.getName());
                updatedPatient.setEmail(_patient.getEmail());
                updatedPatient.setAddress(_patient.getAddress());
                updatedPatient.setAge(_patient.getAge());

                // saving the result
                _patientRepo.save(updatedPatient);

                // result
                return ResponseEntity.ok("Thus data is updated for the id : "+id);

            } else {
                throw new RuntimeException("No such patient exist");
            }
        } catch (Exception exception) {
            return ResponseEntity.ok("Some Exception has occurred in the 'updatePatientRecord' in Service_Patient : \n"+exception.getMessage());
        }
    }

    // Deleting the patient record
    public String removePatientRecord(Long id) {
        try {
            // Obtaining patient record
            Optional<Patient> patient = _patientRepo.findById(id);

            if (patient.isPresent()) {
                _patientRepo.deleteById(id);
                return "Patient data has been removed successfully";
            } else {
                return "no such patient record exist";
            }

        } catch (Exception exception) {
            return "Some error has occurred please look into this : \n"+exception.getMessage();
        }
    }
}
