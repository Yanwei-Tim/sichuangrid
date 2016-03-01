package com.tianque.incident.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.GisInfo;
import com.tianque.domain.PropertyDict;

/**
 * 应急事件对象
 * 
 * @author lizhining
 */
public class Incidents extends BaseDomain {

	private GisInfo gisInfo;
	private IncidentType incidentType;
	/**
	 * 预警标题
	 */
	private String title;
	/**
	 * 大类
	 */
	private PropertyDict category;
	/**
	 * 小类
	 */
	private Long subdivision;
	/**
	 * 级别
	 */
	private PropertyDict degree;
	/**
	 * 事件类型
	 */
	private String sourceType;

	/***
	 * 提交人
	 */
	private String applicant;
	/**
	 * 提交日期
	 */
	private Date applyDate;
	/**
	 * 事发地点
	 */
	private String occurAddress;
	/**
	 * 影响范围
	 */
	private String range;
	/**
	 * 事件详细情况
	 */
	private String content;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 是否需要启动预案
	 */
	private Integer incident;
	/**
	 * 解除原因
	 */
	private String censorreason;
	/**
	 * 事件来源
	 */
	private String source;
	/**
	 * 审核人
	 */
	private String auditer;
	/**
	 * 审核时间
	 */
	private Date auditDate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getSubdivision() {
		return subdivision;
	}

	public void setSubdivision(Long subdivision) {
		this.subdivision = subdivision;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {

		this.applyDate = applyDate;
	}

	public String getOccurAddress() {
		return occurAddress;
	}

	public void setOccurAddress(String occurAddress) {
		this.occurAddress = occurAddress;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIncident() {
		return incident;
	}

	public void setIncident(Integer incident) {
		this.incident = incident;
	}

	public String getCensorreason() {
		return censorreason;
	}

	public void setCensorreason(String censorreason) {
		this.censorreason = censorreason;
	}

	public PropertyDict getCategory() {
		return category;
	}

	public void setCategory(PropertyDict category) {
		this.category = category;
	}

	public PropertyDict getDegree() {
		return degree;
	}

	public void setDegree(PropertyDict degree) {
		this.degree = degree;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAuditer() {
		return auditer;
	}

	public void setAuditer(String auditer) {
		this.auditer = auditer;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public GisInfo getGisInfo() {
		return gisInfo;
	}

	public void setGisInfo(GisInfo gisInfo) {
		this.gisInfo = gisInfo;
	}

	public IncidentType getIncidentType() {
		return incidentType;
	}

	public void setIncidentType(IncidentType incidentType) {
		this.incidentType = incidentType;
	}

}
