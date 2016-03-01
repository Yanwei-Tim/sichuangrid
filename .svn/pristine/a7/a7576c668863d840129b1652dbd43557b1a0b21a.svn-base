package com.tianque.fourTeams.fourTeamsIssue.domain;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.GisInfo;
import com.tianque.domain.IssueType;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * 事件
 */
public class FourTeamsIssue extends BaseDomain {

	/** 服务单号 */
	private String serialNumber;
	/** 主题 */
	private String subject;
	/** 发生网格 */
	private Organization occurOrg;
	/** 发生网格InternalCode */
	private String occurOrgInternalCode;
	/** 创建网格 */
	private Organization createOrg;
	/** 创建网格InternalCode */
	private String createOrgInternalCode;
	/** 最后操作网格 */
	private Organization lastOrg;
	/** 最后操作网格InternalCode */
	private String lastOrgInternalCode;
	/** 最后操作用户 */
	private String lastUsername;
	/** 当前部门org */
	private Organization currentOrg;
	/** 事件性质 */
	private PropertyDict issueKind;
	/** 是否重大事件 */
	private Boolean important;
	/** 来源人 */
	private String sourcePerson;
	/** 来源方式 */
	private PropertyDict sourceKind;
	/** 来源手机号码 */
	private String sourceMobile;
	/** 发生时间 */
	private Date occurDate;
	/** 涉及人数 */
	private Integer relatePeopleCount;
	/** 发生地点 */
	private String occurLocation;
	/** 主要人物 */
	private String mainCharacters;
	/** 事件情况 */
	private String issueContent;
	/** 备注 */
	private String remark;
	/** 显示样式 */
	private Integer displayStyle;
	/** 状态 */
	private Integer status;
	/** 加急 */
	private Long urgent;
	/** 类别 */
	private List<IssueType> issueTypes;
	/** 结束时间 */
	private String endDate;
	/** 是否重大事件 */
	private Boolean isEmergency;
	/**
	 * 当前处理步骤，如果完全结束后，重置为空
	 */
	private FourTeamsIssueOperateStep currentStep;
	/** 经度 */
	private Double lon;
	/** 纬度 */
	private Double lat;

	private String gisSearchType;
	private int giscountNum;
	private int gisSearchState;
	private GisInfo gisInfo;
	private String keyPersonType;

	/** 事件类型 */
	private String domainname;

	public String getDomainname() {
		return domainname;
	}

	public void setDomainname(String domainname) {
		this.domainname = domainname;
	}

	public String getKeyPersonType() {
		return keyPersonType;
	}

	public void setKeyPersonType(String keyPersonType) {
		this.keyPersonType = keyPersonType;
	}

	public GisInfo getGisInfo() {
		return gisInfo;
	}

	public void setGisInfo(GisInfo gisInfo) {
		this.gisInfo = gisInfo;
	}

	public int getGisSearchState() {
		return gisSearchState;
	}

	public void setGisSearchState(int gisSearchState) {
		this.gisSearchState = gisSearchState;
	}

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

	public String getLastUsername() {
		return lastUsername;
	}

	public void setLastUsername(String lastUsername) {
		this.lastUsername = lastUsername;
	}

	public PropertyDict getIssueKind() {
		return issueKind;
	}

	public void setIssueKind(PropertyDict issueKind) {
		this.issueKind = issueKind;
	}

	public Boolean getImportant() {
		return important;
	}

	public void setImportant(Boolean important) {
		this.important = important;
	}

	public String getSourcePerson() {
		return sourcePerson;
	}

	public void setSourcePerson(String sourcePerson) {
		this.sourcePerson = sourcePerson;
	}

	public PropertyDict getSourceKind() {
		return sourceKind;
	}

	public void setSourceKind(PropertyDict sourceKind) {
		this.sourceKind = sourceKind;
	}

	public String getSourceMobile() {
		return sourceMobile;
	}

	public void setSourceMobile(String mobileNumber) {
		this.sourceMobile = mobileNumber;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getOccurDate() {
		return occurDate;
	}

	public void setOccurDate(Date occurDate) {
		this.occurDate = occurDate;
	}

	public Integer getRelatePeopleCount() {
		return relatePeopleCount;
	}

	public void setRelatePeopleCount(Integer referNumber) {
		this.relatePeopleCount = referNumber;
	}

	public String getOccurLocation() {
		return occurLocation;
	}

	public void setOccurLocation(String occurLocation) {
		this.occurLocation = occurLocation;
	}

	public String getMainCharacters() {
		return mainCharacters;
	}

	public void setMainCharacters(String mainCharacters) {
		this.mainCharacters = mainCharacters;
	}

	public String getIssueContent() {
		return issueContent;
	}

	public void setIssueContent(String issueContent) {
		this.issueContent = issueContent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getDisplayStyle() {
		return displayStyle;
	}

	public void setDisplayStyle(Integer displayStyle) {
		this.displayStyle = displayStyle;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getUrgent() {
		return urgent;
	}

	public void setUrgent(Long urgent) {
		this.urgent = urgent;
	}

	public List<IssueType> getIssueTypes() {
		return issueTypes;
	}

	public void setIssueTypes(List<IssueType> issueTypes) {
		this.issueTypes = issueTypes;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Boolean getIsEmergency() {
		return isEmergency;
	}

	public void setIsEmergency(Boolean isEmergency) {
		this.isEmergency = isEmergency;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public FourTeamsIssueOperateStep getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(FourTeamsIssueOperateStep currentStep) {
		this.currentStep = currentStep;
	}

	public Organization getCurrentOrg() {
		return currentOrg;
	}

	public void setCurrentOrg(Organization currentOrg) {
		this.currentOrg = currentOrg;
	}

	public String getGisSearchType() {
		return gisSearchType;
	}

	public void setGisSearchType(String gisSearchType) {
		this.gisSearchType = gisSearchType;
	}

	public int getGiscountNum() {
		return giscountNum;
	}

	public void setGiscountNum(int giscountNum) {
		this.giscountNum = giscountNum;
	}

}
