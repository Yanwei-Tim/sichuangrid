package com.tianque.domain;

import com.tianque.core.base.BaseDomain;

/*
 * 社会治安重点地区排查整治工作情况
 */
@SuppressWarnings("serial")
public class SocietySafeCheck extends BaseDomain {

	private Long dailyLogId;
	private Long checkEngineSum;
	private Long publicitySum;
	private Long hitFerretSum;
	private Long hitArrestSum;
	private Long hitDestroySum;
	private Long checkEngineCadre;
	private Long checkEnginePeople;
	private Long checkEngineWork;
	private Long checkOther;
	private Long publicityIssueReport;
	private Long publicityMeeting;
	private Long publicityPeopleAccuse;
	private Long publicityFerretPenal;
	private Long publicityArrestOffender;
	private Long findCounty;
	private Long findTown;
	private Long findVillage;
	private Long findOther;
	private Long findBlackArea;
	private Long findDangerCrime;
	private Long findRobAndSteal;
	private Long findYellowBetBane;
	private Long findHeresy;
	private Long finishCounty;
	private Long finishTown;
	private Long finishVillage;
	private Long finishOther;
	private Long justCounty;
	private Long justTown;
	private Long justVillage;
	private Long justOher;
	private Long hitFerretDangerCrime;
	private Long hitFerretRobAndSteal;
	private Long hitFerretYellowBetBane;
	private Long hitArrestFlowCrime;
	private Long hitArrestExternalPeople;
	private Long hitArrestNoWork;
	private Long hitArrestLiberate;
	private Long hitArrestYouth;
	private Long hitDestroyOrganization;
	private Long hitDestroyForce;
	private Long cautionProvince;
	private Long cautionCity;
	private Long cautionCounty;
	private Long urgeProvince;
	private Long urgeCity;
	private Long urgeCounty;
	private String createTabUser;
	private String checkUser;
	private String reportDate;
	private String createTabDate;
	private Integer reportState;
	private String orgCode;
	private String reportYear;
	private int reportMonth;
	private String reportName;
	private int reportType;
	private int quarter;
	private String signCode;
	private String directoryNameSign;
	private Long orgId;

	public Long getCheckEngineSum() {
		return checkEngineSum;
	}

	public void setCheckEngineSum(Long checkEngineSum) {
		this.checkEngineSum = checkEngineSum;
	}

	public Long getPublicitySum() {
		return publicitySum;
	}

	public void setPublicitySum(Long publicitySum) {
		this.publicitySum = publicitySum;
	}

	public Long getHitFerretSum() {
		return hitFerretSum;
	}

	public void setHitFerretSum(Long hitFerretSum) {
		this.hitFerretSum = hitFerretSum;
	}

	public Long getHitArrestSum() {
		return hitArrestSum;
	}

	public void setHitArrestSum(Long hitArrestSum) {
		this.hitArrestSum = hitArrestSum;
	}

	public Long getHitDestroySum() {
		return hitDestroySum;
	}

	public void setHitDestroySum(Long hitDestroySum) {
		this.hitDestroySum = hitDestroySum;
	}

	public Long getCheckEngineCadre() {
		return checkEngineCadre;
	}

	public void setCheckEngineCadre(Long checkEngineCadre) {
		this.checkEngineCadre = checkEngineCadre;
	}

	public Long getCheckEnginePeople() {
		return checkEnginePeople;
	}

	public void setCheckEnginePeople(Long checkEnginePeople) {
		this.checkEnginePeople = checkEnginePeople;
	}

	public Long getCheckEngineWork() {
		return checkEngineWork;
	}

	public void setCheckEngineWork(Long checkEngineWork) {
		this.checkEngineWork = checkEngineWork;
	}

	public Long getCheckOther() {
		return checkOther;
	}

	public void setCheckOther(Long checkOther) {
		this.checkOther = checkOther;
	}

	public Long getPublicityIssueReport() {
		return publicityIssueReport;
	}

	public void setPublicityIssueReport(Long publicityIssueReport) {
		this.publicityIssueReport = publicityIssueReport;
	}

	public Long getPublicityMeeting() {
		return publicityMeeting;
	}

