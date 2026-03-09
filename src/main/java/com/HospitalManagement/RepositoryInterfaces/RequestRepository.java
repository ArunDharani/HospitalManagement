// File Created by Arun on Wednesday (4/3/26)

// Importing the necessary packages
package com.HospitalManagement.RepositoryInterfaces;
import com.HospitalManagement.Entities.request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


// Creation of interfaces
public interface RequestRepository extends JpaRepository<request, Long> {
    @Modifying
    @Query("delete from request where email = :value ")
    void delete(@Param("value") String value);
}
