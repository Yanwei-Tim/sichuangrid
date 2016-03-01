package com.tianque.baseInfo.otherFacilities.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class OtherFacilities extends BaseDomain {

	/** 所属网格 */
	private Organization organization;
	/** 所属责任区编号 */
	private String orgInternalCode;
	/** 部件编号 */
	private String objNum;
	/** 部件名称 */
	private String objName;
	/** 部件类型 */
	private PropertyDict objType;
	/** 所在街道 */
	private String street;
	/** 所在社区 */
	private String community;
	/** 主管部门代码 */
	private String deptCode;
	/** 主管部门名称 */
	private String deptName;
	/** 权属单位代码 */
	private String ownershipUnitCode;
	/** 权属单位名称 */
	private String ownershipUnitName;
	/** 养护单位代码 */
	private String maintenanceUnitCode;
	/** 养护单位名称 */
	private String maintenanceUnitName;
	/** 状态 */
	private PropertyDict state;
	/** 初始时间 */
	private Date startDate;
	/** 变更时间 */
	private Date changeDate;
	/** 数据来源 */
	private String dateSource;
	/** 现场调查权属 */
	private String researchOwnership;
	/** 专业部门确认权属 */
	private String verifyOwnership;
	/** 案件资料确认权属 */
	private String caseMaterialOwnership;
	/** 其他来源权属 */
	private String otherSourceOwnership;
	/** 位置描述 */
	private String objPosition;
	/** 备注 */
	private String remark;

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

	public String getObjNum() {
		return objNum;
	}

	public void setObjNum(String objNum) {
		this.objNum = objNum;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public PropertyDict getObjType() {
		return objType;
	}

	public void setObjType(PropertyDict objType) {
		this.objType = objType;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getOwnershipUnitCode() {
		return ownershipUnitCode;
	}

	public void setOwnershipUnitCode(String ownershipUnitCode) {
		this.ownershipUnitCode = ownershipUnitCode;
	}

	public String getOwnershipUnitName() {
		return ownershipUnitName;
	}

	public void setOwnershipUnitName(String ownershipUnitName) {
		this.ownershipUnitName = ownershipUnitName;
	}

	public String getMaintenanceUnitCode() {
		return maintenanceUnitCode;
	}

	public void setMaintenanceUnitCode(String maintenanceUnitCode) {
		this.maintenanceUnitCode = maintenanceUnitCode;
	}

	public String getMaintenanceUnitName() {
		return maintenanceUnitName;
	}

	public void setMaintenanceUnitName(String maintenanceUnitName) {
		this.maintenanceUnitName = maintenanceUnitName;
	}

	public PropertyDict getState() {
		return state;
	}

	public void setState(PropertyDict state) {
		this.state = state;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public String getDateSource() {
		return dateSource;
	}

	public void setDateSource(String dateSource) {
		this.dateSource = dateSource;
	}

	public String getResearchOwnership() {
		return researchOwnership;
	}

	public void setResearchOwnership(String researchOwnership) {
		this.researchOwnership = researchOwnership;
	}

	public String getVerifyOwnership() {
		return verifyOwnership;
	}

	public void setVerifyOwnership(String verifyOwnership) {
		this.verifyOwnership = verifyOwnership;
	}

	public String getCaseMaterialOwnership() {
		return caseMaterialOwnership;
	}

	public void setCaseMaterialOwnership(String caseMaterialOwnership) {
		this.caseMaterialOwnership = caseMaterialOwnership;
	}

	public String getOtherSourceOwnership() {
		return otherSourceOwnership;
	}

	public void setOtherSourceOwnership(String otherSourceOwnership) {
		this.otherSourceOwnership = otherSourceOwnership;
	}

	public String getObjPosition() {
		return objPosition;
	}

	public void setObjPosition(String objPosition) {
		this.objPosition = objPosition;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
