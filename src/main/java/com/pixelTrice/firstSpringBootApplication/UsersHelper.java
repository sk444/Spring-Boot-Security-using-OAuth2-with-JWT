package com.pixelTrice.firstSpringBootApplication;

import org.springframework.security.core.userdetails.User;

public class UsersHelper extends User{

	private static final long serialVersionUID = 1L;
	   public UsersHelper(UsersPojo user) {
	      super(
	    		  user.getUsername(),
	    		  user.getPassword(),
	    		  user.getListOfgrantedAuthorities()
	    		);
	   }
}
