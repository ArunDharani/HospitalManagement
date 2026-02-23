// File Created by Arun on Sunday (25/1/26)

// Importing the necessary packages
package com.HospitalManagement.Services;
import com.HospitalManagement.DTOConverter.StaffDTOConverter;
import com.HospitalManagement.DTOs.DoctorDTO;
import com.HospitalManagement.DTOs.StaffDTO;
import com.HospitalManagement.Entities.Staff;
import com.HospitalManagement.RepositoryInterfaces.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Creation of 'Service Class' for 'Staff'
@Service
public class Service_Staff {

    // Creation of repo
    private final StaffRepository _staffrepo;

    // Creation of instance of EntityDTO Converter
    private final StaffDTOConverter _converter;

    // Creation of Service_Jwt instance
    private final Service_Jwt serviceJwt;

    // Creation of constructor
    @Autowired
    public Service_Staff(StaffRepository staffRepository , StaffDTOConverter staffDTOConverter , Service_Jwt service_jwt) {
        this._staffrepo = staffRepository;
        this._converter = staffDTOConverter;
        this.serviceJwt = service_jwt;
    }

    // Creation of CRUD functions
        // Create
    public String hireStaff(StaffDTO _staff , String token) {
        try {
            // Let us verify the token
            if (token != null && token.startsWith("Bearer ")) {
                String Email =  serviceJwt.extractUserEmail(token.substring(7));
                if (Email.contains("@admin.com")) {
                    // Converting DTO to Entity
                    Staff newstaff = _converter.convetToEntity(_staff);

                    // Cross-Checking the email validation and uniqueness
                    if ( !newstaff.getEmail().contains(".com") || !newstaff.getEmail().contains(String.valueOf('@'))) {
                        throw new RuntimeException("Provide valid email");
                    }

                    _staffrepo.findAll().forEach(staff -> {
                        if (staff.getEmail().equals(_staff.getEmail())) {
                            throw new RuntimeException("Staff Already exists");
                        }
                    });

                    // saving the result
                    _staffrepo.save(newstaff);

                    // result
                    return "New staff hired successfully";
                } else {
                    throw new RuntimeException("Unauthorized");
                }
            } else {
                throw new RuntimeException("Token is invalid");
            }
        } catch (Exception e) {
            throw new RuntimeException("Some exception has occurred in 'hireStaff' function in Service_Staff : "+e.getMessage());
        }
    }

        // Read All
    public List<StaffDTO> getAllStaff(String token) {
        try {
            // Let us verify the token
            if (token != null && token.startsWith("Bearer ")) {
                String Email =  serviceJwt.extractUserEmail(token.substring(7));
                if (Email.contains("@admin.com")) {
                    // Obtaining all available Entity and converting it into DTO
                    List<StaffDTO> newStaffDTo = new ArrayList<>();

                    _staffrepo.findAll().forEach(staff -> newStaffDTo.add(_converter.convertToDTO(staff)));

                    // returning the result
                    return newStaffDTo;
                } else {
                    throw new RuntimeException("Unauthorized");
                }
            } else {
                throw new RuntimeException("Token is invalid");
            }

        } catch (Exception exception) {
            throw new RuntimeException("Some exception has occurred in 'getAllStaff' function in Service_Staff : "+exception.getMessage());
        }
    }

        // Show specific
    public StaffDTO getstaffDetail(Long id , String token){
        try {
            // Let us verify the token
            if (token != null && token.startsWith("Bearer ")) {
                String Email =  serviceJwt.extractUserEmail(token.substring(7));
                if (Email.contains("@admin.com")) {
                    // Obtaining the current staff
                    Optional<Staff> currentstaff = _staffrepo.findById(id);
                    if (currentstaff.isPresent()) {
                        return _converter.convertToDTO(currentstaff.get());
                    } else {
                        throw new Exception("No such staff data exist");
                    }
                } else {
                    throw new RuntimeException("Unauthorized");
                }
            } else {
                throw new RuntimeException("Token is invalid");
            }
        } catch (Exception exception) {
            throw new RuntimeException("Some exception has occurred in 'getstaffDetail' function in Service_Staff : "+exception.getMessage());
        }
    }

        // update staff record
    public String updateStaffRecord(Long id , StaffDTO _staff , String token) {
        try {
            // Let us verify the token
            if (token != null && token.startsWith("Bearer ")) {
                String Email =  serviceJwt.extractUserEmail(token.substring(7));
                if (Email.contains("@admin.com")) {
                    // Let us check whether such staff exist
                    Optional<Staff> currentStaff = _staffrepo.findById(id);
                    if (currentStaff.isPresent()) {

                        // Cross-Checking the email validation and uniqueness
                        if (!currentStaff.get().getEmail().contains(".com") || !currentStaff.get().getEmail().contains(String.valueOf('@'))) {
                            throw new RuntimeException("Provide me valid email");
                        }

//                        _staffrepo.findAll().forEach(staff -> {
//                            if (staff.getEmail().equals(currentStaff.get().getEmail())) {
//                                throw new RuntimeException("Staff already exists");
//                            }
//                        });

                        Staff newStaff = currentStaff.get();
                        newStaff.setName(_staff.getName());
                        newStaff.setEmail(_staff.getEmail());
                        newStaff.setEndTime(_staff.getEndTime());
                        newStaff.setStartTime(_staff.getStartTime());

                        // saving the result
                        _staffrepo.save(newStaff);

                        // returning
                        return "Hence the data is going to be updated";

                    } else {
                        throw new RuntimeException("No such staff data exist");
                    }
                } else {
                    throw new RuntimeException("Unauthorized");
                }
            } else {
                throw new RuntimeException("Token is invalid");
            }

        } catch (Exception exception) {
            throw new RuntimeException("Some exception has occurred in 'updateStaffRecord' in Service_Staff : "+exception.getMessage());
        }
    }

        // Deleting the record
    public String removeStaff(Long id , String token) {
        try {
            // Let us verify the token
            if (token != null && token.startsWith("Bearer ")) {
                String Email =  serviceJwt.extractUserEmail(token.substring(7));
                if (Email.contains("@admin.com")) {
                    // Let us check that particular data exist
                    Optional<Staff> currentStaff = _staffrepo.findById(id);

                    if (currentStaff.isPresent()) {
                        _staffrepo.deleteById(id);
                        return "Staff has been removed";
                    } else {
                        return "No such data exist";
                    }
                } else {
                    throw new RuntimeException("Unauthorized");
                }
            } else {
                throw new RuntimeException("Token is invalid");
            }

        } catch (Exception exception) {
            throw new RuntimeException("Some error has occurred in the 'removeStaff' function in Service_Staff "+exception.getMessage());
        }
    }
}
