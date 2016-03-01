package com.tianque.working.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

public class DailyLog extends BaseDomain {

	/** 台帐名称 */
	private String name;
	/** 台帐目录 */
	private DailyDirectory dailyDirectory;
	/** 年度目录 */
	private DailyYear dailyYear;
	/** 地点 */
	private String site;
	/** 召集人 */
	private String convenor;
	/** 参加单位 */
	private String attendUnit;
	/** 主题 */
	private String theme;
	/** 处理时间 */
	private Date dealDate;
	private String remark;
	/** 网格内置编码 */
	private String orgInternalCode;
	/** 所属网格 */
	private Organization organization;
	/** 文件号 */
	private String fileNumber;
	/** 发文单位 */
	private String fileUnit;

	private List<DailyLogAttachFile> dailyLogAttachFile = new ArrayList<DailyLogAttachFile>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DailyDirectory getDailyDirectory() {
		return dailyDirectory;
	}

	public void setDailyDirectory(DailyDirectory dailyDirectory) {
		this.dailyDirectory = dailyDirectory;
	}

	public String getConvenor() {
		return convenor;
	}

	public void setConvenor(String convenor) {
		this.convenor = convenor;
	}

	public String getAttendUnit() {
		return attendUnit;
	}

	public void setAttendUnit(String attendUnit) {
		this.attendUnit = attendUnit;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}

	public String getFileUnit() {
		return fileUnit;
	}

	public void setFileUnit(String fileUnit) {
		this.fileUnit = fileUnit;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getDealDate() {
		return dealDate;
	}

	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public DailyYear getDailyYear() {
		return dailyYear;
	}

	public void setDailyYear(DailyYear dailyYear) {
		this.dailyYear = dailyYear;
	}

	public List<DailyLogAttachFile> getDailyLogAttachFile() {
		return dailyLogAttachFile;
	}

	public void setDailyLogAttachFile(List<DailyLogAttachFile> dailyLogAttachFile) {
		this.dailyLogAttachFile = dailyLogAttachFile;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
