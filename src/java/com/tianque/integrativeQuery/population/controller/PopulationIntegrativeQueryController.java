package com.tianque.integrativeQuery.population.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.util.DateUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.vo.SearchIdleYouthVo;
import com.tianque.integrativeQuery.population.domain.PopulationQueryVo;
import com.tianque.integrativeQuery.population.service.PopulationIntegrativeQueryServie;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 人口综合查询的controller
 */
@Namespace("/integrativeQuery/populationIntegrativeQuery")
@Controller("PopultaionIntegrativeQueryManage")
public class PopulationIntegrativeQueryController extends SearchBaseAction {
	private PopulationQueryVo populationQueryVo;
	private String actualPersonType;
	private String attentionPopulationTypes;
	/** 性别 */
	private PropertyDict gender;
	private String birthdayStrart;
	private String birthdayEnd;
	private String hasHouse;
	private Long currOrgId;
	private String orgCode;
	private String staffTypeIds;
	private String searchText;
	@Autowired
	private PopulationIntegrativeQueryServie populationIntegrativeQueryServie;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private boolean pageOnly;

	@Action(value = "queryPopulation", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String queryPopulation() throws Exception {
		if (currOrgId == null) {
			errorMessage = "请先选择要查询的范围";
			return "error";
		}
		// 重点青少年的人员类型选择
		if (staffTypeIds != null && !staffTypeIds.isEmpty()) {
			SearchIdleYouthVo searchIdleYouthVo = new SearchIdleYouthVo();
			searchIdleYouthVo.setStaffTypeId(Arrays.asList(staffTypeIds
					.split(",")));
			populationQueryVo.setSearchIdleYouthVo(searchIdleYouthVo);
		}

		orgCode = organizationDubboService.getSimpleOrgById(currOrgId)
				.getOrgInternalCode();

		PageInfo<Countrymen> pageInfo = ControllerHelper
				.processAllOrgRelativeName(
						populationIntegrativeQueryServie
								.searchPopulationByIntegrativeCondition(
										populationQueryVo,
										actualPersonType,
										attentionPopulationTypes == null ? new String[0]
												: attentionPopulationTypes
														.split(","),
										gender,
										getDateBySelectInteger(birthdayStrart),
										getDateBySelectInteger(birthdayEnd,
												"end"), PopulationQueryVo
												.getIsHasHouse(hasHouse),
										orgCode, page, rows, sidx, sord),
						organizationDubboService, new String[] { "organization" },
						currOrgId);
		gridPage = new GridPage(pageInfo);

		return SUCCESS;
	}

	@Action(value = "queryPopulationForWorkBench", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String queryPopulationForWorkBench() throws Exception {

		if (currOrgId == null) {
			errorMessage = "请先选择要查询的范围";
			return "error";
		}

		orgCode = organizationDubboService.getSimpleOrgById(currOrgId)
				.getOrgInternalCode();

		PageInfo<Countrymen> pageInfo = ControllerHelper
				.processAllOrgRelativeName(populationIntegrativeQueryServie
						.queryPopulationForWorkBench(searchText, orgCode, page,
								rows, sidx, sord), organizationDubboService,
						new String[] { "organization" }, currOrgId);
		gridPage = new GridPage(pageInfo);

		return SUCCESS;
	}

	private Date getDateBySelectInteger(String dateStr, String type) {
		if (dateStr == null || dateStr.isEmpty() || dateStr.equals("-1")) {
			return null;
		}

		return DateUtil.parseDate(dateStr + "-12-31 23:59:59",
				"yyyy-MM-dd HH:mm:ss");

	}

	private Date getDateBySelectInteger(String dateStr) {
		if (dateStr == null || dateStr.isEmpty() || dateStr.equals("-1")) {
			return null;
		}
		return DateUtil.parseDate(dateStr, "yyyy");

	}

	@Action(value = "downloadQueryPopulation")
	public String downloadQueryPopulation() throws Exception {
		if (currOrgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} else {
			orgCode = organizationDubboService.getSimpleOrgById(currOrgId)
					.getOrgInternalCode();
			List<Countrymen> records = getNeedExportDatas(page);
			inputStream = ExcelExportHelper.exportDateToExcel(
					populationIntegrativeQueryServie.getExportPopertyArray(),
					records);
			downloadFileName = new String("查询结果列表".getBytes("gbk"), "ISO8859-1")
					+ ".xls";
		}
		return STREAM_SUCCESS;
	}

	@PermissionFilter(ename = "downloadQueryPopulation")
	@Action(value = "downloadQueryPopulationAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() throws Exception {
		if (currOrgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (!pageOnly) {
			orgCode = organizationDubboService.getSimpleOrgById(currOrgId)
					.getOrgInternalCode();
			pageOnly = true;
			List<Countrymen> records = getNeedExportDatas(page);
			String[][] excelDefines = populationIntegrativeQueryServie
					.getExportPopertyArray();
			exportDataAll(records == null ? 0 : records.size(), excelDefines,
					"查询结果列表");
		}
	}

	/**
	 * 设置list的实有人口的类型
	 * 
	 * @param oldRecords
	 * @return
	 */
	private List<Countrymen> rebuildRecords(List<Countrymen> oldRecords) {
		for (Countrymen countrymen : oldRecords) {
			countrymen.setActualPopulationType(getTypeCnameByename(countrymen
					.getActualPopulationType()));
		}
		return oldRecords;
	}

	private String getTypeCnameByename(String ename) {
		if ("floatingPopulations".equals(ename)) {
			return "流动人口";
		}
		if ("householdstaffs".equals(ename)) {
			return "户籍人口";
		}
		if ("unsettledPopulations".equals(ename)) {
			return "未落户人口";
		}
		return "";
	}

	@Override
	public List<Countrymen> getNeedExportDatas(int page) {
		if (pageOnly) {
			return rebuildRecords(populationIntegrativeQueryServie
					.searchPopulationByIntegrativeConditionForExport(
							populationQueryVo, actualPersonType,
							attentionPopulationTypes == null ? new String[0]
									: attentionPopulationTypes.split(","),
							gender, getDateBySelectInteger(birthdayStrart),
							getDateBySelectInteger(birthdayEnd, "end"),
							PopulationQueryVo.getIsHasHouse(hasHouse), orgCode,
							page, rows, sidx, sord));
		} else {
			return rebuildRecords(populationIntegrativeQueryServie
					.searchPopulationByIntegrativeConditionForExport(
							populationQueryVo, actualPersonType,
							attentionPopulationTypes == null ? new String[0]
									: attentionPopulationTypes.split(","),
							gender, getDateBySelectInteger(birthdayStrart),
							getDateBySelectInteger(birthdayEnd, "end"),
							PopulationQueryVo.getIsHasHouse(hasHouse), orgCode,
							null, null, sidx, sord));
		}
	}

	@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/integrativeQuery/population/searchDlg.jsp"),
			@Result(name = "queryForWorkBench", location = "/integrativeQuery/population/searchResultListDlg.jsp") })
	public String dispatchOperate() throws Exception {
		if ("queryForWorkBench".equals(mode))
			return "queryForWorkBench";

		return SUCCESS;
	}

	public PopulationQueryVo getPopulationQueryVo() {
		return populationQueryVo;
	}

	public void setPopulationQueryVo(PopulationQueryVo populationQueryVo) {
		this.populationQueryVo = populationQueryVo;
	}

	public String getActualPersonType() {
		return actualPersonType;
	}

	public String getHasHouse() {
		return hasHouse;
	}

	public void setHasHouse(String hasHouse) {
		this.hasHouse = hasHouse;
	}

	public Long getCurrOrgId() {
		return currOrgId;
	}

	public void setCurrOrgId(Long currOrgId) {
		this.currOrgId = currOrgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public PropertyDict getGender() {
		return gender;
	}

	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}

	public void setActualPersonType(String actualPersonType) {
		this.actualPersonType = actualPersonType;
	}

	public String getAttentionPopulationTypes() {
		return attentionPopulationTypes;
	}

	public void setAttentionPopulationTypes(String attentionPopulationTypes) {
		this.attentionPopulationTypes = attentionPopulationTypes;
	}

	public String getStaffTypeIds() {
		return staffTypeIds;
	}

	public void setStaffTypeIds(String staffTypeIds) {
		this.staffTypeIds = staffTypeIds;
	}

	public String getBirthdayStrart() {
		return birthdayStrart;
	}

	public void setBirthdayStrart(String birthdayStrart) {
		this.birthdayStrart = birthdayStrart;
	}

	public String getBirthdayEnd() {
		return birthdayEnd;
	}

	public void setBirthdayEnd(String birthdayEnd) {
		this.birthdayEnd = birthdayEnd;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

}
