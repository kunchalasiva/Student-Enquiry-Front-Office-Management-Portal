package com.nt.binding;

import lombok.Data;

@Data
public class UnlockForm {

	private String mail;
	
	private String tempwd;
	
	private String newpwd;
	
	private String conpwd;
}
