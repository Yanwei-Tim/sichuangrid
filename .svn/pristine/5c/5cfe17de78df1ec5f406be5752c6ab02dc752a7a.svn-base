package com.tianque.baseInfo.publicComplexPlaces.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.publicComplexPlaces.dao.SearchPublicComplexPlacesDao;
import com.tianque.baseInfo.publicComplexPlaces.domain.PublicComplexPlaces;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.datatransfer.DataType;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModuleConstants;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.domain.Organization;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.SearchPublicComplexPlacesVo;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

@Transactional
@Scope("prototype")
@Controller("searchPublicComplexPlacesController")
@Namespace("/baseinfo/searchPublicComplexPlaces")
public class SearchPublicComplexPlacesController extends SearchBaseAction {

	@Autowired
	private SearchPublicComplexPlacesDao searchPlaceDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;
	private Long organizationId;
	private SearchPublicComplexPlacesVo searchPublicComplexPlacesVo;
	private boolean pageOnly;
	private PublicComplexPlaces place;

	private PublicComplexPlaces location;

	public PublicComplexPlaces getPlace() {
		return place;
	}

	public void setPlace(PublicComplexPlaces place) {
		this.place = place;
	}

	public PublicComplexPlaces getLocation() {
		return location;
	}

	public void setLocation(PublicComplexPlaces location) {
		this.location = location;
	}

	@PermissionFilter(ename = "searchPublicComplexPlaces")
	@Action(value = "findPublicComplexPlacessByQueryCondition", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findPublicComplexPlacessByQueryCondition() throws Exception {
		if (searchPublicComplexPlacesVo == null && organizationId == null) {
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
		if (searchPublicComplexPlacesVo == null) {
			gridPage = new GridPage(emptyRectificativePersonPage(rows));
			return SUCCESS;
		} else {
			searchCommonality();
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "downPublicComplexPlaces")
	@Action(value = "downloadPublicComplexPlaces")
	public String downloadPublicComplexPlaces() throws Exception {
		if (null == organizationId) {
			this.errorMessage = "无法定位到需要查找的数据";
			return ERROR;
		} else {

			locationOrgCondtion();
			List<PublicComplexPlaces> records = getNeedExportDatas(page);
			String[][] excelColumArray = this
					.getPublicComplexPlacesPropertyArray();
			inputStream = ExcelExportHelper.exportDateToExcel(excelColumArray,
					records);
			downloadFileName = new String("公共复杂场所清单".getBytes("gbk"),
					"ISO8859-1") + ".xls";

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
															.getId())
									+ " 导出公共复杂场所", ObjectToJSON
									.convertJSON(searchPublicComplexPlacesVo));
		}
		return STREAM_SUCCESS;
	}

	@PermissionFilter(ename = "downPublicComplexPlaces")
	@Action(value = "downloadPublicComplexPlacesAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchPublicComplexPlacesVo == null) {
			searchPublicComplexPlacesVo = new SearchPublicComplexPlacesVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchPublicComplexPlacesVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = searchPlaceDao
					.getCount(searchPublicComplexPlacesVo);
			String[][] excelDefines = getPublicComplexPlacesPropertyArray();
			exportDataAll(count, excelDefines, "公共复杂场所清单");
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
														.getId()) + " 导出公共复杂场所",
						ObjectToJSON.convertJSON(searchPublicComplexPlacesVo));
	}

	private String[][] getPublicComplexPlacesPropertyArray() {
		String[][] excelColumArray = {
				{ "0", "", "公共复杂场所表", "", "", "true", "0", "24" },
				{ "0", "placeName", "场所名字", "", "", "true" },
				{ "1", "placeAddress", "场所地址", "", "", "true" },
				{ "2", "organization", "所属网格", DataType.DATA_TYPE_ORG, "",
						"true" },
				{ "3", "manager", "负责人", "", "", "true" },
				{ "4", "managerTelephone", "负责人电话", "", "", "true" },
				{ "5", "managerMobile", "负责人手机", "", "", "true" },
				{ "6", "attentionExtent", "关注程度", DataType.DATA_TYPE_DICT,
						PropertyTypes.ATTENTION_EXTENT, "true" },
				{ "7", "type", "场所类型", DataType.DATA_TYPE_DICT,
						PropertyTypes.PUBLICCOMPLEXPLACES_TYPE, "true" },
				{ "8", "detailedType", "场所细类", DataType.DATA_TYPE_DICT,
						PropertyTypes.PUBLICCOMPLEXPLACES_DETAILEDTYPE, "true" },
				{ "9", "hiddenCases", "存在隐患", "", "", "true" },
				{ "10", "hiddenRectification", "隐患整改情况", "", "", "true" },
				{ "11", "remark", "备注", "", "", "true" }, };
		return excelColumArray;
	}

	public List<PublicComplexPlaces> getNeedExportDatas(int page) {
		List<PublicComplexPlaces> list = new ArrayList<PublicComplexPlaces>();
		if (pageOnly) {
			list = searchPlaceDao.searchPublicComplexPlacesForExport(
					searchPublicComplexPlacesVo, page, rows, sidx, sord);
		} else {
			list = searchPlaceDao.searchPublicComplexPlacesForExport(
					searchPublicComplexPlacesVo, null, null, sidx, sord);
		}
		return list;
	}

	private void locationOrgCondtion() {
		if (null == searchPublicComplexPlacesVo) {
			searchPublicComplexPlacesVo = new SearchPublicComplexPlacesVo();
		}
		if (null != organizationId) {
			Organization org = organizationDubboService
					.getSimpleOrgById(organizationId);
			if (org != null) {
				searchPublicComplexPlacesVo.setOrgInternalCode(org
						.getOrgInternalCode());
			} else {
				searchPublicComplexPlacesVo.setOrgInternalCode(null);
			}
		} else {
			searchPublicComplexPlacesVo.setOrgInternalCode(null);
		}

		if (null != place) {
			searchPublicComplexPlacesVo.setIsEmphasis(place.getIsEmphasis());
		}
		// 不知道上面的 place 起到什么作用 页面传入 location
		if (null != location) {
			searchPublicComplexPlacesVo.setIsEmphasis(location.getIsEmphasis());
		}
	}

	private void searchCommonality() {
		Organization ownerOrg = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchPublicComplexPlacesVo.setOrgInternalCode(ownerOrg
				.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchPlaceDao.findPublicComplexPlacessByQueryCondition(
						searchPublicComplexPlacesVo, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" },
				organizationId));
	}

	private PageInfo<PublicComplexPlaces> emptyRectificativePersonPage(
			Integer pageSize) {
		PageInfo<PublicComplexPlaces> pageInfo = new PageInfo<PublicComplexPlaces>();
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<PublicComplexPlaces>());
		pageInfo.setTotalRowSize(0);
		return pageInfo;
	}

	public SearchPublicComplexPlacesVo getSearchPublicComplexPlacesVo() {
		return searchPublicComplexPlacesVo;
	}

	public void setSearchPublicComplexPlacesVo(
			SearchPublicComplexPlacesVo searchPublicComplexPlacesVo) {
		this.searchPublicComplexPlacesVo = searchPublicComplexPlacesVo;
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
}
