package com.tianque.mobile.baseInfo.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.idleYouth.controller.SearchIdleYouthController;
import com.tianque.baseInfo.idleYouth.domain.IdleYouth;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchIdleYouthVo;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.baseInfo.IdleYouthMobileAdapter;

@Controller("idleYouthMobileAdapter")
@Scope("prototype")
@Transactional
public class IdleYouthMobileAdapterImpl extends BaseMobileAction implements IdleYouthMobileAdapter {
	@Autowired
	private SearchIdleYouthController SearchIdleYouthController;
	private Long orgId;
	private SearchIdleYouthVo idleYouthSearchCondition;

	@Override
	public String findIdleYouthList() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
		}
		if (idleYouthSearchCondition == null) {
			idleYouthSearchCondition = new SearchIdleYouthVo();
		}
		setControllerProperties();
		SearchIdleYouthController.searchIdleYouth();
		gridPage = SearchIdleYouthController.getGridPage();
		return SUCCESS;
	}

	private void setControllerProperties() {
		SearchIdleYouthController
				.setSearchIdleYouthVo(idleYouthSearchCondition);
		SearchIdleYouthController.setOrganizationId(orgId);
		SearchIdleYouthController.setPage(page);
		SearchIdleYouthController.setRows(rows);
		SearchIdleYouthController.setSidx(sidx);
		SearchIdleYouthController.setSord(sord);
	}

	private PageInfo<IdleYouth> emptyPage(int pageSize) {
		PageInfo<IdleYouth> pageInfo = new PageInfo<IdleYouth>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<IdleYouth>());
		return pageInfo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public SearchIdleYouthVo getIdleYouthSearchCondition() {
		return idleYouthSearchCondition;
	}

	public void setIdleYouthSearchCondition(
			SearchIdleYouthVo idleYouthSearchCondition) {
		this.idleYouthSearchCondition = idleYouthSearchCondition;
	}

}
