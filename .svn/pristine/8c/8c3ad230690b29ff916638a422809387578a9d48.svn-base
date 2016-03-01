package com.tianque.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.ConstantsProduct;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.PopulationTypeBean;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.service.ActualPopulationService;
import com.tianque.service.PopulationProccessorService;
import com.tianque.service.PopulationTypeService;
import com.tianque.service.SyncActualPopulationToBusinessPopulationService;
import com.tianque.service.util.PopulationType;

@Transactional
@Service("syncActualPopulationToBusinessPopulationService")
public class SyncActualPopulationToBusinessPopulationServiceImpl implements
		SyncActualPopulationToBusinessPopulationService {

	private static Logger logger = LoggerFactory
			.getLogger(SyncActualPopulationToBusinessPopulationServiceImpl.class);

	@Autowired
	private List<PopulationProccessorService> proccessors;

	@Autowired
	private Map<String, ActualPopulationService> residentSeviceMap;

	@Autowired
	private GlobalSettingService globalSettingService;

	@Autowired
	private PopulationTypeService populationTypeService;

	@Override
	public ActualPopulation proccessPopulationSpecializedInfo(
			String[] populationType, Map<String, Object> population) {
		checkPopulationInfo(population);
		ActualPopulation actualPopulation = getActualPopulationById(
				populationType, population);
		ThreadVariable.setSourcesState(ConstantsProduct.SourcesState.SYNCHRO);
		try {
			if (Boolean
					.valueOf(globalSettingService
							.getGlobalValue(GlobalSetting.CAN_MAINTAIN_BUSINESS_POPULATION))) {
				this.populationTypeService
						.deletePopulationTypeByActualIdAndType(Long
								.valueOf(((String[]) population.get("id"))[0]),
								this.getPopulationType(populationType));
				for (PopulationProccessorService proccessor : proccessors) {
					actualPopulation.setActualPopulationType(this
							.getPopulationType(populationType));
					Long populationId = proccessor
							.proccessPopulationSpecializedInfo(
									actualPopulation, populationType,
									population);
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException("处理块状管理", e);
		}
		return actualPopulation;
	}

	@Override
	public void deletePopulationByOrgIdAndIdCardNo(Long orgId, String idCardNo) {
		if (Boolean.valueOf(globalSettingService
				.getGlobalValue(GlobalSetting.DELETE_BUSINESS_POPULATION))) {
			for (PopulationProccessorService proccessor : proccessors) {
				Map<String, Map<String, Object>> populationMap = proccessor
						.getPopulationSpecializedInfoByOrgIdAndIdCardNo(orgId,
								idCardNo);

				if (null != populationMap) {
					Iterator iter = populationMap.entrySet().iterator();
					while (iter.hasNext()) {
						Entry entry = (Entry) iter.next();
						Map<String, Object> value = (Map<String, Object>) entry
								.getValue();
						proccessor.deletePopulationByPopulationId(Long
								.valueOf(String.valueOf(value.get("id"))));
					}
					;
				}
			}
		}
	}

	private void addPopulationType(Long actualId, String actualType,
			Long populationId, String populationType) {
		PopulationTypeBean populationTypes = new PopulationTypeBean();
		populationTypes.setActualId(actualId);
		populationTypes.setActualType(actualType);
		populationTypes.setPopulationId(populationId);
		populationTypes.setPopulationType(populationType);
		if (null != actualId && null != populationId
				&& StringUtils.isNotEmpty(actualType)
				&& StringUtils.isNotEmpty(populationType)) {
			this.populationTypeService.addPopulationType(populationTypes);
		}
	}

	private ActualPopulation getActualPopulationById(String[] populationType,
			Map<String, Object> population) {
		String serviceName = this.getPopulationType(populationType) + "Service";
		ActualPopulationService actualPopulationService = residentSeviceMap
				.get(serviceName);
		return actualPopulationService.getActualPopulationById(Long
				.valueOf(((String[]) population.get("id"))[0]));
	}

	private String getPopulationType(String[] populationType) {
		String serviceName = null;
		if (com.tianque.util.Arrays.hasObjectInArray(populationType,
				PopulationType.FLOATING_POPULATION)) {
			serviceName = PopulationType.FLOATING_POPULATION;
		}
		if (com.tianque.util.Arrays.hasObjectInArray(populationType,
				PopulationType.HOUSEHOLD_STAFF)) {
			serviceName = PopulationType.HOUSEHOLD_STAFF;
		}
		return serviceName;
	}

	private void checkPopulationInfo(Map<String, Object> population) {
		if (null == population.get("organization.id")
				|| "".equals(((String[]) population.get("organization.id"))[0])) {
			throw new BusinessValidationException("orgId不能为空");
		}
		if (null == population.get("idCardNo")
				|| "".equals(((String[]) population.get("idCardNo"))[0])) {
			throw new BusinessValidationException("idCardNo不能为空");
		}
	}

	@Override
	public Map<String, Map<String, Object>> getPopulationSpecializedInfoByOrgIdAndIdCardNo(
			Long actualId, Long orgId, String idCardNo, String[] populationType) {
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
		if (Boolean
				.valueOf(globalSettingService
						.getGlobalValue(GlobalSetting.CAN_MAINTAIN_BUSINESS_POPULATION))) {
			for (PopulationProccessorService proccessor : proccessors) {
				List<PopulationTypeBean> populationTypeList = this.populationTypeService
						.getPopulationTypeByActualIdAndType(actualId,
								this.getPopulationType(populationType));
				for (PopulationTypeBean populationTypeBean : populationTypeList) {
					if (proccessor
							.getClass()
							.getSimpleName()
							.toUpperCase()
							.startsWith(
									populationTypeBean.getPopulationType()
											.toUpperCase())) {
						Map<String, Map<String, Object>> proccessorResult = proccessor
								.getPopulationSpecializedInfoByOrgIdAndIdCardNo(
										orgId, idCardNo);
						if (null == proccessorResult
								|| proccessorResult.size() == 0) {
							continue;
						}
						map.putAll(proccessorResult);
					}
				}
			}
		}
		return map;
	}

	@Override
	public Map<String, Map<String, Object>> getAllPopulationSpecializedInfoByOrgIdAndIdCardNo(
			Long orgId, String idCardNo, String populationSpecializedType) {
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
		if (Boolean
				.valueOf(globalSettingService
						.getGlobalValue(GlobalSetting.CAN_MAINTAIN_BUSINESS_POPULATION))) {
			for (PopulationProccessorService proccessor : proccessors) {
				if (proccessor.getClass().getSimpleName().toUpperCase()
						.startsWith(populationSpecializedType.toUpperCase())) {
					Map<String, Map<String, Object>> proccessorResult = proccessor
							.getPopulationSpecializedInfoByOrgIdAndIdCardNo(
									orgId, idCardNo);
					if (null == proccessorResult
							|| proccessorResult.size() == 0) {
						continue;
					}
					map.putAll(proccessorResult);
				}
			}
		}
		return map;
	}

	@Override
	public void updatePopulationBaseInfo(ActualPopulation actualPopulation) {
		String canMaintainBusinessPopulation = globalSettingService
				.getGlobalValue(GlobalSetting.CAN_MAINTAIN_BUSINESS_POPULATION);
		if (null != canMaintainBusinessPopulation
				&& Boolean.valueOf(canMaintainBusinessPopulation)) {
			for (PopulationProccessorService proccessor : proccessors) {
				proccessor.updatePopulationBaseInfo(actualPopulation);
			}
		}
	}
}
