package com.tianque.plugin.dataManage.location.internetBarTemp.domain;

import com.tianque.core.util.BaseInfoTables;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;
import com.tianque.plugin.dataManage.location.placeCommon.domain.PlaceCommonTemp;

/**
 * 网吧
 */
public class InternetBarTemp extends PlaceCommonTemp {
	public InternetBarTemp() {
		setLocationType(BaseInfoTables.INTERNETBAR_KEY);
	}

	private ClaimDetail claimDetail;
	/** 联系方式 */
	private String mobile;

	/** 所在地派出所名称 */
	private String stationPolice;
	/** 网吧编号 */
	private String barCode;
	/** 营业面积 */
	private Double operationArea;
	/** 临时上网卡数量 */
	private Integer tempNetCardNum;
	/** 电脑总数 */
	private Integer totalComputerNum;
	/** 宽带接入服务商 */
	private String netAccessProviders;
	/** 接入方式 */
	private String internetAccessType;
	/** 现使用IP */
	private String useIP;
	/** 网络文件审核意见书号 */
	private String netCultureLicenceNo;
	/** 网络安全审核意见书号 */
	private String netSecurityAuditNo;
	/** 消防审核意见书号 */
	private String fireAuditDocumentNo;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStationPolice() {
		return stationPolice;
	}

	public void setStationPolice(String stationPolice) {
		this.stationPolice = stationPolice;
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

	public String getNetAccessProviders() {
		return netAccessProviders;
	}

	public void setNetAccessProviders(String netAccessProviders) {
		this.netAccessProviders = netAccessProviders;
	}

	public String getInternetAccessType() {
		return internetAccessType;
	}

	public void setInternetAccessType(String internetAccessType) {
		this.internetAccessType = internetAccessType;
	}

	public String getUseIP() {
		return useIP;
	}

	public void setUseIP(String useIP) {
		this.useIP = useIP;
	}

	public String getNetCultureLicenceNo() {
		return netCultureLicenceNo;
	}

	public void setNetCultureLicenceNo(String netCultureLicenceNo) {
		this.netCultureLicenceNo = netCultureLicenceNo;
	}

	public String getNetSecurityAuditNo() {
		return netSecurityAuditNo;
	}

	public void setNetSecurityAuditNo(String netSecurityAuditNo) {
		this.netSecurityAuditNo = netSecurityAuditNo;
	}

	public String getFireAuditDocumentNo() {
		return fireAuditDocumentNo;
	}

	public void setFireAuditDocumentNo(String fireAuditDocumentNo) {
		this.fireAuditDocumentNo = fireAuditDocumentNo;
	}

	public ClaimDetail getClaimDetail() {
		if (null == claimDetail) {
			claimDetail = new ClaimDetail();
		}
		return claimDetail;
	}

	public void setClaimDetail(ClaimDetail claimDetail) {
		this.claimDetail = claimDetail;
	}

}
