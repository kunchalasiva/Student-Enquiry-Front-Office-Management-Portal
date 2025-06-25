package com.nt.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.binding.LogInForm;
import com.nt.binding.SignUpForm;
import com.nt.binding.UnlockForm;
import com.nt.entity.UserEntity;
import com.nt.repository.IUserRepository;
import com.nt.utils.EmailUtil;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImple implements IUserService {

	@Autowired
	private IUserRepository repository;
	
	

	@Autowired
	private EmailUtil email;

	@Override
	public boolean registerUser(SignUpForm form) {

		try {
			UserEntity entity = new UserEntity();

//			  Copy the Binding Object Details to the Entity Object
			BeanUtils.copyProperties(form, entity);

//			generate the random password
			String password = new Random()
					.ints(6, 0, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".length())
					.mapToObj(i -> "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".charAt(i))
					.map(Object::toString).collect(Collectors.joining());

			entity.setUserPwd(password);
			entity.setUserStatus("Locked");

			// use the repository
			repository.save(entity);

//			 Pass these generated password to the email.
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

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public UserEntity getByUserEmail(String mail) {
		return repository.findByUserEmail(mail);
	}

	@Override
	public boolean unlockUserAccount(UnlockForm form) {
		 
         UserEntity entity = repository.findByUserEmail(form.getMail());
         entity.setUserPwd(form.getNewpwd());
         entity.setUserStatus("Unlocked");
         // use the repository
         repository.save(entity);
		return true;
	}

	@Override
	public List<UserEntity> userLogin(LogInForm form) {
		// use the repository
		List<UserEntity> entity=repository.findAll();
		return entity.stream().filter(e -> 
						(e.getUserName().equalsIgnoreCase(form.getUserName()) || e.getUserEmail().equals(form.getEmail()))
							&& e.getUserPwd().equalsIgnoreCase(form.getPassword())).collect(Collectors.toList());
	}
}
