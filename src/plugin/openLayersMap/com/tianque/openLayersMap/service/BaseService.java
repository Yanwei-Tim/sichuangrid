package com.tianque.openLayersMap.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.core.util.ThreadVariable;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;

public abstract class BaseService {

	public final static Logger logger = LoggerFactory
			.getLogger(BaseService.class);

	public Object getCurrentUser() {
		return ThreadVariable.getSession();
	}

	protected void checkForPoint(ScreenCoordinateVo screenCoordinateVo) {
		if (screenCoordinateVo == null) {
			throw new BusinessValidationException("屏幕坐标不能为空!");
		}
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
			throw new BusinessValidationException("最小纬度不能为空");
		}
	}
}
