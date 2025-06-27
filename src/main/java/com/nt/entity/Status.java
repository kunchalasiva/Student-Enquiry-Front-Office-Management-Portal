package com.nt.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="user_status_tab")
public class Status {
	
	@Id
	@Column(name="status_id_col")
	@GeneratedValue
	private Integer id;
	
	@Column(name="userstatus_col")
	private String userStatus;
}
