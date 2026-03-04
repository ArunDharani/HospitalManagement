// File Created by Arun on Wednesday (4/3/26)

// Importing the necessary packages
package com.HospitalManagement.RepositoryInterfaces;
import com.HospitalManagement.Entities.ResultData;
import org.springframework.data.jpa.repository.JpaRepository;

// Creation of interface for the response result
public interface ResponseRepository extends JpaRepository<ResultData , Long> {
}
