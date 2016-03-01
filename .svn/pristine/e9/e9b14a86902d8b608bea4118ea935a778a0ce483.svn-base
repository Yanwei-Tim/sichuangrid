package com.tianque.baseInfo.floatingPopulation.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.PropertyDict;

/**
 * 流动人口
 * 
 * @author tqkaifa
 */
public class FloatingPopulation extends ActualPopulation {

	public FloatingPopulation() {
		setActualPopulationType(BaseInfoTables.FLOATINGPOPULATION_KEY);
	}

	/**
	 * 实口类型
	 */
	private String populationtypes;
	private String actualtype;
	/**
	 * 是否流入
	 */
	private Boolean isInflowing;
	/**
	 * 流入原因
	 */
	private PropertyDict inflowingReason;
	/**
	 * 流入时间
	 */
	private Date inflowingDate;
	/**
	 * 流入来源省
	 */
	private String inflowingProvince;
	/**
	 * 流入来源市
	 */
	private String inflowingCity;
	/**
	 * 流入来源县
	 */
	private String inflowingDistrict;
	/**
	 * 登记证情况
	 */
	private PropertyDict registrationType;
	/**
	 * 登记日期
	 */
	private Date registerDate;
	/**
	 * 预计到期日期
	 */
	private Date expectedDatedue;
	// 西宁
	/**
	 * 证件编号
	 */
	private String certificateNumber;
	/**
	 * 暂住处所
	 */
	private PropertyDict stayLocationType;
	/**
	 * 经济来源
	 */
	private PropertyDict economySource;
	/**
	 * 居住时限
	 */
	private PropertyDict stayTimeLimit;
	/**
	 * 预居住时限
	 */
	private PropertyDict preparedStayTimeLimit;
	/**
	 * 是否有婚育证明
	 */
	private Boolean hasMarriedProve;
	/** 转为流动人口的时间 */
	private String settleTime;
	/** 户口类别 */
	private PropertyDict residenceType;

	public PropertyDict getInflowingReason() {
		return inflowingReason;
	}

	public void setInflowingReason(PropertyDict inflowingReason) {
		this.inflowingReason = inflowingReason;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getInflowingDate() {
		return inflowingDate;
	}

	public void setInflowingDate(Date inflowingDate) {
		this.inflowingDate = inflowingDate;
	}

	public String getInflowingProvince() {
		return inflowingProvince;
	}

	public void setInflowingProvince(String inflowingProvince) {
		this.inflowingProvince = inflowingProvince;
	}

	public String getInflowingCity() {
		return inflowingCity;
	}

	public void setInflowingCity(String inflowingCity) {
		this.inflowingCity = inflowingCity;
	}

	public String getInflowingDistrict() {
		return inflowingDistrict;
	}

	public void setInflowingDistrict(String inflowingDistrict) {
		this.inflowingDistrict = inflowingDistrict;
	}

	public Boolean getIsInflowing() {
		return isInflowing;
	}

	public void setIsInflowing(Boolean isInflowing) {
		this.isInflowing = isInflowing;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getExpectedDatedue() {
		return expectedDatedue;
	}

	public void setExpectedDatedue(Date expectedDatedue) {
		this.expectedDatedue = expectedDatedue;
	}

	public PropertyDict getRegistrationType() {
		return registrationType;
	}

	public void setRegistrationType(PropertyDict registrationType) {
		this.registrationType = registrationType;
	}

	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public PropertyDict getStayLocationType() {
		return stayLocationType;
	}

	public void setStayLocationType(PropertyDict stayLocationType) {
		this.stayLocationType = stayLocationType;
	}

	public PropertyDict getEconomySource() {
		return economySource;
	}

	public void setEconomySource(PropertyDict economySource) {
		this.economySource = economySource;
	}

	public PropertyDict getStayTimeLimit() {
		return stayTimeLimit;
	}

	public void setStayTimeLimit(PropertyDict stayTimeLimit) {
		this.stayTimeLimit = stayTimeLimit;
	}

	public PropertyDict getPreparedStayTimeLimit() {
		return preparedStayTimeLimit;
	}

	public void setPreparedStayTimeLimit(PropertyDict preparedStayTimeLimit) {
		this.preparedStayTimeLimit = preparedStayTimeLimit;
	}

	public Boolean getHasMarriedProve() {
		return hasMarriedProve;
	}

	public void setHasMarriedProve(Boolean hasMarriedProve) {
		this.hasMarriedProve = hasMarriedProve;
	}

	public String getActualtype() {
		return actualtype;
	}

	public void setActualtype(String actualtype) {
		this.actualtype = actualtype;
	}

	public String getPopulationtypes() {
		return populationtypes;
	}

	public void setPopulationtypes(String populationtypes) {
		this.populationtypes = populationtypes;
	}

	public String getSettleTime() {
		return settleTime;
	}

	public void setSettleTime(String settleTime) {
		this.settleTime = settleTime;
	}

	public String getDetailInflowingAddress() {
		return new StringBuffer(null != getInflowingProvince() ? getInflowingProvince() : "")
				.append(null != getInflowingCity() ? getInflowingCity() : "")
				.append(null != getInflowingDistrict() ? getInflowingDistrict() : "").toString();
	}

	public PropertyDict getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(PropertyDict residenceType) {
		this.residenceType = residenceType;
	}
	
	
}
