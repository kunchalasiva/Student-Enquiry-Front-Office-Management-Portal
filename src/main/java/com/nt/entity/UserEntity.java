package com.nt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name="userentity_tab")
@Data
@Entity
public class UserEntity {

	@Id
	@SequenceGenerator(name="gen",sequenceName="USER_TAB",allocationSize = 1,initialValue = 1)
	@GeneratedValue(generator = "gen",strategy = GenerationType.AUTO)
	@Column(name="user_id_col")
	private Integer id;
	
	
	@Column(name="user_name_col")
	private String userName;
	
	@Column(name="user_email_col")
	private String userEmail;
	
	@Column(name="user_phno_col")
	private String userPhno;
	
	@Column(name="user_pwd_col")
	private String userPwd;
	
	@Column(name="user_status_col")
	private String userStatus;
	
}
