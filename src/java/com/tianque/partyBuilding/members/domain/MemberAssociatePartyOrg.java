package com.tianque.partyBuilding.members.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * 成员党组织关联类
 * 
 * @author yulei
 * 
 */
public class MemberAssociatePartyOrg extends BaseDomain {

	/** 成员 */
	private Member member;
	/** 成员所属组织机构 */
	private Organization organization;
	/** 组织机构内置编码 */
	private String orgInternalCode;
	/** 成员所属党组织类别 */
	private Integer partyOrgType;
	/** 是否注销 */
	private Integer logOut;
	/** 所在党支部 */
	private String partyOrg;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

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

	public Integer getPartyOrgType() {
		return partyOrgType;
	}

	public void setPartyOrgType(Integer partyOrgType) {
		this.partyOrgType = partyOrgType;
	}

	public Integer getLogOut() {
		return logOut;
	}

	public void setLogOut(Integer logOut) {
		this.logOut = logOut;
	}

	public String getPartyOrg() {
		return partyOrg;
	}

	public void setPartyOrg(String partyOrg) {
		this.partyOrg = partyOrg;
	}
}
