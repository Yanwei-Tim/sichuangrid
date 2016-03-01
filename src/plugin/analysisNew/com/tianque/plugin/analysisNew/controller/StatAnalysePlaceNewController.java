package com.tianque.plugin.analysisNew.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.plugin.analysisNew.domain.HighchartColumnVo;
import com.tianque.plugin.analysisNew.domain.HighchartDataColumnVo;
import com.tianque.plugin.analysisNew.domain.StatAnalysePlaceVo;
import com.tianque.plugin.analysisNew.service.BaseInfoStatTypeNewService;
import com.tianque.plugin.analysisNew.service.StatAnalysePlaceNewService;

@Controller("statAnalysePlaceNewController")
public class StatAnalysePlaceNewController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(StatAnalysePlaceNewController.class);
	@Autowired
	private StatAnalysePlaceNewService statAnalysePlaceNewService;
	@Autowired
	private BaseInfoStatTypeNewService baseInfoStatTypeNewService;
	private Long orgId;
	private String keyType;
	private List<StatAnalysePlaceVo> result;
	private List<HighchartDataColumnVo> list = new ArrayList<HighchartDataColumnVo>();
	private HighchartColumnVo placeColumnVo = new HighchartColumnVo();
	private List<Object[]> placePie;
	private int year;
	private int month;
	private String typeTableName;

	/**
	 * 列表数据
	 * 
	 * @return
	 */
	public String findStatAnalysePlace() throws Exception {

		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			this.errorMessage = "所查月份数据尚未生产！";
			return ERROR;
		}
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month == (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			result = statAnalysePlaceNewService.findStatAnalysePlace(
					BaseInfoTables.toString(keyType), orgId, keyType);
		} else {
			// 加载历史数据
			result = statAnalysePlaceNewService
					.findStatAnalysePlaceFromHistory(
							BaseInfoTables.toString(keyType), orgId, keyType,
							year, month);
		}
		return SUCCESS;
	}

	/*
	 * 生成报表
	 */
	public String createHistoryStatistic() throws Exception {
		baseInfoStatTypeNewService.addBaseInfoStatType(year, month);
		return SUCCESS;
	}

	/**
	 * 规上规下企业的历史数据生成
	 * 
	 * @return
	 */

	public String createEnterpriseHistoryStatistic() throws Exception {
		baseInfoStatTypeNewService
				.createEnterpriseHistoryStatistic(year, month);
		return SUCCESS;
	}

	/**
	 * 类型分布图
	 * 
	 * @return
	 */
	public String findStatAnalysePlaceForHighchartColumnVo() throws Exception {
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			this.errorMessage = "所查月份数据尚未生产！";
			return ERROR;
		}
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month == (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			placeColumnVo = statAnalysePlaceNewService.getStatAnalysePlace(
					BaseInfoTables.getTypeValue(keyType), orgId, keyType);
		} else {
			placeColumnVo = statAnalysePlaceNewService
					.getStatAnalysePlaceFromHistory(orgId, keyType, year, month);
		}
		return SUCCESS;

	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public List<StatAnalysePlaceVo> getResult() {
		return result;
	}

	public void setResult(List<StatAnalysePlaceVo> result) {
		this.result = result;
	}

	public List<HighchartDataColumnVo> getList() {
		return list;
	}

	public void setList(List<HighchartDataColumnVo> list) {
		this.list = list;
	}

	public HighchartColumnVo getPlaceColumnVo() {
		return placeColumnVo;
	}

	public void setPlaceColumnVo(HighchartColumnVo placeColumnVo) {
		this.placeColumnVo = placeColumnVo;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public List<Object[]> getPlacePie() {
		return placePie;
	}

	public void setPlacePie(List<Object[]> placePie) {
		this.placePie = placePie;
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
