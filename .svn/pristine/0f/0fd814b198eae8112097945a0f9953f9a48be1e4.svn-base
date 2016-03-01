package com.tianque.service.impl;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.base.domain.AttentionPopulation;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.domain.PopulationTypeBean;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.ActualBaseInfoSyncService;
import com.tianque.service.ActualPopulationService;
import com.tianque.service.PopulationTypeService;
import com.tianque.service.SyncActualPopulationToBusinessPopulationService;
import com.tianque.service.SyncPopulationToActualService;
import com.tianque.service.vo.IsEmphasis;
import com.tianque.util.PropertyUtil;

@Service("syncPopulationToActualService")
@Transactional
public class SyncPopulationToActualImpl implements
		SyncPopulationToActualService {

	public final static Logger logger = LoggerFactory
			.getLogger(SyncPopulationToActualImpl.class);

	@Autowired
	private SyncActualPopulationToBusinessPopulationService populationSpecializedInfoService;
	@Autowired
	private Map<String, ActualBaseInfoSyncService> actualBaseInfoSyncServiceMap;
	@Autowired
	private Map<String, ActualPopulationService> actualPopulationServiceMap;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private PopulationTypeService populationTypeService;

	@Override
	public void maintainPopulationTypeRela(Long populationId,
			String populationType, Long orgId, String idCardNo) {
		reBuildPopulationRela(populationId, populationType, orgId, idCardNo);
	}

	@Override
	public void reBuildPopulationRela(Long populationId, String populationType,
			Long orgId, String idCardNo) {
		for (Entry<String, ActualPopulationService> entry : actualPopulationServiceMap
				.entrySet()) {
			String serviceName = entry.getKey();
			ActualPopulationService service = entry.getValue();
			ActualPopulation actualPopulation = service
					.getActualPopulationByOrgIdAndIdCardNoExcludePopulationId(
							null, orgId, idCardNo);
			if (null != actualPopulation
					&& IsEmphasis.Emphasis.longValue() == actualPopulation
							.getLogOut()) {
				this.reAddPopulationTypeRela(
						actualPopulation.getId(),
						serviceName.substring(0, serviceName.indexOf("Service")),
						populationId, populationType);
			}
		}
	}

	private void reAddPopulationTypeRela(Long actualId, String actualType,
			Long populationId, String populationType) {
		populationTypeService
				.deletePopulationTypeByActualIdAndTypeAndPopulationType(
						actualId, actualType, populationType);
		addPopulationType(actualId, actualType, populationId, populationType);
	}

	private void addPopulationType(Long actualId, String actualType,
			Long populationId, String populationType) {
		PopulationTypeBean populationTypeBean = new PopulationTypeBean();
		populationTypeBean.setActualId(actualId);
		populationTypeBean.setActualType(actualType);
		populationTypeBean.setPopulationId(populationId);
		populationTypeBean.setPopulationType(populationType);
		populationTypeService.addPopulationType(populationTypeBean);
	}

	@Override
	public String syncActualPopulationBaseInfo(AttentionPopulation population) {
		String actualPopulationType = null;
		try {
			if (GlobalSetting.SYNC_ACTUAL_POPULATION
					.equals(globalSettingService
							.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
				actualPopulationType = population.getActualPopulationType();
				ActualBaseInfoSyncService service = getServiceByName(population
						.getActualPopulationType());
				ActualPopulation actualPopulation = service
						.getFullActualPopulationByCardNoAndOrgId(population
								.getIdCardNo(), population.getOrganization()
								.getId());
				PropertyUtil.copyPropertiesWithRecursion(Countrymen.class,
						actualPopulation, population, new String[] { "id" });
				service.asynActualPopulation(actualPopulation);
				populationSpecializedInfoService
						.updatePopulationBaseInfo(actualPopulation);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessValidationException(e.getMessage());
		}
		return actualPopulationType;
	}

	private ActualBaseInfoSyncService getServiceByName(String name) {
		ActualBaseInfoSyncService service = null;
		for (Entry<String, ActualBaseInfoSyncService> entry : actualBaseInfoSyncServiceMap
				.entrySet()) {
			if (entry.getKey().startsWith(name)) {
				service = entry.getValue();
				break;
			}
		}
		return service;
	}
}
