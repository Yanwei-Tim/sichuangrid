package com.tianque.partyBuilding.doubleRegActivities.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.tianque.partyBuilding.doubleRegActivities.domain.PartyOrgReport;
import com.tianque.partyBuilding.doubleRegActivities.domain.ReportHasProject;
import com.tianque.partyBuilding.doubleRegActivities.domain.ServiceProject;
import com.tianque.partyBuilding.doubleRegActivities.domain.Vo.SearchPartyOrgReportVo;
import com.tianque.partyBuilding.doubleRegActivities.service.PartyOrgReportService;
import com.tianque.partyBuilding.doubleRegActivities.service.ReportHasProjectService;
import com.tianque.partyBuilding.doubleRegActivities.service.ServicePojectService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 党组织报到表:服务前端控制类
 * 
 * @author
 * @date 2014-02-25 11:13:54
 */
@Namespace("/partyorgReportManage")
@Scope("request")
@Controller("partyOrgReportController")
public class PartyOrgReportController extends BaseAction {

	private static Logger logger = LoggerFactory
			.getLogger(PartyOrgReportController.class);

	@Autowired
	private PartyOrgReportService partyOrgReportService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ServicePojectService servicePojectService;
	@Autowired
	private ReportHasProjectService reportHasProjectService;

	private PartyOrgReport partyOrgReport;
	private SearchPartyOrgReportVo searchPartyOrgReportVo;
	private String ids;
	private Organization organization;
	private String type;
	private String promptMessage;

