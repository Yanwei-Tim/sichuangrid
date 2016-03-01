package com.tianque.qinYangIssue.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.domain.IssueType;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * @Description: 青羊事件对接 事件类
 * @author zhangyouwei@hztianque.com
 * @date: 2014-12-14 下午02:48:22
 */
public class IssueJointTemp {
	/** 唯一的固定的３２位的字符型ID */
	private String id;
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
	/** 事件性质 */
	private PropertyDict issueKind;
	/** 事件类别（大类） */
	private IssueTypeDomain issueJointType;
	/** 事件类别（子类） */
	private IssueType issueJointTypeSub;
	/** 涉及人数 */
	private Integer relatePeopleCount;
	/** 发生地点 */
	private String occurLocation;
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
	/** 数据处理状态（默认0为刚刚进入社管的状态 0未处理1处理成功2数据异常3处理失败） */
	private Integer state;
	/** 附件名称 */
	private String attachFileName;
	/** 附件的uuid */
	private String attachFileName_uuid;
	/** 新增成功后的返回的事件的id */
	private Long issueId;
	/** 对外开放的事件的处理状态 */
	private Long statusCode;
	/** 最后处理时间 */
	private Date lastDealTime;
	/** 状态（事件的状态）冗余 */
	private Integer status;
	/** 最后处理用户 */
	private String lastUsername;
	/** 最后处理部门 */
	private Organization lastOrg;
	/** 冗余字段 【由于需要导入将这些字段冗余，赋值给log和step】 */
	/** 反馈时间 */
	private Date feedbackTime;
	/** 数据来源(区分青阳和攀枝花) */
	private String data_source;
	private String createUser;
	private Date createDate;
	private String updateUser;
	private Date updateDate;

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

	public PropertyDict getIssueKind() {
		return issueKind;
	}

	public void setIssueKind(PropertyDict issueKind) {
		this.issueKind = issueKind;
	}

	public IssueType getIssueJointTypeSub() {
		return issueJointTypeSub;
	}

	public void setIssueJointTypeSub(IssueType issueJointTypeSub) {
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

	public IssueTypeDomain getIssueJointType() {
		return issueJointType;
	}

	public void setIssueJointType(IssueTypeDomain issueJointType) {
		this.issueJointType = issueJointType;
	}

	public String getAttachFileName() {
		return attachFileName;
	}

	public void setAttachFileName(String attachFileName) {
		this.attachFileName = attachFileName;
	}

	public String getAttachFileName_uuid() {
		return attachFileName_uuid;
	}

	public void setAttachFileName_uuid(String attachFileName_uuid) {
		this.attachFileName_uuid = attachFileName_uuid;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public Long getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Long statusCode) {
		this.statusCode = statusCode;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getData_source() {
		return data_source;
	}

	public void setData_source(String data_source) {
		this.data_source = data_source;
	}
}
