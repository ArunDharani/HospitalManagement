// File Created by Arun on tuesday (3/3/36)

// Importing the necessary packages
package com.HospitalManagement.Services;
import com.HospitalManagement.DTOs.EmailDTO;
import com.HospitalManagement.DTOs.IntermediateDTO;
import com.HospitalManagement.Entities.ResultData;
import com.HospitalManagement.Entities.TestData;
import com.HospitalManagement.RepositoryInterfaces.RequestRepository;
import com.HospitalManagement.RepositoryInterfaces.ResponseRepository;
import org.springframework.stereotype.Service;

// Creation of MultiFactor Service Class
@Service
public class MultiFactor {

    // Let us create instance of Email service , jwt service , RequestRepository , ResponseRepository
    private final RequestRepository requestRepo;
    private final ResponseRepository responseRepo;
    private final Service_Jwt serviceJwt;
    private final Service_Email serviceEmail;

    // Creation of constructor
    public MultiFactor(RequestRepository requestRepo, ResponseRepository responseRepo, Service_Jwt serviceJwt, Service_Email serviceEmail) {
        this.requestRepo = requestRepo;
        this.responseRepo = responseRepo;
        this.serviceJwt = serviceJwt;
        this.serviceEmail = serviceEmail;
    }

    // Creation of function to generate OTP
    public String generateOTP() {
        String randomData = "QWERTYUIOPLKJHFGDSAZXCVBNM1234567890poiuytrewqasdfghjklmnbvcxz";
        StringBuilder result = new StringBuilder();
        for (int iter1 = 0 ; iter1 < 5 ; iter1++) {
            result.append(randomData.charAt((int)Math.random() * randomData.length()));
        }
        return result.toString();
    }

    // Creation of Function to send email otp
    public void sendOTP(TestData testData) {

        // Setting the otp
        testData.setOtp(generateOTP());

        // saving the results
        requestRepo.save(testData);

        // Now creating new EmailDTO
        EmailDTO emailDTO = new EmailDTO(testData.getEmail(), testData.getOtp(), "verification");

        // Now sending the email
        serviceEmail.sendEmail(emailDTO);
    }

    // Creation of function to verify the email
    public String verifyOTP(IntermediateDTO intermediateDTO) {
        try {
            requestRepo.findAll().forEach(request -> {
                if (request.getEmail().equals(intermediateDTO.getEmail())) {
                    if (request.getOtp().equals(intermediateDTO.getOtp())){
                        // Creation of new response object
                        ResultData resultData = new ResultData(request.getName() , request.getEmail());
                        responseRepo.save(resultData);
                        System.out.println("valid verification");
                    } else {
                        throw new RuntimeException("Otp is invalid");
                    }
                }
            });

//            throw new RuntimeException("No such email exist");
            return "good";
        } catch (Exception exception) {
            throw new RuntimeException("Some exception exist : "+exception.getMessage());
        }
    }
}
