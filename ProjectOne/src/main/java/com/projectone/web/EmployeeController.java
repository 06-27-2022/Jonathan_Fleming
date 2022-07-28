package com.projectone.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectone.model.Employee;
import com.projectone.repository.ProjectOneRepositoryEmployee;

public class EmployeeController {
	
	public ObjectMapper objectMapper;
	
	public EmployeeController() {
		objectMapper = new ObjectMapper();
	}
	
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String json = objectMapper.writeValueAsString(ProjectOneRepositoryEmployee.findAllEmployees());
		System.out.println(json);
		return json;
	}
	
	public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Employee newEmployee = objectMapper.readValue(request.getInputStream(), Employee.class);
		ProjectOneRepositoryEmployee.saveEmployee(newEmployee);
	}
	
	public boolean newAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Employee newEmployee = objectMapper.readValue(request.getInputStream(), Employee.class);
		if (ProjectOneRepositoryEmployee.checkUsername(newEmployee)) {
			ProjectOneRepositoryEmployee.saveEmployee(newEmployee);
			return true;
		}else {
			return false;
		}
	}

}
