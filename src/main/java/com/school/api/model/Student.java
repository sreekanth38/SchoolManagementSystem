package com.school.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.Data;
@Data
@Component
@Entity
@Table(name = "students")
public class Student {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "USERNAME", length = 50, nullable = false, unique = true)
	@NotEmpty(message = "userName is a mandatory field, please provide userName")
	private String userName;
	
	@Column(name = "FIRST_NAME", length = 50, nullable = false)
	@NotEmpty(message = "firstName is a mandatory field, please provide firstName")
	@Size(min = 2,message = "firstName should have atleast 2 characters")
	private String firstName;
	
	@Column(name = "LAST_NAME", length = 50, nullable = false)
	@NotEmpty(message = "lastName is a mandatory field, please provide lastName")
	@Size(min = 2,message = "lastName should have atleast 2 characters")
	private String lastName;
	
	@Column(name = "EMAIL_ID", length = 50, nullable = false)
	@NotEmpty(message = "emailId is a mandatory field, please provide emailId")
	private String emailId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	

}