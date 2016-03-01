package com.tianque.plugin.weChat.vo;

import java.util.Date;
import java.util.List;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**统计分析列表显示类*/
public class StatisticAnalysisVo extends BaseDomain {
	/****************************用于查询**************************************************/
	/**所属组织结构*/
	private Organization org;
	/**开始时间*/
	private Date startDate;
	/**结束时间*/
	private Date endDate;
	/**公众号名*/
	private String weChatUserId;
	/****************************用于显示**************************************************/
	/**公众号名*/
	private String weChatUserName;
	/**每个群组的统计信息列表*/
	private List<StatisticAnalysisDetailVo> statisticList;
	
	/**事件大类名称*/
	private String issueTypeDomainName;
	/**事件小类名称*/
	private String issueTypeName;
	
	/**事件大类idlist*/
	private List<IssueTypeDomain> issueTypeDomainIds;
	
	/**来源方式*/
	private PropertyDict sourceKind;

	/**事件类型Id(大类)*/
	private Long issueTypeDomainId;

	private StatisticAnalysisDetailVo statisticAnalysisDetailVo;
	
	public List<StatisticAnalysisDetailVo> getStatisticList() {
		return statisticList;
	}

	public void setStatisticList(List<StatisticAnalysisDetailVo> statisticList) {
		this.statisticList = statisticList;
	}

	public String getWeChatUserName() {
		return weChatUserName;
	}

	public void setWeChatUserName(String weChatUserName) {
		this.weChatUserName = weChatUserName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public String getWeChatUserId() {
		return weChatUserId;
	}

	public void setWeChatUserId(String weChatUserId) {
		this.weChatUserId = weChatUserId;
	}

	public String getIssueTypeDomainName() {
		return issueTypeDomainName;
	}

	public void setIssueTypeDomainName(String issueTypeDomainName) {
		this.issueTypeDomainName = issueTypeDomainName;
	}

	public String getIssueTypeName() {
		return issueTypeName;
	}

	public void setIssueTypeName(String issueTypeName) {
		this.issueTypeName = issueTypeName;
	}

	public List<IssueTypeDomain> getIssueTypeDomainIds() {
		return issueTypeDomainIds;
	}

	public void setIssueTypeDomainIds(List<IssueTypeDomain> issueTypeDomainIds) {
		this.issueTypeDomainIds = issueTypeDomainIds;
	}

	public PropertyDict getSourceKind() {
		return sourceKind;
	}

	public void setSourceKind(PropertyDict sourceKind) {
		this.sourceKind = sourceKind;
	}

	public Long getIssueTypeDomainId() {
		return issueTypeDomainId;
	}

	public void setIssueTypeDomainId(Long issueTypeDomainId) {
		this.issueTypeDomainId = issueTypeDomainId;
	}

	public StatisticAnalysisDetailVo getStatisticAnalysisDetailVo() {
		return statisticAnalysisDetailVo;
	}

	public void setStatisticAnalysisDetailVo(StatisticAnalysisDetailVo statisticAnalysisDetailVo) {
		this.statisticAnalysisDetailVo = statisticAnalysisDetailVo;
	}

}
