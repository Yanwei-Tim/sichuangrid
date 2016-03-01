package com.tianque.ticket;

import java.util.UUID;

public class TicketIdGenerator {
	public synchronized static final String generateTicket() {
		return UUID.randomUUID().toString();
	}

	public static void main(String[] arg) {
		System.out.println(TicketIdGenerator.generateTicket());
		System.out.println(TicketIdGenerator.generateTicket());
		System.out.println(TicketIdGenerator.generateTicket());
		System.out.println(TicketIdGenerator.generateTicket());
		System.out.println(TicketIdGenerator.generateTicket());
	}
}
