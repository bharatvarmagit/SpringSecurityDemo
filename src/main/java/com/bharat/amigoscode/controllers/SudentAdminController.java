package com.bharat.amigoscode.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bharat.amigoscode.models.Student;

@RestController
@RequestMapping("admin/api/students/")
public class SudentAdminController {
	
	private static final List<Student> students=Arrays.asList(
			new Student(1,"jim morrison"),
			new Student(2,"ray manzarek"),
			new Student(3,"robbie kreiger"),
			new Student(4,"bharat varma")
			);
	
	@GetMapping
	public List<Student> getAllStudents(){
		System.out.println("get all students method invoked");
		return students;
	}
	
	@PostMapping
	public void registerNewStudent(@RequestBody Student student) {
		System.out.println("regestering "+student);	
	}
	@DeleteMapping(path= "{studentId}")
	public void deleteStudent(@PathVariable("studentId") Integer studentId) {
		System.out.println("deleting student with ID "+ studentId);
	}
	@PutMapping(path="{studentId}")
	public void updateStudent(@PathVariable("studentId")Integer studentId,@RequestBody Student student) {
		System.out.println("updating student with ID "+studentId+" with " +student);
	}
	
}

	
	
	
	
	












