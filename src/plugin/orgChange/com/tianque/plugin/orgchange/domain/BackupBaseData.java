package com.tianque.plugin.orgchange.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

public class BackupBaseData extends BaseDomain {

	private Long dataId;// 被删除或修改的数据ID
	private OrgChangeInfo orgChangeInfo;// 迁移合并信息ID
	private String tableName;// 涉及表名
	private Organization organization;// 影响数据所在素质机构
	private String expansionData;// 扩展

	public String getExpansionData() {
		return expansionData;
	}

	public void setExpansionData(String expansionData) {
		this.expansionData = expansionData;
	}

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public OrgChangeInfo getOrgChangeInfo() {
		return orgChangeInfo;
	}

	public void setOrgChangeInfo(OrgChangeInfo orgChangeInfo) {
		this.orgChangeInfo = orgChangeInfo;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
