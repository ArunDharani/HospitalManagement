// File Created by Arun on Friday (06/02/26)

// Importing the necessary packages
package com.HospitalManagement.DTOConverter;
import com.HospitalManagement.DTOs.EmailDTO;
import com.HospitalManagement.Entities.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Entity DTO converter for Email Class
@Component
public class EmailDTOConverter {

    // Creation of Default Constructor
    @Autowired
    public EmailDTOConverter() {
        System.out.println("Default Constructor of Email DTO Converter");
    }

    // Function to Convert from DTO to Entity
    public EmailDetails convertToEntity(EmailDTO emailDTO) {
        return new EmailDetails(emailDTO.getReceiver() , emailDTO.getSubject() , emailDTO.getMessage());
    }

    // Function to Convert from Entity to DTO
    public EmailDTO convertToDTO(EmailDetails emailDetails) {
        return new EmailDTO(emailDetails.getId(), emailDetails.getReceiver(), emailDetails.getMessage(), emailDetails.getSubject());
    }
}
