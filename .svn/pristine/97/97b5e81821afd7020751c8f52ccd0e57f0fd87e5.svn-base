package com.tianque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchDisputEsexamineDao;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.vo.SearchDisputEsexamine;
import com.tianque.domain.workingRecord.DisputEsexamine;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.service.DailyDirectoryService;

@SuppressWarnings("serial")
@Transactional
@Scope("prototype")
@Controller("searchDisputEsexamineController")
public class SearchDisputEsexamineController extends BaseAction {
	@Autowired
	private SearchDisputEsexamineDao searchDisputEsexamineDao;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;

	private SearchDisputEsexamine searchDisputEsexamine;

	public String searchDisputEsexamine() {
		PageInfo<DisputEsexamine> reportWorkingPage = new PageInfo<DisputEsexamine>();
		String allDailyDirectoryId = "";
		if (searchDisputEsexamine.getDailyDirectoryId() == null) {
			return ERROR;
		}
		List<DailyDirectory> dailyDirectories = dailyDirectoryService
				.findDailySubDirectoryByParentId(searchDisputEsexamine.getDailyDirectoryId());
		if (dailyDirectories.size() == 0) {
			allDailyDirectoryId = "'" + searchDisputEsexamine.getDailyDirectoryId() + "'";
		} else {
			for (int i = 0; i < dailyDirectories.size(); i++) {
				if (i == 0) {
					allDailyDirectoryId = "'" + dailyDirectories.get(i).getId() + "'";
				} else {
					allDailyDirectoryId = allDailyDirectoryId + ",'"
							+ dailyDirectories.get(i).getId() + "'";
				}
			}
		}
		reportWorkingPage = searchDisputEsexamineDao.searchDisputEsexamine(searchDisputEsexamine,
				page, rows, sidx, sord, allDailyDirectoryId);

		List<DisputEsexamine> disputEsexamineList = reportWorkingPage.getResult();
		for (int i = 0; i < disputEsexamineList.size(); i++) {
			DisputEsexamine disputEsexamine = disputEsexamineList.get(i);
			Organization reportOrg = disputEsexamine.getOrganization();
			Organization org = organizationDubboService.getSimpleOrgById(reportOrg.getId());
			disputEsexamine.setOrganization(org);
			if (disputEsexamine.getSubmitState() != null
					&& disputEsexamine.getSubmitState().getId() != null) {
				PropertyDict reportTypeDict = propertyDictService
						.getPropertyDictById(disputEsexamine.getSubmitState().getId());
				disputEsexamine.setSubmitState(reportTypeDict);
			}
		}

		gridPage = new GridPage(reportWorkingPage);
		return SUCCESS;
	}

	public SearchDisputEsexamine getSearchDisputEsexamine() {
		return searchDisputEsexamine;
	}

	public void setSearchDisputEsexamine(SearchDisputEsexamine searchDisputEsexamine) {
		this.searchDisputEsexamine = searchDisputEsexamine;
	}
}
