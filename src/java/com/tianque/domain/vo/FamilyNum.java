package com.tianque.domain.vo;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

public class FamilyNum extends BaseDomain {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 所属网格
	 */
	private Organization organization;
	/**
	 * 所属责任区编号
	 */
	private String orgInternalCode;
	/**
	 * 常住家庭户口数量
	 */
	private Long familycountNum;

	/**
	 * 常住家庭户籍数量
	 */
	private Long inhabitantcountNum;

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

	public Long getFamilycountNum() {
		return familycountNum;
	}

	public void setFamilycountNum(Long familycountNum) {
		this.familycountNum = familycountNum;
	}

	public Long getInhabitantcountNum() {
		return inhabitantcountNum;
	}

	public void setInhabitantcountNum(Long inhabitantcountNum) {
		this.inhabitantcountNum = inhabitantcountNum;
	}

}
