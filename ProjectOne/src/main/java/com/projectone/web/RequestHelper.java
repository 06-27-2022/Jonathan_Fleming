package com.projectone.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.projectone.repository.ProjectOneRepositoryEmployee;

public class RequestHelper {

	private static EmployeeController employeeController = new EmployeeController();
	private static TicketController ticketController = new TicketController();
	private static HttpSession session;
	
	private static String username;
	private static String password;
	
	public RequestHelper() {
		super();
	}

	public static void processGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String resource = request.getRequestURI();
		PrintWriter writer;

		String isolatedResource = resource.replace("/ProjectOne/", "");

		switch (isolatedResource) {
		case "employees":
			response.setContentType("application/json");
			writer = response.getWriter();
			writer.write(employeeController.findAll(request, response));
			break;
			
		case "tickets":
			response.setContentType("application/json");
			writer = response.getWriter();
			writer.write(ticketController.findAll(request, response));
			break;
			
		case "mytickets":
			session = request.getSession(false);
			if (session !=null) {
				response.setContentType("application/json");
				writer = response.getWriter();
				writer.write(ticketController.findByAccount(username));
			}else {
				response.setStatus(401);
			}
			
			break;

		default:
			response.setStatus(404);
			break;
		}

	}

	public static void processPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String resource = request.getRequestURI().replace("/ProjectOne/", "");

		switch (resource) {
		case "newaccount":
			if (employeeController.newAccount(request, response)) {
				response.setStatus(201);
			} else {
				response.setStatus(409);
				response.setContentType("text/html");
				PrintWriter writer = response.getWriter();
				writer.write("That account name is already taken.");
			}
			break;
			
		case "settings" :
			session = request.getSession(false);
			if(session != null)	{
				employeeController.save(request, response);
				response.setStatus(201);
			}
			break;
			
		case "login":
			username = request.getParameter("username");
			password = request.getParameter("password");
			if (ProjectOneRepositoryEmployee.login(username, password)) {
				session = request.getSession();
				response.setStatus(200);
			}else
				response.setStatus(401);
			break;
		
		case "logout":
			session = request.getSession(false);
			if(session != null) {
				session.invalidate();
				response.setStatus(200);
				username = null;
				password = null;
			}
			else {
				response.setStatus(400);
			}
			break;
		
		case "submitticket":
			session = request.getSession(false);
			if(session !=null) {
				ticketController.save(request, response);
				response.setStatus(201);
			}else {
				response.setStatus(401);
			}
			break;

		case "managetickets":
			session = request.getSession(false);
			if (session != null) {
				if(ProjectOneRepositoryEmployee.checkIfManager(username)) {
					if (ticketController.ticketapproval(request, response)) {
						response.setStatus(201);
					}
					else {
						response.setStatus(405);
					}
				}else {
					response.setStatus(403);
				}
			}else {
				response.setStatus(401);
			}
			
			break;
		default:
			response.setStatus(404);
			break;
		}

	}
}
