package com.tianque.service.impl;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.service.ActualPopulationMutexService;
import com.tianque.service.ActualPopulationService;
import com.tianque.service.util.PopulationType;

@Service("actualPopulationMutexService")
public class ActualPopulationMutexServiceImpl implements ActualPopulationMutexService {

	@Autowired
	private Map<String, ActualPopulationService> actualPopulationServiceMap;

	@Autowired
	private GlobalSettingService globalSettingService;

	/*
	 * (non-Javadoc)
	 * @see com.tianque.service.ActualPopulationMutexService#
	 * isActualPopuationByOrgIdAndIdCardNoAndExcludeActualPopulationType(java.lang.Long,
	 * java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean isActualPopuationByOrgIdAndIdCardNoAndExcludeActualPopulationType(
			Long populationId, Long orgId, String idCardNo, String actualPopulationType) {
		for (Entry<String, ActualPopulationService> entry : actualPopulationServiceMap.entrySet()) {
			String serviceName = entry.getKey();
			ActualPopulationService service = entry.getValue();
			if (Boolean.valueOf(globalSettingService
					.getGlobalValue(GlobalSetting.ACTUAL_POPULATION_MUTEX))) {
				if (serviceName.startsWith(PopulationType.FLOATING_POPULATION)
						|| serviceName.startsWith(PopulationType.HOUSEHOLD_STAFF)) {
					ActualPopulation actualPopulation = service
							.getActualPopulationByOrgIdAndIdCardNoExcludePopulationId(populationId,
									orgId, idCardNo);
					if (null != actualPopulation) {
						if (actualPopulation.getLogOut() == 1) {
							return Boolean.FALSE;
						} else {
							return Boolean.TRUE;
						}
					}
				}
			} else {
				if (serviceName.startsWith(actualPopulationType)) {
					if (null != service.getActualPopulationByOrgIdAndIdCardNoExcludePopulationId(
							populationId, orgId, idCardNo)) {
						return Boolean.TRUE;
					}
				}
			}
		}
		return Boolean.FALSE;
	}

	/*
	 * (non-Javadoc)
	 * @see com.tianque.service.ActualPopulationMutexService#
	 * isExistByOrgIdAndIdCardNoAndExcludeActualPopulationType(java.lang.Long, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Boolean isExistByOrgIdAndIdCardNoAndExcludeActualPopulationType(Long orgId,
			String idCardNo, String actualPopulationType) {
		for (Entry<String, ActualPopulationService> entry : actualPopulationServiceMap.entrySet()) {
			String serviceName = entry.getKey();
			ActualPopulationService service = entry.getValue();
			if (!serviceName.startsWith(actualPopulationType)
					&& !serviceName.startsWith(PopulationType.OVERSEA_STAFF)) {
				if (null != service.getActualPopulationByOrgIdAndIdCardNoExcludePopulationId(null,
						orgId, idCardNo)) {
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}
}
