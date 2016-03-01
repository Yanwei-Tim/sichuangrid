package com.tianque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.HouseInfoType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.HouseLivingPopulationInfo;
import com.tianque.domain.vo.HouseLivingTotalInfo;
import com.tianque.search.result.HouseSimpleInfo;
import com.tianque.search.vo.ActualPopulationSearchCondition;
import com.tianque.search.vo.SearchHouseByAddressLibCondition;
import com.tianque.service.HouseInfoService;
import com.tianque.service.HousePopulationApplyService;
import com.tianque.service.helper.ManagePopulationByHouseHelper;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Controller("houseApplyController")
@Scope("prototype")
public class HouseApplyController extends BaseAction {

	private Long houseId;
	private Long orgId;
	private String certificationNumber;
	private String name;
	private String searchCondition;
	private boolean searchByAddress = false;
	private String searchType;
	private String community;
	private String block;
	private String unit;
	private String bindingToHouseInfo;
	private List<HouseLivingTotalInfo> populationTotalInfos;
	private String populationType;

	@Autowired
	protected HouseInfoService houseInfoService;
	@Autowired
	protected OrganizationDubboService orgService;
	@Autowired
	private HousePopulationApplyService housePopulationApplyService;
	@Autowired
	private ManagePopulationByHouseHelper helper;
	@Autowired
	private PropertyDictService propertyDictService;

	private List<HouseSimpleInfo> houseSimpleInfoList;

	public String getCertificationNumber() {
		return certificationNumber;
	}

