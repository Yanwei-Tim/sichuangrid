package com.tianque.mobile.baseInfo.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.druggy.controller.SearchDruggyController;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchDruggyVo;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.baseInfo.DruggyMobileAdapter;

/**
 * 手机：吸毒人员。
 */
@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("druggyMobileAdapter")
public class DruggyMobileAdapterImpl extends BaseMobileAction implements
		DruggyMobileAdapter {
	@Autowired
	private SearchDruggyController searchDruggyController;
	private Long orgId;
	private SearchDruggyVo searchDruggyVo;

	@Override
	public String findDruggyList() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		}
		if (searchDruggyVo == null) {
			searchDruggyVo = new SearchDruggyVo();
		}
		setControllerProperties();
		searchDruggyController.findDruggysByQueryCondition();
		gridPage = searchDruggyController.getGridPage();
		return SUCCESS;
	}

	private PageInfo<SearchDruggyVo> emptyPage(int pageSize) {
		PageInfo<SearchDruggyVo> pageInfo = new PageInfo<SearchDruggyVo>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<SearchDruggyVo>());
		return pageInfo;
	}

	private void setControllerProperties() {
		searchDruggyController.setSearchDruggyVo(searchDruggyVo);
		searchDruggyController.setOrganizationId(orgId);
		searchDruggyController.setPage(page);
		searchDruggyController.setRows(rows);
		searchDruggyController.setSidx(sidx);
		searchDruggyController.setSord(sord);
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public SearchDruggyVo getSearchDruggyVo() {
		return searchDruggyVo;
	}

	public void setSearchDruggyVo(SearchDruggyVo searchDruggyVo) {
		this.searchDruggyVo = searchDruggyVo;
	}

}