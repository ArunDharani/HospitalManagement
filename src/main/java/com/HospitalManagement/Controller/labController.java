// File Created by Arun on Tuesday (27/1/26)

// Importing the necessary packages
package com.HospitalManagement.Controller;
import com.HospitalManagement.DTOs.LabDTO;
import com.HospitalManagement.Entities.Lab;
import com.HospitalManagement.Services.Service_Lab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

// Creation of 'RestController' for 'lab'
@RestController
@RequestMapping("/api/lab")
public class labController {

    // Creation of instance of LabService
    private final Service_Lab serviceLab;

    // Using constructor to initialize
    @Autowired
    public labController(Service_Lab serviceLab) {
        this.serviceLab = serviceLab;
    }

    // Creation of all CRUD apis
        // GET ALL
    @GetMapping("/all")
    public List<Lab> getAll() {
        return serviceLab.getAll();
    }

        // GET specific data
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Lab>> getbyId(@PathVariable Long id){
        return ResponseEntity.ok(serviceLab.get(id));
    }

        // POST data
    @PostMapping("/create")
    public ResponseEntity<Lab> postData(@RequestBody LabDTO labdto) {
        return ResponseEntity.ok(serviceLab.labact(labdto));
    }

        // Update data
    @PutMapping("/{id}")
    public ResponseEntity<Lab> updateData(@PathVariable Long id , @RequestBody LabDTO labDTO) {
        return ResponseEntity.ok(serviceLab.updateData(id , labDTO));
    }

        // Delete data
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteData(@PathVariable Long id){
        serviceLab.deleteRecord(id);
        return ResponseEntity.ok("Data has been removed successfully");
    }
}
