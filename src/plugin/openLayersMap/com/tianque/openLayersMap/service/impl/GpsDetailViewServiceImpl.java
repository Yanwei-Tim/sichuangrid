package com.tianque.openLayersMap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.openLayersMap.dao.GpsTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.GpsInfoVo;

@Service("gpsMapDetailViewService")
public class GpsDetailViewServiceImpl extends
		AbstractDetailViewService<GpsInfoVo> {

	@Autowired
	private GpsTwoDimensionMapDao gpsTwoDimensionMapDao;

	@Override
	protected GpsInfoVo doGetViewPopupInfoByIdAndTableNameAndType(Long id,
			String childTableName, String type, Long orgId) {
		return gpsTwoDimensionMapDao.getViewPopupInfoByIdAndType(id, type);
	}

	@Override
	protected boolean validateParams(Long id, String tableName, String type) {
		return id != null && type != null;
	}

}
