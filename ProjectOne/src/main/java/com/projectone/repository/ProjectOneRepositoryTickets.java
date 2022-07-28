package com.projectone.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.projectone.model.Tickets;
import com.projectone.utility.ConnectionUtility;

public class ProjectOneRepositoryTickets {

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

	public static void updateTicket(Tickets ticket) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
	
		final String SQL = "update tickets set approval=?, processed=? where id=?";
		
		try {
			conn = ConnectionUtility.getNewConnection();
			stmt = conn.prepareStatement(SQL);
	
			stmt.setString(1, ticket.getApproval());
			stmt.setBoolean(2, ticket.isProcessed());
			stmt.setInt(3, ticket.getTicketid());
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
		PreparedStatement stmt = null;
		ResultSet set = null;
		List<Tickets> tickets = new ArrayList<>();
		
		final String SQL = "select * from tickets where username = ?";
		
		try {
			conn = ConnectionUtility.getNewConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, username);
			set = stmt.executeQuery();
	
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
			if (set != null) {
				set.close();
			}
			}catch(SQLException e) {
				e.printStackTrace();
		    }
	    }
	
	return tickets;
	}
	
	public static List<Tickets> findAllTickets() {
		
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
	
	public static boolean isProcessed(int ticketid) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		boolean isprocessed = true;
		
		String SQL = "select processed from tickets where id = " + ticketid;
		
		try {
			conn = ConnectionUtility.getNewConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			set.next();
			isprocessed = set.getBoolean(1);
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			
			try {
			conn.close();
			stmt.close();
			if (set != null) {
				set.close();
			}
			}catch(SQLException e) {
				e.printStackTrace();
		    }
	    }
	
	return isprocessed;
	}
	
	public static boolean approveticket(Tickets ticket, String approval) {
		
		if (!ProjectOneRepositoryTickets.isProcessed(ticket.getTicketid())) {
			ticket.setApproval(approval);
			ticket.setProcessed(true);
			ProjectOneRepositoryTickets.updateTicket(ticket);
			return true;
		}
		else {
			return false;
		}
	 }

}
