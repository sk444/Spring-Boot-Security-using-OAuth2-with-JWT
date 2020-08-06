package com.pixelTrice.firstSpringBootApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsersService usersService;
	

	   @Bean
	   public PasswordEncoder encoder() {
	      return new BCryptPasswordEncoder();
	   }

	   @Override
	   @Autowired
	   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	      auth.userDetailsService(usersService).passwordEncoder(encoder());
	   }
	   @Override
	   protected void configure(HttpSecurity http) throws Exception {
	      http.authorizeRequests().anyRequest().authenticated().and().sessionManagement()
	         .sessionCreationPolicy(SessionCreationPolicy.NEVER);
	   }
	   @Override
	   public void configure(WebSecurity web) throws Exception {
	      web.ignoring();
	   }
	   @Override
	   @Bean
	   public AuthenticationManager authenticationManagerBean() throws Exception {
	      return super.authenticationManagerBean();
	   }
}
