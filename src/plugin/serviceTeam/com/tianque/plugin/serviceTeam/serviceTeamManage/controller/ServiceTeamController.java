package com.tianque.plugin.serviceTeam.serviceTeamManage.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.plugin.serviceTeam.serviceRecord.service.ServiceRecordService;
import com.tianque.plugin.serviceTeam.serviceTeamManage.domain.ServiceTeam;
import com.tianque.plugin.serviceTeam.serviceTeamManage.service.ServiceTeamService;
import com.tianque.plugin.serviceTeam.serviceTeamManage.vo.ServiceTeamVo;
import com.tianque.plugin.serviceTeam.serviceTeamMember.domain.ServiceTeamMember;
import com.tianque.plugin.serviceTeam.serviceTeamMember.service.ServiceTeamMemberService;
import com.tianque.plugin.serviceTeam.serviceTeamMember.vo.ServiceTeamMemberVo;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 服务团队控制类
 * 
 * @author yangpengdian
 */
@Namespace("/plugin/serviceTeam/serviceTeamManage")
@Controller("serviceTeamController")
@Scope("prototype")
@Transactional
public class ServiceTeamController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(ServiceTeamController.class);
	@Autowired
	private OrganizationDubboService orgService;
	@Autowired
	private ServiceTeamService serviceTeamService;
	@Autowired
	private ServiceTeamMemberService serviceTeamMemberService;
	@Autowired
	private ServiceRecordService serviceRecordService;

	/** 服务团队domain类 */
	private ServiceTeam serviceTeam;
	/** 组织机构ID */
	private Long orgId;
	/** 团队类型 */
	private Long teamTypeId;
	/** 要删除或修改的行 */
	private String selectedIds;
	/** 删除的行数 */
	private int deleteCount;
	/** 修改的行数 */
	private int updateCount;
	/** 仅显示本级、所有下辖 、直属下辖 **/
	private String displayLevel;
	/** 所有显示的对象，列表，搜索结果 */
	private ServiceTeamVo serviceTeamVo;
	/** 页面 对话框名称 */
	private String dailogName;
	/** 团队成员VO */
	private ServiceTeamMemberVo serviceTeamMemberVo;
	/** 团队成员详细domain */
	private ServiceTeamMember serviceTeamMember;
	/** 团队记录数量 */
	private int countRecordsNum;
	/** 团队对象数量 */
	private int[] countObjectsNum;
	/** 记录数 */
	private Integer count;
	private boolean pageOnly;

	/** id加密 业务跳转 */
	@EncryptAnnotation
	@Action(value = "dispatchByEncrypt", results = {
			@Result(name = "maintain", location = "/template/serviceTeam/serviceTeamManage/serviceTeamMaintainDlg.ftl"),
			@Result(name = "search", location = "/template/serviceTeam/serviceTeamManage/serviceTeamSearchDlg.ftl"),
			@Result(name = "view", location = "/template/serviceTeam/serviceTeamManage/serviceTeamViewDlg.ftl"),
			@Result(name = "maintainMembers", location = "/template/serviceTeam/serviceTeamManage/membersForServiceTeamMaintainDlg.ftl"),
			@Result(name = "viewMemberList", location = "/template/serviceTeam/serviceTeamManage/viewMemberList.ftl"),
			@Result(name = "logOut", location = "/template/serviceTeam/serviceTeamManage/serviceTeamLogOutDlg.ftl") })
	public String dispatchByEncrypt() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			return "maintain";
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			getServiceTeamById();
			return "maintain";
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			getServiceTeamById();
			return "view";
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			return "search";
		} else if ("maintainMembers".equals(mode)) {
			getServiceTeamById();
			return "maintainMembers";
		} else if ("logOut".equals(mode)) {
			return "logOut";
		} else if ("viewMemberList".equals(mode)) {
			return "viewMemberList";
		}
		return ERROR;
	}

	/** 业务跳转 */
	@Action(value = "dispatch", results = {
			@Result(name = "maintain", location = "/template/serviceTeam/serviceTeamManage/serviceTeamMaintainDlg.ftl"),
			@Result(name = "search", location = "/template/serviceTeam/serviceTeamManage/serviceTeamSearchDlg.ftl"),
			@Result(name = "view", location = "/template/serviceTeam/serviceTeamManage/serviceTeamViewDlg.ftl"),
			@Result(name = "maintainMembers", location = "/template/serviceTeam/serviceTeamManage/membersForServiceTeamMaintainDlg.ftl"),
			@Result(name = "viewMemberList", location = "/template/serviceTeam/serviceTeamManage/viewMemberList.ftl"),
			@Result(name = "logOut", location = "/template/serviceTeam/serviceTeamManage/serviceTeamLogOutDlg.ftl") })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			return "maintain";
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			getServiceTeamById();
			return "maintain";
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			getServiceTeamById();
			return "view";
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			return "search";
		} else if ("maintainMembers".equals(mode)) {
			getServiceTeamById();
			return "maintainMembers";
		} else if ("logOut".equals(mode)) {
			return "logOut";
		} else if ("viewMemberList".equals(mode)) {
			return "viewMemberList";
		}
		return ERROR;
	}

	@PermissionFilter(ename = "downServiceTeam")
	@Action(value = "downServiceTeam")
	public String downloadTeamManage() throws Exception {

		if (serviceTeamVo == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		List<ServiceTeam> records = getNeedExportDatas(page);
		inputStream = ExcelExportHelper.exportDateToExcel(
				serviceTeamService.getExportPopertyArray(), records);
		downloadFileName = new String("服务团队清单".getBytes("gbk"), "ISO8859-1")
				+ ".xls";
		return STREAM_SUCCESS;
	}

	private List<ServiceTeam> getNeedExportDatas(int page) {
		if (pageOnly) {
			return serviceTeamService.searchServiceTeamsForExport(
					serviceTeamVo, page, rows, sidx, sord);
		} else {
			return serviceTeamService.searchServiceTeamsForExport(
					serviceTeamVo, null, null, sidx, sord);
		}
	}

	/**
	 * 查看服务团队
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "addServiceTeam")
	@Action(value = "addServiceTeam", results = { @Result(name = "success", type = "json", params = {
			"root", "serviceTeamVo", "ignoreHierarchy", "false" }) })
	public String addServiceTeam() throws Exception {
		serviceTeamVo = serviceTeamService.addServiceTeam(serviceTeam);
		return SUCCESS;
	}

	/**
	 * 修改服务团队
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "updateServiceTeam")
	@Action(value = "editServiceTeam", results = { @Result(name = "success", type = "json", params = {
			"root", "serviceTeamVo", "ignoreHierarchy", "false" }) })
	public String updateServiceTeam() throws Exception {
		serviceTeamVo = serviceTeamService.updateServiceTeam(serviceTeam);
		return SUCCESS;
	}

	/**
	 * id加密删除服务团队
	 * 
	 * @return 被删除记录数
	 */
	@EncryptAnnotation
	@PermissionFilter(ename = "deleteServiceTeam")
	@Action(value = "deleteServiceTeamByEncrypt", results = {
			@Result(type = "json", name = "success", params = { "root",
					"deleteCount", "ignoreHierarchy", "false" }),
			@Result(type = "json", name = "error", params = { "root",
					"deleteCount", "ignoreHierarchy", "false" }) })
	public String deleteServiceTeamByEncrypt() throws Exception {
		deleteCount = serviceTeamService.deleteServiceTeam(selectedIds);
		return deleteCount == -1 ? ERROR : SUCCESS;
	}

	/**
	 * 删除服务团队
	 * 
	 * @return 被删除记录数
	 */
	@PermissionFilter(ename = "deleteServiceTeam")
	@Action(value = "deleteServiceTeam", results = {
			@Result(type = "json", name = "success", params = { "root",
					"deleteCount", "ignoreHierarchy", "false" }),
			@Result(type = "json", name = "error", params = { "root",
					"deleteCount", "ignoreHierarchy", "false" }) })
	public String deleteServiceTeam() throws Exception {
		deleteCount = serviceTeamService.deleteServiceTeam(selectedIds);
		return deleteCount == -1 ? ERROR : SUCCESS;
	}

	/**
	 * 根据层级和团队类型分页查询服务团队
	 * 
	 * @return SUCCESS
	 */
	@Action(value = "findServiceTeams", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findServiceTeams() throws Exception {
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				serviceTeamService.findServiceTeams(serviceTeamVo, page, rows,
						sidx, sord), orgService, new String[] { "org" }, orgId));
		return SUCCESS;
	}

	/**
	 * 显示所选团队的成员列表
	 */
	@Action(value = "findServiceTeamMembers", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findServiceTeamMembers() throws Exception {
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				serviceTeamMemberService.findServiceTeamMemberVoPageInTeam(
						serviceTeamMemberVo, page, rows, sidx, sord),
				orgService, new String[] { "org" }, orgId));
		return SUCCESS;
	}

	/**
	 * 显示非当前团队的成员库列表
	 */
	@Action(value = "findServiceTeamMemberNotInTeam", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findServiceTeamMemberNotInTeam() throws Exception {
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				serviceTeamMemberService.findServiceTeamMemberVoPageNotInTeam(
						serviceTeamMemberVo, page, rows, sidx, sord),
				orgService, new String[] { "org" }, orgId));
		return SUCCESS;
	}

	/**
	 * 查看团队信息
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "viewServiceTeam")
	@Action(value = "viewSeriviceTeam", results = { @Result(name = "serviceTeam", location = "/template/serviceTeam/serviceTeamManage/serviceTeamViewTab.ftl") })
	public String viewServiceTeam() throws Exception {
		getServiceTeamById();
		return "serviceTeam";
	}

	/**
	 * 根据id获取服务团队信息
	 */
	private void getServiceTeamById() {
		Long teamId = serviceTeam.getId();
		serviceTeamVo = serviceTeamService.getServiceTeamById(teamId);
		serviceTeamVo.getOrg().setOrgName(
				ControllerHelper.getOrganizationRelativeName(serviceTeamVo
						.getOrg().getId(), orgService));
	}

	/**
	 * 添加团队成员
	 */
	@PermissionFilter(ename = "maintainServiceTeamMembersForServiceTeam")
	@Action(value = "addServiceTeamMember", results = { @Result(type = "json", name = "success", params = {
			"root", "serviceTeamMemberVo", "ignoreHierarchy", "false" }) })
	public String addServiceTeamMember() throws Exception {
		serviceTeamMemberVo = serviceTeamMemberService
				.addTeamAndMemberRelation(serviceTeamMember);
		return SUCCESS;
	}

	/**
	 * 移除团队成员
	 */
	@PermissionFilter(ename = "maintainServiceTeamMembersForServiceTeam")
	@Action(value = "removeServiceTeamMember", results = {
			@Result(type = "json", name = "success", params = { "root",
					"deleteCount", "ignoreHierarchy", "false" }),
			@Result(type = "json", name = "error", params = { "root",
					"deleteCount", "ignoreHierarchy", "false" }) })
	public String removeServiceTeamMember() throws Exception {
		deleteCount = serviceTeamMemberService
				.reMoveTeamAndMemberRelation(serviceTeamMemberVo);
		return SUCCESS;
	}

	/**
	 * 解散团队
	 */
	@PermissionFilter(ename = "logoutServiceTeam")
	@Action(value = "logOutServiceTeam", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String logOutServiceTeam() throws Exception {
		serviceTeamService.logOutServiceTeam(serviceTeam);
		return SUCCESS;
	}

	/**
	 * 团队队员离职或重新担任
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "maintainServiceTeamMembersForServiceTeam")
	@Action(value = "updateServiceTeamMemberOnDuty", results = {
			@Result(type = "json", name = "success", params = { "root",
					"updateCount", "ignoreHierarchy", "false" }),
			@Result(type = "json", name = "error", params = { "root",
					"updateCount", "ignoreHierarchy", "false" }) })
	public String updateServiceTeamMemberOnDuty() throws Exception {
		updateCount = serviceTeamMemberService.updateServiceTeamMemberOnDuty(
				serviceTeamMemberVo, count);
		return SUCCESS;
	}

	/**
	 * 获取团队所属记录数
	 * 
	 * @return
	 */
	@Action(value = "serviceRecordsCount", results = { @Result(type = "json", name = "success", params = {
			"root", "countRecordsNum", "ignoreHierarchy", "false" }) })
	public String serviceRecordsCount() throws Exception {
		countRecordsNum = serviceRecordService
				.countServiceRecordsForTeam(serviceTeam.getId());
		return SUCCESS;
	}

	/**
	 * 获取团队所属对象数
	 * 
	 * @return
	 */
	@Action(value = "serviceObjectsCount", results = { @Result(type = "json", name = "success", params = {
			"root", "countObjectsNum", "ignoreHierarchy", "false" }) })
	public String serviceObjectsCount() {
		countObjectsNum = serviceTeamService
				.countServiceObjectsForTeam(serviceTeamVo);
		return SUCCESS;
	}

	/**
	 * 获取团队下成员是否有关联对象
	 * 
	 * @return
	 */
	@Action(value = "findServiceTeamMemberHasObjects", results = { @Result(type = "json", name = "success", params = {
			"root", "count", "ignoreHierarchy", "false" }) })
	public String findServiceTeamMemberHasObjects() {
		count = serviceTeamService
				.findServiceTeamMemberHasObjects(serviceTeamMemberVo);
		return SUCCESS;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		ServiceTeamController.logger = logger;
	}

	public ServiceTeam getServiceTeam() {
		return serviceTeam;
	}

	public void setServiceTeam(ServiceTeam serviceTeam) {
		this.serviceTeam = serviceTeam;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getTeamTypeId() {
		return teamTypeId;
	}

	public void setTeamTypeId(Long teamTypeId) {
		this.teamTypeId = teamTypeId;
	}

	public String getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(String selectedIds) {
		this.selectedIds = selectedIds;
	}

	public int getDeleteCount() {
		return deleteCount;
	}

	public void setDeleteCount(int deleteCount) {
		this.deleteCount = deleteCount;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getDisplayLevel() {
		return displayLevel;
	}

	public void setDisplayLevel(String displayLevel) {
		this.displayLevel = displayLevel;
	}

	public int getCountRecordsNum() {
		return countRecordsNum;
	}

	public void setCountRecordsNum(int countRecordsNum) {
		this.countRecordsNum = countRecordsNum;
	}

	public ServiceTeamVo getServiceTeamVo() {
		return serviceTeamVo;
	}

	public void setServiceTeamVo(ServiceTeamVo serviceTeamVo) {
		this.serviceTeamVo = serviceTeamVo;
	}

	public String getDailogName() {
		return dailogName;
	}

	public void setDailogName(String dailogName) {
		this.dailogName = dailogName;
	}

	public ServiceTeamMemberVo getServiceTeamMemberVo() {
		return serviceTeamMemberVo;
	}

	public void setServiceTeamMemberVo(ServiceTeamMemberVo serviceTeamMemberVo) {
		this.serviceTeamMemberVo = serviceTeamMemberVo;
	}

	public ServiceTeamMember getServiceTeamMember() {
		return serviceTeamMember;
	}

	public void setServiceTeamMember(ServiceTeamMember serviceTeamMember) {
		this.serviceTeamMember = serviceTeamMember;
	}

	public int getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(int updateCount) {
		this.updateCount = updateCount;
	}

	public int[] getCountObjectsNum() {
		return countObjectsNum;
	}

	public void setCountObjectsNum(int[] countObjectsNum) {
		this.countObjectsNum = countObjectsNum;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

}
