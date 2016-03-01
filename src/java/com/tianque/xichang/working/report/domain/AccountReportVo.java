package com.tianque.xichang.working.report.domain;

public class AccountReportVo {
	/** 所属网格 */
	private Long orgId;
	/** 网格内置编码 */
	private String orgInternalCode;
	/** 统计的年份 */
	private Integer reportYear;
	/** 统计的月份 */
	private Integer reportMonth;
	/** 台账类型 */
	private String accountType;
	/** 报表类型 */
	private Integer reportType;

	public AccountReportVo() {
		super();
	}

	public AccountReportVo(Long orgId, Integer year, Integer month, String accountType, Integer reportType) {
		this.orgId = orgId;
		this.reportYear = year;
		this.reportMonth = month;
		this.accountType = accountType;
		this.reportType = reportType;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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

}
