package com.tianque.mobile.baseInfo.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.unemployedPeople.controller.SearchUnemployedPeopleController;
import com.tianque.baseInfo.unemployedPeople.domain.UnemployedPeople;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchUnemployedPeopleVo;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.baseInfo.UnemployedPeopleAdapter;

@Controller("unemployedPeopleAdapter")
@Scope("prototype")
@Transactional
public class UnemployedPeopleAdapterImpl extends BaseMobileAction implements
		UnemployedPeopleAdapter {
	@Autowired
	protected SearchUnemployedPeopleController searchUnemployedPeopleController;
	private Long orgId;
	private SearchUnemployedPeopleVo searchUnemployedPeopleVo;

	@Override
	public String findUnemployedPeopleList() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
		}
		if (searchUnemployedPeopleVo == null) {
			searchUnemployedPeopleVo = new SearchUnemployedPeopleVo();
		}
		searchUnemployedPeopleController
				.setSearchUnemployedPeopleVo(searchUnemployedPeopleVo);
		searchUnemployedPeopleController.setOrganizationId(orgId);
		searchUnemployedPeopleController.searchUnemployedPeople();
		gridPage = searchUnemployedPeopleController.getGridPage();
		return SUCCESS;
	}

	private PageInfo<UnemployedPeople> emptyPage(int pageSize) {
		PageInfo<UnemployedPeople> pageInfo = new PageInfo<UnemployedPeople>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<UnemployedPeople>());
		return pageInfo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public SearchUnemployedPeopleVo getSearchUnemployedPeopleVo() {
		return searchUnemployedPeopleVo;
	}

	public void setSearchUnemployedPeopleVo(
			SearchUnemployedPeopleVo searchUnemployedPeopleVo) {
		this.searchUnemployedPeopleVo = searchUnemployedPeopleVo;
	}

}
