package com.tianque.openLayersMap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.baseInfo.rentalHouse.service.RentalHouseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.HousePropertyTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.HousePropertyInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.util.GisGlobalValueMap;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 二维地图 住房查询、详情查看方法的实现
 * 
 * @author zhanghuafei
 * 
 */
@Service("housePropertyMapSearchService")
public class HousePropertyTownshipsUnderSearchServiceImpl extends
		AbstractTownshipsUnderSearchService<HousePropertyInfoVo> {

	@Autowired
	private HousePropertyTwoDimensionMapDao housePropertyTwoDimensionMapDao;
	@Autowired
	private RentalHouseService rentalHouseService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private ShardConversion shardConversion;

	@Override
	public PageInfo<HousePropertyInfoVo> findPageInfoByOrgIdAndScreenCoordinateVoAndSearchValue(
			Long orgId, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, Integer pageNum, Integer pageSize, String sidx,
			String sord, String mainTableName) {
		if (orgId == null || searchValue == null) {
			throw new BusinessValidationException("参数错误!");
		}
		checkForPoint(screenCoordinateVo);
		String orgInternalCode = getOrgInternalCode(orgId);

		PageInfo<HousePropertyInfoVo> pageInfo = housePropertyTwoDimensionMapDao
				.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue(
						orgInternalCode, screenCoordinateVo, searchValue,
						pageNum, pageSize, sidx, sord,
						shardConversion.getShardCode(orgId));

		setIsRentalHouse(pageInfo.getResult(), orgInternalCode);
		return pageInfo;
	}

	private void setIsRentalHouse(List<HousePropertyInfoVo> list,
			String orgInternalCode) {
		for (int i = 0; i < list.size(); i++) {
			HousePropertyInfoVo housePropertyInfoVo = list.get(i);
			housePropertyInfoVo.setOrgInternalCode(orgInternalCode);
			RentalHouse rentalHouse = rentalHouseService
					.getHouseInfoByHouseId(housePropertyInfoVo.getId());
			if (rentalHouse == null) {
				list.get(i).setIsRentalHouse("不是");
				continue;
			}
			if (rentalHouse != null
					&& rentalHouse.getHiddenDangerLevel() != null
					&& rentalHouse.getHiddenDangerLevel().getId() != null) {
				Long id = rentalHouse.getHiddenDangerLevel().getId();
				housePropertyInfoVo.setHiddendangerLevel(propertyDictService
						.getPropertyDictById(id).getDisplayName());
			}
			if (housePropertyInfoVo.getIsRentalHouse() != null) {
				if (housePropertyInfoVo.getIsRentalHouse().equals("0"))
					housePropertyInfoVo.setIsRentalHouse("不是");
				else
					housePropertyInfoVo.setIsRentalHouse("是");
			}
		}
	}

	@Override
	public PageInfo<HousePropertyInfoVo> findPageInfoByOrgIdAndTypeName(
			Long orgId, String typeName, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		if (orgId == null || typeName == null) {
			throw new BusinessValidationException("参数错误!");
		}
		if (pageSize > 1000)
			pageSize = 1000;
		String orgInternalCode = getOrgInternalCode(orgId);
		PageInfo<HousePropertyInfoVo> housePropertyInfoVos = new PageInfo<HousePropertyInfoVo>();
		housePropertyInfoVos = housePropertyTwoDimensionMapDao
				.findHouseInfoTwoDimensionMapPageInfoByOrgInternalCode(
						orgInternalCode, pageNum, pageSize, sidx, sord,
						typeName, shardConversion.getShardCode(orgId));
		setIsRentalHouse(housePropertyInfoVos.getResult(), orgInternalCode);
		return housePropertyInfoVos;
	}

	@Override
	public PageInfo<HousePropertyInfoVo> findPageInfoByOrgIdAndScreenCoordinateVoAndTypeName(
			Long orgId, ScreenCoordinateVo screenCoordinateVo, String typeName,
			String childTableName, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		if (orgId == null) {
			throw new BusinessValidationException("组织机构不能为空!");
		}
		checkForPoint(screenCoordinateVo);
		String orgInternalCode = getOrgInternalCode(orgId);
		int isRentalHouse = 2;
		if (GisGlobalValueMap.HOUSEINFO_MODE.equalsIgnoreCase(typeName)) {
			isRentalHouse = 0;
		} else if (GisGlobalValueMap.RENTALHOUSE_MODE
				.equalsIgnoreCase(typeName)) {
			isRentalHouse = 1;
		}
		PageInfo<HousePropertyInfoVo> pageInfo = housePropertyTwoDimensionMapDao
				.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo(
						orgInternalCode, screenCoordinateVo, pageNum, pageSize,
						sidx, sord, isRentalHouse,
						shardConversion.getShardCode(orgId));
		setIsRentalHouse(pageInfo.getResult(), orgInternalCode);
		return pageInfo;
	}

}
