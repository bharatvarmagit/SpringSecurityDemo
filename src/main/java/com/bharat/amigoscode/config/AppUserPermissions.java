package com.bharat.amigoscode.config;

public enum AppUserPermissions {
	STUDENT_READ("student:read"),
	STUDENT_WRITE("student:write"),
	COURSE_READ("course:read"),
	COURSE_WRITE("course:write");
	
	private final String permission;
	
	private AppUserPermissions(String permission) {
		this.permission=permission;
	}
	public String getPermission() {
		return permission;
	}
}
