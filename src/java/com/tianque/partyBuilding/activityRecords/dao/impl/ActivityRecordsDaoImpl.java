package com.tianque.partyBuilding.activityRecords.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.partyBuilding.activityRecords.constant.OrganizationType;
import com.tianque.partyBuilding.activityRecords.dao.ActivityRecordsDao;
import com.tianque.partyBuilding.activityRecords.domain.ActivityRecords;
import com.tianque.partyBuilding.activityRecords.domain.ActivityRecordsAttachFiles;
import com.tianque.partyBuilding.activityRecords.domain.vo.ActivityRecordsVo;
import com.tianque.partyBuilding.activityRecords.domain.vo.SearchOrganizationVo;
import com.tianque.util.ParametersConvertUtil;

@Repository("activityRecordsDao")
public class ActivityRecordsDaoImpl extends AbstractBaseDao implements
		ActivityRecordsDao {

	@Override
	public PageInfo<ActivityRecords> findActivityRecordsForPageByOrgId(
			Long orgId, String orgCode, String organizationType,
			Long organizationId, Integer pageNum, Integer pageSize,
			String sortField, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("orgInternalCode", orgCode);
		map.put("organizationType", organizationType);
		map.put("organizationId", organizationId);
		map.put("table", organizationType);
		map.put("organizationName",
				OrganizationType.getOrganizationNameByType(organizationType));
		map.put("sortField", sortField);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"activityRecord.countActivityRecord", map);
		map.put("countNum", countNum);
		List<ActivityRecords> list = getSqlMapClientTemplate().queryForList(
				"activityRecord.findAllActivityRecord", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<ActivityRecords> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<ActivityRecords> pageInfo = new PageInfo<ActivityRecords>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public List<ActivityRecordsAttachFiles> findActivityRecordAttachFilesByActivityRecordId(
			Long activityRecordId) {
		List<ActivityRecordsAttachFiles> list = getSqlMapClientTemplate()
				.queryForList(
						"activityRecord.findActivityRecordAttachFilesByActivityRecordId",
						activityRecordId);
		return list;
	}

	@Override
	public ActivityRecords addActivityRecords(ActivityRecords activityRecords) {

		Long id = (Long) getSqlMapClientTemplate().insert(
				"activityRecord.addActivityRecord", activityRecords);
		return getActivityRecordById(id);
	}

	@Override
	public ActivityRecords getActivityRecordById(Long id) {
		return (ActivityRecords) getSqlMapClientTemplate().queryForObject(
				"activityRecord.getActivityRecordById", id);
	}

	@Override
	public void addActivityRecordsAttachFiles(
			ActivityRecordsAttachFiles activityRecordsAttachFiles) {
		getSqlMapClientTemplate().insert(
				"activityRecord.addActivityRecordAttachFiles",
				activityRecordsAttachFiles);

	}

	@Override
	public List findOrganizationByType(Long orgId, String orgInternalCode,
			String organizationType, String organizationName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("orgInternalCode", orgInternalCode);
		map.put("table", organizationType);
		map.put("organizationType", organizationType);
		map.put("organizationName",
				OrganizationType.getOrganizationNameByType(organizationType));
		map.put("name", organizationName);

		return getSqlMapClientTemplate().queryForList(
				"activityRecord.findOrganizationByType", map);
	}

	@Override
	public ActivityRecordsAttachFiles getActivityRecordsAttachFilesById(Long id) {
		return (ActivityRecordsAttachFiles) getSqlMapClientTemplate()
				.queryForObject(
						"activityRecord.findActivityRecordAttachFilesById", id);

	}

	@Override
	public void deleteActivityRecordsAttachFilesById(Long id) {
		getSqlMapClientTemplate().delete(
				"activityRecord.deleteActivityRecordAttachFilesById", id);

	}

	@Override
	public void deleteActivityRecordById(Long id) {
		getSqlMapClientTemplate().delete(
				"activityRecord.deleteActivityRecordById", id);

	}

	@Override
	public void deleteActivityRecordAttachFilesByActivityRecordId(
			Long activityRecordId) {
		getSqlMapClientTemplate()
				.delete("activityRecord.deleteActivityRecordAttachFilesByActivityRecordId",
						activityRecordId);
	}

	@Override
	public SearchOrganizationVo findOrganizationById(Long id,
			String organizationType) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("table", organizationType);
		map.put("organizationId", id);
		map.put("organizationType", organizationType);
		map.put("organizationName",
				OrganizationType.getOrganizationNameByType(organizationType));
		return (SearchOrganizationVo) getSqlMapClientTemplate().queryForObject(
				"activityRecord.findOrganizationById", map);
	}

	@Override
	public ActivityRecords updateActivityRecord(ActivityRecords activityRecords) {

		getSqlMapClientTemplate().update("activityRecord.updateActivityRecord",
				activityRecords);
		return getActivityRecordById(activityRecords.getId());
	}

	@Override
	public PageInfo<ActivityRecords> searchActivityRecordsBySearchVo(
			Integer pageNum, Integer pageSize,
			ActivityRecordsVo activityRecordsVo) {

		PageInfo<ActivityRecords> pageInfo = new PageInfo<ActivityRecords>();
		Integer countNum = (Integer) this.getSqlMapClientTemplate()
				.queryForObject(
						"activityRecord.countSearchActivityRecordsBySearchVo",
						activityRecordsVo);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<ActivityRecords> list = this.getSqlMapClientTemplate()
					.queryForList(
							"activityRecord.searchActivityRecordsBySearchVo",
							activityRecordsVo, (pageNum - 1) * pageSize,
							pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<ActivityRecords>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public Integer countActivityRecordByOrgIdOrgInternalCode(List<Long> orgIdList, String organizationType) {
		if(orgIdList==null || orgIdList.size()==0){
			return 0; 
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgIdsList", ParametersConvertUtil.convertToListString(orgIdList));
		map.put("organizationType", organizationType);
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"activityRecord.countActivityRecordByOrgIdOrgInternalCode",
						map);
	}

	@Override
	public List<ActivityRecords> findAllActivityRecordByOrganizationIdAndOrganizationType(
			Long organizationId, String organizationType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("organizationType", organizationType);
		map.put("organizationId", organizationId);
		List<ActivityRecords> list = getSqlMapClientTemplate()
				.queryForList(
						"activityRecord.findAllActivityRecordByOrganizationIdAndOrganizationType",
						map);
		return list;
	}

}
