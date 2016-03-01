package com.tianque.controller.vo;

import java.util.List;

public class FullReportVo {

	public String bigTitle;
	public String[] columnCaption;
	public List objectDataList;

	public String getBigTitle() {
		return bigTitle;
	}

	public void setBigTitle(String bigTitle) {
		this.bigTitle = bigTitle;
	}

	public String[] getColumnCaption() {
		return columnCaption;
	}

	public void setColumnCaption(String[] columnCaption) {
		this.columnCaption = columnCaption;
	}

	public List getObjectDataList() {
		return objectDataList;
	}

	public void setObjectDataList(List objectDataList) {
		this.objectDataList = objectDataList;
	}

}