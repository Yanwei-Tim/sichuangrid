package com.tianque.ticket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.cache.service.CacheService;
import com.tianque.ticket.Ticket;
import com.tianque.ticket.service.TicketService;

@Service("ticketService")
public class TicketServiceImpl extends AbstractBaseService implements TicketService {
	@Autowired
	private CacheService cacheService;

	@Override
	public Ticket addTicket(Ticket ticket, int expriedTime) {
		// TODO generate ticketId;
		cacheService.set(ticket.getTicketId(), expriedTime, ticket);
		return ticket;
	}

	@Override
	public Ticket getTicketById(String ticketId) {
		return (Ticket) cacheService.get(ticketId);
	}

	@Override
	public Ticket updateTicket(Ticket ticket, int expriedTime) {
		cacheService.set(ticket.getTicketId(), expriedTime, ticket);
		return ticket;
	}

}
