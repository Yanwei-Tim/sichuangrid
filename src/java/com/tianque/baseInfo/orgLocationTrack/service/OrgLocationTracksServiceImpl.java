package com.tianque.baseInfo.orgLocationTrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.People;
import com.tianque.baseInfo.orgLocationTrack.dao.OrgLocationTracksDao;
import com.tianque.baseInfo.orgLocationTrack.domain.LocationTracksEntity;
import com.tianque.baseInfo.orgLocationTrack.domain.OrgLocationTracks;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.LocationBaseInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.exception.base.BusinessValidationException;

@Service("orgLocationTracksService")
public class OrgLocationTracksServiceImpl implements OrgLocationTracksService {

	@Autowired
	private OrgLocationTracksDao orgLocationTracksDao;

	public PageInfo<OrgLocationTracks> findOrgLocationTracksByOrgIdAndName(
			Integer orgLocationOrgId, String orgLocationName, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		if (orgLocationOrgId == null
				|| !StringUtil.isStringAvaliable(orgLocationName)) {
			throw new BusinessValidationException("参数错误");
		}
		return orgLocationTracksDao.findOrgLocationTracksByOrgIdAndName(
				orgLocationOrgId, orgLocationName, pageNum, pageSize, sidx,
				sord);
	}

	@Transactional
	public void addLocationTracks(LocationTracksEntity locationTracksEntity,
			String orgLocationType, Integer orgLocationinitType,
			Organization oldOrg, Integer operationType, String operationContent) {
		User operationUser = ThreadVariable.getUser();
		LocationBaseInfo location = locationTracksEntity.getLocationBaseInfo();
		if (!validate(location, locationTracksEntity.getLocationName(),
				orgLocationType, orgLocationinitType, operationType,
				operationUser)) {
			throw new BusinessValidationException("参数错误");
		}
		OrgLocationTracks orgLocationTracks = new OrgLocationTracks();
		orgLocationTracks.setOrgLocationId(locationTracksEntity
				.getLocationBaseInfo().getId());
		orgLocationTracks.setOrgLocationName(locationTracksEntity
				.getLocationName());
		orgLocationTracks.setOrgLocationType(orgLocationType);
		orgLocationTracks.setOrgLocationinitType(orgLocationinitType);
		orgLocationTracks
				.setOrgLocationOrganization(location.getOrganization());
		orgLocationTracks.setOrgLocationOrgInternalCode(locationTracksEntity
				.getLocationOrgInternalCode());
		orgLocationTracks.setOldOrgLocationOrganization(oldOrg);
		orgLocationTracks.setOperationType(operationType);
		orgLocationTracks.setOperationContent(operationContent);
		orgLocationTracks.setOperationUserId(operationUser.getId());
		orgLocationTracks.setOperationOrganization(operationUser
				.getOrganization());
		orgLocationTracks
				.setOperationDate(location.getCreateDate() == null ? CalendarUtil
						.now("yyyy-MM-dd HH:mm:ss") : location.getCreateDate());

		orgLocationTracksDao.addOrgLocationTracks(orgLocationTracks);
	}

	private boolean validate(LocationBaseInfo location, String locationName,
			String orgLocationType, Integer orgLocationinitType,
			Integer operationType, User operationUser) {
		if (location == null || location.getOrganization() == null
				|| !StringUtil.isStringAvaliable(locationName)
				|| !StringUtil.isStringAvaliable(orgLocationType)
				|| orgLocationinitType == null || operationType == null
				|| operationUser == null) {
			return false;
		}
		return true;
	}

	@Override
	public void addNewEconomicOrganizationsLocationTracks(People location,
			String orgLocationType, Integer orgLocationinitType,
			Organization oldOrg, Integer operationType, String operationContent) {
		User operationUser = ThreadVariable.getUser();
		if (!validateNewEco(location, location.getName(), orgLocationType,
				orgLocationinitType, operationType, operationUser)) {
			throw new BusinessValidationException("参数错误");
		}
		OrgLocationTracks orgLocationTracks = new OrgLocationTracks();
		orgLocationTracks.setOrgLocationId(location.getId());
		orgLocationTracks.setOrgLocationName(location.getName());
		orgLocationTracks.setOrgLocationType(orgLocationType);
		orgLocationTracks.setOrgLocationinitType(orgLocationinitType);
		orgLocationTracks
				.setOrgLocationOrganization(location.getOrganization());
		orgLocationTracks.setOrgLocationOrgInternalCode(location
				.getOrgInternalCode());
		orgLocationTracks.setOldOrgLocationOrganization(oldOrg);
		orgLocationTracks.setOperationType(operationType);
		orgLocationTracks.setOperationContent(operationContent);
		orgLocationTracks.setOperationUserId(operationUser.getId());
		orgLocationTracks.setOperationOrganization(operationUser
				.getOrganization());
		orgLocationTracks
				.setOperationDate(location.getCreateDate() == null ? CalendarUtil
						.now("yyyy-MM-dd HH:mm:ss") : location.getCreateDate());
		orgLocationTracksDao.addOrgLocationTracks(orgLocationTracks);
	}

	private boolean validateNewEco(People location, String locationName,
			String orgLocationType, Integer orgLocationinitType,
			Integer operationType, User operationUser) {
		if (location == null || location.getOrganization() == null
				|| !StringUtil.isStringAvaliable(locationName)
				|| !StringUtil.isStringAvaliable(orgLocationType)
				|| orgLocationinitType == null || operationType == null
				|| operationUser == null) {
			return false;
		}
		return true;
	}
}