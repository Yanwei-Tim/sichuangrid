package com.tianque.fourTeams.fourTeamsIssue.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

public class FourTeamsPacificUnionFounding extends BaseDomain {

	private Organization organization;
	private String orgInternalCode;
	private Integer year;
	private Integer january;
	private Integer february;
	private Integer march;
	private Integer april;
	private Integer may;
	private Integer june;
	private Integer july;
	private Integer august;
	private Integer september;
	private Integer october;
	private Integer november;
	private Integer december;

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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getJanuary() {
		return january == null ? 1 : january;
	}

	public void setJanuary(Integer january) {
		this.january = january;
	}

	public Integer getFebruary() {
		return february == null ? 1 : february;
	}

	public void setFebruary(Integer february) {
		this.february = february;
	}

	public Integer getMarch() {
		return march == null ? 1 : march;
	}

	public void setMarch(Integer march) {
		this.march = march;
	}

	public Integer getApril() {
		return april == null ? 1 : april;
	}

	public void setApril(Integer april) {
		this.april = april;
	}

	public Integer getMay() {
		return may == null ? 1 : may;
	}

	public void setMay(Integer may) {
		this.may = may;
	}

	public Integer getJune() {
		return june == null ? 1 : june;
	}

	public void setJune(Integer june) {
		this.june = june;
	}

	public Integer getJuly() {
		return july == null ? 1 : july;
	}

	public void setJuly(Integer july) {
		this.july = july;
	}

	public Integer getAugust() {
		return august == null ? 1 : august;
	}

	public void setAugust(Integer august) {
		this.august = august;
	}

	public Integer getSeptember() {
		return september == null ? 1 : september;
	}

	public void setSeptember(Integer september) {
		this.september = september;
	}

	public Integer getOctober() {
		return october == null ? 1 : october;
	}

	public void setOctober(Integer october) {
		this.october = october;
	}

	public Integer getNovember() {
		return november == null ? 1 : november;
	}

	public void setNovember(Integer november) {
		this.november = november;
	}

	public Integer getDecember() {
		return december == null ? 1 : december;
	}

	public void setDecember(Integer december) {
		this.december = december;
	}
}