	public void setCertificationNumber(String certificationNumber) {
		this.certificationNumber = certificationNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBindingToHouseInfo() {
		return bindingToHouseInfo;
	}

	public void setBindingToHouseInfo(String bindingToHouseInfo) {
		this.bindingToHouseInfo = bindingToHouseInfo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public boolean isSearchByAddress() {
		return searchByAddress;
	}

	public void setSearchByAddress(boolean searchByAddress) {
		this.searchByAddress = searchByAddress;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<HouseSimpleInfo> getHouseSimpleInfoList() {
		return houseSimpleInfoList;
	}

	public void setHouseSimpleInfoList(List<HouseSimpleInfo> houseSimpleInfoList) {
		this.houseSimpleInfoList = houseSimpleInfoList;
	}

	public List<HouseLivingTotalInfo> getPopulationTotalInfos() {
		return populationTotalInfos;
	}

	public void setPopulationTotalInfos(List<HouseLivingTotalInfo> populationTotalInfos) {
		this.populationTotalInfos = populationTotalInfos;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public String findHousesByAddressLib() {
		SearchHouseByAddressLibCondition condition = constructHouseSearchCondition();
		if (!isEmptyCondition(condition)) {
			List propertyDicts = propertyDictService.findPropertyDictByDomainNameAndInternalId(
					PropertyTypes.HOUSEING_INFO_TYPE, HouseInfoType.ACTUAL_HOUSE);
			if (!propertyDicts.isEmpty()) {
				condition.setHouseType(((PropertyDict) propertyDicts.get(0)).getId());
			}
			houseSimpleInfoList = houseInfoService.findHousesForAutoComplete(condition);
		}
		return SUCCESS;
	}

	public String findLivingInHouseTotalInfos() {
		populationTotalInfos = housePopulationApplyService.findLivingHouseTotalInfos(
				PopulationCatalog.POPULATION, houseId);
		return SUCCESS;
	}

	public String findLivingInHousePopulationInfos() {
		PageInfo<HouseLivingPopulationInfo> populations = new PageInfo<HouseLivingPopulationInfo>();
		populations.setCurrentPage(1);
		populations.setResult(housePopulationApplyService.findLivingHousePopulationInfos(
				PopulationCatalog.POPULATION, houseId));
		gridPage = new GridPage(populations);
		return SUCCESS;
	}

	public String findSingleHousesIdByAddressLib() {
		SearchHouseByAddressLibCondition condition = constructHouseSearchCondition();
		if (isEmptyCondition(condition)) {
			houseId = null;
		} else {
			List<HouseSimpleInfo> houses = houseInfoService.findHousesForAutoComplete(condition);
			if (houses.size() == 0) {
				houseId = null;
			} else {
				HouseSimpleInfo house = houses.get(0);
				houseId = "".equals(house.getHouseId()) ? null : Long.valueOf(house.getHouseId());
			}
		}
		return SUCCESS;
	}

	public String prepairMantanceLivingPopulation() {
		return SUCCESS;
	}

	public String saveLivingPopulation() {
		if (bindingToHouseInfo == null) {
			bindingToHouseInfo = "";
		}
		String[] bindingInfos = bindingToHouseInfo.split(",");
		PopulationCatalog[] types = new PopulationCatalog[bindingInfos.length];
		Long[] peoples = new Long[bindingInfos.length];
		for (int index = 0; index < types.length; index++) {
			String tag = bindingInfos[index];
			if (!tag.trim().equals("") && tag.indexOf("_") > 0) {
				types[index] = PopulationCatalog.parse(tag.substring(0, tag.lastIndexOf("_")));
				peoples[index] = Long.valueOf(tag.substring(tag.lastIndexOf("_") + 1));
			}
		}
		helper.bindActualPopulationHouseIgnoreDefaultLiving(types, peoples, houseId);
		return SUCCESS;
	}

	public String findNotLivingInHousePopulationInfos() {
		ActualPopulationSearchCondition condition = constructSearchCondition();
		PageInfo<HouseLivingPopulationInfo> populations = housePopulationApplyService
				.findNotLivingInHousePopulationInfos(houseId, condition, page, rows, sidx, sord);
		gridPage = new GridPage(populations);
		return SUCCESS;
	}

	private ActualPopulationSearchCondition constructSearchCondition() {
		ActualPopulationSearchCondition condition = new ActualPopulationSearchCondition();
		condition.setRootOrgId(orgId);
		condition.setCertificationNumber(certificationNumber);
		condition.setName(name);
		return condition;
	}

	private boolean isEmptyCondition(SearchHouseByAddressLibCondition condition) {
		return StringUtil.isStringAvaliable(condition.getAddress())
				&& StringUtil.isStringAvaliable(condition.getCommunity())
				&& StringUtil.isStringAvaliable(condition.getBlock())
				&& StringUtil.isStringAvaliable(condition.getUnit())
				&& StringUtil.isStringAvaliable(condition.getRoom());
	}

	private SearchHouseByAddressLibCondition constructHouseSearchCondition() {
		SearchHouseByAddressLibCondition result = new SearchHouseByAddressLibCondition();
		result.setRows(getRows());
		if (orgId != null) {
			Organization org = orgService.getSimpleOrgById(orgId);
			result.setOrgInternalCode(org == null ? "" : org.getOrgInternalCode());
		}
		if (searchByAddress) {
			result.setSearchType(SearchHouseByAddressLibCondition.BY_HOUSE_ADDRESS);
			result.setAddress(searchCondition);
		} else {
			result.setAddress("");
			result.setSearchType(searchType);
			if (SearchHouseByAddressLibCondition.BY_HOUSE_CODE.equalsIgnoreCase(searchType)) {
				result.setHouseCode(searchCondition);
			} else if (SearchHouseByAddressLibCondition.BY_COMMUNITY.equalsIgnoreCase(searchType)) {
				result.setCommunity(searchCondition);
			} else if (SearchHouseByAddressLibCondition.BY_COMMUNITY_BLOCK
					.equalsIgnoreCase(searchType)) {
				result.setCommunity(community);
				result.setBlock(searchCondition);
			} else if (SearchHouseByAddressLibCondition.BY_COMMUNITY_BLOCK_UNIT
					.equalsIgnoreCase(searchType)) {
				result.setCommunity(community);
				result.setBlock(block);
				result.setUnit(searchCondition);
			} else if (SearchHouseByAddressLibCondition.BY_COMMUNITY_BLOCK_UNIT_ROOM
					.equalsIgnoreCase(searchType)) {
				result.setCommunity(community);
				result.setBlock(block);
				result.setUnit(unit);
				result.setRoom(searchCondition);
			}
		}
		return result;
	}

}
