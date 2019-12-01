package com.example.demo.security;

import javax.ws.rs.HttpMethod;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.RestController;

@EnableResourceServer
@RestController
public class ResourceServerConfiguration  extends ResourceServerConfigurerAdapter{

	@Override
	 public void configure (HttpSecurity http) throws Exception {

	 http
     //HTTP Basic authentication
     .httpBasic()
     .and()
     .authorizeRequests()
     .antMatchers(HttpMethod.GET, "/student/**").hasRole("GUEST")
     .antMatchers(HttpMethod.POST, "/student").hasRole("USER")
     .antMatchers(HttpMethod.PUT, "/student").hasRole("USER")
     .antMatchers(HttpMethod.DELETE, "/student/**").hasRole("ADMIN")
     .and()
     .csrf().disable()
     .formLogin().disable();

	 }

   
}
