package com.tianque.plugin.serviceTeam.serviceRecord.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.UsedInfo;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecord;
import com.tianque.plugin.serviceTeam.serviceRecord.vo.ServiceRecordVo;

@Repository("serviceRecordDao")
public class ServiceRecordDaoImpl extends AbstractBaseDao implements
		ServiceRecordDao {

	@Override
	public Long addServiceRecord(ServiceRecord serviceRecord) {
		return (Long) getSqlMapClientTemplate().insert(
				"serviceRecord.addServiceRecord", serviceRecord);
	}

	@Override
	public ServiceRecordVo getServiceRecordById(Long id) {

		return (ServiceRecordVo) getSqlMapClientTemplate().queryForObject(
				"serviceRecord.getServiceRecordById", id);
	}

	private Map<String, Object> creatServiceRecordsMap(
			ServiceRecordVo serviceRecordVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("displayLevel", serviceRecordVo.getDisplayLevel());
		map.put("organization", serviceRecordVo.getOrganization());
		map.put("objectId", serviceRecordVo.getObjectId());
		map.put("occurDateStart", serviceRecordVo.getOccurDateStart());
		map.put("occurDateEnd", serviceRecordVo.getOccurDateEnd());

		map.put("recordAddDateStart", serviceRecordVo.getRecordAddDateStart());
		map.put("recordAddDateEnd", serviceRecordVo.getRecordAddDateEnd());

		map.put("occurPlace", serviceRecordVo.getOccurPlace());
		map.put("serviceMembers", serviceRecordVo.getServiceMembers());
		map.put("serviceJoiners", serviceRecordVo.getServiceJoiners());
		map.put("serviceObjects", serviceRecordVo.getServiceObjects());
		map.put("serviceContent", serviceRecordVo.getServiceContent());
		map.put("recordType", serviceRecordVo.getRecordType());
		map.put("objectTypeList", serviceRecordVo.getObjectTypeList());
		return map;

	}

	@Override
	public PageInfo<ServiceRecordVo> findServiceRecords(
			ServiceRecordVo serviceRecordVo, Integer pageNum, Integer pageSize) {
		Map<String, Object> map = creatServiceRecordsMap(serviceRecordVo);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceRecord.countFindServiceRecords", map);
		List<ServiceRecordVo> list = new ArrayList<ServiceRecordVo>();
		if (pageNum == null || pageSize == null) {
			list = getSqlMapClientTemplate().queryForList(
					"serviceRecord.findServiceRecords", map);
			return new PageInfo<ServiceRecordVo>(1, countNum, countNum, list);
		} else {
			list = getSqlMapClientTemplate().queryForList(
					"serviceRecord.findServiceRecords", map,
					(pageNum - 1) * pageSize, pageSize);
			return new PageInfo<ServiceRecordVo>(pageNum, pageSize, countNum,
					list);
		}

	}

	@Override
	public Integer countServiceRecords(ServiceRecordVo serviceRecordVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceRecord.countFindServiceRecords", serviceRecordVo);
	}

	@Override
	public ServiceRecordVo updateServiceRecord(ServiceRecord serviceRecord) {
		getSqlMapClientTemplate().update("serviceRecord.updateServiceRecord",
				serviceRecord);
		return getServiceRecordById(serviceRecord.getId());
	}

	@Override
	public int deleteServiceRecord(Long id) {
		return getSqlMapClientTemplate().delete(
				"serviceRecord.deleteServiceRecord", id);
	}

	@Override
	public Date getMinTime() {
		return (Date) getSqlMapClientTemplate().queryForObject(
				"serviceRecord.getMinTime");
	}

	@Override
	public Integer countServiceRecordsForTeam(Long id) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceRecord.countServiceTeamHasRecords", id);
	}

	@Override
	public Integer countServiceRecordsForTeamMember(Long id) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceRecord.countServiceRecordsForTeamMember", id);
	}

	@Override
	public List<ServiceRecordVo> getServiceRecordObjects(
			ServiceRecordVo serviceRecordVo) {
		return getSqlMapClientTemplate().queryForList(
				"serviceRecord.findServiceRecords", serviceRecordVo);
	}

	@Override
	public Integer findServiceTeamInServiceRecords(Long id) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceRecord.findServiceTeamInServiceRecords", id);
	}

	@Override
	public void updateMemberInRecord(ServiceRecord serviceRecord) {
		getSqlMapClientTemplate().update("serviceRecord.updateMemberInRecord",
				serviceRecord);

	}

	@Override
	public Integer getCount(ServiceRecordVo serviceRecordVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"serviceRecord.countFindServiceRecords", serviceRecordVo);
	}

	@Override
	public void updateServiceRecordsOrg(Long orgId, String orgCode,
			String objectType, Long objectId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("orgCode", orgCode);
		map.put("objectType", objectType);
		map.put("objectId", objectId);
		getSqlMapClientTemplate().update(
				"serviceRecord.updateServiceRecordsOrg", map);
	}

	@Override
	public void updateServiceRecordsOrgAndObejctId(Long orgId, String orgCode,
			String objectType, Long oldObjectId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("orgCode", orgCode);
		map.put("objectType", objectType);
		map.put("oldObjectId", oldObjectId);
		getSqlMapClientTemplate().update(
				"serviceRecord.updateServiceRecordsOrgAndObejctId", map);

	}

	@Override
	public void updateServiceRecordRelyObject(String objectType,
			Long oldObjectId, Long newObjectId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("objectType", objectType);
		map.put("oldObjectId", oldObjectId);
		map.put("newObjectId", newObjectId);
		getSqlMapClientTemplate().update(
				"serviceRecord.updateServiceRecordRelyObject", map);

	}

	@Override
	public void updateServiceRecordsOrgAndObejctId(Long orgId, String orgCode,
			String objectType, Long oldObjectId, String newObjectType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("orgCode", orgCode);
		map.put("objectType", objectType);
		map.put("oldObjectId", oldObjectId);
		map.put("newObjectType", newObjectType);
		getSqlMapClientTemplate()
				.update("serviceRecord.updateServiceRecordsOrgAndObejctIdAndObjectType",
						map);

	}

	@Override
	public void updateServiceRecordRelyObject(String objectType,
			Long oldObjectId, Long newObjectId, String newObjectType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("objectType", objectType);
		map.put("oldObjectId", oldObjectId);
		map.put("newObjectId", newObjectId);
		map.put("newObjectType", newObjectType);
		getSqlMapClientTemplate()
				.update("serviceRecord.updateServiceRecordRelyObjectAndObjectType",
						map);

	}

	@Override
	public void deleteServiceRecordHasObject(Long objectId, String objectType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("objectId", objectId);
		map.put("objectType", objectType);
		getSqlMapClientTemplate().delete(
				"serviceRecord.deleteServiceRecordHasObject", map);

	}

	@Override
	public List<UsedInfo> getSpecialCrowdCountForUsedInfo(
			Date beforDayStartDate, Date beforDayEndDate, Date beforWeekMonday,
			Date beforWeekSunday, Date monthStartDate, Date monthEndDate,
			Long orgId, Long orgTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beforDayStartDate", beforDayStartDate);
		map.put("beforDayEndDate", beforDayEndDate);
		map.put("beforWeekMonday", beforWeekMonday);
		map.put("beforWeekSunday", beforWeekSunday);
		map.put("monthStartDate", monthStartDate);
		map.put("monthEndDate", monthEndDate);
		map.put("orgId", orgId);
		map.put("orgTypeId", orgTypeId);
		return getSqlMapClientTemplate().queryForList(
				"serviceRecord.getSpecialCrowdCountForUsedInfo", map);
	}
}
