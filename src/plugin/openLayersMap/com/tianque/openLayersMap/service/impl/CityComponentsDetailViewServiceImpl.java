package com.tianque.openLayersMap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.domain.PropertyDict;
import com.tianque.openLayersMap.dao.CityComponentsTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.CityComponentsInfoVo;

@Service("cityComponentsMapDetailViewService")
public class CityComponentsDetailViewServiceImpl extends
		AbstractDetailViewService<CityComponentsInfoVo> {

	@Autowired
	private CityComponentsTwoDimensionMapDao cityComponentsTwoDimensionMapDao;

	@Override
	protected CityComponentsInfoVo doGetViewPopupInfoByIdAndTableNameAndType(
			Long id, String tableName, String type, Long orgId) {
		CityComponentsInfoVo cityComponentsInfoVo = cityComponentsTwoDimensionMapDao
				.getViewPopupInfoById(id);
		PropertyDict dict = null;
		if (cityComponentsInfoVo != null
				&& cityComponentsInfoVo.getPartName() != null
				&& cityComponentsInfoVo.getPartName().getId() != null) {
			dict = propertyDictService.getPropertyDictById(cityComponentsInfoVo
					.getPartName().getId());
			if (dict != null) {
				cityComponentsInfoVo.setName(dict.getDisplayName());
				cityComponentsInfoVo.setPartName(dict);
			}
		}
		return cityComponentsInfoVo;
	}

	@Override
	protected boolean validateParams(Long id, String tableName, String type) {
		return id != null;
	}

}
