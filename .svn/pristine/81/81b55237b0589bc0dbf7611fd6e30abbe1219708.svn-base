package com.tianque.plugin.analyzing.controller;

import java.util.Calendar;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.plugin.analyzing.domain.BaseinfoStatisticVo;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.service.BaseinfoStatisticService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 统计查询的控制类
 * 
 * @author
 */
@Namespace("/baseInfo/statisticManage")
@Controller("baseinfoStatisticController")
public class BaseinfoStatisticController extends StatisticAction {
	@Autowired
	private BaseinfoStatisticService baseinfoStatisticService;
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
		statisticList = baseinfoStatisticService.getStatisticInfoForList(orgId,
				year, month, type, domainName, orgLevelDistance);
		return SUCCESS;
	}

	@Action(value = "getYouthBaseInfoStatisticList", results = { @Result(name = "success", type = "json", params = {
			"root", "statisticList" }) })
	public String getYouthBaseInfoStatisticList() throws Exception {
		if (null != domainName && !domainName.equals("")) {
			domainName = java.net.URLDecoder.decode(domainName, "UTF-8");
		}
		statisticList = baseinfoStatisticService.getStatisticInfoForList(orgId,
				year, month, type, domainName, null);
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
		baseinfoStatisticService.createHistoryStatisticByType(year, month,
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
		statisticPie = baseinfoStatisticService.getStatisticInfo(year, month,
				type, organization.getOrgInternalCode(), domainName);
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

			personnelColumn = baseinfoStatisticService
					.getArealDistributionList(type, orgId);
		} else {

			// 根据时间加载历史数据
			personnelColumn = baseinfoStatisticService
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
		personnelColumn = baseinfoStatisticService
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
		personnelColumn = baseinfoStatisticService
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
