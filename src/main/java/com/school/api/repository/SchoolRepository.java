package com.school.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.api.model.Student;

@Repository
public interface SchoolRepository extends JpaRepository<Student, Long> {
	
	Student findByUserName(String username);
}
