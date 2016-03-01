package com.tianque.systemOperateLog.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;

public class SystemOperateLog extends BaseDomain {
	private static final long serialVersionUID = 1L;
	// 操作时间
	private Date operateDate;
	// 操作人
	private String operatePerson;
	// 模块类型
	private String moduleType;
	// 模块类型
	private String businessType;
	// 操作类型
	private Integer operateType;
	// 数据关键字
	private String dataKeyword;
	// 数据所属网格
	private Organization dataOrgId;
	private String dataOrgCode;
	// 数据操作前所属网格
	private Organization dataBeforeOrgId;
	// 数据操作之前
	private String dataBeforeOperate;
	// 数据操作之后
	private String dataAfterOperate;
	// 具体操作
	private String operateDescribe;
	// 数据操作前后对比状态
	private Integer contrastState;
	// 数据名（人的名字）
	private String dataName;
	// 数据操作源（在哪个模块被操作）
	private String operateSource;
	// 数据操作源（在哪个模块被操作）
	private Long dataId;

	private java.sql.Date operateDateHbase;

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public String getOperatePerson() {
		return operatePerson;
	}

	public void setOperatePerson(String operatePerson) {
		this.operatePerson = operatePerson;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

	public String getDataKeyword() {
		return dataKeyword;
	}

	public void setDataKeyword(String dataKeyword) {
		this.dataKeyword = dataKeyword;
	}

	public Organization getDataOrgId() {
		return dataOrgId;
	}

	public void setDataOrgId(Organization dataOrgId) {
		this.dataOrgId = dataOrgId;
	}

	public String getDataOrgCode() {
		return dataOrgCode;
	}

	public void setDataOrgCode(String dataOrgCode) {
		this.dataOrgCode = dataOrgCode;
	}

	public Organization getDataBeforeOrgId() {
		return dataBeforeOrgId;
	}

	public void setDataBeforeOrgId(Organization dataBeforeOrgId) {
		this.dataBeforeOrgId = dataBeforeOrgId;
	}

	public String getDataBeforeOperate() {
		return dataBeforeOperate;
	}

	public void setDataBeforeOperate(String dataBeforeOperate) {
		this.dataBeforeOperate = dataBeforeOperate;
	}

	public String getDataAfterOperate() {
		return dataAfterOperate;
	}

	public void setDataAfterOperate(String dataAfterOperate) {
		this.dataAfterOperate = dataAfterOperate;
	}

	public String getOperateDescribe() {
		return operateDescribe;
	}

	public void setOperateDescribe(String operateDescribe) {
		this.operateDescribe = operateDescribe;
	}

	public Integer getContrastState() {
		return contrastState;
	}

	public void setContrastState(Integer contrastState) {
		this.contrastState = contrastState;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getOperateSource() {
		return operateSource;
	}

	public void setOperateSource(String operateSource) {
		this.operateSource = operateSource;
	}

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public java.sql.Date getOperateDateHbase() {
		return operateDateHbase;
	}

	public void setOperateDateHbase(java.sql.Date operateDateHbase) {
		this.operateDateHbase = operateDateHbase;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(super.getId(), null,
				null);
	}

}
