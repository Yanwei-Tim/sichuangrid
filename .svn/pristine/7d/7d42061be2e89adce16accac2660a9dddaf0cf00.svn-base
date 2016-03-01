package com.tianque.baseInfo.druggy.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.baseInfo.base.domain.AttentionPopulation;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.PropertyDict;

/**
 * 吸毒人员
 */
@SuppressWarnings("serial")
public class Druggy extends AttentionPopulation {

	public Druggy() {
		setAttentionPopulationType(BaseInfoTables.DRUGGY_KEY);
	}

	/** 吸毒原因 */
	private PropertyDict drugReason;
	/** 毒品来源 */
	private PropertyDict drugSource;
	/** 戒毒情况 */
	private PropertyDict detoxicateCase;
	/** 吸毒状态 */
	private PropertyDict detoxicateCondition;

	/** 是否服美沙酮戒毒 */
	private boolean isAdanon;
	/** 管控现状 */
	private String controlActuality;
	/** 滥用毒品种类 */
	private String drugType;

	/** 查获日期 */
	private Date ferretOutDate;
	private Date drugFristDate;

	/** 目前是否在接受治疗 */
	private boolean isUndergoTreatment;
	
	/***
	 * 用于手机接口返回走访数量
	 */
	private Long interviewCount;
	/**临时代替身份证的字段**/
	private String demoIdCardNo;


	public boolean isUndergoTreatment() {
		return isUndergoTreatment;
	}

	public void setUndergoTreatment(boolean isUndergoTreatment) {
		this.isUndergoTreatment = isUndergoTreatment;
	}

	public PropertyDict getDetoxicateCondition() {
		return detoxicateCondition;
	}

	public void setDetoxicateCondition(PropertyDict detoxicateCondition) {
		this.detoxicateCondition = detoxicateCondition;
	}

	public PropertyDict getDrugReason() {
		return drugReason;
	}

	public void setDrugReason(PropertyDict drugReason) {
		this.drugReason = drugReason;
	}

	public PropertyDict getDrugSource() {
		return drugSource;
	}

	public void setDrugSource(PropertyDict drugSource) {
		this.drugSource = drugSource;
	}

	public PropertyDict getDetoxicateCase() {
		return detoxicateCase;
	}

	public void setDetoxicateCase(PropertyDict detoxicateCase) {
		this.detoxicateCase = detoxicateCase;
	}

	public boolean isAdanon() {
		return isAdanon;
	}

	public void setAdanon(boolean isAdanon) {
		this.isAdanon = isAdanon;
	}

	public String getControlActuality() {
		return controlActuality;
	}

	public void setControlActuality(String controlActuality) {
		this.controlActuality = controlActuality;
	}

	public String getDrugType() {
		return drugType;
	}

	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getFerretOutDate() {
		return ferretOutDate;
	}

	public void setFerretOutDate(Date ferretOutDate) {
		this.ferretOutDate = ferretOutDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getDrugFristDate() {
		return drugFristDate;
	}

	public void setDrugFristDate(Date drugFristDate) {
		this.drugFristDate = drugFristDate;
	}

	public Long getInterviewCount() {
		return interviewCount;
	}

	public void setInterviewCount(Long interviewCount) {
		this.interviewCount = interviewCount;
	}

	public String getDemoIdCardNo() {
		return demoIdCardNo;
	}

	public void setDemoIdCardNo(String demoIdCardNo) {
		this.demoIdCardNo = demoIdCardNo;
	}
	
	
}
