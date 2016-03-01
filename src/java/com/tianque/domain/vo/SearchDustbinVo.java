package com.tianque.domain.vo;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

public class SearchDustbinVo extends BaseDomain {
	private String name;
	/** 所属网格 */
	private Organization organization;
	/** 所属责任区编号 */
	private String orgInternalCode;
	/** 部件类型 */
	private Long type;
	/** 部件名称 */
	private Long partName;
	/** 部件标识码 */
	private String dustbinCode;
	/** 主管部门 */
	private String deptName;
	/** 权属单位 */
	private String ownershipUnitName;
	/** 养护单位 */
	private String maintenanceUnitName;
	/** 地址 */
	private String address;
	private Long isEmphasis;
	private String logOutReason;
	private String fastSearchKeyWords;

	/** 是否有视频流 */
	private Long hasVideo;

	/** 部件类型的查询参数 */
	private String typeName;
	/** 根据部件类型的查询参数 */
	private String partType;
	/** 根据部件类型的查询参数 */
	private String partTypeName;

	/** 部件类型的字典项id */
	private Long partTypeId;

	public String getPartType() {
		return partType;
	}

	public void setPartType(String partType) {
		this.partType = partType;
	}

	public String getPartTypeName() {
		return partTypeName;
	}

	public void setPartTypeName(String partTypeName) {
		this.partTypeName = partTypeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMaintenanceUnitName() {
		return maintenanceUnitName;
	}

	public void setMaintenanceUnitName(String maintenanceUnitName) {
		this.maintenanceUnitName = maintenanceUnitName;
	}

	public String getFastSearchKeyWords() {
		return fastSearchKeyWords;
	}

	public void setFastSearchKeyWords(String fastSearchKeyWords) {
		this.fastSearchKeyWords = fastSearchKeyWords;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Long getPartName() {
		return partName;
	}

	public void setPartName(Long partName) {
		this.partName = partName;
	}

	public String getDustbinCode() {
		return dustbinCode;
	}

	public void setDustbinCode(String dustbinCode) {
		this.dustbinCode = dustbinCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getOwnershipUnitName() {
		return ownershipUnitName;
	}

	public void setOwnershipUnitName(String ownershipUnitName) {
		this.ownershipUnitName = ownershipUnitName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Long getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Long isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	public String getLogOutReason() {
		return logOutReason;
	}

	public void setLogOutReason(String logOutReason) {
		this.logOutReason = logOutReason;
	}

	public Long getPartTypeId() {
		return partTypeId;
	}

	public void setPartTypeId(Long partTypeId) {
		this.partTypeId = partTypeId;
	}

	public Long getHasVideo() {
		return hasVideo;
	}

	public void setHasVideo(Long hasVideo) {
		this.hasVideo = hasVideo;
	}

}
