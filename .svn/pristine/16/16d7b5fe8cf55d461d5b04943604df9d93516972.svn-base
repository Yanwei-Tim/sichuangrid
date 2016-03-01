package com.tianque.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.controller.annotation.SolrServerIndex;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.DateUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.dao.HouseHasActualPopulationDao;
import com.tianque.domain.HouseHasActualPopulation;
import com.tianque.jms.OperateMode;
import com.tianque.plugin.tqSearch.sqlMap.UpdateSqlMap;
import com.tianque.service.ActualPopulationService;
import com.tianque.service.HouseHasActualPopulationService;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.vo.IsEmphasis;
import com.tianque.shard.util.IdConversionShardUtil;

@Service("houseHasActualPopulationService")
@Transactional
public class HouseHasActualPopulationServiceImpl extends AbstractBaseService
		implements HouseHasActualPopulationService {
	@Autowired
	private HouseHasActualPopulationDao houseHasActualPopulationDao;

	@Autowired
	private ActualHouseService actualHouseService;

	@Autowired
	private Map<String, ActualPopulationService> actualPopulationServiceMap;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.service.HouseHasActualPopulationService#
	 * addHouseHasActualPopulation(com.tianque.domain.HouseHasActualPopulation)
	 */
	@Override
	@SolrServerIndex(mode = OperateMode.EDIT, value = UpdateSqlMap.HOUSE_POPULATION_KEY)
	public void addHouseHasActualPopulation(
			HouseHasActualPopulation houseHasActualPopulation) {
		try {
			houseHasActualPopulation.setDefaultLivingHouse(1L);
			houseHasActualPopulation
					.setPersonType(PopulationCatalog.ALL_ACTUAL_POPULATION);
			this.houseHasActualPopulationDao
					.addHouseHasActualPopulation(houseHasActualPopulation);
			// 房屋人数
			if (houseHasActualPopulation.getHouseId() != null) {
				String shardCode = IdConversionShardUtil
						.getShardCodeById(houseHasActualPopulation.getHouseId());
				actualHouseService.updateHouseInfoMemberNum(shardCode,
						houseHasActualPopulation.getHouseId(), 1);
			}
			sysActualPopulationByAddHousePopulationRela(houseHasActualPopulation);
		} catch (Exception e) {
			dealException(this, "addHouseHasActualPopulation",
					"保存人房关联关系信息出现错误", e);
		}
	}

	private void sysActualPopulationByAddHousePopulationRela(
			HouseHasActualPopulation houseHasActualPopulation) {
		HouseInfo houseInfo = actualHouseService
				.getHouseInfoById(houseHasActualPopulation.getHouseId());
		for (Entry<String, ActualPopulationService> entry : actualPopulationServiceMap
				.entrySet()) {
			String serviceName = entry.getKey();
			ActualPopulationService service = entry.getValue();
			if (serviceName.startsWith(houseHasActualPopulation
					.getPopulationType())) {
				service.sysActualPopulationByAddHousePopulationRela(
						houseHasActualPopulation.getPopulationId(), houseInfo);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.service.HouseHasActualPopulationService#
	 * updateHouseHasActualPopulationByPopulationTypeAndHouseId
	 * (java.lang.String, java.lang.Long, java.lang.String, java.lang.String,
	 * java.lang.Long)
	 */
	@Override
	public void updateHouseHasActualPopulationByPopulationTypeAndHouseCodes(
			String populationType, Long populationId, String oldHouseCode,
			String newHouseCode, Long orgId) {
		try {
			HouseInfo oldHouseInfo = this.actualHouseService
					.getHouseInfoByHouseCodeAndOrgId(oldHouseCode, orgId);
			HouseInfo newHouseInfo = this.actualHouseService
					.getHouseInfoByHouseCodeAndOrgId(newHouseCode, orgId);
			if (null != oldHouseInfo && null != newHouseInfo) {
				this.houseHasActualPopulationDao
						.updateHouseHasActualPopulationByPopulationTypeAndHouseId(
								populationType, populationId,
								oldHouseInfo.getId(), newHouseInfo.getId());
				// 房屋人数
				String oldShardCode = IdConversionShardUtil
						.getShardCodeById(oldHouseInfo.getId());
				actualHouseService.updateHouseInfoMemberNum(oldShardCode,
						oldHouseInfo.getId(), -1);
				String newShardCode = IdConversionShardUtil
						.getShardCodeById(newHouseInfo.getId());
				actualHouseService.updateHouseInfoMemberNum(newShardCode,
						newHouseInfo.getId(), 1);
			}
		} catch (Exception e) {
			dealException(this, "addHouseHasActualPopulation",
					"修改人房关联关系信息出现错误", e);
		}
	}

	/**
	 * 修改房屋关联信息
	 */
	@Override
	public void updateHouseHasActualPopulationByPopulationByHouseId(
			String populationType, Long populationId, Long houseOldId,
			Long houseNewId, Long orgId) {
		try {
			HouseInfo oldHouseInfo = this.actualHouseService
					.getHouseInfoById(houseOldId);
			HouseInfo newHouseInfo = this.actualHouseService
					.getHouseInfoById(houseNewId);
			if (null != oldHouseInfo && null != newHouseInfo) {
				this.houseHasActualPopulationDao
						.updateHouseHasActualPopulationByPopulationTypeAndHouseId(
								populationType, populationId,
								oldHouseInfo.getId(), newHouseInfo.getId());
				// 房屋人数
				String oldShardCode = IdConversionShardUtil
						.getShardCodeById(oldHouseInfo.getId());
				actualHouseService.updateHouseInfoMemberNum(oldShardCode,
						oldHouseInfo.getId(), -1);
				String newShardCode = IdConversionShardUtil
						.getShardCodeById(newHouseInfo.getId());
				actualHouseService.updateHouseInfoMemberNum(newShardCode,
						newHouseInfo.getId(), 1);

			}
		} catch (Exception e) {
			dealException(this,
					"updateHouseHasActualPopulationByPopulationByHouseId",
					"修改人房关联关系信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.service.HouseHasActualPopulationService#
	 * deleteHouseHasActualPopulationByPopulationTypeAndHouseId
	 * (java.lang.String, java.lang.Long, java.lang.Long)
	 */
	@Override
	@SolrServerIndex(mode = OperateMode.EDIT, value = UpdateSqlMap.HOUSE_POPULATION_KEY)
	public void deleteHouseHasActualPopulationByPopulationTypeAndHouseId(
			String populationType, Long houseId, Long populationId) {
		try {
			// this.houseHasActualPopulationDao
			// .deleteHouseHasActualPopulationByPopulationTypeAndHouseId(
			// populationType, houseId, populationId);
			// 房屋人数
			Long tempHouseId = houseHasActualPopulationDao
					.getHouseIdByPopulationTypeAndPopulationId(populationType,
							populationId);
			if (tempHouseId != null) {
				this.houseHasActualPopulationDao
						.deleteHouseHasActualPopulationByPopulationTypeAndHouseId(
								populationType, houseId, populationId);
				if (tempHouseId.equals(houseId)) {
					String shardCode = IdConversionShardUtil
							.getShardCodeById(houseId);
					Long logOut = actualHouseService
							.getLogOutByPopulationTypeAndPopulationId(
									shardCode, populationType, populationId);
					if (logOut != null && logOut.equals(IsEmphasis.Emphasis)) {
						actualHouseService.updateHouseInfoMemberNum(shardCode,
								houseId, -1);
					}
				}
			}
			syncActualPopulationByDeleteHousePopulationRela(populationType,
					populationId);
		} catch (Exception e) {
			dealException(this,
					"deleteHouseHasActualPopulationByPopulationTypeAndHouseId",
					"删除人房关联关系信息出现错误", e);
		}
	}

	// 这里删除房屋没有用的关联关系
	@Override
	@SolrServerIndex(mode = OperateMode.EDIT, value = UpdateSqlMap.HOUSE_POPULATION_KEY)
	public void deleteHouseHasActualPopulationByPopulationTypeAndHouseInfosId(
			String populationType, Long houseId, Long populationId) {
		try {
			// this.houseHasActualPopulationDao
			// .deleteHouseHasActualPopulationByPopulationTypeAndHouseId(
			// populationType, houseId, populationId);
			// 房屋人数
			Long tempHouseId = houseHasActualPopulationDao
					.getHouseIdByPopulationTypeAndPopulationId(populationType,
							populationId);
			if (tempHouseId != null) {
				this.houseHasActualPopulationDao
						.deleteHouseHasActualPopulationByPopulationTypeAndHouseId(
								populationType, houseId, populationId);
				if (tempHouseId.equals(houseId)) {
					String shardCode = IdConversionShardUtil
							.getShardCodeById(houseId);
					actualHouseService.updateHouseInfoMemberNum(shardCode,
							houseId, -1);
				}
			}
		} catch (Exception e) {
			dealException(this,
					"deleteHouseHasActualPopulationByPopulationTypeAndHouseId",
					"删除人房关联关系信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.service.HouseHasActualPopulationService#
	 * deleteHouseHasActualPopulationByPopulationTypeAndPopulationId
	 * (java.lang.String, java.lang.Long)
	 */
	@Override
	public void deleteHouseHasActualPopulationByPopulationTypeAndPopulationId(
			String populationType, Long populationId) {
		try {
			// 房屋人数
			Long houseId = houseHasActualPopulationDao
					.getHouseIdByPopulationTypeAndPopulationId(populationType,
							populationId);
			this.houseHasActualPopulationDao
					.deleteHouseHasActualPopulationByPopulationTypeAndPopulationId(
							populationType, populationId);
			// 房屋人数
			if (houseId != null) {
				String shardCode = IdConversionShardUtil
						.getShardCodeById(houseId);
				actualHouseService.updateHouseInfoMemberNum(shardCode, houseId,
						-1);
			}
			syncActualPopulationByDeleteHousePopulationRela(populationType,
					populationId);
		} catch (Exception e) {
			dealException(
					this,
					"deleteHouseHasActualPopulationByPopulationTypeAndPopulationId",
					"删除人房关联关系信息出现错误", e);
		}
	}

	private void syncActualPopulationByDeleteHousePopulationRela(
			String populationType, Long populationId) {
		for (Entry<String, ActualPopulationService> entry : actualPopulationServiceMap
				.entrySet()) {
			String serviceName = entry.getKey();
			ActualPopulationService service = entry.getValue();
			if (serviceName.startsWith(populationType)) {
				service.syncActualPopulationByDeleteHousePopulationRela(
						populationId, Boolean.FALSE, "被"
								+ ThreadVariable.getUser().getName() + "于"
								+ DateUtil.formateYMD(new Date()) + "移除");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.service.HouseHasActualPopulationService#
	 * getHouseHasActualPopulationByHouseId(java.lang.Long)
	 */
	@Override
	public List<HouseHasActualPopulation> getHouseHasActualPopulationByHouseId(
			Long houseId) {
		try {
			return this.houseHasActualPopulationDao
					.getHouseHasActualPopulationByHouseId(houseId);
		} catch (Exception e) {
			return dealException(this, "getHouseHasActualPopulationByHouseId",
					"获取人房关联关系信息出现错误", e);

		}
	}

	@Override
	public void deleteHouseHasActualPopulationByHouseId(Long houseId) {
		houseHasActualPopulationDao
				.deleteHouseHasActualPopulationByHouseId(houseId);
	}

	@Override
	public void deleteHousehasimportantpopulationByHouseId(Long houseId) {
		houseHasActualPopulationDao
				.deleteHousehasimportantpopulationByHouseId(houseId);

	}

	// 房屋人数
	@Override
	public Long getHouseIdByPopulationTypeAndPopulationId(
			String populationType, Long populationId) {
		return houseHasActualPopulationDao
				.getHouseIdByPopulationTypeAndPopulationId(populationType,
						populationId);
	}

}
