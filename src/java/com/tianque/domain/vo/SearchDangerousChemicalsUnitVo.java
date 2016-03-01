package com.tianque.domain.vo;

import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.Organization;

public class SearchDangerousChemicalsUnitVo extends BaseDomain {

	/** 所属网格 */
	private Organization organization;
	/** 所属责任区编号 */
	private String orgInternalCode;
	/** 单位名称 */
	private String unitName;
	/** 单位地址 */
	private String unitAddress;
	/** 负责人 */
	private String superintendent;
	/** 联系电话 */
	private String telephone;
	/** 单位类别 */
	private String unitType;
	/** 货物类别 */
	private String commodityType;
	/** 副本许可范围 */
	private String copyScope;
	/** 存储设备 */
	private String storageDevice;
	/** 注销原因 */
	private String logOutReason;
	/** 注销起始时间 */
	private Date startLogOutTime;
	/** 注销结束时间 */
	private Date endLogOutTime;
	/** 备注 */
	private String remark;
	/** 全拼 */
	private String fullPinyin;
	/** 简拼 */
	private String simplePinyin;
	/**
	 * 是否注销,true表示是
	 */
	private Boolean isEmphasis;
	/**
	 * 关注程度
	 */
	private Long attentionExtentId;
	// 是否有治安负责人
	private Long hasServiceTeamMember;
	// 是否有巡场记录
	private Long hasServiceRecord;

	private String importantLocationType;

	public SearchDangerousChemicalsUnitVo() {
		setImportantLocationType(BaseInfoTables.DANGEROUSCHEMICALSUNIT_KEY);
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

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitAddress() {
		return unitAddress;
	}

	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}

	public String getSuperintendent() {
		return superintendent;
	}

	public void setSuperintendent(String superintendent) {
		this.superintendent = superintendent;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getCommodityType() {
		return commodityType;
	}

	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
	}

	public String getCopyScope() {
		return copyScope;
	}

	public void setCopyScope(String copyScope) {
		this.copyScope = copyScope;
	}

	public String getStorageDevice() {
		return storageDevice;
	}

	public void setStorageDevice(String storageDevice) {
		this.storageDevice = storageDevice;
	}

	public String getLogOutReason() {
		return logOutReason;
	}

	public void setLogOutReason(String logOutReason) {
		this.logOutReason = logOutReason;
	}

	public Date getStartLogOutTime() {
		return startLogOutTime;
	}

	public void setStartLogOutTime(Date startLogOutTime) {
		this.startLogOutTime = startLogOutTime;
	}

	public Date getEndLogOutTime() {
		return endLogOutTime;
	}

	public void setEndLogOutTime(Date endLogOutTime) {
		this.endLogOutTime = endLogOutTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		this.simplePinyin = simplePinyin;
	}

	public Boolean getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Boolean isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	public Long getAttentionExtentId() {
		return attentionExtentId;
	}

	public void setAttentionExtentId(Long attentionExtentId) {
		this.attentionExtentId = attentionExtentId;
	}

	public Long getHasServiceTeamMember() {
		return hasServiceTeamMember;
	}

	public void setHasServiceTeamMember(Long hasServiceTeamMember) {
		this.hasServiceTeamMember = hasServiceTeamMember;
	}

	public Long getHasServiceRecord() {
		return hasServiceRecord;
	}

	public void setHasServiceRecord(Long hasServiceRecord) {
		this.hasServiceRecord = hasServiceRecord;
	}

	public String getImportantLocationType() {
		return importantLocationType;
	}

	public void setImportantLocationType(String importantLocationType) {
		this.importantLocationType = importantLocationType;
	}

}
