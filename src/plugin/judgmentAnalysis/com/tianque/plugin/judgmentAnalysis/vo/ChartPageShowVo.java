package com.tianque.plugin.judgmentAnalysis.vo;

import java.io.Serializable;

public class ChartPageShowVo implements Serializable {

	// 柱状图字符串拼接
	private String mosaicBar;

	// 饼状图拼接字符串
	private String mosaic;

	/**
	 * 同比环比返回数据
	 * 
	 */
	private String[] orgId;
	private String[] orgName;
	private String[] amountHb;
	private String[] amountOldHb;
	private String[] rateHb;

	private String[] amountTb;
	private String[] amountOldTb;
	private String[] rateTb;

	// 环比列表数据
	private String dataListHb;
	// 同比列表数据
	private String dataListTb;

	private int month;
	private int monthOld;
	private int year;
	private int yearOld;

	private String[] orgNameAfter;
	private String[] orgNameAfterValue;

	private String[] dimensionName;
	private Long[] dimensionValue;

	private String proName;

	public String getMosaicBar() {
		return mosaicBar;
	}

	public int getMonth() {
		return month;
	}

	public String[] getDimensionName() {
		return dimensionName;
	}

	public void setDimensionName(String[] dimensionName) {
		this.dimensionName = dimensionName;
	}

	public Long[] getDimensionValue() {
		return dimensionValue;
	}

	public void setDimensionValue(Long[] dimensionValue) {
		this.dimensionValue = dimensionValue;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getMonthOld() {
		return monthOld;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getDataListHb() {
		return dataListHb;
	}

	public void setDataListHb(String dataListHb) {
		this.dataListHb = dataListHb;
	}

	public String getDataListTb() {
		return dataListTb;
	}

	public void setDataListTb(String dataListTb) {
		this.dataListTb = dataListTb;
	}

	public String[] getOrgNameAfterValue() {
		return orgNameAfterValue;
	}

	public void setOrgNameAfterValue(String[] orgNameAfterValue) {
		this.orgNameAfterValue = orgNameAfterValue;
	}

	public String[] getOrgNameAfter() {
		return orgNameAfter;
	}

	public void setOrgNameAfter(String[] orgNameAfter) {
		this.orgNameAfter = orgNameAfter;
	}

	public void setMonthOld(int monthOld) {
		this.monthOld = monthOld;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getYearOld() {
		return yearOld;
	}

	public void setYearOld(int yearOld) {
		this.yearOld = yearOld;
	}

	public String[] getOrgId() {
		return orgId;
	}

	public void setOrgId(String[] orgId) {
		this.orgId = orgId;
	}

	public String[] getOrgName() {
		return orgName;
	}

	public void setOrgName(String[] orgName) {
		this.orgName = orgName;
	}

	public String[] getAmountHb() {
		return amountHb;
	}

	public void setAmountHb(String[] amountHb) {
		this.amountHb = amountHb;
	}

	public String[] getAmountOldHb() {
		return amountOldHb;
	}

	public void setAmountOldHb(String[] amountOldHb) {
		this.amountOldHb = amountOldHb;
	}

	public String[] getRateHb() {
		return rateHb;
	}

	public void setRateHb(String[] rateHb) {
		this.rateHb = rateHb;
	}

	public String[] getAmountTb() {
		return amountTb;
	}

	public void setAmountTb(String[] amountTb) {
		this.amountTb = amountTb;
	}

	public String[] getAmountOldTb() {
		return amountOldTb;
	}

	public void setAmountOldTb(String[] amountOldTb) {
		this.amountOldTb = amountOldTb;
	}

	public String[] getRateTb() {
		return rateTb;
	}

	public void setRateTb(String[] rateTb) {
		this.rateTb = rateTb;
	}

	public void setMosaicBar(String mosaicBar) {
		this.mosaicBar = mosaicBar;
	}

	public String getMosaic() {
		return mosaic;
	}

	public void setMosaic(String mosaic) {
		this.mosaic = mosaic;
	}

}
