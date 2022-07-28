package com.projectone.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectone.model.Tickets;
import com.projectone.repository.ProjectOneRepositoryTickets;

public class TicketController {
	

public ObjectMapper objectMapper;
	
	public TicketController() {
		objectMapper = new ObjectMapper();
	}
	
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String json = objectMapper.writeValueAsString(ProjectOneRepositoryTickets.findAllTickets());
		return json;
	}
	
	public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Tickets newTicket = objectMapper.readValue(request.getInputStream(), Tickets.class);
		ProjectOneRepositoryTickets.saveTicket(newTicket);
	}
	
	
	public String findByAccount(String username) throws JsonProcessingException {
	 
		String json = objectMapper.writeValueAsString(ProjectOneRepositoryTickets.findTickets(username));
		return json;
	 }
	
	public boolean ticketapproval(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Tickets newTicket = objectMapper.readValue(request.getInputStream(), Tickets.class);
		return ProjectOneRepositoryTickets.approveticket(newTicket, request.getParameter("approval"));
	}
}
