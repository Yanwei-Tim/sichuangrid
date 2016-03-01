package com.tianque.xichang.working.statisticsAccountTimeout.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * 时限考核成绩实体类
 * */
public class StatisticsAccountTimeout extends BaseDomain {

	/** 组织机构 */
	private Organization organization;
	/** 统计的年份 */
	private Integer year;
	/** 统计的年份 */
	private Integer month;
	/** 统计的组织机构父id */
	private Long parentOrgId;
	/** 办理扣分 */
	private Double doing;
	/** 办结扣分 */
	private Double done;
	/** 流转扣分 */
	private Double transfer;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Long getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public Double getDoing() {
		return doing;
	}

	public void setDoing(Double doing) {
		this.doing = doing;
	}

	public Double getDone() {
		return done;
	}

	public void setDone(Double done) {
		this.done = done;
	}

	public Double getTransfer() {
		return transfer;
	}

	public void setTransfer(Double transfer) {
		this.transfer = transfer;
	}

}
