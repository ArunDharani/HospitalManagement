// File Created by Arun on Tuesday (17/2/26)

// Importing the necessary packages
package com.HospitalManagement.InitialSeeder;
import com.HospitalManagement.Entities.Admin;
import com.HospitalManagement.RepositoryInterfaces.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// Class for initial configuration of Seed of Admin
@Component
public class AdminSeed implements CommandLineRunner {

    // Creation of instance of Admin repository
    private final AdminRepository adminRepository;

    // Constructor for initialization
    @Autowired
    public AdminSeed(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // Creation of Function for Initial Data Seeding
    @Override
    public void run(String... args) throws Exception {
        try {

            // Creation of list to store all the email
            List<String> allEmails = new ArrayList<>();

            // First let us obtain all the data from the repository
            adminRepository.findAll().forEach(admin -> {
                allEmails.add(admin.getEmail());
            });

            if (!allEmails.contains("arunelayaperumal@admin.com")) {
                // Creation of new admin Data
                Admin Defaultadmin = new Admin("Arun" , "arunelayaperumal@admin.com" , "Arun@2003");

                // Saving it in the repository
                adminRepository.save(Defaultadmin);
            }

        } catch (Exception exception) {
            throw new RuntimeException("Some exception has occurred in Admin Initial Seeder : "+exception.getMessage());
        }
    }
}
