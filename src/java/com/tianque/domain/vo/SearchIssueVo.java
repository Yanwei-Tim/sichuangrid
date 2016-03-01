package com.tianque.domain.vo;

import java.util.Date;

import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class SearchIssueVo {

	/** 主题 */
	private String name;
	/** 状态 */
	private Integer status;
	/** 添加用户所在网格 */
	private Organization organization;

	/** 服务单号 */
	private String issueNo;
	/** 录入时间 从 */
	private Date inputFrom;
	/** 录入时间 到 */
	private Date inputEnd;
	/** 来源人 */
	private String fromPerson;
	/** 来源方式 */
	private PropertyDict sourceKind;
	/** 来源手机号码 */
	private String sourceMobile;
	/** 排序字段 **/
	private String sortField;
	/** 排序方式 **/
	private String order;
	/** 矛盾纠纷类型 **/
	private String issueTypeId;
	/** 服务办事类型 **/
	private String issueTypeDomainName;
	/** 事件描述 */
	private String issueContent;

	public String getIssueTypeDomainName() {
		return issueTypeDomainName;
	}

	public void setIssueTypeDomainName(String issueTypeDomainName) {
		this.issueTypeDomainName = issueTypeDomainName;
	}

	public String getIssueTypeId() {
		return issueTypeId;
	}

	public void setIssueTypeId(String issueTypeId) {
		this.issueTypeId = issueTypeId;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getIssueNo() {
		return issueNo;
	}

	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}

	public Date getInputFrom() {
		return inputFrom;
	}

	public void setInputFrom(Date inputFrom) {
		this.inputFrom = inputFrom;
	}

	public Date getInputEnd() {
		return inputEnd;
	}

	public void setInputEnd(Date inputEnd) {
		this.inputEnd = inputEnd;
	}

	public String getFromPerson() {
		return fromPerson;
	}

	public void setFromPerson(String fromPerson) {
		this.fromPerson = fromPerson;
	}

	public void setSourceKind(PropertyDict sourceKind) {
		this.sourceKind = sourceKind;
	}

	public PropertyDict getSourceKind() {
		return sourceKind;
	}

	public void setSourceMobile(String sourceMobile) {
		this.sourceMobile = sourceMobile;
	}

	public String getSourceMobile() {
		return sourceMobile;
	}

	public void setIssueContent(String issueContent) {
		this.issueContent = issueContent;
	}

	public String getIssueContent() {
		return issueContent;
	}

}
