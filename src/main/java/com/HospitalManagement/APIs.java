// File Created by Arun on Friday (23/1/26)

// Importing the necessary packages
package com.HospitalManagement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class APIs {
    public static void main(String[] args) {
        SpringApplication.run(APIs.class, args);
    }
}
