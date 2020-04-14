package com.bharat.amigoscode.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bharat.amigoscode.models.Student;

@RestController
@RequestMapping("api/students")
public class StudentController {
	private static final List<Student> students=Arrays.asList(
			new Student(1,"jim morrison"),
			new Student(2,"ray manzarek"),
			new Student(3,"robbie kreiger")
			);
	@GetMapping("/{studentId}")
	Student getStudentById(@PathVariable("studentId")  int studentid ) {
		return students.stream()
				.filter(student-> studentid==student.getId())
				.findFirst()
				.orElseThrow(()->new IllegalStateException("student not found"));
	}
	

}
