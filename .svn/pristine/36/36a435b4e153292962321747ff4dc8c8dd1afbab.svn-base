package com.tianque.xichang.working.steadyWork.controller;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.annotation.PermissionFilters;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.PageInfoUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.util.keyGenerator.WorkingSerialKeyGenerator;
import com.tianque.xichang.working.domain.WorkingPrefix;
import com.tianque.xichang.working.flow.constant.AccountType;
import com.tianque.xichang.working.flow.service.AccountWarningService;
import com.tianque.xichang.working.steadyWork.domain.PeopleInfo;
import com.tianque.xichang.working.steadyWork.domain.SteadyWork;
import com.tianque.xichang.working.steadyWork.domain.vo.PeopleInfoVo;
import com.tianque.xichang.working.steadyWork.service.PeopleInfoService;
import com.tianque.xichang.working.steadyWork.service.SteadyWorkService;
import com.tianque.xichang.working.util.GlobalValueUtil;

@Scope("request")
@Namespace("/account/steadyWorkManage")
@Controller("steadyWorkController")
public class SteadyWorkController extends BaseAction {
	@Autowired
	private SteadyWorkService steadyWorkService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PeopleInfoService peopleInfoService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private WorkingSerialKeyGenerator workingSerialKeyGenerator;
	@Autowired
	private AccountWarningService accountWarningService;

	private SteadyWork steadyWork;
	private PeopleInfo peopleInfo;
	private PeopleInfo secondPeopleInfo;
	private PeopleInfoVo peopleInfoVo;
	private String ids;
	private Organization organization;
	private String promptMessage;
	private Integer state;// 状态,0待办,1已办

