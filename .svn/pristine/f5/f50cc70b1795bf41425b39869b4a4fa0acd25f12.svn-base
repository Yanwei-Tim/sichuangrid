package com.tianque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.EvaluateType;
import com.tianque.domain.vo.EvaluateVo;
import com.tianque.service.EvaluateStatisticService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Transactional
@Controller("evaluateStatisticController")
@Scope("prototype")
public class EvaluateStatisticController extends BaseAction {

	@Autowired
	private EvaluateStatisticService evaluateStatistiscService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private Long orgId;
	private List<EvaluateVo> list;
	private List<Object[]> evaluatePie;

	public String findEvaluateStatistic() {
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(EvaluateType.EVALUATE_TYPE_KEY,
						EvaluateType.NORMAL_EVALUATE);
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		list = evaluateStatistiscService.findEvaluateVo(propertyDicts.get(0).getId(),
				org.getOrgInternalCode(), orgId);
		return SUCCESS;
	}

	public String getEvaluateColumnByOrgId() {
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(EvaluateType.EVALUATE_TYPE_KEY,
						EvaluateType.NORMAL_EVALUATE);
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		evaluatePie = evaluateStatistiscService.getEvaluateColumnByOrgId(propertyDicts.get(0)
				.getId(), org.getOrgInternalCode(), orgId);
		return SUCCESS;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public List<EvaluateVo> getList() {
		return list;
	}

	public void setList(List<EvaluateVo> list) {
		this.list = list;
	}

	public List<Object[]> getEvaluatePie() {
		return evaluatePie;
	}

	public void setEvaluatePie(List<Object[]> evaluatePie) {
		this.evaluatePie = evaluatePie;
	}

}
