package com.tianque.baseInfo.scenicManage.scenicEquipment.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class ScenicEquipment extends BaseDomain {

	/** 所属网格 */
	private Organization organization;
	/** 所属责任区编号 */
	private String orgInternalCode;
	/** 类型 */
	private PropertyDict equipType;
	/** 名称 */
	private String equipName;
	/** 全拼 */
	private String fullPinyin;
	/** 简拼 */
	private String simplePinyin;
	/*** 地址 */
	private String equipAddress;
	/** 负责人 */
	private String manager;
	/** 联系电话 */
	private String mobile;
	private String managerMobile;
	/** 图片url */
	private String imgUrl;
	/** 备注 */
	private String remark;
	private String aroundScenic;
	private String logOutReason;
	private Date logOutTime;
	private Boolean isEmphasis;

	/** 周边景点的id */
	private String aroundScenicIds;

	public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		if (fullPinyin != null && fullPinyin.length() > 30) {
			fullPinyin = fullPinyin.substring(0, 30);
		}
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		if (simplePinyin != null && simplePinyin.length() > 10) {
			simplePinyin = simplePinyin.substring(0, 10);
		}
		this.simplePinyin = simplePinyin;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public PropertyDict getEquipType() {
		return equipType;
	}

	public void setEquipType(PropertyDict equipType) {
		this.equipType = equipType;
	}

	public String getEquipName() {
		return equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}

	public String getEquipAddress() {
		return equipAddress;
	}

	public void setEquipAddress(String equipAddress) {
		this.equipAddress = equipAddress;
	}

	public String getManagerMobile() {
		return managerMobile;
	}

	public void setManagerMobile(String managerMobile) {
		this.managerMobile = managerMobile;
	}

	public String getAroundScenic() {
		return aroundScenic;
	}

	public void setAroundScenic(String aroundScenic) {
		this.aroundScenic = aroundScenic;
	}

	public String getLogOutReason() {
		return logOutReason;
	}

	public void setLogOutReason(String logOutReason) {
		this.logOutReason = logOutReason;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getLogOutTime() {
		return logOutTime;
	}

	public void setLogOutTime(Date logOutTime) {
		this.logOutTime = logOutTime;
	}

	public Boolean getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Boolean isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	public String getAroundScenicIds() {
		return aroundScenicIds;
	}

	public void setAroundScenicIds(String aroundScenicIds) {
		this.aroundScenicIds = aroundScenicIds;
	}

	// id orgcode加密
	public String getEncryptId() {

		return BaseDomainIdEncryptUtil.encryptDomainId(getId(),
				organization.getOrgInternalCode(), null);
	}

}
