package com.tianque.publicSecurity.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.publicSecurity.domain.SnapshotSystem;
import com.tianque.publicSecurity.domain.vo.SearchSnapshotSystemVo;
import com.tianque.publicSecurity.service.SnapshotSystemService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 抓拍系统表:服务前端控制类
 * 
 * @author
 * @date 2014-02-11 15:12:12
 */
@Namespace("/snapshotSystemManage")
@Scope("request")
@Controller("snapshotSystemController")
public class SnapshotSystemController extends BaseAction {

	@Autowired
	private SnapshotSystemService snapshotSystemService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private SnapshotSystem snapshotSystem;
	private SearchSnapshotSystemVo searchSnapshotSystemVo;
	private String ids;
	private Organization organization;
	private String promptMessage;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "dispatch", results = {
			@Result(name = "success", location = "/digitalCity/publicSecurity/snapshotSystemManagement/maintainSnapshotSystemDlg.jsp"),
			@Result(name = "view", location = "/digitalCity/publicSecurity/snapshotSystemManagement/snapshotSystemViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			snapshotSystem = new SnapshotSystem();
			organization.setOrgName(organizationDubboService
					.getOrganizationRelativeNameByRootOrgIdAndOrgId(
							organization.getId(), OrganizationServiceHelper
									.getRootOrg(organizationDubboService).getId()));
			snapshotSystem.setOrganization(organization);
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			if (snapshotSystem == null || snapshotSystem.getId() == null) {
				return ERROR;
			}
			snapshotSystem = snapshotSystemService.get(snapshotSystem.getId());
			snapshotSystem.getOrganization().setOrgName(
					organizationDubboService
							.getOrganizationRelativeNameByRootOrgIdAndOrgId(
									snapshotSystem.getOrganization().getId(),
									OrganizationServiceHelper.getRootOrg(
											organizationDubboService).getId()));

		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return viewSnapshotSystem();
		}
		return SUCCESS;
	}

