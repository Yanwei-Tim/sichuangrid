package com.tianque.openLayersMap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.KeyPlaceTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.vo.KeyPlaceInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.service.SysGisTypeManageService;

/**
 * 二维地图 重点场所查询、详情查看方法的实现
 * 
 * @author jiangjinling
 * 
 */
@Service("keyPlaceMapSearchService")
public class KeyPlaceTownshipsUnderSearchServiceImpl extends
		AbstractTownshipsUnderSearchService<KeyPlaceInfoVo> {

	@Autowired
	private KeyPlaceTwoDimensionMapDao keyPlaceTwoDimensionMapDao;
	@Autowired
	private SysGisTypeManageService sysGisTypeManageService;

	@Override
	public PageInfo<KeyPlaceInfoVo> findPageInfoByOrgIdAndScreenCoordinateVoAndTypeName(
			Long orgId, ScreenCoordinateVo screenCoordinateVo, String typeName,
			String childTableName, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		if (orgId == null || typeName == null) {
			throw new BusinessValidationException("参数错误!");
		}
		checkForPoint(screenCoordinateVo);
		String orgInternalCode = getOrgInternalCode(orgId);
		PageInfo<KeyPlaceInfoVo> pageInfo = keyPlaceTwoDimensionMapDao
				.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName(
						orgInternalCode, screenCoordinateVo, typeName, pageNum,
						pageSize, sidx, sord);

		return setKeyPlaceInfoVoTypeName(pageInfo, orgInternalCode);
	}

	@Override
	public PageInfo<KeyPlaceInfoVo> findPageInfoByOrgIdAndScreenCoordinateVoAndSearchValue(
			Long orgId, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, Integer pageNum, Integer pageSize, String sidx,
			String sord, String mainTableName) {
		if (orgId == null) {
			throw new BusinessValidationException("请选择一个组织机构!");
		}
		checkForPoint(screenCoordinateVo);
		String orgInternalCode = getOrgInternalCode(orgId);
		// 如果是实有单位,两新组织,企业 则传入场所类型
		List<String> typeList = getTypeList(mainTableName);

		PageInfo<KeyPlaceInfoVo> pageInfo = keyPlaceTwoDimensionMapDao
				.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue(
						orgInternalCode, screenCoordinateVo, typeList,
						searchValue, pageNum, pageSize, sidx, sord);

		return pageInfo;
	}

	@Override
	public PageInfo<KeyPlaceInfoVo> findPageInfoByOrgIdAndTypeName(Long orgId,
			String typeName, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		if (orgId == null || typeName == null) {
			throw new BusinessValidationException("参数错误!");
		}
		String orgInternalCode = getOrgInternalCode(orgId);
		PageInfo<KeyPlaceInfoVo> pageInfo = keyPlaceTwoDimensionMapDao
				.findTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
						orgInternalCode, typeName, pageNum, pageSize, sidx,
						sord);
		return setKeyPlaceInfoVoTypeName(pageInfo, orgInternalCode);
	}

	private PageInfo<KeyPlaceInfoVo> setKeyPlaceInfoVoTypeName(
			PageInfo<KeyPlaceInfoVo> pageInfo, String orgInternalCode) {

		for (int i = 0; i < pageInfo.getResult().size(); i++) {// 得到keyPlaceInfoVo的typeName
			GisTypeManage gisTypeManage = new GisTypeManage();
			gisTypeManage.setKey(pageInfo.getResult().get(i).getType());
			gisTypeManage = sysGisTypeManageService
					.getGisTypeManagesByKey(gisTypeManage);
			pageInfo.getResult().get(i).setTypeName(gisTypeManage.getName());
			pageInfo.getResult().get(i).setOrgInternalCode(orgInternalCode);
		}
		return pageInfo;
	}

}
