// File Created by Arun on Tuesday (27/1/26)

// Importing the necessary packages
package com.HospitalManagement.Services;
import com.HospitalManagement.DTOs.LabDTO;
import com.HospitalManagement.Entities.Doctor;
import com.HospitalManagement.Entities.Lab;
import com.HospitalManagement.Entities.Patient;
import com.HospitalManagement.Entities.Staff;
import com.HospitalManagement.RepositoryInterfaces.DoctorRepository;
import com.HospitalManagement.RepositoryInterfaces.LabRepository;
import com.HospitalManagement.RepositoryInterfaces.PatientRepository;
import com.HospitalManagement.RepositoryInterfaces.StaffRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// Creation of Service Layer for Lab
@Service
public class Service_Lab {

    // Creation of instance labRepo , doctorRepo , PatientRepo , StaffRepo
    private final LabRepository labRepo;
    private final DoctorRepository docRepo;
    private final PatientRepository patRepo;
    private final StaffRepository staffRepo;

    // Creation of constructor
    public Service_Lab(LabRepository _lab , DoctorRepository docRepo , PatientRepository patRepo , StaffRepository staffRepo) {
        this.labRepo = _lab;
        this.docRepo = docRepo;
        this.patRepo = patRepo;
        this.staffRepo = staffRepo;
        System.out.println("Constructor of Service Lab");
    }

    // Creation of CRUD methods
    public List<Lab> getAll() {
        return labRepo.findAll();
    }

    public Optional<Lab> get(Long id) {
        // Obtaining Data
        try {
            Optional<Lab> lab = labRepo.findById(id);
            if (lab.isPresent()) {
                return lab;
            } else {
                return  null;
            }
        } catch (Exception exception)  {
            System.out.println("Some exception has occurred in get specific method of Lab service layer \n"+exception);
            return null;
        }
    }

    public Lab labact(LabDTO lab) {
        try {
            // Checking whether the staff , doctor , patient are present
            Optional<Doctor> doctor = docRepo.findById(lab.getDoctorId());
            Optional<Patient> patient = patRepo.findById(lab.getPatientId());
            Optional<Staff> staff = staffRepo.findById(lab.getStaffId());
            if (doctor.isPresent() && patient.isPresent() && staff.isPresent()) {
                // Creation of lab visit record
                Lab lab1 = new Lab();
                lab1.setDoctor(doctor.get());
                lab1.setPatient(patient.get());
                lab1.setStaff(staff.get());
                lab1.setEndTime(lab.getEndTime());
                lab1.setStartTime(lab.getStartTime());
                lab1.setSessionDate(lab.getSessionDate());
                lab1.setTestResult(lab.getTestResult());

                // saving the record
                labRepo.save(lab1);
                System.out.println("new lab record is created");
                return lab1;
            } else {
                System.out.println("Provide valid input");
                return null;
            }
        } catch (Exception exception) {
            System.out.println("Some issue occured in lab service layer\n"+exception);
            return null;
        }
    }

    public Lab updateData(Long id , LabDTO lab) {
        try {
            Optional<Lab> lab1 = labRepo.findById(id);
            if (lab1.isPresent()) {
                // Now checking the updated staff , doctor , patient are present
                Optional<Staff> staff = staffRepo.findById(lab.getStaffId());
                Optional<Patient> patient = patRepo.findById(lab.getPatientId());
                Optional<Doctor> doctor = docRepo.findById(lab.getDoctorId());
                if (staff.isPresent() && patient.isPresent() && doctor.isPresent()) {
                    Lab newData = new Lab();
                    newData = lab1.get();
                    newData.setTestResult(lab.getTestResult());
                    newData.setSessionDate(lab.getSessionDate());
                    newData.setStartTime(lab.getStartTime());
                    newData.setEndTime(lab.getEndTime());
                    newData.setStaff(staff.get());
                    newData.setPatient(patient.get());
                    newData.setDoctor(doctor.get());

                    // saving the record
                    labRepo.save(newData);

                    System.out.println("Lab data has been updated successfully");
                    return newData;
                } else {
                    System.out.println("Please provide correct values");
                    return null;
                }
            } else {
                System.out.println("No such lab record exist");
                return null;
            }
        } catch (Exception exception) {
            System.out.println("Some exception has occurred during the updating lab data\n"+exception);
            return null;
        }
    }

    public void deleteRecord(Long id) {
        labRepo.deleteById(id);
    }
}
