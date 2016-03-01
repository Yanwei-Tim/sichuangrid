package com.tianque.working.domain;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

public class SocietySecurity extends BaseDomain {
	private String content;
	private Organization organization;
	private String orgInternalCode;
	private Long dailyDirectoryId;
	private List<DailyAttachFiles> dailyAttachFiles = new ArrayList<DailyAttachFiles>();

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
}