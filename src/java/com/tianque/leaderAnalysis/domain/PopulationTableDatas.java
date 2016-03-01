package com.tianque.leaderAnalysis.domain;

import java.io.Serializable;
import java.util.List;

import com.tianque.domain.Organization;

public class PopulationTableDatas implements Serializable {
	private Organization organization;
	private Long orgId;
	private String orgName;
	private int sum;
	private List<PopulationDetails> populationList;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public List<PopulationDetails> getPopulationList() {
		return populationList;
	}

	public void setPopulationList(List<PopulationDetails> populationList) {
		this.populationList = populationList;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
