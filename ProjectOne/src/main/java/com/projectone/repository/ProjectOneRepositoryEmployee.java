package com.projectone.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.projectone.model.Employee;
import com.projectone.utility.ConnectionUtility;

public class ProjectOneRepositoryEmployee {
	
	public static void saveEmployee(Employee employee) {
		
		Connection conn = null;
		PreparedStatement stmt = null;

		final String SQL = "insert into employees values(?, ?, ?, ?)";
		
		try {
			conn = ConnectionUtility.getNewConnection();
			stmt = conn.prepareStatement(SQL);

			stmt.setString(1, employee.getUsername());
			stmt.setString(2, employee.getEmployeeName());
			stmt.setString(3, employee.getPassword());
			stmt.setString(4, employee.getRole());
			stmt.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
			conn.close();
			stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static Employee findByUsername(String Username) {
		
		Employee employee = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		final String SQL = "select * from employees where username = ?";
		
		try {
			conn = ConnectionUtility.getNewConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, Username);
			set = stmt.executeQuery();

			if(set.next()) {
				employee = new Employee(
						set.getString(1),
						set.getString(2),
						set.getString(3),
						set.getString(4)
						);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				stmt.close();
				set.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return employee;
	}
	
public static List<Employee> findAllEmployees() {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;

		List<Employee> employees = new ArrayList<>();
		
		final String SQL = "select * from employees";
		
		try {
			conn = ConnectionUtility.getNewConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			
			while(set.next()) {

				Employee employee = new Employee(
						set.getString(1),
						set.getString(2),
						set.getString(3),
						set.getString(4));
				
				employees.add(employee);
				 
			}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
	
			try {
			conn.close();
			stmt.close();
			set.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return employees;
	}

	
	public static boolean login(String username, String password) {
	
		Employee employee = null;
		
		employee = ProjectOneRepositoryEmployee.findByUsername(username);
		
		if (password.equals(employee.getPassword())) {
			return true;
		}
		
		return false;
	}
	
	public static boolean checkUsername(Employee employee) {
		
		Employee employeeCheck = ProjectOneRepositoryEmployee.findByUsername(employee.getUsername());
		
		if (employeeCheck == null) {
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean checkIfManager(String username) {
		Employee employeeCheck = ProjectOneRepositoryEmployee.findByUsername(username);
		if (employeeCheck.getRole().equals("manager")) {
			return true;
		}
		return false;
	}
}
