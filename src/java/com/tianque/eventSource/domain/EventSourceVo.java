package com.tianque.eventSource.domain;

import java.util.Date;

import com.tianque.domain.PropertyDict;

public class EventSourceVo {
	private Date startDate;
	private Date endDate;
	private String title;
	private PropertyDict sourceType;// 来源方式
	private PropertyDict state;// 是否转入事件
	private int dealState;
	private String sourcePeople;
	private String telephone;
	private String orgName;
	private String content;
	private String sortField = "createDate";
	private String order = "desc";

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
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public PropertyDict getSourceType() {
		return sourceType;
	}

	public void setSourceType(PropertyDict sourceType) {
		this.sourceType = sourceType;
	}

	public PropertyDict getState() {
		return state;
	}

	public void setState(PropertyDict state) {
		this.state = state;
	}

	public String getSourcePeople() {
		return sourcePeople;
	}

	public void setSourcePeople(String sourcePeople) {
		this.sourcePeople = sourcePeople;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getDealState() {
		return dealState;
	}

	public void setDealState(int dealState) {
		this.dealState = dealState;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
