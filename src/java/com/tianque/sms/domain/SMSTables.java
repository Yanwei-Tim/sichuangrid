package com.tianque.sms.domain;

import java.util.Date;

public class SMSTables {
	private int id;
	private String tabName = "";
	private Long smsID_begin;
	private Long smsID_end;
	private int type;
	private int number;
	private Date createTime;
	private int status;
	private int isenddeal;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public Long getSmsID_begin() {
		return smsID_begin;
	}

	public void setSmsID_begin(Long smsIDBegin) {
		smsID_begin = smsIDBegin;
	}

	public Long getSmsID_end() {
		return smsID_end;
	}

	public void setSmsID_end(Long smsIDEnd) {
		smsID_end = smsIDEnd;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getIsenddeal() {
		return isenddeal;
	}

	public void setIsenddeal(int isenddeal) {
		this.isenddeal = isenddeal;
	}

}
