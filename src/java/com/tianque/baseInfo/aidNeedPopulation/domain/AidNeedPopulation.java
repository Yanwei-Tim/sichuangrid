package com.tianque.baseInfo.aidNeedPopulation.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.baseInfo.base.domain.AttentionPopulation;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.PropertyDict;

public class AidNeedPopulation extends AttentionPopulation {

	public AidNeedPopulation() {
		setAttentionPopulationType(BaseInfoTables.AIDNEEDPOPULATION_KEY);
	}

	/**
	 * 救助原因
	 */
	private PropertyDict aidReason;
	/**
	 * 是否低保户
	 */
	private Boolean isLowHouseholds = false;
	/**
	 * 困难证号
	 */
	private String difficultCardNo;
	/**
	 * 困难类型
	 */
	private PropertyDict difficultType;
	/**
	 * 领取金额
	 */
	private String subsidiesAmount;
	/**
	 * 领取时间
	 */
	private Date subsidiesGetTime;
	/**
	 * 享受起始时间
	 */
	private Date subsidiesStartTime;
	/**
	 * 享受人数
	 */
	private String subsidiesPopulation;
	/**
	 * 享受地区
	 */
	private String subsidiesArea;

	public PropertyDict getAidReason() {
		return aidReason;
	}

	public void setAidReason(PropertyDict aidReason) {
		this.aidReason = aidReason;
	}

	public Boolean getIsLowHouseholds() {
		return isLowHouseholds;
	}

	public void setIsLowHouseholds(Boolean isLowHouseholds) {
		this.isLowHouseholds = isLowHouseholds;
	}

	public String getDifficultCardNo() {
		return difficultCardNo;
	}

	public void setDifficultCardNo(String difficultCardNo) {
		this.difficultCardNo = difficultCardNo;
	}

	public PropertyDict getDifficultType() {
		return difficultType;
	}

	public void setDifficultType(PropertyDict difficultType) {
		this.difficultType = difficultType;
	}

	public String getSubsidiesAmount() {
		return subsidiesAmount;
	}

	public void setSubsidiesAmount(String subsidiesAmount) {
		this.subsidiesAmount = subsidiesAmount;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getSubsidiesGetTime() {
		return subsidiesGetTime;
	}

	public void setSubsidiesGetTime(Date subsidiesGetTime) {
		this.subsidiesGetTime = subsidiesGetTime;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getSubsidiesStartTime() {
		return subsidiesStartTime;
	}

	public void setSubsidiesStartTime(Date subsidiesStartTime) {
		this.subsidiesStartTime = subsidiesStartTime;
	}

	public String getSubsidiesPopulation() {
		return subsidiesPopulation;
	}

	public void setSubsidiesPopulation(String subsidiesPopulation) {
		this.subsidiesPopulation = subsidiesPopulation;
	}

	public String getSubsidiesArea() {
		return subsidiesArea;
	}

	public void setSubsidiesArea(String subsidiesArea) {
		this.subsidiesArea = subsidiesArea;
	}

}
