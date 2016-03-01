package com.tianque.partyBuilding.doubleRegActivities.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.partyBuilding.doubleRegActivities.dao.MemberHasVolunteerJobsDao;
import com.tianque.partyBuilding.doubleRegActivities.domain.MemberHasVolunteerJobs;
import com.tianque.partyBuilding.doubleRegActivities.service.MemberHasVolunteerJobsService;

@Transactional
@Service("memberHasVolunteerJobsService")
public class MemberHasVolunteerJobsServiceImpl extends AbstractBaseService
		implements MemberHasVolunteerJobsService {

	@Autowired
	private MemberHasVolunteerJobsDao memberHasVolunteerJobsDao;

	@Override
	public void addMemberHasVolunteerJobs(
			MemberHasVolunteerJobs memberHasVolunteerJobs) {
		try {
			if (memberHasVolunteerJobs == null) {
				throw new BusinessValidationException("对象为空");
			}
			memberHasVolunteerJobsDao
					.addMemberHasVolunteerJobs(memberHasVolunteerJobs);
		} catch (Exception e) {
			logger.error("异常", e);
		}

	}

	@Override
	public void deleteMemberHasVolunteerJobsById(Long id) {
		try {
			if (id == null) {
				throw new BusinessValidationException("参数为空");
			}
			memberHasVolunteerJobsDao.deleteMemberHasVolunteerJobsById(id);
		} catch (Exception e) {
			logger.error("异常", e);
		}
	}

	@Override
	public List<MemberHasVolunteerJobs> getMemberHasVolunteerJobsById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数为空");
		}
		return memberHasVolunteerJobsDao.getMemberHasVolunteerJobsById(id);
	}

	@Override
	public void deleteMemberHasVolunteerJobsByVolunteerJobsId(
			Long volunteerJobsId) {
		try {
			if (volunteerJobsId == null) {
				throw new BusinessValidationException("参数为空");
			}
			memberHasVolunteerJobsDao
					.deleteMemberHasVolunteerJobsByVolunteerJobsId(volunteerJobsId);
		} catch (Exception e) {
			logger.error("异常", e);
		}
	}

	@Override
	public void deleteMemberHasVolunteerJobsByVolunteerJobsIds(
			Long[] volunteerJobsIds) {
		try {
			if (volunteerJobsIds == null) {
				throw new BusinessValidationException("参数为空");
			}
			memberHasVolunteerJobsDao
					.deleteMemberHasVolunteerJobsByVolunteerJobsIds(volunteerJobsIds);
		} catch (Exception e) {
			logger.error("异常", e);
		}
	}

}
