package com.tianque.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.datatransfer.DataType;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModuleConstants;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.domain.NewEconomicOrganizations;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.SearchNewEconomicOrganizationsVo;
import com.tianque.service.NewEconomicOrganizationsService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Controller("newEconomicOrganizationsController")
@Scope("prototype")
@Transactional
public class NewEconomicOrganizationsController extends SearchBaseAction {
	@Autowired
	protected OrganizationDubboService organizationDubboService;

	@Autowired
	protected NewEconomicOrganizationsService newEconomicOrganizationsService;

	@Autowired
	private SystemLogService systemLogService;

	private NewEconomicOrganizations newEconomicOrganizations;
	private SearchNewEconomicOrganizationsVo searchNewEconomicOrganizationsVo;
	private String newEconomicOrganizationsIds;
	private Long organizationId;
	private List<NewEconomicOrganizations> newEconomicOrganizationsList;
	private String dailogName;
	private boolean pageOnly;
	private String errorMessage;
	private NewEconomicOrganizations location;
	protected String locationIds;

	@PermissionFilter(ename = "addNewEconomicOrganizations")
	public String addNewEconomicOrganizations() throws Exception {
		newEconomicOrganizations = newEconomicOrganizationsService
				.addNewEconomicOrganizations(newEconomicOrganizations);
		return SUCCESS;
	}

