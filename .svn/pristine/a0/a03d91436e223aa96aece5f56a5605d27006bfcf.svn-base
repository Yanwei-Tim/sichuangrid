package com.tianque.openLayersMap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.baseInfo.rentalHouse.service.RentalHouseService;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.HousePropertyTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.HousePropertyInfoVo;
import com.tianque.openLayersMap.service.BaseService;
import com.tianque.openLayersMap.service.HousePropertyService;
import com.tianque.openLayersMap.util.HouseTypeEnum;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 二维地图 住房个性化方法的实现
 * 
 * @author zhanghuafei
 * 
 */
@Service("housePropertyService")
public class HousePropertyServiceImpl extends BaseService implements
		HousePropertyService {

	@Autowired
	private HousePropertyTwoDimensionMapDao housePropertyTwoDimensionMapDao;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private RentalHouseService rentalHouseService;
	@Autowired
	private ShardConversion shardConversion;

	@Override
	public List<HousePropertyInfoVo> findHousePropertyInfoVoListByBuilddatasId(
			Long builddatasId, Long orgId) {
		if (builddatasId == null) {
			throw new BusinessValidationException("参数为空");
		}
		List<HousePropertyInfoVo> housePropertyInfoVoList = housePropertyTwoDimensionMapDao
				.findTwoDimensionMapByBuildingId(builddatasId,
						shardConversion.getShardCode(orgId));
		setIsRentalHouse(housePropertyInfoVoList);
		setHouseBackGround(housePropertyInfoVoList);

		return housePropertyInfoVoList;
	}

	/**
	 * 设置住房的背景色
	 */
	private void setHouseBackGround(
			List<HousePropertyInfoVo> housePropertyInfoVoList) {
		for (HousePropertyInfoVo housePropertyInfoVo : housePropertyInfoVoList) {
			if (housePropertyInfoVo.getHouseType() != null) {
				PropertyDict houseType = propertyDictService
						.getPropertyDictById(housePropertyInfoVo.getHouseType()
								.getId());
				if (houseType != null) {
					housePropertyInfoVo.setHouseBackGround(HouseTypeEnum
							.valueOf(houseType.getSimplePinyin().toUpperCase())
							.getColor());
				} else {
					housePropertyInfoVo.setHouseBackGround("");
				}
			}
		}
	}

	@Override
	public void synchronousHousePropertyInfoTwoDimensionMap(
			HousePropertyInfoVo housePropertyInfoVo, String shardCode) {

		housePropertyTwoDimensionMapDao
				.updateHousePropertyInfoVoTwoDimensionMap(housePropertyInfoVo,
						shardCode);
	}

	@Override
	public Integer countHousePropertyByBuildDataId(Long buildDataId, Long orgId) {
		if (buildDataId == null) {
			throw new BusinessValidationException("楼宇id不能为空！");
		}
		return housePropertyTwoDimensionMapDao.countHousePropertyByBuildDataId(
				buildDataId, shardConversion.getShardCode(orgId));
	}

	@Override
	public List<HousePropertyInfoVo> findHousePropertysByCenterLonLat(
			String centerLon, String centerLat, String gisType) {
		if (!StringUtil.isStringAvaliable(centerLon)
				|| !StringUtil.isStringAvaliable(centerLat)) {
			throw new BusinessValidationException("经纬度信息不能为空！");
		}
		List<HousePropertyInfoVo> infos = housePropertyTwoDimensionMapDao
				.findHousePropertysByCenterLonLat(centerLon, centerLat, gisType);
		setIsRentalHouse(infos);
		return infos;
	}

	@Override
	public PageInfo<HousePropertyInfoVo> findTwoDimensionMapPageInfoByBuildingId(
			Long orgId, Long buildDataId, String typeName, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		if (buildDataId == null) {
			throw new BusinessValidationException("buildDataId不能为空");
		}
		PageInfo<HousePropertyInfoVo> pageInfo = housePropertyTwoDimensionMapDao
				.findTwoDimensionMapPageInfoByBuildingId(buildDataId, pageNum,
						pageSize, sidx, sord,
						shardConversion.getShardCode(orgId));
		setIsRentalHouse(pageInfo.getResult());
		return pageInfo;
	}

	private void setIsRentalHouse(List<HousePropertyInfoVo> list) {
		for (int i = 0; i < list.size(); i++) {
			HousePropertyInfoVo housePropertyInfoVo = list.get(i);
			RentalHouse rentalHouse = rentalHouseService
					.getHouseInfoByHouseId(housePropertyInfoVo.getId());
			if (rentalHouse == null)
				continue;
			if (rentalHouse.getHiddenDangerLevel() != null
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
}
