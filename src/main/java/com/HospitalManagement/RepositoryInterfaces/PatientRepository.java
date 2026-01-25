// File Created by Arun on Sunday (25/1/26)
package com.HospitalManagement.RepositoryInterfaces;
import com.HospitalManagement.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
