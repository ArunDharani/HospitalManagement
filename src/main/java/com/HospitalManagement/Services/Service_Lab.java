// File Created by Arun on Tuesday (27/1/26)

// Importing the necessary packages
package com.HospitalManagement.Services;
import com.HospitalManagement.DTOConverter.LabDTOConverter;
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

import javax.print.Doc;
import javax.swing.*;
import javax.swing.text.html.Option;
import java.util.ArrayList;
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

    // Creation of LabConverter instance
    private final LabDTOConverter labDTOConverter;

    // Creation of constructor
    public Service_Lab(LabRepository _lab , DoctorRepository docRepo , PatientRepository patRepo , StaffRepository staffRepo , LabDTOConverter _labconverter) {
        this.labRepo = _lab;
        this.docRepo = docRepo;
        this.patRepo = patRepo;
        this.staffRepo = staffRepo;
        this.labDTOConverter = _labconverter;
        System.out.println("Constructor of Service Lab");
    }

    // Creation of CRUD methods

    // Obtaining all data
    public List<LabDTO> getAll() {
        try {
            // Converting Entity to DTO
            List<LabDTO> result = new ArrayList<>();

            labRepo.findAll().forEach(item -> {
                result.add(labDTOConverter.convertToDTO(item));
            });

            // returning the result
            return result;
        } catch (Exception exception) {
            throw new RuntimeException("Some error has occurred in the 'getall' function in Service_lab : "+exception.getMessage());
        }
    }

    // Obtaining specific DTO
    public LabDTO get(Long id) {
       try {
           // Obtaining the data
           Optional<Lab> data = labRepo.findById(id);
           if (data.isPresent()) {
               return labDTOConverter.convertToDTO(data.get());
           } else {
               throw new RuntimeException("No such lab data exists");
           }
       } catch (Exception exception) {
           throw new RuntimeException("Some Error has occurred in the 'get' function in Service_lab ");
       }
    }

    // Creating new Data in database
    public String createLabData(LabDTO lab) {
        try {
           // Let us perform nested checking
            Optional<Doctor> doctor = docRepo.findById(lab.getDoctorId());
            if (doctor.isPresent()) {
                Optional<Patient> patient = patRepo.findById(lab.getPatientId());
                if (patient.isPresent()) {
                    Optional<Staff> staff = staffRepo.findById(lab.getStaffId());
                    if (staff.isPresent()) {
                        // Creating new Data
                        Lab newData = new Lab();
                        newData.setDoctor(doctor.get());
                        newData.setPatient(patient.get());
                        newData.setStaff(staff.get());
                        newData.setEndTime(lab.getEndTime());
                        newData.setSessionDate(lab.getSessionDate());
                        newData.setStartTime(lab.getStartTime());
                        newData.setTestResult(lab.getTestResult());
                        // Saving the result
                        labRepo.save(newData);

                        // result
                        return "New lab record has been saved";
                    } else {
                        throw new RuntimeException("No such staff data exist");
                    }
                } else {
                    throw new RuntimeException("No such patient data exist");
                }
            } else {
                throw new RuntimeException("No such doctor data exist");
            }
        } catch (Exception exception) {
            throw new RuntimeException("Some error has occurred in 'createLabData' in Service_Lab class : "+exception.getMessage());
        }
    }

    public String updateData(Long id , LabDTO _lab) {
        try {
            // Let us check particular data exist
            Optional<Lab> lab = labRepo.findById(id);
            if (lab.isPresent()) {
                Optional<Doctor> doctor = docRepo.findById(_lab.getDoctorId());
                if (doctor.isPresent()) {
                    Optional<Patient> patient = patRepo.findById(_lab.getPatientId());
                    if (patient.isPresent()) {
                        Optional<Staff> staff = staffRepo.findById(_lab.getStaffId());
                        if (staff.isPresent()) {
                            Lab newLab = lab.get();
                            newLab.setStartTime(_lab.getStartTime());
                            newLab.setSessionDate(_lab.getSessionDate());
                            newLab.setEndTime(_lab.getEndTime());
                            newLab.setStaff(staff.get());
                            newLab.setDoctor(doctor.get());
                            newLab.setPatient(patient.get());

                            // Saving the result
                            labRepo.save(newLab);

                            // Returning the result
                            return "Data has been updated successfully";
                        } else {
                            throw new RuntimeException("No such staff record exist");
                        }
                    } else {
                        throw new RuntimeException("No such patient record exist");
                    }
                } else {
                    throw new RuntimeException("No such Doctor record exist");
                }
            } else {
                throw new RuntimeException("No such Lab record exists");
            }
        } catch (Exception exception) {
            throw new RuntimeException("Some error has occurred in 'updateData' function in Service_Lab : "+exception.getMessage());
        }
    }

    public String deleteRecord(Long id) {
        try {
            // Checking whether the data related to id exist
            Optional<Lab> currentData = labRepo.findById(id);
            if (currentData.isPresent()) {
                labRepo.deleteById(id);
                return "Data has been removed successfully";
            } else {
                throw new RuntimeException("No such data to be deleted");
            }
        } catch (Exception exception) {
            throw new RuntimeException("Some error has occurred in the 'deleteRecord' function of Service_lab : "+exception.getMessage());
        }
    }
}
