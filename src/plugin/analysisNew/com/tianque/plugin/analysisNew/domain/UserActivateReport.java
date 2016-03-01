package com.tianque.plugin.analysisNew.domain;

import java.io.Serializable;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * @Description:研判分析用户激活率实体类
 * @author zhangyouwei@hztianque.com
 * @date: 2015-1-15 下午11:57:25
 */
public class UserActivateReport extends BaseDomain implements Serializable {
	/** 年 */
	private int year;
	/** 月 */
	private int month;
	/** 组织机构（对应统计的组织机构例如（统计某某县的所有的）） */
	private Organization organization;
	/** 组织机构下辖乡镇的个数 */
	private int townCount;
	/** 组织机构下辖社区的个数 */
	private int communityCount;
	/** 组织机构下辖村的个数 */
	private int villageCount;

	/** 组织机构下辖街道乡镇的激活数量（某个乡镇至少有一个账号激活的为1否则为0） */
	private int townActivateCount;
	/** 组织机构下辖社区的激活数量 */
	private int communityActivateCount;
	/** 组织机构下辖村的激活数量（某个村社区至少有一个账号激活的是手机可用为1否则去看下辖的网格是否有至少一个激活手机可用用否则为0） */
	private int villageActivateCount;

	/** 上月社情民意收集数 */
	private int agencyOfOpinionCount;
	/** 上月事件处理总数量 */
	private int issueDealCount;

	/** 乡镇级账号总数 */
	private int townAccountCount;
	/** 乡镇级月活跃账号数 */
	private Integer townActiveAccountCount;
	/** 社区层级账号总数 */
	private int communityAccountCount;
	/** 社区层级周活跃账号数 */
	private Integer communityWeekActiveCount;
	/** 社区层级月活跃账号数 */
	private Integer communityMonthActiveCount;

	/** 乡镇层级参与百分比（激活数量/总数） */
	private String townAccountPercentage;
	/** 社区层级参与百分比（激活数量/总数） */
	private String communityAccountPercentage;
	/** 村层级参与百分比（激活数量/总数） */
	private String villageAcountPercentage;

	/** 乡镇层级账号月活跃度 */
	private String townMonthCoverageRate;

	/** 社区（村）周活跃度 */
	private String communityWeekCoverageRate;
	/** 社区（村）月活跃度 */
	private String communityMonthCoverageRate;

	/** 社区层级组织总数 */
	private int communityAllCount;
	/** 社区层级参数工作组织总数 */
	private int communityAllCountPercentage;

	private Long statisticsType;

	// /** 所有的激活账号个数，包括下辖和和职能部门 */
	// private int allActivateCount;
	// /** 城镇覆盖率 */
	// private String citiesCoverageRate;
	// /** 农村覆盖率 */
	// private String ruralCoverageRate;
	// /** 乡镇覆盖率 */
	// private String townCoverageRate;
	// /** 覆盖率（乡镇开通+社区开通/乡镇数+社区数） */
	// private String coverageRate;
	//
	// /** 月活跃度数 */
	// private int monthActiveRateCount;
	// /** 周活跃度数 */
	// private int weekActiveRateCount;

	// /** 季度活跃数 */
	// private int quarterActiveRateCount;
	// /** 开通职能部门的总数（只统计县级不包括下辖职能部门数，而且是直接统计个数） */
	// private int functionalActivateCount;
	// /** 季度活跃度（为历史记录即上个月开始往前推三个月） */
	// private String quarterActiveRate;
	// /** 季度活跃度环比 */
	// private String quarterLinkRate;
	// /** 月活跃度（同上上个月） */
	// private String monthActiveRate;
	// /** 月活跃度环比 */
	// private String monthLinkRate;
	// /** 周活跃度（同上上个月的最后一天向前推7天） */
	// private String weekActiveRate;
	// /** 周活跃度环比 */
	// private String weekLinkRate;

	public UserActivateReport() {
	}

