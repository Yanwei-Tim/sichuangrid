package com.tianque.plugin.judgmentAnalysis.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.analysis.api.DimensionCombinationCycleDubboService;
import com.tianque.analysis.api.HistoryCycleDubboService;
import com.tianque.core.base.BaseAction;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.StringUtil;
import com.tianque.plugin.judgmentAnalysis.constants.ChartPageConstant;
import com.tianque.plugin.judgmentAnalysis.constants.DimensionCombinationMode;
import com.tianque.plugin.judgmentAnalysis.domain.DimensionCombinationCycle;
import com.tianque.plugin.judgmentAnalysis.domain.HistoryCycle;
import com.tianque.plugin.judgmentAnalysis.vo.ChartPageShowVo;

@Scope("prototype")
@Namespace("/judgmentAnalysis/houseHoldStaff")
@Controller("houseHoldStaffAnalysisController")
public class HouseHoldStaffAnalysisController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(HouseHoldStaffAnalysisController.class);

	@Autowired
	private HistoryCycleDubboService historyCycleDubboService;
	@Autowired
	private DimensionCombinationCycleDubboService dimensionCombinationCycleDubboService;
	private HistoryCycle historyCycle;

	private String dimensionKeyName;

	private Long orgId;
	// 维度查询
	private String dimensionName;

	private String keyNameLeft;
	private String keyNameRight;

	private int year;
	private int month;

	private int yearOld;
	private int monthOld;
	private List<HistoryCycle> historyCyclesHb;
	private List<DimensionCombinationCycle> combinationCycles;

	private ChartPageShowVo chartPageShowVo = new ChartPageShowVo();

	/**
	 * 同比环比计算
	 * 
	 * @return
	 */
	@Action(value = "getArrayHistoryCycles", results = {
			@Result(name = "success", type = "json", params = { "root",
					"chartPageShowVo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getArrayHistoryCycles() {
		try {
			getYearData();
			String modelKeyName = "hstaff";
			historyCyclesHb = historyCycleDubboService.countUnderHistoryCycle(
					orgId, modelKeyName, DimensionCombinationMode.HIS.value,
					year, month);
			// 环比
			if (historyCyclesHb != null && !historyCyclesHb.isEmpty()) {
				getArrayHistoryCyclesHb(historyCyclesHb);
			}
			List<HistoryCycle> historyCyclesOldHb = historyCycleDubboService
					.countUnderHistoryCycle(orgId, modelKeyName,
							DimensionCombinationMode.HIS.value,
							month == 1 ? year - 1 : year, monthOld);
			if (historyCyclesOldHb != null && !historyCyclesOldHb.isEmpty()) {
				getArrayHistoryCyclesOldHb(historyCyclesOldHb);
			} else {
				getArrayHistoryCyclesOldHbIsNull(historyCyclesHb);
			}
			// 计算环比增长率
			countRateHb();

			// 同比
			List<HistoryCycle> historyCyclesTb = historyCycleDubboService
					.countUnderHistoryCycle(orgId, modelKeyName,
							DimensionCombinationMode.HIS.value, year, month);
			if (historyCyclesTb != null && !historyCyclesTb.isEmpty()) {
				getArrayHistoryCyclesTb(historyCyclesTb);
			}
			List<HistoryCycle> historyCyclesOldTb = historyCycleDubboService
					.countUnderHistoryCycle(orgId, modelKeyName,
							DimensionCombinationMode.HIS.value, yearOld, month);
			if (historyCyclesOldTb != null && !historyCyclesOldTb.isEmpty()) {
				getArrayHistoryCyclesOldTb(historyCyclesOldTb);
			} else {
				getArrayHistoryCyclesOldTbIsNull(historyCyclesTb);
			}
			// 计算环比增长率
			countRateTb();
			// 设置列表数据
			setDataList();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "操作失败，请联系管理员";
			return ERROR;
		}
		return SUCCESS;
	}

	private boolean checkHbData(ChartPageShowVo chartPageShowVo) {
		if (chartPageShowVo.getAmountHb() == null
				|| chartPageShowVo.getAmountHb().length == 0
				|| chartPageShowVo.getOrgName() == null
				|| chartPageShowVo.getOrgName().length == 0
				|| chartPageShowVo.getOrgId() == null
				|| chartPageShowVo.getOrgId().length == 0) {
			return false;
		}
		return true;
	}

	private boolean checkTbData(ChartPageShowVo chartPageShowVo) {
		if (chartPageShowVo.getAmountTb() == null
				|| chartPageShowVo.getAmountTb().length == 0
				|| chartPageShowVo.getOrgName() == null
				|| chartPageShowVo.getOrgName().length == 0
				|| chartPageShowVo.getOrgId() == null
				|| chartPageShowVo.getOrgId().length == 0) {
			return false;
		}
		return true;
	}

	// 同比环比// 设置列表数据
	private void setDataList() {
		double amountOldTotalHb = 0.0d;
		double amountTotalHb = 0.0d;
		double amountOldTotalTb = 0.0d;
		double amountTotalTb = 0.0d;
		String rateTotalHb = "";
		String rateTotalTb = "";

		String amountOldTotalHbStr = "";
		String amountTotalHbStr = "";
		String amountOldTotalTbStr = "";
		String amountTotalTbStr = "";

		DecimalFormat df = new DecimalFormat("#0.00");
		String[] amountHb = chartPageShowVo.getAmountHb();
		String[] amountTb = chartPageShowVo.getAmountTb();
		if (checkHbData(chartPageShowVo)) {
			String dataObject = "[";
			for (int i = 0; i < amountHb.length; i++) {
				if (chartPageShowVo.getRateHb() == null
						|| chartPageShowVo.getRateHb().length == 0
						|| chartPageShowVo.getAmountOldHb() == null
						|| chartPageShowVo.getAmountOldHb().length == 0) {
					dataObject += "{ 'AreaDate':'"
							+ (chartPageShowVo.getOrgName())[i]
							+ "', 'October':'" + 0 + "', 'November': '"
							+ chartPageShowVo.getAmountHb()[i]
							+ "', 'hbGrowthRate': '" + 0 + "‰', 'id': '"
							+ chartPageShowVo.getOrgId()[i] + "' },";
				} else {
					dataObject += "{ 'AreaDate':'"
							+ (chartPageShowVo.getOrgName())[i]
							+ "', 'October':'"
							+ chartPageShowVo.getAmountOldHb()[i]
							+ "', 'November': '"
							+ chartPageShowVo.getAmountHb()[i]
							+ "', 'hbGrowthRate': '"
							+ chartPageShowVo.getRateHb()[i] + "‰', 'id': '"
							+ chartPageShowVo.getOrgId()[i] + "' },";
					amountOldTotalHb += Float.parseFloat(chartPageShowVo
							.getAmountOldHb()[i]);
				}

				amountTotalHb += Double.parseDouble(chartPageShowVo
						.getAmountHb()[i]);
			}
			double rateTotalHbTemp = 0.0d;
			if (amountOldTotalHb > 0) {
				rateTotalHbTemp = (amountTotalHb - amountOldTotalHb) > 0 ? ((amountTotalHb - amountOldTotalHb) / amountOldTotalHb) * 1000
						: 0.0d;
			}
			if (rateTotalHbTemp > 0.0d && rateTotalHbTemp < 0.01d) {
				rateTotalHb = "0.01";
			} else {
				rateTotalHb = df.format(rateTotalHbTemp);
			}
			BigDecimal big = new BigDecimal(amountTotalHb);
			amountTotalHbStr = big.toString();

			BigDecimal bigOld = new BigDecimal(amountOldTotalHb);
			amountOldTotalHbStr = bigOld.toString();

			dataObject += "{ 'AreaDate': '总计', 'October': '"
					+ amountOldTotalHbStr + "', 'November': '"
					+ amountTotalHbStr + "', 'hbGrowthRate': '" + rateTotalHb
					+ "‰', 'id': '9' }";
			dataObject += "]";
			chartPageShowVo.setDataListHb(dataObject);
		}
		if (checkTbData(chartPageShowVo)) {
			String dataObjectTb = "[";
			for (int i = 0; i < amountTb.length; i++) {
				if (chartPageShowVo.getRateTb() == null
						|| chartPageShowVo.getRateTb().length == 0
						|| chartPageShowVo.getAmountOldTb() == null
						|| chartPageShowVo.getAmountOldTb().length == 0) {
					dataObjectTb += "{ 'AreaDate':'"
							+ (chartPageShowVo.getOrgName())[i]
							+ "', 'October':'" + 0 + "', 'November': '"
							+ chartPageShowVo.getAmountTb()[i]
							+ "', 'tbGrowthRate': '" + 0 + "‰', 'id': '"
							+ chartPageShowVo.getOrgId()[i] + "' },";
				} else {
					dataObjectTb += "{ 'AreaDate':'"
							+ (chartPageShowVo.getOrgName())[i]
							+ "', 'October':'"
							+ chartPageShowVo.getAmountOldTb()[i]
							+ "', 'November': '"
							+ chartPageShowVo.getAmountTb()[i]
							+ "', 'tbGrowthRate': '"
							+ chartPageShowVo.getRateTb()[i] + "‰', 'id': '"
							+ chartPageShowVo.getOrgId()[i] + "' },";
					amountOldTotalTb += Double.parseDouble(chartPageShowVo
							.getAmountOldTb()[i]);
				}
				amountTotalTb += Double.parseDouble(chartPageShowVo
						.getAmountTb()[i]);
			}
			double rateTotalTbTemp = 0.0d;
			if (amountOldTotalTb > 0) {
				rateTotalTbTemp = (amountTotalTb - amountOldTotalTb) > 0 ? ((amountTotalTb - amountOldTotalTb) / amountOldTotalTb) * 1000
						: 0.0d;
			}
			if (rateTotalTbTemp > 0.0d && rateTotalTbTemp < 0.01d) {
				rateTotalTb = "0.01";
			} else {
				rateTotalTb = df.format(rateTotalTbTemp);
			}
			BigDecimal big = new BigDecimal(amountTotalTb);
			amountTotalTbStr = big.toString();

			BigDecimal bigOld = new BigDecimal(amountOldTotalTb);
			amountOldTotalTbStr = bigOld.toString();

			dataObjectTb += "{ 'AreaDate': '总计', 'October': '"
					+ amountOldTotalTbStr + "', 'November': '"
					+ amountTotalTbStr + "', 'tbGrowthRate': '" + rateTotalTb
					+ "‰', 'id': '9' }";
			dataObjectTb += "]";
			chartPageShowVo.setDataListTb(dataObjectTb);
		}
	}

	private void getYearData() {
		yearOld = year - 1;
		monthOld = (month - 1) == 0 ? 12 : month - 1;
	}

	/**
	 * 维度值
	 */
	@Action(value = "findDimensionCombinationInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"chartPageShowVo", "excludeNullProperties", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findDimensionCombinationInfo() {
		try {
			String modelKeyName = "hstaff";
			if (!StringUtil.isStringAvaliable(dimensionName)) {// 为空 单维度查询
				combinationCycles = dimensionCombinationCycleDubboService
						.countOwnDimensionCombinationCycle(orgId, modelKeyName,
								dimensionKeyName, year, month);
				if (combinationCycles != null && !combinationCycles.isEmpty()) {
					// setPieData(combinationCycles, dimensionKeyName);
					setBarData(combinationCycles, dimensionKeyName);
				}
			} else {
				combinationCycles = dimensionCombinationCycleDubboService
						.countUnderDimensionCombinationCycle(keyNameLeft,
								keyNameRight, dimensionName, orgId,
								modelKeyName, dimensionKeyName, year, month);
				if (combinationCycles != null && !combinationCycles.isEmpty()) {
					// setPieData(combinationCycles, dimensionKeyName);
					setBarData(combinationCycles, dimensionKeyName);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "操作失败，请联系管理员";
			return ERROR;
		}
		return SUCCESS;
	}

	// 拼装饼状图数据
	private void setPieData(List<DimensionCombinationCycle> combinationCycles,
			String dimensionKeyName) {
		String keyName = selectMoudleName(dimensionKeyName);
		String barChartName = "[";
		String pieData = "";
		for (int i = 0; i < combinationCycles.size(); i++) {
			String dimenName = combinationCycles.get(i).getDimensionName1();
			Long amount = combinationCycles.get(i).getAmount();
			if (i != combinationCycles.size() - 1) {
				pieData += "{value:" + amount + ", name:'" + dimenName + "'},";
				barChartName += "'" + dimenName + "',";
			} else {
				pieData += "{value:" + amount + ", name:'" + dimenName + "'}";
				barChartName += "'" + dimenName + "'";
			}
		}
		barChartName += "]";
		String cycleData = "{legend: { x : 'center', y : 'bottom', data:"
				+ barChartName + "},";
		cycleData += " series : [{name:'"
				+ keyName
				+ "',type:'pie',radius : [30, 60],center : ['45%', 120],roseType : 'area', x: '50%',max: 40,sort : 'ascending',";
		cycleData += " data:[" + pieData + "]}]}";
		chartPageShowVo.setMosaic(cycleData);
	}

	// 柱状图数据
	private void setBarData(List<DimensionCombinationCycle> combinationCycles,
			String dimensionKeyName) {
		String[] dimensionName = new String[combinationCycles.size()];
		Long[] dimensionValue = new Long[combinationCycles.size()];
		for (int i = 0; i < combinationCycles.size(); i++) {
			dimensionName[i] = combinationCycles.get(i).getDimensionName1();
			dimensionValue[i] = combinationCycles.get(i).getAmount();
		}
		chartPageShowVo.setDimensionName(dimensionName);
		chartPageShowVo.setDimensionValue(dimensionValue);
	}

	// 柱状图数据拼装
	/*
	 * private void setBarData(List<DimensionCombinationCycle>
	 * combinationCycles, String dimensionKeyName) { String keyName =
	 * selectMoudleName(dimensionKeyName); String barChartAmount = "["; String
	 * barChartName = "["; for (int i = 0; i < combinationCycles.size(); i++) {
	 * String dimenName = combinationCycles.get(i).getDimensionName1(); if (i !=
	 * combinationCycles.size() - 1) { barChartAmount +=
	 * combinationCycles.get(i).getAmount() + ","; barChartName += "'" +
	 * dimenName + "',"; } else { barChartAmount +=
	 * combinationCycles.get(i).getAmount(); barChartName += "'" + dimenName +
	 * "'"; } } barChartAmount += "]"; barChartName += "]"; String mosaicBar =
	 * ""; if (ChartPageConstant.DIMENSIONNAME_SEX.equals(dimensionKeyName) ||
	 * ChartPageConstant.DIMENSIONNAME_MARRY .equals(dimensionKeyName)) {
	 * mosaicBar = " {legend: {data:['" + keyName +
	 * "']},xAxis : [{type : 'category',data : " + barChartName + ""; } else {
	 * mosaicBar = " {legend: {data:['" + keyName +
	 * "']},xAxis : [{type : 'category', axisLabel:{'interval':0,'rotate':-45,'margin':0},data : "
	 * + barChartName + ""; }
	 * 
	 * mosaicBar += "}],series : [{ name:'" + keyName +
	 * "', type:'bar', barMaxWidth:16,data:" + barChartAmount + " }]}";
	 * 
	 * chartPageShowVo.setMosaicBar(mosaicBar); }
	 */

	private String selectMoudleName(String dimensionKeyName) {
		String mName = "";
		if (ChartPageConstant.DIMENSIONNAME_SEX.equals(dimensionKeyName)) {
			mName = "性别";
		} else if (ChartPageConstant.DIMENSIONNAME_AGE.equals(dimensionKeyName)) {
			mName = "年龄";
		} else if (ChartPageConstant.DIMENSIONNAME_SCHOOL
				.equals(dimensionKeyName)) {
			mName = "文化程度";
		} else if (ChartPageConstant.DIMENSIONNAME_MARRY
				.equals(dimensionKeyName)) {
			mName = "婚姻状况";
		}
		return mName;
	}

	private void countRateHb() {
		try {
			String rateHb = "";
			String[] amounts = chartPageShowVo.getAmountHb();
			String[] amountOlds = chartPageShowVo.getAmountOldHb();
			DecimalFormat df = new DecimalFormat("#0.00");
			if (amounts != null && amounts.length != 0 && amountOlds != null
					&& amountOlds.length != 0) {
				for (int i = 0; i < amounts.length; i++) {
					float amoun = Float.parseFloat(amounts[i]);
					float amounOld = Float.parseFloat(amountOlds[i]);
					float rateHbTemp = 0.0f;
					if (amounOld > 0) {
						rateHbTemp = (amoun - amounOld) > 0 ? ((amoun - amounOld) / amounOld) * 1000
								: 0.0f;
					}
					if (i != amounts.length - 1) {
						if (rateHbTemp > 0.0f && rateHbTemp < 0.01f) {
							rateHb += 0.01 + ",";
						} else {
							rateHb += df.format(rateHbTemp) + ",";
						}
					} else {
						if (rateHbTemp > 0.0f && rateHbTemp < 0.01f) {
							rateHb += 0.01;
						} else {
							rateHb += df.format(rateHbTemp);
						}
					}
				}
				chartPageShowVo.setRateHb(rateHb.split(","));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "操作失败，请联系管理员";
		}
	}

	private void getArrayHistoryCyclesHb(List<HistoryCycle> historyCycles) {
		try {
			String orgId = "";
			String orgName = "";
			String amountHb = "";
			for (int i = 0; i < historyCycles.size(); i++) {
				if (i != historyCycles.size() - 1) {
					orgId += historyCycles.get(i).getOrgId() + ",";
					orgName += historyCycles.get(i).getOrgName() + ",";
					amountHb += historyCycles.get(i).getAmount() + ",";
				} else {
					orgId += historyCycles.get(i).getOrgId();
					orgName += historyCycles.get(i).getOrgName();
					amountHb += historyCycles.get(i).getAmount();
				}
			}

			chartPageShowVo.setOrgId(orgId.split(","));
			chartPageShowVo.setOrgName(orgName.split(","));
			chartPageShowVo.setAmountHb(amountHb.split(","));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "操作失败，请联系管理员";
		}
	}

	private void getArrayHistoryCyclesOldHb(List<HistoryCycle> historyCyclesOld) {
		try {
			String amountOldHb = "";
			for (int i = 0; i < historyCyclesOld.size(); i++) {
				if (i != historyCyclesOld.size() - 1) {
					amountOldHb += historyCyclesOld.get(i).getAmount() + ",";
				} else {
					amountOldHb += historyCyclesOld.get(i).getAmount();
				}
			}
			chartPageShowVo.setAmountOldHb(amountOldHb.split(","));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "操作失败，请联系管理员";
		}
	}

	// 同比
	private void countRateTb() {
		try {
			String rateTb = "";
			String[] amounts = chartPageShowVo.getAmountTb();
			String[] amountOlds = chartPageShowVo.getAmountOldTb();
			DecimalFormat df = new DecimalFormat("#0.00");
			if (amounts != null && amounts.length != 0 && amountOlds != null
					&& amountOlds.length != 0) {
				for (int i = 0; i < amounts.length; i++) {
					float amoun = Float.parseFloat(amounts[i]);
					float amounOld = Float.parseFloat(amountOlds[i]);
					float rateTbTemp = 0.0f;
					if (amounOld > 0) {
						rateTbTemp = (amoun - amounOld) > 0 ? ((amoun - amounOld) / amounOld) * 1000
								: 0.0f;
					}
					if (i != amounts.length - 1) {
						if (rateTbTemp > 0.0f && rateTbTemp < 0.01f) {
							rateTb += 0.01 + ",";
						} else {
							rateTb += df.format(rateTbTemp) + ",";
						}
					} else {
						if (rateTbTemp > 0.0f && rateTbTemp < 0.01f) {
							rateTb += 0.01;
						} else {
							rateTb += df.format(rateTbTemp);
						}
					}
				}
				chartPageShowVo.setRateTb(rateTb.split(","));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "操作失败，请联系管理员";
		}
	}

	private void getArrayHistoryCyclesTb(List<HistoryCycle> historyCycles) {
		try {
			String orgId = "";
			String orgName = "";
			String amountTb = "";
			for (int i = 0; i < historyCycles.size(); i++) {
				if (i != historyCycles.size() - 1) {
					orgId += historyCycles.get(i).getOrgId() + ",";
					orgName += historyCycles.get(i).getOrgName() + ",";
					amountTb += historyCycles.get(i).getAmount() + ",";
				} else {
					orgId += historyCycles.get(i).getOrgId();
					orgName += historyCycles.get(i).getOrgName();
					amountTb += historyCycles.get(i).getAmount();
				}
			}
			chartPageShowVo.setOrgId(orgId.split(","));
			chartPageShowVo.setOrgName(orgName.split(","));
			chartPageShowVo.setAmountTb(amountTb.split(","));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "操作失败，请联系管理员";
		}
	}

	private void getArrayHistoryCyclesOldTb(List<HistoryCycle> historyCyclesOld) {
		try {
			String amountOldTb = "";
			for (int i = 0; i < historyCyclesOld.size(); i++) {
				if (i != historyCyclesOld.size() - 1) {
					amountOldTb += historyCyclesOld.get(i).getAmount() + ",";
				} else {
					amountOldTb += historyCyclesOld.get(i).getAmount();
				}
			}
			chartPageShowVo.setAmountOldTb(amountOldTb.split(","));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "操作失败，请联系管理员";
		}
	}

	private void getArrayHistoryCyclesOldTbIsNull(
			List<HistoryCycle> historyCyclesTb) {
		try {
			if (historyCyclesTb != null) {
				String amountOldTb = "";
				for (int i = 0; i < historyCyclesTb.size(); i++) {
					if (i != historyCyclesTb.size() - 1) {
						amountOldTb += 0 + ",";
					} else {
						amountOldTb += 0;
					}
				}
				chartPageShowVo.setAmountOldTb(amountOldTb.split(","));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "操作失败，请联系管理员";
		}
	}

	private void getArrayHistoryCyclesOldHbIsNull(
			List<HistoryCycle> historyCyclesHb) {
		try {
			if (historyCyclesHb != null) {
				String amountOldHb = "";
				for (int i = 0; i < historyCyclesHb.size(); i++) {
					if (i != historyCyclesHb.size() - 1) {
						amountOldHb += 0 + ",";
					} else {
						amountOldHb += 0;
					}
				}
				chartPageShowVo.setAmountOldHb(amountOldHb.split(","));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "操作失败，请联系管理员";
		}
	}

	public HistoryCycle getHistoryCycle() {
		return historyCycle;
	}

	public void setHistoryCycle(HistoryCycle historyCycle) {
		this.historyCycle = historyCycle;
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

	public int getYearOld() {
		return yearOld;
	}

	public void setYearOld(int yearOld) {
		this.yearOld = yearOld;
	}

	public int getMonthOld() {
		return monthOld;
	}

	public void setMonthOld(int monthOld) {
		this.monthOld = monthOld;
	}

	public String getDimensionName() {
		return dimensionName;
	}

	public void setDimensionName(String dimensionName) {
		this.dimensionName = dimensionName;
	}

	public String getDimensionKeyName() {
		return dimensionKeyName;
	}

	public void setDimensionKeyName(String dimensionKeyName) {
		this.dimensionKeyName = dimensionKeyName;
	}

	public List<DimensionCombinationCycle> getCombinationCycles() {
		return combinationCycles;
	}

	public void setCombinationCycles(
			List<DimensionCombinationCycle> combinationCycles) {
		this.combinationCycles = combinationCycles;
	}

	public String getKeyNameLeft() {
		return keyNameLeft;
	}

	public void setKeyNameLeft(String keyNameLeft) {
		this.keyNameLeft = keyNameLeft;
	}

	public String getKeyNameRight() {
		return keyNameRight;
	}

	public ChartPageShowVo getChartPageShowVo() {
		return chartPageShowVo;
	}

	public void setChartPageShowVo(ChartPageShowVo chartPageShowVo) {
		this.chartPageShowVo = chartPageShowVo;
	}

	public void setKeyNameRight(String keyNameRight) {
		this.keyNameRight = keyNameRight;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}
