package com.tianque.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.rectificativePerson.service.SearchRectificativePersonService;
import com.tianque.core.base.BaseAction;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.PersonnelAreaDataVo;
import com.tianque.plugin.analyzing.service.StatisticsPersonnelService;

@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("rectificativePersonCountController")
public class RectificativePersonCountController extends BaseAction {
	@Autowired
	SearchRectificativePersonService searchRectificativePersonService;
	@Autowired
	private StatisticsPersonnelService statisticsPersonnelService;
	private List<PersonnelAreaDataVo> rectificativePersonCountVos;
	private Long orgId;
	private HighchartColumnVo highchartColumnVo;
	private List<Object[]> objects;
	private int year;
	private int month;
	private String typeTableName;

	public List<Object[]> getObjects() {
		return objects;
	}

	public void setObjects(List<Object[]> objects) {
		this.objects = objects;
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

	public HighchartColumnVo getHighchartColumnVo() {
		return highchartColumnVo;
	}

	public void setHighchartColumnVo(HighchartColumnVo highchartColumnVo) {
		this.highchartColumnVo = highchartColumnVo;
	}

	public List<PersonnelAreaDataVo> getRectificativePersonCountVos() {
		return rectificativePersonCountVos;
	}

	public void setRectificativePersonCountVos(
			List<PersonnelAreaDataVo> rectificativePersonCountVos) {
		this.rectificativePersonCountVos = rectificativePersonCountVos;
	}

	public Long getOrgId() {
		return orgId;
	}

	public String addRectificativePersonCount() throws Exception {
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			this.errorMessage = "所查月份数据尚未生成数据！";
			return ERROR;
		}
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month == (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			rectificativePersonCountVos = searchRectificativePersonService
					.returnDataList(orgId);
		} else {
			rectificativePersonCountVos = statisticsPersonnelService
					.getImportantPersonelAreaDataByOrgIdAndMonth(orgId,
							typeTableName, year, month);
		}
		return SUCCESS;
	}

	public String addRectificativePersonColumn() throws Exception {
		highchartColumnVo = searchRectificativePersonService
				.returnColumnList(orgId);
		return SUCCESS;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String addRectificativePersonPie() throws Exception {
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > Calendar.getInstance().get(Calendar.MONTH) + 1) {
			this.errorMessage = "所查月份数据尚未生产！";
			return ERROR;
		}
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month == Calendar.getInstance().get(Calendar.MONTH) + 1) {
			objects = searchRectificativePersonService.returnPieList(orgId);
		} else {
			objects = statisticsPersonnelService
					.getImportantPersonlPieByOrgIdAndMonth(orgId,
							typeTableName, year, month);
		}

		return SUCCESS;
	}
}
