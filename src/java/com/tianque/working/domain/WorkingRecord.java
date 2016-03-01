package com.tianque.working.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class WorkingRecord extends BaseDomain {
	/** 网格内置编码 */
	private String orgInternalCode;
	/** 所属网格 */
	private Organization organization;
	/** 台帐名称 */
	private String name;
	/** 台帐名称简拼 **/
	private String simplePinyin;
	/** 台帐名称全拼 **/
	private String fullPinyin;
	/** 台账主题 */
	private String subject;
	/** 工作台账描述 */
	private String content;
	/** 发生地点 */
	private String proceedSite;
	/** 参与人员 */
	private String participant;
	/** 台帐目录 */
	private DailyDirectory dailyDirectory;
	/** 年度目录 */
	private DailyYear dailyYear;
	/** 处理时间 */
	private Date dealDate;
	/** 台帐类型 */
	private PropertyDict dailyLogType;
	/** 上报状态 */
	private PropertyDict submitState;
	/** 上报时间 */
	private Date submitTime;
	/** 回退时间 */
	private Date backTime;
	/** 文件号 */
	private String fileNo;
	/** 工作单位 */
	private String workingUnit;
	/** 是否过期录入 */
	private Long expiredEntering;

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

	@JSON(format = "yyyy-MM-dd")
	public Date getDealDate() {
		return dealDate;
	}

	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
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

	public void setDailyLogAttachFile(
			List<DailyLogAttachFile> dailyLogAttachFile) {
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

	public PropertyDict getDailyLogType() {
		return dailyLogType;
	}

	public void setDailyLogType(PropertyDict dailyLogType) {
		this.dailyLogType = dailyLogType;
	}

	public PropertyDict getSubmitState() {
		return submitState;
	}

	public void setSubmitState(PropertyDict submitState) {
		this.submitState = submitState;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Date getBackTime() {
		return backTime;
	}

	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getProceedSite() {
		return proceedSite;
	}

	public void setProceedSite(String proceedSite) {
		this.proceedSite = proceedSite;
	}

	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getWorkingUnit() {
		return workingUnit;
	}

	public void setWorkingUnit(String workingUnit) {
		this.workingUnit = workingUnit;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		this.simplePinyin = simplePinyin;
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		this.fullPinyin = fullPinyin;
	}

	public Long getExpiredEntering() {
		return expiredEntering;
	}

	public void setExpiredEntering(Long expiredEntering) {
		this.expiredEntering = expiredEntering;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(super.getId(),
				this.orgInternalCode, null);
	}
}
