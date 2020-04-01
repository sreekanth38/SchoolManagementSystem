package com.school.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.school.api.exception.ResourceExistsException;
import com.school.api.exception.ResourceNotFoundException;
import com.school.api.exception.UserNameNotFoundExcepton;
import com.school.api.model.Student;
import com.school.api.service.StudentService;

//controller
@RestController
@Validated
public class StudentController {

	// Autowired the StudentService class
	@Autowired
	public StudentService studentService;

	
	// createStudent
	// PostMapping Annotation
	// RequestBody Annotation
	@PostMapping("/student")
	public ResponseEntity<Void> createStudent(@Valid @RequestBody Student student, UriComponentsBuilder builder) {
		try {
			studentService.createStudent(student);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(builder.path("/student/{id}").buildAndExpand(student.getId()).toUri());
			return new ResponseEntity<Void>(header,HttpStatus.CREATED);
		} 
		catch (ResourceExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}

	// getAllStudents method
		@GetMapping("/student")
		public List<Student> getAllStudents() {
			return studentService.getAllStudents();
		}
		
	// getStudentById
	@GetMapping("/student/{id}")
	public Optional<Student> getStudentById(@PathVariable("id") @Min(1) Long id) {
		try {
			return studentService.getStudentById(id);
		} catch (ResourceNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

	// updateStudentById
	@PutMapping("/student/{id}")
	public Student updateStudentById(@PathVariable("id") Long id,@Valid @RequestBody Student student) {
		try {
			return studentService.updateStudentById(id, student);
		} catch (ResourceNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}

	// deleteStudentById
	@DeleteMapping("student/{id}")
	public void deleteStudentById(@PathVariable("id") Long id) {
		studentService.deleteStudentById(id);
	}

	// getStudentByUserName
	@GetMapping("/student/ByStudentName/{username}")
	public Student getStudentByUserName(@PathVariable("username") String username) throws UserNameNotFoundExcepton {
		Student student = studentService.getStudentByStudentName(username);
		if(student==null) {
		throw new UserNameNotFoundExcepton("student with userName "+ username + 
				" no exist in student respository");
		}
		return student;
	}
}