	public UserActivateReport(Organization organization, int townCount,
			int communityCount, int villageCount, int townActivateCount,
			int communityActivateCount, int villageActivateCount,
			int functionalActivateCount, int allActivateCount,
			int quarterActiveRateCount, int monthActiveRateCount,
			int weekActiveRateCount, String quarterLinkRate,
			String monthLinkRate, String weekLinkRate) {
		super();
		this.organization = organization;
		this.townCount = townCount;
		this.communityCount = communityCount;
		this.villageCount = villageCount;
		this.townActivateCount = townActivateCount;
		this.communityActivateCount = communityActivateCount;
		this.villageActivateCount = villageActivateCount;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public int getTownCount() {
		return townCount;
	}

	public void setTownCount(int townCount) {
		this.townCount = townCount;
	}

	public int getVillageCount() {
		return villageCount;
	}

	public void setVillageCount(int villageCount) {
		this.villageCount = villageCount;
	}

	public int getTownActivateCount() {
		return townActivateCount;
	}

	public void setTownActivateCount(int townActivateCount) {
		this.townActivateCount = townActivateCount;
	}

	public int getCommunityActivateCount() {
		return communityActivateCount;
	}

	public void setCommunityActivateCount(int communityActivateCount) {
		this.communityActivateCount = communityActivateCount;
	}

	public int getVillageActivateCount() {
		return villageActivateCount;
	}

	public void setVillageActivateCount(int villageActivateCount) {
		this.villageActivateCount = villageActivateCount;
	}

	public int getCommunityCount() {
		return communityCount;
	}

	public void setCommunityCount(int communityCount) {
		this.communityCount = communityCount;
	}

	public String getTownAccountPercentage() {
		return townAccountPercentage;
	}

	public void setTownAccountPercentage(String townAccountPercentage) {
		this.townAccountPercentage = townAccountPercentage;
	}

	public String getCommunityAccountPercentage() {
		return communityAccountPercentage;
	}

	public void setCommunityAccountPercentage(String communityAccountPercentage) {
		this.communityAccountPercentage = communityAccountPercentage;
	}

	public String getVillageAcountPercentage() {
		return villageAcountPercentage;
	}

	public void setVillageAcountPercentage(String villageAcountPercentage) {
		this.villageAcountPercentage = villageAcountPercentage;
	}

	public String getTownMonthCoverageRate() {
		return townMonthCoverageRate;
	}

	public void setTownMonthCoverageRate(String townMonthCoverageRate) {
		this.townMonthCoverageRate = townMonthCoverageRate;
	}

	public String getCommunityWeekCoverageRate() {
		return communityWeekCoverageRate;
	}

	public void setCommunityWeekCoverageRate(String communityWeekCoverageRate) {
		this.communityWeekCoverageRate = communityWeekCoverageRate;
	}

	public String getCommunityMonthCoverageRate() {
		return communityMonthCoverageRate;
	}

	public void setCommunityMonthCoverageRate(String communityMonthCoverageRate) {
		this.communityMonthCoverageRate = communityMonthCoverageRate;
	}

	public int getAgencyOfOpinionCount() {
		return agencyOfOpinionCount;
	}

	public void setAgencyOfOpinionCount(int agencyOfOpinionCount) {
		this.agencyOfOpinionCount = agencyOfOpinionCount;
	}

	public int getIssueDealCount() {
		return issueDealCount;
	}

	public void setIssueDealCount(int issueDealCount) {
		this.issueDealCount = issueDealCount;
	}

	public int getTownAccountCount() {
		return townAccountCount;
	}

	public void setTownAccountCount(int townAccountCount) {
		this.townAccountCount = townAccountCount;
	}

	public int getCommunityAccountCount() {
		return communityAccountCount;
	}

	public void setCommunityAccountCount(int communityAccountCount) {
		this.communityAccountCount = communityAccountCount;
	}

	public int getCommunityAllCount() {
		return communityAllCount;
	}

	public void setCommunityAllCount(int communityAllCount) {
		this.communityAllCount = communityAllCount;
	}

	public int getCommunityAllCountPercentage() {
		return communityAllCountPercentage;
	}

	public void setCommunityAllCountPercentage(int communityAllCountPercentage) {
		this.communityAllCountPercentage = communityAllCountPercentage;
	}

	public Long getStatisticsType() {
		return statisticsType;
	}

	public void setStatisticsType(Long statisticsType) {
		this.statisticsType = statisticsType;
	}

	public Integer getTownActiveAccountCount() {
		if (townActiveAccountCount == null) {
			return 0;
		}
		return townActiveAccountCount;
	}

	public void setTownActiveAccountCount(Integer townActiveAccountCount) {
		this.townActiveAccountCount = townActiveAccountCount;
	}

	public Integer getCommunityWeekActiveCount() {
		if (communityWeekActiveCount == null) {
			return 0;
		}
		return communityWeekActiveCount;
	}

	public void setCommunityWeekActiveCount(Integer communityWeekActiveCount) {
		this.communityWeekActiveCount = communityWeekActiveCount;
	}

	public Integer getCommunityMonthActiveCount() {
		if (communityMonthActiveCount == null) {
			return 0;
		}
		return communityMonthActiveCount;
	}

	public void setCommunityMonthActiveCount(Integer communityMonthActiveCount) {
		this.communityMonthActiveCount = communityMonthActiveCount;
	}

}