	public void setPublicityMeeting(Long publicityMeeting) {
		this.publicityMeeting = publicityMeeting;
	}

	public Long getPublicityPeopleAccuse() {
		return publicityPeopleAccuse;
	}

	public void setPublicityPeopleAccuse(Long publicityPeopleAccuse) {
		this.publicityPeopleAccuse = publicityPeopleAccuse;
	}

	public Long getPublicityFerretPenal() {
		return publicityFerretPenal;
	}

	public void setPublicityFerretPenal(Long publicityFerretPenal) {
		this.publicityFerretPenal = publicityFerretPenal;
	}

	public Long getPublicityArrestOffender() {
		return publicityArrestOffender;
	}

	public void setPublicityArrestOffender(Long publicityArrestOffender) {
		this.publicityArrestOffender = publicityArrestOffender;
	}

	public Long getFindCounty() {
		return findCounty;
	}

	public void setFindCounty(Long findCounty) {
		this.findCounty = findCounty;
	}

	public Long getFindTown() {
		return findTown;
	}

	public void setFindTown(Long findTown) {
		this.findTown = findTown;
	}

	public Long getFindVillage() {
		return findVillage;
	}

	public void setFindVillage(Long findVillage) {
		this.findVillage = findVillage;
	}

	public Long getFindOther() {
		return findOther;
	}

	public void setFindOther(Long findOther) {
		this.findOther = findOther;
	}

	public Long getFindBlackArea() {
		return findBlackArea;
	}

	public void setFindBlackArea(Long findBlackArea) {
		this.findBlackArea = findBlackArea;
	}

	public Long getFindDangerCrime() {
		return findDangerCrime;
	}

	public void setFindDangerCrime(Long findDangerCrime) {
		this.findDangerCrime = findDangerCrime;
	}

	public Long getFindRobAndSteal() {
		return findRobAndSteal;
	}

	public void setFindRobAndSteal(Long findRobAndSteal) {
		this.findRobAndSteal = findRobAndSteal;
	}

	public Long getFindYellowBetBane() {
		return findYellowBetBane;
	}

	public void setFindYellowBetBane(Long findYellowBetBane) {
		this.findYellowBetBane = findYellowBetBane;
	}

	public Long getFindHeresy() {
		return findHeresy;
	}

	public void setFindHeresy(Long findHeresy) {
		this.findHeresy = findHeresy;
	}

	public Long getFinishCounty() {
		return finishCounty;
	}

	public void setFinishCounty(Long finishCounty) {
		this.finishCounty = finishCounty;
	}

	public Long getFinishTown() {
		return finishTown;
	}

	public void setFinishTown(Long finishTown) {
		this.finishTown = finishTown;
	}

	public Long getFinishVillage() {
		return finishVillage;
	}

	public void setFinishVillage(Long finishVillage) {
		this.finishVillage = finishVillage;
	}

	public Long getFinishOther() {
		return finishOther;
	}

	public void setFinishOther(Long finishOther) {
		this.finishOther = finishOther;
	}

	public Long getJustCounty() {
		return justCounty;
	}

	public void setJustCounty(Long justCounty) {
		this.justCounty = justCounty;
	}

	public Long getJustTown() {
		return justTown;
	}

	public void setJustTown(Long justTown) {
		this.justTown = justTown;
	}

	public Long getJustVillage() {
		return justVillage;
	}

	public void setJustVillage(Long justVillage) {
		this.justVillage = justVillage;
	}

	public Long getJustOher() {
		return justOher;
	}

	public void setJustOher(Long justOher) {
		this.justOher = justOher;
	}

	public Long getHitFerretDangerCrime() {
		return hitFerretDangerCrime;
	}

	public void setHitFerretDangerCrime(Long hitFerretDangerCrime) {
		this.hitFerretDangerCrime = hitFerretDangerCrime;
	}

	public Long getHitFerretRobAndSteal() {
		return hitFerretRobAndSteal;
	}

	public void setHitFerretRobAndSteal(Long hitFerretRobAndSteal) {
		this.hitFerretRobAndSteal = hitFerretRobAndSteal;
	}

	public Long getHitFerretYellowBetBane() {
		return hitFerretYellowBetBane;
	}

