// File Created by Arun on Sunday (25/1/26)

// Importing the necessary packages
package com.HospitalManagement.Services;
import com.HospitalManagement.Entities.Staff;
import com.HospitalManagement.RepositoryInterfaces.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Creation of 'Service Class' for 'Staff'
@Service
public class Service_Staff {

    // Creation of repo instance
    private final StaffRepository _staffrepo;

    // Creation of constructor
    @Autowired
    public Service_Staff(StaffRepository staffRepository) {
        this._staffrepo = staffRepository;
    }

    // Creation of CRUD functions
        // Create
    public String hireStaff(Staff _staff) {
        _staffrepo.save(_staff);
        return "New staff data has been created";
    }

        // Read All
    public List<Staff> getAllStaff() {
        return _staffrepo.findAll();
    }

        // Show specific
    public Staff getstaffDetail(Long id){
        return _staffrepo.findById(id).orElseThrow( ()-> new RuntimeException("No such staff record is present"));
    }

        // update staff record
    public String updateStaffRecord(Long id , Staff _staff) {
        // Obtaining the current record
        Staff _currentStaff = _staffrepo.findById(id).orElseThrow(() -> new RuntimeException("No such Staff Record present"));

        // Modifying the record
        _currentStaff.setName(_staff.getName());
        _currentStaff.setEndTime(_staff.getEndTime());
        _currentStaff.setStartTime(_staff.getStartTime());
        _currentStaff.setEmail(_staff.getEmail());

        // Returning the result
        _staffrepo.save(_currentStaff);
        return "Data has been updated";
    }

        // Deleting the record
    public void removeStaff(Long id) {
        _staffrepo.deleteById(id);
    }
}