	public String dispath() throws Exception {
		if (null == id && newEconomicOrganizations != null
				&& newEconomicOrganizations.getId() != null) {
			id = newEconomicOrganizations.getId();
		}
		if (null != id) {
			newEconomicOrganizations = newEconomicOrganizationsService
					.getNewEconomicOrganizationsById(id);
			newEconomicOrganizations.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							newEconomicOrganizations.getOrganization().getId(),
							organizationDubboService));
			organizationId = newEconomicOrganizations.getOrganization().getId();
		}
		if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			return "view";
		} else if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			return "print";
		}
		return SUCCESS;
	}

	@EncryptAnnotation
	public String dispathByEncrypt() throws Exception {
		if (null == id && newEconomicOrganizations != null
				&& newEconomicOrganizations.getId() != null) {
			id = newEconomicOrganizations.getId();
		}
		if (null != id) {
			newEconomicOrganizations = newEconomicOrganizationsService
					.getNewEconomicOrganizationsById(id);
			newEconomicOrganizations.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							newEconomicOrganizations.getOrganization().getId(),
							organizationDubboService));
			organizationId = newEconomicOrganizations.getOrganization().getId();
		}
		if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			return "view";
		} else if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			return "print";
		}
		return SUCCESS;
	}

	@EncryptAnnotation
	public String viewNewEconomicOrganizationsByEncrypt() throws Exception {
		if (null != newEconomicOrganizations
				&& null != newEconomicOrganizations.getId()) {
			newEconomicOrganizations = newEconomicOrganizationsService
					.getNewEconomicOrganizationsById(newEconomicOrganizations
							.getId());
			newEconomicOrganizations.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							newEconomicOrganizations.getOrganization().getId(),
							organizationDubboService));
		}
		return SUCCESS;
	}

	public String viewNewEconomicOrganizations() throws Exception {
		if (null != newEconomicOrganizations
				&& null != newEconomicOrganizations.getId()) {
			newEconomicOrganizations = newEconomicOrganizationsService
					.getNewEconomicOrganizationsById(newEconomicOrganizations
							.getId());
			newEconomicOrganizations.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							newEconomicOrganizations.getOrganization().getId(),
							organizationDubboService));
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "updateNewEconomicOrganizations")
	public String update() throws Exception {
		newEconomicOrganizations = newEconomicOrganizationsService
				.updateNewEconomicOrganizations(newEconomicOrganizations);
		return SUCCESS;
	}

	@PermissionFilter(ename = "addNewEconomicOrganizations")
	public String saveNewEconomicOrganizations() throws Exception {
		if (null != newEconomicOrganizations
				&& null != newEconomicOrganizations.getId()) {
			newEconomicOrganizations = this.newEconomicOrganizationsService
					.updateNewEconomicOrganizations(newEconomicOrganizations);

		} else {
			newEconomicOrganizations = this.newEconomicOrganizationsService
					.addNewEconomicOrganizations(newEconomicOrganizations);
		}
		newEconomicOrganizations.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeNameList(
						newEconomicOrganizations.getOrganization().getId(),
						organizationDubboService, newEconomicOrganizations
								.getOrganization().getId()));
		return SUCCESS;
	}

	@PermissionFilter(ename = "getNewEconomicOrganizations")
	public String searchNewEconomicOrganizations() throws Exception {
		if (null == organizationId) {
			gridPage = new GridPage(new PageInfo<NewEconomicOrganizations>());
			return SUCCESS;
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		if (null == searchNewEconomicOrganizationsVo) {
			searchNewEconomicOrganizationsVo = new SearchNewEconomicOrganizationsVo();
		}
		searchNewEconomicOrganizationsVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		PageInfo<NewEconomicOrganizations> pageInfo = ControllerHelper
				.processAllOrgRelativeName(newEconomicOrganizationsService
						.searchNewEconomicOrganizationss(page, rows, sidx,
								sord, searchNewEconomicOrganizationsVo),
						organizationDubboService, new String[] { "organization" },
						organizationId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@PermissionFilter(ename = "getNewEconomicOrganizations")
	public String findNewEconomicOrganizations() throws Exception {
		if (null == organizationId) {
			gridPage = new GridPage(new PageInfo<NewEconomicOrganizations>());
			return SUCCESS;
		}
		PageInfo<NewEconomicOrganizations> pageInfo;
		if (null != searchNewEconomicOrganizationsVo) {
			pageInfo = ControllerHelper
					.processAllOrgRelativeName(
							newEconomicOrganizationsService
									.findNewEconomicOrganizationsForPageByOrgInternalCode(
											organizationId, page, rows, sidx,
											sord,
											searchNewEconomicOrganizationsVo
													.getIsEmphasis()),
							organizationDubboService,
							new String[] { "organization" }, organizationId);
		} else {
			pageInfo = ControllerHelper
					.processAllOrgRelativeName(
							newEconomicOrganizationsService
									.findNewEconomicOrganizationsForPageByOrgInternalCode(
											organizationId, page, rows, sidx,
											sord, null), organizationDubboService,
							new String[] { "organization" }, organizationId);
		}
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public String getNewEconomicOrganizationsById() throws Exception {
		if (id == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		newEconomicOrganizations = newEconomicOrganizationsService
				.getNewEconomicOrganizationsById(id);
		return SUCCESS;
	}

	private Boolean hasDuplicateNewEconomicOrganizations;

	public Boolean getHasDuplicateNewEconomicOrganizations() {
		return hasDuplicateNewEconomicOrganizations;
	}

	public void setHasDuplicateNewEconomicOrganizations(
			Boolean hasDuplicateNewEconomicOrganizations) {
		this.hasDuplicateNewEconomicOrganizations = hasDuplicateNewEconomicOrganizations;
	}

	public String hasDuplicateNewEconomicOrganizations() throws Exception {
		if (organizationId == null) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		} else {
			if (newEconomicOrganizations.getName() != null) {
				hasDuplicateNewEconomicOrganizations = newEconomicOrganizationsService
						.hasDuplicateNewEconomicOrganizationsByName(
								newEconomicOrganizations.getName(),
								organizationId,
								newEconomicOrganizations.getId());
			} else if (newEconomicOrganizations.getLicenseNumber() != null) {
				hasDuplicateNewEconomicOrganizations = newEconomicOrganizationsService
						.hasDuplicateNewEconomicOrganizationsByLicenseNumber(
								newEconomicOrganizations.getLicenseNumber(),
								organizationId,
								newEconomicOrganizations.getId());
			}
			return SUCCESS;
		}
	}

	/**
	 * 注销
	 * 
	 * @return
	 */
	public String updateLogOutOfNewEconomicOrganizationss() throws Exception {
		String[] updateId = newEconomicOrganizationsIds.split(",");
		List<Long> idList;
		if (updateId[0].equals("")) {
			idList = initTargetId(updateId, 1);
		} else {
			idList = initTargetId(updateId, 0);
		}
		newEconomicOrganizationsList = newEconomicOrganizationsService
				.updateLogOutOfNewEconomicOrganizationssByIdList(idList,
						newEconomicOrganizations.getIsEmphasis());
		return SUCCESS;
	}

	/**
	 * 注销
	 * 
	 * @return
	 */
	public String updateEmphasiseById() throws Exception {
		newEconomicOrganizationsService.updateEmphasiseByIds(
				analyzeLocationIds(), location);
		return SUCCESS;
	}

	private List<Long> analyzeLocationIds() {
		String[] selIds = locationIds.split(",");
		List<Long> deleteIds = new ArrayList<Long>();
		for (int i = 0; i < selIds.length; i++) {
			if (selIds[i].equals("")) {
				continue;
			}
			deleteIds.add(Long.valueOf(selIds[i]));
		}
		return deleteIds;
	}

	@PermissionFilter(ename = "deleteNewEconomicOrganizations")
	@EncryptAnnotation
	public String deleteNewEconomicOrganizations() throws Exception {
		String[] deleteId = newEconomicOrganizationsIds.split(",");
		List<Long> idList;
		if (deleteId[0].equals("")) {
			idList = initTargetId(deleteId, 1);
		} else {
			idList = initTargetId(deleteId, 0);
		}
		newEconomicOrganizationsService
				.deleteNewEconomicOrganizationssByIdList(idList);
		return SUCCESS;
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

	/**
	 * 导出
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "downloadNewEconomicOrganizations")
	public String downloadNewEconomicOrganizations() throws Exception {
		if (null == organizationId || null == searchNewEconomicOrganizationsVo) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		Organization organization = this.organizationDubboService
				.getSimpleOrgById(organizationId);
		searchNewEconomicOrganizationsVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		List<NewEconomicOrganizations> newEconomicOrganizationsList = getNeedExportDatas(page);
		String[][] excelColumArray = this.getOptimalObjectPropertyArray();
		inputStream = ExcelExportHelper.exportDateToExcel(excelColumArray,
				newEconomicOrganizationsList);
		downloadFileName = new String(excelColumArray[0][2].getBytes("gbk"),
				"ISO8859-1") + ".xls";
		systemLogService
				.log(com.vladium.logging.Logger.INFO,
						ModuleConstants.ELDERLYPEOPLE,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出新经济组织",
						ObjectToJSON.convertJSON(new HouseholdStaff()));
		return SUCCESS;
	}

	@PermissionFilter(ename = "downloadNewEconomicOrganizations")
	public void downloadExcelExportAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchNewEconomicOrganizationsVo == null) {
			searchNewEconomicOrganizationsVo = new SearchNewEconomicOrganizationsVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchNewEconomicOrganizationsVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = newEconomicOrganizationsService
					.getCount(searchNewEconomicOrganizationsVo);
			String[][] excelDefines = getOptimalObjectPropertyArray();
			exportDataAll(count, excelDefines, "新经济组织清单");
		}
		systemLogService
				.log(com.vladium.logging.Logger.INFO,
						ModuleConstants.ELDERLYPEOPLE,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出新经济组织",
						ObjectToJSON.convertJSON(new HouseholdStaff()));
	}

	/**
	 * 序号|属性|中文名|属性类型|是否采用默认样式|合并行|合并列|
	 * 
	 * @return
	 */
	private String[][] getOptimalObjectPropertyArray() {
		String[][] excelColumArray = {
				{ "0", "", "新经济组织信息", "", "", "true", "0", "21" },
				{ "0", "name", "名称", "", "", "true" },
				{ "1", "licenseNumber", "营业执照号码", "", "", "true" },
				{ "2", "organization", "所属网格", DataType.DATA_TYPE_ORG, "",
						"true" },
				{ "3", "residence", "住所", "", "", "true" },
				{ "4", "validityStartDate", "有效期开始日期", DataType.DATA_TYPE_DATE,
						"", "true" },
				{ "5", "validityEndDate", "有效期结束日期", DataType.DATA_TYPE_DATE,
						"", "true" },
				{ "6", "style", "类别", DataType.DATA_TYPE_DICT, "", "true" },
				{ "7", "areaStringValue", "面积（平方米）", "", "", "true" },
				{ "8", "employeeNumber", "从业人数", "", "", "true" },
				{ "9", "partyMemberNumber", "党员人数", "", "", "true" },
				{ "10", "chief", "负责人（法人）", "", "", "true" },
				{ "11", "foxNumber", "传真", "", "", "true" },
				{ "12", "telephone", "固定电话", "", "", "true" },
				{ "13", "mobileNumber", "联系手机", "", "", "true" },
				{ "14", "introduction", "简介", "", "", "true" },
				{ "15", "honor", "荣誉", "", "", "true" },
				{ "16", "hasServiceTeamMember", "有无治安负责人",
						DataType.DATA_TYPE_COUNTS, "", "true" },
				{ "17", "lastRecordTime", "最新巡场时间", DataType.DATA_TYPE_DATE,
						"", "true" },
				{ "18", "remark", "备注", "", "", "true" },
				{ "19", "isEmphasis", "是否注销", DataType.DATA_TYPE_BOOLEAN, "",
						"true" },
				{ "20", "logoutDetail.logoutDate", "注销时间",
						DataType.DATA_TYPE_DATE, "", "true" },
				{ "21", "logoutDetail.logoutReason", "注销原因", "", "", "true" }

		};
		return excelColumArray;
	}

	public NewEconomicOrganizations getNewEconomicOrganizations() {
		return newEconomicOrganizations;
	}

	public void setNewEconomicOrganizations(
			NewEconomicOrganizations newEconomicOrganizations) {
		this.newEconomicOrganizations = newEconomicOrganizations;
	}

	public SearchNewEconomicOrganizationsVo getSearchNewEconomicOrganizationsVo() {
		return searchNewEconomicOrganizationsVo;
	}

	public void setSearchNewEconomicOrganizationsVo(
			SearchNewEconomicOrganizationsVo searchNewEconomicOrganizationsVo) {
		this.searchNewEconomicOrganizationsVo = searchNewEconomicOrganizationsVo;
	}

	public String getNewEconomicOrganizationsIds() {
		return newEconomicOrganizationsIds;
	}

	public void setNewEconomicOrganizationsIds(
			String newEconomicOrganizationsIds) {
		this.newEconomicOrganizationsIds = newEconomicOrganizationsIds;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public List<NewEconomicOrganizations> getNewEconomicOrganizationsList() {
		return newEconomicOrganizationsList;
	}

	public void setNewEconomicOrganizationsList(
			List<NewEconomicOrganizations> newEconomicOrganizationsList) {
		this.newEconomicOrganizationsList = newEconomicOrganizationsList;
	}

	public String getDailogName() {
		return dailogName;
	}

	public void setDailogName(String dailogName) {
		this.dailogName = dailogName;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public NewEconomicOrganizations getLocation() {
		return location;
	}

	public void setLocation(NewEconomicOrganizations location) {
		this.location = location;
	}

	public String getLocationIds() {
		return locationIds;
	}

	public void setLocationIds(String locationIds) {
		this.locationIds = locationIds;
	}

	@Override
	public List getNeedExportDatas(int page) {
		// TODO Auto-generated method stub
		List<NewEconomicOrganizations> newEconomicOrganizationsList = new ArrayList<NewEconomicOrganizations>();
		if (pageOnly) {
			newEconomicOrganizationsList = this.newEconomicOrganizationsService
					.searchNewEconomicOrganizationss(page, rows, sidx, sord,
							searchNewEconomicOrganizationsVo).getResult();
		} else {
			newEconomicOrganizationsList = this.newEconomicOrganizationsService
					.searchAllNewEconomicOrganizationss(sidx, sord,
							searchNewEconomicOrganizationsVo);
		}
		return newEconomicOrganizationsList;
	}

}
