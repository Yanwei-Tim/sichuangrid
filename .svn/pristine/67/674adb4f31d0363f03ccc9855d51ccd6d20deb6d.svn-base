package com.tianque.plugin.statistics.vo;

import java.io.Serializable;
import java.util.List;

/***
 * 该类主要是用于任务清单研判的前端查询参数条件封装
 * @author wangchao
 *
 */
public class GeneralStituationSearchVo implements Serializable{

	private String orgStr;
	private List<Long> orgIds;
	private Integer dateSearchType;//时间查询类别(按 月0、季度1、年份2)查询
	private Integer year;//年份
	private Integer month;//月份
	private Integer quarter;//季度
	private Integer particularYear;//查询的年份(全年、上半年、下半年)
	
	private String basicTypeStr;
	private String[] basicType;//主类别(可以选择多项)
	private String detailTypeStr;
	private String[] detailType;//子类别也可以选择多项
	
	private Long[] subType;//详细类别(详细类别全是数据字典，所以全部数据字典ID来传输)
	
	private Integer isSign;//查询数据的状态(0:上报数据 1：签到数据 3:上报和签到数据的对比)

	

	public String getBasicTypeStr() {
		return basicTypeStr;
	}

	public void setBasicTypeStr(String basicTypeStr) {
		this.basicTypeStr = basicTypeStr;
	}

	public String getDetailTypeStr() {
		return detailTypeStr;
	}

	public void setDetailTypeStr(String detailTypeStr) {
		this.detailTypeStr = detailTypeStr;
	}

	public String getOrgStr() {
		return orgStr;
	}

	public void setOrgStr(String orgStr) {
		this.orgStr = orgStr;
	}

	public List<Long> getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(List<Long> orgIds) {
		this.orgIds = orgIds;
	}

	public Integer getDateSearchType() {
		return dateSearchType;
	}

	public void setDateSearchType(Integer dateSearchType) {
		this.dateSearchType = dateSearchType;
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

	public Integer getQuarter() {
		return quarter;
	}

	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}

	public Integer getParticularYear() {
		return particularYear;
	}

	public void setParticularYear(Integer particularYear) {
		this.particularYear = particularYear;
	}

	public String[] getBasicType() {
		return basicType;
	}

	public void setBasicType(String[] basicType) {
		this.basicType = basicType;
	}

	public String[] getDetailType() {
		return detailType;
	}

	public void setDetailType(String[] detailType) {
		this.detailType = detailType;
	}

	public Long[] getSubType() {
		return subType;
	}

	public void setSubType(Long[] subType) {
		this.subType = subType;
	}

	public Integer getIsSign() {
		return isSign;
	}

	public void setIsSign(Integer isSign) {
		this.isSign = isSign;
	}
	
	
}
