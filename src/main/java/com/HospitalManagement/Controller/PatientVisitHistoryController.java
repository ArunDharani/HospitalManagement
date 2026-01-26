// File Created by Arun on Monday (26/1/26)

// Importing the necessary packages
package com.HospitalManagement.Controller;
import com.HospitalManagement.DTOs.PatientVisitHistoryDTO;
import com.HospitalManagement.Entities.PatientVisitHistory;
import com.HospitalManagement.Services.Service_Doctor;
import com.HospitalManagement.Services.Service_Patient;
import com.HospitalManagement.Services.Service_PatientVisitHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

// Creation of 'RestController Class' for PatientVisitHistory
@RestController
@RequestMapping("/api/patientVisitHistory")
public class PatientVisitHistoryController {

    // Creation of instance of the 'Patient' , 'Doctor' , 'PatientVisitHistory'
    private final Service_Patient _servicePatient;
    private final Service_Doctor _serviceDoctor;
    private final Service_PatientVisitHistory _servicePatientVisitHistory;

    // Creation of constructor
    @Autowired
    public PatientVisitHistoryController(Service_PatientVisitHistory visitHistory , Service_Patient servicePatient , Service_Doctor serviceDoctor) {
        System.out.println("Calling the constructor in PatientVisitHistory Controller class");
        this._serviceDoctor = serviceDoctor;
        this._servicePatient = servicePatient;
        this._servicePatientVisitHistory = visitHistory;
    }

    // Creation of all CRUD apis

      // GET All api
    @GetMapping("/getall")
    public List<PatientVisitHistory> getall() {
        return _servicePatientVisitHistory.showAllVisit();
    }

      // GET specific data
    @GetMapping("/{id}")
    public ResponseEntity<Optional<PatientVisitHistory> > getData(@PathVariable Long id) {
        return ResponseEntity.ok(_servicePatientVisitHistory.showThisVisit(id));
    }

      // Create VisitRecord
    @PostMapping("/create")
    public ResponseEntity<PatientVisitHistory> createVisitData(@RequestBody PatientVisitHistoryDTO data) {
        return ResponseEntity.ok(_servicePatientVisitHistory.createRecord(data));
    }

      // UpdateRecord
    @PutMapping("/{id}")
    public ResponseEntity<PatientVisitHistory> updateData(@PathVariable Long id , @RequestBody PatientVisitHistoryDTO data) {
        return ResponseEntity.ok(_servicePatientVisitHistory.updateData(id , data));
    }

      // DeleteRecord
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable Long id) {
        _servicePatientVisitHistory.removeData(id);
        return ResponseEntity.ok("Data has been removed successfully");
    }

}
