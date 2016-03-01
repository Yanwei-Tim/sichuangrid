package com.tianque.domain.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SearchDisputEsexamine implements Serializable {
	/** 信息填写日期 */
	private Long seachReportTimeStart;
	private Long seachReportTimeEnd;
	private Long dailyDirectoryId;// 台账目录
	private Long orgId;// 网格id

	public Long getDailyDirectoryId() {
		return dailyDirectoryId;
	}

	public void setDailyDirectoryId(Long dailyDirectoryId) {
		this.dailyDirectoryId = dailyDirectoryId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getSeachReportTimeStart() {
		return seachReportTimeStart;
	}

	public void setSeachReportTimeStart(Long seachReportTimeStart) {
		this.seachReportTimeStart = seachReportTimeStart;
	}

	public Long getSeachReportTimeEnd() {
		return seachReportTimeEnd;
	}

	public void setSeachReportTimeEnd(Long seachReportTimeEnd) {
		this.seachReportTimeEnd = seachReportTimeEnd;
	}
}