	/**
	 * 页面跳转(解密)
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "dispatchByEncrypt", results = {
			@Result(name = "success", location = "/digitalCity/publicSecurity/snapshotSystemManagement/maintainSnapshotSystemDlg.jsp"),
			@Result(name = "view", location = "/digitalCity/publicSecurity/snapshotSystemManagement/snapshotSystemViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	@EncryptAnnotation
	public String dispatchByEncrypt() throws Exception {
		if (DialogMode.EDIT_MODE.equals(mode)) {
			if (snapshotSystem == null || snapshotSystem.getId() == null) {
				return ERROR;
			}
			snapshotSystem = snapshotSystemService.get(snapshotSystem.getId());
			snapshotSystem.getOrganization().setOrgName(
					organizationDubboService
							.getOrganizationRelativeNameByRootOrgIdAndOrgId(
									snapshotSystem.getOrganization().getId(),
									OrganizationServiceHelper.getRootOrg(
											organizationDubboService).getId()));

		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return viewSnapshotSystem();
		}
		return SUCCESS;
	}

	/**
	 * 查看
	 * 
	 * @return
	 */
	private String viewSnapshotSystem() throws Exception {
		if (snapshotSystem == null || snapshotSystem.getId() == null) {
			return ERROR;
		}
		snapshotSystem = snapshotSystemService.get(snapshotSystem.getId());
		snapshotSystem.getOrganization().setOrgName(
				organizationDubboService
						.getOrganizationRelativeNameByRootOrgIdAndOrgId(
								snapshotSystem.getOrganization().getId(),
								OrganizationServiceHelper.getRootOrg(
										organizationDubboService).getId()));
		return "view";
	}

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	@Action(value = "addSnapshotsystem", results = {
			@Result(name = "success", type = "json", params = { "root",
					"snapshotSystem", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "addSnapshotSystem")
	public String addSnapshotsystem() throws Exception {
		if (snapshotSystem == null
				|| !checkOrganization(snapshotSystem.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		snapshotSystem.setOrgInternalCode(organizationDubboService.getSimpleOrgById(
				snapshotSystem.getOrganization().getId()).getOrgInternalCode());
		snapshotSystem = snapshotSystemService.add(snapshotSystem);
		return SUCCESS;
	}

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	@Action(value = "updateSnapshotsystem", results = {
			@Result(name = "success", type = "json", params = { "root",
					"snapshotSystem", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "updateSnapshotSystem")
	public String updateSnapshotsystem() throws Exception {
		if (snapshotSystem == null || snapshotSystem.getId() == null
				|| !checkOrganization(snapshotSystem.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		snapshotSystem = snapshotSystemService.update(snapshotSystem);
		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deleteSnapshotsystemByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@EncryptAnnotation
	@PermissionFilter(ename = "deleteSnapshotSystem")
	public String deleteSnapshotsystemByIds() throws Exception {
		if (ids == null || "".equals(ids.trim())) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		Long[] idsLong = splitArray();
		if (idsLong.length == 0) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		snapshotSystemService.delete(idsLong);
		return SUCCESS;
	}

	private Long[] splitArray() {
		String[] idsStr = ids.split(",");
		Long[] idsLong = new Long[idsStr.length];
		String id = null;
		for (int i = 0; i < idsStr.length; i++) {
			id = idsStr[i];
			if (id != null && !"".equals(id.trim())) {
				idsLong[i] = Long.parseLong(idsStr[i]);
			}
		}
		return idsLong;
	}

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 * 
	 * @return Pager对象
	 */
	@Action(value = "findSnapshotsystemPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "snapshotSystemManagement")
	public String findSnapshotsystemPagerBySearchVo() throws Exception {
		if (searchSnapshotSystemVo == null || organization.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Organization org = organizationDubboService.getSimpleOrgById(organization
				.getId());
		if (org == null) {
			this.errorMessage = "网格不存在";
			return ERROR;
		}
		searchSnapshotSystemVo.setOrgInternalCode(org.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				snapshotSystemService.findPagerBySearchVo(
						searchSnapshotSystemVo, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" }, null));
		return SUCCESS;
	}

	/**
	 * 验证是否存在相同的编号
	 * 
	 * @return
	 */
	@Action(value = "hasDuplicateSnapshotNo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"promptMessage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String hasDuplicateSnapshotNo() throws Exception {
		if (snapshotSystem == null || snapshotSystem.getSnapshotNo() == null) {
			errorMessage = "编号不能为空";
			return ERROR;
		} else {
			promptMessage = snapshotSystemService.hasDuplicateSnapshotNo(
					snapshotSystem.getId(), snapshotSystem.getOrganization()
							.getId(), snapshotSystem.getSnapshotNo());
		}
		return SUCCESS;
	}

	/**
	 * 关注、取消关注
	 * 
	 * @return
	 */

	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root", "ids",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		Long[] idsLong = splitArray();
		if (idsLong.length == 0) {
			this.errorMessage = "请选择要关注(或取消关注)的记录!";
			return ERROR;
		}
		snapshotSystemService.updateEmphasiseByIds(idsLong,
				snapshotSystem.getIsEmphasis() ? 1L : 0L,
				snapshotSystem.getLogoutReason(),
				snapshotSystem.getLogoutTime());
		return SUCCESS;
	}

	/**
	 * ID加密 取消关注
	 * 
	 * @param org
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "updateEmphasiseByEncryptId", results = {
			@Result(name = "success", type = "json", params = { "root", "ids",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseByEncryptId() throws Exception {
		Long[] idsLong = splitArray();
		if (idsLong.length == 0) {
			this.errorMessage = "请选择要关注(或取消关注)的记录!";
			return ERROR;
		}
		snapshotSystemService.updateEmphasiseByIds(idsLong,
				snapshotSystem.getIsEmphasis() ? 1L : 0L,
				snapshotSystem.getLogoutReason(),
				snapshotSystem.getLogoutTime());
		return SUCCESS;
	}

	private boolean checkOrganization(Organization org) {
		return (org != null && org.getId() != null);
	}

	public SnapshotSystem getSnapshotSystem() {
		return snapshotSystem;
	}

	public void setSnapshotSystem(SnapshotSystem snapshotSystem) {
		this.snapshotSystem = snapshotSystem;
	}

	public SearchSnapshotSystemVo getSearchSnapshotSystemVo() {
		return searchSnapshotSystemVo;
	}

	public void setSearchSnapshotSystemVo(
			SearchSnapshotSystemVo searchSnapshotSystemVo) {
		this.searchSnapshotSystemVo = searchSnapshotSystemVo;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPromptMessage() {
		return promptMessage;
	}

	public void setPromptMessage(String promptMessage) {
		this.promptMessage = promptMessage;
	}

}
