package com.tianque.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModuleConstants;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.domain.Dustbin;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypesYinchuan;
import com.tianque.domain.vo.SearchDustbinVo;
import com.tianque.service.DustbinService;
import com.tianque.service.SearchDustbinService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.vladium.logging.Logger;

@Transactional
@Scope("prototype")
@Controller("searchDustbinController")
@Namespace("/baseinfo/searchDustbin")
public class SearchDustbinController extends SearchBaseAction {

	private SearchDustbinVo searchDustbinVo;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private DustbinService dustbinService;
	@Autowired
	private SearchDustbinService searchDustbinService;
	private Long organizationId;
	private Dustbin dustbin;
	private boolean pageOnly;
	@Autowired
	private SystemLogService systemLogService;
	private String partType;
	@Autowired
	private PropertyDictService propertyDictService;

	/**
	 * 查询部件信息
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "searchDustbin")
	@Action(value = "findDustbinsByQueryCondition", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findDustbinsByQueryCondition() throws Exception {
		if (searchDustbinVo == null && organizationId == null) {
			this.errorMessage = "参数错误";
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchDustbinVo.setOrgInternalCode(organization.getOrgInternalCode());
		PageInfo<Dustbin> pageInfo = ControllerHelper
				.processAllOrgRelativeName(dustbinService
						.searchDustbinPagerBySearchVo(organizationId,
								searchDustbinVo, page, rows, sidx, sord,
								partType), organizationDubboService,
						new String[] { "organization" }, organizationId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@Action(value = "fastSearch", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String fastSearch() throws Exception {
		if (searchDustbinVo == null) {
			gridPage = new GridPage(emptyDustbinPage(rows));
			return SUCCESS;
		} else {
			fastSearchCommonality();
		}
		return SUCCESS;
	}

	private void fastSearchCommonality() {
		Organization ownerOrg = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchDustbinVo.setOrgInternalCode(ownerOrg.getOrgInternalCode());
		searchDustbinVo.setTypeName(PropertyTypesYinchuan.PART_TYPE);
		searchDustbinVo.setPartTypeName(getPartTypeName(partType));
		PropertyDict dict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						searchDustbinVo.getTypeName(),
						searchDustbinVo.getPartTypeName());
		if (dict != null) {
			searchDustbinVo.setPartTypeId(dict.getId());
		}
		dict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypesYinchuan.PART_NAME,
						searchDustbinVo.getFastSearchKeyWords());
		if (dict != null) {
			searchDustbinVo.setPartName(dict.getId());
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchDustbinService.fastSearchDustbin(searchDustbinVo, page,
						rows, sidx, sord, null), organizationDubboService,
				new String[] { "organization" }, organizationId));
	}

	private PageInfo<Dustbin> emptyDustbinPage(int pageSize) {
		PageInfo<Dustbin> pageInfo = new PageInfo<Dustbin>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<Dustbin>());
		return pageInfo;
	}

	/**
	 * 导出
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "downloadDustbin")
	@Action(value = "downloadDustbin")
	public String downloadDustbin() throws Exception {
		if (searchDustbinVo == null && organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} else {
			populateOrgCondition();
			List<Dustbin> records = getNeedExportDatas(page);
			String fileName = getPartTypeName(partType);
			if (fileName == null || fileName.trim().length() == 0) {
				fileName = "部件信息";
			}
			downloadFileName = new String((fileName + "清单").getBytes("gbk"),
					"ISO8859-1") + ".xls";
			inputStream = ExcelExportHelper.exportDateToExcel(
					dustbinService.getExportPopertyArray(fileName + "清单"),
					records);
			systemLogService
					.log(Logger.INFO,
							ModuleConstants.DRUGGY,
							OperatorType.EXPORT,
							ThreadVariable.getSession().getUserName()
									+ "在"
									+ organizationDubboService
											.getOrganizationRelativeNameByRootOrgIdAndOrgId(
													organizationId,
													OrganizationServiceHelper
															.getRootOrg(
																	organizationDubboService)
															.getId())
									+ " 导出部件信息", ObjectToJSON
									.convertJSON(searchDustbinVo));
		}
		return STREAM_SUCCESS;
	}

	/**
	 * 导出
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "downloadDustbin")
	@Action(value = "downloadDustbinAll")
	public void downloadDustbinAll() throws Exception {
		if (searchDustbinVo == null && organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		} else {
			populateOrgCondition();
			String fileName = getPartTypeName(partType);
			if (fileName == null || fileName.trim().length() == 0) {
				fileName = "部件信息";
			}
			downloadFileName = new String((fileName + "清单").getBytes("gbk"),
					"ISO8859-1") + ".xls";
			if (!pageOnly) {
				pageOnly = true;
				Integer count = searchDustbinService.getCount(searchDustbinVo);
				String[][] excelDefines = dustbinService
						.getExportPopertyArray(fileName + "清单");
				exportDataAll(count, excelDefines, fileName);
			}
			systemLogService
					.log(Logger.INFO,
							ModuleConstants.DRUGGY,
							OperatorType.EXPORT,
							ThreadVariable.getSession().getUserName()
									+ "在"
									+ organizationDubboService
											.getOrganizationRelativeNameByRootOrgIdAndOrgId(
													organizationId,
													OrganizationServiceHelper
															.getRootOrg(
																	organizationDubboService)
															.getId())
									+ " 导出部件信息", ObjectToJSON
									.convertJSON(searchDustbinVo));
		}
		return;
	}

	private void populateOrgCondition() {
		if (searchDustbinVo == null) {
			searchDustbinVo = new SearchDustbinVo();
		}
		if (searchDustbinVo.getIsEmphasis() == null) {
			searchDustbinVo.setIsEmphasis(0L);
		}
		if (organizationId != null) {
			Organization org = organizationDubboService
					.getSimpleOrgById(organizationId);
			if (org != null) {
				searchDustbinVo.setOrgInternalCode(org.getOrgInternalCode());
			} else {
				searchDustbinVo.setOrgInternalCode(null);
			}
		} else {
			searchDustbinVo.setOrgInternalCode(null);
		}
		if (dustbin != null) {
			searchDustbinVo.setIsEmphasis(searchDustbinVo.getIsEmphasis());
		}
	}

	public List<Dustbin> getNeedExportDatas(int page) {
		if (searchDustbinVo != null) {
			searchDustbinVo.setPartType(partType);
			searchDustbinVo.setTypeName(PropertyTypesYinchuan.PART_TYPE);
			searchDustbinVo.setPartTypeName(getPartTypeName(partType));
			searchDustbinVo.setSortField(sidx);
			searchDustbinVo.setOrder(sord);
		}
		PropertyDict dict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						searchDustbinVo.getTypeName(),
						searchDustbinVo.getPartTypeName());
		if (dict != null) {
			searchDustbinVo.setPartTypeId(dict.getId());
		}
		if (pageOnly) {
			return searchDustbinService.searchDustbinForExport(searchDustbinVo,
					page, rows, sidx, sord, null);
		} else {
			return searchDustbinService.searchDustbinForExport(searchDustbinVo,
					null, null, sidx, sord, null);
		}
	}

	private String getPartTypeName(String partType) {
		String partTypeName = BaseInfoTables.digitalCityType.get(partType);
		if (partTypeName == null || partTypeName.trim().length() == 0) {
			partTypeName = "";
		}
		return partTypeName;
	}

	/**
	 * 序号|属性|中文名|属性类型|是否采用默认样式|合并行|合并列|
	 * 
	 * @return
	 */

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Dustbin getDustbin() {
		return dustbin;
	}

	public void setDustbin(Dustbin dustbin) {
		this.dustbin = dustbin;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public SearchDustbinVo getSearchDustbinVo() {
		return searchDustbinVo;
	}

	public void setSearchDustbinVo(SearchDustbinVo searchDustbinVo) {
		this.searchDustbinVo = searchDustbinVo;
	}

	public String getPartType() {
		return partType;
	}

	public void setPartType(String partType) {
		this.partType = partType;
	}

}
