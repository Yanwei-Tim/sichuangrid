package com.tianque.baseInfo.nurturesWomen.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.floatingPopulation.domain.FloatingPopulation;
import com.tianque.baseInfo.nurturesWomen.domain.NurturesWomen;
import com.tianque.baseInfo.nurturesWomen.service.NurturesWomenService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModuleConstants;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.SearchNurturesWomenVo;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

@Transactional
@Scope("prototype")
@Controller("searchNurturesWomenController")
@Namespace("/baseinfo/searchNurturesWomen")
public class SearchNurturesWomenController extends SearchBaseAction {

	@Autowired
	private NurturesWomenService nurturesWomenService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private Long organizationId;

	private SearchNurturesWomenVo searchNurturesWomenVo;

	private boolean pageOnly;

	private List<AutoCompleteData> autoCompleteDatas = new ArrayList<AutoCompleteData>();

	private String tag;

	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private ShardConversion shardConversion;

	private NurturesWomen nurturesWomen;

	private NurturesWomen population;

	@PermissionFilter(ename = "searchNurturesWomen")
	@Actions({
			@Action(value = "fastSearch", results = { @Result(name = "success", type = "json", params = {
					"root", "gridPage", "ignoreHierarchy", "false" }) }),
			@Action(value = "findNurturesWomensByQueryCondition", results = { @Result(name = "success", type = "json", params = {
					"root", "gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }) }) })
	public String searchNurturesWomen() throws Exception {
		if (null == organizationId) {
			gridPage = new GridPage(new PageInfo<FloatingPopulation>());
			return SUCCESS;
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		String shardCode = shardConversion.getShardCode(organization
				.getDepartmentNo());
		searchNurturesWomenVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		searchNurturesWomenVo.setShardCode(shardCode);
		PageInfo<FloatingPopulation> pageInfo = ControllerHelper
				.processAllOrgRelativeName(nurturesWomenService
						.searchNurturesWomen(page, rows, sidx, sord,
								searchNurturesWomenVo), organizationDubboService,
						new String[] { "organization" }, organizationId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@PermissionFilter(ename = "downNurturesWomen")
	@Action(value = "downloadNurturesWomen")
	public String downloadNurturesWomen() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		if (searchNurturesWomenVo == null) {
			searchNurturesWomenVo = new SearchNurturesWomenVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		String shardCode = shardConversion.getShardCode(organization
				.getDepartmentNo());
		searchNurturesWomenVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		searchNurturesWomenVo.setShardCode(shardCode);
		List<NurturesWomen> records = getNeedExportDatas(page);
		inputStream = ExcelExportHelper.exportDateToExcel(
				nurturesWomenService.getExportPopertyArray(), records);
		downloadFileName = new String("育妇清单".getBytes("gbk"), "ISO8859-1")
				+ ".xls";
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.NURTURESWOMEN,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "育妇清单",
						ObjectToJSON.convertJSON(searchNurturesWomenVo));
		return STREAM_SUCCESS;
	}

	@PermissionFilter(ename = "downNurturesWomen")
	@Action(value = "downloadNurturesWomenAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchNurturesWomenVo == null) {
			searchNurturesWomenVo = new SearchNurturesWomenVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		String shardCode = shardConversion.getShardCode(organization
				.getDepartmentNo());
		searchNurturesWomenVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		searchNurturesWomenVo.setShardCode(shardCode);
		if (!pageOnly) {
			pageOnly = true;
			Integer count = nurturesWomenService
					.getCount(searchNurturesWomenVo);
			String[][] excelDefines = nurturesWomenService
					.getExportPopertyArray();
			exportDataAll(count, excelDefines, "育妇清单");
		}
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.NURTURESWOMEN,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "育妇清单",
						ObjectToJSON.convertJSON(searchNurturesWomenVo));
	}

	public List<NurturesWomen> getNeedExportDatas(int page) {
		List<NurturesWomen> list = new ArrayList<NurturesWomen>();
		if (population != null) {
			searchNurturesWomenVo.setIsEmphasis(population.getIsEmphasis());
		}
		if (pageOnly) {
			list = nurturesWomenService.searchNurturesWomen(page, rows, sidx,
					sord, searchNurturesWomenVo).getResult();

		} else {
			list = nurturesWomenService.searchAllNurturesWomen(sidx, sord,
					searchNurturesWomenVo);
		}
		return list;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public SearchNurturesWomenVo getSearchNurturesWomenVo() {
		return searchNurturesWomenVo;
	}

	public void setSearchNurturesWomenVo(
			SearchNurturesWomenVo searchNurturesWomenVo) {
		this.searchNurturesWomenVo = searchNurturesWomenVo;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public List<AutoCompleteData> getAutoCompleteDatas() {
		return autoCompleteDatas;
	}

	public void setAutoCompleteDatas(List<AutoCompleteData> autoCompleteDatas) {
		this.autoCompleteDatas = autoCompleteDatas;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public NurturesWomen getNurturesWomen() {
		return nurturesWomen;
	}

	public void setNurturesWomen(NurturesWomen nurturesWomen) {
		this.nurturesWomen = nurturesWomen;
	}

	public NurturesWomen getPopulation() {
		return population;
	}

	public void setPopulation(NurturesWomen population) {
		this.population = population;
	}
}
