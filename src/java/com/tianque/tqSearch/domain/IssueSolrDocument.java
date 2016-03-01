package com.tianque.tqSearch.domain;

import java.lang.reflect.Field;
import java.util.Date;

import org.apache.solr.common.SolrInputDocument;

import com.tianque.tqSearch.constant.TqSolrSearchType;

public class IssueSolrDocument extends BaseSolrDocument {
	private Long issueId;
	private Long issueStepId;
	private Integer publicltycass = 0;
	private Integer superviseLevel = -1;
	private String subject;
	private Integer status;
	private Integer stateCode;
	private Integer delayState;
	private Date occurDate;
	private Long targetOrg;
	private Long createOrg;
	private Date dealTime;
	private Long sourceKind;
	private Long evaluateType;
	private Long currentOrg;
	private String currentOrginternalCode;
	private Long currentOrgLevel;
	private Long currentOrgFunctionalType;
	private Long occurOrg;
	private Long issueTypeId;
	private Long issueTypedDomainId;
	private Long emergencyLevel;
	private Long createOrgLevel;
	private String createOrginternalCode;
	private Long createOrgFunctionalOrgType;
	private Long targetOrgLevel;
	private String targetInternalCode;
	private Long targetOrgFunctionalOrgType;
	private Integer submit;
	private Integer assign;
	private Date endDate;
	private Integer issueEvaluate = 0;// 是否评价
	private String occurLocation;
	private Long issueKind;
	private Integer relatePeopleCount;
	private String serialNumber;
	private Integer isStayStep;
	private Long urgent = 0L;
	private Long eventState;

