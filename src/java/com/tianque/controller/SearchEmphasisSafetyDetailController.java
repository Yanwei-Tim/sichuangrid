package com.tianque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchEmphasisSafetyDetailDao;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.vo.SearchEmphasisSafetyDetail;
import com.tianque.domain.workingRecord.EmphasisSafetyDetail;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.service.DailyDirectoryService;

@SuppressWarnings("serial")
@Transactional
@Scope("prototype")
@Controller("searchEmphasisSafetyDetailController")
public class SearchEmphasisSafetyDetailController extends BaseAction {
	@Autowired
	private SearchEmphasisSafetyDetailDao searchEmphasisSafetyDetailDao;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;

	private SearchEmphasisSafetyDetail searchEmphasisSafetyDetail;

	public String searchEmphasisSafetyDetail() {
		PageInfo<EmphasisSafetyDetail> reportWorkingPage = new PageInfo<EmphasisSafetyDetail>();
		String allDailyDirectoryId = "";
		if (searchEmphasisSafetyDetail.getDailyDirectoryId() == null) {
			return ERROR;
		}
		List<DailyDirectory> dailyDirectories = dailyDirectoryService
				.findDailySubDirectoryByParentId(searchEmphasisSafetyDetail.getDailyDirectoryId());
		if (dailyDirectories.size() == 0) {
			allDailyDirectoryId = "'" + searchEmphasisSafetyDetail.getDailyDirectoryId() + "'";
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
		reportWorkingPage = searchEmphasisSafetyDetailDao.searchEmphasisSafetyDetail(
				searchEmphasisSafetyDetail, page, rows, sidx, sord, allDailyDirectoryId);

		List<EmphasisSafetyDetail> emphasisSafetyDetailList = reportWorkingPage.getResult();
		for (int i = 0; i < emphasisSafetyDetailList.size(); i++) {
			EmphasisSafetyDetail emphasisSafetyDetail = emphasisSafetyDetailList.get(i);
			Organization reportOrg = emphasisSafetyDetail.getOrganization();
			Organization org = organizationDubboService.getSimpleOrgById(reportOrg.getId());
			emphasisSafetyDetail.setOrganization(org);
			if (emphasisSafetyDetail.getSubmitState() != null
					&& emphasisSafetyDetail.getSubmitState().getId() != null) {
				PropertyDict reportTypeDict = propertyDictService
						.getPropertyDictById(emphasisSafetyDetail.getSubmitState().getId());
				emphasisSafetyDetail.setSubmitState(reportTypeDict);
			}
		}

		gridPage = new GridPage(reportWorkingPage);
		return SUCCESS;
	}

	public SearchEmphasisSafetyDetail getSearchEmphasisSafetyDetail() {
		return searchEmphasisSafetyDetail;
	}

	public void setSearchEmphasisSafetyDetail(SearchEmphasisSafetyDetail searchEmphasisSafetyDetail) {
		this.searchEmphasisSafetyDetail = searchEmphasisSafetyDetail;
	}
}
