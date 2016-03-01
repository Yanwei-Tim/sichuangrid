package com.tianque.baseInfo.superiorVisit.domain;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.PropertyDict;

/** 上访历史 **/
public class SuperiorVisitHistory extends BaseDomain {

	// 上访时间
	private Date visitDate;

	// 上访原因
	private String visitReason;

	// 上访事件是否解决
	private PropertyDict visitState;

	// 上访类型
	private Integer visitType;

	// 上访类型子类型
	private List<PropertyDict> visitTypes;

	// 上访类型子类型名以逗号分隔
	private String typeName;

	private Long visitId;

	@JSON(format = "yyyy-MM-dd")
	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public String getVisitReason() {
		return visitReason;
	}

	public void setVisitReason(String visitReason) {
		this.visitReason = visitReason;
	}

	public Integer getVisitType() {
		return visitType;
	}

	public void setVisitType(Integer visitType) {
		this.visitType = visitType;
	}

	public List<PropertyDict> getVisitTypes() {
		return visitTypes;
	}

	public void setVisitTypes(List<PropertyDict> visitTypes) {
		this.visitTypes = visitTypes;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Long getVisitId() {
		return visitId;
	}

	public void setVisitId(Long visitId) {
		this.visitId = visitId;
	}

	public PropertyDict getVisitState() {
		return visitState;
	}

	public void setVisitState(PropertyDict visitState) {
		this.visitState = visitState;
	}

}
