package com.tianque.plugin.weChat.domain.evaluationIssueHandle;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * 评价事件处理
 */
public class EvaluationIssueHandle extends BaseDomain {
	
	/**评价人openId*/
    private String openId;
    /**信息所属组织机构*/
    private Organization org;
    /**微信公众号*/
    private String toUserName;
	/** 事件服务单号 */
	private String serialNumber;
	/** 评价人 */
	private String scorePerson;
	/** 评价星数 */
	private Long scoreStarNumber;
	/** 内容 */
	private String content;
	/** 事件名称 */
	private String issueName;
	/** 结案时间 */
	private Date closeCaseDate;
	/**搜索用，开始与结束时间*/
	private Date startCloseCaseDate;
	private Date endCloseCaseDate;


	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getScorePerson() {
		return scorePerson;
	}

	public void setScorePerson(String scorePerson) {
		this.scorePerson = scorePerson;
	}

	public Long getScoreStarNumber() {
		return scoreStarNumber;
	}

	public void setScoreStarNumber(Long scoreStarNumber) {
		this.scoreStarNumber = scoreStarNumber;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getIssueName() {
		return issueName;
	}

	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCloseCaseDate() {
		return closeCaseDate;
	}

	public void setCloseCaseDate(Date closeCaseDate) {
		this.closeCaseDate = closeCaseDate;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getStartCloseCaseDate() {
		return startCloseCaseDate;
	}

	public void setStartCloseCaseDate(Date startCloseCaseDate) {
		this.startCloseCaseDate = startCloseCaseDate;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getEndCloseCaseDate() {
		return endCloseCaseDate;
	}

	public void setEndCloseCaseDate(Date endCloseCaseDate) {
		this.endCloseCaseDate = endCloseCaseDate;
	}

	
}
