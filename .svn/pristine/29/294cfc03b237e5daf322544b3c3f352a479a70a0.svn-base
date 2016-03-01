package com.tianque.openLayersMap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.IssueTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.GisBoundVo;
import com.tianque.openLayersMap.domian.vo.IssueInfoVo;
import com.tianque.openLayersMap.service.BaseService;
import com.tianque.openLayersMap.service.MapBindingManageService;
import com.tianque.openLayersMap.util.GisTransformatUtil;
import com.tianque.openLayersMap.util.GisType;

/**
 * @功能：事件绑定和解除绑定
 * @edit by longzhendong
 * 
 */
@Service("issueMapBindingManageService")
public class IssueMapBindingManageServiceImpl extends BaseService implements
		MapBindingManageService<Boolean> {

	@Autowired
	private IssueTwoDimensionMapDao issueTwoDimensionMapDao;

	@Override
	public Boolean boundTwoDimensionMap(GisBoundVo gisBoundVo, String type) {
		if (gisBoundVo == null || gisBoundVo.getIds() == null
				|| gisBoundVo.getIds()[0] == null) {
			throw new BusinessValidationException("id不能为空");
		}
		Long id = Long.valueOf(gisBoundVo.getIds()[0]);
		GisTransformatUtil.autoFillGisBound(gisBoundVo);

		IssueInfoVo domain = new IssueInfoVo(id, gisBoundVo.getLon(),
				gisBoundVo.getLat(), gisBoundVo.getLon2(), gisBoundVo.getLat2());
		return issueTwoDimensionMapDao.updateTwoDimensionMap(domain);
	}

	@Override
	public Boolean boundTwoDimensionMap(String[] ids, String lon, String lat,
			String type, Long buildDataId, String gisType) {
		if (ids == null) {
			throw new BusinessValidationException("id为空");
		}
		IssueInfoVo issueInfoVo = null;
		if (GisType.M3D.equals(gisType)) {
			String[] lonlat2 = GisTransformatUtil.to2DPoint(lon, lat);
			issueInfoVo = new IssueInfoVo(Long.valueOf(ids[0]), lon, lat,
					lonlat2[0], lonlat2[1]);
		} else {
			String[] lonlat3 = GisTransformatUtil.to25DPoint(lon, lat);
			issueInfoVo = new IssueInfoVo(Long.valueOf(ids[0]), lonlat3[0],
					lonlat3[1], lon, lat);
		}

		return issueTwoDimensionMapDao.updateTwoDimensionMap(issueInfoVo);
	}

	@Override
	public Boolean unBoundTwoDimensionMap(Long id, String type, Long orgId) {
		if (id == null) {
			throw new BusinessValidationException("id为空");
		}
		IssueInfoVo issueInfoVo = new IssueInfoVo(id, null, null, null, null);
		return issueTwoDimensionMapDao.updateTwoDimensionMap(issueInfoVo);
	}

}
