// File Created by Arun on Monday (26/1/26)

// Importing the necessary packages
package com.HospitalManagement.RepositoryInterfaces;

import com.HospitalManagement.Entities.PatientVisitHistory;
import org.springframework.data.jpa.repository.JpaRepository;

// Creation of 'Repository Class'
public interface PatientVisitHistoryRepository extends JpaRepository<PatientVisitHistory , Long> {

}
