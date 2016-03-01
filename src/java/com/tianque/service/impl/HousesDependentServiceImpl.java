package com.tianque.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.baseInfo.rentalHouse.service.RentalHouseService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.service.HousesDependentService;
import com.tianque.util.PropertyUtil;

@Service("housesDependentService")
@Transactional
public class HousesDependentServiceImpl implements HousesDependentService {

	public final static Logger logger = LoggerFactory
			.getLogger(HousesDependentServiceImpl.class);

	@Autowired
	private RentalHouseService rentalHouseService;

	@Autowired
	private ActualHouseService actualHouseService;

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tianque.service.HousesDependentService#
	 * synActualHouseToRentalHouseForUpdate(com.tianque.domain.RentalHouse)
	 */
	@Override
	public void synActualHouseToRentalHouseForUpdate(RentalHouse rentalHouse) {
		try {
			RentalHouse rentalHouseInfo = this.rentalHouseService
					.getHouseInfoByHouseCodeAndOrgId(
							rentalHouse.getHouseCode(), rentalHouse
									.getOrganization().getId());
			if (null != rentalHouseInfo) {
				PropertyUtil.copyPropertiesWithRecursion(RentalHouse.class,
						rentalHouseInfo, rentalHouse, new String[] { "id" });
				if (rentalHouse.getIsRentalHouse().booleanValue()) {// 页面勾选出租房
					this.rentalHouseService
							.updateHouseBaseInfo(rentalHouseInfo);
					updateEmphasiseByIds(rentalHouseInfo.getId(),
							Long.valueOf(0));
				} else {// 注销出租房信息
					updateEmphasiseByIds(rentalHouseInfo.getId(),
							Long.valueOf(1));
				}
			}
		} catch (Exception e) {
			logger.error(
					"类HousesDependentServiceImpl的synActualHouseToRentalHouseForUpdate方法出现异常，原因：",
					e);
			if (ExcelImportHelper.isImport.get()) {
				return;
			}
		}
	}

	private void updateEmphasiseByIds(Long id, Long emphasiseState) {
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		rentalHouseService.updateEmphasiseByIds(ids, emphasiseState);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tianque.service.HousesDependentService#
	 * synActualHouseToRentalHouseForDelete(java.lang.String, java.lang.Long)
	 */
	@Override
	public void synActualHouseToRentalHouseForDelete(String houseCode,
			Long orgId) {
		try {
			// 删除出租房信息
			RentalHouse rentalHouse = this.rentalHouseService
					.getHouseInfoByHouseCodeAndOrgId(houseCode, orgId);
			if (null != rentalHouse) {
				List<Long> houseIdList = new ArrayList<Long>();
				houseIdList.add(rentalHouse.getId());

				this.rentalHouseService
						.deleteRentalHouseByIds(parseToLong(houseIdList));
			}
		} catch (Exception e) {
			throw new ServiceValidationException("删除房屋信息同步删除出租房出现错误", e);
		}
	}

	private Long[] parseToLong(List<Long> idList) {
		Long[] ids = new Long[idList.size()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = idList.get(i);
		}
		return ids;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tianque.service.HousesDependentService#
	 * synRentalHouseToActualHouseForDelete(java.lang.String, java.lang.Long)
	 */
	@Override
	public void synRentalHouseToActualHouseForDelete(String houseCode,
			Long orgId) {
		try {
			// 删除实有房屋信息
			HouseInfo houseInfo = this.actualHouseService
					.getHouseInfoByHouseCodeAndOrgId(houseCode, orgId);
			if (null != houseInfo) {
				this.actualHouseService.deleteHouseInfosById(houseInfo.getId());
			}
		} catch (Exception e) {
			throw new ServiceValidationException("删除出租房时同步删除房屋信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.tianque.service.HousesDependentService#
	 * synRentalHouseToActualHouseForIsEmphasis(java.lang.Long, boolean)
	 */
	@Override
	public void synRentalHouseToActualHouseForIsEmphasis(Long houseId,
			Long isEmphasis) {
		RentalHouse rentalHouse = this.rentalHouseService
				.getHouseInfoById(houseId);
		if (null != rentalHouse) {
			// 更新实有房屋是否出租房状态
			HouseInfo houseInfo = this.actualHouseService
					.getHouseInfoById(rentalHouse.getHouseId());
			if (isEmphasis == 1) {
				houseInfo.setIsRentalHouse(Boolean.FALSE);
			} else {
				houseInfo.setIsRentalHouse(Boolean.TRUE);
			}
			this.actualHouseService.updateHouseInfo(houseInfo);
		}
	}

	@Override
	public void synActualHouseToRentalHouseForDelete(List<HouseInfo> houseInfos) {
		if (houseInfos == null || houseInfos.size() < 1) {
			return;
		}
		try {
			for (HouseInfo houseInfo : houseInfos) {
				// 删除出租房信息
				RentalHouse rentalHouse = this.rentalHouseService
						.getHouseInfoByHouseIdAndOrgId(houseInfo.getId(),
								houseInfo.getOrganization().getId());
				if (null != rentalHouse) {
					List<Long> houseIdList = new ArrayList<Long>();
					houseIdList.add(rentalHouse.getId());
					this.rentalHouseService
							.deleteRentalHouseByIds(parseToLong(houseIdList));
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException("删除房屋信息同步删除出租房出现错误", e);
		}
	}
}
