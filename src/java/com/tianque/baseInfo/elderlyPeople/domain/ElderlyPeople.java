package com.tianque.baseInfo.elderlyPeople.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.baseInfo.base.domain.AttentionPopulation;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.NumbericUtil;
import com.tianque.domain.PropertyDict;

/**
 * 老年人
 */
public class ElderlyPeople extends AttentionPopulation {

	private String elderPeopleId;// 老年人证号
	private PropertyDict peopleType;// 人员类型
	private PropertyDict currentStatus; // 目前情况
	private PropertyDict liveStatus; // 居住情况
	private PropertyDict spouseStatus; // 配偶情况
	private PropertyDict incomeSource; // 经济来源
	private Date enterWorkDate; // 参加工作日期
	private String retireUnitAndDuty; // 离退休单位
	private Date retireDate; // 离退休日期
	private String zhiwu;// 离退休单位的职务
	private Double pension; // 退休金

	public ElderlyPeople() {
		setAttentionPopulationType(BaseInfoTables.ELDERLYPEOPLE_KEY);
	}

	public String getElderPeopleId() {
		return elderPeopleId;
	}

	public void setElderPeopleId(String elderPeopleId) {
		this.elderPeopleId = elderPeopleId;
	}

	public PropertyDict getPeopleType() {
		return peopleType;
	}

	public void setPeopleType(PropertyDict peopleType) {
		this.peopleType = peopleType;
	}

	public PropertyDict getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(PropertyDict currentStatus) {
		this.currentStatus = currentStatus;
	}

	public PropertyDict getLiveStatus() {
		return liveStatus;
	}

	public void setLiveStatus(PropertyDict liveStatus) {
		this.liveStatus = liveStatus;
	}

	public PropertyDict getSpouseStatus() {
		return spouseStatus;
	}

	public void setSpouseStatus(PropertyDict spouseStatus) {
		this.spouseStatus = spouseStatus;
	}

	public PropertyDict getIncomeSource() {
		return incomeSource;
	}

	public void setIncomeSource(PropertyDict incomeSource) {
		this.incomeSource = incomeSource;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getEnterWorkDate() {
		return enterWorkDate;
	}

	public void setEnterWorkDate(Date enterWorkDate) {
		this.enterWorkDate = enterWorkDate;
	}

	public String getRetireUnitAndDuty() {
		return retireUnitAndDuty;
	}

	public void setRetireUnitAndDuty(String retireUnitAndDuty) {
		this.retireUnitAndDuty = retireUnitAndDuty;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getRetireDate() {
		return retireDate;
	}

	public void setRetireDate(Date retireDate) {
		this.retireDate = retireDate;
	}

	public String getZhiwu() {
		return zhiwu;
	}

	public void setZhiwu(String zhiwu) {
		this.zhiwu = zhiwu;
	}

	public Double getPension() {
		return pension;
	}

	public void setPension(Double pension) {
		this.pension = pension;
	}

	public String getPensionStringValue() {
		if (getPension() == null) {
			return null;
		}
		String value = NumbericUtil.toString(getPension(), 2);
		if (value.substring(value.length() - 3).equals(".00")) {
			return value.substring(0, value.length() - 3);
		} else if (value.substring(value.length() - 1).equals("0")) {
			return value.substring(0, value.length() - 1);
		}
		return value;
	}

}