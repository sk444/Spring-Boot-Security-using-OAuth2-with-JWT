package com.pixelTrice.firstSpringBootApplication;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class UsersPojo {

	   private String username;
	   private String password;
	   private Collection<GrantedAuthority> listOfgrantedAuthorities = new ArrayList<>();
	
	   public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<GrantedAuthority> getListOfgrantedAuthorities() {
		return listOfgrantedAuthorities;
	}
	public void setListOfgrantedAuthorities(Collection<GrantedAuthority> listOfgrantedAuthorities) {
		this.listOfgrantedAuthorities = listOfgrantedAuthorities;
	}
	

}
