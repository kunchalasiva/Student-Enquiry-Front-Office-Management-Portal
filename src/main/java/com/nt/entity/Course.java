package com.nt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="user_course_tab")
public class Course {
	@Id
	@SequenceGenerator(name="course_gen",allocationSize = 1,initialValue = 1,sequenceName = "COURSE_SEQ")
	@Column(name="course_id_col")
	private Integer id;
	
	@Column(name="usercourse_col")
	private String userCourse;
}