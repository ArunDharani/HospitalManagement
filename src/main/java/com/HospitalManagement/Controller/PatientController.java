// File created by Arun on Sunday (25/1/26)
package com.HospitalManagement.Controller;
import com.HospitalManagement.Entities.Patient;
import com.HospitalManagement.Services.Service_Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public List<Patient> getAllPatient() {
        return servicePatient.showAllPatient();
    }

    @GetMapping("{_id}")
    public ResponseEntity<Patient> getPatientbyId(@PathVariable Long _id) {
        return ResponseEntity.ok(servicePatient.showPatientDetail(_id));
    }

    @PostMapping("/create")
    public ResponseEntity<Patient> admitPatient(@RequestBody Patient _patient) {
        return ResponseEntity.ok(servicePatient.admitPatient(_patient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatepatient(@PathVariable Long id , @RequestBody Patient _patient) {
        return ResponseEntity.ok(servicePatient.updatePatientRecord(id , _patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        servicePatient.removePatientRecord(id);
        return ResponseEntity.ok("Patient Data has been removed");
    }
}
