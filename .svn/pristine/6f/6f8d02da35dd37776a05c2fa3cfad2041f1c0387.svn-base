package com.tianque.plugin.orgchange.domain;

import java.util.Date;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 组织机构变更前后对应信息
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年9月23日
 */
public class OrgMapping implements Cloneable {

	private final static Logger logger = LoggerFactory
			.getLogger(OrgMapping.class);

	private OrgChangeInfo orgChangeInfo;

	private Long oldOrgId;

	private Long oldParentOrgId;

	private String oldOrgCode;

	private String oldOrgName;

	private Long newOrgId;

	private String newOrgCode;

	private Date createDate;

	private ModuleTable moduleTable;

	private OrgChangeLog orgChangeLog;

	private Long newParentOrgId;

	/** 组织机构部门编号前4位 用于人口分表 */
	private String deptNoTail;

	/** 迁移合并后所对应的县级的orgId */
	private Long newDistrictOrgId;
	/** 迁移合并前所对应的县级的orgId */
	private Long oldDistrictOrgId;

	private boolean hasData;
	/** 查询的处理过结果集 */
	private Set<String> queryResults;

	public OrgMapping() {
		createDate = new Date();
	}

	public OrgMapping(OrgChangeInfo orgChangeInfo) {
		this();
		this.orgChangeInfo = orgChangeInfo;
	}

	public OrgMapping(OrgChangeInfo orgChangeInfo, Long oldOrgId,
			String oldOrgCode, Long newOrgId, String newOrgCode) {
		this();
		this.orgChangeInfo = orgChangeInfo;
		this.oldOrgId = oldOrgId;
		this.oldOrgCode = oldOrgCode;
		this.newOrgId = newOrgId;
		this.newOrgCode = newOrgCode;
	}

	public boolean isOrgIdChange() {
		return oldOrgId != null && !oldOrgId.equals(newOrgId);
	}

	public Long getOldOrgId() {
		return oldOrgId;
	}

	public void setOldOrgId(Long oldOrgId) {
		this.oldOrgId = oldOrgId;
	}

	public String getOldOrgCode() {
		return oldOrgCode;
	}

	public void setOldOrgCode(String oldOrgCode) {
		this.oldOrgCode = oldOrgCode;
	}

	public Long getNewOrgId() {
		return newOrgId;
	}

	public void setNewOrgId(Long newOrgId) {
		this.newOrgId = newOrgId;
	}

	public String getNewOrgCode() {
		return newOrgCode;
	}

	public void setNewOrgCode(String newOrgCode) {
		this.newOrgCode = newOrgCode;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public OrgChangeInfo getOrgChangeInfo() {
		return orgChangeInfo;
	}

	public void setOrgChangeInfo(OrgChangeInfo orgChangeInfo) {
		this.orgChangeInfo = orgChangeInfo;
	}

	public ModuleTable getModuleTable() {
		return moduleTable;
	}

	public void setModuleTable(ModuleTable moduleTable) {
		this.moduleTable = moduleTable;
	}

	public OrgChangeLog getOrgChangeLog() {
		return orgChangeLog;
	}

	public void setOrgChangeLog(OrgChangeLog orgChangeLog) {
		this.orgChangeLog = orgChangeLog;
	}

	public boolean isHasData() {
		return hasData;
	}

	public void setHasData(boolean hasData) {
		this.hasData = hasData;
	}

	public Long getNewDistrictOrgId() {
		return newDistrictOrgId;
	}

	public void setNewDistrictOrgId(Long newDistrictOrgId) {
		this.newDistrictOrgId = newDistrictOrgId;
	}

	public Long getOldDistrictOrgId() {
		return oldDistrictOrgId;
	}

	public void setOldDistrictOrgId(Long oldDistrictOrgId) {
		this.oldDistrictOrgId = oldDistrictOrgId;
	}

	public Set<String> getQueryResults() {
		return queryResults;
	}

	public void setQueryResults(Set<String> queryResults) {
		this.queryResults = queryResults;
	}

	@Override
	public String toString() {
		return "OrgMapping [orgChangeInfo=" + orgChangeInfo + ", oldOrgId="
				+ oldOrgId + ", oldOrgCode=" + oldOrgCode + ", newOrgId="
				+ newOrgId + ", newOrgCode=" + newOrgCode + ", createDate="
				+ createDate + "]";
	}

	public Long getOldParentOrgId() {
		return oldParentOrgId;
	}

	public void setOldParentOrgId(Long oldParentOrgId) {
		this.oldParentOrgId = oldParentOrgId;
	}

	public String getOldOrgName() {
		return oldOrgName;
	}

	public void setOldOrgName(String oldOrgName) {
		this.oldOrgName = oldOrgName;
	}

	public String getDeptNoTail() {
		return deptNoTail;
	}

	public void setDeptNoTail(String deptNoTail) {
		this.deptNoTail = deptNoTail;
	}

	public Long getNewParentOrgId() {
		return newParentOrgId;
	}

	public void setNewParentOrgId(Long newParentOrgId) {
		this.newParentOrgId = newParentOrgId;
	}

	@Override
	public OrgMapping clone() {
		try {
			return (OrgMapping) super.clone();
		} catch (CloneNotSupportedException e) {
			logger.error("", e);
		}
		return this;
	}

}
