package com.tianque.baseInfo.handicapped.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.handicapped.dao.SearchHandicappedDao;
import com.tianque.baseInfo.handicapped.domain.Handicapped;
import com.tianque.baseInfo.handicapped.domain.HandicappedSdisabilityType;
import com.tianque.baseInfo.handicapped.service.HandicappedService;
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
import com.tianque.domain.PropertyDict;
import com.tianque.domain.vo.SearchHandicappedVo;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

@Transactional
@Controller("searchHandicappedController")
@Namespace("/baseinfo/searchHandicapped")
public class SearchHandicappedController extends SearchBaseAction {
	private SearchHandicappedVo searchHandicappedVo;
	private Long organizationId;

	@Autowired
	protected ActualPopulationProcessorService actualPopulationProcessorService;
	@Autowired
	private HandicappedService handicappedService;
	@Autowired
	private SearchHandicappedDao searchHandicappedDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;
	private List<AutoCompleteData> autoCompleteDatas = new ArrayList<AutoCompleteData>();
	private String tag;
	private Long orgId;
	private String issueSerch = "";
	private boolean pageOnly;
	private Handicapped handicapped;
	private Handicapped population;

	@Action(value = "findHandicappedsByQueryCondition", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String findHandicappedsByQueryCondition() throws Exception {
		if (organizationId == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		if (searchHandicappedVo == null) {
			searchHandicappedVo = new SearchHandicappedVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchHandicappedVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		PageInfo<Handicapped> pageInfo = ControllerHelper
				.processAllOrgRelativeName(handicappedService
						.searchHandicappeds(page, rows, sidx, sord,
								searchHandicappedVo), organizationDubboService,
						new String[] { "organization" }, organizationId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@Action(value = "findAllHandicappedForMobile", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String findAllHandicappedForMobile() throws Exception {
		if (organizationId == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		if (searchHandicappedVo == null) {
			searchHandicappedVo = new SearchHandicappedVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchHandicappedVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		PageInfo<Handicapped> pageInfo = ControllerHelper
				.processAllOrgRelativeName(handicappedService
						.searchHandicappedsForMobile(page, rows, sidx, sord,
								searchHandicappedVo), organizationDubboService,
						new String[] { "organization" }, organizationId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@Action(value = "fastSearch", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String fastSearch() {
		if (searchHandicappedVo == null) {
			gridPage = new GridPage(new PageInfo<Handicapped>());
			return SUCCESS;
		} else {
			fastSearchCommonality();
		}
		return SUCCESS;
	}

	private void fastSearchCommonality() {
		Organization ownerOrg = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchHandicappedVo.setOrgInternalCode(ownerOrg.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				handicappedService.searchHandicappeds(page, rows, sidx, sord,
						searchHandicappedVo), organizationDubboService,
				new String[] { "organization" }, organizationId));
	}

	@SuppressWarnings("unchecked")
	public String searchHandicappedForAutoComplete() throws Exception {
		if (orgId == null) {
			orgId = ThreadVariable.getUser().getOrganization().getId();
		}
		orgId = organizationDubboService.getTownOrganizationId(orgId);
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		List<Handicapped> handicappedList = (List<Handicapped>) searchHandicappedDao
				.findHandicappedNameAndPinyinAndOrgInternalCode(tag,
						organization.getOrgInternalCode());
		for (Handicapped handicapped : handicappedList) {
			AutoCompleteData autoCompleteData = new AutoCompleteData();
			autoCompleteData.setLabel(handicapped.getName());
			autoCompleteData.setDesc((handicapped.getCurrentAddress()));
			autoCompleteData.setValue(handicapped.getId().toString());
			autoCompleteDatas.add(autoCompleteData);
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "downHandicapped")
	@Action(value = "downloadHandicapped")
	public String downloadHandicapped() throws Exception {
		if (searchHandicappedVo == null && organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} else {
			populateOrgCondition();
			List<Handicapped> records = getNeedExportDatas(page);
			inputStream = ExcelExportHelper.exportDateToExcel(
					handicappedService.getExportPopertyArray(), records);
			downloadFileName = new String("残疾人清单".getBytes("gbk"), "ISO8859-1")
					+ ".xls";
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
									+ " 导出残疾人", ObjectToJSON
									.convertJSON(searchHandicappedVo));
		}
		return STREAM_SUCCESS;
	}

	@PermissionFilter(ename = "downHandicapped")
	@Action(value = "downloadHandicappedAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchHandicappedVo == null) {
			searchHandicappedVo = new SearchHandicappedVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchHandicappedVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = handicappedService.getCount(searchHandicappedVo);
			String[][] excelDefines = handicappedService
					.getExportPopertyArray();
			exportDataAll(count, excelDefines, "残疾人清单");
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
														.getId()) + " 导出残疾人",
						ObjectToJSON.convertJSON(searchHandicappedVo));
	}

	public List<Handicapped> getNeedExportDatas(int page) {
		List<Handicapped> list = null;
		if (population != null) {
			searchHandicappedVo.setIsEmphasis(population.getIsEmphasis());
		}
		if (pageOnly) {
			list = handicappedService.searchHandicappeds(page, rows, sidx,
					sord, searchHandicappedVo).getResult();
		} else {
			list = handicappedService
					.searchAllHandicappeds(searchHandicappedVo);
		}
		if (list != null) {
			for (Handicapped handicapped : list) {
				List<PropertyDict> disabilitys = new ArrayList<PropertyDict>();
				;
				List<PropertyDict> disabilityTypes = new ArrayList<PropertyDict>();
				;
				HandicappedSdisabilityType handicappedSdisabilityType = new HandicappedSdisabilityType();
				handicappedSdisabilityType.setId(handicapped.getId());
				List<HandicappedSdisabilityType> hdtList = handicappedService
						.queryDisabilityLevelById(handicappedSdisabilityType);
				for (HandicappedSdisabilityType dst : hdtList) {
					PropertyDict pd = new PropertyDict();
					pd.setId(dst.getHandicappedslevel());
					disabilitys.add(pd);
					PropertyDict pd2 = new PropertyDict();
					pd2.setId(dst.getHandicappedstype());
					disabilityTypes.add(pd2);
				}
				ActualPopulation actualPopulation = actualPopulationProcessorService
						.getActualPopulationbyOrgIdAndIdCardNo(handicapped
								.getOrganization().getId(), handicapped
								.getIdCardNo());
				handicapped.setHouseCode(actualPopulation.getHouseCode());

				handicapped.setDisabilitys(disabilitys);
				handicapped.setDisabilityTypes(disabilityTypes);
			}
		}
		return list;
	}

	private void populateOrgCondition() {
		if (searchHandicappedVo == null) {
			searchHandicappedVo = new SearchHandicappedVo();
		}
		if (organizationId != null) {
			Organization org = organizationDubboService
					.getSimpleOrgById(organizationId);
			if (org != null) {
				searchHandicappedVo
						.setOrgInternalCode(org.getOrgInternalCode());
			} else {
				searchHandicappedVo.setOrgInternalCode(null);
			}
		} else {
			searchHandicappedVo.setOrgInternalCode(null);
		}
		if (handicapped != null) {
			searchHandicappedVo.setIsEmphasis(handicapped.getIsEmphasis());
		}
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public SearchHandicappedVo getSearchHandicappedVo() {
		return searchHandicappedVo;
	}

	public void setSearchHandicappedVo(SearchHandicappedVo searchHandicappedVo) {
		this.searchHandicappedVo = searchHandicappedVo;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long orgId) {
		this.organizationId = orgId;
	}

	public Handicapped getHandicapped() {
		return handicapped;
	}

	public void setHandicapped(Handicapped handicapped) {
		this.handicapped = handicapped;
	}

	public Handicapped getPopulation() {
		return population;
	}

	public void setPopulation(Handicapped population) {
		this.population = population;
	}

}
