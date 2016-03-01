package com.tianque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.ExaminePlan;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.domain.vo.ExamineItemVo;
import com.tianque.service.CommonService;
import com.tianque.service.ExamineCatalogService;
import com.tianque.service.ExaminePlanService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;

@Controller("examinePlanController")
@Scope("prototype")
@Transactional
public class ExaminePlanController extends BaseAction {

	@Autowired
	private ExamineCatalogService examineCatalogService;
	@Autowired
	private ExaminePlanService examinePlanService;
	@Autowired
	private CommonService comonService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private List<ExamineItemVo> examineItemVos;
	private ExaminePlan examinePlan;
	private List<Integer> availableYears;

	public String dispatch() {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			putAvailableYears(null);
			examinePlan = new ExaminePlan();
			examinePlan.setDraftOrganization(getCurrentOrg().getOrgName());
			examineItemVos = examineCatalogService
					.findExamineItemVosByPlanId(null);
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {

			if (examinePlan == null || examinePlan.getId() == null) {
				this.errorMessage = "参数错误";
				return ERROR;
			}
			examinePlan = examinePlanService
					.getSimpleExaminePlanById(examinePlan.getId());
			putAvailableYears(Integer.parseInt(examinePlan.getYear()));
			examineItemVos = examineCatalogService
					.findExamineItemVosByPlanId(examinePlan.getId());
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			if (examinePlan == null || examinePlan.getId() == null) {
				this.errorMessage = "参数错误";
				return ERROR;
			}

			examinePlan = examinePlanService
					.getSimpleExaminePlanById(examinePlan.getId());
			putAvailableYears(Integer.parseInt(examinePlan.getYear()));
			examineItemVos = examineCatalogService
					.findExamineItemVosByPlanId(examinePlan.getId());
		}
		return SUCCESS;
	}

	public String validateExaminePlanYearHasAvailable() {
		putAvailableYears(null);
		if (this.availableYears.size() == 0) {
			return ERROR;
		}
		return SUCCESS;
	}

	private void putAvailableYears(Integer year) {
		this.availableYears = comonService.getUsableYear();
		List<Integer> examinePlanYears = examinePlanService
				.findExaminePlanYears();
		for (int i = 0; i < examinePlanYears.size(); i++) {
			Integer examinePlanYear = examinePlanYears.get(i);
			if (year == null || year != null
					&& (year.intValue() != examinePlanYear.intValue())) {
				availableYears.remove(examinePlanYear);
			}
		}
	}

	@PermissionFilter(ename = "addPlan")
	public String addExaminePlan() {
		examinePlanService.addExaminePlan(examinePlan);
		return SUCCESS;
	}

	@PermissionFilter(ename = "examineRules")
	public String findExaminePlans() {
		setSidx("year");
		PageInfo<ExaminePlan> pageInfo = examinePlanService
				.findExaminePlansForPage(page, rows, sidx, sord);

		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@PermissionFilter(ename = "updatePlan")
	public String updateExaminePlan() {
		examinePlanService.updateExaminePlan(examinePlan);
		return SUCCESS;
	}

	@PermissionFilter(ename = "deletePan")
	public String deleteExaminePlanById() {
		if (examinePlan.getId() == null) {
			errorMessage = "没有指定删除的数据";
			return ERROR;
		}
		examinePlanService.deleteExaminePlanById(examinePlan.getId());
		return SUCCESS;
	}

	public String validateYear() {
		if (examinePlan.getYear() == null) {
			this.errorMessage = "参数不正确";
		}
		if (examinePlanService.exsistedExaminePlanByYear(examinePlan.getYear())) {
			return SUCCESS;
		}
		return ERROR;
	}

	public List<ExamineItemVo> getExamineItemVos() {
		return examineItemVos;
	}

	public void setExamineItemVos(List<ExamineItemVo> examineItemVos) {
		this.examineItemVos = examineItemVos;
	}

	public ExaminePlan getExaminePlan() {
		return examinePlan;
	}

	public void setExaminePlan(ExaminePlan examinePlan) {
		this.examinePlan = examinePlan;
	}

	public List<Integer> getAvailableYears() {
		return availableYears;
	}

	public void setAvailableYears(List<Integer> availableYears) {
		this.availableYears = availableYears;
	}

	private Organization getCurrentOrg() {
		return organizationDubboService.getSimpleOrgById(getCurrentUser()
				.getOrganization().getId());
	}

	private User getCurrentUser() {
		return permissionService.getFullUserById(ThreadVariable.getSession()
				.getUserId());
	}
}
