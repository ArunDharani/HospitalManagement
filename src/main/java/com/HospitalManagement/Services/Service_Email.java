// File Created by Arun on Friday (06/02/26)

// Importing the necessary packages
package com.HospitalManagement.Services;
import com.HospitalManagement.DTOConverter.EmailDTOConverter;
import com.HospitalManagement.DTOs.EmailDTO;
import com.HospitalManagement.Entities.EmailDetails;
import com.HospitalManagement.RepositoryInterfaces.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

// Creation of service class
@Service
public class Service_Email {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailSender;

    // Creation of instance of Email Converter
    private final EmailDTOConverter _emailConverter;

    // Creation of instance of Email Repository
    private final EmailRepository _emailRepo;

    // Creation of constructor
    public Service_Email(EmailDTOConverter emailDTOConverter , EmailRepository emailRepository) {
        this._emailConverter = emailDTOConverter;
        this._emailRepo = emailRepository;
    }


    public void sendEmail(EmailDTO emailDTO) {
        try {
            // Converting DTO into Entity
            EmailDetails newEmail = _emailConverter.convertToEntity(emailDTO);

            // Creating new simple Mail instance
            SimpleMailMessage sender = new SimpleMailMessage();
            sender.setFrom(emailSender);
            sender.setTo(newEmail.getReceiver());
            sender.setSubject(newEmail.getSubject());
            sender.setText(newEmail.getMessage());

            // Now send the mail
            javaMailSender.send(sender);

            // saving the contents
            _emailRepo.save(newEmail);


        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Some error has occurred in 'sendEmail' function in Service_Email : "+exception.getMessage());
        }
    }

}
