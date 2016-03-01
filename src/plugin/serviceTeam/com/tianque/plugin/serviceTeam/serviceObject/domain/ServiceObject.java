package com.tianque.plugin.serviceTeam.serviceObject.domain;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.domain.PropertyDict;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecord;
import com.tianque.plugin.serviceTeam.serviceTeamManage.domain.ServiceTeam;
import com.tianque.plugin.serviceTeam.serviceTeamMember.domain.ServiceTeamMember;

public class ServiceObject extends Countrymen {

	// 人员类型
	private String populationType;
	// 人员Id
	private Long populationId;
	// 服务对象性别
	private PropertyDict gender;
	// 人员身份证
	private String idCardNumber;
	// 服务组织Id
	private Long serviceTeamId;
	// 服务记录Id
	private Long serviceRecordId;
	// 服务者Id
	private Long serviceTeamMemberId;
	// 自治组织
	private List<ServiceTeam> serviceTeams;
	// 自治组织成员
	private List<ServiceTeamMember> serviceMembers;
	// 服务记录
	private List<ServiceRecord> serviceRecords;
	// 最新服务记录时间
	private Date lastestServiceRecordDate;

	public ServiceObject() {
		super();
	}

	public ServiceObject(String populationType, Long populationId, Long serviceTeamId) {
		super();
		this.populationType = populationType;
		this.populationId = populationId;
		this.serviceTeamId = serviceTeamId;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public Long getPopulationId() {
		return populationId;
	}

	public void setPopulationId(Long populationId) {
		this.populationId = populationId;
	}

	public Long getServiceTeamId() {
		return serviceTeamId;
	}

	public void setServiceTeamId(Long serviceTeamId) {
		this.serviceTeamId = serviceTeamId;
	}

	public Long getServiceRecordId() {
		return serviceRecordId;
	}

	public void setServiceRecordId(Long serviceRecordId) {
		this.serviceRecordId = serviceRecordId;
	}

	public Long getServiceTeamMemberId() {
		return serviceTeamMemberId;
	}

	public void setServiceTeamMemberId(Long serviceTeamMemberId) {
		this.serviceTeamMemberId = serviceTeamMemberId;
	}

	public List<ServiceTeam> getServiceTeams() {
		return serviceTeams;
	}

	public void setServiceTeams(List<ServiceTeam> serviceTeams) {
		this.serviceTeams = serviceTeams;
	}

	public List<ServiceTeamMember> getServiceMembers() {
		return serviceMembers;
	}

	public void setServiceMembers(List<ServiceTeamMember> serviceMembers) {
		this.serviceMembers = serviceMembers;
	}

	public PropertyDict getGender() {
		return gender;
	}

	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}

	public String getIdCardNumber() {
		return idCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	public List<ServiceRecord> getServiceRecords() {
		return serviceRecords;
	}

	public void setServiceRecords(List<ServiceRecord> serviceRecords) {
		this.serviceRecords = serviceRecords;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getLastestServiceRecordDate() {
		return lastestServiceRecordDate;
	}

	public void setLastestServiceRecordDate(Date lastestServiceRecordDate) {
		this.lastestServiceRecordDate = lastestServiceRecordDate;
	}

}
