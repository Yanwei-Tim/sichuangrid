package com.tianque.openLayersMap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.CityComponentsTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.CityComponentsInfoVo;
import com.tianque.openLayersMap.domian.vo.GisBoundVo;
import com.tianque.openLayersMap.service.BaseService;
import com.tianque.openLayersMap.service.MapBindingManageService;
import com.tianque.openLayersMap.util.GisTransformatUtil;
import com.tianque.openLayersMap.util.GisType;

/**
 * @功能：事件绑定和解除绑定
 * @edit by longzhendong
 * 
 */
@Service("cityComponentsMapBindingManageService")
public class CityComponentsMapBindingManageServiceImpl extends BaseService
		implements MapBindingManageService<Boolean> {

	@Autowired
	private CityComponentsTwoDimensionMapDao cityComponentsTwoDimensionMapDao;

	@Override
	public Boolean boundTwoDimensionMap(GisBoundVo gisBoundVo, String type) {
		if (gisBoundVo == null || gisBoundVo.getIds() == null
				|| gisBoundVo.getIds()[0] == null) {
			throw new BusinessValidationException("id不能为空");
		}
		Long id = Long.valueOf(gisBoundVo.getIds()[0]);
		GisTransformatUtil.autoFillGisBound(gisBoundVo);

		CityComponentsInfoVo domain = new CityComponentsInfoVo(id,
				gisBoundVo.getLon(), gisBoundVo.getLat(), gisBoundVo.getLon2(),
				gisBoundVo.getLat2());
		return cityComponentsTwoDimensionMapDao.updateTwoDimensionMap(domain);
	}

	@Override
	public Boolean boundTwoDimensionMap(String[] ids, String lon, String lat,
			String type, Long buildDataId, String gisType) {
		if (ids == null) {
			throw new BusinessValidationException("id为空");
		}
		CityComponentsInfoVo cityComponentsInfoVo = null;
		if (GisType.M3D.equals(gisType)) {
			String[] lonlat2 = GisTransformatUtil.to2DPoint(lon, lat);
			cityComponentsInfoVo = new CityComponentsInfoVo(
					Long.valueOf(ids[0]), lon, lat, lonlat2[0], lonlat2[1]);
		} else {
			String[] lonlat3 = GisTransformatUtil.to25DPoint(lon, lat);
			cityComponentsInfoVo = new CityComponentsInfoVo(
					Long.valueOf(ids[0]), lonlat3[0], lonlat3[1], lon, lat);
		}

		return cityComponentsTwoDimensionMapDao
				.updateTwoDimensionMap(cityComponentsInfoVo);
	}

	@Override
	public Boolean unBoundTwoDimensionMap(Long id, String type, Long orgId) {
		if (id == null) {
			throw new BusinessValidationException("id为空");
		}
		CityComponentsInfoVo cityComponentsInfoVo = new CityComponentsInfoVo(
				id, null, null, null, null);
		return cityComponentsTwoDimensionMapDao
				.updateTwoDimensionMap(cityComponentsInfoVo);
	}

}
