package com.tianque.partyBuilding.doubleRegActivities.domain;

import org.apache.struts2.json.JSONUtil;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;

/**
 * 服务项目表:实体类(SERVICEPROJECT)
 * 
 * @author
 * @date 2014-02-26 10:02:47
 */
public class ServiceProject extends com.tianque.core.base.BaseDomain implements
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
	/** 项目名称(PROJECTNAME) */
	private String projectName;
	/** 联系人(CONTRACTOR) */
	private String contractor;
	/** 备注(REMARK) */
	private String remark;
	/** 项目内容(PROJECTCONTENT) */
	private String projectContent;

	/** 已认领数 */
	private Integer hasClaimedAmount;

	public ServiceProject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceProject(Organization organization, Long claimPlans,
			String telephone, String orgInternalCode, String projectName,
			String contractor, String remark, String projectContent) {
		super();
		this.organization = organization;
		this.claimPlans = claimPlans;
		this.telephone = telephone;
		this.orgInternalCode = orgInternalCode;
		this.projectName = projectName;
		this.contractor = contractor;
		this.remark = remark;
		this.projectContent = projectContent;
	}

	public Organization getOrganization() {
		return organization;
	}

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

	/** get 项目名称(projectName) */
	public String getProjectName() {
		return projectName;
	}

	/** set 项目名称(PROJECTNAME) */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	/** get 项目内容(projectContent) */
	public String getProjectContent() {
		return projectContent;
	}

	/** set 项目内容(PROJECTCONTENT) */
	public void setProjectContent(String projectContent) {
		this.projectContent = projectContent;
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
