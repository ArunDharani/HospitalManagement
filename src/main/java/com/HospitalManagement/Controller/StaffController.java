// File Created by Arun on Sunday (25/1/26)

// Importing the necessary packages
package com.HospitalManagement.Controller;
import com.HospitalManagement.Entities.Staff;
import com.HospitalManagement.Services.Service_Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Creation of StaffController Class
@RestController
@RequestMapping("/api/staff")
public class StaffController {

    // Creation of service_staff instance
    private final Service_Staff serviceStaff;

    // Creation of constructor
    @Autowired
    public StaffController(Service_Staff _serviceStaff) {
        this.serviceStaff = _serviceStaff;
    }

    // Creation of CRUD controllers
    @GetMapping("/all")
    public List<Staff> getallStaff() {
        return serviceStaff.getAllStaff();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getSpecificStaff(@PathVariable Long id) {
        return ResponseEntity.ok(serviceStaff.getstaffDetail(id));
    }

    @PostMapping("/create")
    public ResponseEntity<String> hireStaff(@RequestBody Staff staff) {
        return ResponseEntity.ok(serviceStaff.hireStaff(staff));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStaffDetail(@PathVariable Long id , @RequestBody Staff _staff) {
        return ResponseEntity.ok(serviceStaff.updateStaffRecord(id , _staff));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeStaff(@PathVariable Long id) {
        serviceStaff.removeStaff(id);
        return ResponseEntity.ok("Staff has been removed");
    }
}
