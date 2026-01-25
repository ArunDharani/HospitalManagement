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

    // Creation of service instance
    private final StaffRepository _staffrepo;

    // Creation of constructor
    @Autowired
    public Service_Staff(StaffRepository staffRepository) {
        this._staffrepo = staffRepository;
    }

    // Creation of CRUD functions
        // Create
    public Staff hireStaff(Staff _staff) {
        return _staffrepo.save(_staff);
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
    public Staff updateStaffRecord(Long id , Staff _staff) {
        // Obtaining the current record
        Staff _currentStaff = _staffrepo.findById(id).orElseThrow(() -> new RuntimeException("No such Staff Record present"));

        // Modifying the record
        _currentStaff.setName(_staff.getName());
        _currentStaff.setEndTime(_staff.getEndTime());
        _currentStaff.setStartTime(_staff.getStartTime());

        // Returning the result
        return _currentStaff;
    }

        // Deleting the record
    public void removeStaff(Long id) {
        _staffrepo.deleteById(id);
    }
}
