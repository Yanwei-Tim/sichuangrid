package com.tianque.baseInfo.handicapped.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.controller.PopulationControllerAdapter;
import com.tianque.baseInfo.handicapped.domain.Handicapped;
import com.tianque.baseInfo.handicapped.domain.HandicappedSdisabilityType;
import com.tianque.baseInfo.handicapped.service.HandicappedService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.SearchHandicappedVo;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Controller("handicappedController")
@Namespace("/baseinfo/handicappedManage")
@Scope("prototype")
@Transactional
public class HandicappedController extends
		PopulationControllerAdapter<Handicapped> {
	@Autowired
	private HandicappedService handicappedService;
	@Autowired
	protected OrganizationDubboService organizationDubboService;

	private SearchHandicappedVo searchHandicappedVo;
	@Autowired
	private PropertyDictService propertyDictService;

	private Handicapped population;
	private Handicapped handicapped;
	private List<Handicapped> populationList;
	private String supervisorName;// 监管人员名称
	private String populationType;// 人员类型名称
	private Map<Long, String> disablilityLevelMap;
	private String disabilityTypeIds;
	private String disabilityLevelIds;
	private Long orgId;

	@Action(value = "handicappedList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findGridPageByOrgIdAndIsEmphasis() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					handicappedService.findHandicappedsForPageByOrganizationId(
							organizationId, page, rows, sidx, sord,
							population.getIsEmphasis()), organizationDubboService,
					new String[] { "organization" }, organizationId));
		}

		return SUCCESS;
	}

	private PageInfo<Handicapped> emptyPage(int pageSize) {
		PageInfo<Handicapped> pageInfo = new PageInfo<Handicapped>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<Handicapped>());
		return pageInfo;
	}

	@Actions({
			@Action(value = "dispatchOperate", results = {
					@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
					@Result(name = "search", location = "/baseinfo/handicapped/searchHandicappedCondition.jsp"),
					@Result(name = "view", location = "/baseinfo/handicapped/viewHandicappedDlg.jsp"),
					@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
					@Result(name = "statistic", location = "/baseinfo/handicapped/handicappedListForStatistics.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "dispatchOperateForGis", results = {
					@Result(name = "view", location = "/baseinfo/handicapped/viewHandicappedDlg_gis.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	@Override
	public String dispatchOperate() throws Exception {
		actionName = "maintainHandicappedBaseInfo";
		ajaxUrl = "hasDuplicateHandicapped";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@EncryptAnnotation
	@Actions({ @Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/commonPopulationDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/handicapped/viewHandicappedDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "statistic", location = "/baseinfo/handicapped/handicappedListForStatistics.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperateByEncrypt() throws Exception {
		actionName = "maintainHandicappedBaseInfo";
		ajaxUrl = "hasDuplicateHandicapped";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@Override
	protected Handicapped getPopulationFetchOrgById(Long id) {
		Handicapped population = handicappedService.getHandicappedById(id);
		population.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(population
						.getOrganization().getId(), organizationDubboService));
		return population;
	}

	@Action(value = "dispatchHandicappedBusiness", results = {
			@Result(name = "success", location = "/baseinfo/handicapped/handicappedBusinessDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchBusiness() throws Exception {
		handicapped = new Handicapped();
		handicapped.setDisabilityTypes(propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.DISABILITY_TYPE));
		handicapped.setDisabilitys(propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.DISABILITY_STATUS));
		if (null != id) {
			population = getPopulationFetchOrgById(id);
		}
		if (population != null) {
			HandicappedSdisabilityType handicappedSdisabilityType = new HandicappedSdisabilityType();
			handicappedSdisabilityType.setId(id);
			List<HandicappedSdisabilityType> hdtList = handicappedService
					.queryDisabilityLevelById(handicappedSdisabilityType);
			if (disablilityLevelMap == null) {
				disablilityLevelMap = new HashMap<Long, String>();
			}
			disablilityLevelMap.clear();
			for (HandicappedSdisabilityType hst : hdtList) {
				disablilityLevelMap.put(hst.getHandicappedstype(),
						hst.getHandicappedslevel() + "");
			}
		}
		return SUCCESS;
	}

	@Action(value = "hasDuplicateHandicapped", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicatePopulation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicatePopulation() throws Exception {
		if (organizationId == null) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		}

		hasDuplicatePopulation = handicappedService.hasDuplicateHandicapped(
				organizationId, population.getIdCardNo(), population.getId());
		return SUCCESS;
	}

	@PermissionFilter(ename = "addHandicapped")
	@Action(value = "saveHandicapped", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBusinessInfo() throws Exception {
		population = handicappedService.updateHandicappedBusiness(population);
		return SUCCESS;
	}

	@Action(value = "maintainHandicappedBaseInfo", results = {
			@Result(name = "addcache", type = "json", params = { "root",
					"cacheId", "ignoreHierarchy", "false" }),
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBaseInfo() throws Exception {
		if (null != population && null != population.getId()) {
			population = handicappedService
					.updateHandicappedBaseInfo(population);
		} else {
			population = handicappedService.addHandicappedBaseInfo(population);
		}
		return SUCCESS;
	}

	@EncryptAnnotation
	@Actions({
			@Action(value = "viewCommonPopulation", results = { @Result(name = "success", location = "/baseinfo/commonPopulation/viewCommonPopulation.jsp") }),
			@Action(value = "viewHandicapped", results = {
					@Result(name = "success", location = "/baseinfo/handicapped/viewInHandicapped.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String viewInfo() throws Exception {
		if (population != null && population.getId() != null) {
			population = handicappedService.getHandicappedById(population
					.getId());
			population.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(population
							.getOrganization().getId(), organizationDubboService));
			population
					.setDisabilityTypes(propertyDictService
							.findPropertyDictByDomainName(PropertyTypes.DISABILITY_TYPE));
			HandicappedSdisabilityType handicappedSdisabilityType = new HandicappedSdisabilityType();
			handicappedSdisabilityType.setId(population.getId());
			List<HandicappedSdisabilityType> hdtList = handicappedService
					.queryDisabilityLevelById(handicappedSdisabilityType);
			if (disablilityLevelMap == null) {
				disablilityLevelMap = new HashMap<Long, String>();
			}
			disablilityLevelMap.clear();
			for (HandicappedSdisabilityType hst : hdtList) {
				PropertyDict pd = propertyDictService.getPropertyDictById(hst
						.getHandicappedslevel());
				String displayName = "";
				if (pd != null) {
					displayName = pd.getDisplayName();
				}
				disablilityLevelMap.put(hst.getHandicappedstype(), displayName);
			}
		}
		// 拆分图片路径字符串
		if (null != population.getImgUrl()) {
			String[] value = population.getImgUrl().split(",");
			population.setImgUrl(value[0]);
		}
		return SUCCESS;
	}

	@Action(value = "getHandicappedByIdForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getHandicappedByIdForMobile() throws Exception {
		if (population != null && population.getId() != null) {
			population = handicappedService.getHandicappedById(population
					.getId());
			population.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(population
							.getOrganization().getId(), organizationDubboService));
			population
					.setDisabilityTypes(propertyDictService
							.findPropertyDictByDomainName(PropertyTypes.DISABILITY_TYPE));
			HandicappedSdisabilityType handicappedSdisabilityType = new HandicappedSdisabilityType();
			handicappedSdisabilityType.setId(population.getId());
			List<HandicappedSdisabilityType> hdtList = handicappedService
					.queryDisabilityLevelById(handicappedSdisabilityType);
			if (disablilityLevelMap == null) {
				disablilityLevelMap = new HashMap<Long, String>();
			}
			disablilityLevelMap.clear();
			for (HandicappedSdisabilityType hst : hdtList) {
				disablilityLevelMap.put(hst.getHandicappedstype(),
						hst.getHandicappedslevel() + "");
			}
			population.setDisablilityLevelMapForMobile(disablilityLevelMap);
		}
		// 拆分图片路径字符串
		if (null != population.getImgUrl()) {
			String[] value = population.getImgUrl().split(",");
			population.setImgUrl(value[0]);
		}

		return SUCCESS;
	}

	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIdList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		populationIdList = handicappedService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.HANDICAPPED_KEY, analyzePopulationIds());
		return SUCCESS;
	}

	/**
	 * ID加密 关注
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "updateEmphasiseByEncryptId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIdList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseByEncryptId() throws Exception {
		populationIdList = handicappedService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.HANDICAPPED_KEY, analyzePopulationIds());
		return SUCCESS;
	}

	@Action(value = "findHandicappedByIdCardNoAndOrgId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findHandicappedByIdCardNoAndOrgId() throws Exception {
		population = handicappedService
				.getHandicappedByIdCardNoAndOrganizationId(
						population.getIdCardNo(), organizationId);

		return SUCCESS;
	}

	private Long[] analyzePopulationIds() {
		String[] deleteId = populationIds.split(",");
		List<Long> idList;
		if (deleteId[0].equals("")) {
			idList = initTargetId(deleteId, 1);
		} else {
			idList = initTargetId(deleteId, 0);
		}
		Long[] ids = new Long[idList.size()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = idList.get(i);
		}
		return ids;
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

	@EncryptAnnotation
	@PermissionFilter(ename = "deleteHandicapped")
	@Action(value = "deleteHandicappedByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteByIds() throws Exception {
		handicappedService.deleteHandicappedByIds(analyzePopulationIds());
		return SUCCESS;
	}

	@Action(value = "updateDeathByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIds", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDeathByIds() throws Exception {
		handicappedService.updateDeathByIds(analyzePopulationIds(),
				population.isDeath());
		return SUCCESS;
	}

	@EncryptAnnotation
	@Action(value = "updateDeathByEncryptIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"populationIds", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDeathByEncryptIds() throws Exception {
		handicappedService.updateDeathByIds(analyzePopulationIds(),
				population.isDeath());
		return SUCCESS;
	}

	@Action(value = "addHandicappedForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addHandicappedForMobile() throws Exception {
		population.setDisabilityTypeIds(setHandicappedValue(population
				.getTypeIdsForMobile()));
		population.setDisabilityLevelIds(setHandicappedValue(population
				.getLevelIdsForMobile()));
		population = handicappedService.addHandicapped(population);
		return SUCCESS;
	}

	private List setHandicappedValue(String val) {
		String[] values = val.split(",");
		List<Long> typeList = new ArrayList<Long>();
		for (int i = 0; i < values.length; i++) {
			if (values[i] != null && !"".equals(values[i])) {
				typeList.add(Long.parseLong(values[i]));
			}
		}
		return typeList;
	}

	@Action(value = "updateHandicappedForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateHandicappedForMobile() throws Exception {
		population.setDisabilityTypeIds(setHandicappedValue(population
				.getTypeIdsForMobile()));
		population.setDisabilityLevelIds(setHandicappedValue(population
				.getLevelIdsForMobile()));
		population = handicappedService.updateHandicapped(population);
		return SUCCESS;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public SearchHandicappedVo getSearchHandicappedVo() {
		return searchHandicappedVo;
	}

	public void setSearchHandicappedVo(SearchHandicappedVo searchHandicappedVo) {
		this.searchHandicappedVo = searchHandicappedVo;
	}

	public Handicapped getPopulation() {
		return population;
	}

	public void setPopulation(Handicapped population) {
		this.population = population;
	}

	public List<Handicapped> getPopulationList() {
		return populationList;
	}

	public void setPopulationList(List<Handicapped> populationList) {
		this.populationList = populationList;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public Map<Long, String> getDisablilityLevelMap() {
		return disablilityLevelMap;
	}

	public void setDisablilityLevelMap(Map<Long, String> disablilityLevelMap) {
		this.disablilityLevelMap = disablilityLevelMap;
	}

	public Handicapped getHandicapped() {
		return handicapped;
	}

	public void setHandicapped(Handicapped handicapped) {
		this.handicapped = handicapped;
	}

	public String getDisabilityTypeIds() {
		return disabilityTypeIds;
	}

	public void setDisabilityTypeIds(String disabilityTypeIds) {
		this.disabilityTypeIds = disabilityTypeIds;
	}

	public String getDisabilityLevelIds() {
		return disabilityLevelIds;
	}

	public void setDisabilityLevelIds(String disabilityLevelIds) {
		this.disabilityLevelIds = disabilityLevelIds;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}
