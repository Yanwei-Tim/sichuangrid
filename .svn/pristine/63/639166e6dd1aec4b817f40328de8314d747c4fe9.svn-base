package com.tianque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.working.dao.SearchWorkBulletinDao;
import com.tianque.working.vo.SearchWorkBulletinVo;

@Controller("searchworkBulletinController")
@SuppressWarnings("serial")
@Scope("prototype")
public class SearchWorkBulletinController extends BaseAction {
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private Long orgId;
	@Autowired
	private SearchWorkBulletinDao searchWorkBulletinDao;
	private SearchWorkBulletinVo searchWorkBulletinVo;

	public OrganizationDubboService getOrganizationDubboService() {
		return organizationDubboService;
	}

	public void setOrganizationDubboService(OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public SearchWorkBulletinDao getSearchWorkBulletinDao() {
		return searchWorkBulletinDao;
	}

	public void setSearchWorkBulletinDao(SearchWorkBulletinDao searchWorkBulletinDao) {
		this.searchWorkBulletinDao = searchWorkBulletinDao;
	}

	@PermissionFilter(ename = "searchWorkBulletin")
	public String searchworkBulletin() {

		if (searchWorkBulletinVo == null) {
			searchWorkBulletinVo = new SearchWorkBulletinVo();
		}
		if (orgId != null) {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org != null) {
				searchWorkBulletinVo.setOrgInternalCode(org.getOrgInternalCode());
			} else {
				searchWorkBulletinVo.setOrgInternalCode(null);
			}
		} else {
			searchWorkBulletinVo.setOrgInternalCode(null);
		}

		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(searchWorkBulletinDao
				.searchWorkBulletin(searchWorkBulletinVo, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" }, orgId));

		return SUCCESS;
	}

	public SearchWorkBulletinVo getSearchWorkBulletinVo() {
		return searchWorkBulletinVo;
	}

	public void setSearchWorkBulletinVo(SearchWorkBulletinVo searchWorkBulletinVo) {
		this.searchWorkBulletinVo = searchWorkBulletinVo;
	}

}
