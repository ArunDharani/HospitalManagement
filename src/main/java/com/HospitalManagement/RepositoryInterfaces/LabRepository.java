// File Created by Arun on Tuesday (27/1/26)

// Importing the necessary packages
package com.HospitalManagement.RepositoryInterfaces;
import com.HospitalManagement.Entities.Lab;
import org.springframework.data.jpa.repository.JpaRepository;

// Creation of Repository for lab
public interface LabRepository extends JpaRepository<Lab, Long> {
}
