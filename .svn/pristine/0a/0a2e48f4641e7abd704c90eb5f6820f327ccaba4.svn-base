package com.tianque.baseInfo.primaryOrg.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.primaryOrg.constant.FourLevelPlatformOrgType;
import com.tianque.baseInfo.primaryOrg.domain.FourLevelPlatform;
import com.tianque.baseInfo.primaryOrg.domain.vo.SearchFourLevelPlatformVo;
import com.tianque.baseInfo.primaryOrg.service.FourLevelPlatformService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModuleConstants;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;

/**
 * 四级平台表:服务前端控制类
 * 
 * @author
 * @date 2014-03-10 09:38:17
 */
@Namespace("/fourLevelPlatformManage")
@Scope("request")
@Controller("fourLevelPlatformController")
public class FourLevelPlatformController extends SearchBaseAction {

	@Autowired
	private FourLevelPlatformService fourLevelPlatformService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private PermissionService permissionService;

	private FourLevelPlatform fourLevelPlatform;
	private SearchFourLevelPlatformVo searchFourLevelPlatformVo;
	private String ids;
	private Organization organization;
	private boolean pageOnly;
	/** 页面 对话框名称 */
	private String dailogName;
	private Integer count;// 统计值
	/** 删除的行数 */
	private int deleteCount;
	private Long isFourLevelPlatform;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/baseinfo/fourLevelPlatformManage/maintainFourLevelPlatformDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/fourLevelPlatformManage/fourLevelPlatformViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			fourLevelPlatform = new FourLevelPlatform();
			organization.setOrgLevel(organizationDubboService.getFullOrgById(
					organization.getId()).getOrgLevel());
			organization.setOrgName(organizationDubboService
					.getOrganizationRelativeNameByRootOrgIdAndOrgId(
							organization.getId(), OrganizationServiceHelper
									.getRootOrg(organizationDubboService)
									.getId()));
			fourLevelPlatform.setOrganization(organization);
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			if (fourLevelPlatform == null || fourLevelPlatform.getId() == null) {
				return ERROR;
			}
			getPrimaryOrgById();
			getTeamTypeNameByTeamType(fourLevelPlatform);
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			if (fourLevelPlatform == null || fourLevelPlatform.getId() == null) {
				return ERROR;
			}
			getPrimaryOrgById();
			getTeamTypeNameByTeamType(fourLevelPlatform);
			return "view";
		}
		return SUCCESS;
	}

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/fourLevelPlatformManage/maintainFourLevelPlatformDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/fourLevelPlatformManage/fourLevelPlatformViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	@EncryptAnnotation
	public String dispatchOperateByEncrypt() throws Exception {
		if (DialogMode.EDIT_MODE.equals(mode)) {
			if (fourLevelPlatform == null || fourLevelPlatform.getId() == null) {
				return ERROR;
			}
			getPrimaryOrgById();
			getTeamTypeNameByTeamType(fourLevelPlatform);
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			if (fourLevelPlatform == null || fourLevelPlatform.getId() == null) {
				return ERROR;
			}
			getPrimaryOrgById();
			getTeamTypeNameByTeamType(fourLevelPlatform);
			return "view";
		}
		return SUCCESS;
	}

	/**
	 * 根据id给四级平台组织机构赋值
	 * 
	 * @return
	 */
	private void getPrimaryOrgById() {
		fourLevelPlatform = fourLevelPlatformService.get(fourLevelPlatform
				.getId());
		fourLevelPlatform.getOrganization().setOrgName(
				organizationDubboService
						.getOrganizationRelativeNameByRootOrgIdAndOrgId(
								fourLevelPlatform.getOrganization().getId(),
								OrganizationServiceHelper.getRootOrg(
										organizationDubboService).getId()));
		fourLevelPlatform.getOrganization().setOrgLevel(
				organizationDubboService.getFullOrgById(
						fourLevelPlatform.getOrganization().getId())
						.getOrgLevel());
	}

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	@Action(value = "addFourLevelPlatform", results = {
			@Result(name = "success", type = "json", params = { "root",
					"fourLevelPlatform", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "addFourLevelPlatform")
	public String addFourLevelPlatform() throws Exception {
		if (fourLevelPlatform == null
				|| !checkOrganization(fourLevelPlatform.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		fourLevelPlatform.setOrgInternalCode(organizationDubboService
				.getSimpleOrgById(fourLevelPlatform.getOrganization().getId())
				.getOrgInternalCode());
		fourLevelPlatform = fourLevelPlatformService.add(fourLevelPlatform);
		return SUCCESS;
	}

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	@Action(value = "updateFourLevelPlatform", results = {
			@Result(name = "success", type = "json", params = { "root",
					"fourLevelPlatform", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "editFourLevelPlatform")
	public String updateFourLevelPlatform() throws Exception {
		if (fourLevelPlatform == null || fourLevelPlatform.getId() == null
				|| !checkOrganization(fourLevelPlatform.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		fourLevelPlatform = fourLevelPlatformService.update(fourLevelPlatform);
		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deleteFourLevelPlatformByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"deleteCount" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteFourLevelPlatform")
	@EncryptAnnotation
	public String deleteFourLevelPlatformByIds() throws Exception {
		if (ids == null || "".equals(ids.trim())) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		deleteCount = fourLevelPlatformService.deleteFourLevelPlatformByIds(
				ids, isFourLevelPlatform);
		return deleteCount == -1 ? ERROR : SUCCESS;
	}

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 * 
	 * @return Pager对象
	 */
	@Action(value = "findFourLevelPlatformPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "fourLevelPlatformManagement")
	public String findFourLevelPlatformPagerBySearchVo() throws Exception {
		if (searchFourLevelPlatformVo == null
				|| !checkOrganization(organization)) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Organization org = organizationDubboService
				.getSimpleOrgById(organization.getId());
		if (org == null) {
			this.errorMessage = "网格不存在";
			return ERROR;
		}
		searchFourLevelPlatformVo.setOrgInternalCode(org.getOrgInternalCode());
		gridPage = new GridPage(
				ControllerHelper.processAllOrgRelativeName(
						fourLevelPlatformService.findPagerBySearchVo(
								searchFourLevelPlatformVo, page, rows, sidx,
								sord), organizationDubboService,
						new String[] { "organization" }, null));
		getTeamTypeNameList(gridPage.getRows());
		getFourthAccountList(gridPage.getRows());
		return SUCCESS;
	}

	/**
	 * 根据组织机构统计四级平台的数据
	 * 
	 * @return
	 */
	@Action(value = "countFourLevelPlatformByOrgId", results = {
			@Result(name = "success", type = "json", params = { "root", "count" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String countFourLevelPlatformByOrgId() throws Exception {
		if (fourLevelPlatform == null
				|| !checkOrganization(fourLevelPlatform.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		count = fourLevelPlatformService
				.countFourLevelPlatformByOrgId(fourLevelPlatform
						.getOrganization().getId());
		return SUCCESS;
	}

	/**
	 * 导出四级平台的本页数据
	 * 
	 * @return
	 */
	@Action(value = "downloadPrimaryOrg", results = {
			@Result(name = "success", type = "stream", params = {
					"contentType",
					"application/vnd.ms-excel;charset=ISO8859-1", "inputName",
					"inputStream", "contentDisposition",
					"attachment;filename='${downloadFileName}'", "bufferSize",
					"4096" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String downloadPrimaryOrg() throws Exception {
		if (null == searchFourLevelPlatformVo
				|| searchFourLevelPlatformVo.getOrgId() == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		List<FourLevelPlatform> records = getNeedExportDatas(page);

		inputStream = ExcelExportHelper.exportDateToExcel(
				SpecialGroupsContext.getFourLevelPlatformArraySlf(), records);
		downloadFileName = new String("四级体系建设".getBytes("gbk"), "ISO8859-1")
				+ ".xls";
		systemLogService
				.log(com.vladium.logging.Logger.INFO,
						ModuleConstants.PRIMARYORGANIZATIONS,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												searchFourLevelPlatformVo
														.getOrgId(),
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出组织信息",
						ObjectToJSON.convertJSON(new FourLevelPlatform()));
		return STREAM_SUCCESS;

	}

	/**
	 * 导出四级平台的全部数据
	 * 
	 * @return
	 */
	@Action(value = "downloadPrimaryOrgAll")
	public void downloadPrimaryOrgAll() throws Exception {
		if (null == searchFourLevelPlatformVo
				|| searchFourLevelPlatformVo.getOrgId() == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		Integer count = fourLevelPlatformService
				.getCount(searchFourLevelPlatformVo);
		String[][] excelDefines = SpecialGroupsContext
				.getFourLevelPlatformArraySlf();
		exportDataAll(count, excelDefines, "四级体系建设");
		systemLogService
				.log(com.vladium.logging.Logger.INFO,
						ModuleConstants.PRIMARYORGANIZATIONS,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												searchFourLevelPlatformVo
														.getOrgId(),
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出组织信息",
						ObjectToJSON.convertJSON(new FourLevelPlatform()));
	}

	@Override
	public List getNeedExportDatas(int page) {
		List<FourLevelPlatform> records = new ArrayList<FourLevelPlatform>();
		Organization org = organizationDubboService
				.getSimpleOrgById(searchFourLevelPlatformVo.getOrgId());
		if (org == null) {
			this.errorMessage = "网格不存在";
			return null;
		}
		searchFourLevelPlatformVo.setOrgInternalCode(org.getOrgInternalCode());
		if (pageOnly) {
			records = fourLevelPlatformService.findPagerBySearchVo(
					searchFourLevelPlatformVo, page, rows, sidx, sord)
					.getResult();
		} else {
			records = fourLevelPlatformService
					.findPagerBySearchVo(searchFourLevelPlatformVo, 1,
							Integer.MAX_VALUE, sidx, sord).getResult();
		}
		getTeamTypeNameList(records);
		getFourthAccountList(records);
		return records;
	}

	/**
	 * 根据组织类型id给组织类型名称赋值
	 * 
	 * @param fourLevelPlatform
	 * @return
	 */
	private FourLevelPlatform getTeamTypeNameByTeamType(
			FourLevelPlatform fourLevelPlatform) {
		Long teamTypeId = fourLevelPlatform.getTeamType();
		if (teamTypeId.equals(Long.valueOf(OrganizationLevel.DISTRICT))) {
			fourLevelPlatform
					.setTeamTypeName(FourLevelPlatformOrgType.DISTRICT_ORGTYPE);
		} else if (teamTypeId.equals(Long.valueOf(OrganizationLevel.TOWN))) {
			fourLevelPlatform
					.setTeamTypeName(FourLevelPlatformOrgType.TOWN_ORGTYPE);
		} else if (teamTypeId.equals(Long.valueOf(OrganizationLevel.VILLAGE))) {
			fourLevelPlatform
					.setTeamTypeName(FourLevelPlatformOrgType.VILLAGE_ORGTYPE);
		} else if (teamTypeId.equals(Long.valueOf(OrganizationLevel.GRID))) {
			fourLevelPlatform
					.setTeamTypeName(FourLevelPlatformOrgType.GRID_ORGTYPE);
		}
		return fourLevelPlatform;

	}

	/**
	 * 得到所有的组织类型名称
	 * 
	 * @return
	 */
	private void getTeamTypeNameList(List<FourLevelPlatform> list) {
		for (int i = 0; i < list.size(); i++) {
			getTeamTypeNameByTeamType(list.get(i));
		}
	}

	/**
	 * 得到参与管理数的统计数
	 * 
	 * @param list
	 */
	private void getFourthAccountList(List<FourLevelPlatform> list) {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setFourthAccount(
						Integer.valueOf(permissionService
								.countFourthAccountUserByOrg(list.get(i)
										.getOrganization())));
			}
		}

	}

	private boolean checkOrganization(Organization org) {
		return (org != null && org.getId() != null);
	}

	public FourLevelPlatform getFourLevelPlatform() {
		return fourLevelPlatform;
	}

	public void setFourLevelPlatform(FourLevelPlatform fourLevelPlatform) {
		this.fourLevelPlatform = fourLevelPlatform;
	}

	public SearchFourLevelPlatformVo getSearchFourLevelPlatformVo() {
		return searchFourLevelPlatformVo;
	}

	public void setSearchFourLevelPlatformVo(
			SearchFourLevelPlatformVo searchFourLevelPlatformVo) {
		this.searchFourLevelPlatformVo = searchFourLevelPlatformVo;
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

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public String getDailogName() {
		return dailogName;
	}

	public void setDailogName(String dailogName) {
		this.dailogName = dailogName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public int getDeleteCount() {
		return deleteCount;
	}

	public void setDeleteCount(int deleteCount) {
		this.deleteCount = deleteCount;
	}

	public Long getIsFourLevelPlatform() {
		return isFourLevelPlatform;
	}

	public void setIsFourLevelPlatform(Long isFourLevelPlatform) {
		this.isFourLevelPlatform = isFourLevelPlatform;
	}

}
