package com.tianque.plugin.analyzing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.service.TendencyChartService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 趋势图控制类。
 */
@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("tendencyChartController")
public class TendencyChartController extends BaseAction {
	@Autowired
	private TendencyChartService tendencyChartService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private HighchartColumnVo highchartColumnVo;
	private String typeTableName;
	private String type;
	private Organization organization;

	/**
	 * 统计分析 重点场所线性图
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showTendencyChart() throws Exception {
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		highchartColumnVo = tendencyChartService.findTendencyChart(
				typeTableName, organization.getOrgInternalCode());
		return SUCCESS;
	}

	public String showTendencyChartForPositiveinfo() {
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		highchartColumnVo = tendencyChartService
				.findTendencyChartForPositiveinfo(type,
						organization.getOrgInternalCode());
		return SUCCESS;
	}

	public String showTendencyChartForPopulation() {
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		highchartColumnVo = tendencyChartService.findTendencyChart(
				typeTableName, organization.getOrgInternalCode());
		return SUCCESS;
	}

	public String showActualPopulationTendencyChart() {
		String orgInternalCode = ThreadVariable.getSession()
				.getOrgInternalCode();
		highchartColumnVo = tendencyChartService
				.findTendencyChartForActualPopulation(orgInternalCode);
		return SUCCESS;
	}

	public HighchartColumnVo getHighchartColumnVo() {
		return highchartColumnVo;
	}

	public void setHighchartColumnVo(HighchartColumnVo highchartColumnVo) {
		this.highchartColumnVo = highchartColumnVo;
	}

	public String getTypeTableName() {
		return typeTableName;
	}

	public void setTypeTableName(String typeTableName) {
		this.typeTableName = typeTableName;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
