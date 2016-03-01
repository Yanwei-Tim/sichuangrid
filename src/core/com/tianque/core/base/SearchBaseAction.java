package com.tianque.core.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.tianque.datatransfer.DataExportHelper;
import com.tianque.datatransfer.newExcelExport.NewExcelDataExportThread;
import com.tianque.exception.base.OperationFailedException;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.ticket.DataImportTicket;
import com.tianque.ticket.Ticket;
import com.tianque.ticket.TicketIdGenerator;
import com.tianque.ticket.service.TicketService;

public abstract class SearchBaseAction extends BaseAction implements
		ApplicationContextAware {

	private ApplicationContext appContext;

	@Autowired
	private TicketService ticketService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	public Ticket generateDataExportTicket() {
		DataImportTicket dataImportTicket = new DataImportTicket();
		dataImportTicket.setTicketId(TicketIdGenerator.generateTicket());
		dataImportTicket.setCurrentRowCount(0);
		dataImportTicket.setTotalRowCount(1);
		return ticketService.addTicket(dataImportTicket, 0);
	}

	public DataExportHelper getConstructDataExportHelper() {
		DataExportHelper helper = new DataExportHelper();
		helper.setOrganizationService(organizationDubboService);
		helper.setPropertyDictService(propertyDictService);
		helper.setContext(appContext);
		return helper;
	}

	public void exportDataAll(Integer count, String[][] excelDefines,
			String excelName) {
		Integer pageRows = NewExcelDataExportThread.pageRows;
		int pageCount = (int) ((count - 1) / pageRows + 1);
		String fileName = UUID.randomUUID().toString();
		Ticket ticket = generateDataExportTicket();
		String ticketId = ticket.getTicketId();
		rows = pageRows;
		sord = "asc";
		NewExcelDataExportThread newExportThread = new NewExcelDataExportThread(
				this, ticketId, pageCount, excelDefines,
				getConstructDataExportHelper(), fileName);
		for (int j = 1; j <= pageCount; j++) {
			newExportThread.allotTask(j);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=GBK");
		try {
			PrintWriter printWriter = response.getWriter();

			printWriter.print("{ticketId:'" + ticket.getTicketId()
					+ "',uploadFileName:'" + excelName + "',threadId:'"
					+ Thread.currentThread().getId()
					+ "',errorMessageExcelName:'" + fileName + ".zip" + "'}");
		} catch (IOException e) {
			throw new OperationFailedException("导出数据异常", e);
		}
	}

	public List getNeedExportDatas(int page) {
		return null;
	}

	public ApplicationContext getAppContext() {
		return appContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.appContext = context;
	}

}