	public IssueSolrDocument() {
		setType(TqSolrSearchType.ISSUE_TYPE);
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public Long getIssueStepId() {
		return issueStepId;
	}

	public void setIssueStepId(Long issueStepId) {
		this.issueStepId = issueStepId;
	}

	@Override
	public SolrInputDocument getSolrInputDocument() throws Exception {
		SolrInputDocument document = new SolrInputDocument();
		setDocumentId(getType() + "_" + getIssueStepId());
		Class clazz = IssueSolrDocument.class;
		document.addField("id", getDocumentId());
		for (Field field : clazz.getDeclaredFields()) {
			document.addField(
					field.getName(),
					clazz.getMethod(
							GET + field.getName().substring(0, 1).toUpperCase()
									+ field.getName().substring(1))
							.invoke(this));
		}
		return document;
	}

	public Integer getPublicltycass() {
		return publicltycass;
	}

	public void setPublicltycass(Integer publicltycass) {
		this.publicltycass = publicltycass;
	}

	public Integer getSuperviseLevel() {
		return superviseLevel;
	}

	public void setSuperviseLevel(Integer superviseLevel) {
		this.superviseLevel = superviseLevel;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public Integer getDelayState() {
		return delayState;
	}

	public void setDelayState(Integer delayState) {
		this.delayState = delayState;
	}

	public Date getOccurDate() {
		return occurDate;
	}

	public void setOccurDate(Date occurDate) {
		this.occurDate = occurDate;
	}

	public Long getTargetOrg() {
		return targetOrg;
	}

	public void setTargetOrg(Long targetOrg) {
		this.targetOrg = targetOrg;
	}

	public Long getCreateOrg() {
		return createOrg;
	}

	public void setCreateOrg(Long createOrg) {
		this.createOrg = createOrg;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public Long getSourceKind() {
		return sourceKind;
	}

	public void setSourceKind(Long sourceKind) {
		this.sourceKind = sourceKind;
	}

	public Long getEvaluateType() {
		return evaluateType;
	}

	public void setEvaluateType(Long evaluateType) {
		this.evaluateType = evaluateType;
	}

	public Long getCurrentOrg() {
		return currentOrg;
	}

	public void setCurrentOrg(Long currentOrg) {
		this.currentOrg = currentOrg;
	}

	public Long getOccurOrg() {
		return occurOrg;
	}

	public void setOccurOrg(Long occurOrg) {
		this.occurOrg = occurOrg;
	}

	public Long getIssueTypeId() {
		return issueTypeId;
	}

	public void setIssueTypeId(Long issueTypeId) {
		this.issueTypeId = issueTypeId;
	}

	public Long getIssueTypedDomainId() {
		return issueTypedDomainId;
	}

	public void setIssueTypedDomainId(Long issueTypedDomainId) {
		this.issueTypedDomainId = issueTypedDomainId;
	}

	public Long getEmergencyLevel() {
		return emergencyLevel;
	}

	public void setEmergencyLevel(Long emergencyLevel) {
		this.emergencyLevel = emergencyLevel;
	}

	public Long getCreateOrgLevel() {
		return createOrgLevel;
	}

	public void setCreateOrgLevel(Long createOrgLevel) {
		this.createOrgLevel = createOrgLevel;
	}

	public String getCreateOrginternalCode() {
		return createOrginternalCode;
	}

	public void setCreateOrginternalCode(String createOrginternalCode) {
		this.createOrginternalCode = createOrginternalCode;
	}

	public Integer getSubmit() {
		return submit;
	}

	public void setSubmit(Integer submit) {
		this.submit = submit;
	}

	public Integer getAssign() {
		return assign;
	}

	public void setAssign(Integer assign) {
		this.assign = assign;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getIssueEvaluate() {
		return issueEvaluate;
	}

	public void setIssueEvaluate(Integer issueEvaluate) {
		this.issueEvaluate = issueEvaluate;
	}

	public String getOccurLocation() {
		return occurLocation;
	}

	public void setOccurLocation(String occurLocation) {
		this.occurLocation = occurLocation;
	}

	public Long getIssueKind() {
		return issueKind;
	}

	public void setIssueKind(Long issueKind) {
		this.issueKind = issueKind;
	}

	public Integer getRelatePeopleCount() {
		return relatePeopleCount;
	}

	public void setRelatePeopleCount(Integer relatePeopleCount) {
		this.relatePeopleCount = relatePeopleCount;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public void setCreateOrgFunctionalOrgType(Long createOrgFunctionalOrgType) {
		this.createOrgFunctionalOrgType = createOrgFunctionalOrgType;
	}

	public Long getCreateOrgFunctionalOrgType() {
		return createOrgFunctionalOrgType;
	}

	public Long getTargetOrgLevel() {
		return targetOrgLevel;
	}

	public void setTargetOrgLevel(Long targetOrgLevel) {
		this.targetOrgLevel = targetOrgLevel;
	}

	public String getTargetInternalCode() {
		return targetInternalCode;
	}

	public void setTargetInternalCode(String targetInternalCode) {
		this.targetInternalCode = targetInternalCode;
	}

	public Long getTargetOrgFunctionalOrgType() {
		return targetOrgFunctionalOrgType;
	}

	public void setTargetOrgFunctionalOrgType(Long targetOrgFunctionalOrgType) {
		this.targetOrgFunctionalOrgType = targetOrgFunctionalOrgType;
	}

	public Integer getIsStayStep() {
		return isStayStep;
	}

	public void setIsStayStep(Integer isStayStep) {
		this.isStayStep = isStayStep;
	}

	public void setUrgent(Long urgent) {
		this.urgent = urgent;
	}

	public Long getUrgent() {
		return urgent;
	}

	public void setEventState(Long eventState) {
		this.eventState = eventState;
	}

	public Long getEventState() {
		return eventState;
	}

	public String getCurrentOrginternalCode() {
		return currentOrginternalCode;
	}

	public void setCurrentOrginternalCode(String currentOrginternalCode) {
		this.currentOrginternalCode = currentOrginternalCode;
	}

	public Long getCurrentOrgLevel() {
		return currentOrgLevel;
	}

	public void setCurrentOrgLevel(Long currentOrgLevel) {
		this.currentOrgLevel = currentOrgLevel;
	}

	public Long getCurrentOrgFunctionalType() {
		return currentOrgFunctionalType;
	}

	public void setCurrentOrgFunctionalType(Long currentOrgFunctionalType) {
		this.currentOrgFunctionalType = currentOrgFunctionalType;
	}

}
