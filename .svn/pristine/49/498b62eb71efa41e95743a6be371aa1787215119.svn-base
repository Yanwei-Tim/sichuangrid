package com.tianque.eventSource.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.IssueType;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.vo.SearchIssueVoNew;
import com.tianque.eventSource.dao.SearchCommandCenterDao;
import com.tianque.eventSource.service.SearchCommandCenterService;
import com.tianque.issue.constants.IssueTypes;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.state.IssueState;
import com.tianque.service.IssueTypeService;
import com.tianque.state.IssueQueryState;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Scope("request")
@Namespace("/commandCenter/searchCommandManage")
@Controller("searchCommandController")
@Transactional
public class SearchCommandController extends BaseAction {
	private PropertyDict propertyDict;
	private SearchIssueVoNew searchIssueVo;
	private List<IssueType> contradiction;
	private List<IssueType> specialisation;
	private List<IssueType> peopleliveService;
	private List<Long> selContradictionId;
	private List<Long> selSpecialisationId;
	private List<Long> selPeopleliveServiceId;
	private List<Long> selOtherTypeId;

	@Autowired
	private IssueTypeService issueTypeService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SearchCommandCenterService searchCommandCenterService;
	@Autowired
	private SearchCommandCenterDao searchCommandCenterDao;

	@Action(value = "searchCommandCenterIssue", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String searchCommandCenterIssue() throws Exception {
		if (null == searchIssueVo) {
			gridPage = new GridPage(emptyIssuePage(rows));
			return SUCCESS;
		} else {
			searchIssueVo.setDealState(new Long(IssueState.STEPCOMPLETE_CODE));
			preparePageData();
			searchIssueVo.setIssueTypes(transToIssueType());

			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					searchCommandCenterService
							.searchCommandCenterIssueForPageBySearchIssueVoNew(
									searchIssueVo, page, rows, sidx, sord,
									propertyDict), organizationDubboService,
					new String[] { "occurOrg" }, null));
		}
		return SUCCESS;
	}

	private void preparePageData() {
		setContradiction(issueTypeService
				.findIssueTypesByParentName(IssueTypes.CONTRADICTION));
		setSpecialisation(issueTypeService
				.findIssueTypesByParentName(IssueTypes.SECURITYTROUBLE));
		setPeopleliveService(issueTypeService
				.findIssueTypesByParentName(IssueTypes.PEOPLELIVE_SERVICE));
	}

	private PageInfo<IssueNew> emptyIssuePage(int pageSize) {
		PageInfo<IssueNew> pageInfo = new PageInfo<IssueNew>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<IssueNew>());
		return pageInfo;
	}

	private List<IssueType> transToIssueType() {
		List<IssueType> issueTypes = new ArrayList<IssueType>();
		if (selContradictionId != null)
			for (Long issueTypeId : selContradictionId) {
				issueTypes.add(createIssueTypeById(issueTypeId));
			}

		if (selSpecialisationId != null)
			for (Long issueTypeId : selSpecialisationId) {
				issueTypes.add(createIssueTypeById(issueTypeId));
			}

		if (selPeopleliveServiceId != null)
			for (Long issueTypeId : selPeopleliveServiceId) {
				issueTypes.add(createIssueTypeById(issueTypeId));
			}

		if (selOtherTypeId != null)
			for (Long issueTypeId : selOtherTypeId) {
				issueTypes.add(createIssueTypeById(issueTypeId));
			}
		return issueTypes;
	}

	private IssueType createIssueTypeById(Long id) {
		IssueType issueType = new IssueType();
		issueType.setId(id);
		return issueType;
	}

	@Action(value = "searchDoneIssues", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String searchDoneIssues() throws Exception {
		if (null == searchIssueVo) {
			gridPage = new GridPage(emptyIssuePage(rows));
			return SUCCESS;
		}
		List<Long> dealStateList = new ArrayList<Long>();
		dealStateList.add(IssueQueryState.MY_DONE_ONE);
		dealStateList.add(IssueQueryState.MY_DONE_TWO);
		dealStateList.add(IssueQueryState.MY_DONE_THREE);
		searchIssueVo.setDealStateList(dealStateList);
		preparePageData();
		searchIssueVo.setIssueTypes(transToIssueType());
		searchIssueVo.setDealState(new Long(IssueState.STEPCOMPLETE_CODE));
		gridPage = new GridPage(
				ControllerHelper.processAllOrgRelativeName(
						searchCommandCenterDao.searchDoneIssues(searchIssueVo,
								page, rows, sidx, sord), organizationDubboService,
						new String[] { "occurOrg", "lastOrg", "targeOrg",
								"currentOrg" }, null));
		return SUCCESS;
	}

	public void setContradiction(List<IssueType> contradiction) {
		this.contradiction = contradiction;
	}

	public List<IssueType> getContradiction() {
		return contradiction;
	}

	public void setSpecialisation(List<IssueType> specialisation) {
		this.specialisation = specialisation;
	}

	public List<IssueType> getSpecialisation() {
		return specialisation;
	}

	public void setPeopleliveService(List<IssueType> peopleliveService) {
		this.peopleliveService = peopleliveService;
	}

	public List<IssueType> getPeopleliveService() {
		return peopleliveService;
	}

	public List<Long> getSelContradictionId() {
		return selContradictionId;
	}

	public void setSelContradictionId(List<Long> selContradictionId) {
		this.selContradictionId = selContradictionId;
	}

	public List<Long> getSelSpecialisationId() {
		return selSpecialisationId;
	}

	public void setSelSpecialisationId(List<Long> selSpecialisationId) {
		this.selSpecialisationId = selSpecialisationId;
	}

	public List<Long> getSelPeopleliveServiceId() {
		return selPeopleliveServiceId;
	}

	public void setSelPeopleliveServiceId(List<Long> selPeopleliveServiceId) {
		this.selPeopleliveServiceId = selPeopleliveServiceId;
	}

	public PropertyDict getPropertyDict() {
		return propertyDict;
	}

	public void setPropertyDict(PropertyDict propertyDict) {
		this.propertyDict = propertyDict;
	}

	public SearchIssueVoNew getSearchIssueVo() {
		return searchIssueVo;
	}

	public void setSearchIssueVo(SearchIssueVoNew searchIssueVo) {
		this.searchIssueVo = searchIssueVo;
	}

	public List<Long> getSelOtherTypeId() {
		return selOtherTypeId;
	}

	public void setSelOtherTypeId(List<Long> selOtherTypeId) {
		this.selOtherTypeId = selOtherTypeId;
	}
}
