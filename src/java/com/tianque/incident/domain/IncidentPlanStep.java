package com.tianque.incident.domain;

import com.tianque.core.base.BaseDomain;

/**
 * @author lizhining
 *         应急事件预案处置步骤对象
 */
public class IncidentPlanStep extends BaseDomain {

	// incident 因为没有必要维护双向关系，可以考虑从该对象中移出。
	/**
	 * 应急事件预案对象
	 */
	private IncidentDealPlan incidentDealPlan;
	/**
	 * 顺序
	 */
	private Integer seqIndex;
	/**
	 * 处置内容
	 */
	private String content;

	public Integer getSeqIndex() {
		return seqIndex;
	}

	public void setSeqIndex(Integer seqIndex) {
		this.seqIndex = seqIndex;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public IncidentDealPlan getIncidentDealPlan() {
		return incidentDealPlan;
	}

	public void setIncidentDealPlan(IncidentDealPlan incidentDealPlan) {
		this.incidentDealPlan = incidentDealPlan;
	}

}
