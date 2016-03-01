package com.tianque.plugin.analysisNew.controller;

import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Session;
import com.tianque.plugin.analysisNew.domain.OrgLoginStanals;
import com.tianque.plugin.analysisNew.service.OrgLoginStanalsNewService;
import com.tianque.ticket.StatDataTicket;
import com.tianque.ticket.service.TicketService;

public class StatAnalyseOrgLoginNewThread extends Thread {
	private OrgLoginStanalsNewService orgLoginStanalsService;
	private OrgLoginStanals orgLoginStanals;
	private String ticketId;
	private TicketService ticketService;
	private Session session;

	public StatAnalyseOrgLoginNewThread(Session session,
			OrgLoginStanalsNewService orgLoginStanalsService,
			OrgLoginStanals orgLoginStanals, String ticketId,
			TicketService ticketService, int internalId) {
		this.session = session;
		this.orgLoginStanalsService = orgLoginStanalsService;
		this.orgLoginStanals = orgLoginStanals;
		this.ticketId = ticketId;
		this.ticketService = ticketService;
	}

	@Override
	public void run() {
		try {
			ThreadVariable.setSession(session);
			// updateTicketInfo(ticketId,"{msg:'.'}");
			reProduceDate(orgLoginStanals);
			updateTicketInfo(ticketId, "{successMsg:'数据生成完成'}");
		} catch (Exception e) {
			updateTicketInfo(ticketId, "{errorMsg:'强制退出，出错信息：" + e.getMessage()
					+ "'}");
		}
	}

	public void reProduceDate(OrgLoginStanals orgLoginStanals) {
		orgLoginStanalsService.setTicketService(ticketService, ticketId);
		orgLoginStanalsService.reCreateOrgLoginStanals(
				orgLoginStanals.getYear(), orgLoginStanals.getMonth());
	}

	private void updateTicketInfo(String ticketId, String description) {
		StatDataTicket ticket = (StatDataTicket) ticketService
				.getTicketById(ticketId);
		ticket.setDescription(description);
		ticketService
				.updateTicket(ticket, TicketService.DEFAULT_EXPRIED_SECOND);
	}

}
