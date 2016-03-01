package com.tianque.working.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.User;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.domain.WorkDiary;
import com.tianque.working.service.WorkDiaryService;

@Controller("workDiaryController")
@Scope("prototype")
@SuppressWarnings("serial")
public class WorkDiaryController extends BaseAction {
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private WorkDiaryService workDiaryService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private WorkDiary workDiary;// 工作日志
	private List<PropertyDict> workDiaryTypes;// 日志类型
	private Organization organization;// 部门
	private boolean searchChild;// 过滤条件
	private Long deleteIds;
	/** 要删除的行 */
	private String selectedIds;

	public String dispatch() {
		workDiaryTypes = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.WORKDIARY_DIARY_TYPE);
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			workDiary = new WorkDiary();
			workDiary.setWorkUserName(getCurrentUser().getName());
			workDiary.setOrganization(getCurrentUser().getOrganization());
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)
				|| DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			workDiary = workDiaryService.getWorkDiaryById(workDiary.getId());
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(mode)) {
			return "search";
		} else if ("workBench".equalsIgnoreCase(mode)) {
			workDiary = new WorkDiary();
			workDiary.setWorkUserName(getCurrentUser().getName());
			workDiary.setOrganization(getCurrentUser().getOrganization());
			return mode;
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "addWorkDiary")
	public String addWorkDiary() {
		if (validateInput()) {
			return ERROR;
		}
		workDiary = workDiaryService.addWorkDiary(workDiary);
		return SUCCESS;
	}

	@PermissionFilter(ename = "workDiary")
	public String findWorkDiaryForPageByOrgId() {
		if (null == organization || null == organization.getId()) {
			organization = getCurrentUser().getOrganization();
		}

		PageInfo<WorkDiary> pageInfo = ControllerHelper
				.processAllOrgRelativeName(workDiaryService
						.findWorkDiarysForPage(organization.getId(), rows,
								page, sidx, sord, searchChild),
						organizationDubboService, new String[] { "organization" },
						null);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@PermissionFilter(ename = "updateWorkDiary")
	public String updateWorkDiary() {
		if (validateInput()) {
			return ERROR;
		}
		workDiary = workDiaryService.updateWorkDiary(workDiary);
		return SUCCESS;
	}

	@PermissionFilter(ename = "deleteWorkDiary")
	public String deleteWorkDiaryById() {

		List<Long> deletedIds = getDeletedIds();
		deleteIds = workDiaryService.deleteWorkDiaryById(deletedIds);

		return SUCCESS;
	}

	private List<Long> getDeletedIds() {
		String[] deleteIdStrs = selectedIds.split(",");
		List<Long> deleteIds = new ArrayList<Long>();

		for (String deleteIdStr : deleteIdStrs) {
			Long deleteId = Long.parseLong(deleteIdStr);
			deleteIds.add(deleteId);
		}
		return deleteIds;
	}

	private boolean validateInput() {
		if (workDiary == null || workDiary.getDiaryType() == null
				|| workDiary.getOrganization() == null
				|| workDiary.getOrganization().getId() == null
				|| workDiary.getOrganization().getId().longValue() == 0L
				|| StringUtil.isStringAvaliable(workDiary.getWorkContent())
				|| StringUtil.isStringAvaliable(workDiary.getWorkPlace())
				|| StringUtil.isStringAvaliable(workDiary.getWorkUserName())) {
			this.errorMessage = "参数不正确!";
			return false;
		}
		return true;
	}

	private User getCurrentUser() {
		return permissionService.getFullUserById(ThreadVariable.getSession()
				.getUserId());
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public WorkDiary getWorkDiary() {
		return workDiary;
	}

	public void setWorkDiary(WorkDiary workDiary) {
		this.workDiary = workDiary;
	}

	public Long getDeleteIds() {
		return deleteIds;
	}

	public void setDeleteIds(Long deleteIds) {
		this.deleteIds = deleteIds;
	}

	public String getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(String selectedIds) {
		this.selectedIds = selectedIds;
	}

	public boolean isSearchChild() {
		return searchChild;
	}

	public void setSearchChild(boolean searchChild) {
		this.searchChild = searchChild;
	}

	public List<PropertyDict> getWorkDiaryTypes() {
		return workDiaryTypes;
	}

	public void setWorkDiaryTypes(List<PropertyDict> workDiaryTypes) {
		this.workDiaryTypes = workDiaryTypes;
	}
}
