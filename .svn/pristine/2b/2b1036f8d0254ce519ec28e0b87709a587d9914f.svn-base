package com.tianque.openLayersMap.domian.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.domain.Organization;

@SuppressWarnings("serial")
public class IssueInfoVo implements Serializable {

	/** 服务单号 */
	private String serialNumber;

	/** 主题 */
	private String subject;

	/** 服务办事Id */
	private Long issueId;

	/** 服务办事日志Id */
	private Long issueLogId;

	/** 经度 */
	private String lon;

	/** 纬度 */
	private String lat;

	/** 所属网格 */
	private Organization organization;
	/** 处理时间 */
	private Date dealTime;
	/** 发生地点 */
	private String occurLocation;

	/** 事件类型 */
	private String issueType;
	/** 解决状态 */
	private String status;
	/** type（待办，已办，已办结） */
	private String type;

	/** 经度 */
	private String lon2;
	/** 纬度 */
	private String lat2;

	public String getLon2() {
		return lon2;
	}

	public void setLon2(String lon2) {
		this.lon2 = lon2;
	}

	public String getLat2() {
		return lat2;
	}

	public void setLat2(String lat2) {
		this.lat2 = lat2;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public IssueInfoVo() {
		// TODO Auto-generated constructor stub
	}

	public IssueInfoVo(Long issueId, String lon, String lat, String lon2,
			String lat2) {
		super();
		this.issueId = issueId;
		this.lon = lon;
		this.lat = lat;
		this.lon2 = lon2;
		this.lat2 = lat2;
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

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public Long getIssueLogId() {
		return issueLogId;
	}

	public void setIssueLogId(Long issueLogId) {
		this.issueLogId = issueLogId;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public String getOccurLocation() {
		return occurLocation;
	}

	public void setOccurLocation(String occurLocation) {
		this.occurLocation = occurLocation;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
