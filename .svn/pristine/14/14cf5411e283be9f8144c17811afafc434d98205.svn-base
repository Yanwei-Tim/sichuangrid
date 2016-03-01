package com.tianque.plugin.serviceTeam.serviceTeamMember.controller;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.tianque.domain.Organization;
import com.tianque.plugin.serviceTeam.serviceTeamMember.domain.ServiceTeamMember;
import com.tianque.plugin.serviceTeam.serviceTeamMember.domain.ServiceTeamMemberBase;
import com.tianque.plugin.serviceTeam.serviceTeamMember.service.ServiceTeamMemberService;
import com.tianque.plugin.serviceTeam.serviceTeamMember.vo.ServiceTeamMemberVo;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 服务成员控制类
 * 
 * @author tengjia
 */
@Namespace("/plugin/serviceTeam/serviceTeamMember")
@Controller("serviceTeamMemberController")
@Scope("prototype")
@Transactional
public class ServiceTeamMemberController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(ServiceTeamMemberController.class);

	/** 成员基本信息表domain类对象 **/
	private ServiceTeamMemberBase serviceTeamMemberBase;

	/** 成员基本信息表domain类对象 **/
	private ServiceTeamMember serviceTeamMemberDetails;

	/** 要删除的行 的id */
	private String selectedIds;

	/** 删除的行数 */
	private int deleteCount;

	/** 服务成员vo对象 **/
	private ServiceTeamMemberVo serviceTeamMemberVo;
	/** 当前选择的组织机构ID */
	private Long orgId;
	private List<Integer> list;
	private boolean pageOnly;
	private List<Long> memberIds;

	@Autowired
	private ServiceTeamMemberService serviceTeamMemberService;
	@Autowired
	private OrganizationDubboService orgService;

	/** id加密业务跳转 */
	@Action(value = "dispatchByEncrypt", results = {
			@Result(name = "maintain", location = "/template/serviceTeam/serviceMember/serviceTeamMemberMaintainDlg.ftl"),
			@Result(name = "combine", location = "/template/serviceTeam/serviceMember/serviceTeamMemberCombineDlg.ftl"),
			@Result(name = "changeOrg", location = "/template/serviceTeam/serviceMember/serviceTeamMemberOrgSelectDlg.ftl") })
	@EncryptAnnotation
	public String dispatchByEncrypt() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			return "maintain";
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			getServiceTeamMemberBaseById(serviceTeamMemberBase.getId());
			return "maintain";
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			getServiceTeamMemberBaseById(serviceTeamMemberBase.getId());
			return "view";
		} else if ("combine".equals(mode)) {
			getServiceTeamMemberBaseById(serviceTeamMemberVo.getId());
			return "combine";
		} else if ("changeOrg".equals(mode)) {
			return "changeOrg";
		}
		return ERROR;
	}

	/** 业务跳转 */
	@Action(value = "dispatch", results = {
			@Result(name = "maintain", location = "/template/serviceTeam/serviceMember/serviceTeamMemberMaintainDlg.ftl"),
			@Result(name = "search", location = "/template/serviceTeam/serviceMember/serviceTeamMemberSearchDlg.ftl"),
			@Result(name = "view", location = "/template/serviceTeam/serviceMember/viewServiceTeamMemberDlg.ftl"),
			@Result(name = "maintainServiceObjects", location = "/template/serviceTeam/serviceMemberWithObject/serviceMemberHasObjectListDlg.ftl"),
			@Result(name = "combine", location = "/template/serviceTeam/serviceMember/serviceTeamMemberCombineDlg.ftl"),
			@Result(name = "changeOrg", location = "/template/serviceTeam/serviceMember/serviceTeamMemberOrgSelectDlg.ftl") })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			return "maintain";
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			getServiceTeamMemberBaseById(serviceTeamMemberBase.getId());
			return "maintain";
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			getServiceTeamMemberBaseById(serviceTeamMemberBase.getId());
			return "view";
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			return "search";
		} else if ("maintainServiceObjects".equals(mode)) {
			return "maintainServiceObjects";
		} else if ("combine".equals(mode)) {
			getServiceTeamMemberBaseById(serviceTeamMemberVo.getId());
			return "combine";
		} else if ("changeOrg".equals(mode)) {
			return "changeOrg";
		}
		return ERROR;
	}

	/**
	 * id加密查看成员基本信息
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "viewServiceMemberByEncrypt", results = { @Result(name = "viewServiceMember", location = "/template/serviceTeam/serviceMember/serviceTeamMemberViewTab.ftl") })
	public String viewServiceMemberByEncrypt() throws Exception {
		getServiceTeamMemberBaseById(serviceTeamMemberBase.getId());
		return "viewServiceMember";
	}

	/**
	 * 查看成员基本信息
	 * 
	 * @return
	 */
	@Action(value = "viewServiceMember", results = { @Result(name = "viewServiceMember", location = "/template/serviceTeam/serviceMember/serviceTeamMemberViewTab.ftl") })
	public String viewServiceMember() throws Exception {
		getServiceTeamMemberBaseById(serviceTeamMemberBase.getId());
		return "viewServiceMember";
	}

	/**
	 * 根据层级分页查询服务团队 列表的方法
	 * 
	 * @return SUCCESS
	 */
	@Action(value = "findServiceTeamMemberBases", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findServiceTeamMemberBases() throws Exception {
		if(tqmobile!=null&&tqmobile.equals("true")){
			serviceTeamMemberVo.setSearchType("mobile");
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				serviceTeamMemberService.findServiceTeamMemberBasesPageList(
						serviceTeamMemberVo, page, rows, sidx, sord),
				orgService, new String[] { "org" }, orgId));
		return SUCCESS;
	}

	/**
	 * 根据姓名和手机查寻重复数据列表（基础表）
	 * 
	 * @return SUCCESS
	 */
	@Action(value = "findSameMembersByNameAndMobile", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findSameMembersByNameAndMobile() throws Exception {
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				serviceTeamMemberService.findSameMembersByNameAndMobile(
						serviceTeamMemberVo, page, rows, sidx, sord),
				orgService, new String[] { "org" }, orgId));
		return SUCCESS;
	}

	/**
	 * 新增服务成员
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "addServiceTeamMember")
	@Action(value = "addServiceTeamMemberBase", results = {
			@Result(name = "success", type = "json", params = { "root",
					"serviceTeamMemberVo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addServiceTeamMemberBase() throws Exception {
		serviceTeamMemberVo = serviceTeamMemberService
				.addServiceTeamMemberBase(serviceTeamMemberBase);
		return SUCCESS;
	}

	/**
	 * 修改服务成员
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "updateServiceTeamMember")
	@Action(value = "editServiceTeamMemberBase", results = {
			@Result(name = "success", type = "json", params = { "root",
					"serviceTeamMemberVo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateServiceTeamMemberBase() throws Exception {
		serviceTeamMemberVo = serviceTeamMemberService
				.updateServiceTeamMemberBase(serviceTeamMemberBase);
		return SUCCESS;
	}

	/**
	 * id加密删除服务成员
	 * 
	 * @return 被删除记录数
	 */
	@EncryptAnnotation
	@PermissionFilter(ename = "deleteServiceTeamMember")
	@Action(value = "deleteServiceTeamMemberByEncrypt", results = { @Result(type = "json", name = "success", params = {
			"root", "deleteCount", "ignoreHierarchy", "false" }) })
	public String deleteServiceTeamMemberBaseByEncrypt() throws Exception {
		deleteCount = serviceTeamMemberService
				.deleteServiceTeamMemberBase(selectedIds);
		return SUCCESS;
	}

	/**
	 * 删除服务成员
	 * 
	 * @return 被删除记录数
	 */
	@PermissionFilter(ename = "deleteServiceTeamMember")
	@Action(value = "deleteServiceTeamMember", results = { @Result(type = "json", name = "success", params = {
			"root", "deleteCount", "ignoreHierarchy", "false" }) })
	public String deleteServiceTeamMemberBase() throws Exception {
		deleteCount = serviceTeamMemberService
				.deleteServiceTeamMemberBase(selectedIds);
		return SUCCESS;
	}

	/**
	 * 高级搜索
	 * 
	 * @return
	 */
	@Action(value = "searchServiceTeamMember", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String searchServiceTeamMemberBases() throws Exception {
		gridPage = new GridPage<ServiceTeamMemberVo>(
				serviceTeamMemberService.findServiceTeamMemberBases(
						serviceTeamMemberVo, page, rows, sidx, sord));
		return SUCCESS;
	}

	/**
	 * 合并服务成员（基本信息）
	 * serviceTeamMemberBase为修改后合并的标准信息，serviceTeamMemberVo为之前判断是否重复的标准信息
	 * 
	 * @return
	 */
	@Action(value = "combineServiceTeamMembers", results = {
			@Result(name = "success", type = "json", params = { "root",
					"serviceTeamMemberVo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String combineServiceTeamMembers() throws Exception {
		serviceTeamMemberService.combineServiceTeamMembers(
				serviceTeamMemberBase, serviceTeamMemberVo, selectedIds);
		return SUCCESS;
	}

	/**
	 * 服务成员层级转移（基本信息）
	 */
	@Action(value = "changeOrg", results = {
			@Result(name = "success", type = "json", params = { "root",
					"orgId", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String changeOrg() throws Exception {
		serviceTeamMemberService.changeOrg(orgId, selectedIds);
		return SUCCESS;
	}

	@Action(value = "getServiceTeamMemberDetailsByBaseId", results = { @Result(type = "json", name = "success", params = {
			"root", "memberIds" }) })
	// 获取成员的详细信息
	public String getServiceTeamMemberDetailsByBaseId() throws Exception {
		Long baseId = serviceTeamMemberBase.getId();
		List<ServiceTeamMemberVo> list = serviceTeamMemberService
				.getServiceTeamMemberDetailsByBaseId(baseId);
		memberIds = new ArrayList<Long>();
		for (ServiceTeamMemberVo vo : list) {
			memberIds.add(vo.getMemberId());
		}
		return SUCCESS;
	}

	/**
	 * 根据id获取服务成员信息
	 */
	private void getServiceTeamMemberBaseById(Long id) {
		serviceTeamMemberVo = serviceTeamMemberService
				.getTeamMemberBaseById(id);
		serviceTeamMemberVo.getOrg().setOrgName(
				ControllerHelper.getOrganizationRelativeName(
						serviceTeamMemberVo.getOrg().getId(), orgService));
	}

	@Action(value = "getCurrentTimeForIntegrativeQueryYear", results = { @Result(type = "json", name = "success", params = {
			"root", "list", "ignoreHierarchy", "false" }) })
	public String getCurrentTimeForIntegrativeQueryYear() throws Exception {
		list = new ArrayList<Integer>();
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		for (int i = year; i > year - 100; i--) {

			list.add(i);
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "downServiceTeam")
	@Action(value = "downloadServiceTeamMember")
	public String downloadServiceTeamMember() throws Exception {
		if (serviceTeamMemberVo == null && orgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} else {
			populateOrgCondition();
			List<ServiceTeamMemberVo> records = getNeedExportDatas(page);
			Organization org = orgService.getSimpleOrgById(orgId);
			if (orgId != null) {
				if (org != null) {
					serviceTeamMemberVo.setOrgInternalCode(org
							.getOrgInternalCode());
				} else {
					serviceTeamMemberVo.setOrgInternalCode(null);
				}
			} else {
				serviceTeamMemberVo.setOrgInternalCode(null);
			}
			inputStream = ExcelExportHelper.exportDateToExcel(
					serviceTeamMemberService.getExportPopertyArray(), records);
			downloadFileName = new String("团队现有成员清单".getBytes("gbk"),
					"ISO8859-1") + ".xls";
		}
		return STREAM_SUCCESS;
	}

	/**
	 * 设置组织机构
	 */
	private void populateOrgCondition() {
		if (serviceTeamMemberVo == null) {
			serviceTeamMemberVo = new ServiceTeamMemberVo();
		}
		if (orgId != null) {
			Organization org = orgService.getSimpleOrgById(orgId);
			if (org != null) {
				serviceTeamMemberVo
						.setOrgInternalCode(org.getOrgInternalCode());
			} else {
				serviceTeamMemberVo.setOrgInternalCode(null);
			}
		} else {
			serviceTeamMemberVo.setOrgInternalCode(null);
		}
	}

	/**
	 * 获取导出所需数据
	 */
	private List<ServiceTeamMemberVo> getNeedExportDatas(int page) {
		if (pageOnly) {
			return serviceTeamMemberService.searchServiceTeamMemberForExport(
					serviceTeamMemberVo, page, rows, sidx, sord);
		} else {
			return serviceTeamMemberService.searchServiceTeamMemberForExport(
					serviceTeamMemberVo, null, null, sidx, sord);
		}
	}

	/**
	 * 将ID字符串转换为list
	 * 
	 * @return
	 */
	private List<Long> getIds(String selectedIds) {
		String[] deleteIdStrs = selectedIds.split(",");
		List<Long> deleteIds = new ArrayList<Long>();
		for (String deleteIdStr : deleteIdStrs) {
			Long deleteId = Long.parseLong(deleteIdStr);
			deleteIds.add(deleteId);
		}
		return deleteIds;
	}
	
	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		ServiceTeamMemberController.logger = logger;
	}

	public ServiceTeamMemberBase getServiceTeamMemberBase() {
		return serviceTeamMemberBase;
	}

	public void setServiceTeamMemberBase(
			ServiceTeamMemberBase serviceTeamMemberBase) {
		this.serviceTeamMemberBase = serviceTeamMemberBase;
	}

	public String getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(String selectedIds) {
		this.selectedIds = selectedIds;
	}

	public ServiceTeamMemberService getServiceTeamMemberService() {
		return serviceTeamMemberService;
	}

	public void setServiceTeamMemberService(
			ServiceTeamMemberService serviceTeamMemberService) {
		this.serviceTeamMemberService = serviceTeamMemberService;
	}

	public ServiceTeamMemberVo getServiceTeamMemberVo() {
		return serviceTeamMemberVo;
	}

	public void setServiceTeamMemberVo(ServiceTeamMemberVo serviceTeamMemberVo) {
		this.serviceTeamMemberVo = serviceTeamMemberVo;
	}

	public int getDeleteCount() {
		return deleteCount;
	}

	public void setDeleteCount(int deleteCount) {
		this.deleteCount = deleteCount;
	}

	public ServiceTeamMember getServiceTeamMemberDetails() {
		return serviceTeamMemberDetails;
	}

	public void setServiceTeamMemberDetails(
			ServiceTeamMember serviceTeamMemberDetails) {
		this.serviceTeamMemberDetails = serviceTeamMemberDetails;
	}

	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public List<Long> getMemberIds() {
		return memberIds;
	}

	public void setMemberIds(List<Long> memberIds) {
		this.memberIds = memberIds;
	}

}
