package com.tianque.newVillage.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

public class ReportDataSummary extends BaseDomain {
	/** 单位 */
	private Organization organization;
	/** 上报单位 */
	private Organization reportOrg;
	/** 上报年份 */
	private Integer year;
	/** 上报季度 */
	private Integer quarter;
	/** 审核状态 */
	private Integer checkStatus;
	/** 审核时间 */
	private Date checkDate;
	/** 审核状态 */
	private String checkUser;
	/** 上报状态 */
	private Integer reportStatus;
	/** 上报时间 */
	private Date reportDate;
	/** 上报状态 */
	private String reportUser;
	/** 上报数据ID(多个) */
	private String reportIds;
	/** 是计划还是上报 */
	private Integer isPlaning;

	private BasicYearInfo basicYearInfo = new BasicYearInfo();
	private String basicYearInfoData;

	private NewVillage newVillage = new NewVillage();
	private String newVillageData;

	private CommonServiceInfo commonServiceInfo = new CommonServiceInfo();
	private String commonServiceInfoData;

	private EnvironmentalReform environmentalReform = new EnvironmentalReform();
	private String environmentalReformData;

	private IndustryDevelopment industryDevelopment = new IndustryDevelopment();
	private String industryDevelopmentData;

	private Infrastructure infrastructure = new Infrastructure();
	private String infrastructureData;

	private OrganizationConstruction organizationConstruction = new OrganizationConstruction();
	private String organizationConstructionData;

	private CapitalInvested capitalInvested = new CapitalInvested();
	private String capitalInvestedData;

	private FarmerPerIncomeInfo farmerPerIncomeInfo = new FarmerPerIncomeInfo();
	private String farmerPerIncomeInfoData;

	private String statisticsDimensions;

	public String getStatisticsDimensions() {
		return statisticsDimensions;
	}

	public void setStatisticsDimensions(String statisticsDimensions) {
		this.statisticsDimensions = statisticsDimensions;
	}

	public Integer getIsPlaning() {
		return isPlaning;
	}

	public void setIsPlaning(Integer isPlaning) {
		this.isPlaning = isPlaning;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Organization getReportOrg() {
		return reportOrg;
	}

	public void setReportOrg(Organization reportOrg) {
		this.reportOrg = reportOrg;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getQuarter() {
		return quarter;
	}

	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

	public Integer getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getReportUser() {
		return reportUser;
	}

	public void setReportUser(String reportUser) {
		this.reportUser = reportUser;
	}

	public String getReportIds() {
		return reportIds;
	}

	public void setReportIds(String reportIds) {
		this.reportIds = reportIds;
	}

	public NewVillage getNewVillage() {
		return newVillage == null ? new NewVillage() : newVillage;
	}

	public void setNewVillage(NewVillage newVillage) {
		this.newVillage = newVillage;
	}

	public String getNewVillageData() {
		return newVillageData;
	}

	public void setNewVillageData(String newVillageData) {
		this.newVillageData = newVillageData;
	}

	public CommonServiceInfo getCommonServiceInfo() {
		return commonServiceInfo == null ? new CommonServiceInfo()
				: commonServiceInfo;
	}

	public void setCommonServiceInfo(CommonServiceInfo commonServiceInfo) {
		this.commonServiceInfo = commonServiceInfo;
	}

	public String getCommonServiceInfoData() {
		return commonServiceInfoData;
	}

	public void setCommonServiceInfoData(String commonServiceInfoData) {
		this.commonServiceInfoData = commonServiceInfoData;
	}

	public EnvironmentalReform getEnvironmentalReform() {
		return environmentalReform == null ? new EnvironmentalReform()
				: environmentalReform;
	}

	public void setEnvironmentalReform(EnvironmentalReform environmentalReform) {
		this.environmentalReform = environmentalReform;
	}

	public String getEnvironmentalReformData() {
		return environmentalReformData;
	}

	public void setEnvironmentalReformData(String environmentalReformData) {
		this.environmentalReformData = environmentalReformData;
	}

	public IndustryDevelopment getIndustryDevelopment() {
		return industryDevelopment == null ? new IndustryDevelopment()
				: industryDevelopment;
	}

	public void setIndustryDevelopment(IndustryDevelopment industryDevelopment) {
		this.industryDevelopment = industryDevelopment;
	}

	public String getIndustryDevelopmentData() {
		return industryDevelopmentData;
	}

	public void setIndustryDevelopmentData(String industryDevelopmentData) {
		this.industryDevelopmentData = industryDevelopmentData;
	}

	public Infrastructure getInfrastructure() {
		return infrastructure == null ? new Infrastructure() : infrastructure;
	}

	public void setInfrastructure(Infrastructure infrastructure) {
		this.infrastructure = infrastructure;
	}

	public String getInfrastructureData() {
		return infrastructureData;
	}

	public void setInfrastructureData(String infrastructureData) {
		this.infrastructureData = infrastructureData;
	}

	public OrganizationConstruction getOrganizationConstruction() {
		return organizationConstruction == null ? new OrganizationConstruction()
				: organizationConstruction;
	}

	public void setOrganizationConstruction(
			OrganizationConstruction organizationConstruction) {
		this.organizationConstruction = organizationConstruction;
	}

	public String getOrganizationConstructionData() {
		return organizationConstructionData;
	}

	public void setOrganizationConstructionData(
			String organizationConstructionData) {
		this.organizationConstructionData = organizationConstructionData;
	}

	public CapitalInvested getCapitalInvested() {
		return capitalInvested == null ? new CapitalInvested()
				: capitalInvested;
	}

	public void setCapitalInvested(CapitalInvested capitalInvested) {
		this.capitalInvested = capitalInvested;
	}

	public String getCapitalInvestedData() {
		return capitalInvestedData;
	}

	public void setCapitalInvestedData(String capitalInvestedData) {
		this.capitalInvestedData = capitalInvestedData;
	}

	public FarmerPerIncomeInfo getFarmerPerIncomeInfo() {
		return farmerPerIncomeInfo == null ? new FarmerPerIncomeInfo()
				: farmerPerIncomeInfo;
	}

	public void setFarmerPerIncomeInfo(FarmerPerIncomeInfo farmerPerIncomeInfo) {
		this.farmerPerIncomeInfo = farmerPerIncomeInfo;
	}

	public String getFarmerPerIncomeInfoData() {
		return farmerPerIncomeInfoData;
	}

	public void setFarmerPerIncomeInfoData(String farmerPerIncomeInfoData) {
		this.farmerPerIncomeInfoData = farmerPerIncomeInfoData;
	}

	public BasicYearInfo getBasicYearInfo() {
		return basicYearInfo == null ? new BasicYearInfo() : basicYearInfo;
	}

	public void setBasicYearInfo(BasicYearInfo basicYearInfo) {
		this.basicYearInfo = basicYearInfo;
	}

	public String getBasicYearInfoData() {
		return basicYearInfoData;
	}

	public void setBasicYearInfoData(String basicYearInfoData) {
		this.basicYearInfoData = basicYearInfoData;
	}

}
