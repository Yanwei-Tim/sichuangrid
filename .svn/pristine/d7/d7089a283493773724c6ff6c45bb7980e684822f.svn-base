package com.tianque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.dao.SearchKeyAreasOfInvestigationInfoDao;
import com.tianque.domain.vo.SearchKeyAreasOfInvestigationInfo;

@Controller("searchKeyAreasOfInvestigationInfoContrller")
@Scope("prototype")
@Transactional
public class SearchKeyAreasOfInvestigationInfoContrller extends BaseAction {
	public SearchKeyAreasOfInvestigationInfoDao getSearchKeyAreasOfInvestigationInfoDao() {
		return searchKeyAreasOfInvestigationInfoDao;
	}

	public void setSearchKeyAreasOfInvestigationInfoDao(
			SearchKeyAreasOfInvestigationInfoDao searchKeyAreasOfInvestigationInfoDao) {
		this.searchKeyAreasOfInvestigationInfoDao = searchKeyAreasOfInvestigationInfoDao;
	}

	public SearchKeyAreasOfInvestigationInfo getSearchKeyAreasOfInvestigationInfoWorkingRecord() {
		return searchKeyAreasOfInvestigationInfoWorkingRecord;
	}

	public void setSearchKeyAreasOfInvestigationInfoWorkingRecord(
			SearchKeyAreasOfInvestigationInfo searchKeyAreasOfInvestigationInfoWorkingRecord) {
		this.searchKeyAreasOfInvestigationInfoWorkingRecord = searchKeyAreasOfInvestigationInfoWorkingRecord;
	}

	@Autowired
	private SearchKeyAreasOfInvestigationInfoDao searchKeyAreasOfInvestigationInfoDao;
	private SearchKeyAreasOfInvestigationInfo searchKeyAreasOfInvestigationInfoWorkingRecord;

	public String searchKeyAreasOfInvestigationInfos() {
		searchKeyAreasOfInvestigationInfoWorkingRecord.setOrgId(ThreadVariable.getUser()
				.getOrganization().getId());
		gridPage = new GridPage(
				searchKeyAreasOfInvestigationInfoDao.searchKeyAreasOfInvestigationInfo(
						searchKeyAreasOfInvestigationInfoWorkingRecord, page, rows, sidx, sord));
		return SUCCESS;
	}
}
