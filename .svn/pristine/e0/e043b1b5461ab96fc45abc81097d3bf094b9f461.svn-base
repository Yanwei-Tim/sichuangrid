package com.tianque.working.domain;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts2.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class ReportWorkingRecord extends WorkingRecord {
	private static Logger logger = LoggerFactory.getLogger(ReportWorkingRecord.class);
	/** 制表人 */
	private String lister;
	/** 签发人 */
	private String dealPerson;
	/** 报表时间 */
	private long reportTime;

	public ReportWorkingRecord() {

	}

	public ReportWorkingRecord(ReportWorkingRecord report) {
		try {
			PropertyUtils.copyProperties(this, report);
			String content = JSONUtil.serialize(report);
			setContent(content);
		} catch (Exception e) {
			logger.error("构造器错误：", e);
		}
	}

	public String getLister() {
		return lister;
	}

	public void setLister(String lister) {
		this.lister = lister;
	}

	public String getDealPerson() {
		return dealPerson;
	}

	public void setDealPerson(String dealPerson) {
		this.dealPerson = dealPerson;
	}

	public long getReportTime() {
		return reportTime;
	}

	public void setReportTime(long reportTime) {
		this.reportTime = reportTime;
	}
}
