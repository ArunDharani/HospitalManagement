package com.HospitalManagement.Controller;

import com.HospitalManagement.Entities.Doctor;
import com.HospitalManagement.Services.Service_Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    private final Service_Doctor serviceDoctor;

    @Autowired
    public DoctorController(Service_Doctor serviceDoctor) {
        this.serviceDoctor = serviceDoctor;
    }

    // GET ALL
    @GetMapping("/all")
    public List<Doctor> getAllDocs() {
        return serviceDoctor.showAllDoc();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDocById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceDoctor.showDetail(id));
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Doctor> hireDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(serviceDoctor.hireDoctor(doctor));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(
            @PathVariable Long id,
            @RequestBody Doctor doctor) {

        return ResponseEntity.ok(serviceDoctor.updateDocRecord(id, doctor));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        serviceDoctor.removeDoc(id);
        return ResponseEntity.ok("Doctor removed successfully");
    }
}
