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
import com.tianque.partyBuilding.doubleRegActivities.domain.VolunteerJobs;
import com.tianque.partyBuilding.doubleRegActivities.domain.Vo.SearchVolunteerJobsVo;
import com.tianque.partyBuilding.doubleRegActivities.service.VolunteerJobsService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 志愿者岗位表:服务前端控制类
 * 
 * @author
 * @date 2014-02-27 10:06:23
 */
@Namespace("/volunteerjobsManage")
@Scope("request")
@Controller("volunteerJobsController")
public class VolunteerJobsController extends BaseAction {

	@Autowired
	private VolunteerJobsService volunteerJobsService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private VolunteerJobs volunteerJobs;
	private SearchVolunteerJobsVo searchvolunteerJobsVo;
	private String ids;
	private Organization organization;
	private String promptMessage;

	/**
	 * id加密页面跳转
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/partyBuilding/doubleRegActivities/volunteerJobsManage/maintainVolunteerJobsDlg.jsp"),
			@Result(name = "view", location = "/partyBuilding/doubleRegActivities/volunteerJobsManage/volunteerJobsViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	@EncryptAnnotation
	public String dispatchOperateByEncrypt() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			volunteerJobs = new VolunteerJobs();
			organization.setOrgName(organizationDubboService
					.getOrganizationRelativeNameByRootOrgIdAndOrgId(
							organization.getId(), OrganizationServiceHelper
									.getRootOrg(organizationDubboService).getId()));
			volunteerJobs.setOrganization(organization);
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			if (volunteerJobs == null || volunteerJobs.getId() == null) {
				return ERROR;
			}
			volunteerJobs = volunteerJobsService.get(volunteerJobs.getId());
			volunteerJobs.getOrganization().setOrgName(
					organizationDubboService
							.getOrganizationRelativeNameByRootOrgIdAndOrgId(
									volunteerJobs.getOrganization().getId(),
									OrganizationServiceHelper.getRootOrg(
											organizationDubboService).getId()));
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return viewVolunteerJobs();
		}
		return SUCCESS;
	}

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/partyBuilding/doubleRegActivities/volunteerJobsManage/maintainVolunteerJobsDlg.jsp"),
			@Result(name = "view", location = "/partyBuilding/doubleRegActivities/volunteerJobsManage/volunteerJobsViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			volunteerJobs = new VolunteerJobs();
			organization.setOrgName(organizationDubboService
					.getOrganizationRelativeNameByRootOrgIdAndOrgId(
							organization.getId(), OrganizationServiceHelper
									.getRootOrg(organizationDubboService).getId()));
			volunteerJobs.setOrganization(organization);
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			if (volunteerJobs == null || volunteerJobs.getId() == null) {
				return ERROR;
			}
			volunteerJobs = volunteerJobsService.get(volunteerJobs.getId());
			volunteerJobs.getOrganization().setOrgName(
					organizationDubboService
							.getOrganizationRelativeNameByRootOrgIdAndOrgId(
									volunteerJobs.getOrganization().getId(),
									OrganizationServiceHelper.getRootOrg(
											organizationDubboService).getId()));
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return viewVolunteerJobs();
		}
		return SUCCESS;
	}

	/**
	 * 查看
	 * 
	 * @return
	 */
	private String viewVolunteerJobs() throws Exception {
		if (volunteerJobs == null || volunteerJobs.getId() == null) {
			return ERROR;
		}
		volunteerJobs = volunteerJobsService.get(volunteerJobs.getId());
		volunteerJobs.getOrganization().setOrgName(
				organizationDubboService
						.getOrganizationRelativeNameByRootOrgIdAndOrgId(
								volunteerJobs.getOrganization().getId(),
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
	@Action(value = "addVolunteerJobs", results = {
			@Result(name = "success", type = "json", params = { "root",
					"volunteerJobs", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "addVolunteerJobs")
	public String addVolunteerJobs() throws Exception {
		if (volunteerJobs == null
				|| !checkOrganization(volunteerJobs.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		volunteerJobs.setOrgInternalCode(organizationDubboService.getSimpleOrgById(
				volunteerJobs.getOrganization().getId()).getOrgInternalCode());
		volunteerJobs = volunteerJobsService.add(volunteerJobs);
		return SUCCESS;
	}

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	@Action(value = "updateVolunteerJobs", results = {
			@Result(name = "success", type = "json", params = { "root",
					"volunteerJobs", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "updateVolunteerJobs")
	public String updateVolunteerJobs() throws Exception {
		if (volunteerJobs == null || volunteerJobs.getId() == null
				|| !checkOrganization(volunteerJobs.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		volunteerJobs = volunteerJobsService.update(volunteerJobs);
		return SUCCESS;
	}

	/**
	 * id加密根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deleteVolunteerJobsByIdsByEncrypt", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteVolunteerJobs")
	public String deleteVolunteerJobsByIdsByEncrypt() throws Exception {
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
		volunteerJobsService.deleteVolunteerJobsByIds(idsLong);
		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deleteVolunteerJobsByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteVolunteerJobs")
	public String deleteVolunteerJobsByIds() throws Exception {
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
		volunteerJobsService.deleteVolunteerJobsByIds(idsLong);
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
	@Action(value = "findVolunteerJobsPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findVolunteerJobsPagerBySearchVo() throws Exception {
		if (searchvolunteerJobsVo == null || organization.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Organization org = organizationDubboService.getSimpleOrgById(organization
				.getId());
		if (org == null) {
			this.errorMessage = "网格不存在";
			return ERROR;
		}
		searchvolunteerJobsVo.setOrgInternalCode(org.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				volunteerJobsService.findPagerBySearchVo(searchvolunteerJobsVo,
						page, rows, sidx, sord), organizationDubboService,
				new String[] { "organization" }, null));
		countClaimedList(gridPage.getRows());
		return SUCCESS;
	}

	/**
	 * 验证是否存在相同的志愿者岗位
	 * 
	 * @return
	 */
	@Action(value = "hasDuplicateName", results = {
			@Result(name = "success", type = "json", params = { "root",
					"promptMessage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String hasDuplicateName() throws Exception {
		if (volunteerJobs == null || volunteerJobs.getName() == null) {
			errorMessage = "编号不能为空";
			return ERROR;
		} else {
			promptMessage = volunteerJobsService.hasDuplicateName(volunteerJobs
					.getId(), volunteerJobs.getOrganization().getId(),
					volunteerJobs.getName());
		}

		return SUCCESS;
	}

	/**
	 * 统计已认领数
	 * 
	 * @return
	 */

	private void countClaimed(VolunteerJobs volunteerJobs) {
		Integer count = volunteerJobsService.countClaimedByIdAndOrgId(
				volunteerJobs.getId(), volunteerJobs.getOrganization().getId());
		volunteerJobs.setHasClaimedAmount(count);
	}

	private void countClaimedList(List<VolunteerJobs> volunteerJobsList) {
		for (int i = 0; i < volunteerJobsList.size(); i++) {
			countClaimed(volunteerJobsList.get(i));
		}
	}

	private boolean checkOrganization(Organization org) {
		return (org != null && org.getId() != null);
	}

	public VolunteerJobs getVolunteerJobs() {
		return volunteerJobs;
	}

	public void setVolunteerJobs(VolunteerJobs volunteerJobs) {
		this.volunteerJobs = volunteerJobs;
	}

	public SearchVolunteerJobsVo getSearchvolunteerJobsVo() {
		return searchvolunteerJobsVo;
	}

	public void setSearchvolunteerJobsVo(
			SearchVolunteerJobsVo searchvolunteerJobsVo) {
		this.searchvolunteerJobsVo = searchvolunteerJobsVo;
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
