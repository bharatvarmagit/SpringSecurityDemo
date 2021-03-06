package com.bharat.amigoscode.config;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum AppUserRoles {
	
	STUDENT(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(AppUserPermissions.COURSE_READ,
						  AppUserPermissions.COURSE_WRITE,
						  AppUserPermissions.STUDENT_READ,
						  AppUserPermissions.STUDENT_WRITE
			
			)),
	ADMINTRAINEE(Sets.newHashSet(AppUserPermissions.COURSE_READ,
			  					 AppUserPermissions.STUDENT_READ
			));
	
	private Set<AppUserPermissions> permissions;
	
	private AppUserRoles(Set<AppUserPermissions> permits) {
		
		this.permissions=permits;
	}
	
	public Set<AppUserPermissions> getPermissions() {
		return this.permissions;
	}
	public Set<GrantedAuthority> getGrantedAuthorities(){
		Set<GrantedAuthority> permissions=getPermissions().stream()
		.map(permission->new SimpleGrantedAuthority(permission.getPermission()))//COURSE_WRITE,..e.t.c
		.collect(Collectors.toSet());
		permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
		for (GrantedAuthority permission: permissions) {
			System.out.println(this.name());
			System.out.println("permissions :"+permission);
		}
		return permissions;
	
		
	}


}
