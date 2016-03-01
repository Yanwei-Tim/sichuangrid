package com.tianque.mobile.baseInfo.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.handicapped.controller.SearchHandicappedController;
import com.tianque.baseInfo.handicapped.domain.Handicapped;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchHandicappedVo;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.baseInfo.HandicappedMobileAdapter;

@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("handicappedMobileAdapter")
public class HandicappedMobileAdapterImpl extends BaseMobileAction implements
		HandicappedMobileAdapter {
	@Autowired
	private SearchHandicappedController searchHandicappedController;
	private SearchHandicappedVo searchHandicappedVo;
	private Long orgId;

	@Override
	public String findHandicappedList() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
		}
		if (searchHandicappedVo == null) {
			searchHandicappedVo = new SearchHandicappedVo();
		}
		setControllerProperties();
		searchHandicappedController.findHandicappedsByQueryCondition();
		gridPage = searchHandicappedController.getGridPage();
		return SUCCESS;
	}

	private void setControllerProperties() {
		searchHandicappedController.setSearchHandicappedVo(searchHandicappedVo);
		searchHandicappedController.setOrganizationId(orgId);
		searchHandicappedController.setPage(page);
		searchHandicappedController.setRows(rows);
		searchHandicappedController.setSidx(sidx);
		searchHandicappedController.setSord(sord);
	}

	private PageInfo<Handicapped> emptyPage(int pageSize) {
		PageInfo<Handicapped> pageInfo = new PageInfo<Handicapped>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<Handicapped>());
		return pageInfo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public SearchHandicappedVo getSearchHandicappedVo() {
		return searchHandicappedVo;
	}

	public void setSearchHandicappedVo(SearchHandicappedVo searchHandicappedVo) {
		this.searchHandicappedVo = searchHandicappedVo;
	}

}
