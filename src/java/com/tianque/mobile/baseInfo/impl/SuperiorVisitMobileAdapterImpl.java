package com.tianque.mobile.baseInfo.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.nurturesWomen.domain.NurturesWomen;
import com.tianque.baseInfo.superiorVisit.controller.SearchSuperiorVisitController;
import com.tianque.baseInfo.superiorVisit.dao.SearchSuperiorVisitDao;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchSuperiorVisitVo;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.baseInfo.SuperiorVisitMobileAdapter;

@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("SuperiorVisitMobileAdapter")
public class SuperiorVisitMobileAdapterImpl extends BaseMobileAction implements
		SuperiorVisitMobileAdapter {
	private Long orgId;
	private SearchSuperiorVisitVo searchSuperiorVisitVo;
	@Autowired
	protected SearchSuperiorVisitController searchSuperiorVisitController;
	@Autowired
	protected SearchSuperiorVisitDao searchSuperiorVisitDao;

	@Override
	public String findSuperiorVisitList() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
		}
		if (searchSuperiorVisitVo == null) {
			searchSuperiorVisitVo = new SearchSuperiorVisitVo();
		}
		setControllerProperties();
		searchSuperiorVisitController.searchSuperiorVisit();
		gridPage = searchSuperiorVisitController.getGridPage();
		return SUCCESS;
	}

	private void setControllerProperties() {
		searchSuperiorVisitController
				.setSearchSuperiorVisitVo(searchSuperiorVisitVo);
		searchSuperiorVisitController.setOrganizationId(orgId);
		searchSuperiorVisitController.setPage(page);
		searchSuperiorVisitController.setRows(rows);
		searchSuperiorVisitController.setSidx(sidx);
		searchSuperiorVisitController.setSord(sord);
	}

	private PageInfo<NurturesWomen> emptyPage(int pageSize) {
		PageInfo<NurturesWomen> pageInfo = new PageInfo<NurturesWomen>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<NurturesWomen>());
		return pageInfo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public SearchSuperiorVisitVo getSearchSuperiorVisitVo() {
		return searchSuperiorVisitVo;
	}

	public void setSearchSuperiorVisitVo(
			SearchSuperiorVisitVo searchSuperiorVisitVo) {
		this.searchSuperiorVisitVo = searchSuperiorVisitVo;
	}

}
