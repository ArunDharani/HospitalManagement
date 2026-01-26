// File Created by Arun on Monday (26/1/26)

// Importing the necessary package
package com.HospitalManagement.DTOs;
import java.time.LocalDate;
import java.time.LocalTime;

// This DTO is created so that there will some abstraction between the Database and the service layer

// Creation of 'Patient Visit History DTO'
public class PatientVisitHistoryDTO {

    // Creation of Class Members
    private LocalDate visitDate;
    private LocalTime visitTime;
    private Long patientId;
    private Long doctorId;
    private String reason;

    // Creation of Getters and Setters
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

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
