package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.Status;

public interface IStatusRepository extends JpaRepository<Status, Integer> {

}
