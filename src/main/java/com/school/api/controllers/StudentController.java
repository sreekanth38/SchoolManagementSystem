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
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//controller
@CrossOrigin(origins ="*")
@Api(tags = "student Management RestFul Services", value = "student controller", description = "controller for student")
@RestController
@Validated
public class StudentController {

	// Autowired the StudentService class
	@Autowired
	public StudentService studentService;

	// createStudent
	// PostMapping Annotation
	// RequestBody Annotation
	@ApiOperation(value = "Creates the new Students")
	@PostMapping("/student")
	public ResponseEntity<Void> createStudent(@ApiParam("Student information for a new student to be created") @Valid @RequestBody Student student, UriComponentsBuilder builder) {
		try {
			studentService.createStudent(student);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(builder.path("/student/{id}").buildAndExpand(student.getId()).toUri());
			return new ResponseEntity<Void>(header, HttpStatus.CREATED);
		} catch (ResourceExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}

	// getAllStudents method
	@ApiOperation(value = "Retrives the list of Students")
	@GetMapping("/student")
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	// getStudentById
	@ApiOperation(value = "Retrives Student based on StudentId")
	@GetMapping("/student/{id}")
	public Student getStudentById(@PathVariable("id") @Min(1) Long id) {
		try {
			Optional<Student> student = studentService.getStudentById(id);
			return student.get();// this get method returms the model class instead of optional one
		} catch (ResourceNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

	// updateStudentById
	@ApiOperation(value = "Updates the Students based on StudentId")
	@PutMapping("/student/{id}")
	public Student updateStudentById(@PathVariable("id") Long id, @Valid @RequestBody Student student) {
		try {
			return studentService.updateStudentById(id, student);
		} catch (ResourceNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}

	// deleteStudentById
	@ApiOperation(value = "Deletes the Student based on StudentId")
	@DeleteMapping("student/{id}")
	public void deleteStudentById(@PathVariable("id") Long id) {
		studentService.deleteStudentById(id);
	}

	// getStudentByUserName
	@ApiOperation(value = "Retrives Student based on UserName")
	@GetMapping("/student/ByStudentName/{username}")
	public Student getStudentByUserName(@PathVariable("username") String username) throws UserNameNotFoundExcepton {
		Student student = studentService.getStudentByStudentName(username);
		if (student == null) {
			throw new UserNameNotFoundExcepton(
					"student with userName " + username + " no exist in student respository");
		}
		return student;
	}
}
