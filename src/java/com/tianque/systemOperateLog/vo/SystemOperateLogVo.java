package com.tianque.systemOperateLog.vo;

import java.util.Date;

import com.tianque.core.util.DateUtil;
import com.tianque.systemOperateLog.domain.SystemOperateLog;

public class SystemOperateLogVo extends SystemOperateLog {

	private Date startDate;

	private Date endDate;

	private String searchType;

	private String searchDataKeyword;

	private String searchBusinessType;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = DateUtil.getEndTime(endDate);
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchDataKeyword() {
		return searchDataKeyword;
	}

	public void setSearchDataKeyword(String searchDataKeyword) {
		this.searchDataKeyword = searchDataKeyword;
	}

	public String getSearchBusinessType() {
		return searchBusinessType;
	}

	public void setSearchBusinessType(String searchBusinessType) {
		this.searchBusinessType = searchBusinessType;
	}

}
