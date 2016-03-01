package com.tianque.mobile.baseInfo.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.dangerousGoodsPractitioner.controller.SearchDangerousGoodsPractitionerController;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchDangerousGoodsPractitioner;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.baseInfo.DangerousGoodsPractitionerMobileAdapter;

@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("dangerousGoodsPractitionerMobileAdapter")
public class DangerousGoodsPractitionerMobileAdapterImpl extends
		BaseMobileAction implements DangerousGoodsPractitionerMobileAdapter {

	private SearchDangerousGoodsPractitioner queryPopulation;
	private Long orgId;
	@Autowired
	private SearchDangerousGoodsPractitionerController searchDangerousGoodsPractitionerController;

	@Override
	public String findDangerousGoodsPractitionerList() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		}
		if (queryPopulation == null) {
			queryPopulation = new SearchDangerousGoodsPractitioner();
		}
		setControllerProperties();
		searchDangerousGoodsPractitionerController
				.searchDangerousGoodsPractitioners();
		gridPage = searchDangerousGoodsPractitionerController.getGridPage();
		return SUCCESS;
	}

	private PageInfo<SearchDangerousGoodsPractitioner> emptyPage(int pageSize) {
		PageInfo<SearchDangerousGoodsPractitioner> pageInfo = new PageInfo<SearchDangerousGoodsPractitioner>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<SearchDangerousGoodsPractitioner>());
		return pageInfo;
	}

	private void setControllerProperties() {
		searchDangerousGoodsPractitionerController.setPage(page);
		searchDangerousGoodsPractitionerController.setRows(rows);
		searchDangerousGoodsPractitionerController.setSidx(sidx);
		searchDangerousGoodsPractitionerController.setSord(sord);
		searchDangerousGoodsPractitionerController.setOrganizationId(orgId);
		searchDangerousGoodsPractitionerController
				.setSearchDangerousGoodsPractitionerVo(queryPopulation);
	}

	public SearchDangerousGoodsPractitioner getQueryPopulation() {
		return queryPopulation;
	}

	public void setQueryPopulation(
			SearchDangerousGoodsPractitioner queryPopulation) {
		this.queryPopulation = queryPopulation;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}