	/**
	 * id加密页面跳转
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Actions({ @Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/partyBuilding/doubleRegActivities/partyorgReportManage/maintainPartyOrgReportDlg.jsp"),
			@Result(name = "view", location = "/partyBuilding/doubleRegActivities/partyorgReportManage/partyOrgReportViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperateByEncrypt() {
		if (DialogMode.ADD_MODE.equals(mode)) {
			partyOrgReport = new PartyOrgReport();
			organization.setOrgName(organizationDubboService
					.getOrganizationRelativeNameByRootOrgIdAndOrgId(
							organization.getId(), OrganizationServiceHelper
									.getRootOrg(organizationDubboService)
									.getId()));
			partyOrgReport.setOrganization(organization);
			partyOrgReport.setType(type);
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			if (partyOrgReport == null || partyOrgReport.getId() == null) {
				return ERROR;
			}
			partyOrgReport = partyOrgReportService.get(partyOrgReport.getId());
			partyOrgReport.getOrganization().setOrgName(
					organizationDubboService
							.getOrganizationRelativeNameByRootOrgIdAndOrgId(
									partyOrgReport.getOrganization().getId(),
									OrganizationServiceHelper.getRootOrg(
											organizationDubboService).getId()));
			getClaimServiceProjectName(partyOrgReport);
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return viewPartyOrgReport();
		}
		return SUCCESS;

	}

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/partyBuilding/doubleRegActivities/partyorgReportManage/maintainPartyOrgReportDlg.jsp"),
			@Result(name = "view", location = "/partyBuilding/doubleRegActivities/partyorgReportManage/partyOrgReportViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperate() {
		if (DialogMode.ADD_MODE.equals(mode)) {
			partyOrgReport = new PartyOrgReport();
			organization.setOrgName(organizationDubboService
					.getOrganizationRelativeNameByRootOrgIdAndOrgId(
							organization.getId(), OrganizationServiceHelper
									.getRootOrg(organizationDubboService)
									.getId()));
			partyOrgReport.setOrganization(organization);
			partyOrgReport.setType(type);
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			if (partyOrgReport == null || partyOrgReport.getId() == null) {
				return ERROR;
			}
			partyOrgReport = partyOrgReportService.get(partyOrgReport.getId());
			partyOrgReport.getOrganization().setOrgName(
					organizationDubboService
							.getOrganizationRelativeNameByRootOrgIdAndOrgId(
									partyOrgReport.getOrganization().getId(),
									OrganizationServiceHelper.getRootOrg(
											organizationDubboService).getId()));
			getClaimServiceProjectName(partyOrgReport);
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return viewPartyOrgReport();
		}
		return SUCCESS;

	}

	/**
	 * 查看
	 * 
	 * @return
	 */
	private String viewPartyOrgReport() {
		if (partyOrgReport == null || partyOrgReport.getId() == null) {
			return ERROR;
		}
		partyOrgReport = partyOrgReportService.get(partyOrgReport.getId());
		partyOrgReport.getOrganization().setOrgName(
				organizationDubboService
						.getOrganizationRelativeNameByRootOrgIdAndOrgId(
								partyOrgReport.getOrganization().getId(),
								OrganizationServiceHelper.getRootOrg(
										organizationDubboService).getId()));
		getClaimServiceProjectName(partyOrgReport);
		return "view";
	}

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	@Action(value = "addPartyorgReport", results = {
			@Result(name = "success", type = "json", params = { "root",
					"partyOrgReport", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "addPartyOrgReport")
	public String addPartyorgReport() {
		try {
			if (partyOrgReport == null
					|| !checkOrganization(partyOrgReport.getOrganization())) {
				this.errorMessage = "参数无效!";
				return ERROR;
			}
			partyOrgReport.setOrgInternalCode(organizationDubboService
					.getSimpleOrgById(partyOrgReport.getOrganization().getId())
					.getOrgInternalCode());
			partyOrgReport = partyOrgReportService.add(partyOrgReport);
			getClaimServiceProjectName(partyOrgReport);
		} catch (Exception e) {
			logger.error("新增数据时出现错误：", e);
			this.errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	@Action(value = "updatePartyorgReport", results = {
			@Result(name = "success", type = "json", params = { "root",
					"partyOrgReport", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "updatePartyOrgReport")
	public String updatePartyorgReport() {
		try {
			if (partyOrgReport == null || partyOrgReport.getId() == null
					|| !checkOrganization(partyOrgReport.getOrganization())) {
				this.errorMessage = "参数无效!";
				return ERROR;
			}
			partyOrgReport = partyOrgReportService.update(partyOrgReport);
			getClaimServiceProjectName(partyOrgReport);
		} catch (Exception e) {
			logger.error("更新数据时出现错误：", e);
			this.errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * id加密根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@EncryptAnnotation
	@Action(value = "deletePartyorgReportByIdsByEncrypt", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deletePartyOrgReport")
	public String deletePartyorgReportByIdsByEncrypt() {
		try {
			if (ids == null || "".equals(ids.trim())) {
				this.errorMessage = "请选择要删除的记录!";
				return ERROR;
			}
			Long[] idsLong = splitArray();
			if (idsLong.length == 0) {
				this.errorMessage = "请选择要删除的记录!";
				return ERROR;
			}
			partyOrgReportService.deletePartyorgReportByIdsAndType(idsLong,
					type);
		} catch (Exception e) {
			logger.error("删除数据时出现错误：", e);
			this.errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deletePartyorgReportByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deletePartyOrgReport")
	public String deletePartyorgReportByIds() {
		try {
			if (ids == null || "".equals(ids.trim())) {
				this.errorMessage = "请选择要删除的记录!";
				return ERROR;
			}
			Long[] idsLong = splitArray();
			if (idsLong.length == 0) {
				this.errorMessage = "请选择要删除的记录!";
				return ERROR;
			}
			partyOrgReportService.deletePartyorgReportByIdsAndType(idsLong,
					type);
		} catch (Exception e) {
			logger.error("删除数据时出现错误：", e);
			this.errorMessage = e.getMessage();
			return ERROR;
		}
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
	@Action(value = "findPartyorgReportPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findPartyorgReportPagerBySearchVo() {
		try {
			if (searchPartyOrgReportVo == null || organization.getId() == null) {
				this.errorMessage = "参数错误";
				return ERROR;
			}
			Organization org = organizationDubboService
					.getSimpleOrgById(organization.getId());
			if (org == null) {
				this.errorMessage = "网格不存在";
				return ERROR;
			}
			searchPartyOrgReportVo.setOrgInternalCode(org.getOrgInternalCode());
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					partyOrgReportService.findPagerBySearchVo(
							searchPartyOrgReportVo, page, rows, sidx, sord),
					organizationDubboService, new String[] { "organization" },
					null));
			getClaimServiceProjectNameList(gridPage.getRows());
		} catch (Exception e) {
			logger.error("查询数据时出现错误：", e);
			this.errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 验证是否存在相同的单位名称
	 * 
	 * @return
	 */
	@Action(value = "hasDuplicateName", results = {
			@Result(name = "success", type = "json", params = { "root",
					"promptMessage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String hasDuplicateName() {
		try {
			if (partyOrgReport == null || partyOrgReport.getName() == null) {
				errorMessage = "编号不能为空";
				return ERROR;
			} else {
				promptMessage = partyOrgReportService.hasDuplicateName(
						partyOrgReport.getId(), partyOrgReport
								.getOrganization().getId(), partyOrgReport
								.getName());
			}

		} catch (Exception e) {
			logger.error("校验数据时出现错误：", e);
			this.errorMessage = e.getMessage();
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * id加密关注、取消关注
	 * 
	 * @return
	 */

	@EncryptAnnotation
	@Action(value = "updateEmphasiseByIdByEncrypt", results = {
			@Result(name = "success", type = "json", params = { "root", "ids",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseByIdByEncrypt() {
		try {
			Long[] idsLong = splitArray();
			if (idsLong.length == 0) {
				this.errorMessage = "请选择要关注(或取消关注)的记录!";
				return ERROR;
			}
			partyOrgReportService.updateEmphasiseByIds(idsLong,
					partyOrgReport.getIsEmphasis() ? 1L : 0L,
					partyOrgReport.getLogOutReason(),
					partyOrgReport.getLogOutTime());
		} catch (Exception e) {
			errorMessage = e.getMessage();
			logger.error("错误信息", e);
			return ERROR;
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
	public String updateEmphasiseById() {
		try {
			Long[] idsLong = splitArray();
			if (idsLong.length == 0) {
				this.errorMessage = "请选择要关注(或取消关注)的记录!";
				return ERROR;
			}
			partyOrgReportService.updateEmphasiseByIds(idsLong,
					partyOrgReport.getIsEmphasis() ? 1L : 0L,
					partyOrgReport.getLogOutReason(),
					partyOrgReport.getLogOutTime());
		} catch (Exception e) {
			errorMessage = e.getMessage();
			logger.error("错误信息", e);
			return ERROR;
		}
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

	private boolean checkOrganization(Organization org) {
		return (org != null && org.getId() != null);
	}

	/**
	 * 得到认领服务项目的名称
	 */
	private void getClaimServiceProjectName(PartyOrgReport partyOrgReport) {
		List<ReportHasProject> reportHasProjects = reportHasProjectService
				.getReportHasProjectByReportId(partyOrgReport.getId());
		if (reportHasProjects.size() == 0) {
			return;
		}
		StringBuffer projectName = new StringBuffer();
		StringBuffer projectId = new StringBuffer();
		for (int i = 0; i < reportHasProjects.size(); i++) {
			ServiceProject serviceProject = servicePojectService
					.get(reportHasProjects.get(i).getProjectId());
			projectName.append(serviceProject.getProjectName() + ",");
			projectId.append(serviceProject.getId() + ",");

		}
		partyOrgReport.setClaimServiceProjectName(new String(projectName
				.substring(0, projectName.length() - 1)));
		partyOrgReport.setClaimServiceProjectIds(new String(projectId
				.substring(0, projectId.length() - 1)));

	}

	/**
	 * 得到认领服务项目的所有名称
	 * 
	 * @return
	 */
	private void getClaimServiceProjectNameList(List<PartyOrgReport> list) {
		for (int i = 0; i < list.size(); i++) {
			getClaimServiceProjectName(list.get(i));
		}
	}

	public PartyOrgReport getPartyOrgReport() {
		return partyOrgReport;
	}

	public void setPartyOrgReport(PartyOrgReport partyOrgReport) {
		this.partyOrgReport = partyOrgReport;
	}

	public SearchPartyOrgReportVo getSearchPartyOrgReportVo() {
		return searchPartyOrgReportVo;
	}

	public void setSearchPartyOrgReportVo(
			SearchPartyOrgReportVo searchPartyOrgReportVo) {
		this.searchPartyOrgReportVo = searchPartyOrgReportVo;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPromptMessage() {
		return promptMessage;
	}

	public void setPromptMessage(String promptMessage) {
		this.promptMessage = promptMessage;
	}

}
