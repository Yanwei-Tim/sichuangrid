package com.tianque.xichang.working.peopleAspiration.controller;

import java.util.Date;

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
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.keyGenerator.WorkingSerialKeyGenerator;
import com.tianque.xichang.working.domain.WorkingPrefix;
import com.tianque.xichang.working.flow.constant.AccountType;
import com.tianque.xichang.working.flow.service.AccountWarningService;
import com.tianque.xichang.working.peopleAspiration.domain.PeopleAspirations;
import com.tianque.xichang.working.peopleAspiration.domain.vo.SearchPeopleAspirationsVo;
import com.tianque.xichang.working.peopleAspiration.service.PeopleAspirationService;

/**
 * @ClassName: PoorPeopleController
 * @Description: 三本台账-民生诉求台账-控制器
 * @author wangxiaohu wsmalltiger@163.com
 * @date Dec 23, 2013 5:28:08 PM
 */
@Scope("prototype")
@Namespace("/account/peopleAspirationManage")
@Controller("peopleAspirationController")
public class PeopleAspirationController extends BaseAction {
	@Autowired
	private PeopleAspirationService peopleAspirationService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private WorkingSerialKeyGenerator workingSerialKeyGenerator;
	@Autowired
	private AccountWarningService accountWarningService;

	private PeopleAspirations peopleAspirations;
	private SearchPeopleAspirationsVo searchPeopleAspirationsVo;
	private String ids;
	private Organization organization;
	private String exsistedIdCard;
	private String promptMessage;
	private String type;

