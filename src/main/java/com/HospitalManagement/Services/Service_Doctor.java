// File has been Modified by Arun on Sunday (1/2/26)
package com.HospitalManagement.Services;
import com.HospitalManagement.DTOConverter.DoctorDTOConverter;
import com.HospitalManagement.DTOs.DoctorDTO;
import com.HospitalManagement.Entities.Doctor;
import com.HospitalManagement.RepositoryInterfaces.DoctorRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Service_Doctor {

    // Creation of instance of DoctorRepository and Service_Jwt
    private final DoctorRepository doctorRepository;
    private final Service_Jwt serviceJwt;

    // Creation of instance of DoctorDTOConverter
    private final DoctorDTOConverter converter;

    @Autowired
    public Service_Doctor(DoctorRepository doctorRepository , DoctorDTOConverter _converter , Service_Jwt serviceJwt) {
        this.doctorRepository = doctorRepository;
        this.converter = _converter;
        this.serviceJwt = serviceJwt;
        System.out.println("Instance has been created in Service_Doctor class");
    }

    // Creating new doctor details in the database
    public ResponseEntity<@NotNull String> hireDoctor(DoctorDTO doctorDTO) {
        try {
            // Let us obtain the doctor object from 'DoctorDTOConverter' clas
            Doctor doctor = converter.convertToEntity(doctorDTO);

            // Let us cross-check the email for validation
            if ( !doctor.getEmail().contains(".com") || !doctor.getEmail().contains(String.valueOf('@'))) {
                throw new RuntimeException("It is not a proper email");
            }

            // Let us prevent only unique email is allowed
            doctorRepository.findAll().forEach(doctor1 -> {
                if (doctor1.getEmail().equals(doctorDTO.getEmail())) {
                    throw new RuntimeException("Already user exist");
                }
            });

            // Saving the data in the database
            doctorRepository.save(doctor);

            // returning the result
            return ResponseEntity.ok("New doctor has been appointed to the hospital");
            
        } catch (Exception exception) {
            return ResponseEntity.ok("Some Exception has occurred while creating new doctor object in hireDoctor function\n"+exception);
        }
    }

    // Obtaining all doctor details
    public List<DoctorDTO> showAllDoc(String token) {
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
                    return doctorDTOS;
                } else {
                    throw new RuntimeException("Unauthorized");
                }
            } else {
                throw new RuntimeException("Token is invalid");
            }
            
        } catch (Exception exception) {
            throw new RuntimeException("Some Problem as occurred during the execution of 'showAll' function in Service Doctor class\nPlease look into this : "+exception);
        }
    }

    // Obtaining specific doctor detail
    public DoctorDTO showDetail(Long id) {
        try {
            // Obtaining the available doctor
            Optional<Doctor> currentDoctor = doctorRepository.findById(id);
            
            // Checking whether the particular doctor is available
            if (currentDoctor.isPresent()) {

                // returning the result
                return converter.convertToDTO(currentDoctor.get());

            } else {
                throw new RuntimeException("No such doctor exists please provide correct ID");
            }
        } catch (Exception exception) {
            throw new RuntimeException("Some Problem as occurred during the execution of 'showDetail' function in Service Doctor class\nPlease look into this : "+exception);
        }
    }

    // Updating the existing doctor detail
    public ResponseEntity<@NotNull String> updateDocRecord(Long id, DoctorDTO doctorDTO) {
        try {
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
                return ResponseEntity.ok("Thus successfully updated the data");
            } else {
                return ResponseEntity.ok("No such doctor exists please provide correct ID");
            }
        } catch (Exception exception) {
            return ResponseEntity.ok("Some Problem as occurred during the execution of 'updateDocRecord' function in Service Doctor class\nPlease look into this : "+exception.getMessage());
        }
    }

    // Removing doctor data from the table
    public String removeDoc(Long id) {
        try {
            // First let us check particular doctor data exist
            Optional<Doctor> doctor = doctorRepository.findById(id);

            if (doctor.isPresent()) {
                doctorRepository.deleteById(id);
                return "Data has been removed successfully";
            } else {
                throw new RuntimeException("No such doctor record exist");
            }
        } catch (Exception exception) {
            return "Some exception has occurred in the 'removeDoc' function in Service_doctor :\n"+exception.getMessage();
        }
    }
}
