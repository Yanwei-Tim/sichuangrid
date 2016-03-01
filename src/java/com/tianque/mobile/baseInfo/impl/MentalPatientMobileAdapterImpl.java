package com.tianque.mobile.baseInfo.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.mentalPatient.controller.SearchMentalPatientController;
import com.tianque.baseInfo.mentalPatient.domain.MentalPatient;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchMentalPatientVo;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.baseInfo.MentalPatientMobileAdapter;

@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("mentalPatientMobileAdapter")
public class MentalPatientMobileAdapterImpl extends BaseMobileAction implements
		MentalPatientMobileAdapter {
	@Autowired
	private SearchMentalPatientController searchMentalPatientController;
	private Long orgId;
	private SearchMentalPatientVo searchMentalPatientVo;

	@Override
	public String findMentalPatientList() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		}
		if (searchMentalPatientVo == null) {
			searchMentalPatientVo = new SearchMentalPatientVo();
		}
		setControllerProperties();
		searchMentalPatientController.searchMentalPatient();
		gridPage = searchMentalPatientController.getGridPage();
		return SUCCESS;
	}

	private void setControllerProperties() {
		searchMentalPatientController
				.setSearchMentalPatientVo(searchMentalPatientVo);
		searchMentalPatientController.setOrganizationId(orgId);
		searchMentalPatientController.setPage(page);
		searchMentalPatientController.setRows(rows);
		searchMentalPatientController.setSidx(sidx);
		searchMentalPatientController.setSord(sord);
	}

	private PageInfo<MentalPatient> emptyPage(int pageSize) {
		PageInfo<MentalPatient> pageInfo = new PageInfo<MentalPatient>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<MentalPatient>());
		return pageInfo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public SearchMentalPatientVo getSearchMentalPatientVo() {
		return searchMentalPatientVo;
	}

	public void setSearchMentalPatientVo(
			SearchMentalPatientVo searchMentalPatientVo) {
		this.searchMentalPatientVo = searchMentalPatientVo;
	}

}