	/**
	 * 待办台账权限
	 * 
	 * @return
	 */
	@Action(value = "unDoPeopleAspirationList", results = {
			@Result(name = "success", location = "/xichang/working/peopleAspiration/unDoPeopleAspirationsList.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	@PermissionFilter(ename = "unDoPeopleAspirationListManagement")
	public String unDoPeopleAspirationList() throws Exception {
		return SUCCESS;
	}

	/**
	 * 已办台账权限
	 * 
	 * @return
	 */
	@Action(value = "donePeopleAspirationList", results = {
			@Result(name = "success", location = "/xichang/working/peopleAspiration/donePeopleAspirationsList.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	@PermissionFilter(ename = "donePeopleAspirationListManagement")
	public String donePeopleAspirationList() throws Exception {
		return SUCCESS;
	}

	/**
	 * 下辖：待办台账权限
	 * 
	 * @return
	 */
	@Action(value = "unDoChlidPeopleAspirationList", results = {
			@Result(name = "success", location = "/xichang/working/peopleAspiration/unDoChlidPeopleAspirationList.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	@PermissionFilter(ename = "unDoChlidPeopleAspirationListManagement")
	public String unDoChlidPeopleAspirationList() throws Exception {
		return SUCCESS;
	}

	/**
	 * 下辖：已办台账权限
	 * 
	 * @return
	 */
	@Action(value = "doneChlidPeopleAspirationList", results = {
			@Result(name = "success", location = "/xichang/working/peopleAspiration/doneChlidPeopleAspirationList.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	@PermissionFilter(ename = "doneChlidPeopleAspirationListManagement")
	public String doneChlidPeopleAspirationList() throws Exception {
		return SUCCESS;
	}

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/xichang/working/peopleAspiration/peopleAspirationsViewDlg.jsp"),
			@Result(name = "view", location = "/xichang/working/peopleAspiration/viewPeopleAspirationsDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			String serialNumber = workingSerialKeyGenerator
					.getNextKey(WorkingPrefix.PEOPLEASPIRATION);
			peopleAspirations = new PeopleAspirations();
			peopleAspirations.setSerialNumber(serialNumber);
			peopleAspirations.setOrganization(ThreadVariable.getOrganization());
			// 方法没有显式调用
			// organization.setOrgName(organizationDubboService
			// .getOrganizationRelativeNameByRootOrgIdAndOrgId(
			// organization.getId(), OrganizationServiceHelper
			// .getRootOrg(organizationDubboService)
			// .getId()));
			// peopleAspirations.setOrganization(organization);
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			if (peopleAspirations == null || peopleAspirations.getId() == null) {
				return ERROR;
			}
			peopleAspirations = peopleAspirationService.get(peopleAspirations
					.getId());
			peopleAspirations
					.getOrganization()
					.setOrgName(
							organizationDubboService
									.getOrganizationRelativeNameByRootOrgIdAndOrgId(
											peopleAspirations.getOrganization()
													.getId(),
											OrganizationServiceHelper
													.getRootOrg(
															organizationDubboService)
													.getId()));
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return viewPeopleAspiration();
		}
		return SUCCESS;
	}

	/**
	 * 保存实体对象
	 * 
	 * @return
	 */
	@Action(value = "addPeopleAspiration", results = {
			@Result(name = "success", type = "json", params = { "root",
					"peopleAspirations", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "addPeopleAspiration")
	public String addPeopleAspiration() throws Exception {
		if (peopleAspirations == null
				|| !checkOrganization(peopleAspirations.getOrganization())
				|| !checkOrganization(peopleAspirations.getOccurOrg())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		setParam();
		peopleAspirations = peopleAspirationService.add(peopleAspirations);
		return SUCCESS;
	}

	// 设置参数
	private void setParam() {
		peopleAspirations.setOrgInternalCode(organizationDubboService
				.getSimpleOrgById(peopleAspirations.getOrganization().getId())
				.getOrgInternalCode());
		peopleAspirations.setOccurOrgInternalCode(organizationDubboService
				.getSimpleOrgById(peopleAspirations.getOccurOrg().getId())
				.getOrgInternalCode());
		peopleAspirations.setRegistrationTime(new Date());
	}

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	@Action(value = "updatePeopleAspiration", results = {
			@Result(name = "success", type = "json", params = { "root",
					"peopleAspirations", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "updatePeopleAspiration")
	public String updatePeopleAspiration() throws Exception {
		if (peopleAspirations == null || peopleAspirations.getId() == null
				|| !checkOrganization(peopleAspirations.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		peopleAspirations = peopleAspirationService
				.updatePeopleAspirations(peopleAspirations);

		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deletePeopleAspirationByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deletePeopleAspiration")
	public String deletePeopleAspirationByIds() throws Exception {
		if (ids == null || "".equals(ids.trim())) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		String[] idsStr = ids.split(",");
		Long[] idsLong = new Long[idsStr.length];
		String id = null;
		for (int i = 0; i < idsStr.length; i++) {
			id = idsStr[i];
			if (id != null && !"".equals(id.trim())) {
				idsLong[i] = Long.parseLong(idsStr[i]);
			}
		}
		if (idsLong.length == 0) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		peopleAspirationService.delete(idsLong);
		return SUCCESS;
	}

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 * @return Pager对象
	 */
	@PermissionFilters({
			@PermissionFilter(ename = "unDoPeopleAspirationListManagement", actionName = "findPeopleAspirationPagerBySearchVo"),
			@PermissionFilter(ename = "unDoChlidPeopleAspirationListManagement", actionName = "findPeopleAspirationPagerBySearchVo") })
	@Action(value = "findPeopleAspirationPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findPeopleAspirationPagerBySearchVo() throws Exception {
		if (!appendPagerCommonParams()) {
			return ERROR;
		}
		searchPeopleAspirationsVo.setIsFinish(0);
		if (type != null && type.equalsIgnoreCase("chlid")) {
			searchPeopleAspirationsVo
					.setOrgInternalCode(searchPeopleAspirationsVo
							.getOrgInternalCode());// 占位符，过滤本级数据
			searchPeopleAspirationsVo.setOrgId(null);// 过滤本级数据
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				peopleAspirationService.findPagerBySearchVo(
						searchPeopleAspirationsVo, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization",
						"occurOrg" }, null));
		accountWarningService.fillUnDoEaringWarn(
				searchPeopleAspirationsVo.getOrgId(),
				searchPeopleAspirationsVo.getOrgInternalCode(), gridPage,
				searchPeopleAspirationsVo.getIsFinish(),
				AccountType.PEOPLEASPIRATION);
		return SUCCESS;
	}

	/**
	 * 已办：根据SearchVo进行查询(提供分页、查找、排序功能)
	 */
	@PermissionFilters({
			@PermissionFilter(ename = "donePeopleAspirationListManagement", actionName = "findDonePeopleAspirationPagerBySearchVo"),
			@PermissionFilter(ename = "doneChlidPeopleAspirationListManagement", actionName = "findDonePeopleAspirationPagerBySearchVo") })
	@Action(value = "findDonePeopleAspirationPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findDonePeopleAspirationPagerBySearchVo() throws Exception {
		if (!appendPagerCommonParams()) {
			return ERROR;
		}
		searchPeopleAspirationsVo.setIsFinish(1);
		if (type != null && type.equalsIgnoreCase("chlid")) {
			searchPeopleAspirationsVo
					.setOrgInternalCode(searchPeopleAspirationsVo
							.getOrgInternalCode());// 占位符，过滤本级数据
			searchPeopleAspirationsVo.setOrgId(null);// 过滤本级数据
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				peopleAspirationService.findDonePagerBySearchVo(
						searchPeopleAspirationsVo, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization",
						"occurOrg" }, null));
		accountWarningService.fillDoneEaringWarn(
				searchPeopleAspirationsVo.getOrgId(),
				searchPeopleAspirationsVo.getOrgInternalCode(), gridPage,
				searchPeopleAspirationsVo.getIsFinish(),
				AccountType.PEOPLEASPIRATION);
		return SUCCESS;
	}

	private boolean appendPagerCommonParams() {
		if (organization.getId() == null) {
			this.errorMessage = "参数错误";
			return false;
		}
		Organization org = organizationDubboService
				.getSimpleOrgById(organization.getId());
		if (org == null) {
			this.errorMessage = "网格不存在";
			return false;

		}
		searchPeopleAspirationsVo.setOrgInternalCode(org.getOrgInternalCode());
		searchPeopleAspirationsVo.setOrgId(org.getId());
		searchPeopleAspirationsVo.setAccountType(AccountType.PEOPLEASPIRATION);
		return true;
	}

	/**
	 * 查看
	 * 
	 * @return
	 */
	private String viewPeopleAspiration() throws Exception {
		if (peopleAspirations == null || peopleAspirations.getId() == null) {
			return ERROR;
		}
		peopleAspirations = peopleAspirationService.get(peopleAspirations
				.getId());
		peopleAspirations.getOccurOrg().setOrgName(
				organizationDubboService
						.getOrganizationRelativeNameByRootOrgIdAndOrgId(
								peopleAspirations.getOccurOrg().getId(),
								OrganizationServiceHelper.getRootOrg(
										organizationDubboService).getId()));
		return "view";
	}

	/**
	 * 验证是否存在相同的身份证
	 */
	@Action(value = "exsistedIdCard", results = {
			@Result(name = "success", type = "json", params = { "root",
					"exsistedIdCard", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String exsistedIdCard() throws Exception {
		if (peopleAspirations == null
				|| peopleAspirations.getIdCardNo() == null) {
			errorMessage = "身份证不能为空";
			return ERROR;
		} else {
			exsistedIdCard = peopleAspirationService
					.exsistedIdCardByIdAndIdCardNo(peopleAspirations.getId(),
							peopleAspirations.getOrganization().getId(),
							peopleAspirations.getIdCardNo());
		}
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
		if (peopleAspirations == null
				|| peopleAspirations.getSerialNumber() == null) {
			errorMessage = "编号不能为空";
			return ERROR;
		} else {
			promptMessage = peopleAspirationService.hasDuplicateSerialNumber(
					peopleAspirations.getId(), peopleAspirations
							.getOrganization().getId(), peopleAspirations
							.getSerialNumber());
		}
		return SUCCESS;
	}

	// 验证组织机构
	private boolean checkOrganization(Organization org) {
		return (org != null && org.getId() != null);
	}

	public PeopleAspirations getPeopleAspirations() {
		return peopleAspirations;
	}

	public void setPeopleAspirations(PeopleAspirations peopleAspirations) {
		this.peopleAspirations = peopleAspirations;
	}

	public SearchPeopleAspirationsVo getSearchPeopleAspirationsVo() {
		return searchPeopleAspirationsVo;
	}

	public void setSearchPeopleAspirationsVo(
			SearchPeopleAspirationsVo searchPeopleAspirationsVo) {
		this.searchPeopleAspirationsVo = searchPeopleAspirationsVo;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getExsistedIdCard() {
		return exsistedIdCard;
	}

	public void setExsistedIdCard(String exsistedIdCard) {
		this.exsistedIdCard = exsistedIdCard;
	}

	public String getPromptMessage() {
		return promptMessage;
	}

	public void setPromptMessage(String promptMessage) {
		this.promptMessage = promptMessage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
