package com.tianque.datatransfer;

import org.springframework.context.ApplicationContext;

import com.tianque.baseInfo.excelimportlog.service.ExcelImportLogService;
import com.tianque.core.datatransfer.util.DataImportVo;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.platformMessage.constants.PlatformMessageSendType;
import com.tianque.platformMessage.constants.PlatformMessageType;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.state.TicketState;
import com.tianque.ticket.DataImportTicket;
import com.tianque.ticket.service.TicketService;

public class UpdateTicketInfo {
	private ApplicationContext appContext;
	private TicketService ticketService;

	private ExcelImportLogService excelImportLogService;

	private PlatformMessageService platformaMessageService;

	public UpdateTicketInfo(ApplicationContext appContext) {
		this.appContext = appContext;
		init();
	}

	private void init() {
		this.ticketService = (TicketService) appContext
				.getBean("ticketService");
		this.excelImportLogService = (ExcelImportLogService) appContext
				.getBean("excelImportLogService");
		this.platformaMessageService = (PlatformMessageService) appContext
				.getBean("platformaMessageService");

	}

	public void updateTicketInfo(String ticketId, String description,
			int currentDealRow, int importDataNum, int currentRow,
			int firstDataRow, int nullRowNum, Integer state) {
		DataImportTicket ticket = (DataImportTicket) ticketService
				.getTicketById(ticketId);
		if (null != DataImportVo.getTable().get(ticketId)) {
			ticket.setUserTicketNumber(DataImportVo.getTable().get(ticketId));
		}
		ticket.setState(state);
		ticket.setDescription(description);
		if (ticket.getFailureTime() != null
				&& ticket.getFailureTime() < System.currentTimeMillis()) {
			ticket.setDescription("{msg:'请求超时'}");
			ticket.setState(TicketState.TIMEOUT);
		}
		ticket.setCurrentRowCount(currentDealRow);
		ticket.setTotalRowCount(importDataNum);
		ticket.setNullRowNum(nullRowNum);
		ticket.setErrorCount(currentRow - firstDataRow);
		ticketService
				.updateTicket(ticket, TicketService.DEFAULT_EXPRIED_SECOND);
	}

	public void updateTicketErrorMsg(String ticketId, ValidateResult vresult,
			int currentDealRow, int importDataNum, int currentRow,
			int firstDataRow, int nullRowNum, Integer state) {

		DataImportTicket ticket = (DataImportTicket) ticketService
				.getTicketById(ticketId);
		if (null != DataImportVo.getTable().get(ticketId)) {
			ticket.setUserTicketNumber(DataImportVo.getTable().get(ticketId));
		}
		ticket.setState(state);
		if (ticket.getFailureTime() != null
				&& ticket.getFailureTime() < System.currentTimeMillis()) {
			ticket.setDescription("{msg:'请求超时'}");
			ticket.setState(TicketState.TIMEOUT);
		}
		if (vresult != null && vresult.getMapMessages().size() == 0) {
			ticket.setErrorMsgs(vresult.getMessages());
		}
		ticket.setCurrentRowCount(currentDealRow);
		ticket.setTotalRowCount(importDataNum);
		ticket.setNullRowNum(nullRowNum);
		ticket.setErrorCount(currentRow - firstDataRow);
		ticketService
				.updateTicket(ticket, TicketService.DEFAULT_EXPRIED_SECOND);
	}

	public void updateErrorTitleAndRowMsg(String ticketId, String title,
			int row, String msg, int currentDealRow, int importDataNum,
			int currentRow, int firstDataRow, int nullRowNum) {
		updateTicketMessage(ticketId, "{msg:'" + title + "'}");
		ValidateResult vResult = new ValidateResult();
		vResult.addErrorMessage(row, msg);
		updateTicketErrorMsg(ticketId, vResult, currentDealRow, importDataNum,
				currentRow, firstDataRow, nullRowNum, TicketState.EXCEPTIONSTOP);
	}

	private void updateTicketMessage(String ticketId, String description) {
		DataImportTicket ticket = (DataImportTicket) ticketService
				.getTicketById(ticketId);
		if (null != DataImportVo.getTable().get(ticketId)) {
			ticket.setUserTicketNumber(DataImportVo.getTable().get(ticketId));
		}
		ticket.setDescription(description);
		if (ticket.getFailureTime() != null
				&& ticket.getFailureTime() < System.currentTimeMillis()) {
			ticket.setDescription("{msg:'请求超时'}");
			ticket.setState(TicketState.TIMEOUT);
		}
		ticketService
				.updateTicket(ticket, TicketService.DEFAULT_EXPRIED_SECOND);
	}

	public void updateCompleteMsgTitle(String ticketId, String title,
			int currentDealRow, int importDataNum, int currentRow,
			int firstDataRow, int nullRowNum) {
		updateTicketInfo(ticketId, "{successMsg:'" + title + "'}",
				currentDealRow, importDataNum, currentRow, firstDataRow,
				nullRowNum, TicketState.DONE);
	}

	public void addLog(String uuid, String fileName, String fileType,
			Integer importDataNum, String importModuleName) {
		excelImportLogService.addImportLog(uuid, fileName, fileType,
				importDataNum, importModuleName);
	}

	public void updateLogNum(String uuid, Integer currentDealNum,
			Integer errorCountNum) {
		excelImportLogService.updateImportLogCurrentNum(uuid, currentDealNum,
				errorCountNum);
	}

	public void updateLogStatus(String uuid, Number status, String fileName) {
		excelImportLogService.updateImportStatus(uuid, status);
		sendPlatformMessageToUser(fileName);
	}

	private void sendPlatformMessageToUser(String fileName) {
		PlatformMessage platformMessage = new PlatformMessage();
		platformMessage.setSendType(PlatformMessageSendType.SYSTERM_SNED);
		platformMessage.setTitle(fileName + "文件导入完成");
		platformMessage.setContent(fileName + "文件导入完成,请在导入日志系统中查看详情。");
		platformMessage.setMessageType(PlatformMessageType.SYSTEM_MESSAGE);
		platformMessage.setReceiverId(ThreadVariable.getUser().getId());
		platformaMessageService.sendPlatformMessageToUser(platformMessage);
	}
}
