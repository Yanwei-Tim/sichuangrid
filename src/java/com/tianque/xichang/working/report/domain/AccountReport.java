package com.tianque.xichang.working.report.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

public class AccountReport extends BaseDomain {
	/** 所属网格 */
	private Organization organization;
	/** 网格内置编码 */
	private String orgInternalCode;
	/** 统计的年份 */
	private Integer reportYear;
	/** 统计的月份 */
	private Integer reportMonth;
	/** 台账明细 */
	private String content;
	private Object contentObject;
	/** 台账类型 */
	private String accountType;
	/** 报表类型 */
	private Integer reportType;

	public AccountReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountReport(Organization organization, String orgInternalCode, Integer reportYear, Integer reportMonth,
			String content, String accountType, Integer reportType) {
		super();
		this.organization = organization;
		this.orgInternalCode = orgInternalCode;
		this.reportYear = reportYear;
		this.reportMonth = reportMonth;
		this.content = content;
		this.accountType = accountType;
		this.reportType = reportType;
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

	public Integer getReportYear() {
		return reportYear;
	}

	public void setReportYear(Integer reportYear) {
		this.reportYear = reportYear;
	}

	public Integer getReportMonth() {
		return reportMonth;
	}

	public void setReportMonth(Integer reportMonth) {
		this.reportMonth = reportMonth;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Integer getReportType() {
		return reportType;
	}

	public void setReportType(Integer reportType) {
		this.reportType = reportType;
	}

	public Object getContentObject() {
		return contentObject;
	}

	public void setContentObject(Object contentObject) {
		this.contentObject = contentObject;
	}

}
