package com.projectone.model;

import java.util.Objects;

public class Tickets {

	private int ticketid;
	private int amount;
	private String description;
	private String type;
	private String approval;
	private boolean processed;
	private String username;
	
	public Tickets() {
		super();
	}

	public Tickets(int ticketid, int amount, String description, String type, String approval, boolean processed, String username) {
		super();
		this.ticketid = ticketid;
		this.amount = amount;
		this.description = description;
		this.type = type;
		this.approval = approval;
		this.processed = processed;
		this.username = username;
	}
	
	public int getTicketid() {
		return ticketid;
	}

	public void setTicketid(int ticketid) {
		this.ticketid = ticketid;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, approval, description, processed, ticketid, type, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tickets other = (Tickets) obj;
		return amount == other.amount && Objects.equals(approval, other.approval)
				&& Objects.equals(description, other.description) && processed == other.processed
				&& ticketid == other.ticketid && Objects.equals(type, other.type)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Tickets [ticketid=" + ticketid + ", amount=" + amount + ", description=" + description + ", type="
				+ type + ", approval=" + approval + ", processed=" + processed + ", username=" + username + "]";
	}

		
}
