package com.tianque.baseInfo.unsettledPopulation.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.PropertyDict;

public class UnsettledPopulation extends ActualPopulation {

	public UnsettledPopulation() {
		setActualPopulationType(BaseInfoTables.UNSETTEDPOPULATION_KEY);
	}

	private String actualType;
	/**
	 * 持证种类
	 */
	private PropertyDict certificateType;
	/**
	 * 持证编号
	 */
	private String certificateNo;
	/**
	 * 未落户时间
	 */
	private Date unSettedTime;
	/**
	 * 未落户原因
	 */
	private PropertyDict unSettedReason;

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getUnSettedTime() {
		return unSettedTime;
	}

	public void setUnSettedTime(Date unSettedTime) {
		this.unSettedTime = unSettedTime;
	}

	public PropertyDict getUnSettedReason() {
		return unSettedReason;
	}

	public void setUnSettedReason(PropertyDict unSettedReason) {
		this.unSettedReason = unSettedReason;
	}

	public PropertyDict getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(PropertyDict certificateType) {
		this.certificateType = certificateType;
	}

	public String getActualType() {
		return actualType;
	}

	public void setActualType(String actualType) {
		this.actualType = actualType;
	}

}
