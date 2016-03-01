package com.tianque.working.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

public class GridManagementNormal extends BaseDomain {
	private String work;
	private String content;
	private Date activityDate;
	private Organization organization;
	private String orgInternalCode;
	private Long dailyDirectoryId;
	/** 是否过期录入 */
	private Long expiredEntering;
	private List<DailyAttachFiles> dailyAttachFiles = new ArrayList<DailyAttachFiles>();

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
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

	public Long getDailyDirectoryId() {
		return dailyDirectoryId;
	}

	public void setDailyDirectoryId(Long dailyDirectoryId) {
		this.dailyDirectoryId = dailyDirectoryId;
	}

	public List<DailyAttachFiles> getDailyAttachFiles() {
		return dailyAttachFiles;
	}

	public void setDailyAttachFiles(List<DailyAttachFiles> dailyAttachFiles) {
		this.dailyAttachFiles = dailyAttachFiles;
	}

	public Long getExpiredEntering() {
		return expiredEntering;
	}

	public void setExpiredEntering(Long expiredEntering) {
		this.expiredEntering = expiredEntering;
	}
}