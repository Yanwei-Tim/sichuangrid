package com.tianque.baseInfo.houseTrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.houseTrack.dao.HouseTracksDao;
import com.tianque.baseInfo.houseTrack.domain.HouseTracks;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.exception.base.BusinessValidationException;

@Service("houseTracksService")
public class HouseTracksServiceImpl implements HouseTracksService {

	@Autowired
	private HouseTracksDao houseTracksDao;

	public PageInfo<HouseTracks> findHouseTracksByHouseId(Integer houseId,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		if (houseId == null) {
			throw new BusinessValidationException("参数错误");
		}
		return houseTracksDao.findHouseTracksByHouseId(houseId, pageNum,
				pageSize, sidx, sord);
	}

	@Transactional
	public void addHouseTracks(HouseInfo houseInfo, String houseType,
			Integer houseinitType, Organization oldOrg, Integer operationType,
			String operationContent) {
		User operationUser = ThreadVariable.getUser();
		if (!validate(houseInfo, houseType, houseinitType, operationType,
				operationUser)) {
			throw new BusinessValidationException("参数错误");
		}
		HouseTracks houseTracks = new HouseTracks();
		houseTracks.setHouseId(houseInfo.getId());
		houseTracks.setHouseAddress(houseInfo.getAddress());
		houseTracks.setHouseType(houseType);
		houseTracks.setHouseinitType(houseinitType);
		houseTracks.setHouseOrganization(houseInfo.getOrganization());
		houseTracks.setHouseOrgInternalCode(houseInfo.getOrgInternalCode());
		houseTracks.setOldHouseOrganization(oldOrg);
		houseTracks.setOperationType(operationType);
		houseTracks.setOperationContent(operationContent);
		houseTracks.setOperationUserId(operationUser.getId());
		houseTracks.setOperationOrganization(operationUser.getOrganization());
		houseTracks
				.setOperationDate(houseInfo.getCreateDate() == null ? CalendarUtil
						.now("yyyy-MM-dd HH:mm:ss") : houseInfo.getCreateDate());

		houseTracksDao.addHouseTracks(houseTracks);
	}

	private boolean validate(HouseInfo houseInfo, String houseType,
			Integer houseinitType, Integer operationType, User operationUser) {
		if (houseInfo == null || houseInfo.getOrganization() == null
				|| !StringUtil.isStringAvaliable(houseType)
				|| houseinitType == null || operationType == null
				|| operationUser == null) {
			return false;
		}
		return true;
	}
}
