package com.tianque.domain.vo;

import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.Organization;

@SuppressWarnings("serial")
public class SearchInternetBarVo extends BaseDomain {
	/** 场所id */
	private Long placeId;
	/** 列表页面快速查询的字段 */
	private String unitName;

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
	private Integer tempNetCardNumStart;
	/** 临时上网卡数量 */
	private Integer tempNetCardNumEnd;
	/** 电脑台数 */
	private Integer totalComputerNumStart;
	/** 电脑台数 */
	private Integer totalComputerNumEnd;
	/** 消防审核意见书号 */
	private String fireAuditDocumentNo;
	/** 网吧编号 */
	private String barCode;
	/** 营业面积 */
	private Double operationAreaStart;
	/** 营业面积 */
	private Double operationAreaEnd;
	/** 现使用IP */
	private String useIP;
	/** 所在地派出所名称 */
	private String stationPolice;

	/** 注销原因 */
	private String logOutReason;
	/** 注销时间 */
	private Date logOutTimeStart;
	/** 注销时间 */
	private Date logOutTimeEnd;

	private Organization organization;
	/** 所属网格内置码 */
	private String orgInternalCode;
	/** 场所地址 */
	private String placeAddress;
	private String placeName;

	/** 负责人 */
	private String manager;

	private Boolean isEmphasis;
	private String remark;
	// 是否有治安负责人
	private Long hasServiceTeamMember;
	// 是否有巡场记录
	private Long hasServiceRecord;
	private String importantLocationType;

	public SearchInternetBarVo() {
		setImportantLocationType(BaseInfoTables.INTERNETBAR_KEY);
	}

	/**
	 * 关注程度
	 */
	private Long attentionExtentId;

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

	public String getPlaceAddress() {
		return placeAddress;
	}

	public void setPlaceAddress(String placeAddress) {
		this.placeAddress = placeAddress;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Boolean getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Boolean isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
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

	public String getNetSecurityAuditNo() {
		return netSecurityAuditNo;
	}

	public void setNetSecurityAuditNo(String netSecurityAuditNo) {
		this.netSecurityAuditNo = netSecurityAuditNo;
	}

	public Integer getTempNetCardNumStart() {
		return tempNetCardNumStart;
	}

	public void setTempNetCardNumStart(Integer tempNetCardNumStart) {
		this.tempNetCardNumStart = tempNetCardNumStart;
	}

	public Integer getTempNetCardNumEnd() {
		return tempNetCardNumEnd;
	}

	public void setTempNetCardNumEnd(Integer tempNetCardNumEnd) {
		this.tempNetCardNumEnd = tempNetCardNumEnd;
	}

	public Integer getTotalComputerNumStart() {
		return totalComputerNumStart;
	}

	public void setTotalComputerNumStart(Integer totalComputerNumStart) {
		this.totalComputerNumStart = totalComputerNumStart;
	}

	public Integer getTotalComputerNumEnd() {
		return totalComputerNumEnd;
	}

	public void setTotalComputerNumEnd(Integer totalComputerNumEnd) {
		this.totalComputerNumEnd = totalComputerNumEnd;
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

	public Double getOperationAreaStart() {
		return operationAreaStart;
	}

	public void setOperationAreaStart(Double operationAreaStart) {
		this.operationAreaStart = operationAreaStart;
	}

	public Double getOperationAreaEnd() {
		return operationAreaEnd;
	}

	public void setOperationAreaEnd(Double operationAreaEnd) {
		this.operationAreaEnd = operationAreaEnd;
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

	public String getLogOutReason() {
		return logOutReason;
	}

	public void setLogOutReason(String logOutReason) {
		this.logOutReason = logOutReason;
	}

	public Date getLogOutTimeStart() {
		return logOutTimeStart;
	}

	public void setLogOutTimeStart(Date logOutTimeStart) {
		this.logOutTimeStart = logOutTimeStart;
	}

	public Date getLogOutTimeEnd() {
		return logOutTimeEnd;
	}

	public void setLogOutTimeEnd(Date logOutTimeEnd) {
		this.logOutTimeEnd = logOutTimeEnd;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
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
