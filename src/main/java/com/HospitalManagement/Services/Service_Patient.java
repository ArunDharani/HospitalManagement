// File Created by Arun on Sunday (25/1/26)

package com.HospitalManagement.Services;
import com.HospitalManagement.Entities.Patient;
import com.HospitalManagement.RepositoryInterfaces.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

// Creation of 'Service Class' with 'Patient'
@Service
public class Service_Patient {

    // Creation of service instance
    private final PatientRepository _patientRepo;

    // Creation of constructor
    @Autowired
    public Service_Patient(PatientRepository _patRepo) {
        this._patientRepo = _patRepo;
    }

    // Creation of Patient
    public Patient admitPatient(Patient _patient){
        return _patientRepo.save(_patient);
    }

    // Obtaining all available patient
    public List<Patient> showAllPatient() {
        return _patientRepo.findAll();
    }

    // Obtaining only specific patient
    public Patient showPatientDetail(Long id) {
        return _patientRepo.findById(id).orElseThrow(()-> new RuntimeException("No such patient record"));
    }

    // Updating the existing patient record
    public Patient updatePatientRecord(Long id , Patient _patient) {
        // First let us check such patient exist or not
        Patient _currentPatient = _patientRepo.findById(id).orElseThrow( ()-> new RuntimeException("No such patient record exist"));

        // Updating the records
        _currentPatient.setName(_patient.getName());
        _currentPatient.setAddress(_patient.getAddress());
        _currentPatient.setAge(_patient.getAge());
        _currentPatient.setEmail(_patient.getEmail());

        // Returning the result
        return _patientRepo.save(_currentPatient);

    }

    // Deleting the patient record
    public void removePatientRecord(Long id) {
        _patientRepo.deleteById(id);
    }
}
