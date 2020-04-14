package com.bharat.amigoscode.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	PasswordEncoder passwordencoder;

	@Autowired
	public SecurityConfig(PasswordEncoder passwordencoder) {
		super();
		this.passwordencoder = passwordencoder;
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.
		authorizeRequests()
		.antMatchers("/","index","/css/*","/js/*")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails adminbharat=User.builder()	
		.username("adminbharat")
		.password(passwordencoder.encode("adminpass"))
		.roles("ADMIN")
		.build();
		
		UserDetails userbharat=User.builder()
		.username("bharat")
		.password(passwordencoder.encode("pass"))
		.roles("STUDENT")
		.build();
		return new InMemoryUserDetailsManager(userbharat,adminbharat);
	}
	
	

	
}
