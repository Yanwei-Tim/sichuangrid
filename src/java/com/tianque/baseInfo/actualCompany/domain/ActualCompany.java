package com.tianque.baseInfo.actualCompany.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.GisInfo;
import com.tianque.domain.LocationBaseInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * 实有单位
 */
public class ActualCompany extends LocationBaseInfo {

	private GisInfo gisInfo;
	// gis统计类型
	private String gisSearchType;
	private int giscountNum;
	private String keyPersonType;

	/** 所属网格 */
	private Organization organization;
	/** 所属责任区编号 */
	private String orgInternalCode;
	/** 单位名称 */
	private String companyName;
	/** 地址id */
	private Long addressId;
	/** 单位地址 */
	private String companyAddress;
	/** 单位类别 */
	private PropertyDict companyType;
	/** 是否重点单位 */
	private Boolean isKey;
	/** 法人代表 */
	private String corporateRepresentative;
	/** 身份证号 */
	private String idCardNo;
	/** 单位电话 */
	private String telephone;
	/** 单位传真 */
	private String facsimile;
	/** 营业执照号码 */
	private String businessLicenseNo;
	/** 组织机构代码 */
	private String orgCode;
	/** 注册资本 */
	private Double registeredCapital;
	/** 经济性质 */
	private PropertyDict economicNature;
	/** 有效期至 */
	private Date expiryDate;
	/** 注册日期 */
	private Date registrationDate;
	/** 经营范围 */
	private String businessScope;
	/** 注册地址 */
	private String registrationAddress;
	/** 从业人数 */
	private Long employeesNum;
	/** 主管部门 */
	private String competentDepartment;
	/** 管理级别 */
	private PropertyDict supervisoryLevel;
	/** 管理部门 */
	private String supervisoryDepartment;
	/** 消防等级 */
	private PropertyDict fireFightingLevel;
	/** 治安负责人 */
	private String securityChief;
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

	/** 重点要害部位 */
	/** 名称 */
	private String keyName;
	/** 武警人数 */
	private Long policeNumber;
	/** 方位 */
	private String position;
	/** 重要设施 */
	private String importantFacilities;
	/** 护卫队人数 */
	private Long convoyNumber;
	/** 经济价值 */
	private Double economicValue;
	/** 保安人数 */
	private Long securityNumber;
	/** 工作人员数 */
	private Long staffNumber;
	/** 经警人数 */
	private Long classicsAlarmNumber;
	/** 电话 */
	private String keyPhone;
	/** 具体位置 */
	private String location;
	/** 保卫制度建立情况 */
	private String defendEstablishment;
	/** 负责人 */
	private String chief;
	/** 负责人身份证号 */
	private String chiefIdNumber;
	/** 有关安全防范标准 */
	private String relevantSafety;
	/** 技防措施 */
	private String safetyMeasures;
	/** 人防措施 */
	private String civilMeasures;
	/** 物防措施 */
	private String thingMeasures;
	/** 犬防措施 */
	private String dogMeasures;
	/** 判定依据 */
	private String judgementBasis;
	/** 技防设施建设投入资金 */
	private Double safetyMeasuresFunds;

	/** 防范设施信息 */
	/** 设施名称 */
	private String facilitiesName;
	/** 设施类别 */
	private PropertyDict facilitiesCategory;
	/** 设施安装时间 */
	private Date installationTime;
	/** 运行投入金额 */
	private Double investmentAmount;
	/** 设施安装位置 */
	private String installationPosition;
	/** 所属公安业务分类 */
	private PropertyDict securityClassification;
	/** 能否输至公安图像中心 */
	private Boolean canLose;
	/** 报警设施连接部门 */
	private String connectedDepartment;
	/** 覆盖区域 */
	private String coverageArea;
	/** 设施保存天数 */
	private Long keepDays;
	/** 存储方式 */
	private String storageWays;
	/** 其中含摄像机数量 */
	private Long camerasNumber;
	/** 配置描述 */
	private String configurationDescribe;
	/** 其中含入侵探测器数量 */
	private Long intrusionDetectorNumber;

	public GisInfo getGisInfo() {
		return gisInfo;
	}

