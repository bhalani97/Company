package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
@EnableWebSecurity
public class RestServiceApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(RestServiceApplication.class, args);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder().username("user").password("{noop}secret").roles("USER","GUEST")
				.build();
		UserDetails userAdmin = User.builder().username("admin").password("{noop}secret")
				.roles("ADMIN","GUEST","USER").build();
		UserDetails guest = User.builder().username("guest").password("{noop}secret").roles("GUEST")
				.build();
		return new InMemoryUserDetailsManager(user, userAdmin,guest);
	}

//	@Bean
//	
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

	@Override protected void configure(HttpSecurity http) throws Exception { 
		 http 
		 .csrf()
		 .disable()
		 .authorizeRequests()
		 .antMatchers("/")
		 .permitAll() .antMatchers("/student")
		 .authenticated() ;
	}
}
