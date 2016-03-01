package com.tianque.baseInfo.actualHouse.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.aidNeedPopulation.domain.AidNeedPopulation;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.SearchHouseInfoVo;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.util.DateFormat;

@SuppressWarnings("serial")
@Controller("SearchActualHouseController")
@Namespace("/baseinfo/searchActualHouse")
@Scope("prototype")
public class SearchActualHouseController extends BaseAction {
	private SearchHouseInfoVo searchActualHouseVo;
	@Autowired
	private ActualHouseService searchActualHouseService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private PropertyDictService propertyDictService;

	private List<AutoCompleteData> autoCompleteDatas = new ArrayList<AutoCompleteData>();

	private Long organizationId;

	private String issueSerch = "";

	private boolean pageOnly;
	private AidNeedPopulation population;
	/** 研判分析饼图详情传来的年份*/
	private Long year;
	/** 研判分析饼图详情传来的月份 */
	private Long month;
	/** 研判分析饼图详情传来的类别 */
	private String detailedType;

	@PermissionFilter(ename = "searchActualHouse")
	@Action(value = "findActualHousesByQueryCondition", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String searchAidNeedPopulation() {
		if (searchActualHouseVo == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		searchCommonality();
		return SUCCESS;
	}

	@Action(value = "fastSearch", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String fastSearch() {
		if (searchActualHouseVo == null) {
			gridPage = new GridPage(new PageInfo<AidNeedPopulation>());
			return SUCCESS;
		} else {
			searchCommonality();
		}
		return SUCCESS;
	}
	
	@Action(value = "fastSearchFromMap", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String fastSearchFromMap() {
		searchCommonality();
		return SUCCESS;
	}
	
	private void searchCommonality() {
		if(searchActualHouseVo==null){
			searchActualHouseVo = new SearchHouseInfoVo();
		}
		if(detailedType!=null&&!"".equals(detailedType)){
			if(detailedType.equals("1")){
				searchActualHouseVo.setIsRentalHouse(1L);
			}
			if(detailedType.equals("0")){
				searchActualHouseVo.setIsRentalHouse(0L);
			}
		}
		if (issueSerch.equalsIgnoreCase("issueSerch")) {
			organizationId = organizationDubboService
					.getTownOrganizationId(organizationId);
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchActualHouseVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if(year!=null&&month!=null){
			searchActualHouseVo.setCreateDate(DateFormat.getDateByYearAndMonth(year,month));
		}
		PageInfo<AidNeedPopulation> pageInfo = ControllerHelper
				.processAllOrgRelativeName(searchActualHouseService
						.searchHouseInfos(page, rows, sidx, sord,searchActualHouseVo), organizationDubboService,
						new String[] { "organization" }, organizationId);
		gridPage = new GridPage(pageInfo);
	}


	public String getIssueSerch() {
		return issueSerch;
	}

	public void setIssueSerch(String issueSerch) {
		this.issueSerch = issueSerch;
	}

	public List<AutoCompleteData> getAutoCompleteDatas() {
		return autoCompleteDatas;
	}

	public void setAutoCompleteDatas(List<AutoCompleteData> autoCompleteDatas) {
		this.autoCompleteDatas = autoCompleteDatas;
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

	public SearchHouseInfoVo getSearchActualHouseVo() {
		return searchActualHouseVo;
	}

	public void setSearchActualHouseVo(
			SearchHouseInfoVo searchActualHouseVo) {
		this.searchActualHouseVo = searchActualHouseVo;
	}

	public AidNeedPopulation getPopulation() {
		return population;
	}

	public void setPopulation(AidNeedPopulation population) {
		this.population = population;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public String getDetailedType() {
		return detailedType;
	}

	public void setDetailedType(String detailedType) {
		this.detailedType = detailedType;
	}
	
}
