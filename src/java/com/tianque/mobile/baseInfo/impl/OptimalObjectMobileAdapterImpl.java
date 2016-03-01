package com.tianque.mobile.baseInfo.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.optimalObject.controller.SearchOptimalObjectController;
import com.tianque.baseInfo.optimalObject.domain.OptimalObject;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchOptimalObjectVo;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.baseInfo.OptimalObjectMobileAdapter;

@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("optimalObjectMobileAdapter")
public class OptimalObjectMobileAdapterImpl extends BaseMobileAction implements
		OptimalObjectMobileAdapter {
	@Autowired
	private SearchOptimalObjectController searchOptimalObjectController;
	private Long orgId;
	private SearchOptimalObjectVo searchOptimalObjectVo;

	public String findOptimalObjectList() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
		}
		if (searchOptimalObjectVo == null) {
			searchOptimalObjectVo = new SearchOptimalObjectVo();
		}
		setControllerProperties();
		searchOptimalObjectController.findOptimalObjectsByQueryCondition();
		gridPage = searchOptimalObjectController.getGridPage();
		return SUCCESS;
	}

	private void setControllerProperties() {
		searchOptimalObjectController
				.setSearchOptimalObjectVo(searchOptimalObjectVo);
		searchOptimalObjectController.setOrganizationId(orgId);
		searchOptimalObjectController.setPage(page);
		searchOptimalObjectController.setRows(rows);
		searchOptimalObjectController.setSidx(sidx);
		searchOptimalObjectController.setSord(sord);
	}

	private PageInfo<OptimalObject> emptyPage(int pageSize) {
		PageInfo<OptimalObject> pageInfo = new PageInfo<OptimalObject>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<OptimalObject>());
		return pageInfo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public SearchOptimalObjectVo getSearchOptimalObjectVo() {
		return searchOptimalObjectVo;
	}

	public void setSearchOptimalObjectVo(
			SearchOptimalObjectVo searchOptimalObjectVo) {
		this.searchOptimalObjectVo = searchOptimalObjectVo;
	}
}
