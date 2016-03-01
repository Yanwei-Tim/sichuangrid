package com.tianque.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.datatransfer.DataType;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModuleConstants;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.domain.Organization;
import com.tianque.domain.PartyOrgActivity;
import com.tianque.domain.PartyOrgActivityFile;
import com.tianque.domain.vo.SearchPartyOrgActivityVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.PartyOrgActivityFileService;
import com.tianque.service.PartyOrgActivityService;
import com.tianque.service.PartyOrgInfoService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;

@SuppressWarnings("serial")
@Controller("partyOrgActivityController")
@Scope("prototype")
public class PartyOrgActivityController extends SearchBaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(PartyOrgActivityController.class);

	@Autowired
	private PartyOrgActivityService partyOrgActivityService;

	@Autowired
	protected OrganizationDubboService organizationDubboService;

	private Long orgId;

	private PartyOrgActivity population;

	private boolean bol;

	private boolean pageOnly;

	private Long organizationId;

	private String deleteIds;

	private List<Long> noRelatePersonIds;

	private String[] attachFiles;

	private List<PartyOrgActivity> partyOrgActivityList;

	private SearchPartyOrgActivityVo searchPartyOrgActivityVo;

	private PartyOrgActivityFile partyOrgActivityFile;

	@Autowired
	private PartyOrgInfoService partyOrgInfoService;

	@Autowired
	private PartyOrgActivityFileService partyOrgActivityFileService;

	@Autowired
	private SystemLogService systemLogService;

	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			this.procOrganization();
			population.setPartyOrgInfo(this.partyOrgInfoService
					.getPartyOrgInfoByOrgId(population.getOrganization()
							.getId()));
		} else if (DialogMode.EDIT_MODE.endsWith(mode)) {
			this.getPartyOrgActivityInfo();
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			this.procOrganization();
			return "search";
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			this.getPartyOrgActivityInfo();
			return "view";
		}
		return SUCCESS;
	}

	/**
	 * 新增和修改本级党建党组织活动记录
	 * 
	 * @return SUCCESS
	 */
	public String addPartyOrgActivity() throws Exception {
		Organization organization = organizationDubboService
				.getSimpleOrgById(population.getOrganization().getId());
		population.setOrganization(organization);
		if (DialogMode.ADD_MODE.equals(mode)) {
			population = this.partyOrgActivityService
					.addPartyOrgActivity(population);
			population.setActivityAttachFile(this.partyOrgActivityFileService
					.addPartyOrgActivityFile(population.getId(), attachFiles));
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			population
					.setActivityAttachFile(this.partyOrgActivityFileService
							.updatePartyOrgActivityFile(population.getId(),
									attachFiles));
			population = this.partyOrgActivityService
					.updatePartyOrgActivity(population);

		}
		return SUCCESS;
	}

	/**
	 * 根据网格内置编码分页查询本级党建党组织活动记录
	 * 
	 * @return SUCCESS
	 */
	public String findPartyOrgActivityForPageByOrgId() throws Exception {
		if (null == orgId) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			gridPage = new GridPage(
					ControllerHelper.processAllOrgRelativeName(
							this.partyOrgActivityService
									.findPartyOrgActivityForPageByOrgId(orgId,
											page, rows, sidx, sord,
											searchPartyOrgActivityVo
													.getYearActivity()),
							organizationDubboService,
							new String[] { "organization" }, orgId));
		}
		return SUCCESS;
	}

	/**
	 * 获取组织活动及附件列表
	 * 
	 * @return
	 */
	public String getPartyOrgActivityById() throws Exception {
		population = this.partyOrgActivityService
				.getPartyOrgActivityById(population.getId());
		if (null != population) {
			population
					.setActivityAttachFile(this.partyOrgActivityFileService
							.getPartyOrgActivityFileByOrgActivityId(population
									.getId()));
		}
		return SUCCESS;
	}

	public String deletePartyOrgActivityAttachFile() throws Exception {
		if (null != partyOrgActivityFile) {
			this.partyOrgActivityFileService
					.deletePartyOrgActivityFileById(partyOrgActivityFile
							.getId());
		}
		return SUCCESS;
	}

	/**
	 * 根据查询条件查询本级党建党组织活动记录
	 * 
	 * @return
	 */
	public String searchPartyOrgActivity() throws Exception {
		if (null == orgId) {
			gridPage = new GridPage(new PageInfo<PartyOrgActivity>());
			return SUCCESS;
		}
		searchPartyOrgActivityVo.setSortField(sidx);
		searchPartyOrgActivityVo.setOrder(sord);
		PageInfo<PartyOrgActivity> pageInfo = ControllerHelper
				.processAllOrgRelativeName(partyOrgActivityService
						.searchPartyOrgActivitys(page, rows,
								searchPartyOrgActivityVo), organizationDubboService,
						new String[] { "organization" }, orgId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/**
	 * 删除本级党建党组织活动记录
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "deletePartyOrgActivitys")
	public String deletePartyOrgActivity() throws Exception {
		List<Long> idList;
		String[] deleteId = deleteIds.split(",");
		if (StringUtils.isEmpty(deleteId[0])) {
			idList = initTargetId(deleteId, 1);
		} else {
			idList = initTargetId(deleteId, 0);
		}
		this.partyOrgActivityFileService
				.deletePartyOrgActivityFileByOrgActivityId(idList);
		noRelatePersonIds = this.partyOrgActivityService
				.deletePartyPartyOrgActivityById(idList);
		return SUCCESS;
	}

	public String downloadPartyOrgActivityFile() throws Exception {
		if (null == partyOrgActivityFile
				|| null == partyOrgActivityFile.getId()) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		partyOrgActivityFile = this.partyOrgActivityFileService
				.getPartyOrgActivityFileById(partyOrgActivityFile.getId());
		if (null == partyOrgActivityFile) {
			this.errorMessage = "未找到对应的附件";
			return ERROR;
		}
		try {
			inputStream = new java.io.FileInputStream(FileUtil.getWebRoot()
					+ File.separator + partyOrgActivityFile.getFileActualUrl());
			downloadFileName = new String(partyOrgActivityFile.getFileName()
					.getBytes("gbk"), "ISO8859-1");
		} catch (FileNotFoundException e) {
			logger.error("downloadDailyLogAttachFile 错误", e);
			throw new BusinessValidationException("文件打开失败!");
		} catch (UnsupportedEncodingException uee) {
			logger.error("downloadDailyLogAttachFile 错误", uee);
			throw new BusinessValidationException("文件打开失败!");
		}

		return SUCCESS;
	}

	/**
	 * 导出本级党建党组织活动记录
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "downloadPartyOrgActivitys")
	public String downloadPartyOrgActivity() throws Exception {
		if (null == orgId || null == searchPartyOrgActivityVo) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		Organization organization = this.organizationDubboService
				.getSimpleOrgById(orgId);
		searchPartyOrgActivityVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		searchPartyOrgActivityVo.setSortField(sidx);
		searchPartyOrgActivityVo.setOrder(sord);
		List<PartyOrgActivity> exportDataList = new ArrayList<PartyOrgActivity>();
		if (pageOnly) {
			exportDataList = this.partyOrgActivityService
					.searchPartyOrgActivitys(page, rows,
							searchPartyOrgActivityVo).getResult();
		} else {
			exportDataList = this.partyOrgActivityService
					.searchAllPartyOrgActivitys(searchPartyOrgActivityVo);
		}
		String[][] excelColumArray = this.getPartyOrgActivityPropertyArray();
		inputStream = ExcelExportHelper.exportDateToExcel(excelColumArray,
				exportDataList);
		downloadFileName = new String(excelColumArray[0][2].getBytes("gbk"),
				"ISO8859-1") + ".xls";
		systemLogService
				.log(com.vladium.logging.Logger.INFO,
						ModuleConstants.PARTYORGACTIVITY,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												orgId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出优抚对象",
						ObjectToJSON.convertJSON(new HouseholdStaff()));
		return SUCCESS;
	}

	/**
	 * 导出本级党建党组织活动记录
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "downloadPartyOrgActivitys")
	public void downloadPartyOrgActivityAll() throws Exception {
		if (null == orgId || null == searchPartyOrgActivityVo) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		Organization organization = this.organizationDubboService
				.getSimpleOrgById(orgId);
		searchPartyOrgActivityVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		searchPartyOrgActivityVo.setSortField(sidx);
		searchPartyOrgActivityVo.setOrder(sord);
		if (!pageOnly) {
			pageOnly = true;
			Integer count = partyOrgActivityService
					.getCount(searchPartyOrgActivityVo);
			String[][] excelDefines = getPartyOrgActivityPropertyArray();
			exportDataAll(count, excelDefines, excelDefines[0][2]);
		}
		systemLogService
				.log(com.vladium.logging.Logger.INFO,
						ModuleConstants.PARTYORGACTIVITY,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												orgId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId())
								+ "导出本级党建党组织活动记录", ObjectToJSON
								.convertJSON(new HouseholdStaff()));
		return;
	}

	public List<PartyOrgActivity> getNeedExportDatas(int page) {
		List<PartyOrgActivity> exportDataList = new ArrayList<PartyOrgActivity>();
		if (pageOnly) {
			exportDataList = this.partyOrgActivityService
					.searchPartyOrgActivitys(page, rows,
							searchPartyOrgActivityVo).getResult();
		}
		return exportDataList;
	}

	/**
	 * 序号|属性|中文名|属性类型|是否采用默认样式|合并行|合并列|
	 * 
	 * @return
	 */
	private String[][] getPartyOrgActivityPropertyArray() {
		String[][] excelColumArray = {
				{ "0", "", "本级党建党组织活动记录", "", "", "true", "0", "14" },
				{ "0", "organization", "所属网格", DataType.DATA_TYPE_ORG, "",
						"true" },
				{ "1", "partyOrgInfo.partyBranchName", "所属党支部", "", "", "true" },
				{ "2", "activityDate", "活动时间", DataType.DATA_TYPE_DATE, "",
						"true" },
				{ "3", "activityAddr", "活动地点", "", "", "true" },
				{ "4", "activitySubject", "活动主题", "", "", "true" },
				{ "5", "organizers", "组织者", "", "", "true" },
				{ "6", "participants", "参与者", "", "", "true" },
				{ "7", "activeContent", "活动内容", "", "", "true" } };
		return excelColumArray;
	}

	private PageInfo<PartyOrgActivity> emptyPage(int pageSize) {
		PageInfo<PartyOrgActivity> pageInfo = new PageInfo<PartyOrgActivity>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<PartyOrgActivity>());
		return pageInfo;
	}

	private void getPartyOrgActivityInfo() {
		if (null != population && null != population.getId()) {
			population = this.partyOrgActivityService
					.getPartyOrgActivityById(population.getId());
			population.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(population
							.getOrganization().getId(), organizationDubboService));
		}
	}

	private List<Long> initTargetId(String[] targetIds, int size) {
		List<Long> idLongs = new ArrayList<Long>();
		for (int i = size; i < targetIds.length; i++) {
			String tempId = targetIds[i];
			if (size == 0) {
				idLongs.add(Long.parseLong(targetIds[i]));
			} else {
				idLongs.add(Long.parseLong(tempId));
			}
		}
		return idLongs;
	}

	private void procOrganization() {
		Organization organization = new Organization();
		organization.setId(orgId);
		organization.setOrgName(ControllerHelper.getOrganizationRelativeName(
				organization.getId(), organizationDubboService));
		population = new PartyOrgActivity();
		population.setOrganization(organization);
	}

	public PartyOrgActivityFile getPartyOrgActivityFile() {
		return partyOrgActivityFile;
	}

	public void setPartyOrgActivityFile(
			PartyOrgActivityFile partyOrgActivityFile) {
		this.partyOrgActivityFile = partyOrgActivityFile;
	}

	public PartyOrgActivity getPopulation() {
		return population;
	}

	public void setPopulation(PartyOrgActivity population) {
		this.population = population;
	}

	public boolean isBol() {
		return bol;
	}

	public void setBol(boolean bol) {
		this.bol = bol;
	}

	public List<PartyOrgActivity> getPartyOrgActivityList() {
		return partyOrgActivityList;
	}

	public void setPartyOrgActivityList(
			List<PartyOrgActivity> partyOrgActivityList) {
		this.partyOrgActivityList = partyOrgActivityList;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getDeleteIds() {
		return deleteIds;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public void setDeleteIds(String deleteIds) {
		this.deleteIds = deleteIds;
	}

	public List<Long> getNoRelatePersonIds() {
		return noRelatePersonIds;
	}

	public void setNoRelatePersonIds(List<Long> noRelatePersonIds) {
		this.noRelatePersonIds = noRelatePersonIds;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public SearchPartyOrgActivityVo getSearchPartyOrgActivityVo() {
		return searchPartyOrgActivityVo;
	}

	public void setSearchPartyOrgActivityVo(
			SearchPartyOrgActivityVo searchPartyOrgActivityVo) {
		this.searchPartyOrgActivityVo = searchPartyOrgActivityVo;
	}
}