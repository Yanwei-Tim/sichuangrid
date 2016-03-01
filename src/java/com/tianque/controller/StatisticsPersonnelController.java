package com.tianque.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.PersonnelAreaDataVo;
import com.tianque.plugin.analyzing.service.StatisticsPersonnelService;

@SuppressWarnings("serial")
@Controller("statisticsPersonnelController")
public class StatisticsPersonnelController extends BaseAction {

	@Autowired
	private StatisticsPersonnelService statisticsPersonnelService;

	private List<PersonnelAreaDataVo> personnelAreaData;
	private HighchartColumnVo personnelColumn;
	private Long orgId;
	private List<Object[]> personnelPie;
	private int year;
	private int month;
	private String typeTableName;

	public String getStatisticsPositiveInfoList() throws Exception {
		if (orgId == null) {
			this.errorMessage = "部门编号错误!";
			return ERROR;
		}

		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			this.errorMessage = "所查月份数据尚未生成数据！";
			return ERROR;
		}
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month == (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			personnelAreaData = statisticsPersonnelService
					.getPositiveinfoAreaDataByOrgId(orgId);
		} else {
			personnelAreaData = statisticsPersonnelService
					.getImportantPersonelAreaDataByOrgIdAndMonth(orgId,
							typeTableName, year, month);
		}
		return SUCCESS;
	}

	public String getStatisticsDangerousGoodsPractitionerList()
			throws Exception {
		if (orgId == null) {
			this.errorMessage = "部门编号错误!";
			return ERROR;
		}
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			this.errorMessage = "所查月份数据尚未生成数据！";
			return ERROR;
		}
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month == (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			personnelAreaData = statisticsPersonnelService
					.getDangerousGoodsPractitionerAreaDataByOrgId(orgId);
		} else {
			personnelAreaData = statisticsPersonnelService
					.getImportantPersonelAreaDataByOrgIdAndMonth(orgId,
							typeTableName, year, month);
		}
		return SUCCESS;
	}

	public String getStatisticsIdleyouthList() throws Exception {
		if (orgId == null) {
			this.errorMessage = "部门编号错误!";
			return ERROR;
		}
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			this.errorMessage = "所查月份数据尚未生成数据！";
			return ERROR;
		}
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month == (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			personnelAreaData = statisticsPersonnelService
					.getIdleyouthAreaDataByOrgId(orgId);
		} else {
			personnelAreaData = statisticsPersonnelService
					.getImportantPersonelAreaDataByOrgIdAndMonth(orgId,
							typeTableName, year, month);
		}
		return SUCCESS;
	}

	public String getStatisticsImportantPersonlList() throws Exception {
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
			personnelAreaData = statisticsPersonnelService
					.getImportantPersonelAreaDataByOrgId(orgId);
		} else {
			personnelAreaData = statisticsPersonnelService
					.getImportantPersonelAreaDataByOrgIdAndMonth(orgId,
							typeTableName, year, month);
		}
		return SUCCESS;
	}

	public String getStatisticsPositiveInfoColumn() throws Exception {
		if (orgId == null) {
			this.errorMessage = "部门编号错误!";
			return ERROR;
		}
		personnelColumn = statisticsPersonnelService
				.getPositiveinfoColumnByOrgId(orgId);
		return SUCCESS;
	}

	public String getStatisticsDangerousGoodsPractitionerColumn()
			throws Exception {
		if (orgId == null) {
			this.errorMessage = "部门编号错误!";
			return ERROR;
		}
		personnelColumn = statisticsPersonnelService
				.getDangerousGoodsColumnByOrgId(orgId);
		return SUCCESS;
	}

	public String getStatisticsSuperiorVisitColumn() throws Exception {
		if (orgId == null) {
			return ERROR;
		}
		personnelColumn = statisticsPersonnelService
				.getSuperiorVisitColumnByOrgId(orgId);
		return SUCCESS;
	}

	public String findStatAnalyseSuperiorVisit() throws Exception {
		if (orgId == null) {
			this.errorMessage = "部门编号错误!";
			return ERROR;
		}
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			this.errorMessage = "所查月份数据尚未生成数据！";
			return ERROR;
		}
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month == (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			personnelAreaData = statisticsPersonnelService
					.findStatAnalyseSuperiorVisit(orgId);
		} else {
			personnelAreaData = statisticsPersonnelService
					.getImportantPersonelAreaDataByOrgIdAndMonth(orgId,
							typeTableName, year, month);
		}

		return SUCCESS;
	}

	public String getStatisticsIdleyouthColumn() throws Exception {
		if (orgId == null) {
			this.errorMessage = "部门编号错误!";
			return ERROR;
		}
		personnelColumn = statisticsPersonnelService
				.getIdleyouthColumnByOrgId(orgId);
		return SUCCESS;
	}

	public String getStatisticsImportantPersonlColumn() throws Exception {
		if (orgId == null) {
			this.errorMessage = "部门编号错误!";
			return ERROR;
		}
		personnelColumn = statisticsPersonnelService
				.getImportantPersonlColumnByOrgId(orgId);
		return SUCCESS;
	}

	public String getStatisticsPositiveInfoPie() throws Exception {
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
			personnelPie = statisticsPersonnelService
					.getPositiveInfoPieByOrgId(orgId);
		} else {
			personnelPie = statisticsPersonnelService
					.getImportantPersonlPieByOrgIdAndMonth(orgId,
							typeTableName, year, month);
		}
		return SUCCESS;
	}

	public String getStatisticsDangerousGoodsPractitionerPie() throws Exception {
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
			personnelPie = statisticsPersonnelService
					.getDangerousGoodsPractitionerPieByOrgId(orgId);
		} else {
			personnelPie = statisticsPersonnelService
					.getImportantPersonlPieByOrgIdAndMonth(orgId,
							typeTableName, year, month);
		}
		return SUCCESS;
	}

	public String getStatisticsIdleyouthPie() throws Exception {
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
			personnelPie = statisticsPersonnelService
					.getIdleyouthPieByOrgId(orgId);
		} else {
			personnelPie = statisticsPersonnelService
					.getImportantPersonlPieByOrgIdAndMonth(orgId,
							typeTableName, year, month);
		}
		return SUCCESS;
	}

	public String getStatisticsImportantPersonlPie() throws Exception {
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
			personnelPie = statisticsPersonnelService
					.getImportantPersonlPieByOrgId(orgId);
		} else {
			personnelPie = statisticsPersonnelService
					.getImportantPersonlPieByOrgIdAndMonth(orgId,
							typeTableName, year, month);
		}
		return SUCCESS;
	}

	public List<PersonnelAreaDataVo> getPersonnelAreaData() {
		return personnelAreaData;
	}

	public void setPersonnelAreaData(List<PersonnelAreaDataVo> personnelAreaData) {
		this.personnelAreaData = personnelAreaData;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public HighchartColumnVo getPersonnelColumn() {
		return personnelColumn;
	}

	public void setPersonnelColumn(HighchartColumnVo personnelColumn) {
		this.personnelColumn = personnelColumn;
	}

	public List<Object[]> getPersonnelPie() {
		return personnelPie;
	}

	public void setPersonnelPie(List<Object[]> personnelPie) {
		this.personnelPie = personnelPie;
	}

	public StatisticsPersonnelService getStatisticsPersonnelService() {
		return statisticsPersonnelService;
	}

	public void setStatisticsPersonnelService(
			StatisticsPersonnelService statisticsPersonnelService) {
		this.statisticsPersonnelService = statisticsPersonnelService;
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

	public String getTypeTableName() {
		return typeTableName;
	}

	public void setTypeTableName(String typeTableName) {
		this.typeTableName = typeTableName;
	}

}
