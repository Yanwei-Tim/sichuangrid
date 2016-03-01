package com.tianque.controller.statanalysethread;

import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.IssueTypeStanal;
import com.tianque.domain.Session;
import com.tianque.service.IssueTypeStanalService;
import com.tianque.ticket.StatDataTicket;
import com.tianque.ticket.service.TicketService;

public class StatAnalyseIssueTypeThread extends Thread {
	private IssueTypeStanalService issueTypeStanalService;
	private IssueTypeStanal issueTypeStanal;
	private String ticketId;
	private TicketService ticketService;
	private Session session;

	public StatAnalyseIssueTypeThread(Session session,
			IssueTypeStanalService issueTypeStanalService, IssueTypeStanal issueTypeStanal,
			String ticketId, TicketService ticketService) {
		this.session = session;
		this.issueTypeStanalService = issueTypeStanalService;
		this.issueTypeStanal = issueTypeStanal;
		this.ticketId = ticketId;
		this.ticketService = ticketService;
	}

	@Override
	public void run() {
		try {
			ThreadVariable.setSession(session);
			updateTicketInfo(ticketId, "{msg:'.'}");
			reProduceDate(issueTypeStanal);
			updateTicketInfo(ticketId, "{successMsg:'数据生成完成'}");
		} catch (Exception e) {
			updateTicketInfo(ticketId, "{errorMsg:'强制退出，出错信息：" + e.getMessage() + "'}");
		}
	}

	public void reProduceDate(IssueTypeStanal issueTypeStanal) {
		issueTypeStanalService.reCreateMonthIssueTypeStanalsByOrgId(issueTypeStanal.getOrg()
				.getId(), issueTypeStanal.getYear(), issueTypeStanal.getMonth());
		// issueTypeStanalService.reProduceDate(issueTypeStanal.getOrg().getId(),issueTypeStanal.getYear(),
		// issueTypeStanal.getMonth());
	}

	private void updateTicketInfo(String ticketId, String description) {
		StatDataTicket ticket = (StatDataTicket) ticketService.getTicketById(ticketId);
		ticket.setDescription(description);
		ticketService.updateTicket(ticket, TicketService.DEFAULT_EXPRIED_SECOND);
	}

}
