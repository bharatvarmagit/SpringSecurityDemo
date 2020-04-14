package com.bharat.amigoscode.config;

import com.google.common.collect.Sets;

import java.util.Set;

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
	
	public Set<AppUserPermissions> getPermission() {
		return this.permissions;
		
	}


}
