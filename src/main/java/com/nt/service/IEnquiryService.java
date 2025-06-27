package com.nt.service;

import java.util.List;

import com.nt.binding.Dashboard;
import com.nt.binding.StudentForm;
import com.nt.entity.Course;
import com.nt.entity.Status;

public interface IEnquiryService {

	public Dashboard getEnquires(Integer id);
	
	public List<Status> getAllStatus();
	
	public List<Course> getAllCourse();
	
	public String addEnquiries(StudentForm entity);
}
