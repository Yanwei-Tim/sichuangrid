package com.tianque.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class StatAnalyseIssue implements Serializable {
	private Organization organization;
	/** 操作用户所在网格InternalCode */
	private String orgInternalCode;
	/** 查询时间 */
	private Date happenDate;
	/** 状态 */
	private String stateName;
	/** 总数 */
	private Integer count;
	/** 比例 */
	private String ratio;
	/** 状态 */
	private Long state;
	/** 结束时间 */
	private Date endDate;
	/** 开始时间 */
	private Date startDate;

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

	public StatAnalyseIssue(Long state, String stateName, Integer count, String ratio,
			Date startDate, Date endDate) {
		this.state = state;
		this.stateName = stateName;
		this.count = count;
		this.ratio = ratio;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public StatAnalyseIssue() {

	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
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

	public Date getHappenDate() {
		return happenDate;
	}

	public void setHappenDate(Date happenDate) {
		this.happenDate = happenDate;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
