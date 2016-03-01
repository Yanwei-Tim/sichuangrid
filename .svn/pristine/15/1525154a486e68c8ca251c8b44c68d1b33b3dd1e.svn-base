package com.tianque.ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataImportTicket extends AbstractTicket {
	public static final int COMPLETE = 1;
	public static final int VALIDATION = 2;
	public static final int ERROR = 4;

	private int totalRowCount;

	private int currentRowCount;// 当前处理行数

	private int errorCount;// 数据有错的行数

	private int nullRowNum;// 空行的数量

	private String description;

	private int importStatus = 0;

	private int userTicketNumber = 0;// 当前操作用户排队票号

	private List<String> errorMsgs = new ArrayList<String>();
	protected Map<String, String> mapMessages = new HashMap<String, String>();

	public int getImportStatus() {
		return importStatus;
	}

	public void setImportStatus(int importStatus) {
		this.importStatus = importStatus;
	}

	public int getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

	public int getCurrentRowCount() {
		return currentRowCount;
	}

	public void setCurrentRowCount(int currentRowCount) {
		this.currentRowCount = currentRowCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void appendErrorMsgs(List<String> errors) {
		if (getErrorMsgs() == null) {
			errorMsgs = new ArrayList<String>();
		}
		getErrorMsgs().addAll(errors);
	}

	public void appendErrorMsg(String error) {
		if (getErrorMsgs() == null) {
			errorMsgs = new ArrayList<String>();
		}
		getErrorMsgs().add(error);
	}

	public List<String> getErrorMsgs() {
		return errorMsgs;
	}

	public void setErrorMsgs(List<String> errorMsgs) {
		this.errorMsgs = errorMsgs;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	public int getNullRowNum() {
		return nullRowNum;
	}

	public void setNullRowNum(int nullRowNum) {
		this.nullRowNum = nullRowNum;
	}

	public int getUserTicketNumber() {
		return userTicketNumber;
	}

	public void setUserTicketNumber(int userTicketNumber) {
		this.userTicketNumber = userTicketNumber;
	}

}
