package com.tianque.openLayersMap.service.impl;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.service.BaseService;
import com.tianque.openLayersMap.service.TownshipsUnderSearchService;

/**
 * 
 * 
 * @date 2013-3-18
 */
public abstract class AbstractCommonTownshipsUnderSearchService<T> extends
		BaseService implements TownshipsUnderSearchService<T> {

	protected String tableName;
	protected String functionType;

	public void initParams(String tableName, String functionType) {
		this.tableName = tableName;
		this.functionType = functionType;
	}

	/**
	 * 检查经纬度是否存为空
	 * 
	 * @param screenCoordinateVo
	 *            屏幕经纬度对象
	 */
	protected void checkForPoint(ScreenCoordinateVo screenCoordinateVo) {
		if (screenCoordinateVo.getMaxLon() == null) {
			throw new BusinessValidationException("最大经度不能为空");
		}
		if (screenCoordinateVo.getMinLon() == null) {
			throw new BusinessValidationException("最小经度不能为空");
		}
		if (screenCoordinateVo.getMaxLat() == null) {
			throw new BusinessValidationException("最大纬度不能为空");
		}
		if (screenCoordinateVo.getMinLat() == null) {
			throw new BusinessValidationException("最大纬度不能为空");
		}
	}

}
