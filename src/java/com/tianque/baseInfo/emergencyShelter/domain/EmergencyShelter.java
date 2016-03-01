package com.tianque.baseInfo.emergencyShelter.domain;

import java.util.Date;

import com.tianque.domain.LocationBaseInfo;
import com.tianque.domain.Organization;

/**
 * 应急避难场所
 */
public class EmergencyShelter extends LocationBaseInfo {
	/** 所属网格 */
	private Organization organization;
	/** 所属责任区编号 */
	private String orgInternalCode;
	/** 场地名称 */
	private String name;
	/** 场地位置 */
	private String address;
	/** 经纬度 */
	private String longAititude;
	/** 场地类型 */
	private String siteType;
	/** 面积 */
	private Double area;
	/** 可容纳人数（人） */
	private Integer fullPersonNum;
	/** 周边社区/村数量 */
	private Integer aroundVillageNum;
	/** 周边人口 */
	private String aroundPersonNum;
	/** 周边道路情况 */
	private String aroundRoadCondition;
	/** 功能完善 */
	private String functionComplete;
	/** 标识 */
	private Integer identificationNum;
	/** 图形符号 */
	private Integer imageSymbolNum;
	/** 指示 */
	private Integer pointNum;
	/** 备注 */
	private String remark;
	/** 图像路径 */
	private String imgUrl;
	/** 全拼 */
	private String fullPinyin;
	/** 简拼 */
	private String simplePinyin;
	/** 注销时间 */
	private Date logOutTime;
	/** 注销原因 */
	private String logOutReason;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLongAititude() {
		return longAititude;
	}

	public void setLongAititude(String longAititude) {
		this.longAititude = longAititude;
	}

	public String getSiteType() {
		return siteType;
	}

	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Integer getFullPersonNum() {
		return fullPersonNum;
	}

	public void setFullPersonNum(Integer fullPersonNum) {
		this.fullPersonNum = fullPersonNum;
	}

	public Integer getAroundVillageNum() {
		return aroundVillageNum;
	}

	public void setAroundVillageNum(Integer aroundVillageNum) {
		this.aroundVillageNum = aroundVillageNum;
	}

	public String getAroundPersonNum() {
		return aroundPersonNum;
	}

	public void setAroundPersonNum(String aroundPersonNum) {
		this.aroundPersonNum = aroundPersonNum;
	}

	public String getAroundRoadCondition() {
		return aroundRoadCondition;
	}

	public void setAroundRoadCondition(String aroundRoadCondition) {
		this.aroundRoadCondition = aroundRoadCondition;
	}

	public String getFunctionComplete() {
		return functionComplete;
	}

	public void setFunctionComplete(String functionComplete) {
		this.functionComplete = functionComplete;
	}

	public Integer getIdentificationNum() {
		return identificationNum;
	}

	public void setIdentificationNum(Integer identificationNum) {
		this.identificationNum = identificationNum;
	}

	public Integer getImageSymbolNum() {
		return imageSymbolNum;
	}

	public void setImageSymbolNum(Integer imageSymbolNum) {
		this.imageSymbolNum = imageSymbolNum;
	}

	public Integer getPointNum() {
		return pointNum;
	}

	public void setPointNum(Integer pointNum) {
		this.pointNum = pointNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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

	public Date getLogOutTime() {
		return logOutTime;
	}

	public void setLogOutTime(Date logOutTime) {
		this.logOutTime = logOutTime;
	}

	public String getLogOutReason() {
		return logOutReason;
	}

	public void setLogOutReason(String logOutReason) {
		this.logOutReason = logOutReason;
	}

}
