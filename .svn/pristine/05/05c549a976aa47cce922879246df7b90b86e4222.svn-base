package com.tianque.aidsPopulations.domain;

import org.apache.struts2.json.JSONUtil;

import com.tianque.baseInfo.base.domain.AttentionPopulation;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.PropertyDict;

/**
 * :实体类(AIDSPOPULATIONS)
 * 
 * @author liaoshanshan
 * @date 2013-06-09 16:34:04
 */
public class Aidspopulations extends AttentionPopulation {
	private static final long serialVersionUID = 1L;
	/** 感染途径(INFECTWAY) */
	private PropertyDict infectway;
	/** 违法情况(violationsofthelaw) */
	private PropertyDict violationsofthelaw;
	/** 犯罪类型(CRIMETYPE) */
	private PropertyDict crimetype;
	/** 收治机构所属层级(RECEIVEDLEVEL) */
	private PropertyDict receivedlevel;
	/** 帮扶情况(HELPCIRCUMSTANCES) */
	private Long helpcircumstances;
	/** 地址编号(ADDRESSNO) */
	private String addressno;
	/** 收治机构(RECEIVEDORGANIZATION) */
	private String receivedorganization;

	public Aidspopulations() {
		setAttentionPopulationType(BaseInfoTables.AIDSPOPULATIONS_KEY);
	}

	/** get 感染途径(infectway) */
	public PropertyDict getInfectway() {
		return infectway;
	}

	/** set 感染途径(INFECTWAY) */
	public void setInfectway(PropertyDict infectway) {
		this.infectway = infectway;
	}

	public PropertyDict getViolationsofthelaw() {
		return violationsofthelaw;
	}

	public void setViolationsofthelaw(PropertyDict violationsofthelaw) {
		this.violationsofthelaw = violationsofthelaw;
	}

	/** get 犯罪类型(crimetype) */
	public PropertyDict getCrimetype() {
		return crimetype;
	}

	/** set 犯罪类型(CRIMETYPE) */
	public void setCrimetype(PropertyDict crimetype) {
		this.crimetype = crimetype;
	}

	/** get 收治机构所属层级(receivedlevel) */
	public PropertyDict getReceivedlevel() {
		return receivedlevel;
	}

	/** set 收治机构所属层级(RECEIVEDLEVEL) */
	public void setReceivedlevel(PropertyDict receivedlevel) {
		this.receivedlevel = receivedlevel;
	}

	/** get 帮扶情况(helpcircumstances) */
	public Long getHelpcircumstances() {
		return helpcircumstances;
	}

	/** set 帮扶情况(HELPCIRCUMSTANCES) */
	public void setHelpcircumstances(Long helpcircumstances) {
		this.helpcircumstances = helpcircumstances;
	}

	public String getAddressno() {
		return addressno;
	}

	public void setAddressno(String addressno) {
		this.addressno = addressno;
	}

	public String getReceivedorganization() {
		return receivedorganization;
	}

	public void setReceivedorganization(String receivedorganization) {
		this.receivedorganization = receivedorganization;
	}

	public String toString() {
		try {
			return JSONUtil.serialize(this);
		} catch (Exception e) {
			return super.toString();
		}
	}
}
