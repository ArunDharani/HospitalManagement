// File has been Modified by Arun on Sunday (1/2/26)
package com.HospitalManagement.Services;
import com.HospitalManagement.DTOConverter.DoctorDTOConverter;
import com.HospitalManagement.DTOs.DoctorDTO;
import com.HospitalManagement.Entities.Doctor;
import com.HospitalManagement.RepositoryInterfaces.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
@EnableAsync
public class Service_Doctor {

    // Creation of instance of DoctorRepository and Service_Jwt
    private final DoctorRepository doctorRepository;
    private final Service_Jwt serviceJwt;

    // Creation of instance of DoctorDTOConverter
    private final DoctorDTOConverter converter;

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    public Service_Doctor(DoctorRepository doctorRepository , DoctorDTOConverter _converter , Service_Jwt serviceJwt) {
        this.doctorRepository = doctorRepository;
        this.converter = _converter;
        this.serviceJwt = serviceJwt;
        System.out.println("Instance has been created in Service_Doctor class");
    }

    // Creating new doctor details in the database
    public CompletableFuture<String> hireDoctor(DoctorDTO doctorDTO, String token) {

        return CompletableFuture.supplyAsync(() ->

                        transactionTemplate.execute(status -> {
                            try {

                                System.out.println("Thread: " + Thread.currentThread().getName());

                                if (token == null || !token.startsWith("Bearer ")) {
                                    throw new RuntimeException("Token is invalid");
                                }

                                String extractedEmail = serviceJwt.extractUserEmail(token.substring(7));

                                if (extractedEmail == null || !extractedEmail.contains("@admin.com")) {
                                    throw new RuntimeException("Unauthorized access");
                                }

                                Doctor doctor = converter.convertToEntity(doctorDTO);

                                String email = doctor.getEmail();

                                if (email == null || !email.contains("@") || !email.contains(".com")) {
                                    throw new RuntimeException("Invalid email format");
                                }

                                if (doctorRepository.existsByEmail(email)) {
                                    throw new RuntimeException("Doctor already exists with this email");
                                }

                                doctorRepository.save(doctor);

                                return "New doctor has been appointed to the hospital";

                            } catch (Exception e) {

                                status.setRollbackOnly();

                                throw new RuntimeException(
                                        "Error occurred while hiring doctor: " + e.getMessage()
                                );
                            }
                        })

                , executorService);
    }

    // Obtaining all doctor details
    @Async
    public CompletableFuture<List<DoctorDTO>> showAllDoc(String token) {
        try {
            // Let us verify the token
            if (token != null && token.startsWith("Bearer ")) {
                String Email =  serviceJwt.extractUserEmail(token.substring(7));
                if (Email.contains("@admin.com")) {
                    // Creation of array to store the doctorsDTO
                    List<DoctorDTO> doctorDTOS = new ArrayList<>();

                    // Now converting each 'Doctor' object to 'DoctorDTO' object
                    doctorRepository.findAll().forEach(currentDoctor -> {
                        doctorDTOS.add(converter.convertToDTO(currentDoctor));
                    });

                    // returning the result
                    return CompletableFuture.completedFuture(doctorDTOS);
                } else {
                    throw new RuntimeException("Unauthorized");
                }
            } else {
                throw new RuntimeException("Token is invalid");
            }

        } catch (Exception exception) {
            throw new RuntimeException("Some Problem as occurred during the execution of 'showAll' function in Service Doctor class\nPlease look into this : "+exception.getMessage());
        }
    }

    // Obtaining specific doctor detail
    @Async
    public CompletableFuture<DoctorDTO> showDetail(Long id , String token) {
        try {
            // Let us verify the token
            if (token != null && token.startsWith("Bearer ")) {
                String Email =  serviceJwt.extractUserEmail(token.substring(7));
                if (Email.contains("@admin.com")) {
                    // Obtaining the available doctor
                    Optional<Doctor> currentDoctor = doctorRepository.findById(id);

                    // Checking whether the particular doctor is available
                    if (currentDoctor.isPresent()) {

                        // returning the result
                        return CompletableFuture.completedFuture(converter.convertToDTO(currentDoctor.get()));

                    } else {
                        throw new RuntimeException("No such doctor exists please provide correct ID");
                    }
                } else {
                    throw new RuntimeException("Unauthorized");
                }
            } else {
                throw new RuntimeException("Token is invalid");
            }
        } catch (Exception exception) {
            throw new RuntimeException("Some Problem as occurred during the execution of 'showDetail' function in Service Doctor class\nPlease look into this : "+exception.getMessage());
        }
    }

    // Updating the existing doctor detail
    @Async
    public String updateDocRecord(Long id, DoctorDTO doctorDTO , String token) {
        try {

            // Let us verify the token
            if (token != null && token.startsWith("Bearer ")) {
                String Email =  serviceJwt.extractUserEmail(token.substring(7));
                if (Email.contains("@admin.com")) {
                    // obtaining the current doctor
                    Optional<Doctor> currentDoctor = doctorRepository.findById(id);

                    if (currentDoctor.isPresent()) {

                        // Converting the DTO into Entity Domain
                        Doctor newData = converter.convertToEntity(doctorDTO);

                        // Cross-Checking the email
                        if (!newData.getEmail().contains(".com") || !newData.getEmail().contains(String.valueOf('@'))) {
                            throw new RuntimeException("It is not a valid email");
                        }

                        // Only unique email exist
                        doctorRepository.findAll().forEach(doctor -> {
                            if (doctor.getEmail().equals(doctorDTO.getEmail())) {
                                throw new RuntimeException("Only unique mail is allowed");
                            }
                        });

                        // Updating all the detail by creating new instance
                        Doctor doctor = currentDoctor.get();
                        doctor.setName(newData.getName());
                        doctor.setEmail(newData.getEmail());
                        doctor.setSpecialist(newData.getSpecialist());
                        doctor.setStartTime(newData.getStartTime());
                        doctor.setEndTime(newData.getEndTime());

                        // Storing the result
                        doctorRepository.save(doctor);

                        // Returning the result
                        return "Thus successfully updated the data";
                    } else {
                        return "No such doctor exists please provide correct ID";
                    }

                } else {
                    throw new RuntimeException("Unauthorized");
                }
            } else {
                throw new RuntimeException("Token is invalid");
            }
        } catch (Exception exception) {
            return "Some Problem as occurred during the execution of 'updateDocRecord' function in Service Doctor class\nPlease look into this : "+exception.getMessage();
        }
    }

    // Removing doctor data from the table
    @Async
    public String removeDoc(Long id , String token) {
        try {
            // Let us verify the token
            if (token != null && token.startsWith("Bearer ")) {
                String Email =  serviceJwt.extractUserEmail(token.substring(7));
                if (Email.contains("@admin.com")) {
                    // First let us check particular doctor data exist
                    Optional<Doctor> doctor = doctorRepository.findById(id);

                    if (doctor.isPresent()) {
                        doctorRepository.deleteById(id);
                        return "Data has been removed successfully";
                    } else {
                        throw new RuntimeException("No such doctor record exist");
                    }
                } else {
                    throw new RuntimeException("Unauthorized");
                }
            } else {
                throw new RuntimeException("Token is invalid");
            }
        } catch (Exception exception) {
            return "Some exception has occurred in the 'removeDoc' function in Service_doctor :\n"+exception.getMessage();
        }
    }
}
