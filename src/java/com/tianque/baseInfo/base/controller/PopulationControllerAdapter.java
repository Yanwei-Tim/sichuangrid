package com.tianque.baseInfo.base.controller;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.context.service.BaseInfoContextService;
import com.tianque.controller.BaseInfoListPageController;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.MaintainController;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.domain.Organization;
import com.tianque.exception.base.OperationFailedException;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.service.HouseInfoService;
import com.tianque.service.helper.ManagePopulationByHouseHelper;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.sysadmin.service.OrganizationDubboService;

public abstract class PopulationControllerAdapter<T extends Countrymen> extends
		SearchBaseAction implements BaseInfoListPageController,
		MaintainController {
	protected Class<T> entityClass;

	public PopulationControllerAdapter() {
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();
			this.entityClass = (Class<T>) parameterizedType[0];
		}
	}

	@Autowired
	protected BaseInfoContextService baseInfoContextService;
	@Autowired
	protected ActualPopulationProcessorService actualPopulationProcessorService;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private ManagePopulationByHouseHelper managePopulationByHouseHelper;

	@Autowired
	private HouseInfoService houseInfoService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	protected Long organizationId;

	protected String populationIds;

	protected Boolean hasDuplicatePopulation;

	protected String populationType;

	protected List<T> populations;

	protected List<Long> populationIdList;

	protected String superviceRecordName;

	public String getPopulationType() {
		String[] clss = this.getClass().getName().split("\\.");
		setPopulationType(clss[clss.length - 1]
				.substring(0, clss[clss.length - 1].length() - 10)
				.substring(0, 1).toLowerCase()
				+ clss[clss.length - 1].substring(0,
						clss[clss.length - 1].length() - 10).substring(1));
		return populationType;
	}

	protected T dispathBaseInfo(T population) {
		if (null != id) {
			population = getPopulationFetchOrgById(id);
			// 拆分图片路径字符串
			if (null != population.getImgUrl()) {
				String[] value = population.getImgUrl().split(",");
				if (value.length > 1) {
					population.setImgUrl(value[0]);
				}
			}
			loadAddressInfo(population);
		} else if (null != population && null != population.getId()) {
			population = getPopulationFetchOrgById(population.getId());
			loadAddressInfo(population);
		} else {
			try {
				population = entityClass.newInstance();
			} catch (Exception e) {
				throw new OperationFailedException("创建对象异常", e);
			}
			population.setOrganization(new Organization());
			population.getOrganization().setId(organizationId);
			population.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							organizationId, organizationDubboService));
		}
		return population;

	}

	protected T getPopulationFetchOrgById(Long id) {
		return null;
	}

	protected void loadAddressInfo(Countrymen population) {
		if (!GlobalSetting.NOT_ADD_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))
				&& !GlobalSetting.SYNC_ACTUAL_POPULATION
						.equals(globalSettingService
								.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			return;
		}

		ActualPopulation actualPopulation = actualPopulationProcessorService
				.getActualPopulationbyOrgIdAndIdCardNo(population
						.getOrganization().getId(), population.getIdCardNo());
		if (actualPopulation != null) {
			population.setActualPopulationType(actualPopulation
					.getActualPopulationType());
			population.setHouseId(actualPopulation.getHouseId());
		}

		if (null != population.getIsHaveHouse()
				&& population.getIsHaveHouse() == true) {
			Long houseId;
			if (null != population.getHouseId()) {
				houseId = population.getHouseId();
			} else {
				houseId = managePopulationByHouseHelper
						.getPopulationLivingHouseId(
								PopulationCatalog.parse(population
										.getAttentionPopulationType()),
								population.getId());

			}
			if(houseId == null){
				return;
			}
			HouseInfo house = houseInfoService.getSimpleHouseInfoById(houseId);
			if (house != null) {
				population.setIsHaveHouse(population.getIsHaveHouse());
				population.setCurrentAddressType(house.getAddressType());
				population.setCommunity(house.getCommunity());
				population.setRoom(house.getRoom());
				population.setUnit(house.getUnit());
				population.setBlock(house.getBlock());
				population.setCurrentAddress(house.getAddress());
				population.setHouseCode(house.getHouseCode());
				population.setHouseId(house.getId());
			}
		}

	}

	protected void putCacheIdOrPopulationIdInContext(Countrymen population) {
		ActualPopulation actualPopulation = actualPopulationProcessorService
				.getActualPopulationbyOrgIdAndIdCardNo(population
						.getOrganization().getId(), population.getIdCardNo());
		if (null == actualPopulation) {
			baseInfoContextService.putActualPopulationCacheIdToContext(
					contextId, cacheId.get("id"));
		} else {
			baseInfoContextService.putActualPopulationIdToContext(contextId,
					actualPopulation.getId());
		}

	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public String getPopulationIds() {
		return populationIds;
	}

	public Boolean getHasDuplicatePopulation() {
		return hasDuplicatePopulation;
	}

	public List<T> getPopulations() {
		return populations;
	}

	public List<Long> getPopulationIdList() {
		return populationIdList;
	}

	public String getSuperviceRecordName() {
		return superviceRecordName;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public void setPopulationIds(String populationIds) {
		this.populationIds = populationIds;
	}

	public void setHasDuplicatePopulation(Boolean hasDuplicatePopulation) {
		this.hasDuplicatePopulation = hasDuplicatePopulation;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public void setPopulations(List<T> populations) {
		this.populations = populations;
	}

	public void setPopulationIdList(List<Long> populationIdList) {
		this.populationIdList = populationIdList;
	}

	public void setSuperviceRecordName(String superviceRecordName) {
		this.superviceRecordName = superviceRecordName;
	}
}
