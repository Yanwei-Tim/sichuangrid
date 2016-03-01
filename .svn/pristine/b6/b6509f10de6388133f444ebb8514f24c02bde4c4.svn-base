package com.tianque.ticket.service;

import java.io.Serializable;

import com.tianque.ticket.Ticket;

public interface TicketService extends Serializable {
	final int DEFAULT_EXPRIED_SECOND = 300;

	Ticket addTicket(Ticket ticket, int expriedTime);

	Ticket updateTicket(Ticket ticket, int expriedTime);

	Ticket getTicketById(String ticketId);
}
