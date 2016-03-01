package com.tianque.openLayersMap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.PublicSecurityTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.GisBoundVo;
import com.tianque.openLayersMap.domian.vo.PublicSecurityInfoVo;
import com.tianque.openLayersMap.service.BaseService;
import com.tianque.openLayersMap.service.MapBindingManageService;
import com.tianque.openLayersMap.util.GisTransformatUtil;
import com.tianque.openLayersMap.util.GisType;

/**
 * @功能：公安部件绑定和解除绑定
 * 
 */
@Service("publicSecurityMapBindingManageService")
public class PublicSecurityMapBindingManageServiceImpl extends BaseService
		implements MapBindingManageService<Boolean> {

	@Autowired
	private PublicSecurityTwoDimensionMapDao publicSecurityTwoDimensionMapDao;

	@Override
	public Boolean boundTwoDimensionMap(GisBoundVo gisBoundVo, String type) {
		if (gisBoundVo == null || gisBoundVo.getIds() == null
				|| gisBoundVo.getIds()[0] == null) {
			throw new BusinessValidationException("id不能为空");
		}
		Long id = Long.valueOf(gisBoundVo.getIds()[0]);
		GisTransformatUtil.autoFillGisBound(gisBoundVo);

		PublicSecurityInfoVo domain = new PublicSecurityInfoVo(id,
				gisBoundVo.getLon(), gisBoundVo.getLat(), gisBoundVo.getLon2(),
				gisBoundVo.getLat2(), type);
		return publicSecurityTwoDimensionMapDao.updateTwoDimensionMap(domain);
	}

	@Override
	public Boolean boundTwoDimensionMap(String[] ids, String lon, String lat,
			String type, Long buildDataId, String gisType) {
		if (ids == null) {
			throw new BusinessValidationException("id为空");
		}
		PublicSecurityInfoVo publicSecurityInfoVo = null;

		if (GisType.M3D.equals(gisType)) {
			String[] lonlat2 = GisTransformatUtil.to2DPoint(lon, lat);
			publicSecurityInfoVo = new PublicSecurityInfoVo(
					Long.valueOf(ids[0]), lon, lat, lonlat2[0], lonlat2[1],
					type);
		} else {
			String[] lonlat3 = GisTransformatUtil.to25DPoint(lon, lat);
			publicSecurityInfoVo = new PublicSecurityInfoVo(
					Long.valueOf(ids[0]), lonlat3[0], lonlat3[1], lon, lat,
					type);
		}

		return publicSecurityTwoDimensionMapDao
				.updateTwoDimensionMap(publicSecurityInfoVo);
	}

	@Override
	public Boolean unBoundTwoDimensionMap(Long id, String type, Long orgId) {
		if (id == null) {
			throw new BusinessValidationException("id为空");
		}
		PublicSecurityInfoVo publicSecurityInfoVo = new PublicSecurityInfoVo(
				id, null, null, null, null, type);
		return publicSecurityTwoDimensionMapDao
				.updateTwoDimensionMap(publicSecurityInfoVo);
	}

}
