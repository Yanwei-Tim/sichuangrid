package com.tianque.baseInfo.publicPlace.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.publicPlace.dao.SearchPublicPlaceDao;
import com.tianque.baseInfo.publicPlace.domain.PublicPlace;
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
import com.tianque.domain.vo.SearchPublicPlaceVo;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

@Transactional
@Scope("prototype")
@Controller("searchPublicPlaceController")
@Namespace("/baseinfo/searchPublicPlace")
public class SearchPublicPlaceController extends SearchBaseAction {

	@Autowired
	private SearchPublicPlaceDao searchPlaceDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;
	private Long organizationId;
	private SearchPublicPlaceVo searchPublicPlaceVo;
	private boolean pageOnly;
	private PublicPlace place;

	private PublicPlace location;

	public PublicPlace getPlace() {
		return place;
	}

	public void setPlace(PublicPlace place) {
		this.place = place;
	}

	public PublicPlace getLocation() {
		return location;
	}

	public void setLocation(PublicPlace location) {
		this.location = location;
	}

	@PermissionFilter(ename = "searchPublicPlace")
	@Action(value = "findPublicPlacesByQueryCondition", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findPublicPlacesByQueryCondition() throws Exception {
		if (searchPublicPlaceVo == null && organizationId == null) {
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
		if (searchPublicPlaceVo == null) {
			gridPage = new GridPage(emptyRectificativePersonPage(rows));
			return SUCCESS;
		} else {
			searchCommonality();
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "downPublicPlace")
	@Action(value = "downloadPublicPlace")
	public String downloadPublicPlace() throws Exception {
		if (null == organizationId) {
			this.errorMessage = "无法定位到需要查找的数据";
			return ERROR;
		} else {

			locationOrgCondtion();
			List<PublicPlace> records = getNeedExportDatas(page);
			String[][] excelColumArray = this.getPublicPlacePropertyArray();
			inputStream = ExcelExportHelper.exportDateToExcel(excelColumArray,
					records);
			downloadFileName = new String("公共场所清单".getBytes("gbk"), "ISO8859-1")
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
															.getId())
									+ " 导出公共场所", ObjectToJSON
									.convertJSON(searchPublicPlaceVo));
		}
		return STREAM_SUCCESS;
	}

	@PermissionFilter(ename = "downPublicPlace")
	@Action(value = "downloadPublicPlaceAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchPublicPlaceVo == null) {
			searchPublicPlaceVo = new SearchPublicPlaceVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchPublicPlaceVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = searchPlaceDao.getCount(searchPublicPlaceVo);
			String[][] excelDefines = getPublicPlacePropertyArray();
			exportDataAll(count, excelDefines, "公共场所清单");
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
														.getId()) + " 导出公共场所",
						ObjectToJSON.convertJSON(searchPublicPlaceVo));
	}

	private String[][] getPublicPlacePropertyArray() {
		String[][] excelColumArray = {
				{ "0", "", "公共场所表", "", "", "true", "0", "24" },
				{ "0", "placeName", "场所名称", "", "", "true" },
				{ "1", "placeAddress", "场所地址", "", "", "true" },
				{ "2", "manager", "负责人", "", "", "true" },
				{ "3", "licenseDate", "领取执照时间", DataType.DATA_TYPE_DATE, "",
						"true" },
				{ "4", "openingDate", "开业时间", DataType.DATA_TYPE_DATE, "",
						"true" },
				{ "5", "category", "公共场所类别", "", "", "true" },
				{ "6", "registrationNumber", "备案登记号码", "", "", "true" },
				{ "7", "organization", "所属网格", DataType.DATA_TYPE_ORG, "",
						"true" },
				{ "8", "placeLevel", "场所等级", "", "", "true" },
				{ "9", "buildingStructure", "建筑物结构", "", "", "true" },
				{ "10", "totalArea", "总面积(单位：平方米)", "", "", "true" },
				{ "11", "operationArea", "营业面积(单位：平方米)", "", "", "true" },
				{ "12", "cloakroom", "小件寄存处", DataType.DATA_TYPE_DICT, "",
						"true" },
				{ "13", "vouchedPeopleCount", "核定人数", "", "", "true" },
				{ "14", "privateRoomCount", "包间数", "", "", "true" },
				{ "15", "surrounding", "周围环境", "", "", "true" },
				{ "16", "passageway", "通道口", "", "", "true" },
				{ "17", "innerStructure", "内部结构", "", "", "true" },
				{ "18", "attentionExtent", "关注程度", DataType.DATA_TYPE_DICT,
						PropertyTypes.ATTENTION_EXTENT, "true" },
				{ "19", "hasServiceTeamMember", "有无治安负责人",
						DataType.DATA_TYPE_LONG, "", "true" },
				{ "20", "lastRecordTime", "最新巡场记录", DataType.DATA_TYPE_DATE,
						"", "true" },
				{ "21", "remark", "备注", "", "", "true" },
				// fateson add 添加 注销时间 注销原因
				{ "22", "isEmphasis", "是否注销", DataType.DATA_TYPE_BOOLEAN, "",
						"true" },
				{ "23", "logOutTime", "注销时间", DataType.DATA_TYPE_DATE, "",
						"true" },
				{ "24", "logOutReason", "注销原因", "", "", "true" } };
		return excelColumArray;
	}

	public List<PublicPlace> getNeedExportDatas(int page) {
		List<PublicPlace> list = new ArrayList<PublicPlace>();
		if (pageOnly) {
			list = searchPlaceDao.searchPublicPlaceForExport(
					searchPublicPlaceVo, page, rows, sidx, sord);
		} else {
			list = searchPlaceDao.searchPublicPlaceForExport(
					searchPublicPlaceVo, null, null, sidx, sord);
		}
		return list;
	}

	private void locationOrgCondtion() {
		if (null == searchPublicPlaceVo) {
			searchPublicPlaceVo = new SearchPublicPlaceVo();
		}
		if (null != organizationId) {
			Organization org = organizationDubboService
					.getSimpleOrgById(organizationId);
			if (org != null) {
				searchPublicPlaceVo
						.setOrgInternalCode(org.getOrgInternalCode());
			} else {
				searchPublicPlaceVo.setOrgInternalCode(null);
			}
		} else {
			searchPublicPlaceVo.setOrgInternalCode(null);
		}

		if (null != place) {
			searchPublicPlaceVo.setIsEmphasis(place.getIsEmphasis());
		}
		// 不知道上面的 place 起到什么作用 页面传入 location
		if (null != location) {
			searchPublicPlaceVo.setIsEmphasis(location.getIsEmphasis());
		}
	}

	private void searchCommonality() {
		Organization ownerOrg = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchPublicPlaceVo.setOrgInternalCode(ownerOrg.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchPlaceDao.findPublicPlacesByQueryCondition(
						searchPublicPlaceVo, page, rows, sidx, sord),
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

	public SearchPublicPlaceVo getSearchPublicPlaceVo() {
		return searchPublicPlaceVo;
	}

	public void setSearchPublicPlaceVo(SearchPublicPlaceVo searchPublicPlaceVo) {
		this.searchPublicPlaceVo = searchPublicPlaceVo;
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
