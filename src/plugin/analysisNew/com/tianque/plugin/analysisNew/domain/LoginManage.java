package com.tianque.plugin.analysisNew.domain;

import java.io.Serializable;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

public class LoginManage extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	private Organization organization;
	private String orgInternalCode;
	private String orgName;

	/**
	 * 总账号数
	 */
	private Integer allLoginCount;
	/**
	 * 1-3月未登录数
	 */
	private Integer threeMonthsLoginCount;
	/**
	 * 3个月以上未登录
	 */
	private Integer outThreeMonthsLoginCount;
	/**
	 * 从未登录
	 */
	private Integer nerverLoginCount;

	/**
	 * 年
	 */
	private Integer year;
	/**
	 * 月
	 */
	private Integer month;
	private Integer state;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getAllLoginCount() {
		return allLoginCount;
	}

	public void setAllLoginCount(Integer allLoginCount) {
		this.allLoginCount = allLoginCount;
	}

	public Integer getThreeMonthsLoginCount() {
		return threeMonthsLoginCount;
	}

	public void setThreeMonthsLoginCount(Integer threeMonthsLoginCount) {
		this.threeMonthsLoginCount = threeMonthsLoginCount;
	}

	public Integer getOutThreeMonthsLoginCount() {
		return outThreeMonthsLoginCount;
	}

	public void setOutThreeMonthsLoginCount(Integer outThreeMonthsLoginCount) {
		this.outThreeMonthsLoginCount = outThreeMonthsLoginCount;
	}

	public Integer getNerverLoginCount() {
		return nerverLoginCount;
	}

	public void setNerverLoginCount(Integer nerverLoginCount) {
		this.nerverLoginCount = nerverLoginCount;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
