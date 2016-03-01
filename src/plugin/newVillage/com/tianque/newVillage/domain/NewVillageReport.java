package com.tianque.newVillage.domain;

import java.util.List;

import com.tianque.domain.Organization;

public class NewVillageReport {

	private Organization organization;
	private List<ReportDataSummary> list;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<ReportDataSummary> getList() {
		return list;
	}

	public void setList(List<ReportDataSummary> list) {
		this.list = list;
	}

}
