package com.tianque.plugin.statistics.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

public class GeneralSituation extends BaseDomain{

	private Integer year;//年
	private Integer month;//月
	private Organization organization;//组织机构信息
	private String basesicType;//主类别
	private String detailType;//子类别
	private Long subType;//详情类别
	private String typeName;
	private Integer monthCreateSign;//月签收
	private Integer policeStationSign;//精神病-派出所月签收
	private Integer clinicSign;//精神病-卫生所月签收
	private Integer allCountSign;//截止当前月总签收数
	private Integer monthCreateCount;//月上报数
	private Integer allCount;//截止当前月上报数
	
	public GeneralSituation(){
		
	}
	
	public GeneralSituation(Organization organization,String basesicType,String detailType){
		this.organization= organization;
		this.basesicType = detailType;
		this.detailType = detailType;
	}
	public GeneralSituation(Organization organization,Long subType){
		this.organization= organization;
		this.subType = subType;
	}
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public String getBasesicType() {
		return basesicType;
	}
	public void setBasesicType(String basesicType) {
		this.basesicType = basesicType;
	}
	public String getDetailType() {
		return detailType;
	}
	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}
	
	public Long getSubType() {
		return subType;
	}
	public void setSubType(Long subType) {
		this.subType = subType;
	}
	public Integer getMonthCreateSign() {
		return monthCreateSign==null?0:monthCreateSign;
	}
	public void setMonthCreateSign(Integer monthCreateSign) {
		this.monthCreateSign = monthCreateSign;
	}
	public Integer getPoliceStationSign() {
		return policeStationSign==null?0:policeStationSign;
	}
	public void setPoliceStationSign(Integer policeStationSign) {
		this.policeStationSign = policeStationSign;
	}
	public Integer getClinicSign() {
		return clinicSign==null?0:clinicSign;
	}
	public void setClinicSign(Integer clinicSign) {
		this.clinicSign = clinicSign;
	}
	public Integer getAllCountSign() {
		return allCountSign==null?0:allCountSign;
	}
	public void setAllCountSign(Integer allCountSign) {
		this.allCountSign = allCountSign;
	}
	public Integer getMonthCreateCount() {
		return monthCreateCount==null?0:monthCreateCount;
	}
	public void setMonthCreateCount(Integer monthCreateCount) {
		this.monthCreateCount = monthCreateCount;
	}
	public Integer getAllCount() {
		return allCount==null?0:allCount;
	}
	public void setAllCount(Integer allCount) {
		this.allCount = allCount;
	}
	
	
	
}
