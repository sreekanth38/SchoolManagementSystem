package com.school.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@ApiModel(description ="this model is to create a student")
@Data
@Component
@Entity
@Table(name = "students")
public class Student {
	@ApiModelProperty(notes ="Auto generated unique id", required = true, position = 1)
	@Id
	@GeneratedValue
	@JsonProperty("Student_id")
	private Long id;
	@ApiModelProperty(notes ="username should be unique",example = "sreekanth38", required = true, position = 2)
	@Column(name = "USERNAME", length = 50, nullable = false, unique = true)
	@NotEmpty(message = "userName is a mandatory field, please provide userName")
	@Size(min = 6,max = 12, message ="user name must be in between 6 to 12 characters")
	@JsonProperty("User_Name")
	private String userName;
	
	@Column(name = "FIRST_NAME", length = 50, nullable = false)
	@NotEmpty(message = "firstName is a mandatory field, please provide firstName")
	@Size(min = 2, max = 50 ,message = "firstName should have atleast 2 characters")
	@JsonProperty("First_Name")
	private String firstName;
	
	@Column(name = "LAST_NAME", length = 50, nullable = false)
	@NotEmpty(message = "lastName is a mandatory field, please provide lastName")
	@Size(min = 2,max = 50, message = "lastName should have atleast 2 characters")
	@JsonProperty("Last_Name")
	private String lastName;
	
	@Column(name = "EMAIL_ID", length = 50, nullable = false)
	@NotEmpty(message = "emailId is a mandatory field, please provide emailId")
	@JsonProperty("Email")
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