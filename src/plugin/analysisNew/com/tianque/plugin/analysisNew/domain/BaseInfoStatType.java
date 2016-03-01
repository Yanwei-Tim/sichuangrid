package com.tianque.plugin.analysisNew.domain;

import java.util.Date;

import com.tianque.core.base.BaseDomain;

public class BaseInfoStatType extends BaseDomain {
	private Integer year;
	private Integer month;
	private Long total;
	private String typeName;
	private String baseinfoType;
	private String orgInternalCode;
	private Date startDate;
	private Date endDate;
	private String percentage;
	private int isHelp;
	private int noHelp;
	private int resited;
	private int recidivism;
	private int sum;
	private int objectsum;
	private int monthcreate;

	public int getIsHelp() {
		return isHelp;
	}

	public void setIsHelp(int isHelp) {
		this.isHelp = isHelp;
	}

	public int getNoHelp() {
		return noHelp;
	}

	public void setNoHelp(int noHelp) {
		this.noHelp = noHelp;
	}

	public int getResited() {
		return resited;
	}

	public void setResited(int resited) {
		this.resited = resited;
	}

	public int getRecidivism() {
		return recidivism;
	}

	public void setRecidivism(int recidivism) {
		this.recidivism = recidivism;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
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

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getBaseinfoType() {
		return baseinfoType;
	}

	public void setBaseinfoType(String baseinfoType) {
		this.baseinfoType = baseinfoType;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getObjectsum() {
		return objectsum;
	}

	public void setObjectsum(int objectsum) {
		this.objectsum = objectsum;
	}

	public int getMonthcreate() {
		return monthcreate;
	}

	public void setMonthcreate(int monthcreate) {
		this.monthcreate = monthcreate;
	}

}
