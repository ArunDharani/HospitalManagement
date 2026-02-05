// File Created by Arun on Friday (06/02/26

// Importing the necessary packages
package com.HospitalManagement.RepositoryInterfaces;
import com.HospitalManagement.Entities.EmailDetails;
import org.springframework.data.jpa.repository.JpaRepository;

// Creation of Repository interface for Email
public interface EmailRepository extends JpaRepository<EmailDetails , Long> {
}
