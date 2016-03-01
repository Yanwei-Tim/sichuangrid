package com.tianque.baseInfo.scenicManage.scenicTraffic.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.baseInfo.publicPlace.domain.PublicPlace;
import com.tianque.baseInfo.scenicManage.scenicTraffic.domain.ScenicTraffic;
import com.tianque.baseInfo.scenicManage.scenicTraffic.service.ScenicTrafficService;
import com.tianque.baseInfo.scenicManage.scenicTraffic.vo.SearchScenicTrafficVo;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModuleConstants;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.domain.Organization;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

/**
 * @ClassName: SearchScenicTrafficController
 * @Description: TODO
 * @author wangxiaohu wsmalltiger@163.com
 * @date Oct 31, 2013 2:58:31 PM
 */
@Namespace("/baseinfo/searchScenicTraffic")
public class SearchScenicTrafficController extends SearchBaseAction {

	@Autowired
	private ScenicTrafficService scenicTrafficService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;
	private Long organizationId;
	private SearchScenicTrafficVo searchScenicTrafficVo;
	private boolean pageOnly;
	private ScenicTraffic scenicTraffic;

	@PermissionFilter(ename = "searchScenicTraffic")
	@Action(value = "findScenicTrafficsByQueryCondition", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findPublicPlacesByQueryCondition() throws Exception {
		if (searchScenicTrafficVo == null && organizationId == null) {
			this.errorMessage = "参数错误";
		} else {
			searchCommonality();
		}
		return SUCCESS;
	}

	@Action(value = "fastSearch", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "igonreHierarch", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String fastSearch() throws Exception {
		if (searchScenicTrafficVo == null) {
			gridPage = new GridPage(emptyRectificativePersonPage(rows));
			return SUCCESS;
		} else {
			searchCommonality();
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "downScenicTraffic")
	@Action(value = "downloadScenicTraffic")
	public String downloadScenicTraffic() throws Exception {
		if (null == organizationId) {
			this.errorMessage = "无法定位到需要查找的数据";
			return ERROR;
		} else {
			locationOrgCondtion();
			List<ScenicTraffic> records = getNeedExportDatas(page);
			String[][] excelColumArray = SpecialGroupsContext
					.getScenicTrafficImportArray();
			inputStream = ExcelExportHelper.exportDateToExcel(excelColumArray,
					records);
			downloadFileName = new String("景区交通清单".getBytes("gbk"), "ISO8859-1")
					+ ".xls";
			systemLogService
					.log(Logger.INFO,
							ModuleConstants.PUBLICPLACE,
							OperatorType.EXPORT,
							ThreadVariable.getSession().getUserName()
									+ "在"
									+ organizationDubboService
											.getOrganizationRelativeNameByRootOrgIdAndOrgId(
													organizationId,
													OrganizationServiceHelper
															.getRootOrg(
																	organizationDubboService)
															.getId()) + "景区交通",
							ObjectToJSON.convertJSON(searchScenicTrafficVo));
		}
		return STREAM_SUCCESS;
	}

	@PermissionFilter(ename = "downScenicTraffic")
	@Action(value = "downloadScenicTrafficAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchScenicTrafficVo == null) {
			searchScenicTrafficVo = new SearchScenicTrafficVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchScenicTrafficVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = scenicTrafficService
					.getCount(searchScenicTrafficVo);
			String[][] excelDefines = SpecialGroupsContext
					.getScenicTrafficImportArray();
			exportDataAll(count, excelDefines, "景区交通清单");
		}
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.PUBLICPLACE,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + " 景区交通",
						ObjectToJSON.convertJSON(searchScenicTrafficVo));
	}

	public List<ScenicTraffic> getNeedExportDatas(int page) {
		List<ScenicTraffic> list = new ArrayList<ScenicTraffic>();
		if (pageOnly) {
			list = scenicTrafficService.searchScenicTrafficForPage(page, rows,
					sidx, sord, searchScenicTrafficVo).getResult();
		} else {
			list = scenicTrafficService.searchScenicTrafficForPage(null, rows,
					sidx, sord, searchScenicTrafficVo).getResult();
		}
		return list;
	}

	private void locationOrgCondtion() {
		if (null == searchScenicTrafficVo) {
			searchScenicTrafficVo = new SearchScenicTrafficVo();
		}
		if (null != organizationId) {
			Organization org = organizationDubboService
					.getSimpleOrgById(organizationId);
			if (org != null) {
				searchScenicTrafficVo.setOrgInternalCode(org
						.getOrgInternalCode());
			} else {
				searchScenicTrafficVo.setOrgInternalCode(null);
			}
		} else {
			searchScenicTrafficVo.setOrgInternalCode(null);
		}
		if (null != scenicTraffic) {
			searchScenicTrafficVo.setIsEmphasis(scenicTraffic.getIsEmphasis());
		}
	}

	private void searchCommonality() {
		searchScenicTrafficVo.setOrganization(organizationDubboService
				.getSimpleOrgById(organizationId));
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				scenicTrafficService.searchScenicTrafficForPage(page, rows,
						sidx, sord, searchScenicTrafficVo),
				organizationDubboService, new String[] { "organization" },
				organizationId));
	}

	private PageInfo<PublicPlace> emptyRectificativePersonPage(Integer pageSize) {
		PageInfo<PublicPlace> pageInfo = new PageInfo<PublicPlace>();
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<PublicPlace>());
		pageInfo.setTotalRowSize(0);
		return pageInfo;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public SearchScenicTrafficVo getSearchScenicTrafficVo() {
		return searchScenicTrafficVo;
	}

	public void setSearchScenicTrafficVo(
			SearchScenicTrafficVo searchScenicTrafficVo) {
		this.searchScenicTrafficVo = searchScenicTrafficVo;
	}

	public ScenicTraffic getScenicTraffic() {
		return scenicTraffic;
	}

	public void setScenicTraffic(ScenicTraffic scenicTraffic) {
		this.scenicTraffic = scenicTraffic;
	}

}
