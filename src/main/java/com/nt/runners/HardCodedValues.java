package com.nt.runners;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.nt.entity.Course;
import com.nt.entity.Status;
import com.nt.repository.ICourseRepository;
import com.nt.repository.IStatusRepository;

@Component
public class HardCodedValues implements ApplicationRunner {

    @Autowired
    private ICourseRepository courseRepo;

    @Autowired
    private IStatusRepository statusRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
       
    	// Clear existing data 
        courseRepo.deleteAll();
        statusRepo.deleteAll();

        // Create and save courses
        Course c1 = new Course();
        c1.setUserCourse("Java");

        Course c2 = new Course();
        c2.setUserCourse("AWS");

        Course c3 = new Course();
        c3.setUserCourse("Spring");

        courseRepo.saveAll(Arrays.asList(c1, c2, c3));

        // Create and save statuses
        Status s1 = new Status();
        s1.setUserStatus("New");

        Status s2 = new Status();
        s2.setUserStatus("Enrolled");

        Status s3 = new Status();
        s3.setUserStatus("Lost");

        statusRepo.saveAll(Arrays.asList(s1, s2, s3));
    }
}
