package com.projectone.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.projectone.model.Employee;
import com.projectone.model.Tickets;
import com.projectone.utility.ConnectionUtility;

public class ProjectOneRepository {
	
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
	
	public static void saveTicket(Tickets ticket) {
		
		Connection conn = null;
		PreparedStatement stmt = null;

		final String SQL = "insert into tickets values(default, ?, ?, ?, ?, ?,?)";
		
		try {
			conn = ConnectionUtility.getNewConnection();
			stmt = conn.prepareStatement(SQL);

			stmt.setInt(1, ticket.getAmount());
			stmt.setString(2, ticket.getDescription());
			stmt.setString(3, ticket.getType());
			stmt.setString(4, ticket.getApproval());
			stmt.setBoolean(5, ticket.isProcessed());
			stmt.setString(6, ticket.getUsername());
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

	public static List<Tickets> findTickets(String username) {
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet set = null;
	List<Tickets> tickets = new ArrayList<>();
	
	final String SQL = "select * from tickets";
	
	try {
		conn = ConnectionUtility.getNewConnection();
		stmt = conn.createStatement();
		set = stmt.executeQuery(SQL);

		while(set.next()) {
						
			Tickets ticket = new Tickets(
					set.getInt(1),
					set.getInt(2),
					set.getString(3),
					set.getString(4),
					set.getString(5),
					set.getBoolean(6),
					set.getString(7));					
			
			tickets.add(ticket);
			 
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
	
	return tickets;
	}

 	public static boolean login(String username, String password) {
	
		Employee employee = null;
		
		employee = ProjectOneRepository.findByUsername(username);
		
		if (password.equals(employee.getPassword())) {
			return true;
		}
		
		return false;
	}
	
	public static boolean checkUsername(Employee employee) {
		
		Employee employeeCheck = ProjectOneRepository.findByUsername(employee.getUsername());
		
		if (employeeCheck.equals(null)) {
			return true;
		}
		else {
			return false;
		}
	}
}
