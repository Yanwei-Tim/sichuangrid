package com.tianque.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.PersonnelAreaDataVo;
import com.tianque.plugin.analyzing.service.StatisticsPersonnelService;
import com.tianque.service.SearchMentalPatientService;

@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("mentalPatientCountController")
public class MentalPatientCountController extends BaseAction {
	@Autowired
	private SearchMentalPatientService searchMentalPatientService;
	@Autowired
	private StatisticsPersonnelService statisticsPersonnelService;
	private HighchartColumnVo highchartColumnVo;
	private List<PersonnelAreaDataVo> mentalPatientCountVos;
	private List<Object[]> objects;
	private int year;
	private int month;
	private String typeTableName;

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

	public List<Object[]> getObjects() {
		return objects;
	}

	public void setObjects(List<Object[]> objects) {
		this.objects = objects;
	}

	public List<PersonnelAreaDataVo> getMentalPatientCountVos() {
		return mentalPatientCountVos;
	}

	public void setMentalPatientCountVos(List<PersonnelAreaDataVo> mentalPatientCountVos) {
		this.mentalPatientCountVos = mentalPatientCountVos;
	}

	private Long orgId;

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String addMentalPatientCount() {
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			this.errorMessage = "所查月份数据尚未生成数据！";
			return ERROR;
		}
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month == (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			mentalPatientCountVos = searchMentalPatientService.returnDataList(orgId);
		} else {
			mentalPatientCountVos = statisticsPersonnelService
					.getImportantPersonelAreaDataByOrgIdAndMonth(orgId, typeTableName, year, month);
		}
		return SUCCESS;
	}

	public String addMentalPatientColumn() {
		highchartColumnVo = searchMentalPatientService.returnColumnList(orgId);
		return SUCCESS;
	}

	public String addMentalPatientPie() {
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > Calendar.getInstance().get(Calendar.MONTH) + 1) {
			this.errorMessage = "所查月份数据尚未生产！";
			return ERROR;
		}
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month == Calendar.getInstance().get(Calendar.MONTH) + 1) {
			objects = searchMentalPatientService.returnPieList(orgId);
		} else {
			objects = statisticsPersonnelService.getImportantPersonlPieByOrgIdAndMonth(orgId,
					typeTableName, year, month);
		}
		return SUCCESS;
	}

	public HighchartColumnVo getHighchartColumnVo() {
		return highchartColumnVo;
	}

	public void setHighchartColumnVo(HighchartColumnVo highchartColumnVo) {
		this.highchartColumnVo = highchartColumnVo;
	}

}
