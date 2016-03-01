package com.tianque.partyBuilding.activityRecords.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.partyBuilding.activityRecords.domain.ActivityRecords;
import com.tianque.partyBuilding.activityRecords.domain.ActivityRecordsAttachFiles;
import com.tianque.partyBuilding.activityRecords.domain.vo.ActivityRecordsVo;
import com.tianque.partyBuilding.activityRecords.domain.vo.SearchOrganizationVo;

public interface ActivityRecordsDao {

	public PageInfo<ActivityRecords> findActivityRecordsForPageByOrgId(
			Long orgId, String orgCode, String organizationType,
			Long organizationId, Integer pageNum, Integer pageSize,
			String sortField, String sord);

	public ActivityRecordsAttachFiles getActivityRecordsAttachFilesById(Long id);

	public void deleteActivityRecordsAttachFilesById(Long id);

	public List<ActivityRecordsAttachFiles> findActivityRecordAttachFilesByActivityRecordId(
			Long activityRecordId);

	public ActivityRecords addActivityRecords(ActivityRecords activityRecords);

	public void addActivityRecordsAttachFiles(
			ActivityRecordsAttachFiles activityRecordsAttachFiles);

	public List findOrganizationByType(Long orgId, String orgInternalCode,
			String organizationType, String organizationName);

	public ActivityRecords getActivityRecordById(Long id);

	public void deleteActivityRecordById(Long id);

	public void deleteActivityRecordAttachFilesByActivityRecordId(
			Long activityRecordId);

	public SearchOrganizationVo findOrganizationById(Long id,
			String organizationType);

	public ActivityRecords updateActivityRecord(ActivityRecords activityRecords);

	public PageInfo searchActivityRecordsBySearchVo(Integer pageNum,
			Integer pageSize, ActivityRecordsVo activityRecordsVo);

	public Integer countActivityRecordByOrgIdOrgInternalCode(
			List<Long> orgIdList, String organizationType);

	public List<ActivityRecords> findAllActivityRecordByOrganizationIdAndOrganizationType(
			Long organizationId, String organizationType);
}
