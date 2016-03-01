package com.tianque.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

public class HelpPrecord extends BaseDomain {

	private Date helpTime;
	private String address;
	private String events;
	private Long personnelId;
	private String personnelType;
	private String helpPersonnelName;
	private String personNmae;
	private String personTypeName;

	public String getPersonNmae() {
		return personNmae;
	}

	public void setPersonNmae(String personNmae) {
		this.personNmae = personNmae;
	}

	public String getPersonTypeName() {
		return personTypeName;
	}

	public void setPersonTypeName(String personTypeName) {
		this.personTypeName = personTypeName;
	}

	public String getAddress() {
		return address;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getHelpTime() {
		return helpTime;
	}

	public void setHelpTime(Date helpTime) {
		this.helpTime = helpTime;
	}

	public Long getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(Long personnelId) {
		this.personnelId = personnelId;
	}

	public String getPersonnelType() {
		return personnelType;
	}

	public void setPersonnelType(String personnelType) {
		this.personnelType = personnelType;
	}

	public String getHelpPersonnelName() {
		return helpPersonnelName;
	}

	public void setHelpPersonnelName(String helpPersonnelName) {
		this.helpPersonnelName = helpPersonnelName.replaceAll(" ", "");
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEvents() {
		return events;
	}

	public void setEvents(String events) {
		this.events = events;
	}

}
