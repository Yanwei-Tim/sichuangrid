package com.tianque.baseInfo.superiorVisit.domain;

import java.util.List;

import com.tianque.baseInfo.base.domain.AttentionPopulation;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.PropertyDict;

/**
 * 上访人员
 */
@SuppressWarnings("serial")
public class SuperiorVisit extends AttentionPopulation {
	public SuperiorVisit() {
		setAttentionPopulationType(BaseInfoTables.SUPERIORVISIT_KEY);
	}

	/**
	 * 上访状态
	 */
	private PropertyDict visitState;
	/**
	 * 上访类型
	 */
	private Long visitType;
	/**
	 * 上访原因
	 */
	private String visitReason;

	/**
	 * 上访类型子类型
	 */
	private List<PropertyDict> visitTypes;
	/**
	 * 上访类型子类型名以逗号分隔
	 */
	private String typeName;

	public PropertyDict getVisitState() {
		return visitState;
	}

	public void setVisitState(PropertyDict visitState) {
		this.visitState = visitState;
	}

	public Long getVisitType() {
		return visitType;
	}

	public void setVisitType(Long visitType) {
		this.visitType = visitType;
	}

	public String getVisitReason() {
		return visitReason;
	}

	public void setVisitReason(String visitReason) {
		this.visitReason = visitReason;
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

}
