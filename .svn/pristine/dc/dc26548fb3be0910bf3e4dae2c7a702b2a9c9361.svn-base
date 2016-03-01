package com.tianque.openLayersMap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;
import com.tianque.openLayersMap.service.Gis2DLayerService;

/**
 * 二维地图网格查询、详情查看方法的实现
 * 
 * @author zhanghuafei
 * 
 */
@Transactional
@Service("gridLayerMapSearchService")
public class GridLayerTownshipsUnderSearchServiceImpl extends
		AbstractTownshipsUnderSearchService<Gis2DLayer> {
	@Autowired
	private Gis2DLayerService gis2DLayerService;

	@Override
	public PageInfo<Gis2DLayer> findPageInfoByOrgIdAndScreenCoordinateVoAndTypeName(
			Long orgId, ScreenCoordinateVo screenVo, String typeName,
			String childTableName, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		if (orgId == null) {
			throw new BusinessValidationException("网格id不能为空");
		}
		checkForPoint(screenVo);
		List<PropertyDict> dictList = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.GRID);
		PropertyDict gridDict = null;
		for (PropertyDict pDict : dictList) {
			gridDict = pDict;
		}
		String orgInternalCode = getOrgInternalCode(orgId);
		SearchInfoVo searchVo = new SearchInfoVo();
		searchVo.setScreenVo(screenVo);
		searchVo.setOrgInternalCode(orgInternalCode);
		searchVo.setOrgLevelId(gridDict.getId());
		return gis2DLayerService.findForPageBySearchVo(searchVo, pageNum, pageSize, sidx, sord);
	}

	@Override
	public PageInfo<Gis2DLayer> findPageInfoByOrgIdAndScreenCoordinateVoAndSearchValue(
			Long orgId, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, Integer pageNum, Integer pageSize, String sidx,
			String sord, String mainTableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<Gis2DLayer> findPageInfoByOrgIdAndTypeName(Long orgId,
			String typeName, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		// TODO Auto-generated method stub
		return null;
	}

}
