package com.tianque.xichang.working.flow.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;
import com.tianque.xichang.working.flow.constant.FinishType;

public class AccountLogs extends BaseDomain {

	/** 台账id(ACCOUNTID) */
	private Long accountId;
	/** 日志时间(LOGDATE) */
	private Date logDate;
	/** 处理时间(dealDate) */
	private Date dealDate;
	/** 台账类型(ACCOUNTTYPE) */
	private String accountType;
	/** 承办人(dealUser) */
	private String dealUser;
	/** 工作内容(CONTENT) */
	private String content;
	/** 地点 */
	private String site;
	/** 当事人意见 */
	private String opinion;
	/** 是否办结 0 否 1 是(ISFINISH) */
	private Boolean isFinish;
	/** 办结时间(FINISHDATE) */
	private Date finishDate;
	/** 办结方式(FINISHTYPE) */
	private Integer finishType;
	/** 办结结果(FINISHCONTENT) */
	private String finishContent;
	private Organization dealOrganization;
	private String finishTypeName;

	/** 程序性办结或实质性办结 */
	private Integer essenceAndProgram;

	/** 目标处理部门 */
	private Organization targetOrg;
	/** 是否系统级别日志，例如：新增时注册的日志，就是系统级别的日志 */
	private Boolean isSysOperate;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getDealDate() {
		return dealDate;
	}

	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getDealUser() {
		return dealUser;
	}

	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Boolean getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(Boolean isFinish) {
		this.isFinish = isFinish;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public String getFinishContent() {
		return finishContent;
	}

	public void setFinishContent(String finishContent) {
		this.finishContent = finishContent;
	}

	public Integer getFinishType() {
		return finishType;
	}

	public void setFinishType(Integer finishType) {
		this.finishType = finishType;
	}

	public Organization getDealOrganization() {
		return dealOrganization;
	}

	public void setDealOrganization(Organization dealOrganization) {
		this.dealOrganization = dealOrganization;
	}

	public String getFinishTypeName() {
		return FinishType.FINISHTYPE.get(getFinishType());
	}

	public void setFinishTypeName(String finishTypeName) {
		this.finishTypeName = finishTypeName;
	}

	public void setIsSysOperate(Boolean isSysOperate) {
		this.isSysOperate = isSysOperate;
	}

	public Boolean getIsSysOperate() {
		return isSysOperate;
	}

	public void setTargetOrg(Organization targetOrg) {
		this.targetOrg = targetOrg;
	}

	public Organization getTargetOrg() {
		return targetOrg;
	}

	public void setEssenceAndProgram(Integer essenceAndProgram) {
		this.essenceAndProgram = essenceAndProgram;
	}

	public Integer getEssenceAndProgram() {
		return essenceAndProgram;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(), null, null);
	}
}
