package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.StudentEntity;

public interface IStudentRepository extends JpaRepository<StudentEntity, Integer>{

}
