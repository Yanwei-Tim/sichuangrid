package com.tianque.baseInfo.internetBar.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.GisInfo;
import com.tianque.domain.LocationBaseInfo;

public class InternetBar extends LocationBaseInfo {

	public InternetBar() {
		setLocationType(BaseInfoTables.INTERNETBAR_KEY);
	}

	/** 场所名称 */
	private String placeName;
	/** 全拼 */
	private String fullPinyin;
	/** 简拼 */
	private String simplePinyin;
	/*** 场所地址 */
	private String placeAddress;
	/** 场所id */
	private Long placeId;
	/** 负责人 */
	private String manager;
	/** 联系电话 */
	private String mobile;
	/** 网络文化经营许可证号 */
	private String netCultureLicenceNo;
	/** 宽带接入服务商 */
	private String netAccessProviders;
	/** 接入方式 */
	private String internetAccessType;
	/** 网络安全审核意见书号 */
	private String netSecurityAuditNo;
	/** 临时上网卡数量 */
	private Integer tempNetCardNum;
	/** 电脑台数 */
	private Integer totalComputerNum;
	/** 消防审核意见书号 */
	private String fireAuditDocumentNo;
	/** 网吧编号 */
	private String barCode;
	/** 营业面积 */
	private Double operationArea;
	/** 现使用IP */
	private String useIP;
	/** 所在地派出所名称 */
	private String stationPolice;
	/** 图片url */
	private String imgUrl;
	/** 注销原因 */
	private String logOutReason;
	/** 注销时间 */
	private Date logOutTime;
	/** 备注 */
	private String remark;
	/** gis */
	private GisInfo gisInfo;

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
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

	public String getPlaceAddress() {
		return placeAddress;
	}

	public void setPlaceAddress(String placeAddress) {
		this.placeAddress = placeAddress;
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
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

	public String getNetCultureLicenceNo() {
		return netCultureLicenceNo;
	}

	public void setNetCultureLicenceNo(String netCultureLicenceNo) {
		this.netCultureLicenceNo = netCultureLicenceNo;
	}

	public String getInternetAccessType() {
		return internetAccessType;
	}

	public void setInternetAccessType(String internetAccessType) {
		this.internetAccessType = internetAccessType;
	}

	public Integer getTempNetCardNum() {
		return tempNetCardNum;
	}

	public void setTempNetCardNum(Integer tempNetCardNum) {
		this.tempNetCardNum = tempNetCardNum;
	}

	public Integer getTotalComputerNum() {
		return totalComputerNum;
	}

	public void setTotalComputerNum(Integer totalComputerNum) {
		this.totalComputerNum = totalComputerNum;
	}

	public String getFireAuditDocumentNo() {
		return fireAuditDocumentNo;
	}

	public void setFireAuditDocumentNo(String fireAuditDocumentNo) {
		this.fireAuditDocumentNo = fireAuditDocumentNo;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Double getOperationArea() {
		return operationArea;
	}

	public void setOperationArea(Double operationArea) {
		this.operationArea = operationArea;
	}

	public String getUseIP() {
		return useIP;
	}

	public void setUseIP(String useIP) {
		this.useIP = useIP;
	}

	public String getStationPolice() {
		return stationPolice;
	}

	public void setStationPolice(String stationPolice) {
		this.stationPolice = stationPolice;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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

	public String getNetAccessProviders() {
		return netAccessProviders;
	}

	public void setNetAccessProviders(String netAccessProviders) {
		this.netAccessProviders = netAccessProviders;
	}

	public String getNetSecurityAuditNo() {
		return netSecurityAuditNo;
	}

	public void setNetSecurityAuditNo(String netSecurityAuditNo) {
		this.netSecurityAuditNo = netSecurityAuditNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
				getOrganization().getOrgInternalCode(), null);
	}

}
