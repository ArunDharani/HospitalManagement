// File Created by Arun on Friday (23/1/26)

package com.HospitalManagement.RepositoryInterfaces;

import com.HospitalManagement.Entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
