package com.tianque.ticket;

public class StatDataTicket extends AbstractTicket {
	public static final int COMPLETE = 1;
	public static final int VALIDATION = 2;
	public static final int ERROR = 4;

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
