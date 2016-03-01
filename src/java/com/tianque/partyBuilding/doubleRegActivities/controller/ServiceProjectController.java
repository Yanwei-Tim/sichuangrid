package com.tianque.partyBuilding.doubleRegActivities.controller;

import java.util.List;

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
import com.tianque.partyBuilding.doubleRegActivities.domain.ServiceProject;
import com.tianque.partyBuilding.doubleRegActivities.domain.Vo.SearchServiceProjectVo;
import com.tianque.partyBuilding.doubleRegActivities.service.ServicePojectService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 服务项目表:服务前端控制类
 * 
 * @author
 * @date 2014-02-26 10:02:48
 */
@Namespace("/serviceprojectManage")
@Scope("request")
@Controller("serviceProjectController")
public class ServiceProjectController extends BaseAction {

	@Autowired
	private ServicePojectService servicePojectService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private ServiceProject serviceProject;
	private SearchServiceProjectVo searchServiceProjectVo;
	private String ids;
	private Organization organization;
	private String promptMessage;

	/**
	 * id加密页面跳转
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/partyBuilding/doubleRegActivities/serviceProjectManage/maintainServiceProjectDlg.jsp"),
			@Result(name = "view", location = "/partyBuilding/doubleRegActivities/serviceProjectManage/serviceProjectViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	@EncryptAnnotation
	public String dispatchOperateByEncrypt() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			serviceProject = new ServiceProject();
			organization.setOrgName(organizationDubboService
					.getOrganizationRelativeNameByRootOrgIdAndOrgId(
							organization.getId(), OrganizationServiceHelper
									.getRootOrg(organizationDubboService).getId()));
			serviceProject.setOrganization(organization);
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			if (serviceProject == null || serviceProject.getId() == null) {
				return ERROR;
			}
			serviceProject = servicePojectService.get(serviceProject.getId());
			serviceProject.getOrganization().setOrgName(
					organizationDubboService
							.getOrganizationRelativeNameByRootOrgIdAndOrgId(
									serviceProject.getOrganization().getId(),
									OrganizationServiceHelper.getRootOrg(
											organizationDubboService).getId()));

		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return viewServiceProject();
		}
		return SUCCESS;
	}

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/partyBuilding/doubleRegActivities/serviceProjectManage/maintainServiceProjectDlg.jsp"),
			@Result(name = "view", location = "/partyBuilding/doubleRegActivities/serviceProjectManage/serviceProjectViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			serviceProject = new ServiceProject();
			organization.setOrgName(organizationDubboService
					.getOrganizationRelativeNameByRootOrgIdAndOrgId(
							organization.getId(), OrganizationServiceHelper
									.getRootOrg(organizationDubboService).getId()));
			serviceProject.setOrganization(organization);
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			if (serviceProject == null || serviceProject.getId() == null) {
				return ERROR;
			}
			serviceProject = servicePojectService.get(serviceProject.getId());
			serviceProject.getOrganization().setOrgName(
					organizationDubboService
							.getOrganizationRelativeNameByRootOrgIdAndOrgId(
									serviceProject.getOrganization().getId(),
									OrganizationServiceHelper.getRootOrg(
											organizationDubboService).getId()));

		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return viewServiceProject();
		}
		return SUCCESS;
	}

	/**
	 * 查看
	 * 
	 * @return
	 */
	private String viewServiceProject() throws Exception {
		if (serviceProject == null || serviceProject.getId() == null) {
			return ERROR;
		}
		serviceProject = servicePojectService.get(serviceProject.getId());
		serviceProject.getOrganization().setOrgName(
				organizationDubboService
						.getOrganizationRelativeNameByRootOrgIdAndOrgId(
								serviceProject.getOrganization().getId(),
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
	@Action(value = "addServiceProject", results = {
			@Result(name = "success", type = "json", params = { "root",
					"serviceProject", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "addServiceProject")
	public String addServiceProject() throws Exception {
		if (serviceProject == null
				|| !checkOrganization(serviceProject.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		serviceProject.setOrgInternalCode(organizationDubboService.getSimpleOrgById(
				serviceProject.getOrganization().getId()).getOrgInternalCode());
		serviceProject = servicePojectService.add(serviceProject);
		return SUCCESS;
	}

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	@Action(value = "updateServiceProject", results = {
			@Result(name = "success", type = "json", params = { "root",
					"serviceProject", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "updateServiceProject")
	public String updateServiceProject() throws Exception {
		if (serviceProject == null || serviceProject.getId() == null
				|| !checkOrganization(serviceProject.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		serviceProject = servicePojectService.update(serviceProject);
		return SUCCESS;
	}

	/**
	 * id加密根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@EncryptAnnotation
	@Action(value = "deleteServiceProjectByIdsByEncrypt", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteServiceProject")
	public String deleteServiceProjectByIdsByEncrypt() throws Exception {
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
		servicePojectService.deleteServiceProjectByIds(idsLong);
		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deleteServiceProjectByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteServiceProject")
	public String deleteServiceProjectByIds() throws Exception {
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
		servicePojectService.deleteServiceProjectByIds(idsLong);
		return SUCCESS;
	}

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 * 
	 * @return Pager对象
	 */
	@Action(value = "findServiceProjectPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "customizedServicesManagement")
	public String findServiceProjectPagerBySearchVo() throws Exception {
		if (searchServiceProjectVo == null || organization.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Organization org = organizationDubboService.getSimpleOrgById(organization
				.getId());
		if (org == null) {
			this.errorMessage = "网格不存在";
			return ERROR;
		}
		searchServiceProjectVo.setOrgInternalCode(org.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				servicePojectService.findPagerBySearchVo(
						searchServiceProjectVo, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" }, null));
		countClaimedList(gridPage.getRows());
		return SUCCESS;
	}

	/**
	 * 验证是否存在相同的项目名称
	 * 
	 * @return
	 */
	@Action(value = "hasDuplicateProjectName", results = {
			@Result(name = "success", type = "json", params = { "root",
					"promptMessage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String hasDuplicateProjectName() throws Exception {
		if (serviceProject == null || serviceProject.getProjectName() == null) {
			errorMessage = "编号不能为空";
			return ERROR;
		} else {
			promptMessage = servicePojectService.hasDuplicateProjectName(
					serviceProject.getId(), serviceProject.getOrganization()
							.getId(), serviceProject.getProjectName());
		}

		return SUCCESS;
	}

	/**
	 * 统计已认领数
	 * 
	 * @return
	 */

	private void countClaimed(ServiceProject serviceProject) {
		Integer count = servicePojectService.countClaimedByIdAndOrgId(
				serviceProject.getId(), serviceProject.getOrganization()
						.getId());
		serviceProject.setHasClaimedAmount(count);
	}

	private void countClaimedList(List<ServiceProject> serviceProjects) {
		for (int i = 0; i < serviceProjects.size(); i++) {
			countClaimed(serviceProjects.get(i));
		}
	}

	private boolean checkOrganization(Organization org) {
		return (org != null && org.getId() != null);
	}

	public ServiceProject getServiceProject() {
		return serviceProject;
	}

	public void setServiceProject(ServiceProject serviceProject) {
		this.serviceProject = serviceProject;
	}

	public SearchServiceProjectVo getSearchServiceProjectVo() {
		return searchServiceProjectVo;
	}

	public void setSearchServiceProjectVo(
			SearchServiceProjectVo searchServiceProjectVo) {
		this.searchServiceProjectVo = searchServiceProjectVo;
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
