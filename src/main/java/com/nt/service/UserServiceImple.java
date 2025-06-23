package com.nt.service;

import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.binding.SignUpForm;
import com.nt.entity.UserEntity;
import com.nt.repository.IUserRepository;
import com.nt.utils.EmailUtil;

@Service
public class UserServiceImple implements IUserService {

	@Autowired
	private IUserRepository repository;
	
	@Autowired
	private EmailUtil email;
	
	@Override
	public boolean registerUser(SignUpForm form) {
		
		// check whether email is already available or not
		 int count=repository.findByEmailId(form.getUserEmail());
		 
		 if(count>0) {
		  return false;	 
		 }
		 
		try {
			UserEntity entity = new UserEntity();
			
			 // TODO : Copy the Binding Object Details to the Entity Object
			 BeanUtils.copyProperties(form, entity);
			 
			 // TODO : generate the random password
			 String password = new Random()
					    .ints(6, 0, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".length())
					    .mapToObj(i -> "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".charAt(i))
					    .map(Object::toString)
					    .collect(Collectors.joining());
		        
		        entity.setUserPwd(password);
		        entity.setUserStatus("Locked");
		       
		        // use the repository
		        repository.save(entity);
		        
			 // TODO : Pass these generated password to the email.
		        String to = form.getUserEmail();
		        String subject = "Unlock Your Account for Signup";
		        StringBuilder builder = new StringBuilder();

		        builder.append("Hello " + form.getUserName());
		        builder.append("<br><br>");
		        builder.append("Thank you for signing up with us!");
		        builder.append("<br><br>");
		        builder.append("Use This Temporary Password: " + password);
		        builder.append("<br><br>");

		        String link = "http://localhost:9001/NARESHIT-FRONT-OFFICE-STUDENT-PORTAL-APPLICATION/unlock?email=" + to;
		        builder.append("<a href='" + link + "'>Click here to unlock your account</a>");

		        email.SendEmail(to, subject, builder.toString());

			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		 return true;
	}
}
