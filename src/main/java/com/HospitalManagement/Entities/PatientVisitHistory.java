// File Created by Arun Monday (26/1/26)

// Importing the necessary packages
package com.HospitalManagement.Entities;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

// Creation of 'patient visit history' class
@Entity
@Table(name = "PatientVisitHistory")
public class PatientVisitHistory {

    // Creation of Class Entity Members
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate visitDate;

    @Column(nullable = false)
    private LocalTime visitTime;

    // Foreign Keys
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id" , nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id" , nullable = false)
    private Doctor doctor;

    @Column(nullable = false)
    private String reason;

    // Creation of Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public LocalTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalTime visitTime) {
        this.visitTime = visitTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
