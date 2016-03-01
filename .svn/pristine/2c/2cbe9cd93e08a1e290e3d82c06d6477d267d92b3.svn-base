package com.tianque.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.service.BaseInfoService;
import com.tianque.baseInfo.floatingPopulation.service.FloatingPopulationService;
import com.tianque.baseInfo.householdStaff.service.HouseholdStaffService;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.openLayersMap.util.EmphasisType;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.service.ActualPopulationService;
import com.tianque.service.helper.ManagePopulationByHouseHelper;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;
import com.tianque.service.vo.IsEmphasis;
import com.tianque.sysadmin.service.PropertyDictService;

@Transactional
@Service("actualPopulationProcessorService")
public class ActualPopulationProcessorServiceImpl implements
		ActualPopulationProcessorService {

	@Autowired
	private Map<String, ActualPopulationService> actualPopulationServiceMap;

	@Autowired
	private ManagePopulationByHouseHelper managePopulationByHouseHelper;

	@Autowired
	private GlobalSettingService globalSettingService;

	@Autowired
	private PropertyDictService propertyDictService;

	@Autowired
	private BaseInfoService baseInfoSerivce;
	@Autowired
	private HouseholdStaffService householdStaffService;
	@Autowired
	private FloatingPopulationService floatingPopulationService;

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tianque.service.ActualPopulationProcessorService#
	 * getActualPopulationbyOrgIdAndIdCardNo(java.lang.Long, java.lang.String)
	 */
	@Override
	public ActualPopulation getActualPopulationbyOrgIdAndIdCardNo(Long orgId,
			String idCardNo) {
		ActualPopulation actualPopulation = null;
		Countrymen countrymen = baseInfoSerivce.existBaseInfo(idCardNo);
		if (countrymen == null) {
			return actualPopulation;
		}
		for (Entry<String, ActualPopulationService> entry : actualPopulationServiceMap
				.entrySet()) {
			String serviceName = entry.getKey();
			ActualPopulationService service = entry.getValue();
			if (GlobalSetting.NOT_ADD_POPULATION
					.equals(globalSettingService
							.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))
					|| GlobalSetting.SYNC_ACTUAL_POPULATION
							.equals(globalSettingService
									.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
				if (serviceName.startsWith(PopulationType.FLOATING_POPULATION)
						|| serviceName
								.startsWith(PopulationType.HOUSEHOLD_STAFF)) {
					if (serviceName.startsWith(PopulationType.HOUSEHOLD_STAFF)) {
						actualPopulation = householdStaffService
								.getHouseholdStaffByBaseInfoId(
										countrymen.getId(), orgId);
					} else if (serviceName
							.startsWith(PopulationType.FLOATING_POPULATION)) {
						actualPopulation = floatingPopulationService
								.getFloatingPopulationByBaseInfoId(
										countrymen.getId(), orgId);
					}
					if (null != actualPopulation
							&& (actualPopulation.getLogOut() == null || EmphasisType.Emphasis
									.equals((actualPopulation.getLogOut())))) {
						actualPopulation = service
								.getActualPopulationHouseId(actualPopulation);
						actualPopulation.setActualPopulationType(serviceName
								.substring(0, serviceName.indexOf("Service")));
						managePopulationByHouseHelper.loadLivingHouseIfNecc(
								PopulationCatalog.parse(actualPopulation
										.getActualPopulationType()),
								actualPopulation);
						if (null != actualPopulation
								&& null != actualPopulation.getGender()
								&& null != actualPopulation.getGender().getId())
							actualPopulation.setGender(propertyDictService
									.getPropertyDictById(actualPopulation
											.getGender().getId()));
						return actualPopulation;
					}
				}
			}
		}
		return actualPopulation;
	}

	@Override
	public ActualPopulation getActualPopulationbyOrgIdAndIdCardNoIncludeLogout(
			Long orgId, String idCardNo) {
		ActualPopulation actualPopulation = null;
		Countrymen countrymen = baseInfoSerivce.existBaseInfo(idCardNo);
		if (countrymen == null) {
			return actualPopulation;
		}
		for (Entry<String, ActualPopulationService> entry : actualPopulationServiceMap
				.entrySet()) {
			String serviceName = entry.getKey();
			ActualPopulationService service = entry.getValue();
			if (GlobalSetting.NOT_ADD_POPULATION
					.equals(globalSettingService
							.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))
					|| GlobalSetting.SYNC_ACTUAL_POPULATION
							.equals(globalSettingService
									.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
				if (serviceName.startsWith(PopulationType.FLOATING_POPULATION)
						|| serviceName
								.startsWith(PopulationType.HOUSEHOLD_STAFF)) {
					if (serviceName.startsWith(PopulationType.HOUSEHOLD_STAFF)) {
						actualPopulation = householdStaffService
								.getHouseholdStaffByBaseInfoId(
										countrymen.getId(), orgId);
					} else if (serviceName
							.startsWith(PopulationType.FLOATING_POPULATION)) {
						actualPopulation = floatingPopulationService
								.getFloatingPopulationByBaseInfoId(
										countrymen.getId(), orgId);
					}
					if (null != actualPopulation) {
						actualPopulation = service
								.getActualPopulationHouseId(actualPopulation);
						actualPopulation.setActualPopulationType(serviceName
								.substring(0, serviceName.indexOf("Service")));
						managePopulationByHouseHelper.loadLivingHouseIfNecc(
								PopulationCatalog.parse(actualPopulation
										.getActualPopulationType()),
								actualPopulation);
						if (null != actualPopulation
								&& null != actualPopulation.getGender()
								&& null != actualPopulation.getGender().getId())
							actualPopulation.setGender(propertyDictService
									.getPropertyDictById(actualPopulation
											.getGender().getId()));
						return actualPopulation;
					}
				}
			}
		}
		return actualPopulation;
	}

	@Override
	public String getActualPopulationbyOrgIdAndIdCardNoExistedMessage(
			Long orgId, String idCardNo, String populationType,
			Long populationId) {
		ActualPopulation actualPopulation = null;
		StringBuilder existedIdCardNoMessage = new StringBuilder();
		Countrymen countrymen = baseInfoSerivce.existBaseInfo(idCardNo);
		if (countrymen == null) {
			return existedIdCardNoMessage.toString();
		}
		for (Entry<String, ActualPopulationService> entry : actualPopulationServiceMap
				.entrySet()) {
			String serviceName = entry.getKey();
			ActualPopulationService service = entry.getValue();
			if (serviceName.startsWith(PopulationType.FLOATING_POPULATION)
					|| serviceName.startsWith(PopulationType.HOUSEHOLD_STAFF)) {
				if (serviceName.startsWith(PopulationType.HOUSEHOLD_STAFF)) {
					actualPopulation = householdStaffService
							.getHouseholdStaffByBaseInfoId(countrymen.getId(),
									orgId);
				} else if (serviceName
						.startsWith(PopulationType.FLOATING_POPULATION)) {
					actualPopulation = floatingPopulationService
							.getFloatingPopulationByBaseInfoId(
									countrymen.getId(), orgId);
				}
				if (null != populationType
						&& serviceName.startsWith(populationType)
						&& null != actualPopulation
						&& actualPopulation.getId().equals(populationId)) {
					actualPopulation = null;
				}
				if (null != actualPopulation) {
					if (existedIdCardNoMessage.length() <= 0) {
						existedIdCardNoMessage.append("该身份证号在此网格已被新增为");
					}
					actualPopulation.setActualPopulationType(serviceName
							.substring(0, serviceName.indexOf("Service")));
					if (actualPopulation.getLogOut() == null
							|| EmphasisType.Emphasis.equals((actualPopulation
									.getLogOut()))) {
						existedIdCardNoMessage.append(BaseInfoTables
								.getTypeDisplayNames(actualPopulation
										.getActualPopulationType())
								+ "    ");
					} else {
						existedIdCardNoMessage.append(BaseInfoTables
								.getTypeDisplayNames(actualPopulation
										.getActualPopulationType())
								+ "（已注销）    ");
					}
				}
			}
			if (PopulationType.FLOATING_POPULATION.equals(populationType)) {
				if (serviceName.startsWith(PopulationType.UNSETTLED_POPULATION)) {
					actualPopulation = service
							.getActualPopulationByOrgIdAndIdCardNoExcludePopulationIdIncludeLogout(
									null, orgId, idCardNo);
					if (null != actualPopulation) {
						if (existedIdCardNoMessage.length() <= 0) {
							existedIdCardNoMessage.append("该身份证号在此网格已被新增为");
						}
						if (actualPopulation.getLogOut() == null
								|| EmphasisType.Emphasis
										.equals((actualPopulation.getLogOut()))) {
							existedIdCardNoMessage.append(BaseInfoTables
									.getTypeDisplayNames(actualPopulation
											.getActualPopulationType()));
						} else {
							existedIdCardNoMessage.append(BaseInfoTables
									.getTypeDisplayNames(actualPopulation
											.getActualPopulationType())
									+ "（已注销）");
						}
					}
				}
			}
		}
		return existedIdCardNoMessage.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tianque.service.ActualPopulationProcessorService#
	 * getActualPopulationbyOrgIdAndIdCardNoList(java.lang.Long,
	 * java.lang.String)
	 */
	@Override
	public List<ActualPopulation> getActualPopulationbyOrgIdAndIdCardNoList(
			Long orgId, String idCardNo) {
		List<ActualPopulation> actualPopulationList = new ArrayList<ActualPopulation>();
		for (Entry<String, ActualPopulationService> entry : actualPopulationServiceMap
				.entrySet()) {
			String serviceName = entry.getKey();
			ActualPopulationService service = entry.getValue();
			if (GlobalSetting.NOT_ADD_POPULATION
					.equals(globalSettingService
							.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))
					|| GlobalSetting.SYNC_ACTUAL_POPULATION
							.equals(globalSettingService
									.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
				if (serviceName.startsWith(PopulationType.FLOATING_POPULATION)
						|| serviceName
								.startsWith(PopulationType.HOUSEHOLD_STAFF)) {
					List<ActualPopulation> actualPopulationLis = service
							.getActualPopulationByOrgIdAndIdCardNoExcludePopulationIdList(
									null, orgId, idCardNo);
					if (!actualPopulationLis.isEmpty()) {
						actualPopulationList.addAll(actualPopulationLis);
					}
				}
			}
		}
		for (ActualPopulation actualPopulation : actualPopulationList) {
			managePopulationByHouseHelper.loadLivingHouseIfNecc(
					PopulationCatalog.parse(actualPopulation
							.getActualPopulationType()), actualPopulation);
		}
		return actualPopulationList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tianque.service.ActualPopulationProcessorService#
	 * isActualPopulationDeathOrEmphasisByIdCardNoAndOrgId(java.lang.Long,
	 * java.lang.String)
	 */
	@Override
	public boolean isActualPopulationDeathOrEmphasisByIdCardNoAndOrgId(
			Long orgId, String idCardNo) {
		if (Boolean
				.valueOf(globalSettingService
						.getGlobalValue(GlobalSetting.EMPHASIS_DEPENDENT_POPULATION_STATE))) {
			for (Entry<String, ActualPopulationService> entry : actualPopulationServiceMap
					.entrySet()) {
				String serviceName = entry.getKey();
				ActualPopulationService service = entry.getValue();
				if (serviceName.startsWith(PopulationType.FLOATING_POPULATION)
						|| serviceName
								.startsWith(PopulationType.HOUSEHOLD_STAFF)) {
					ActualPopulation actualPopulation = service
							.getActualPopulationByOrgIdAndIdCardNoExcludePopulationId(
									null, orgId, idCardNo);
					if (null != actualPopulation
							&& !actualPopulation.isDeath()
							&& (IsEmphasis.Emphasis.longValue() == actualPopulation
									.getLogOut())) {
						return Boolean.TRUE;
					}
				}
			}
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tianque.service.ActualPopulationProcessorService#
	 * getActualPopulationByOrgIdAndIdCardNoForList(java.lang.Long,
	 * java.lang.String)
	 */
	@Override
	public List<ActualPopulation> getActualPopulationByOrgIdAndIdCardNoForList(
			Long orgId, String idCardNo) {
		List<ActualPopulation> actualPopulationList = new ArrayList<ActualPopulation>();
		for (Entry<String, ActualPopulationService> entry : actualPopulationServiceMap
				.entrySet()) {
			String serviceName = entry.getKey();
			ActualPopulationService service = entry.getValue();
			if (serviceName.startsWith(PopulationType.FLOATING_POPULATION)
					|| serviceName.startsWith(PopulationType.HOUSEHOLD_STAFF)) {
				ActualPopulation actualPopulation = service
						.getActualPopulationByOrgIdAndIdCardNoExcludePopulationId(
								null, orgId, idCardNo);
				if (null != actualPopulation
						&& (actualPopulation.isDeath() || (IsEmphasis.IsNotEmphasis
								.longValue() == actualPopulation.getLogOut()))) {
					actualPopulation.setActualPopulationType(serviceName
							.substring(0, serviceName.indexOf("Service")));
					actualPopulationList.add(actualPopulation);
				}
			}
		}
		return actualPopulationList;
	}

	@Override
	public ActualPopulation getActualPopulationIncludeUnsettledPopulationByOrgIdAndIdCardNo(
			Long orgId, String idCardNo) {
		ActualPopulation actualPopulation = null;
		for (Entry<String, ActualPopulationService> entry : actualPopulationServiceMap
				.entrySet()) {
			String serviceName = entry.getKey();
			ActualPopulationService service = entry.getValue();
			if (GlobalSetting.NOT_DEPENDENT
					.equals(globalSettingService
							.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))
					|| GlobalSetting.NOT_ADD_POPULATION
							.equals(globalSettingService
									.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))
					|| GlobalSetting.SYNC_ACTUAL_POPULATION
							.equals(globalSettingService
									.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
				if (serviceName.startsWith(PopulationType.FLOATING_POPULATION)
						|| serviceName
								.startsWith(PopulationType.HOUSEHOLD_STAFF)
						|| serviceName
								.startsWith(PopulationType.UNSETTLED_POPULATION)) {
					actualPopulation = service
							.getActualPopulationByOrgIdAndIdCardNoExcludePopulationId(
									null, orgId, idCardNo);
					if (null != actualPopulation) {
						actualPopulation = service
								.getActualPopulationHouseId(actualPopulation);
						actualPopulation.setActualPopulationType(serviceName
								.substring(0, serviceName.indexOf("Service")));
						managePopulationByHouseHelper.loadLivingHouseIfNecc(
								PopulationCatalog.parse(actualPopulation
										.getActualPopulationType()),
								actualPopulation);
						if (null != actualPopulation
								&& null != actualPopulation.getGender()
								&& null != actualPopulation.getGender().getId())
							actualPopulation.setGender(propertyDictService
									.getPropertyDictById(actualPopulation
											.getGender().getId()));
						return actualPopulation;
					}
				}
			}
		}
		return actualPopulation;
	}

}
