package com.tianque.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.rentalHouse.service.RentalHouseService;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.HouseLivingPopulationInfo;
import com.tianque.domain.vo.HouseLivingTotalInfo;
import com.tianque.search.vo.ActualPopulationSearchCondition;
import com.tianque.service.HousePopulationApplyService;
import com.tianque.service.helper.ManagePopulationByHouseHelper;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Controller("actualHouseApplyController")
@Scope("prototype")
public class ActualHouseApplyController extends BaseAction {

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

	private String[][] houseInfors;

	private String bindingToHouseInfo;

	private List<HouseLivingTotalInfo> populationTotalInfos;

	private String populationType;

	private HouseInfo houseInfo;

	private String accountNumber;

	@Autowired
	protected ActualHouseService houseInfoService;

	@Autowired
	private RentalHouseService rentalHouseService;

	@Autowired
	protected OrganizationDubboService orgService;

	@Autowired
	private HousePopulationApplyService housePopulationApplyService;

	@Autowired
	private ManagePopulationByHouseHelper helper;

	private Integer gender;

	private Map<String, Integer> countPopulationInHouseMap;

	/**
	 * 查询房屋人员信息
	 * 
	 * @return
	 */
	public String findLivingInHouseTotalInfos() throws Exception {
		houseInfo = houseInfoService.getHouseInfoByHouseCodeAndOrgId(
				houseInfo.getHouseCode(), orgId);
		populationTotalInfos = housePopulationApplyService
				.findLivingHouseTotalInfos(PopulationCatalog.POPULATION,
						houseInfo.getId());
		return SUCCESS;
	}

	/**
	 * 现有住户信息
	 * 
	 * @return
	 */
	public String findLivingInHousePopulationInfosForPage() throws Exception {
		PageInfo<HouseLivingPopulationInfo> populations = new PageInfo<HouseLivingPopulationInfo>();
		populations.setCurrentPage(1);
		List<HouseLivingPopulationInfo> houseLivingPopulationInfos = housePopulationApplyService
				.findLivingHousePopulationInfos(
						PopulationCatalog.ACTUAL_POPULATION, houseId);
		populations.setResult(houseLivingPopulationInfos);
		populations.setTotalRowSize(houseLivingPopulationInfos.size());
		gridPage = new GridPage(populations);
		return SUCCESS;
	}

	@EncryptAnnotation
	public String findLivingInHousePopulationInfos() throws Exception {
		if (houseId == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			PageInfo<HouseLivingPopulationInfo> populations = housePopulationApplyService
					.findLivingInHousePopulationInfos(
							PopulationCatalog.ACTUAL_POPULATION, houseId, page,
							rows, sidx, sord);
			gridPage = new GridPage(populations);
		}
		return SUCCESS;
	}

	@EncryptAnnotation
	public String countPopulationInHouse() {
		countPopulationInHouseMap = housePopulationApplyService
				.getMapForActualPopulation(houseId, orgId);
		return SUCCESS;
	}

	private PageInfo<HouseLivingPopulationInfo> emptyPage(int pageSize) {
		PageInfo<HouseLivingPopulationInfo> pageInfo = new PageInfo<HouseLivingPopulationInfo>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<HouseLivingPopulationInfo>());
		return pageInfo;
	}

	/**
	 * 维护使用人信息
	 * 
	 * @return
	 */
	public String prepairMantanceLivingPopulation() {
		houseInfo = houseInfoService.getHouseInfoById(houseInfo.getId());
		return SUCCESS;
	}

	/**
	 * 维护使用人信息
	 * 
	 * @return
	 */
	@EncryptAnnotation
	public String prepairMantanceLivingPopulationByEncrypt() {
		houseInfo = houseInfoService.getHouseInfoById(houseInfo.getId());
		return SUCCESS;
	}

	/**
	 * 保存使用人信息
	 * 
	 * @return
	 */
	public String saveLivingPopulation() throws Exception {
		if (null == bindingToHouseInfo) {
			bindingToHouseInfo = "";
		}
		String[] bindingInfos = bindingToHouseInfo.split(",");
		PopulationCatalog[] types = new PopulationCatalog[bindingInfos.length];
		Long[] peoples = new Long[bindingInfos.length];
		for (int index = 0; index < types.length; index++) {
			String tag = bindingInfos[index];
			if (!tag.trim().equals("") && tag.indexOf("_") > 0) {
				types[index] = PopulationCatalog.parse(tag.substring(0,
						tag.lastIndexOf("_")));
				peoples[index] = Long.valueOf(tag.substring(tag
						.lastIndexOf("_") + 1));
			}
		}
		helper.bindActualPopulationHouseIgnoreDefaultLiving(types, peoples,
				houseId);
		return SUCCESS;
	}

	/**
	 * 可选住户信息
	 * 
	 * @return
	 */
	public String findNotLivingInHousePopulationInfos() throws Exception {
		ActualPopulationSearchCondition condition = constructSearchCondition();
		PageInfo<HouseLivingPopulationInfo> populations = housePopulationApplyService
				.findNotLivingInHousePopulationInfos(houseId, condition, page,
						rows, sidx, sord);
		gridPage = new GridPage(populations);
		return SUCCESS;
	}

	private ActualPopulationSearchCondition constructSearchCondition() {
		ActualPopulationSearchCondition condition = new ActualPopulationSearchCondition();
		condition.setRootOrgId(orgId);
		condition.setCertificationNumber(certificationNumber);
		condition.setName(name);
		condition.setGender(gender);
		condition.setAccountNumber(accountNumber);
		return condition;
	}

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

	public String[][] getHouseInfors() {
		return houseInfors;
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

	public void setHouseInfors(String[][] houseInfors) {
		this.houseInfors = houseInfors;
	}

	public List<HouseLivingTotalInfo> getPopulationTotalInfos() {
		return populationTotalInfos;
	}

	public void setPopulationTotalInfos(
			List<HouseLivingTotalInfo> populationTotalInfos) {
		this.populationTotalInfos = populationTotalInfos;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public HouseInfo getHouseInfo() {
		return houseInfo;
	}

	public void setHouseInfo(HouseInfo houseInfo) {
		this.houseInfo = houseInfo;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Map<String, Integer> getCountPopulationInHouseMap() {
		return countPopulationInHouseMap;
	}

	public void setCountPopulationInHouseMap(
			Map<String, Integer> countPopulationInHouseMap) {
		this.countPopulationInHouseMap = countPopulationInHouseMap;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