	public void setGisInfo(GisInfo gisInfo) {
		this.gisInfo = gisInfo;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public PropertyDict getCompanyType() {
		return companyType;
	}

	public void setCompanyType(PropertyDict companyType) {
		this.companyType = companyType;
	}

	/*
	 * public boolean isKey() { return isKey; } public void setKey(boolean
	 * isKey) { this.isKey = isKey; }
	 */
	public Boolean getIsKey() {
		return isKey;
	}

	public void setIsKey(Boolean isKey) {
		this.isKey = isKey;
	}

	public String getCorporateRepresentative() {
		return corporateRepresentative;
	}

	public void setCorporateRepresentative(String corporateRepresentative) {
		this.corporateRepresentative = corporateRepresentative;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFacsimile() {
		return facsimile;
	}

	public void setFacsimile(String facsimile) {
		this.facsimile = facsimile;
	}

	public String getBusinessLicenseNo() {
		return businessLicenseNo;
	}

	public void setBusinessLicenseNo(String businessLicenseNo) {
		this.businessLicenseNo = businessLicenseNo;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getRegistrationAddress() {
		return registrationAddress;
	}

	public void setRegistrationAddress(String registrationAddress) {
		this.registrationAddress = registrationAddress;
	}

	public Long getEmployeesNum() {
		return employeesNum;
	}

	public void setEmployeesNum(Long employeesNum) {
		this.employeesNum = employeesNum;
	}

	public String getCompetentDepartment() {
		return competentDepartment;
	}

	public void setCompetentDepartment(String competentDepartment) {
		this.competentDepartment = competentDepartment;
	}

	public PropertyDict getSupervisoryLevel() {
		return supervisoryLevel;
	}

	public void setSupervisoryLevel(PropertyDict supervisoryLevel) {
		this.supervisoryLevel = supervisoryLevel;
	}

	public String getSupervisoryDepartment() {
		return supervisoryDepartment;
	}

	public void setSupervisoryDepartment(String supervisoryDepartment) {
		this.supervisoryDepartment = supervisoryDepartment;
	}

	public Double getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(Double registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public PropertyDict getEconomicNature() {
		return economicNature;
	}

	public void setEconomicNature(PropertyDict economicNature) {
		this.economicNature = economicNature;
	}

	public PropertyDict getFireFightingLevel() {
		return fireFightingLevel;
	}

	public void setFireFightingLevel(PropertyDict fireFightingLevel) {
		this.fireFightingLevel = fireFightingLevel;
	}

	public String getSecurityChief() {
		return securityChief;
	}

	public void setSecurityChief(String securityChief) {
		this.securityChief = securityChief;
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

	@JSON(format = "yyyy-MM-dd")
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

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public Long getPoliceNumber() {
		return policeNumber;
	}

	public void setPoliceNumber(Long policeNumber) {
		this.policeNumber = policeNumber;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getImportantFacilities() {
		return importantFacilities;
	}

	public void setImportantFacilities(String importantFacilities) {
		this.importantFacilities = importantFacilities;
	}

	public Long getConvoyNumber() {
		return convoyNumber;
	}

	public void setConvoyNumber(Long convoyNumber) {
		this.convoyNumber = convoyNumber;
	}

	public Double getEconomicValue() {
		return economicValue;
	}

	public void setEconomicValue(Double economicValue) {
		this.economicValue = economicValue;
	}

	public Long getSecurityNumber() {
		return securityNumber;
	}

	public void setSecurityNumber(Long securityNumber) {
		this.securityNumber = securityNumber;
	}

	public Long getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(Long staffNumber) {
		this.staffNumber = staffNumber;
	}

	public Long getClassicsAlarmNumber() {
		return classicsAlarmNumber;
	}

	public void setClassicsAlarmNumber(Long classicsAlarmNumber) {
		this.classicsAlarmNumber = classicsAlarmNumber;
	}

	public String getKeyPhone() {
		return keyPhone;
	}

	public void setKeyPhone(String keyPhone) {
		this.keyPhone = keyPhone;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDefendEstablishment() {
		return defendEstablishment;
	}

	public void setDefendEstablishment(String defendEstablishment) {
		this.defendEstablishment = defendEstablishment;
	}

	public String getChief() {
		return chief;
	}

	public void setChief(String chief) {
		this.chief = chief;
	}

	public String getChiefIdNumber() {
		return chiefIdNumber;
	}

	public void setChiefIdNumber(String chiefIdNumber) {
		this.chiefIdNumber = chiefIdNumber;
	}

	public String getRelevantSafety() {
		return relevantSafety;
	}

	public void setRelevantSafety(String relevantSafety) {
		this.relevantSafety = relevantSafety;
	}

	public String getSafetyMeasures() {
		return safetyMeasures;
	}

	public void setSafetyMeasures(String safetyMeasures) {
		this.safetyMeasures = safetyMeasures;
	}

	public String getCivilMeasures() {
		return civilMeasures;
	}

	public void setCivilMeasures(String civilMeasures) {
		this.civilMeasures = civilMeasures;
	}

	public String getThingMeasures() {
		return thingMeasures;
	}

	public void setThingMeasures(String thingMeasures) {
		this.thingMeasures = thingMeasures;
	}

	public String getDogMeasures() {
		return dogMeasures;
	}

	public void setDogMeasures(String dogMeasures) {
		this.dogMeasures = dogMeasures;
	}

	public String getJudgementBasis() {
		return judgementBasis;
	}

	public void setJudgementBasis(String judgementBasis) {
		this.judgementBasis = judgementBasis;
	}

	public Double getSafetyMeasuresFunds() {
		return safetyMeasuresFunds;
	}

	public void setSafetyMeasuresFunds(Double safetyMeasuresFunds) {
		this.safetyMeasuresFunds = safetyMeasuresFunds;
	}

	public String getFacilitiesName() {
		return facilitiesName;
	}

	public void setFacilitiesName(String facilitiesName) {
		this.facilitiesName = facilitiesName;
	}

	public PropertyDict getFacilitiesCategory() {
		return facilitiesCategory;
	}

	public void setFacilitiesCategory(PropertyDict facilitiesCategory) {
		this.facilitiesCategory = facilitiesCategory;
	}

	public Date getInstallationTime() {
		return installationTime;
	}

	public void setInstallationTime(Date installationTime) {
		this.installationTime = installationTime;
	}

	public Double getInvestmentAmount() {
		return investmentAmount;
	}

	public void setInvestmentAmount(Double investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	public String getInstallationPosition() {
		return installationPosition;
	}

	public void setInstallationPosition(String installationPosition) {
		this.installationPosition = installationPosition;
	}

	public PropertyDict getSecurityClassification() {
		return securityClassification;
	}

	public void setSecurityClassification(PropertyDict securityClassification) {
		this.securityClassification = securityClassification;
	}

	public Boolean getCanLose() {
		return canLose;
	}

	public void setCanLose(Boolean canLose) {
		this.canLose = canLose;
	}

	public String getConnectedDepartment() {
		return connectedDepartment;
	}

	public void setConnectedDepartment(String connectedDepartment) {
		this.connectedDepartment = connectedDepartment;
	}

	public String getCoverageArea() {
		return coverageArea;
	}

	public void setCoverageArea(String coverageArea) {
		this.coverageArea = coverageArea;
	}

	public Long getKeepDays() {
		return keepDays;
	}

	public void setKeepDays(Long keepDays) {
		this.keepDays = keepDays;
	}

	public String getStorageWays() {
		return storageWays;
	}

	public void setStorageWays(String storageWays) {
		this.storageWays = storageWays;
	}

	public Long getCamerasNumber() {
		return camerasNumber;
	}

	public void setCamerasNumber(Long camerasNumber) {
		this.camerasNumber = camerasNumber;
	}

	public String getConfigurationDescribe() {
		return configurationDescribe;
	}

	public void setConfigurationDescribe(String configurationDescribe) {
		this.configurationDescribe = configurationDescribe;
	}

	public Long getIntrusionDetectorNumber() {
		return intrusionDetectorNumber;
	}

	public void setIntrusionDetectorNumber(Long intrusionDetectorNumber) {
		this.intrusionDetectorNumber = intrusionDetectorNumber;
	}

	public String getGisSearchType() {
		return gisSearchType;
	}

	public void setGisSearchType(String gisSearchType) {
		this.gisSearchType = gisSearchType;
	}

	public int getGiscountNum() {
		return giscountNum;
	}

	public void setGiscountNum(int giscountNum) {
		this.giscountNum = giscountNum;
	}

	public String getKeyPersonType() {
		return keyPersonType;
	}

	public void setKeyPersonType(String keyPersonType) {
		this.keyPersonType = keyPersonType;
	}

	// id orgcode加密
	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(),
				getOrgInternalCode(), null);
	}
}
