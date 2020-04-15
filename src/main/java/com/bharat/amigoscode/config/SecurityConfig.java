package com.bharat.amigoscode.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
		csrf().disable() 
		.authorizeRequests()
		.antMatchers("/","index","/css/*","/js/*")
		.permitAll()
		.antMatchers("/api/**")
		.hasRole(AppUserRoles.STUDENT.name())	
		.antMatchers(HttpMethod.POST,"/admin/api**")
		.hasAuthority(AppUserPermissions.COURSE_WRITE.getPermission())
		.antMatchers(HttpMethod.PUT,"/admin/api**")
		.hasAuthority(AppUserPermissions.COURSE_WRITE.getPermission())
		.antMatchers(HttpMethod.DELETE,"/admin/api**")
		.hasAuthority(AppUserPermissions.COURSE_WRITE.getPermission())
		.antMatchers(HttpMethod.GET,"/admin/api**")
		.hasAnyRole(AppUserRoles.ADMIN.name(),AppUserRoles.ADMINTRAINEE.name())
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails adminBharat=User.builder()	
		.username("adminbharat")
		.password(passwordencoder.encode("adminpass"))
		.authorities(AppUserRoles.ADMIN.getGrantedAuthorities())
//		.roles(AppUserRoles.ADMIN.name())
		.build();
		
		UserDetails userBharat=User.builder()
		.username("userbharat")
		.password(passwordencoder.encode("userpass"))
		.authorities(AppUserRoles.STUDENT.getGrantedAuthorities())
//		.roles(AppUserRoles.STUDENT.name())
		.build();
		
		UserDetails adminTrainee=User.builder()
				.username("admintrainee")
				.password(passwordencoder.encode("admintraineepass"))
				.authorities(AppUserRoles.ADMINTRAINEE.getGrantedAuthorities())
//				.roles(AppUserRoles.ADMINTRAINEE.name())
				.build();
		return new InMemoryUserDetailsManager(userBharat,adminBharat,adminTrainee);
	}
	
						

	
}
