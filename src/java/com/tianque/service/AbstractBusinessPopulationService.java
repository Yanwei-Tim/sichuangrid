package com.tianque.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.service.helper.ManagePopulationByHouseHelper;
import com.tianque.service.impl.LogableService;
import com.tianque.service.util.PopulationCatalog;

public class AbstractBusinessPopulationService extends LogableService {
	@Autowired
	private OperateLogService operateLogService;
	@Autowired
	private PopulationTypeService populationTypeService;
	@Autowired
	private ManagePopulationByHouseHelper managePopulationByHouseHelper;

	public void deletePopulationTypeByPopulationIdAndType(Long populationId, String populationType) {
		this.populationTypeService.deletePopulationTypeByPopulationIdAndType(populationId,
				populationType);
	}

	/**
	 * 删除关联关系
	 * 
	 * @param actualId
	 * @param actualType
	 */
	public void deletePopulationTypeByActualIdAndType(Long actualId, String actualType) {
		this.populationTypeService.deletePopulationTypeByActualIdAndType(actualId, actualType);
	}

	/**
	 * 给业务人员维护人房关联关系
	 * 
	 * @param countrymen
	 */
	public void proccessHouseBind(Countrymen countrymen) {
		if (null != countrymen.getIsHaveHouse() && countrymen.getIsHaveHouse()) {
			managePopulationByHouseHelper.reBindHouseIfNecc(
					getCatalogByType(countrymen.getAttentionPopulationType()), countrymen,
					countrymen.getHouseId());
		} else {
			managePopulationByHouseHelper.releasePopulationBindedHouses(
					getCatalogByType(countrymen.getAttentionPopulationType()), countrymen.getId());
		}

	}

	public void logOperate(Long orgId, String moduleName, String operation, Integer operationType,
			String moduleType, String operateContent) {
		operateLogService.log(orgId, operation, moduleName, operationType, moduleType,
				operateContent);
	}

	private PopulationCatalog getCatalogByType(String type) {
		if (null != type) {
			return PopulationCatalog.parse(type);
		}
		return null;
	}
}
