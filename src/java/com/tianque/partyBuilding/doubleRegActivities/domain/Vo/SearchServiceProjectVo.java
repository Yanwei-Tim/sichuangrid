package com.tianque.partyBuilding.doubleRegActivities.domain.Vo;

/**
 * 服务项目表:SrarchVO(SERVICEPROJECT)
 * 
 * @author
 * @date 2014-02-26 10:02:48
 */
public class SearchServiceProjectVo extends com.tianque.core.base.BaseDomain {

	/** 所属网格 */
	private Long orgId;
	/** 拟认领数 */
	private Long claimPlans;
	/** 联系电话 */
	private String telephone;
	/** 所属网格编号 */
	private String orgInternalCode;
	/** 项目名称 */
	private String projectName;
	/** 联系人 */
	private String contractor;
	/** 备注 */
	private String remark;
	/** 项目内容 */
	private String projectContent;

	/** get 所属网格 */
	public Long getOrgId() {
		return orgId;
	}

	/** set 所属网格 */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	/** get 拟认领数 */
	public Long getClaimPlans() {
		return claimPlans;
	}

	/** set 拟认领数 */
	public void setClaimPlans(Long claimPlans) {
		this.claimPlans = claimPlans;
	}

	/** get 联系电话 */
	public String getTelephone() {
		return telephone;
	}

	/** set 联系电话 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/** get 所属网格编号 */
	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	/** set 所属网格编号 */
	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	/** get 项目名称 */
	public String getProjectName() {
		return projectName;
	}

	/** set 项目名称 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/** get 联系人 */
	public String getContractor() {
		return contractor;
	}

	/** set 联系人 */
	public void setContractor(String contractor) {
		this.contractor = contractor;
	}

	/** get 备注 */
	public String getRemark() {
		return remark;
	}

	/** set 备注 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/** get 项目内容 */
	public String getProjectContent() {
		return projectContent;
	}

	/** set 项目内容 */
	public void setProjectContent(String projectContent) {
		this.projectContent = projectContent;
	}

}
