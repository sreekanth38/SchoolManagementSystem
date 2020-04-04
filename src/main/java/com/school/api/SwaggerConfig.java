package com.school.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class) 
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)//we are telling that wew are going to use documentation type of Swagget2 to Docket
				.apiInfo(getApiInfo())//use this method when we have a method that returns ApiInfo(getApiInfo method here) 
				.select()//this select creates a builder which is used to define which controllers and which of there methods are included in the generated Documentation
				//.apis(RequestHandlerSelectors.any())//apis define the classes which is nothing but controller and model classes to be included-herewe including all of thembut we can limit them by base package or class annotation ect.
				.apis(RequestHandlerSelectors.basePackage("com.school.api"))//here we are restricting theservices based on basepackage
				//.paths(PathSelectors.any())//this will give all the services of any path
				.paths(PathSelectors.ant("/student/**"))
				.build();
	}
	
	//Swagger Metadata : http://localhost:8080/v2/api-docs
	//swagger UI URL: http://localhost:8080/swagger-ui.html
	
	
	//method for swagger header information
	public ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("School Management Service")
				.description("this page lists all the services of school management")
				.version("1.0")
				.contact(new Contact("Sreekanth Reddy","https://www.javaDeveloper.com","ch.sreekanth38@gmail.com"))
				.build();
	}
	
	// jsr-303 spec: https://beanvalidation.org/1.0/spec/
}
