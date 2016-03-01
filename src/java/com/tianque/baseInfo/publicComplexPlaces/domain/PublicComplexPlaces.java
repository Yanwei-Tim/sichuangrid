package com.tianque.baseInfo.publicComplexPlaces.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.GisInfo;
import com.tianque.domain.LocationBaseInfo;
import com.tianque.domain.PropertyDict;

/**
 * 公共复杂场所
 */
public class PublicComplexPlaces extends LocationBaseInfo {

	public PublicComplexPlaces() {
		setLocationType(BaseInfoTables.PUBLICCOMPLEXPLACES_KEY);
	}

	/** 所属网格内置码 */
	private String orgInternalCode;
	/** 场所名称 */
	private String placeName;
	/*** 场所地址 */
	private String placeAddress;
	/** 负责人 */
	private String manager;
	/** 负责人手机 */
	private String managerMobile;
	/** 负责人电话 */
	private String managerTelephone;
	/** 场所类型 */
	private PropertyDict type;
	/** 场所细类 */
	private PropertyDict detailedType;
	/** 存在隐患 */
	private String hiddenCases;
	/** 隐患整改情况 */
	private String hiddenRectification;
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

	private String imgUrl;
	/** gis */
	private GisInfo gisInfo;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getManagerMobile() {
		return managerMobile;
	}

	public void setManagerMobile(String managerMobile) {
		this.managerMobile = managerMobile;
	}

	public String getManagerTelephone() {
		return managerTelephone;
	}

	public void setManagerTelephone(String managerTelephone) {
		this.managerTelephone = managerTelephone;
	}

	public PropertyDict getType() {
		return type;
	}

	public void setType(PropertyDict type) {
		this.type = type;
	}

	public PropertyDict getDetailedType() {
		return detailedType;
	}

	public void setDetailedType(PropertyDict detailedType) {
		this.detailedType = detailedType;
	}

	public String getHiddenCases() {
		return hiddenCases;
	}

	public void setHiddenCases(String hiddenCases) {
		this.hiddenCases = hiddenCases;
	}

	public String getHiddenRectification() {
		return hiddenRectification;
	}

	public void setHiddenRectification(String hiddenRectification) {
		this.hiddenRectification = hiddenRectification;
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
		if (null != fullPinyin && fullPinyin.length() >= 30) {
			fullPinyin = fullPinyin.substring(0, 30);
		}
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		if (null != simplePinyin && simplePinyin.length() >= 10) {
			simplePinyin = simplePinyin.substring(0, 10);
		}
		this.simplePinyin = simplePinyin;
	}

	public String getPlaceAddress() {
		return placeAddress;
	}

	public void setPlaceAddress(String placeAddress) {
		this.placeAddress = placeAddress;
	}

	public GisInfo getGisInfo() {
		return gisInfo;
	}

	public void setGisInfo(GisInfo gisInfo) {
		this.gisInfo = gisInfo;
	}

	// id orgcode加密
	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(),
				getOrgInternalCode(), null);
	}

}
