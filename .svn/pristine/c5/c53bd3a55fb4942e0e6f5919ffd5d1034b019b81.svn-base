package com.tianque.mobile.baseInfo.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.elderlyPeople.controller.SearchElderlyPeopleController;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchElderlyPeopleVo;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.baseInfo.ElderlyPeopleMobileAdapter;

@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("elderlyPeopleMobileAdapter")
public class ElderlyPeopleMobileAdapterImpl extends BaseMobileAction implements
		ElderlyPeopleMobileAdapter {
	@Autowired
	private SearchElderlyPeopleController searchElderlyPeopleController;
	private SearchElderlyPeopleVo searchElderlyPeopleVo;
	private Long orgId;

	@Override
	public String findElderlyPeopleList() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
		}
		if (searchElderlyPeopleVo == null) {
			searchElderlyPeopleVo = new SearchElderlyPeopleVo();
		}
		setControllerProperties();
		searchElderlyPeopleController.searchElderlyPeople();
		gridPage = searchElderlyPeopleController.getGridPage();
		return SUCCESS;
	}

	private PageInfo<SearchElderlyPeopleVo> emptyPage(int pageSize) {
		PageInfo<SearchElderlyPeopleVo> pageInfo = new PageInfo<SearchElderlyPeopleVo>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<SearchElderlyPeopleVo>());
		return pageInfo;
	}

	private void setControllerProperties() {
		searchElderlyPeopleController.setPage(page);
		searchElderlyPeopleController.setRows(rows);
		searchElderlyPeopleController.setSidx(sidx);
		searchElderlyPeopleController.setSord(sord);
		searchElderlyPeopleController.setOrganizationId(orgId);
		searchElderlyPeopleController
				.setSearchElderlyPeopleVo(searchElderlyPeopleVo);
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public SearchElderlyPeopleVo getSearchElderlyPeopleVo() {
		return searchElderlyPeopleVo;
	}

	public void setSearchElderlyPeopleVo(
			SearchElderlyPeopleVo searchElderlyPeopleVo) {
		this.searchElderlyPeopleVo = searchElderlyPeopleVo;
	}

}
