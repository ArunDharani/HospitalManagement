// File Created by Arun on Friday (23/1/26)

// Importing the necessary packages
package com.HospitalManagement.RepositoryInterfaces;
import com.HospitalManagement.Entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

// Creation of interface to handle data in the database
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