	// 我的台账的列表
	@PermissionFilters({
			@PermissionFilter(ename = "unDoSteadyWorkListManagement", actionName = "findUndoSteadyWorkForPageByOrgInternalCode"),
			@PermissionFilter(ename = "doneSteadyWorkListManagement", actionName = "findUndoSteadyWorkForPageByOrgInternalCode") })
	@Action(value = "findUndoSteadyWorkForPageByOrgInternalCode", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findUndoSteadyWorkForPageByOrgInternalCode() throws Exception {
		PageInfo<SteadyWork> pageInfo = ControllerHelper
				.processAllOrgRelativeName(steadyWorkService
						.findUndoSteadyWorkForPageByOrgInternalCode(null,
								ThreadVariable.getOrganization().getId(),
								state, PageInfoUtil.genaratePager(page, rows,
										sidx, sord)), organizationDubboService,
						new String[] { "organization", "occurOrg" }, null);
		setPeopleInfos(pageInfo.getResult());// 设置人员信息
		gridPage = new GridPage(pageInfo);
		setWarning(ThreadVariable.getOrganization().getId(), null);
		return SUCCESS;
	}

	private void setWarning(Long orgId, String orgCode) {
		if (state == 0) {
			accountWarningService.fillUnDoEaringWarn(orgId, orgCode, gridPage,
					state, AccountType.STEADYWORK);
		} else if (state == 1) {
			accountWarningService.fillDoneEaringWarn(orgId, orgCode, gridPage,
					state, AccountType.STEADYWORK);
		}
	}

	// 下辖台账的列表
	@PermissionFilters({
			@PermissionFilter(ename = "unDoChildSteadyWorkListManagement", actionName = "findUnDoChlidSteadyWorkForPageByOrgInternalCode"),
			@PermissionFilter(ename = "doneChildSteadyWorkListManagement", actionName = "findUnDoChlidSteadyWorkForPageByOrgInternalCode") })
	@Action(value = "findUnDoChlidSteadyWorkForPageByOrgInternalCode", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findUnDoChlidSteadyWorkForPageByOrgInternalCode()
			throws Exception {
		if (organization == null || organization.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Organization org = organizationDubboService.getSimpleOrgById(organization
				.getId());
		if (org == null) {
			this.errorMessage = "网格不存在";
			return ERROR;

		}

		steadyWork.setOrgInternalCode(org.getOrgInternalCode());// 占位符，过滤本级数据

		PageInfo<SteadyWork> pageInfo = ControllerHelper
				.processAllOrgRelativeName(steadyWorkService
						.findUndoSteadyWorkForPageByOrgInternalCode(steadyWork,
								null, state, PageInfoUtil.genaratePager(page,
										rows, sidx, sord)),
						organizationDubboService, new String[] { "organization",
								"occurOrg" }, null);
		setPeopleInfos(pageInfo.getResult());// 设置人员信息
		gridPage = new GridPage(pageInfo);
		setWarning(null, org.getOrgInternalCode());
		return SUCCESS;
	}

	// 设置人员信息
	private void setPeopleInfos(List<SteadyWork> list) {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setPeopleInfos(
					peopleInfoService.findPeopleInfoBySteadyWorkId(list.get(i)
							.getId()));
		}

	}

	@Action(value = "addSteadyWork", results = {
			@Result(name = "success", type = "json", params = { "root",
					"steadyWork", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	@PermissionFilter(ename = "addSteadyWork")
	public String addSteadyWork() throws Exception {
		if (steadyWork == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		setParam();
		steadyWork = steadyWorkService.addSteadyWork(steadyWork, peopleInfoVo);
		return SUCCESS;
	}

	// 设置参数
	private void setParam() {
		steadyWork.setOrgInternalCode(organizationDubboService.getSimpleOrgById(
				steadyWork.getOrganization().getId()).getOrgInternalCode());
		steadyWork.setOccurOrgInternalCode(organizationDubboService
				.getSimpleOrgById(steadyWork.getOccurOrg().getId())
				.getOrgInternalCode());
		steadyWork.setRegistrationTime(new Date());
	}

	@Action(value = "updateSteadyWork", results = {
			@Result(name = "success", type = "json", params = { "root",
					"steadyWork", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	@PermissionFilter(ename = "updateSteadyWork")
	public String updateSteadyWork() throws Exception {
		if (steadyWork == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		steadyWork = steadyWorkService.updateSteadyWork(steadyWork,
				peopleInfoVo);
		return SUCCESS;
	}

	@Action(value = "deleteSteadyWorkByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"opreationResult", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	@PermissionFilter(ename = "deleteSteadyWork")
	public String deleteSteadyWorkByIds() throws Exception {
		if (!StringUtil.isStringAvaliable(ids)) {
			errorMessage = "参数错误";
			return ERROR;
		}
		opreationResult = steadyWorkService
				.deleteSteadyWorkByIds(analyzeToList(ids));
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/xichang/working/steadyWork/maintainSteadyWorkDlg.jsp"),
			@Result(name = "view", location = "/xichang/working/steadyWork/steadyWorkViewTab.jsp"),
			@Result(name = "search", location = "/xichang/working/steadyWork/maintainSearchSteadyWorkDlg.jsp") })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			String serialNumber = workingSerialKeyGenerator
					.getNextKey(WorkingPrefix.STEADYWORK);
			steadyWork = new SteadyWork();
			steadyWork.setSerialNumber(serialNumber);
			steadyWork.setOrganization(ThreadVariable.getOrganization());
			steadyWork.setRegistrant(ThreadVariable.getUser().getName());
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			dispachEditMode();
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return "view";
		}
		return constructResultType();
	}

	private void dispachEditMode() {
		if (steadyWork == null || steadyWork.getId() == null) {
			return;
		}
		steadyWork = steadyWorkService.getSteadyWorkById(steadyWork.getId());

		if (steadyWork == null || steadyWork.getOrganization() == null
				|| steadyWork.getOrganization().getId() == null) {
			return;
		}

		steadyWork.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(steadyWork
						.getOrganization().getId(), organizationDubboService));
	}

	/**
	 * 查看
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "viewSteadyWork", results = { @Result(name = "success", location = "/xichang/working/steadyWork/maintainViewSteadyWorkDlg.jsp") }) })
	public String viewSteadyWork() throws Exception {
		if (steadyWork == null || steadyWork.getId() == null) {
			return ERROR;
		}
		steadyWork = steadyWorkService.getSteadyWorkById(steadyWork.getId());

		if (steadyWork == null || steadyWork.getOrganization() == null
				|| steadyWork.getOrganization().getId() == null) {
			return ERROR;
		}

		steadyWork.getOccurOrg().setOrgName(
				ControllerHelper.getOrganizationRelativeName(steadyWork
						.getOccurOrg().getId(), organizationDubboService));
		setAspirationTypeName(steadyWork);
		setWarningTypeName(steadyWork);
		return SUCCESS;
	}

	/**
	 * 根据条件查询稳定工作台账信息
	 * 
	 * @return
	 */
	@PermissionFilters({
			@PermissionFilter(ename = "searchSteadyWork", actionName = "findSteadyWorkForPageByCondition"),
			@PermissionFilter(ename = "searchDoneSteadyWork", actionName = "findSteadyWorkForPageByCondition") })
	@Action(value = "findSteadyWorkForPageByCondition", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findSteadyWorkForPageByCondition() throws Exception {
		if (organization.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Organization org = organizationDubboService.getSimpleOrgById(organization
				.getId());
		if (org == null) {
			this.errorMessage = "网格不存在";
			return ERROR;

		}
		steadyWork.setOrganization(org);
		PageInfo<SteadyWork> pageInfo = ControllerHelper
				.processAllOrgRelativeName(steadyWorkService
						.findSteadyWorkForPageByCondition(steadyWork,
								peopleInfo, state, PageInfoUtil.genaratePager(
										page, rows, sidx, sord)),
						organizationDubboService, new String[] { "organization",
								"occurOrg" }, null);
		setPeopleInfos(pageInfo.getResult());// 设置人员信息
		gridPage = new GridPage(pageInfo);
		setWarning(org.getId(), null);

		return SUCCESS;
	}

	/**
	 * 根据条件查询稳定工作台账信息---下辖
	 * 
	 * @return
	 */
	@PermissionFilters({
			@PermissionFilter(ename = "searchChildSteadyWork", actionName = "findUnDoChlidSteadyWorkForPageByCondition"),
			@PermissionFilter(ename = "searchDoneChildSteadyWork", actionName = "findUnDoChlidSteadyWorkForPageByCondition") })
	@Action(value = "findUnDoChlidSteadyWorkForPageByCondition", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findUnDoChlidSteadyWorkForPageByCondition() throws Exception {
		if (organization.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Organization org = organizationDubboService.getSimpleOrgById(organization
				.getId());
		if (org == null) {
			this.errorMessage = "网格不存在";
			return ERROR;

		}
		steadyWork.setOrgInternalCode(org.getOrgInternalCode());// 占位符，过滤本级数据
		steadyWork.setOrganization(null);
		PageInfo<SteadyWork> pageInfo = ControllerHelper
				.processAllOrgRelativeName(steadyWorkService
						.findSteadyWorkForPageByCondition(steadyWork,
								peopleInfo, state, PageInfoUtil.genaratePager(
										page, rows, sidx, sord)),
						organizationDubboService, new String[] { "organization",
								"occurOrg" }, null);
		setPeopleInfos(pageInfo.getResult());// 设置人员信息
		gridPage = new GridPage(pageInfo);
		setWarning(null, org.getOrgInternalCode());

		return SUCCESS;
	}

	/**
	 * 验证是否存在相同的编号
	 * 
	 * @return
	 */
	@Action(value = "hasDuplicateSerialNumber", results = {
			@Result(name = "success", type = "json", params = { "root",
					"promptMessage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String hasDuplicateSerialNumber() throws Exception {
		if (steadyWork == null || steadyWork.getSerialNumber() == null) {
			errorMessage = "参数错误";
			return ERROR;
		} else {
			promptMessage = steadyWorkService.hasDuplicateSerialNumber(
					steadyWork.getId(), steadyWork.getSerialNumber());
		}
		return SUCCESS;
	}

	@PermissionFilters({
			@PermissionFilter(ename = "unDoSteadyWorkListManagement", actionName = "unDoSteadyWorkList"),
			@PermissionFilter(ename = "doneSteadyWorkListManagement", actionName = "doneSteadyWorkList"),
			@PermissionFilter(ename = "unDoChildSteadyWorkListManagement", actionName = "unDoChildSteadyWorkList"),
			@PermissionFilter(ename = "doneChildSteadyWorkListManagement", actionName = "doneChildSteadyWorkList") })
	@Actions({
			@Action(value = "unDoSteadyWorkList", results = {
					@Result(name = "success", location = "/xichang/working/steadyWork/unDoSteadyWorkList.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }),
			@Action(value = "doneSteadyWorkList", results = {
					@Result(name = "success", location = "/xichang/working/steadyWork/doneSteadyWorkList.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }),
			@Action(value = "unDoChildSteadyWorkList", results = {
					@Result(name = "success", location = "/xichang/working/steadyWork/unDoChildSteadyWorkList.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }),
			@Action(value = "doneChildSteadyWorkList", results = {
					@Result(name = "success", location = "/xichang/working/steadyWork/doneChildSteadyWorkList.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String unDoSteadyWorkList() throws Exception {
		return SUCCESS;
	}

	// 设置诉求类别名称
	private void setAspirationTypeName(SteadyWork steadyWork) {
		String aspirationTypeIds = steadyWork.getAspirationType();
		if (aspirationTypeIds != null) {
			Long[] ids = analyze(aspirationTypeIds);
			StringBuffer aspirationTypeName = new StringBuffer();
			for (int i = 0; i < ids.length; i++) {
				aspirationTypeName.append(propertyDictService
						.getPropertyDictName(ids[i]) + ",");
			}
			steadyWork.setAspirationTypeName(new String(aspirationTypeName
					.length() > 0 ? aspirationTypeName.substring(0,
					aspirationTypeName.length() - 1) : aspirationTypeName
					.toString()));
		}
	}

	// 设置预警级别名称
	private void setWarningTypeName(SteadyWork steadyWork) {
		Integer warningTypeIds = steadyWork.getWarningType();
		if (warningTypeIds != null) {
			String warningTypeName = GlobalValueUtil
					.getWarningTypeValueByKey(Long.valueOf(warningTypeIds));
			steadyWork.setWarningTypeName(warningTypeName);

		}
	}

	public SteadyWork getSteadyWork() {
		return steadyWork;
	}

	public void setSteadyWork(SteadyWork steadyWork) {
		this.steadyWork = steadyWork;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public PeopleInfo getPeopleInfo() {
		return peopleInfo;
	}

	public void setPeopleInfo(PeopleInfo peopleInfo) {
		this.peopleInfo = peopleInfo;
	}

	public PeopleInfo getSecondPeopleInfo() {
		return secondPeopleInfo;
	}

	public void setSecondPeopleInfo(PeopleInfo secondPeopleInfo) {
		this.secondPeopleInfo = secondPeopleInfo;
	}

	public PeopleInfoVo getPeopleInfoVo() {
		return peopleInfoVo;
	}

	public void setPeopleInfoVo(PeopleInfoVo peopleInfoVo) {
		this.peopleInfoVo = peopleInfoVo;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getPromptMessage() {
		return promptMessage;
	}

	public void setPromptMessage(String promptMessage) {
		this.promptMessage = promptMessage;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
