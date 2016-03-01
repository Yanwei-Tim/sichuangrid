package com.tianque.mobile.baseInfo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.baseInfo.CommonActualPopulationMobileAdapter;
import com.tianque.mobile.baseInfo.CommonActualPopulationMobileService;

@Transactional
@Scope("request")
@Controller("commonActualPopulationMobileAdapter")
@Namespace("/commonPopulation/commonActualPopulationManage")
public class CommonActualPopulationMobileAdapterImpl extends BaseMobileAction
		implements CommonActualPopulationMobileAdapter {

	@Autowired
	private CommonActualPopulationMobileService commonActualPopulationMobileService;

	private String populationType;
	private String populationIds;
	private Long orgId;
	private Countrymen population;
	private List<Long> populationIdList;

	@Action(value = "updateActualPopulationEmphasiseForMobile", results = { @Result(name = "success", type = "json", params = {
			"root", "populationIdList", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	@Override
	public String updateActualPopulationEmphasiseForMobile() throws Exception {
		String[] updateId = populationIds.split(",");
		List<Long> idList;
		if (updateId[0].equals("")) {
			idList = initTargetId(updateId, 1);
		} else {
			idList = initTargetId(updateId, 0);
		}
		populationIdList = commonActualPopulationMobileService
				.updateActualPopulationEmphasiseForMobile(orgId,
						population.getLogoutDetail(), populationType,
						idList.toArray(new Long[0]));
		return SUCCESS;
	}

	private List<Long> initTargetId(String[] targetIds, int size) {
		List<Long> idLongs = new ArrayList<Long>();
		for (int i = size; i < targetIds.length; i++) {
			String tempId = targetIds[i];
			if (size == 0) {
				idLongs.add(Long.parseLong(targetIds[i]));
			} else {
				idLongs.add(Long.parseLong(tempId));
			}
		}
		return idLongs;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public String getPopulationIds() {
		return populationIds;
	}

	public void setPopulationIds(String populationIds) {
		this.populationIds = populationIds;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Countrymen getPopulation() {
		return population;
	}

	public void setPopulation(Countrymen population) {
		this.population = population;
	}

	public List<Long> getPopulationIdList() {
		return populationIdList;
	}

	public void setPopulationIdList(List<Long> populationIdList) {
		this.populationIdList = populationIdList;
	}

}
