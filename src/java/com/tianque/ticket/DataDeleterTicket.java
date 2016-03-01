package com.tianque.ticket;

import java.util.ArrayList;
import java.util.List;

public class DataDeleterTicket extends AbstractTicket {
	public static final int COMPLETE = 1;
	public static final int VALIDATION = 2;
	public static final int ERROR = 4;

	private int totalRowCount;// 数据总行数

	private int currentRowCount;// 当前处理的行数

	private String description;
	private List<String> errorMsgs = new ArrayList<String>();
	private List<String> typeMsgs = new ArrayList<String>();
	/**
	 * 消息提示的类型 正确 或者失败 0 or 1
	 */
	private int type;

	public List<String> getErrorMsgs() {
		return errorMsgs;
	}

	public void setErrorMsgs(List<String> errorMsgs) {
		this.errorMsgs = errorMsgs;
	}

	public List<String> getTypeMsgs() {
		return typeMsgs;
	}

	public void setTypeMsgs(List<String> typeMsgs) {
		this.typeMsgs = typeMsgs;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public void appendTypeErrorMsgs(List<String> typeslist) {
		if (getTypeMsgs() == null) {
			typeMsgs = new ArrayList<String>();
		}
		getTypeMsgs().addAll(typeslist);
	}

	public void appendTypeErrorMsg(String types) {
		if (getTypeMsgs() == null) {
			typeMsgs = new ArrayList<String>();
		}
		getTypeMsgs().add(types);
	}
}
