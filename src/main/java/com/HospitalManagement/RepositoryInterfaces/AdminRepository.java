// File Created by Arun on Tuesday (10/02/26)

// Importing the necessary packages
package com.HospitalManagement.RepositoryInterfaces;
import com.HospitalManagement.Entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

// Creating the repository for 'Admin'
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
