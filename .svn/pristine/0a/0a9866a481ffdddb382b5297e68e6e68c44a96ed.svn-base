package com.tianque.openLayersMap.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.CityComponentsTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.CityComponentsInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;

/**
 * 
 * @功能：搜索:图层、精确搜索
 * @edit by lizhixiang
 */
@Service("cityComponentsMapSearchService")
public class CityComponentsTownshipsUnderSearchServiceImpl extends
		AbstractTownshipsUnderSearchService<CityComponentsInfoVo> {

	@Autowired
	private CityComponentsTwoDimensionMapDao cityComponentsTwoDimensionMapDao;

	@Override
	public PageInfo<CityComponentsInfoVo> findPageInfoByOrgIdAndScreenCoordinateVoAndTypeName(
			Long orgId, ScreenCoordinateVo screenCoordinateVo, String partType,
			String partName, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		if (orgId == null) {
			throw new BusinessValidationException("请选择一个组织机构!");
		}
		checkForPoint(screenCoordinateVo);
		String orgInternalCode = getOrgInternalCode(orgId);
		PageInfo<CityComponentsInfoVo> page = cityComponentsTwoDimensionMapDao
				.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo(
						orgInternalCode, screenCoordinateVo, pageNum, pageSize,
						sidx, sord, partType, partName);
		setDisplayName(page.getResult());
		return page;
	}

	@Override
	public PageInfo<CityComponentsInfoVo> findPageInfoByOrgIdAndScreenCoordinateVoAndSearchValue(
			Long orgId, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, Integer pageNum, Integer pageSize, String sidx,
			String sord, String mainTableName) {
		if (orgId == null || searchValue == null) {
			throw new BusinessValidationException("参数错误!");
		}
		checkForPoint(screenCoordinateVo);
		String orgInternalCode = getOrgInternalCode(orgId);
		PageInfo<CityComponentsInfoVo> page = cityComponentsTwoDimensionMapDao
				.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue(
						mainTableName, orgInternalCode, screenCoordinateVo,
						searchValue, pageNum, pageSize, sidx, sord);
		setDisplayName(page.getResult());
		return page;
	}

	@Override
	public PageInfo<CityComponentsInfoVo> findPageInfoByOrgIdAndTypeName(
			Long orgId, String typeName, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		// TODO Auto-generated method stub
		return null;
	}

	private void setDisplayName(List<CityComponentsInfoVo> list) {
		if (list == null || list.isEmpty()) {
			return;
		}
		for (Iterator<CityComponentsInfoVo> it = list.iterator(); it.hasNext();) {
			CityComponentsInfoVo bean = it.next();
			PropertyDict dict = null;
			if (bean.getPartName() != null
					&& bean.getPartName().getId() != null) {
				dict = propertyDictService.getPropertyDictById(bean
						.getPartName().getId());
			}
			if (dict != null) {
				bean.setName(dict.getDisplayName());
				bean.setPartName(dict);
			}
		}
	}

}
