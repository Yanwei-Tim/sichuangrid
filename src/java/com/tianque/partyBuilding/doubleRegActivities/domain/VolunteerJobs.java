package com.tianque.partyBuilding.doubleRegActivities.domain;

import org.apache.struts2.json.JSONUtil;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;

/**
 * 志愿者岗位表:实体类(VOLUNTEERJOBS)
 * 
 * @author
 * @date 2014-02-27 10:06:23
 */
public class VolunteerJobs extends com.tianque.core.base.BaseDomain implements
		java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 所属网格(ORGID) */
	private Organization organization;
	/** 拟认领数(CLAIMPLANS) */
	private Long claimPlans;
	/** 联系电话(TELEPHONE) */
	private String telephone;
	/** 所属网格编号(ORGINTERNALCODE) */
	private String orgInternalCode;
	/** 志愿者岗位(NAME) */
	private String name;
	/** 联系人(CONTRACTOR) */
	private String contractor;
	/** 备注(REMARK) */
	private String remark;
	/** 专长要求(CONTENT) */
	private String content;

	/** 已认领数 */
	private Integer hasClaimedAmount;

	public VolunteerJobs() {

	}

	public VolunteerJobs(Organization organization, Long claimPlans,
			String telephone, String orgInternalCode, String name,
			String contractor, String remark, String content) {
		this.organization = organization;
		this.claimPlans = claimPlans;
		this.telephone = telephone;
		this.orgInternalCode = orgInternalCode;
		this.name = name;
		this.contractor = contractor;
		this.remark = remark;
		this.content = content;
	}

	/** get 所属网格(orgId) */

	public Organization getOrganization() {
		return organization;
	}

	/** set 所属网格(ORGID) */

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/** get 拟认领数(claimPlans) */
	public Long getClaimPlans() {
		return claimPlans;
	}

	/** set 拟认领数(CLAIMPLANS) */
	public void setClaimPlans(Long claimPlans) {
		this.claimPlans = claimPlans;
	}

	/** get 联系电话(telephone) */
	public String getTelephone() {
		return telephone;
	}

	/** set 联系电话(TELEPHONE) */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/** get 所属网格编号(orgInternalCode) */
	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	/** set 所属网格编号(ORGINTERNALCODE) */
	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	/** get 志愿者岗位(name) */
	public String getName() {
		return name;
	}

	/** set 志愿者岗位(NAME) */
	public void setName(String name) {
		this.name = name;
	}

	/** get 联系人(contractor) */
	public String getContractor() {
		return contractor;
	}

	/** set 联系人(CONTRACTOR) */
	public void setContractor(String contractor) {
		this.contractor = contractor;
	}

	/** get 备注(remark) */
	public String getRemark() {
		return remark;
	}

	/** set 备注(REMARK) */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/** get 专长要求(content) */
	public String getContent() {
		return content;
	}

	/** set 专长要求(CONTENT) */
	public void setContent(String content) {
		this.content = content;
	}

	public Integer getHasClaimedAmount() {
		return hasClaimedAmount;
	}

	public void setHasClaimedAmount(Integer hasClaimedAmount) {
		this.hasClaimedAmount = hasClaimedAmount;
	}

	public String toString() {
		try {
			return JSONUtil.serialize(this);
		} catch (Exception e) {
			return super.toString();
		}
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(super.getId(),
				this.orgInternalCode, null);
	}
}
