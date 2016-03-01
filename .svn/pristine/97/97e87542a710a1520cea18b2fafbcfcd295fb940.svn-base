package com.tianque.plugin.analysisNew.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.plugin.analysisNew.domain.HighchartColumnVo;
import com.tianque.plugin.analysisNew.service.TendencyChartNewService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 趋势图控制类。
 */
@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("tendencyChartNewController")
public class TendencyChartNewController extends BaseAction {
	@Autowired
	private TendencyChartNewService tendencyChartNewService;
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
		highchartColumnVo = tendencyChartNewService.findTendencyChart(
				typeTableName, organization.getId());
		return SUCCESS;
	}

	public String showTendencyChartForPositiveinfo() {
		// organization = organizationDubboService.getSimpleOrgById(organization
		// .getId());
		if (null == organization && null == organization.getId()) {
			this.errorMessage = "参数出现错误！";
			return ERROR;
		}
		highchartColumnVo = tendencyChartNewService
				.findTendencyChartForPositiveinfo(type, organization.getId());
		return SUCCESS;
	}

	public String showTendencyChartForPopulation() {
		// organization = organizationDubboService.getSimpleOrgById(organization
		// .getId());
		if (null == organization && null == organization.getId()) {
			this.errorMessage = "参数出现错误！";
			return ERROR;
		}
		highchartColumnVo = tendencyChartNewService.findTendencyChart(
				typeTableName, organization.getId());
		return SUCCESS;
	}

	public String showActualPopulationTendencyChart() {
		Long orgId = ThreadVariable.getSession().getOrganization().getId();
		if (null == orgId) {
			this.errorMessage = "参数出现错误！";
			return ERROR;
		}
		highchartColumnVo = tendencyChartNewService
				.findTendencyChartForActualPopulation(orgId);
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
