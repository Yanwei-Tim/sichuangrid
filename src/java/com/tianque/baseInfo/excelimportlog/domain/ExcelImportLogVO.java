package com.tianque.baseInfo.excelimportlog.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

public class ExcelImportLogVO extends BaseDomain {
	// 导入模块名称
	private String importModuleName;
	// 操作账号所属组织结构
	private Organization organizationId;

	private Date startTime;
	private Date endTime;
	// 操作人姓名
	private String operateName;
	// 有无错误分析表
	private Integer status;

	public String getImportModuleName() {
		return importModuleName;
	}

	public void setImportModuleName(String importModuleName) {
		this.importModuleName = importModuleName;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Organization getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Organization organizationId) {
		this.organizationId = organizationId;
	}
}
