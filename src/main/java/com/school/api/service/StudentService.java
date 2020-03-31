package com.school.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.school.api.exception.ResourceExistsException;
import com.school.api.exception.ResourceNotFoundException;
import com.school.api.model.Student;
import com.school.api.repository.SchoolRepository;

@Service
public class StudentService {

	@Autowired
	public SchoolRepository schoolRepository;

	// CreateStudent Method
	public Student createStudent(Student student) throws ResourceExistsException {
		// if user exists (userName) throw ResourceExistException
		Student existingStudent = schoolRepository.findByUserName(student.getUserName());
		if (existingStudent != null) {
			throw new ResourceExistsException(
					"student with  username " + student.getUserName() + " already exist in student repository");
		}
		return schoolRepository.save(student);

	}

	// getAllStudents
	public List<Student> getAllStudents() {
		return schoolRepository.findAll();
	}

	// getStudentById
	public Optional<Student> getStudentById(Long id) throws ResourceNotFoundException {
		Optional<Student> student = schoolRepository.findById(id);
		if (!student.isPresent()) {
			throw new ResourceNotFoundException("Student With id : " + id + " Not Found In Student Repository");
		}
		return student;
	}

	// getStudentByStudentName
	public Student getStudentByStudentName(String username) {
		return schoolRepository.findByUserName(username);
	}

	// updateStudentById
	public Student updateStudentById(Long id, Student student) throws ResourceNotFoundException {
		Optional<Student> upsatestudent = schoolRepository.findById(id);
		if (!upsatestudent.isPresent()) {
			throw new ResourceNotFoundException(
					"Student With id : " + id + " Not Found In Student Repository to update");
		}
		student.setId(id);
		return schoolRepository.save(student);

	}

	// deleteStudentById
	// responseStatusException is handles in service layer only(not in controller
	// layer)
	public void deleteStudentById(Long id) {
		Optional<Student> deletestudent = schoolRepository.findById(id);
		if (!deletestudent.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Student With id : " + id + " Not Found In Student Repository to delete");
		}
		schoolRepository.deleteById(id);
	}

}
