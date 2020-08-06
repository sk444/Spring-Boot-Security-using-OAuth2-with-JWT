package com.pixelTrice.firstSpringBootApplication;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

@Repository
public class UsersDBQuery {

	 @Autowired
	   private JdbcTemplate jdbcTemplate;

	   public UsersPojo getUserDetails(String username) {
	      Collection<GrantedAuthority> listOfgrantedAuthorities = new ArrayList<>();
	      String userSQLQuery = "SELECT * FROM USERS WHERE USERNAME=?";
	      List<UsersPojo> list = jdbcTemplate.query(userSQLQuery, new String[] { username },
	         (ResultSet rs, int rowNum) -> {
	         
	        	 UsersPojo user = new UsersPojo();
	         user.setUsername(username);
	         user.setPassword(rs.getString("PASSWORD"));
	         return user;
	      });
	      if (list.size() > 0) {
	         GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
	         listOfgrantedAuthorities.add(grantedAuthority);
	         list.get(0).setListOfgrantedAuthorities(listOfgrantedAuthorities);
	         return list.get(0);
	      }
	      return null;
	   }
}
