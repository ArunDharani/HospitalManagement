// File created by Arun on Sunday (25/1/26)

// Importing the necessary packages
package com.HospitalManagement.Controller;
import com.HospitalManagement.Services.Service_Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.HospitalManagement.DTOs.PatientDTO;
import java.util.List;

// Creation of RestController class
@RestController
@RequestMapping("/api/patient")
public class PatientController {

    // Creation of service_patient instance
    private final Service_Patient servicePatient;

    // Creation of constructor
    @Autowired
    public PatientController(Service_Patient _servePatient) {
        this.servicePatient = _servePatient;
    }

    // Creation of all the CRUD methods
    @GetMapping("/all")
    public ResponseEntity<List<PatientDTO>> getAllPatient() {
        return ResponseEntity.ok(servicePatient.showAllPatient());
    }

    @GetMapping("/{_id}")
    public ResponseEntity<PatientDTO> getPatientbyId(@PathVariable Long _id) {
        return ResponseEntity.ok(servicePatient.showPatientDetail(_id));
    }

    @PostMapping("/create")
    public ResponseEntity<String> admitPatient(@RequestBody PatientDTO _patient) {
        return servicePatient.admitPatient(_patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatepatient(@PathVariable Long id , @RequestBody PatientDTO _patient) {
        return servicePatient.updatePatientRecord(id , _patient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        return ResponseEntity.ok(servicePatient.removePatientRecord(id));

    }
}
