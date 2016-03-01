package com.tianque.openLayersMap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.rentalHouse.service.RentalHouseService;
import com.tianque.openLayersMap.dao.HousePropertyTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.HousePropertyInfoVo;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 住房详情查看实现类
 * 
 * @date 2013-3-18
 */
@Service("housePropertyMapDetailViewService")
public class HousePropertyDetailViewServiceImpl extends
		AbstractDetailViewService<HousePropertyInfoVo> {

	@Autowired
	private HousePropertyTwoDimensionMapDao housePropertyTwoDimensionMapDao;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private RentalHouseService rentalHouseService;
	@Autowired
	private ShardConversion shardConversion;

	@Override
	protected HousePropertyInfoVo doGetViewPopupInfoByIdAndTableNameAndType(
			Long id, String tableName, String type, Long orgId) {
		HousePropertyInfoVo housePropertyInfoVo = housePropertyTwoDimensionMapDao
				.getHousePropertyInfoById(id,
						shardConversion.getShardCode(orgId));
		return housePropertyInfoVo;
	}

	@Override
	protected boolean validateParams(Long id, String tableName, String type) {
		return id != null;
	}

	private void setIsRentalHouse(List<HousePropertyInfoVo> list) {
		for (int i = 0; i < list.size(); i++) {
			HousePropertyInfoVo housePropertyInfoVo = list.get(i);
			Long id = rentalHouseService
					.getHouseInfoByHouseId(housePropertyInfoVo.getId())
					.getHiddenDangerLevel().getId();
			housePropertyInfoVo.setHiddendangerLevel(propertyDictService
					.getPropertyDictById(id).getDisplayName());
			if (housePropertyInfoVo.getIsRentalHouse() != null) {
				if (housePropertyInfoVo.getIsRentalHouse().equals("0"))
					housePropertyInfoVo.setIsRentalHouse("不是");
				else
					housePropertyInfoVo.setIsRentalHouse("是");
			}
		}
	}

}
