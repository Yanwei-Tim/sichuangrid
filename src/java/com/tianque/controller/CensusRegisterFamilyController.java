package com.tianque.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.CensusRegisterFamily;
import com.tianque.domain.Organization;
import com.tianque.service.CensusRegisterFamilyService;

@SuppressWarnings("serial")
@Controller("censusRegisterFamilyController")
@Scope("prototype")
@Transactional
public class CensusRegisterFamilyController extends BaseAction {

	@Autowired
	private CensusRegisterFamilyService censusRegisterFamilyService;
	private CensusRegisterFamily censusRegisterFamily;
	private Organization organization;

	public String deleteCensusRegisterFamilyById() throws Exception {
		censusRegisterFamilyService.deleteCensusRegisterFamilyById(
				censusRegisterFamily.getId(), censusRegisterFamily
						.getOrganization().getId());
		return SUCCESS;
	}

	public String findCensusRegisterFamilyByOrgId() throws Exception {
		if (organization == null || organization.getId() == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			gridPage = new GridPage(
					censusRegisterFamilyService
							.findCensusRegisterFamilyForPageByOrgId(
									organization.getId(), page, rows));
		}
		return SUCCESS;
	}

	public String getCensusRegisterFamilyById() throws Exception {
		if (censusRegisterFamily != null
				&& censusRegisterFamily.getId() != null) {
			censusRegisterFamily = censusRegisterFamilyService
					.getCensusRegisterFamilyById(censusRegisterFamily.getId());
		}
		return SUCCESS;
	}

	public String updateCensusRegisterFamily() throws Exception {
		censusRegisterFamily = censusRegisterFamilyService
				.updateCensusRegisterFamily(censusRegisterFamily);
		return SUCCESS;
	}

	public String addCensusRegisterFamily() throws Exception {
		censusRegisterFamily = censusRegisterFamilyService
				.addCensusRegisterFamily(censusRegisterFamily);
		return SUCCESS;
	}

	private PageInfo<CensusRegisterFamily> emptyPage(int pageSize) {
		PageInfo<CensusRegisterFamily> pageInfo = new PageInfo<CensusRegisterFamily>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<CensusRegisterFamily>());
		return pageInfo;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public CensusRegisterFamily getCensusRegisterFamily() {
		return censusRegisterFamily;
	}

	public void setCensusRegisterFamily(
			CensusRegisterFamily censusRegisterFamily) {
		this.censusRegisterFamily = censusRegisterFamily;
	}
}
