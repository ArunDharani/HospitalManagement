// File Created by Arun on Friday (23/1/26)

// Importing the necessary packages
package com.HospitalManagement.RepositoryInterfaces;
import com.HospitalManagement.Entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

// Creation of interface to handle data in the database
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    boolean existsByEmail(String email);
}
