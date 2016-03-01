package com.tianque.domain.vo;

import com.tianque.domain.Organization;

public class EvaluateStatisticVo {

	private Organization organization;
	private Long fraction;

	public Long getFraction() {
		return fraction;
	}

	public void setFraction(Long fraction) {
		this.fraction = fraction;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
