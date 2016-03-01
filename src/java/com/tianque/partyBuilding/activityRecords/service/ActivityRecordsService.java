package com.tianque.partyBuilding.activityRecords.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.partyBuilding.activityRecords.domain.ActivityRecords;
import com.tianque.partyBuilding.activityRecords.domain.ActivityRecordsAttachFiles;
import com.tianque.partyBuilding.activityRecords.domain.vo.ActivityRecordsVo;

public interface ActivityRecordsService {

	/**
	 * @param orgId
	 * @param organizationType
	 * @param orgCode
	 *            根据组织机构id,orgCode 和 organizationType(类型)organizationId(组织id)查询
	 *            【可以根据传的参数的不同得到不同的结果集，如根据组织id和组织类型查询一个组织下所有的组织活动信息】
	 * 
	 * */
	public PageInfo findActivityRecordsForPageByOrgId(Long orgId,
			String orgCode, String organizationType, Long organizationId,
			Integer pageNum, Integer pageSize, String sortField, String sord);

	/**
	 * @param id
	 *            根据组织活动附件id查询附件
	 * 
	 * */
	public ActivityRecordsAttachFiles getActivityRecordsAttachFilesById(Long id);

	/**
	 * @param id
	 *            根据附件id删除附件
	 * */
	public void deleteActivityRecordsAttachFilesById(Long id);

	/**
	 * @param activityRecordId
	 *            根据组织活动id查询所有附件
	 * 
	 * */
	public List<ActivityRecordsAttachFiles> findActivityRecordAttachFilesByActivityRecordId(
			Long activityRecordId);

	/**
	 * @param activityRecords
	 *            新增组织活动
	 * 
	 * */
	public ActivityRecords addActivityRecords(ActivityRecords activityRecords);

	/**
	 * @param activityRecords
	 *            新增组织活动的附件返回一个组织活动所有的附件
	 * 
	 * */
	public List<ActivityRecordsAttachFiles> addAttachFileByActivityRecordsId(
			Long id, String[] attachFiles);

	/**
	 * @param orgId
	 * @param orgInternalCodeById
	 * @param organizationType
	 *            匹配查询组织（主要包括组织的类型【对应的表】组织id）
	 * 
	 * */
	public List findOrganizationByType(Long orgId, String orgInternalCode,
			String organizationType, String organizationName);

	/**
	 * @param id
	 * @param organizationType
	 *            根据id查询组织活动
	 * 
	 * */
	public ActivityRecords getActivityRecordById(Long id,
			String organizationType);

	/**
	 * @param id
	 *            根据ids删除组织活动以及附件
	 * 
	 * */
	public void deleteActivityRecordByIds(List<Long> idList);

	/**
	 * @param activityRecords
	 * @param attachFiles
	 *            修改组织活动以及附件
	 * 
	 * */
	public ActivityRecords updateActivityRecord(
			ActivityRecords activityRecords, String[] attachFiles);

	/**
	 * @param activityRecordsVo
	 * @param
	 * @param 根据条件查询组织活动
	 * 
	 * */
	public PageInfo searchActivityRecordsBySearchVo(Integer pageNum,
			Integer pageSize, ActivityRecordsVo activityRecordsVo);

	/**
	 * @param internalId
	 * @param organizationType
	 * @param orgInternalCode
	 * @param 根据区域级别id和区域编号和组织类型统计一个区域所有的组织活动信息的数量
	 *            【提供给统计的方法】
	 * 
	 * */
	public Integer countActivityRecordByOrgIdOrgInternalCode(Long internalId,
			String orgInternalCode, String organizationType);

	/**
	 * @param organizationType
	 * @param OrganizationId
	 * @param 根据党组织id和党组织类型删除所有该党组织的下的活动记录和活动记录的附件
	 *            【提供给组织删除组织的方法】
	 * 
	 * */
	public void deleteAllActivityRecordAndAttachFilesByOrganizationIdsAndOrganizationType(
			Long[] OrganizationIds, String organizationType);

	/**
	 * @param organizationType
	 * @param OrganizationId
	 * @param 根据党组织id和党组织类型查询所有该党组织的下的活动记录和活动记录的附件
	 * 
	 * */
	public List<ActivityRecords> findAllActivityRecordByOrganizationIdAndOrganizationType(
			Long OrganizationId, String organizationType);
}
