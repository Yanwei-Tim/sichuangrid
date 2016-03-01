package com.tianque.baseInfo.base.domain;

import java.util.List;

import org.mongodb.morphia.annotations.NotSaved;

import com.tianque.domain.PopulationTypeBean;

/**
 * 实有人口实体
 */

@SuppressWarnings("serial")
public class ActualPopulation extends Countrymen {
	/** 工作情况 */
	@NotSaved
	private String workSituation;
	/** 是否注销 */
	private Long logOut;
	@NotSaved
	private List<PopulationTypeBean> populationTypes;

	public String getWorkSituation() {
		return workSituation;
	}

	public void setWorkSituation(String workSituation) {
		this.workSituation = workSituation;
	}

	public Long getLogOut() {
		return logOut;
	}

	public void setLogOut(Long logOut) {
		this.logOut = logOut;
	}

	public List<PopulationTypeBean> getPopulationTypes() {
		return populationTypes;
	}

	public void setPopulationTypes(List<PopulationTypeBean> populationTypes) {
		this.populationTypes = populationTypes;
	}
}