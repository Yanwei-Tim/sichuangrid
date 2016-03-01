package com.tianque.mobile.baseInfo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.baseInfo.rectificativePerson.controller.SearchRectificativePersonController;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchRectificativePersonVo;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.baseInfo.RectificativePersonMobileAdapter;

/**
 * 手机端：社区矫正人员
 */
@Transactional
@Scope("request")
@Controller("rectificativePersonMobileAdapter")
public class RectificativePersonMobileAdapterImpl extends BaseMobileAction
		implements RectificativePersonMobileAdapter {

	@Autowired
	private SearchRectificativePersonController searchRectificativePersonController;
	private SearchRectificativePersonVo rectificativePersonCondition;
	private Long orgId;

	@Override
	public String findRectificativePersonList() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(new PageInfo<PositiveInfo>());
		}

		if (rectificativePersonCondition == null) {
			rectificativePersonCondition = new SearchRectificativePersonVo();
		}
		setControllerProperties();
		searchRectificativePersonController
				.findRectificativePersonsByQueryCondition();
		gridPage = searchRectificativePersonController.getGridPage();
		return SUCCESS;
	}

	private void setControllerProperties() {
		searchRectificativePersonController
				.setSearchRectificativePersonVo(rectificativePersonCondition);
		searchRectificativePersonController.setOrganizationId(orgId);
		searchRectificativePersonController.setPage(page);
		searchRectificativePersonController.setRows(rows);
		searchRectificativePersonController.setSidx(sidx);
		searchRectificativePersonController.setSord(sord);
	}

	public SearchRectificativePersonVo getRectificativePersonCondition() {
		return rectificativePersonCondition;
	}

	public void setRectificativePersonCondition(
			SearchRectificativePersonVo rectificativePersonCondition) {
		this.rectificativePersonCondition = rectificativePersonCondition;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}
