package com.tianque.plugin.orgchange.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.plugin.orgchange.common.OrgChangeUtils;

/**
 * 执行组织机构变更日志
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年9月23日
 */
public class OrgChangeLog extends BaseDomain {

	private Long orgChangeId;

	private String moduleName;

	private String ename;

	private String tableName;

	private Date startTime;

	private int operateType;

	private Date endTime;

	private int logType;

	private String description = "";

	public OrgChangeLog() {
	}

	public OrgChangeLog(OrgChangeInfo orgChangeInfo) {
		if (orgChangeInfo != null) {
			orgChangeId = orgChangeInfo.getId();
			moduleName = "组织机构";
			ename = "organizationManagement";
			tableName = "ORGANIZATIONS";
			startTime = new Date();
			operateType = orgChangeInfo.getOperateType();
			logType = OrgChangeUtils.LOG_INIT;
			description = "变更[" + orgChangeInfo.getSourceOrgName() + "]为["
					+ orgChangeInfo.getTargetOrgName() + "]";
		}
	}

	public OrgChangeLog(OrgMapping orgMapping) {
		if (orgMapping != null) {

			ModuleTable moduleTable = orgMapping.getModuleTable();
			if (moduleTable != null && moduleTable.getPermission() != null) {
				moduleName = moduleTable.getPermission().getCname();
				ename = moduleTable.getPermission().getEname();
				tableName = moduleTable.getTableName();
			} else {
				description = "moduleTable is null";
			}
			startTime = new Date();
			logType = OrgChangeUtils.LOG_INIT;

			OrgChangeInfo orgChangeInfo = orgMapping.getOrgChangeInfo();
			if (orgChangeInfo != null) {
				orgChangeId = orgChangeInfo.getId();
				operateType = orgChangeInfo.getOperateType();
				description = "表[" + tableName + "]变更["
						+ orgChangeInfo.getSourceOrgName() + "]至["
						+ orgChangeInfo.getTargetOrgName() + "]执行["
						+ orgMapping.getOldOrgId() + "]>["
						+ orgMapping.getNewOrgId() + "]["
						+ orgMapping.getOldOrgCode() + "]>["
						+ orgMapping.getNewOrgCode() + "]";
			} else {
				description = "orgChangeInfo is null";
			}

		}
	}

	public Long getOrgChangeId() {
		return orgChangeId;
	}

	public void setOrgChangeId(Long orgChangeId) {
		this.orgChangeId = orgChangeId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public int getOperateType() {
		return operateType;
	}

	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getLogType() {
		return logType;
	}

	public void setLogType(int logType) {
		// endTime = new Date();
		this.logType = logType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		// endTime = new Date();
		this.description = description;
	}

	public void setErrorDesc(String description) {
		endTime = new Date();
		this.logType = OrgChangeUtils.LOG_ERROR;
		this.description = description;
	}

	public void setSuccessDesc(String description) {
		endTime = new Date();
		this.logType = OrgChangeUtils.LOG_SUCCESS;
		this.description = description;
	}

	public void appendErrorDesc(String description) {
		endTime = new Date();
		this.logType = OrgChangeUtils.LOG_ERROR;
		this.description += description;
	}

	public void appendSuccessDesc(String description) {
		endTime = new Date();
		this.logType = OrgChangeUtils.LOG_SUCCESS;
		this.description += description;
	}

	@Override
	public String toString() {
		return "OrgChangeLog [orgChangeId=" + orgChangeId + ", moduleName="
				+ moduleName + ", ename=" + ename + ", tableName=" + tableName
				+ ", startTime=" + startTime + ", operateType=" + operateType
				+ ", endTime=" + endTime + ", logType=" + logType
				+ ", description=" + description + ", getId()=" + getId() + "]";
	}

}
