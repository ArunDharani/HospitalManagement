package com.HospitalManagement.Controller;

import com.HospitalManagement.DTOs.DoctorDTO;
import com.HospitalManagement.Entities.Doctor;
import com.HospitalManagement.Services.Service_Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    // Creation of instance of the 'ServiceDoctor'
    private final Service_Doctor serviceDoctor;

    @Autowired
    public DoctorController(Service_Doctor serviceDoctor) {
        this.serviceDoctor = serviceDoctor;
    }

    // obtaining all the doctor details
    @GetMapping("/all")
    public ResponseEntity<List<DoctorDTO>> getAllDocs() {
        return ResponseEntity.ok(serviceDoctor.showAllDoc());
    }

    // obtaining particular doctor detail
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDocById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceDoctor.showDetail(id));
    }

    // CREATE
    @PostMapping
    public ResponseEntity<String> hireDoctor(@RequestBody DoctorDTO doctor) {
        return serviceDoctor.hireDoctor(doctor);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<String> updateDoctor(@PathVariable Long id, @RequestBody DoctorDTO doctor) {
        return serviceDoctor.updateDocRecord(id , doctor);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        return ResponseEntity.ok(serviceDoctor.removeDoc(id));
    }
}
