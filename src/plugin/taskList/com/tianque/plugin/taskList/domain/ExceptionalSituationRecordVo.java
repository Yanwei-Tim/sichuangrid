package com.tianque.plugin.taskList.domain;

import java.util.Date;

import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * 异常情况记录查询vo
 * @author lanhaifeng
 *
 */
public class ExceptionalSituationRecordVo extends ExceptionalSituationRecord {
	/** 组织层级 **/
	private Organization organization;
	/** 地点 **/
	private String address;
	/** 快速查询关键字  **/
	private String fastSearchCondition;
	/** 用户所在层级id  **/
	private Long userOrgId;
	/** 记录时间  **/
	private Date recordStartDate;
	private Date recordEndDate;
	/** 签收人姓名 **/
	private String signMemberName;
	/** 签收时间 **/
	private Date signStartDate;
	private Date signEndDate;
	/** 是否签收 **/
	private Long status;
	/** 异常类型 **/
	private PropertyDict exceptionSituation;

	private String mode;

	private Long funOrgId;

	/**是否有回复*/
	private Integer hasReplay;
	/**有无异常*/
	private Integer hasException;

	public Integer getHasException() {
		return hasException;
	}

	public void setHasException(Integer hasException) {
		this.hasException = hasException;
	}

	public Integer getHasReplay() {
		return hasReplay;
	}

	public void setHasReplay(Integer hasReplay) {
		this.hasReplay = hasReplay;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFastSearchCondition() {
		return fastSearchCondition;
	}

	public void setFastSearchCondition(String fastSearchCondition) {
		this.fastSearchCondition = fastSearchCondition;
	}

	public Long getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}

	public Date getRecordStartDate() {
		return recordStartDate;
	}

	public void setRecordStartDate(Date recordStartDate) {
		this.recordStartDate = recordStartDate;
	}

	public Date getRecordEndDate() {
		return recordEndDate;
	}

	public void setRecordEndDate(Date recordEndDate) {
		this.recordEndDate = recordEndDate;
	}

	public String getSignMemberName() {
		return signMemberName;
	}

	public void setSignMemberName(String signMemberName) {
		this.signMemberName = signMemberName;
	}

	public Date getSignStartDate() {
		return signStartDate;
	}

	public void setSignStartDate(Date signStartDate) {
		this.signStartDate = signStartDate;
	}

	public Date getSignEndDate() {
		return signEndDate;
	}

	public void setSignEndDate(Date signEndDate) {
		this.signEndDate = signEndDate;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public PropertyDict getExceptionSituation() {
		return exceptionSituation;
	}

	public void setExceptionSituation(PropertyDict exceptionSituation) {
		this.exceptionSituation = exceptionSituation;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Long getFunOrgId() {
		return funOrgId;
	}

	public void setFunOrgId(Long funOrgId) {
		this.funOrgId = funOrgId;
	}

}