package com.tianque.openLayersMap.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.KeyPersonTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.vo.KeyPersonInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.service.SysGisTypeManageService;
import com.tianque.shard.util.ShardConversion;
import com.tianque.shard.util.ShardTables;

/**
 * 二维地图 重点人员查询、详情查看方法的实现
 * 
 * @author jiangjinling
 * 
 */
@Service("keyPersonMapSearchService")
public class KeyPersonTownshipsUnderSearchServiceImpl extends
		AbstractTownshipsUnderSearchService<KeyPersonInfoVo> {

	@Autowired
	private KeyPersonTwoDimensionMapDao keyPersonTwoDimensionMapDao;
	@Autowired
	private SysGisTypeManageService sysGisTypeManageService;
	@Autowired
	private ShardConversion shardConversion;

	@Override
	public PageInfo<KeyPersonInfoVo> findPageInfoByOrgIdAndScreenCoordinateVoAndTypeName(
			Long orgId, ScreenCoordinateVo screenCoordinateVo, String typeName,
			String childTableName, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		if (orgId == null || typeName == null) {
			throw new BusinessValidationException("网格id不能为空或者类型名称不能为空");
		}
		checkForPoint(screenCoordinateVo);
		String orgInternalCode = getOrgInternalCode(orgId);
		PageInfo<KeyPersonInfoVo> pageInfo = keyPersonTwoDimensionMapDao
				.findTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
						orgInternalCode, typeName, pageNum, pageSize, sidx,
						sord, shardConversion.getShardCode(orgId));
		setDisplayName(pageInfo.getResult(), orgInternalCode);
		setPersonType(pageInfo.getResult());
		return pageInfo;
	}

	@Override
	public PageInfo<KeyPersonInfoVo> findPageInfoByOrgIdAndScreenCoordinateVoAndSearchValue(
			Long orgId, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, Integer pageNum, Integer pageSize, String sidx,
			String sord, String mainTableName) {
		if (orgId == null || searchValue == null) {
			throw new BusinessValidationException("参数错误!");
		}
		checkForPoint(screenCoordinateVo);
		if (pageSize > 1000)
			pageSize = 1000;

		String orgInternalCode = getOrgInternalCode(orgId);

		GisTypeManage gisTypeManage = new GisTypeManage();
		gisTypeManage.setInnerKey(mainTableName);
		List<GisTypeManage> gisTypeManageList = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(gisTypeManage);
		String shardCode = shardConversion.getShardCode(orgId);
		for (GisTypeManage gisType : gisTypeManageList) {
			gisType.setOriginalTableName(gisType.getTableName());
			if (ShardTables.isShardTables(gisType.getTableName())) {
				gisType.setTableName(gisType.getTableName() + "_" + shardCode);
			}
		}
		PageInfo<KeyPersonInfoVo> pageInfo = keyPersonTwoDimensionMapDao
				.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeNameAndSearchValue(
						orgInternalCode, screenCoordinateVo, searchValue,
						pageNum, pageSize, sidx, sord, gisTypeManageList,
						shardCode);
		setDisplayName(pageInfo.getResult(), orgInternalCode);
		return pageInfo;
	}

	@Override
	public PageInfo<KeyPersonInfoVo> findPageInfoByOrgIdAndTypeName(Long orgId,
			String typeName, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		if (orgId == null || typeName == null) {
			throw new BusinessValidationException("网格id不能为空或者类型名称不能为空");
		}
		String orgInternalCode = getOrgInternalCode(orgId);
		PageInfo<KeyPersonInfoVo> pageInfo = keyPersonTwoDimensionMapDao
				.findTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
						orgInternalCode, typeName, pageNum, pageSize, sidx,
						sord, shardConversion.getShardCode(orgId));
		setDisplayName(pageInfo.getResult(), orgInternalCode);
		return pageInfo;
	}

	private void setPersonType(List<KeyPersonInfoVo> list) {
		for (int i = 0; i < list.size(); i++) {
			KeyPersonInfoVo keyPersonInfoVo = list.get(i);
			if (keyPersonInfoVo.getTypeName() != null) {
				if (keyPersonInfoVo.getTypeName().equals("floatingPopulation")) {
					keyPersonInfoVo.setTypeName("流动人口");
				} else
					keyPersonInfoVo.setTypeName("常住人口");
			}
		}
	}

	private void setDisplayName(List<KeyPersonInfoVo> infos,
			String orgInternalCode) {
		if (infos == null || infos.isEmpty()) {
			return;
		}
		for (Iterator<KeyPersonInfoVo> it = infos.iterator(); it.hasNext();) {
			KeyPersonInfoVo info = it.next();
			PropertyDict gender = info.getGender();
			if (gender == null) {
				return;
			}
			Long id = info.getGender().getId();
			PropertyDict dict = propertyDictService.getPropertyDictById(id);
			if (dict != null) {
				info.setGender(dict);
				info.setGenderName(dict.getDisplayName());
			}
			info.setOrgInternalCode(orgInternalCode);
		}
	}

}
