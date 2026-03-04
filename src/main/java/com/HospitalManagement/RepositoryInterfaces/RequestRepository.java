// File Created by Arun on Wednesday (4/3/26)

// Importing the necessary packages
package com.HospitalManagement.RepositoryInterfaces;
import com.HospitalManagement.Entities.TestData;
import org.springframework.data.jpa.repository.JpaRepository;

// Creation of interfaces
public interface RequestRepository extends JpaRepository<TestData , Long> {
}
