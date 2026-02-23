// File Created by Arun on (29/1/26)


// Importing the necessary packages
package com.HospitalManagement.Controller;
import com.HospitalManagement.DTOs.DoctorDTO;
import com.HospitalManagement.Services.Service_Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Creation of 'DoctorController'
@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    // Creation of instance of the 'ServiceDoctor' and 'ServiceJwt'
    private final Service_Doctor serviceDoctor;

    // Creation of constructor
    @Autowired
    public DoctorController(Service_Doctor serviceDoctor) {
        this.serviceDoctor = serviceDoctor;
    }

    // Obtaining the available doctor details
    @GetMapping("/all")
    public ResponseEntity<List<DoctorDTO>> getAllDocs(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(serviceDoctor.showAllDoc(token));
    }

    // Obtaining the specific doctor details
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDocById(@PathVariable Long id , @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(serviceDoctor.showDetail(id , token));
    }

    // Creating new Doctor object
    @PostMapping
    public ResponseEntity<String> hireDoctor(@RequestBody DoctorDTO doctor , @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(serviceDoctor.hireDoctor(doctor , token));
    }

    // Updating the doctor object
    @PutMapping("/{id}")
    public ResponseEntity<String> updateDoctor(@PathVariable Long id, @RequestBody DoctorDTO doctor , @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(serviceDoctor.updateDocRecord(id , doctor , token));
    }

    // Deleting the doctor object
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(serviceDoctor.removeDoc(id , token));
    }
}
