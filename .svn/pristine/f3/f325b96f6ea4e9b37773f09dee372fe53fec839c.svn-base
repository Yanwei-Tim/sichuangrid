package com.tianque.mobile.baseInfo.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.overseaPersonnel.controller.OverseaPersonnelSearchController;
import com.tianque.baseInfo.overseaPersonnel.domain.OverseaPersonnel;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchOverseaPersonnelVo;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.baseInfo.OverseaPersonnelMobileAdapter;

@Controller("overseaPersonnelMobileAdapter")
@Scope("prototype")
@Transactional
public class OverseaPersonnelMobileAdapterImpl extends BaseMobileAction implements
		OverseaPersonnelMobileAdapter {
	private Long orgId;
	private SearchOverseaPersonnelVo searchOverseaPersonnelVo;
	@Autowired
	protected OverseaPersonnelSearchController overseaPersonnelSearchController;

	public String findOverseaPersonnelList() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
		}
		if (searchOverseaPersonnelVo == null) {
			searchOverseaPersonnelVo = new SearchOverseaPersonnelVo();
		}
		overseaPersonnelSearchController.setOrgId(orgId);
		overseaPersonnelSearchController
				.setSearchOverseaPersonnelVo(searchOverseaPersonnelVo);
		overseaPersonnelSearchController.searchOverseaPersonnel();
		gridPage = overseaPersonnelSearchController.getGridPage();
		return SUCCESS;
	}

	private PageInfo<OverseaPersonnel> emptyPage(int pageSize) {
		PageInfo<OverseaPersonnel> pageInfo = new PageInfo<OverseaPersonnel>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<OverseaPersonnel>());
		return pageInfo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public SearchOverseaPersonnelVo getSearchOverseaPersonnelVo() {
		return searchOverseaPersonnelVo;
	}

	public void setSearchOverseaPersonnelVo(
			SearchOverseaPersonnelVo searchOverseaPersonnelVo) {
		this.searchOverseaPersonnelVo = searchOverseaPersonnelVo;
	}

}
