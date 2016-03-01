package com.tianque.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

/**
 * 矛盾纠纷排查调处情况
 */
@SuppressWarnings("serial")
public class IssueInvestigationMediate extends BaseDomain {

	public static final String DEAL_ITEM = "调处数";
	public static final String INPUT_ITEM = "排查数";

	public static final String BELOW_FIFTY_PERSON = "50人以下";
	public static final String FIFTY_TO_ONEHUNDRED_PERSON = "50至100人";
	public static final String ONEHUNDRED_TO_FIVEHUNDRED_PERSON = "100至500人";
	public static final String OVER_FIVEHUNDRED_PERSON = "500人以上";

	public static final String IMPORTANT_ITEM = "重大矛盾纠纷";

	public static final String FIX_SUBSET = "起数";

	private IssueType issueType;

	// 矛盾纠纷排查调处情况父级类型
	private String typeName;
	// 矛盾纠纷排查调处情况子级类型
	private String typeSubsetName;
	// 矛盾纠纷排查调处市级记录
	private Long cityCount;
	// 矛盾纠纷排查调处县级记录
	private Long districtCount;
	// 矛盾纠纷排查调处镇级记录
	private Long townCount;
	// 矛盾纠纷排查调处村级记录
	private Long villageCount;
	// 矛盾纠纷排查调处小计
	private Long subtotal = 0L;
	// 矛盾纠纷排查调处年度累计
	private Long yearCumulative = 0L;
	// 矛盾纠纷排查调处统计开始时间
	private Date startDate;
	// 矛盾纠纷排查调处统计结束时间
	private Date endDate;
	// 矛盾纠纷排查调处统计年份
	private Integer year;
	// 矛盾纠纷排查调处统计月份
	private Integer month;
	// 矛盾纠纷排查调处统计第几周
	private Long week = 0L;
	// 矛盾纠纷排查调处统计第几季度
	private Long quarter = 0L;
	// 矛盾纠纷排查调处统计报表类型(年报，月报，周报)
	private String types;
	// 矛盾纠纷排查调处实际查询结束时间
	private Date searchEndDate;
	// 调查排查部门
	private Organization org;

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeSubsetName() {
		return typeSubsetName;
	}

	public void setTypeSubsetName(String typeSubsetName) {
		this.typeSubsetName = typeSubsetName;
	}

	public Long getCityCount() {
		return cityCount;
	}

	public void setCityCount(Long cityCount) {
		this.cityCount = cityCount;
	}

	public Long getDistrictCount() {
		return districtCount;
	}

	public void setDistrictCount(Long districtCount) {
		this.districtCount = districtCount;
	}

	public Long getTownCount() {
		return townCount;
	}

	public void setTownCount(Long townCount) {
		this.townCount = townCount;
	}

	public Long getVillageCount() {
		return villageCount;
	}

	public void setVillageCount(Long villageCount) {
		this.villageCount = villageCount;
	}

	public Long getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Long subtotal) {
		this.subtotal = subtotal;
	}

	public Long getYearCumulative() {
		return yearCumulative;
	}

	public void setYearCumulative(Long yearCumulative) {
		this.yearCumulative = yearCumulative;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public Long getWeek() {
		return week;
	}

	public void setWeek(Long week) {
		this.week = week;
	}

	public Long getQuarter() {
		return quarter;
	}

	public void setQuarter(Long quarter) {
		this.quarter = quarter;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getSearchEndDate() {
		return searchEndDate;
	}

	public void setSearchEndDate(Date searchEndDate) {
		this.searchEndDate = searchEndDate;
	}

	public IssueType getIssueType() {
		return issueType;
	}

	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}

}
