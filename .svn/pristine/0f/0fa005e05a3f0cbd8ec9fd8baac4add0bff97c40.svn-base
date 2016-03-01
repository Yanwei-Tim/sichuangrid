package com.tianque.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.StatementStatistic;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.service.CommonService;
import com.tianque.service.StatementStatisticService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@SuppressWarnings("serial")
@Controller("statementStatisticController")
@Scope("prototype")
@Transactional
public class StatementStatisticController extends BaseAction {

	@Autowired
	private StatementStatisticService statementStatisticService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private CommonService commonService;

	public String getYearsByOrgId() throws Exception {
		years = commonService.getUsableYear();
		return SUCCESS;
	}

	public String getMonthsByOrgId() throws Exception {
		if (organization == null || organization.getId() == null) {
			this.errorMessage = "请选择网格!";
			return ERROR;
		}
		if (year == null) {
			this.errorMessage = "请选择年份!";
			return ERROR;
		}
		months = statementStatisticService.getMonthsByOrgId(
				organization.getId(), year);
		return SUCCESS;
	}

	public String findStatementStatisticsForPageByOrgId() throws Exception {
		if (null == organization || null == organization.getId()) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		}
		if (year == 0) {
			year = Calendar.getInstance().get(Calendar.YEAR);
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				statementStatisticService
						.findStatementStatisticsForPageByOrgId(
								organization.getId(), getStatementType(), year,
								month, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" },
				organization.getId()));
		return SUCCESS;
	}

	public String findStatementStatisticsForPageByOrgInternalCode()
			throws Exception {
		if (null == organization || null == organization.getId()) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		}
		if (year == null) {
			year = Calendar.getInstance().get(Calendar.YEAR);
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				statementStatisticService
						.findStatementStatisticsForPageByOrgInternalCode(
								organizationDubboService.getSimpleOrgById(
										organization.getId())
										.getOrgInternalCode(),
								getStatementType(), year, month, page, rows,
								sidx, sord), organizationDubboService,
				new String[] { "organization" }, organization.getId()));
		return SUCCESS;
	}

	private Long getStatementType() {
		if (year != 0 && month == 0) {
			month = null;
			return statementStatisticService
					.getStatementTypeByDomainNameAndDisplayName(
							PropertyTypes.STATEMENT_STATISTIC_TYPE, YEARLY_STR)
					.getId();
		}
		if (year != 0 && month != 0) {
			return statementStatisticService
					.getStatementTypeByDomainNameAndDisplayName(
							PropertyTypes.STATEMENT_STATISTIC_TYPE, MONTHLY_STR)
					.getId();
		}
		return null;
	}

	private PageInfo<StatementStatistic> emptyPage(int pageSize) {
		PageInfo<StatementStatistic> pageInfo = new PageInfo<StatementStatistic>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<StatementStatistic>());
		return pageInfo;
	}

	private Organization organization;
	private Integer year;
	private Integer month;
	private List<Integer> years;
	private List<Integer> months;
	private static final String YEARLY_STR = "年报";
	private static final String MONTHLY_STR = "月报";

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public List<Integer> getYears() {
		return years;
	}

	public void setYears(List<Integer> years) {
		this.years = years;
	}

	public List<Integer> getMonths() {
		return months;
	}

	public void setMonths(List<Integer> months) {
		this.months = months;
	}
}
