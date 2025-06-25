package com.nt.service;

import com.nt.binding.LogInForm;
import com.nt.binding.SignUpForm;
import com.nt.binding.UnlockForm;
import com.nt.entity.UserEntity;

public interface IUserService {

	public boolean registerUser(SignUpForm form);
	
	public boolean unlockUserAccount(UnlockForm form);
	
	public UserEntity getByUserEmail(String mail);
	
	public String loginUser(LogInForm form);
}
