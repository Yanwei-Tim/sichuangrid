package com.tianque.threeRecordsIssue.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.account.api.ThreeRecordsKeyGeneratorDubboService;
import com.tianque.controller.BaseFileUploadAction;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.datatransfer.util.CircularDoubleBufferedQueue;
import com.tianque.core.datatransfer.util.DataImportVo;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.ThreadVariable;
import com.tianque.datatransfer.ExcelWriter;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.vo.ReportInfoTableTypes;
import com.tianque.threeRecordsIssue.dataTrans.DataTransferConstants;
import com.tianque.threeRecordsIssue.dataTrans.ThreadPool;
import com.tianque.threeRecordsIssue.dataTrans.dataImport.ExcelDataImport;
import com.tianque.ticket.DataImportTicket;
import com.tianque.ticket.Ticket;
import com.tianque.ticket.TicketIdGenerator;
import com.tianque.ticket.service.TicketService;

@Transactional
@Namespace("/threeRecords/dataTransManage")
@Controller("dataTransController")
@Scope("request")
public class DataTransController extends BaseFileUploadAction implements
		ApplicationContextAware {

	private static Logger logger = LoggerFactory
			.getLogger(DataTransController.class);

	private static final long serialVersionUID = 1L;
	private String ticketId;
	private int dataFormat = DataTransferConstants.EXCEL_DATA_FORMAT;
	private String dataType;
	private int startRow;// Excel中要导入数据开始的行数
	private ApplicationContext appContext;
	private Long threadId;
	private String templates;
	private String dataTemplatesName;
	private String enterpriseType = "";
	private int importSuccessNum;// 导入成功的条数
	private int errorNum;// 导入失败的条数
	private int isNew;// 是否进入新的方法（含多线程）
	private CircularDoubleBufferedQueue queue = CircularDoubleBufferedQueue
			.getInstance();
	private int userTicketNumber;// 当前操作用户排队票号
	/**
	 * 导入功能预留标示符
	 */
	private String module;
	/**
	 * 是否是为乡镇级以上的领导视图提供的导入的
	 */
	private int isTownLeaderImport = 0;

	@Autowired
	private TicketService ticketService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private ThreeRecordsKeyGeneratorDubboService serialKeyGenerator;

	private Ticket generateDataImportTicket() {
		DataImportTicket dataImportTicket = new DataImportTicket();
		dataImportTicket.setTicketId(TicketIdGenerator.generateTicket());
		userTicketNumber = queue.getDefaultMaxCapacity()
				- queue.remainingCapacity();
		DataImportVo.getTable().put(dataImportTicket.getTicketId(),
				userTicketNumber);
		dataImportTicket.setUserTicketNumber(userTicketNumber);
		dataImportTicket.setCurrentRowCount(0);
		dataImportTicket.setTotalRowCount(1);
		dataImportTicket.setDescription(getStoredFilePath()
				+ getStoredFileName());
		return ticketService.addTicket(dataImportTicket, 0);
	}

	@Action("importToDomain")
	public void importToDomain() throws Exception {
		if (checkInCache()) {
			return;
		}
		proccessUploadFile();
		Ticket ticket = generateDataImportTicket();
		ticketId = ticket.getTicketId();
		String errorExcelName = UUID.randomUUID().toString();
		ExcelDataImport excelDataImport = new ExcelDataImport(ThreadVariable
				.getSession(), appContext, ticketId, createFileUrl(), dataType,
				startRow, templates, queue, errorExcelName,
				getUploadFileName(), module, isTownLeaderImport,
				serialKeyGenerator);
		ThreadPool.getInstance().execute(excelDataImport);
		threadId = excelDataImport.getId();

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=GBK");
		PrintWriter printWriter = response.getWriter();
		printWriter.print("{ticketId:'" + ticket.getTicketId()
				+ "',uploadFileName:'" + getUploadFileName() + "',threadId:'"
				+ threadId + "',userTicketNumber:'" + userTicketNumber
				+ "',errorMessageExcelName:'" + errorExcelName + "'}");

	}

	private boolean checkInCache() {
		if (getUpload() != null && getUpload().length() > 0) {
			String key = "updateFileLength" + getUpload().length() + "size";
			if (cacheService.get(key) != null) {
				return true;
			}
			cacheService.set(key, 30, getUpload().length());
			return false;
		}
		return true;
	}

	private String createFileUrl() {
		return FileUtil.getWebRoot() + File.separator
				+ getActualFileUrl(getStoredFileName());
	}

	@Action("threadStop")
	public void threadStop() throws Exception {
		ThreadPool.remove(threadId);
	}

	private String getActualFileUrl(String fileName) {

		return getStoredFilePath() + "/" + fileName;
	}

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.appContext = context;
	}

	// 下载错误文件
	@Action("downloadErrorDataTemplate")
	public void downloadErrorDataTemplate() throws Exception {
		if (dataTemplatesName == null) {
			this.errorMessage = "请点击要下载的文件!";
			return;
		}
		try {
			File file = new File(FileUtil.getWebRoot() + File.separator
					+ GridProperties.UPLOAD_FILE_ERRORMESSAGE + File.separator
					+ dataTemplatesName + ".zip");
			if (!file.exists()) {
				inputStream = new java.io.FileInputStream(FileUtil.getWebRoot()
						+ File.separator
						+ GridProperties.UPLOAD_FILE_ERRORMESSAGE
						+ File.separator + dataTemplatesName + ".xls");
				downloadFileName = new String(((String) BaseInfoTables
						.getTypeDisplayNames(dataType) + "错误分析登记表")
						.getBytes("gbk"), "ISO8859-1")
						+ ".xls";
			} else {
				inputStream = new java.io.FileInputStream(FileUtil.getWebRoot()
						+ File.separator
						+ GridProperties.UPLOAD_FILE_ERRORMESSAGE
						+ File.separator + dataTemplatesName + ".zip");
				downloadFileName = new String(((String) BaseInfoTables
						.getTypeDisplayNames(dataType) + "错误分析登记表")
						.getBytes("gbk"), "ISO8859-1")
						+ ".zip";
			}

			OutputStream outputStream = ServletActionContext.getResponse()
					.getOutputStream();
			ServletActionContext.getResponse().setContentType(
					"application/vnd.ms-excel;charset=GBK");
			ServletActionContext.getResponse().addHeader("Content-Disposition",
					"attachment; filename=" + downloadFileName);
			int read;
			while ((read = inputStream.read()) != -1) {
				outputStream.write(read);
			}
		} catch (FileNotFoundException e) {
			errorMessage = "文件不存在";
			throw new BusinessValidationException("文件打开失败!");
		} catch (UnsupportedEncodingException uee) {
			errorMessage = "文件下载错误";
			throw new BusinessValidationException("文件打开失败!");
		} catch (Exception e) {
			errorMessage = "文件下载错误";
			logger.error("文件下载错误", e);
		}
	}

	@Action("downloadDataTemplate")
	public void downloadDataTemplate() throws Exception {
		if (dataTemplatesName == null) {
			this.errorMessage = "请点击要下载的文件!";
			return;
		}
		try {
			inputStream = new java.io.FileInputStream(FileUtil.getWebRoot()
					+ File.separator + dataTemplatesName);
			if (dataTemplatesName.lastIndexOf("/") != -1)
				dataTemplatesName = dataTemplatesName.substring(
						dataTemplatesName.lastIndexOf("/") + 1,
						dataTemplatesName.length());
			String extendname = "";
			if (dataTemplatesName.indexOf(".") != -1) {
				extendname = dataTemplatesName.substring(dataTemplatesName
						.lastIndexOf("."), dataTemplatesName.length());
				dataTemplatesName = dataTemplatesName.substring(0,
						dataTemplatesName.lastIndexOf("."));
			}

			downloadFileName = new String((dataTemplatesName + "登记表")
					.getBytes("gbk"), "ISO8859-1")
					+ extendname;

			OutputStream outputStream = ServletActionContext.getResponse()
					.getOutputStream();
			ServletActionContext.getResponse().setContentType(
					"application/vnd.ms-excel;charset=GBK");
			ServletActionContext.getResponse().addHeader("Content-Disposition",
					"attachment; filename=" + downloadFileName);
			int read;
			while ((read = inputStream.read()) != -1) {
				outputStream.write(read);
			}

		} catch (FileNotFoundException e) {
			throw new BusinessValidationException("文件打开失败!");
		} catch (UnsupportedEncodingException uee) {
			throw new BusinessValidationException("文件打开失败!");
		} catch (Exception e) {
			logger.error("模版下载错误", e);
		}
	}

	@Action("downloadReportTemplate")
	public String downloadReportTemplate() throws Exception {
		if (dataTemplatesName == null) {
			this.errorMessage = "请点击要下载的文件!";
			return ERROR;
		}
		try {
			inputStream = new java.io.FileInputStream(FileUtil.getWebRoot()
					+ File.separator + dataTemplatesName);
			if (dataTemplatesName.lastIndexOf("/") != -1)
				dataTemplatesName = dataTemplatesName.substring(
						dataTemplatesName.lastIndexOf("/") + 1,
						dataTemplatesName.length());
			String extendname = "";
			if (dataTemplatesName.indexOf(".") != -1) {
				extendname = dataTemplatesName.substring(dataTemplatesName
						.lastIndexOf("."), dataTemplatesName.length());
				dataTemplatesName = dataTemplatesName.substring(0,
						dataTemplatesName.lastIndexOf("."));
			}

			downloadFileName = new String(((String) ReportInfoTableTypes
					.getReportDisplayNames(dataTemplatesName) + "填报表")
					.getBytes("gbk"), "ISO8859-1")
					+ extendname;
		} catch (FileNotFoundException e) {
			throw new BusinessValidationException("文件打开失败!");
		} catch (UnsupportedEncodingException uee) {
			throw new BusinessValidationException("文件打开失败!");
		}
		return SUCCESS;
	}

	@Action("downloadDataZipTemplate")
	public void downloadDataZipTemplate() throws Exception {
		if (dataTemplatesName == null) {
			this.errorMessage = "请点击要下载的文件!";
			return;
		}
		try {

			ExcelWriter writer = new ExcelWriter();
			inputStream = new java.io.FileInputStream(FileUtil.getWebRoot()
					+ File.separator + writer.getStoreFilePath()
					+ File.separator + dataTemplatesName);
			if (dataTemplatesName.lastIndexOf("/") != -1)
				dataTemplatesName = dataTemplatesName.substring(
						dataTemplatesName.lastIndexOf("/") + 1,
						dataTemplatesName.length());
			String extendname = "";
			if (dataTemplatesName.indexOf(".") != -1) {
				extendname = dataTemplatesName.substring(dataTemplatesName
						.lastIndexOf("."), dataTemplatesName.length());
				dataTemplatesName = dataTemplatesName.substring(0,
						dataTemplatesName.lastIndexOf("."));
			}

			downloadFileName = new String(((String) templates + "登记表")
					.getBytes("gbk"), "ISO8859-1")
					+ extendname;

			OutputStream outputStream = ServletActionContext.getResponse()
					.getOutputStream();
			ServletActionContext.getResponse().setContentType(
					"application/vnd.ms-excel;charset=GBK");
			ServletActionContext.getResponse().addHeader("Content-Disposition",
					"attachment; filename=" + downloadFileName);
			int read;
			while ((read = inputStream.read()) != -1) {
				outputStream.write(read);
			}

		} catch (FileNotFoundException e) {
			throw new BusinessValidationException("文件打开失败!");
		} catch (UnsupportedEncodingException uee) {
			throw new BusinessValidationException("文件打开失败!");
		} catch (Exception e) {
			logger.error("模版下载错误", e);
		}
	}

	public int getDataFormat() {
		return dataFormat;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public void setDataFormat(int dataType) {
		this.dataFormat = dataType;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getTemplates() {
		return templates;
	}

	public void setTemplates(String templates) {
		this.templates = templates;
	}

	public String getDataTemplatesName() {
		return dataTemplatesName;
	}

	public void setDataTemplatesName(String dataTemplatesName) {
		this.dataTemplatesName = dataTemplatesName;
	}

	public String getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	public Long getThreadId() {
		return threadId;
	}

	public void setThreadId(Long threadId) {
		this.threadId = threadId;
	}

	public int getImportSuccessNum() {
		return importSuccessNum;
	}

	public void setImportSuccessNum(int importSuccessNum) {
		this.importSuccessNum = importSuccessNum;
	}

	public int getErrorNum() {
		return errorNum;
	}

	public void setErrorNum(int errorNum) {
		this.errorNum = errorNum;
	}

	public int getIsNew() {
		return isNew;
	}

	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}

	public int getUserTicketNumber() {
		return userTicketNumber;
	}

	public void setUserTicketNumber(int userTicketNumber) {
		this.userTicketNumber = userTicketNumber;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public int getIsTownLeaderImport() {
		return isTownLeaderImport;
	}

	public void setIsTownLeaderImport(int isTownLeaderImport) {
		this.isTownLeaderImport = isTownLeaderImport;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
}
