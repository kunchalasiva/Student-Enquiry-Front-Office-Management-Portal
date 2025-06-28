package com.nt.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.binding.Dashboard;
import com.nt.binding.StudentForm;
import com.nt.entity.Course;
import com.nt.entity.Status;
import com.nt.entity.StudentEntity;
import com.nt.entity.UserEntity;
import com.nt.repository.ICourseRepository;
import com.nt.repository.IStatusRepository;
import com.nt.repository.IStudentRepository;
import com.nt.repository.IUserRepository;

@Service
public class EnquiryServiceImple implements IEnquiryService {

    @Autowired
    private ICourseRepository courseRepo;

    @Autowired
    private IStatusRepository statusRepo;

    @Autowired
    private IUserRepository userRepo; 
    

    @Autowired
    private IStudentRepository studentRepo;
    
    @Override
    public Dashboard getEnquires(Integer userid) {
        Dashboard dashboard = new Dashboard();

        Integer userId= userid;

        UserEntity user = userRepo.findById(userId).orElse(null);

        if (user == null) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }

        List<StudentEntity> studentEntity = user.getEnquires();

        List<Course> course = courseRepo.findAll();
        List<Status> status = statusRepo.findAll();

        int totalEnqCount = studentEntity.size();
        int enrollCount = studentEntity.stream()
                .filter(e -> "Enrolled".equalsIgnoreCase(e.getEnqStatus()))
                .collect(Collectors.toList()).size();

        int lostCount = studentEntity.stream()
                .filter(e -> "Lost".equalsIgnoreCase(e.getEnqStatus()))
                .collect(Collectors.toList()).size();

        dashboard.setTotalEnqCount(totalEnqCount);
        dashboard.setEnrolledEnqCount(enrollCount);
        dashboard.setLostEnqCount(lostCount);

        return dashboard;
    }
    
    @Override
    public List<Course> getAllCourse() {
    	// use the repo
    	List<Course> course=courseRepo.findAll();
    	return course;
    }
    
    @Override
    public List<Status> getAllStatus() {
    	return statusRepo.findAll();
    }
    
  
    @Override
    public String addEnquiries(StudentForm form) {
        StudentEntity entity = new StudentEntity();
        BeanUtils.copyProperties(form, entity);

        UserEntity user = userRepo.findById(form.getUserId()).orElse(null);
        entity.setUser(user); 

        studentRepo.save(entity);

        return "Enquiry Saved";
    }
}
