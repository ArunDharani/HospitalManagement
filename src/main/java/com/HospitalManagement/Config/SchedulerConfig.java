// Creation of scheduler class by E.Arun on (27th July 2026)

// Importing the necessary packages
package com.HospitalManagement.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;


// Declaring Scheduler class

@Configuration      // since it is a parent configured class
public class SchedulerConfig {

    @Bean(name="customTaskScheduler")
    public ThreadPoolTaskScheduler taskScheduler() {

        // creation of new reference of TaskScheduler
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();

        // providing properties
        scheduler.setPoolSize(10);
        scheduler.setThreadNamePrefix("custom-scheduler - ");
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        scheduler.setAwaitTerminationSeconds(60);

        // initializing and returning
        scheduler.initialize();
        return scheduler;
    }
}
