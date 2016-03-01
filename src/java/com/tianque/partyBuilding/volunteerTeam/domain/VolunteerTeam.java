package com.tianque.partyBuilding.volunteerTeam.domain;

import org.apache.struts2.json.JSONUtil;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * 党员志愿者队伍表:实体类(VOLUNTEER_TEAM)
 * 
 * @author
 * @date 2014-02-11 15:27:43
 */
public class VolunteerTeam extends com.tianque.core.base.BaseDomain implements
		java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 所属网格(ORGID) */
	private Long orgId;
	/** 所属网格编号(ORGINTERNALCODE) */
	private String orgInternalCode;
	private Organization organization;
	/** 所属部门(BELONGORG) */
	private PropertyDict belongOrg;
	/** 组织名称(NAME) */
	private String name;
	/** 服务方向(SERVICEDIRECTION) */
	private String serviceDirection;
	/** 备注(REMARK) */
	private String remark;

	private Integer memberNum;

	public VolunteerTeam() {

	}

	public VolunteerTeam(Long orgId, String orgInternalCode, String name,
			String serviceDirection, String remark) {
		this.orgId = orgId;
		this.orgInternalCode = orgInternalCode;
		this.name = name;
		this.serviceDirection = serviceDirection;
		this.remark = remark;
	}

	public Integer getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(Integer memberNum) {
		this.memberNum = memberNum;
	}

	/** get 所属网格(orgId) */
	public Long getOrgId() {
		return orgId;
	}

	/** set 所属网格(ORGID) */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public PropertyDict getBelongOrg() {
		return belongOrg;
	}

	public void setBelongOrg(PropertyDict belongOrg) {
		this.belongOrg = belongOrg;
	}

	/** get 所属网格编号(orgInternalCode) */
	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	/** set 所属网格编号(ORGINTERNALCODE) */
	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	/** get 组织名称(name) */
	public String getName() {
		return name;
	}

	/** set 组织名称(NAME) */
	public void setName(String name) {
		this.name = name;
	}

	/** get 服务方向(serviceDirection) */
	public String getServiceDirection() {
		return serviceDirection;
	}

	/** set 服务方向(SERVICEDIRECTION) */
	public void setServiceDirection(String serviceDirection) {
		this.serviceDirection = serviceDirection;
	}

	/** get 备注(remark) */
	public String getRemark() {
		return remark;
	}

	/** set 备注(REMARK) */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
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