	public void setHitFerretYellowBetBane(Long hitFerretYellowBetBane) {
		this.hitFerretYellowBetBane = hitFerretYellowBetBane;
	}

	public Long getHitArrestFlowCrime() {
		return hitArrestFlowCrime;
	}

	public void setHitArrestFlowCrime(Long hitArrestFlowCrime) {
		this.hitArrestFlowCrime = hitArrestFlowCrime;
	}

	public Long getHitArrestExternalPeople() {
		return hitArrestExternalPeople;
	}

	public void setHitArrestExternalPeople(Long hitArrestExternalPeople) {
		this.hitArrestExternalPeople = hitArrestExternalPeople;
	}

	public Long getHitArrestNoWork() {
		return hitArrestNoWork;
	}

	public void setHitArrestNoWork(Long hitArrestNoWork) {
		this.hitArrestNoWork = hitArrestNoWork;
	}

	public Long getHitArrestLiberate() {
		return hitArrestLiberate;
	}

	public void setHitArrestLiberate(Long hitArrestLiberate) {
		this.hitArrestLiberate = hitArrestLiberate;
	}

	public Long getHitArrestYouth() {
		return hitArrestYouth;
	}

	public void setHitArrestYouth(Long hitArrestYouth) {
		this.hitArrestYouth = hitArrestYouth;
	}

	public Long getHitDestroyOrganization() {
		return hitDestroyOrganization;
	}

	public void setHitDestroyOrganization(Long hitDestroyOrganization) {
		this.hitDestroyOrganization = hitDestroyOrganization;
	}

	public Long getHitDestroyForce() {
		return hitDestroyForce;
	}

	public void setHitDestroyForce(Long hitDestroyForce) {
		this.hitDestroyForce = hitDestroyForce;
	}

	public Long getCautionProvince() {
		return cautionProvince;
	}

	public void setCautionProvince(Long cautionProvince) {
		this.cautionProvince = cautionProvince;
	}

	public Long getCautionCity() {
		return cautionCity;
	}

	public void setCautionCity(Long cautionCity) {
		this.cautionCity = cautionCity;
	}

	public Long getCautionCounty() {
		return cautionCounty;
	}

	public void setCautionCounty(Long cautionCounty) {
		this.cautionCounty = cautionCounty;
	}

	public Long getUrgeProvince() {
		return urgeProvince;
	}

	public void setUrgeProvince(Long urgeProvince) {
		this.urgeProvince = urgeProvince;
	}

	public Long getUrgeCity() {
		return urgeCity;
	}

	public void setUrgeCity(Long urgeCity) {
		this.urgeCity = urgeCity;
	}

	public Long getUrgeCounty() {
		return urgeCounty;
	}

	public void setUrgeCounty(Long urgeCounty) {
		this.urgeCounty = urgeCounty;
	}

	public String getCreateTabUser() {
		return createTabUser;
	}

	public void setCreateTabUser(String createTabUser) {
		this.createTabUser = createTabUser;
	}

	public String getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public Integer getReportState() {
		return reportState;
	}

	public void setReportState(Integer reportState) {
		this.reportState = reportState;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getReportYear() {
		return reportYear;
	}

	public void setReportYear(String reportYear) {
		this.reportYear = reportYear;
	}

	public int getReportMonth() {
		return reportMonth;
	}

	public void setReportMonth(int reportMonth) {
		this.reportMonth = reportMonth;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getCreateTabDate() {
		return createTabDate;
	}

	public void setCreateTabDate(String createTabDate) {
		this.createTabDate = createTabDate;
	}

	public Long getDailyLogId() {
		return dailyLogId;
	}

	public void setDailyLogId(Long dailyLogId) {
		this.dailyLogId = dailyLogId;
	}

	public int getReportType() {
		return reportType;
	}

	public void setReportType(int reportType) {
		this.reportType = reportType;
	}

	public int getQuarter() {
		return quarter;
	}

	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}

	public String getSignCode() {
		return signCode;
	}

	public void setSignCode(String signCode) {
		this.signCode = signCode;
	}

	public String getDirectoryNameSign() {
		return directoryNameSign;
	}

	public void setDirectoryNameSign(String directoryNameSign) {
		this.directoryNameSign = directoryNameSign;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}
