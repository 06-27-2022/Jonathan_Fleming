package com.projectone.execute;

import com.projectone.model.Tickets;
import com.projectone.repository.ProjectOneRepository;

public class Executable {

	public static void main(String[] args) {
		
		/*
		 * Employee employee = new Employee("jon@gmail.com", "jon", "jonspassword",
		 * "employee");
		 * 
		 * ProjectOneRepository.saveEmployee(employee);
		 */
		
		Tickets ticket = new Tickets(0, 100, "java certification", "certification", "pending", false, "jon@gmail.com");
		
		ProjectOneRepository.saveTicket(ticket);
	}
	

}
