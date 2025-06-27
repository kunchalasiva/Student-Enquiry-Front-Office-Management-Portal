package com.nt.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "student_enquires")
public class StudentEntity {

	@Id
	@GeneratedValue
	private Integer enqid;
	
	private String studentName;
	
	private String phoneNumber;
	
	private String classMode;
	
	private String courseName;
	
	private String enqStatus;
	
	@CreationTimestamp
	private LocalDate dataCreated;
	
	@UpdateTimestamp
	private LocalDate lastupdated;
	
	@ManyToOne
	@JoinColumn(name="user_id_col")
	private UserEntity user;
}
