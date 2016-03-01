package com.tianque.partyBuilding.doubleRegActivities.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.partyBuilding.doubleRegActivities.dao.VolunteerJobsDao;
import com.tianque.partyBuilding.doubleRegActivities.domain.VolunteerJobs;
import com.tianque.partyBuilding.doubleRegActivities.domain.Vo.SearchVolunteerJobsVo;
import com.tianque.partyBuilding.doubleRegActivities.service.MemberHasVolunteerJobsService;
import com.tianque.partyBuilding.doubleRegActivities.service.VolunteerJobsService;
import com.tianque.sysadmin.service.PermissionService;

/**
 * 志愿者岗位表:业务逻辑层
 * 
 * @author
 * @date 2014-02-27 10:06:23
 */
@Transactional
@Service("volunteerJobsService")
public class VolunteerJobsServiceImpl extends
		BaseServiceImpl<VolunteerJobs, SearchVolunteerJobsVo> implements
		VolunteerJobsService {

	private static Logger logger = LoggerFactory
			.getLogger(VolunteerJobsServiceImpl.class);

	@Autowired
	private VolunteerJobsDao volunteerJobsDao;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private MemberHasVolunteerJobsService memberHasVolunteerJobsService;

	@Autowired
	@Qualifier("volunteerJobsValidator")
	private DomainValidator<VolunteerJobs> domainValidator;

	@Resource(name = "volunteerJobsDao")
	public void setBaseDao(VolunteerJobsDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public VolunteerJobs add(VolunteerJobs volunteerjobs) {
		volunteerjobsValidator(volunteerjobs);
		try {
			volunteerjobs = getBaseDao().add(volunteerjobs);
			return volunteerjobs;
		} catch (Exception e) {
			return dealException("VolunteerJobsServiceImpl", "add",
					"新增志愿者岗位表信息出现错误", e);
		}
	}

	@Override
	public VolunteerJobs update(VolunteerJobs volunteerjobs) {
		volunteerjobsValidator(volunteerjobs);
		try {
			volunteerjobs = getBaseDao().update(volunteerjobs);
			return volunteerjobs;
		} catch (Exception e) {
			return dealException("VolunteerJobsServiceImpl", "update",
					"更新志愿者岗位表信息出现错误", e);
		}
	}

	private void volunteerjobsValidator(VolunteerJobs volunteerjobs) {
		ValidateResult baseDataValidator = domainValidator
				.validate(volunteerjobs);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public String hasDuplicateName(Long id, Long orgId, String name) {
		if (orgId == null || name == null) {
			throw new BusinessValidationException("参数错误");
		}
		VolunteerJobs getVolunteerJobs = volunteerJobsDao
				.getVolunteerJobsByNameAndOrgId(name, orgId);

		if (getVolunteerJobs != null && getVolunteerJobs.getId() != null
				&& !getVolunteerJobs.getId().equals(id)) {

			String userName = ThreadVariable.getUser().getUserName()
					.equals(getVolunteerJobs.getCreateUser()) ? "您"
					: permissionService.getFullUserByUerName(getVolunteerJobs
							.getCreateUser()) == null ? "" : permissionService
							.getFullUserByUerName(
									getVolunteerJobs.getCreateUser()).getName();
			return "当前组织机构(" + userName + ")下已存在相同的编号,请重新输入";
		} else {
			return null;
		}
	}

	@Override
	public Integer countClaimedByIdAndOrgId(Long id, Long orgId) {
		if (id == null || orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		return volunteerJobsDao.countClaimedByIdAndOrgId(id, orgId);
	}

	@Override
	public void deleteVolunteerJobsByIds(Long[] ids) {
		try {
			if (ids == null || ids.length == 0) {
				throw new BusinessValidationException("Id不能为空");
			}
			memberHasVolunteerJobsService
					.deleteMemberHasVolunteerJobsByVolunteerJobsIds(ids);// 根据志愿者岗位的id删除与党员报到关联的信息
			volunteerJobsDao.deleteVolunteerJobsByIds(ids);
		} catch (Exception e) {
			logger.error(
					"类VolunteerJobsServiceImpl的deleteVolunteerJobsByIds方法出现异常，原因：",
					e);
		}

	}
}
