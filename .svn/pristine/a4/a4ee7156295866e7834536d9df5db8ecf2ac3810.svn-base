package com.tianque.plugin.analysisNew.controller;

import java.util.Calendar;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.plugin.analysisNew.domain.BaseinfoStatisticVo;
import com.tianque.plugin.analysisNew.domain.HighchartColumnVo;
import com.tianque.plugin.analysisNew.service.BaseinfoStatisticNewService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 统计查询的控制类
 * 
 * @author
 */
@Namespace("/baseInfo/statisticManageNew")
@Controller("baseinfoStatisticNewController")
public class BaseinfoStatisticNewController extends StatisticAction {
	@Autowired
	private BaseinfoStatisticNewService baseinfoStatisticNewService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private List<Object[]> statisticPie;
	private HighchartColumnVo personnelColumn;// 区域分布图的数据

	private String domainName;
	private Integer orgLevelDistance;

	/**
	 * 查看列表信息
	 */
	@Action(value = "getBaseInfoStatisticList", results = { @Result(name = "success", type = "json", params = {
			"root", "statisticList" }) })
	public String getBaseInfoStatisticList() throws Exception {
		if (null != domainName && !domainName.equals("")) {
			domainName = java.net.URLDecoder.decode(domainName, "UTF-8");
		}
		statisticList = baseinfoStatisticNewService.getStatisticInfoForList(
				orgId, year, month, type, domainName, orgLevelDistance);
		return SUCCESS;
	}

	@Action(value = "getYouthBaseInfoStatisticList", results = { @Result(name = "success", type = "json", params = {
			"root", "statisticList" }) })
	public String getYouthBaseInfoStatisticList() throws Exception {
		if (null != domainName && !domainName.equals("")) {
			domainName = java.net.URLDecoder.decode(domainName, "UTF-8");
		}
		statisticList = baseinfoStatisticNewService.getStatisticInfoForList(
				orgId, year, month, type, domainName, null);
		return SUCCESS;
	}

	/**
	 * 生成报表
	 * 
	 * @return
	 */
	@Action(value = "createHistoryStatistic", results = {
			@Result(name = "success", type = "json", params = { "root",
					"message" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String createHistoryStatistic() throws Exception {
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		baseinfoStatisticNewService.createHistoryStatisticByType(year, month,
				type, organization.getOrgInternalCode());

		return SUCCESS;
	}

	/**
	 * 类型分布图的方法
	 * 
	 * @return
	 */

	@Action(value = "getStatisticPie", results = { @Result(name = "success", type = "json", params = {
			"root", "statisticPie" }) })
	public String getStatisticListForPie() throws Exception {
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		statisticPie = baseinfoStatisticNewService.getStatisticInfo(year,
				month, type, organization, domainName);
		return SUCCESS;
	}

	/**
	 * 区域分布图
	 * 
	 * @return
	 */
	@Action(value = "getStatisticColumn", results = { @Result(name = "success", type = "json", params = {
			"root", "personnelColumn" }) })
	public String getStatisticColumn() throws Exception {
		if (orgId == null) {
			this.errorMessage = "部门编号错误!";
			return ERROR;
		}
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > Calendar.getInstance().get(Calendar.MONTH) + 1) {
			return ERROR;
		}

		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month == Calendar.getInstance().get(Calendar.MONTH) + 1) {

			personnelColumn = baseinfoStatisticNewService
					.getArealDistributionList(type, orgId);
		} else {

			// 根据时间加载历史数据
			personnelColumn = baseinfoStatisticNewService
					.getArealDistributionListFromHistory(orgId, type, year,
							month);

		}

		return SUCCESS;
	}

	/**
	 * 工作台实有房屋区域分布图
	 * 
	 * @return
	 */
	@Action(value = "getActualHouseStatisticColumn", results = { @Result(name = "success", type = "json", params = {
			"root", "personnelColumn" }) })
	public String getActualHouseStatisticColumn() throws Exception {
		personnelColumn = baseinfoStatisticNewService
				.getArealDistributionListForWorkBench(ThreadVariable
						.getSession().getOrganization().getId());
		return SUCCESS;
	}

	/**
	 * 工作台事件在办/已办区域分布图
	 * 
	 * @return
	 */
	@Action(value = "getIssueStatisticColumn", results = { @Result(name = "success", type = "json", params = {
			"root", "personnelColumn" }) })
	public String getIssueStatisticColumn() throws Exception {
		personnelColumn = baseinfoStatisticNewService
				.getIssueListForWorkBench(ThreadVariable.getSession()
						.getOrganization().getId());
		return SUCCESS;
	}

	public List<BaseinfoStatisticVo> getStatisticList() {
		return statisticList;
	}

	public void setStatisticList(List<BaseinfoStatisticVo> statisticList) {
		this.statisticList = statisticList;
	}

	public List<Object[]> getStatisticPie() {
		return statisticPie;
	}

	public void setStatisticPie(List<Object[]> statisticPie) {
		this.statisticPie = statisticPie;
	}

	public HighchartColumnVo getPersonnelColumn() {
		return personnelColumn;
	}

	public void setPersonnelColumn(HighchartColumnVo personnelColumn) {
		this.personnelColumn = personnelColumn;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public Integer getOrgLevelDistance() {
		return orgLevelDistance;
	}

	public void setOrgLevelDistance(Integer orgLevelDistance) {
		this.orgLevelDistance = orgLevelDistance;
	}

}
