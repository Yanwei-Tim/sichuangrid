package com.tianque.baseInfo.dangerousChemicalsUnit.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.GisInfo;
import com.tianque.domain.LocationBaseInfo;

public class DangerousChemicalsUnit extends LocationBaseInfo {

	public DangerousChemicalsUnit() {
		setLocationType(BaseInfoTables.DANGEROUSCHEMICALSUNIT_KEY);
	}

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
	/** 注销时间 */
	private Date logOutTime;
	/** 备注 */
	private String remark;
	/** 全拼 */
	private String fullPinyin;
	/** 简拼 */
	private String simplePinyin;
	/** gis */
	private GisInfo gisInfo;
	/** 图像url */
	private String imgUrl;

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

	@JSON(format = "yyyy-MM-dd")
	public Date getLogOutTime() {
		return logOutTime;
	}

	public void setLogOutTime(Date logOutTime) {
		this.logOutTime = logOutTime;
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

	public GisInfo getGisInfo() {
		return gisInfo;
	}

	public void setGisInfo(GisInfo gisInfo) {
		this.gisInfo = gisInfo;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	// id orgcode加密
	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(),
				getOrgInternalCode(), null);
	}

}
