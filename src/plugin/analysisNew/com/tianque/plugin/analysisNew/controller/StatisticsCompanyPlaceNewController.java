package com.tianque.plugin.analysisNew.controller;

import java.util.Calendar;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.plugin.analysisNew.domain.HighchartColumnVo;
import com.tianque.plugin.analysisNew.domain.PopulationAreaDataVo;
import com.tianque.plugin.analysisNew.service.CompanyPlaceLeaderViewNewService;

/**
 * @Description:单位场所研判分析的控制层
 * @author zhangyouwei@hztian.com
 * @date: 2014-8-24 下午07:44:42
 */
@Namespace("/baseInfoStat/companyPlaceStanalsManageNew")
@Controller("statisticsCompanyPlaceNewController")
@Scope("prototype")
public class StatisticsCompanyPlaceNewController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(StatisticsCompanyPlaceNewController.class);
	/** 区别单位场所是哪个单位场所 */
	private String moduleType;
	/** 组织机构id */
	private Long orgId;
	/** 年 */
	private int year;
	/** 月 */
	private int month;
	/** 区域图返回值 */
	private HighchartColumnVo companyPlaceColumn;
	/** 列表图返回值 */
	private List<PopulationAreaDataVo> populationAreaData;
	/** 趋势图返回值 */
	private List<Object[]> companyPlacePie;
	/** 列表图用于区分显示那个图 */
	private Integer orgLevelDistance;
	@Autowired
	private CompanyPlaceLeaderViewNewService companyPlaceLeaderViewNewService;

	/**
	 * 区域图
	 * 
	 * @return
	 */
	@Action(value = "getStatisticsCompanyPlaceColumn", results = { @Result(name = "success", type = "json", params = {
			"root", "companyPlaceColumn" }) })
	public String getStatisticsCompanyPlaceColumn() throws Exception {
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

			companyPlaceColumn = companyPlaceLeaderViewNewService
					.getCompanyPlaceColumnByOrgIdAndType(orgId, moduleType);

		} else {
			// 加载历史数据
			companyPlaceColumn = companyPlaceLeaderViewNewService
					.getCompanyPlaceColumnByTime(orgId, moduleType, year, month);
		}
		return SUCCESS;
	}

	/**
	 * 列表图
	 * 
	 * @return
	 */
	@Action(value = "getStatisticsCompanyPlaceList", results = { @Result(name = "success", type = "json", params = {
			"root", "populationAreaData" }) })
	public String getStatisticsCompanyPlaceList() throws Exception {
		if (orgId == null) {
			this.errorMessage = "部门编号错误!";
			return ERROR;
		}

		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			this.errorMessage = "所查月份数据尚未生产！";
			return ERROR;
		}
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month == (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			populationAreaData = companyPlaceLeaderViewNewService
					.getCurrentAreaDate(orgId, moduleType, orgLevelDistance);
		} else {
			populationAreaData = companyPlaceLeaderViewNewService
					.getAreaDateByDate(orgId, moduleType, year, month,
							orgLevelDistance);
		}
		return SUCCESS;
	}

	/**
	 * 生成表格
	 * 
	 * @return
	 */
	@Action(value = "createStatisticsCompanyPlaceList", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String createStatisticsCompanyPlaceList() throws Exception {
		if (orgId == null) {
			this.errorMessage = "部门编号错误!";
			return ERROR;
		}
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			this.errorMessage = "所查月份数据尚未生产！";
			return ERROR;
		}
		companyPlaceLeaderViewNewService.addStatisticsCompanyPlace(year, month,
				orgId, moduleType);
		return SUCCESS;
	}

	/**
	 * 趋势图
	 * 
	 * @return
	 */
	@Action(value = "showTendencyChartForCompanyPlace", results = { @Result(name = "success", type = "json", params = {
			"root", "companyPlaceColumn" }) })
	public String showTendencyChartForCompanyPlace() throws Exception {
		if (orgId == null) {
			this.errorMessage = "部门编号错误!";
			return ERROR;
		}
		companyPlaceColumn = companyPlaceLeaderViewNewService
				.findTendencyChart(moduleType, orgId);
		return SUCCESS;
	}

	/**
	 * 类型分布图
	 * 
	 * @return
	 */
	@Action(value = "getStatisticsCompanyPlacePie", results = { @Result(name = "success", type = "json", params = {
			"root", "companyPlacePie" }) })
	public String getStatisticsCompanyPlacePie() throws Exception {

		if (orgId == null) {
			this.errorMessage = "部门编号错误!";
			return ERROR;
		}
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > Calendar.getInstance().get(Calendar.MONTH) + 1) {
			this.errorMessage = "所查月份数据尚未生产！";
			return ERROR;
		}

		companyPlacePie = companyPlaceLeaderViewNewService
				.getCompanyPlacePieInfo(year, month, orgId, moduleType);
		return SUCCESS;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public HighchartColumnVo getCompanyPlaceColumn() {
		return companyPlaceColumn;
	}

	public void setCompanyPlaceColumn(HighchartColumnVo companyPlaceColumn) {
		this.companyPlaceColumn = companyPlaceColumn;
	}

	public List<PopulationAreaDataVo> getPopulationAreaData() {
		return populationAreaData;
	}

	public void setPopulationAreaData(
			List<PopulationAreaDataVo> populationAreaData) {
		this.populationAreaData = populationAreaData;
	}

	public List<Object[]> getCompanyPlacePie() {
		return companyPlacePie;
	}

	public void setCompanyPlacePie(List<Object[]> companyPlacePie) {
		this.companyPlacePie = companyPlacePie;
	}

	public Integer getOrgLevelDistance() {
		return orgLevelDistance;
	}

	public void setOrgLevelDistance(Integer orgLevelDistance) {
		this.orgLevelDistance = orgLevelDistance;
	}

}
