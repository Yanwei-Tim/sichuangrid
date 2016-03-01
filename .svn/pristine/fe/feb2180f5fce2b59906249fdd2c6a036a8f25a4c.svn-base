package com.tianque.working.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.working.dao.SearchWorkDiaryDao;
import com.tianque.working.domain.WorkDiary;
import com.tianque.working.vo.SearchWorkDiaryVo;

@SuppressWarnings("serial")
@Controller("searchWorkDiaryController")
public class SearchWorkDiaryController extends BaseAction {

	@Autowired
	private SearchWorkDiaryDao searchWorkDiaryDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private SearchWorkDiaryVo searchWorkDiaryVo;
	private boolean searchChild;

	public SearchWorkDiaryVo getSearchWorkDiaryVo() {
		return searchWorkDiaryVo;
	}

	public void setSearchWorkDiaryVo(SearchWorkDiaryVo searchWorkDiaryVo) {
		this.searchWorkDiaryVo = searchWorkDiaryVo;
	}

	public String searchWorkDiary() {
		Organization organization = organizationDubboService.getSimpleOrgById(searchWorkDiaryVo
				.getOrganization().getId());
		searchWorkDiaryVo.setOrgInternalCode(organization.getOrgInternalCode());
		PageInfo<WorkDiary> pageInfo = ControllerHelper.processAllOrgRelativeName(
				searchWorkDiaryDao.searchWorkDiary(searchWorkDiaryVo, page, rows, sidx, sord,
						searchChild), organizationDubboService, new String[] { "organization" },
				searchWorkDiaryVo.getOrganization().getId());
		gridPage = new GridPage(pageInfo);

		return SUCCESS;
	}

	public boolean isSearchChild() {
		return searchChild;
	}

	public void setSearchChild(boolean searchChild) {
		this.searchChild = searchChild;
	}

}
