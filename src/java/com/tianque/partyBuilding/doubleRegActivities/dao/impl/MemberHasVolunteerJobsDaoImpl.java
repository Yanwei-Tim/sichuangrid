package com.tianque.partyBuilding.doubleRegActivities.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.partyBuilding.doubleRegActivities.dao.MemberHasVolunteerJobsDao;
import com.tianque.partyBuilding.doubleRegActivities.domain.MemberHasVolunteerJobs;

@Repository("memberHasVolunteerJobsDao")
public class MemberHasVolunteerJobsDaoImpl extends AbstractBaseDao implements
		MemberHasVolunteerJobsDao {

	@Override
	public void addMemberHasVolunteerJobs(
			MemberHasVolunteerJobs memberHasVolunteerJobs) {
		getSqlMapClientTemplate().insert(
				"memberHasVolunteerJobs.addMemberHasVolunteerJobs",
				memberHasVolunteerJobs);
	}

	@Override
	public void deleteMemberHasVolunteerJobsById(Long id) {
		getSqlMapClientTemplate().delete(
				"memberHasVolunteerJobs.deleteMemberHasVolunteerJobsById", id);

	}

	@Override
	public List<MemberHasVolunteerJobs> getMemberHasVolunteerJobsById(Long id) {
		return getSqlMapClientTemplate().queryForList(
				"memberHasVolunteerJobs.getMemberHasVolunteerJobsById", id);
	}

	@Override
	public void deleteMemberHasVolunteerJobsByVolunteerJobsId(
			Long volunteerJobsId) {
		getSqlMapClientTemplate()
				.delete("memberHasVolunteerJobs.deleteMemberHasVolunteerJobsByVolunteerJobsId",
						volunteerJobsId);

	}

	@Override
	public void deleteMemberHasVolunteerJobsByVolunteerJobsIds(
			Long[] volunteerJobsIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("volunteerJobsIds", volunteerJobsIds);
		getSqlMapClientTemplate()
				.delete("memberHasVolunteerJobs.deleteMemberHasVolunteerJobsByVolunteerJobsIds",
						map);

	}

}
