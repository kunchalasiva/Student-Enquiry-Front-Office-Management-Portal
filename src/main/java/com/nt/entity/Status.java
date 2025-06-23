package com.nt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="user_status_tab")
public class Status {
	
	@Id
	@SequenceGenerator(name="status_gen",allocationSize = 1,initialValue = 1,sequenceName = "STATUS_SEQ")
	@Column(name="status_id_col")
	private Integer id;
	
	@Column(name="userstatus_col")
	private String userStatus;
}
