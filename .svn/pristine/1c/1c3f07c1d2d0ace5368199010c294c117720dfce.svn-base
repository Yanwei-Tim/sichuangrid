package com.tianque.openLayersMap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.BuildDataTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.BuildDataInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.service.HousePropertyService;
import com.tianque.openLayersMap.util.GisGlobalValueMap;

/**
 * 二维地图 楼宇信息查询、详情查看方法的实现
 * 
 * @author jiangjinling
 * 
 */
@Service("buildDataMapSearchService")
public class BuildDataTownshipsUnderSearchServiceImpl extends
		AbstractTownshipsUnderSearchService<BuildDataInfoVo> {

	@Autowired
	private BuildDataTwoDimensionMapDao buildDataTwoDimensionMapDao;
	@Autowired
	private HousePropertyService housePropertyService;

	@Override
	public PageInfo<BuildDataInfoVo> findPageInfoByOrgIdAndScreenCoordinateVoAndSearchValue(
			Long orgId, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, Integer pageNum, Integer pageSize, String sidx,
			String sord, String mainTableName) {
		if (orgId == null || searchValue == null) {
			throw new BusinessValidationException("参数错误!");
		}
		checkForPoint(screenCoordinateVo);
		String orgInternalCode = getOrgInternalCode(orgId);
		return buildDataTwoDimensionMapDao
				.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue(
						orgInternalCode, screenCoordinateVo, searchValue,
						pageNum, pageSize, sidx, sord);
	}

	@Override
	public PageInfo<BuildDataInfoVo> findPageInfoByOrgIdAndTypeName(Long orgId,
			String typeName, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		String orgInternalCode = getOrgInternalCode(orgId);
		PageInfo<BuildDataInfoVo> buildDataInfoVoPageInfo = new PageInfo<BuildDataInfoVo>();
		if (typeName.equals(GisGlobalValueMap.BOUNDBUILDING)) {
			/*
			 * buildDataInfoVoPageInfo = boundBuilding(pageNum, pageSize, sidx,
			 * sord, orgInternalCode);
			 */
		} else if (typeName.equals(GisGlobalValueMap.UNBOUNDBUILDING)) {
			buildDataInfoVoPageInfo = unboundBuilding(pageNum, pageSize, sidx,
					sord, orgInternalCode);
		}
		return buildDataInfoVoPageInfo;
	}

	private PageInfo<BuildDataInfoVo> boundBuilding(Integer pageNum,
			Integer pageSize, String sidx, String sord, String orgInternalCode,
			String gisType) {
		PageInfo<BuildDataInfoVo> buildDataInfoVoPageInfo;
		buildDataInfoVoPageInfo = buildDataTwoDimensionMapDao
				.findBoundedTwoDimensionMapPageInfoByOrgInternalCode(
						orgInternalCode, pageNum, pageSize, sidx, sord);
		List<BuildDataInfoVo> buildDataInfoVoList = buildDataInfoVoPageInfo
				.getResult();
		for (int i = 0; i < buildDataInfoVoList.size(); i++) {
			/*
			 * Integer houseNum = housePropertyService
			 * .countHousePropertyByCenterLonLat(buildDataInfoVoList
			 * .get(i).getLon(), buildDataInfoVoList.get(i) .getLat());//
			 * 绑定在楼宇上的住房数量 buildDataInfoVoList.get(i).setHouseNum(houseNum);
			 */
			buildDataInfoVoPageInfo.setResult(buildDataInfoVoList);
		}
		return buildDataInfoVoPageInfo;
	}

	private PageInfo<BuildDataInfoVo> unboundBuilding(Integer pageNum,
			Integer pageSize, String sidx, String sord, String orgInternalCode) {
		PageInfo<BuildDataInfoVo> buildDataInfoVoPageInfo;
		buildDataInfoVoPageInfo = buildDataTwoDimensionMapDao
				.findUnBoundTwoDimensionMapPageInfoByOrgInternalCode(
						orgInternalCode, pageNum, pageSize, sidx, sord);
		List<BuildDataInfoVo> buildDataInfoVoList = buildDataInfoVoPageInfo
				.getResult();
		for (int i = 0; i < buildDataInfoVoList.size(); i++) {
			/*
			 * Integer houseNum = housePropertyService
			 * .countHousePropertyByCenterLonLat(buildDataInfoVoList
			 * .get(i).getLon(), buildDataInfoVoList.get(i) .getLat());//
			 * 绑定在楼宇上的住房数量 buildDataInfoVoList.get(i).setHouseNum(houseNum);
			 */
			buildDataInfoVoPageInfo.setResult(buildDataInfoVoList);
		}
		return buildDataInfoVoPageInfo;
	}

	@Override
	public PageInfo<BuildDataInfoVo> findPageInfoByOrgIdAndScreenCoordinateVoAndTypeName(
			Long orgId, ScreenCoordinateVo screenCoordinateVo, String typeName,
			String childTableName, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		// TODO Auto-generated method stub
		return null;
	}
}
