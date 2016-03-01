package com.tianque.issueAbutmentJoint.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * @Description: 事件对接 事件类
 * @author zhangyouwei@hztian.com
 * @date: 2014-7-22 上午10:55:38
 */
public class IssueJoint extends BaseDomain {
	/** 服务单号 */
	private String serialNumber;
	/** 主题 */
	private String subject;
	/** 主要当事人 */
	private String maincharacters;
	/** 主要当事人手机 */
	private String mobile;
	/** 主要当事人电话 */
	private String telephone;
	/** 发生网格 */
	private Organization occurOrg;
	/** 发生网格InternalCode */
	private String occurOrgInternalCode;
	/** 创建网格 */
	private Organization createOrg;
	/** 创建网格InternalCode */
	private String createOrgInternalCode;
	/** 最后处理部门 */
	private Organization lastOrg;
	/** 最后操作网格InternalCode */
	private String lastOrgInternalCode;
	/** 事件性质 */
	private PropertyDict issueKind;
	/** 事件类别（大类） */
	private PropertyDict issueJointType;
	/** 事件类别（子类） */
	private PropertyDict issueJointTypeSub;
	/** 涉及人数 */
	private Integer relatePeopleCount;
	/** 最后处理用户 */
	private String lastUsername;
	/** 发生地点 */
	private String occurLocation;
	/** 状态 */
	private Integer status;
	/** 发生时间 */
	private Date occurDate;
	/** 时间发生的小时 */
	private String hours;
	/** 时间发生的分钟 */
	private String minute;
	/** 事件情况 */
	private String issueContent;
	/** 是否重点场所 */
	private Long importantPlace;
	/** 冗余字段 【由于需要导入将这些字段冗余，赋值给log和step】 */
	/** 反馈时间 */
	private Date feedbackTime;
	/** 最后处理时间 */
	private Date lastDealTime;
	/** 承办人 */
	private String dealUserName;
	/** 处理人 电话 **/
	private String dealMobile;
	/** 处理意见 */
	private String dealContent;
	/** 冗余字段导入时间 */
	private Date impDate;
	/** 冗余字段关联id（对方事件的id） */
	private String associateId;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMaincharacters() {
		return maincharacters;
	}

	public void setMaincharacters(String maincharacters) {
		this.maincharacters = maincharacters;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Organization getOccurOrg() {
		return occurOrg;
	}

	public void setOccurOrg(Organization occurOrg) {
		this.occurOrg = occurOrg;
	}

	public String getOccurOrgInternalCode() {
		return occurOrgInternalCode;
	}

	public void setOccurOrgInternalCode(String occurOrgInternalCode) {
		this.occurOrgInternalCode = occurOrgInternalCode;
	}

	public Organization getCreateOrg() {
		return createOrg;
	}

	public void setCreateOrg(Organization createOrg) {
		this.createOrg = createOrg;
	}

	public String getCreateOrgInternalCode() {
		return createOrgInternalCode;
	}

	public void setCreateOrgInternalCode(String createOrgInternalCode) {
		this.createOrgInternalCode = createOrgInternalCode;
	}

	public Organization getLastOrg() {
		return lastOrg;
	}

	public void setLastOrg(Organization lastOrg) {
		this.lastOrg = lastOrg;
	}

	public String getLastOrgInternalCode() {
		return lastOrgInternalCode;
	}

	public void setLastOrgInternalCode(String lastOrgInternalCode) {
		this.lastOrgInternalCode = lastOrgInternalCode;
	}

	public PropertyDict getIssueKind() {
		return issueKind;
	}

	public void setIssueKind(PropertyDict issueKind) {
		this.issueKind = issueKind;
	}

	public PropertyDict getIssueJointType() {
		return issueJointType;
	}

	public void setIssueJointType(PropertyDict issueJointType) {
		this.issueJointType = issueJointType;
	}

	public PropertyDict getIssueJointTypeSub() {
		return issueJointTypeSub;
	}

	public void setIssueJointTypeSub(PropertyDict issueJointTypeSub) {
		this.issueJointTypeSub = issueJointTypeSub;
	}

	public Integer getRelatePeopleCount() {
		return relatePeopleCount;
	}

	public void setRelatePeopleCount(Integer relatePeopleCount) {
		this.relatePeopleCount = relatePeopleCount;
	}

	public String getLastUsername() {
		return lastUsername;
	}

	public void setLastUsername(String lastUsername) {
		this.lastUsername = lastUsername;
	}

	public String getOccurLocation() {
		return occurLocation;
	}

	public void setOccurLocation(String occurLocation) {
		this.occurLocation = occurLocation;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOccurDateString() {
		if (null == occurDate) {
			return "";
		}
		SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd");
		String nowTime = sformat.format(this.occurDate);
		if (hours != null && !"".equals(hours)) {
			nowTime = nowTime + " " + hours + ":" + minute;
		}
		return nowTime;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getOccurDate() {
		return occurDate;
	}

	public void setOccurDate(Date occurDate) {
		this.occurDate = occurDate;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getIssueContent() {
		return issueContent;
	}

	public void setIssueContent(String issueContent) {
		this.issueContent = issueContent;
	}

	public Long getImportantPlace() {
		return importantPlace;
	}

	public void setImportantPlace(Long importantPlace) {
		this.importantPlace = importantPlace;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getLastDealTime() {
		return lastDealTime;
	}

	public void setLastDealTime(Date lastDealTime) {
		this.lastDealTime = lastDealTime;
	}

	public String getDealContent() {
		return dealContent;
	}

	public void setDealContent(String dealContent) {
		this.dealContent = dealContent;
	}

	public String getDealUserName() {
		return dealUserName;
	}

	public void setDealUserName(String dealUserName) {
		this.dealUserName = dealUserName;
	}

	public String getDealMobile() {
		return dealMobile;
	}

	public void setDealMobile(String dealMobile) {
		this.dealMobile = dealMobile;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getImpDate() {
		return impDate;
	}

	public void setImpDate(Date impDate) {
		this.impDate = impDate;
	}

	public String getAssociateId() {
		return associateId;
	}

	public void setAssociateId(String associateId) {
		this.associateId = associateId;
	}

}
