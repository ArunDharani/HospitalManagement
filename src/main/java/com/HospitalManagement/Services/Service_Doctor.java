package com.HospitalManagement.Services;

import com.HospitalManagement.Entities.Doctor;
import com.HospitalManagement.RepositoryInterfaces.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Service_Doctor {

    private final DoctorRepository doctorRepository;

    @Autowired
    public Service_Doctor(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // CREATE
    public Doctor hireDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // READ ALL
    public List<Doctor> showAllDoc() {
        return doctorRepository.findAll();
    }

    // READ BY ID
    public Doctor showDetail(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    // UPDATE
    public Doctor updateDocRecord(Long id, Doctor newData) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctor.setName(newData.getName());
        doctor.setSpecialist(newData.getSpecialist());
        doctor.setEndTime(newData.getEndTime());
        doctor.setStartTime(newData.getStartTime());

        return doctorRepository.save(doctor);
    }

    // DELETE
    public void removeDoc(Long id) {
        doctorRepository.deleteById(id);
    }
}
