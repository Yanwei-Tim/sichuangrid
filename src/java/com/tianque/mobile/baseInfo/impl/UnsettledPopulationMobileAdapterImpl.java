package com.tianque.mobile.baseInfo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.unsettledPopulation.controller.UnsettledPopulationSearchController;
import com.tianque.domain.vo.BaseUnsettledPopulationSearchCondition;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.baseInfo.UnsettledPopulationMobileAdapter;

/**
 * 手机端：未落户人员
 */
@Transactional
@Scope("request")
@Controller("unsettledPopulationMobileAdapter")
public class UnsettledPopulationMobileAdapterImpl extends BaseMobileAction
		implements UnsettledPopulationMobileAdapter {
	@Autowired
	private UnsettledPopulationSearchController unsettledPopulationSearchController;
	private Long orgId;
	private BaseUnsettledPopulationSearchCondition searchUnsettledPopulationVo;

	@Override
	public String findUnsettledPopulationList() throws Exception {
		if (orgId == null) {
			this.errorMessage = "参数错误";
			return MOBILE_ERROR;
		}
		if (searchUnsettledPopulationVo == null) {
			searchUnsettledPopulationVo = new BaseUnsettledPopulationSearchCondition();
		}
		setControllerProperties();
		unsettledPopulationSearchController
				.searchUnsettledPopulationForMobile();
		gridPage = unsettledPopulationSearchController.getGridPage();
		return SUCCESS;
	}

	private void setControllerProperties() {
		unsettledPopulationSearchController
				.setUnsettledPopulationCondition(searchUnsettledPopulationVo);
		unsettledPopulationSearchController.setOrgId(orgId);
		unsettledPopulationSearchController.setPage(page);
		unsettledPopulationSearchController.setRows(rows);
		unsettledPopulationSearchController.setSidx(sidx);
		unsettledPopulationSearchController.setSord(sord);
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public BaseUnsettledPopulationSearchCondition getSearchUnsettledPopulationVo() {
		return searchUnsettledPopulationVo;
	}

	public void setSearchUnsettledPopulationVo(
			BaseUnsettledPopulationSearchCondition searchUnsettledPopulationVo) {
		this.searchUnsettledPopulationVo = searchUnsettledPopulationVo;
	}

}
