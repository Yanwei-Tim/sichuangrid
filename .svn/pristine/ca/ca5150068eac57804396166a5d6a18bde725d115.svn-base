package com.tianque.partyBuilding.doubleRegActivities.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.partyBuilding.doubleRegActivities.dao.VolunteerJobsDao;
import com.tianque.partyBuilding.doubleRegActivities.domain.VolunteerJobs;
import com.tianque.partyBuilding.doubleRegActivities.domain.Vo.SearchVolunteerJobsVo;

/**
 * 志愿者岗位表:数据操作层
 * 
 * @author
 * @date 2014-02-27 10:06:23
 */
@Repository("volunteerJobsDao")
public class VolunteerJobsDaoImpl extends
		BaseDaoImpl<VolunteerJobs, SearchVolunteerJobsVo> implements
		VolunteerJobsDao {

	@Override
	public VolunteerJobs getVolunteerJobsByNameAndOrgId(String name, Long orgId) {
		if (name == null || orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("orgId", orgId);
		return (VolunteerJobs) getSqlMapClientTemplate().queryForObject(
				"volunteerJobs.getVolunteerJobsByNameAndOrgId", map);
	}

	@Override
	public Integer countClaimedByIdAndOrgId(Long id, Long orgId) {
		if (id == null || orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("orgId", orgId);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"volunteerJobs.countClaimedByIdAndOrgId", map);
	}

	@Override
	public void deleteVolunteerJobsByIds(Long[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idList", ids);
		getSqlMapClientTemplate().delete(
				"volunteerJobs.deleteVolunteerJobsByIds", map);
	}

}
