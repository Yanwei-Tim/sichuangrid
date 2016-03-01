package com.tianque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.HouseLivingPopulationInfo;
import com.tianque.search.vo.ActualPopulationSearchCondition;
import com.tianque.service.ActulPopulationUnifiedService;

@Controller("actualPopulationApplyController")
@Scope("prototype")
public class ActualPopulationApplyController extends BaseAction {
	@Autowired
	private ActulPopulationUnifiedService actualPopulationService;

	private String certificationNumber;

	private String name;

	private Long orgId;

	public String getCertificationNumber() {
		return certificationNumber;
	}

	public void setCertificationNumber(String certificationNumber) {
		this.certificationNumber = certificationNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String findActualPopulation() {
		ActualPopulationSearchCondition condition = constructSearchCondition();
		PageInfo<HouseLivingPopulationInfo> populations = actualPopulationService
				.findActualPopulations(condition, page, rows, sidx, sord);
		gridPage = new GridPage(populations);
		return SUCCESS;
	}

	private ActualPopulationSearchCondition constructSearchCondition() {
		ActualPopulationSearchCondition condition = new ActualPopulationSearchCondition();
		condition.setRootOrgId(orgId);
		condition.setCertificationNumber(certificationNumber);
		condition.setName(name);
		return condition;
	}

}
