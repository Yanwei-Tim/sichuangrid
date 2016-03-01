package com.tianque.plugin.analyzing.controller;

import java.util.ArrayList;
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

import com.tianque.domain.Organization;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.PrimaryOrganizationDataColumnVo;
import com.tianque.plugin.analyzing.service.PrimaryOrganizationsStatisticsService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 组织机构的统计控制类
 * 
 * @author
 */
@Scope("prototype")
@Namespace("/baseInfo/primaryOrganizationStat")
@Controller("primaryOrganizationsStatisticsController")
public class PrimaryOrganizationsStatisticsController extends StatisticAction {

	private static Logger logger = LoggerFactory
			.getLogger(PrimaryOrganizationsStatisticsController.class);
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	private PrimaryOrganizationsStatisticsService primaryOrganizationsStatisticsService;

	private HighchartColumnVo personnelColumn;// 区域分布图的数据
	private HighchartColumnVo highchartColumnVo;// 趋势图数据
	List<PrimaryOrganizationDataColumnVo> listDataColumnVo = null;
	private Organization organization;
	private Long orgId;
	private String type;
	private String orginternalCode;

	/***
	 * 组织机构区域分布图统计
	 */
	@Action(value = "primaryOrgnizationColmunData", results = {
			@Result(name = "success", type = "json", params = { "root",
					"personnelColumn" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String primaryOrgnizationColmunData() throws Exception {
		if (orgId == null) {
			this.errorMessage = "部门编号错误!";
			return ERROR;
		}
		if (validateOtherDate()) {
			this.errorMessage = "所选时间数据统计失败";
			return ERROR;
		}

		if (validateNowMonth()) {

			personnelColumn = primaryOrganizationsStatisticsService
					.getPrimaryOrgnizationsStisticsArealDistributionList(type,
							orgId, year, month);
		} else {

			// 根据时间加载历史数据
			personnelColumn = primaryOrganizationsStatisticsService
					.getArealDistributionListFromHistory(orgId, type, year,
							month);

		}
		return SUCCESS;
	}

	/***
	 * 组织机构区域分布图统计
	 */
	@Action(value = "primaryDepartmentPartyColmunData", results = {
			@Result(name = "success", type = "json", params = { "root",
					"personnelColumn" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String primaryDepartmentPartyColmunData() throws Exception {
		if (orgId == null) {
			this.errorMessage = "部门编号错误!";
			return ERROR;
		}
		if (validateOtherDate()) {
			this.errorMessage = "所选时间数据统计失败";
			return ERROR;
		}

		if (validateNowMonth()) {

			personnelColumn = primaryOrganizationsStatisticsService
					.getPrimaryOrgnizationsStisticsArealDistributionList(type,
							orgId, year, month);
		} else {

			// 根据时间加载历史数据
			personnelColumn = primaryOrganizationsStatisticsService
					.getArealDistributionListFromHistory(orgId, type, year,
							month);

		}
		return SUCCESS;
	}

	/***
	 * 组织机构趋势图统计
	 */
	@Action(value = "primaryOrgnizationTrendData", results = {
			@Result(name = "success", type = "json", params = { "root",
					"highchartColumnVo" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String primaryOrgnizationTrendData() throws Exception {
		if (orgId == null) {
			this.errorMessage = "组织机构不存在，请重试";
			return ERROR;
		}
		organization = organizationDubboService.getSimpleOrgById(orgId);
		if (organization == null || organization.getOrgInternalCode() == null) {
			this.errorMessage = "操作失败，请重试";
			return ERROR;
		}
		highchartColumnVo = primaryOrganizationsStatisticsService
				.findTendencyChartForPositiveinfo(type,
						organization.getOrgInternalCode());
		return SUCCESS;
	}

	/***
	 * 组织机构报表数据
	 */
	@Action(value = "primaryOrganizationStatisticList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"listDataColumnVo" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String primaryOrganizationStatisticList() throws Exception {
		if (validateOtherDate()) {
			this.errorMessage = "所选时间数据统计失败";
			return ERROR;
		}
		if (orgId != null) {
			listDataColumnVo = primaryOrganizationsStatisticsService
					.getPrimaryOrganizationDataByYearMonthColumnVoList(orgId,
							year, month);
			setListDataColumnVo(listDataColumnVo);
		} else {
			errorMessage = "统计失败，组织机构不存在";
			return ERROR;
		}
		return SUCCESS;
	}

	/***
	 * 生成报表数据
	 * 
	 * @return
	 */
	@Action(value = "createHistoryStatistic", results = {
			@Result(name = "success", type = "json", params = { "root",
					"success" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String createHistoryStatistic() throws Exception {
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		if (organization == null || organization.getOrgInternalCode() == null) {
			this.errorMessage = "操作失败，请重试";
			return ERROR;
		}
		primaryOrganizationsStatisticsService.createHistoryStatisticByType(
				year, month, type, organization.getOrgInternalCode());

		return SUCCESS;
	}

	/***
	 * 验证所统计的时间段是否大于当前月
	 * 
	 * @return
	 */
	private boolean validateOtherDate() {
		return year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	/***
	 * 所选时间是否为当前月
	 * 
	 * @return
	 */
	private boolean validateNowMonth() {
		return year == Calendar.getInstance().get(Calendar.YEAR)
				&& month == Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	public List<PrimaryOrganizationDataColumnVo> getListDataColumnVo() {
		if (null == listDataColumnVo) {
			listDataColumnVo = new ArrayList<PrimaryOrganizationDataColumnVo>();
		}
		return listDataColumnVo;
	}

	public void setListDataColumnVo(
			List<PrimaryOrganizationDataColumnVo> listDataColumnVo) {
		this.listDataColumnVo = listDataColumnVo;
	}

	public HighchartColumnVo getPersonnelColumn() {
		return personnelColumn;
	}

	public void setPersonnelColumn(HighchartColumnVo personnelColumn) {
		this.personnelColumn = personnelColumn;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public HighchartColumnVo getHighchartColumnVo() {
		return highchartColumnVo;
	}

	public void setHighchartColumnVo(HighchartColumnVo highchartColumnVo) {
		this.highchartColumnVo = highchartColumnVo;
	}

	public String getOrginternalCode() {
		return orginternalCode;
	}

	public void setOrginternalCode(String orginternalCode) {
		this.orginternalCode = orginternalCode;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
