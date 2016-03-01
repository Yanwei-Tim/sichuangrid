package com.tianque.ticket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.tianque.ticket.DataDeleterTicket;
import com.tianque.ticket.DataImportTicket;
import com.tianque.ticket.Ticket;
import com.tianque.ticket.service.TicketService;

@Controller("ticketController")
@Scope("request")
public class TicketController extends ActionSupport {

	private static Logger logger = LoggerFactory
			.getLogger(TicketController.class);

	@Autowired
	private TicketService ticketService;

	private Ticket ticket;

	private String ticketId;

	private int currentErrorRow;

	private DataImportTicket imfomation;

	public String getTicketById() throws Exception {
		ticket = ticketService.getTicketById(ticketId);
		return ticket != null ? SUCCESS : ERROR;
	}

	private Ticket convertToReturnInfo(DataImportTicket info) {
		DataImportTicket result = new DataImportTicket();
		BeanUtils.copyProperties(info, result);
		return result;
	}

	public String getDataImportTicketByTicketId() throws Exception {
		DataImportTicket infomation = (DataImportTicket) ticketService
				.getTicketById(ticketId);
		if (null != infomation) {
			ticket = convertToReturnInfo(infomation);
		}
		return SUCCESS;
	}

	public String getDataExportTicketByTicketId() throws Exception {
		DataImportTicket imfomation = (DataImportTicket) ticketService
				.getTicketById(ticketId);
		if (null != imfomation) {
			ticket = convertToReturnInfo(imfomation);
		}
		return SUCCESS;
	}

	public String getDataDeleterTicketByTicketId() throws Exception {
		DataDeleterTicket imfomation = (DataDeleterTicket) ticketService
				.getTicketById(ticketId);
		ticket = convertToReturnDeleterInfo(imfomation);
		return ticket != null ? SUCCESS : ERROR;
	}

	private Ticket convertToReturnDeleterInfo(DataDeleterTicket info) {
		DataDeleterTicket result = new DataDeleterTicket();
		result.setCurrentRowCount(info.getCurrentRowCount());
		result.setDescription(info.getDescription());
		result.setTicketId(info.getTicketId());
		result.setTotalRowCount(info.getTotalRowCount());
		for (int index = currentErrorRow; index < info.getErrorMsgs().size(); index++) {
			result.appendErrorMsg(info.getErrorMsgs().get(index));
		}
		for (int index = currentErrorRow; index < info.getTypeMsgs().size(); index++) {
			result.appendTypeErrorMsg(info.getTypeMsgs().get(index));
		}
		return result;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public int getCurrentErrorRow() {
		return currentErrorRow;
	}

	public void setCurrentErrorRow(int currentErrorRow) {
		this.currentErrorRow = currentErrorRow;
	}

	public DataImportTicket getImfomation() {
		return imfomation;
	}

	public void setImfomation(DataImportTicket imfomation) {
		this.imfomation = imfomation;
	}

}
