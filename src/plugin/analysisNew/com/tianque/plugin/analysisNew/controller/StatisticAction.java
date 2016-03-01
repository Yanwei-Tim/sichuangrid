package com.tianque.plugin.analysisNew.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.core.base.BaseAction;
import com.tianque.plugin.analysisNew.domain.BaseinfoStatisticVo;
import com.tianque.plugin.analysisNew.service.BaseinfoStatisticNewService;

public class StatisticAction extends BaseAction {
	protected int year;
	protected int month;
	protected Long orgId;
	protected List<BaseinfoStatisticVo> statisticList;
	protected String type;
	@Autowired
	private BaseinfoStatisticNewService baseinfoStatisticNewService;

	protected String getBaseInfoStatisticList() throws Exception {
		statisticList = baseinfoStatisticNewService.getStatisticInfoForList(
				orgId, year, month, type, "", null);
		return SUCCESS;
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

}
