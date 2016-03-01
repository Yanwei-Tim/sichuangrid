package com.tianque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.dao.SearchSingificantIssuesealDao;
import com.tianque.domain.vo.SearchSingificantIssuesealVo;

@SuppressWarnings("serial")
@Controller("searchSignificantIssuesealController")
@Scope("prototype")
@Transactional
public class SearchSignificantIssuesealController extends BaseAction {
	@Autowired
	private SearchSingificantIssuesealDao searchSingificantIssuesealDao;
	private SearchSingificantIssuesealVo searchSignificantIssuedealWorkingRecord;

	public String searchsingificantIssuedeals() {
		searchSignificantIssuedealWorkingRecord.setOrgId(ThreadVariable.getUser().getOrganization()
				.getId());
		gridPage = new GridPage(searchSingificantIssuesealDao.searchSingificantIssueseal(
				searchSignificantIssuedealWorkingRecord, page, rows, sidx, sord));
		return SUCCESS;

	}

	public SearchSingificantIssuesealDao getSearchSingificantIssuesealDao() {
		return searchSingificantIssuesealDao;
	}

	public void setSearchSingificantIssuesealDao(
			SearchSingificantIssuesealDao searchSingificantIssuesealDao) {
		this.searchSingificantIssuesealDao = searchSingificantIssuesealDao;
	}

	public SearchSingificantIssuesealVo getSearchSignificantIssuedealWorkingRecord() {
		return searchSignificantIssuedealWorkingRecord;
	}

	public void setSearchSignificantIssuedealWorkingRecord(
			SearchSingificantIssuesealVo searchSignificantIssuedealWorkingRecord) {
		this.searchSignificantIssuedealWorkingRecord = searchSignificantIssuedealWorkingRecord;
	}
}
