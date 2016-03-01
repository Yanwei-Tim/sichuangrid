package com.tianque.domain.vo;

import java.util.List;

import com.tianque.domain.Organization;

public class MentalPatientVo {
	private Organization org;
	private Long amount; // 总数
	private List<MentalPatientDetailCounts> mentalPatientDetailCounts;

	public List<MentalPatientDetailCounts> getMentalPatientDetailCounts() {
		return mentalPatientDetailCounts;
	}

	public void setMentalPatientDetailCounts(
			List<MentalPatientDetailCounts> mentalPatientDetailCounts) {
		this.mentalPatientDetailCounts = mentalPatientDetailCounts;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
}
