package com.tianque.plugin.analyzing.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.PersonnelAreaDataVo;
import com.tianque.plugin.analyzing.service.StatisticsPersonnelService;
import com.tianque.plugin.analyzing.service.StatisticsPlaceService;

@SuppressWarnings("serial")
@Controller("statisticsPlaceController")
public class StatisticsPlaceController extends BaseAction {

	private Long orgId;
	private List<PersonnelAreaDataVo> personnelAreaData;
	private HighchartColumnVo personnelColumn;
	private List<Object[]> placePie;
	private int year;
	private int month;
	private String typeTableName;
	@Autowired
	private StatisticsPlaceService statisticsPlaceService;

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

	@Autowired
	private StatisticsPersonnelService statisticsPersonnelService;

	public String getStatisticsPlaceInfoList() {
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
			personnelAreaData = statisticsPlaceService
					.getImportantPlaceAreaDataByOrgId(orgId);
		} else {
			personnelAreaData = statisticsPersonnelService
					.getImportantPersonelAreaDataByOrgIdAndMonth(orgId,
							typeTableName, year, month);
		}

		return SUCCESS;
	}

	public String getStatisticsImportantPlaceColumn() {
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
			personnelColumn = statisticsPlaceService
					.getImportantPlaceColumnByOrgId(orgId);
		} else {
			// 加载历史数据
			personnelColumn = statisticsPlaceService
					.getImportantPlaceCountFromHistory(orgId, typeTableName,
							year, month);
		}
		return SUCCESS;
	}

	public String getStatisticsImportantPlacePie() {
		if (orgId == null) {
			this.errorMessage = "部门编号错误!";
			return ERROR;
		}
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > Calendar.getInstance().get(Calendar.MONTH)) {
			return ERROR;
		}
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month == Calendar.getInstance().get(Calendar.MONTH)) {
			placePie = statisticsPlaceService
					.getImportantPlacePieByOrgId(orgId);
		} else {
			placePie = statisticsPersonnelService
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

	public List<Object[]> getPlacePie() {
		return placePie;
	}

	public void setPlacePie(List<Object[]> placePie) {
		this.placePie = placePie;
	}
}
