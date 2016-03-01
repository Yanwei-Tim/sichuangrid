package com.tianque.mobile.baseInfo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.positiveInfo.controller.SearchPositiveInfoController;
import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchPositiveInfoVo;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.baseInfo.PositiveInfoMobileAdapter;

/**
 * 手机端：社区矫正
 */
@Transactional
@Scope("request")
@Controller("positiveInfoMobileAdapter")
public class PositiveInfoMobileAdapterImpl extends BaseMobileAction implements
		PositiveInfoMobileAdapter {
	@Autowired
	private SearchPositiveInfoController searchPositiveInfoController;
	private Long orgId;
	private SearchPositiveInfoVo searchPositiveInfoVo;

	@Override
	public String findPositiveInfoList() throws Exception {
		if (orgId == null || orgId == 0L) {
			gridPage = new GridPage(new PageInfo<PositiveInfo>());
			return SUCCESS;
		}
		if (searchPositiveInfoVo == null) {
			searchPositiveInfoVo = new SearchPositiveInfoVo();
		}
		setControllerProperties();
		searchPositiveInfoController.searchPositiveInfo();
		gridPage = searchPositiveInfoController.getGridPage();
		return SUCCESS;
	}

	private void setControllerProperties() {
		searchPositiveInfoController
				.setSearchPositiveInfoVo(searchPositiveInfoVo);
		searchPositiveInfoController.setOrganizationId(orgId);
		searchPositiveInfoController.setPage(page);
		searchPositiveInfoController.setRows(rows);
		searchPositiveInfoController.setSidx(sidx);
		searchPositiveInfoController.setSord(sord);
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public SearchPositiveInfoVo getSearchPositiveInfoVo() {
		return searchPositiveInfoVo;
	}

	public void setSearchPositiveInfoVo(
			SearchPositiveInfoVo searchPositiveInfoVo) {
		this.searchPositiveInfoVo = searchPositiveInfoVo;
	}

}
