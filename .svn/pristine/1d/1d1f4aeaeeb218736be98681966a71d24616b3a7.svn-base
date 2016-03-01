package com.tianque.mobile.baseInfo.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.aidNeedPopulation.controller.SearchAidNeedPopulationController;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchAidNeedPopulationVo;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.baseInfo.AidNeedPopulationMobileAdapter;

@Controller("aidNeedPopulationMobileAdapter")
@Scope("prototype")
@Transactional
public class AidNeedPopulationMobileAdapterImpl extends BaseMobileAction implements
		AidNeedPopulationMobileAdapter {
	@Autowired
	private SearchAidNeedPopulationController searchAidNeedPopulationController;
	private Long orgId;
	private SearchAidNeedPopulationVo searchAidNeedPopulationVo;

	@Override
	public String findAidNeedPopulationList() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
		}
		if (searchAidNeedPopulationVo == null) {
			searchAidNeedPopulationVo = new SearchAidNeedPopulationVo();
		}
		setControllerProperties();
		searchAidNeedPopulationController.searchAidNeedPopulation();
		gridPage = searchAidNeedPopulationController.getGridPage();
		return SUCCESS;
	}

	private PageInfo<SearchAidNeedPopulationVo> emptyPage(int pageSize) {
		PageInfo<SearchAidNeedPopulationVo> pageInfo = new PageInfo<SearchAidNeedPopulationVo>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<SearchAidNeedPopulationVo>());
		return pageInfo;
	}

	private void setControllerProperties() {
		searchAidNeedPopulationController.setPage(page);
		searchAidNeedPopulationController.setRows(rows);
		searchAidNeedPopulationController.setSidx(sidx);
		searchAidNeedPopulationController.setSord(sord);
		searchAidNeedPopulationController.setOrganizationId(orgId);
		searchAidNeedPopulationController
				.setSearchAidNeedPopulationVo(searchAidNeedPopulationVo);
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public SearchAidNeedPopulationVo getSearchAidNeedPopulationVo() {
		return searchAidNeedPopulationVo;
	}

	public void setSearchAidNeedPopulationVo(
			SearchAidNeedPopulationVo searchAidNeedPopulationVo) {
		this.searchAidNeedPopulationVo = searchAidNeedPopulationVo;
	}

}
