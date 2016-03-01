package com.tianque.fourTeams.fourTeamsIssue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.fourTeams.fourTeamsIssue.constants.FourTeamsIssueApplyDelayState;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueApplyDelayDao;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueDao;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueProcessDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueApplyDelay;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueSkiprule;
import com.tianque.fourTeams.fourTeamsIssue.factory.FourTeamsIssueServiceFactory;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueApplyDelayService;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueSkipruleService;
import com.tianque.sysadmin.service.PermissionService;

/**
 * @ClassName: IssueApplyDelayServiceImpl
 * @Description: 申请延期服务接口实现类
 * @author wangxiaohu wsmalltiger@163.com
 * @date Nov 25, 2013 3:59:42 PM
 */
@Transactional
@Service("fourTeamsIssueApplyDelayService")
public class FourTeamsIssueApplyDelayServiceImpl implements
		FourTeamsIssueApplyDelayService {
	@Autowired
	private FourTeamsIssueApplyDelayDao issueApplyDelayDao;
	@Autowired
	private FourTeamsIssueProcessDao issueProcessDao;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private FourTeamsIssueServiceFactory issueServiceFactory;
	@Autowired
	private FourTeamsIssueSkipruleService issueSkipruleService;
	@Autowired
	private FourTeamsIssueDao issueDao;

	@Override
	public FourTeamsIssueApplyDelay applyDelay(
			FourTeamsIssueApplyDelay issueApplyDelay) {
		checkIssueApplyDelay(issueApplyDelay);
		issueApplyDelay.setApplyUser(ThreadVariable.getUser());
		autoFillAuditOrg(issueApplyDelay);
		issueApplyDelay.setAuditState(FourTeamsIssueApplyDelayState.APPIYING);
		issueApplyDelay = issueApplyDelayDao.saveApplyDelay(issueApplyDelay);
		issueProcessDao.updateDelayStateOrDelayDateByIssueStepId(
				issueApplyDelay.getIssueStepId(),
				FourTeamsIssueApplyDelayState.APPIYING, null);
		return issueApplyDelay;
	}

	private void autoFillAuditOrg(FourTeamsIssueApplyDelay issueApplyDelay) {
		FourTeamsIssueNew issueNew = issueServiceFactory
				.getIssueServiceBySeries(
						FourTeamsIssueServiceFactory.DEFAULT_SERIES)
				.getFullIssueByStepId(issueApplyDelay.getIssueStepId());
		if (issueNew.getEmergencyLevel() == null) {
			issueApplyDelay.setAuditOrg(ThreadVariable.getOrganization()
					.getParentOrg());
		} else {
			FourTeamsIssueSkiprule issueSkiprule = issueSkipruleService
					.getFourTeamsIssueSkipruleByIssue(issueNew);
			if (issueSkiprule == null) {
				issueApplyDelay.setAuditOrg(ThreadVariable.getOrganization()
						.getParentOrg());
			} else {
				Organization org = new Organization();
				org.setId(issueSkiprule.getSubmitOrgId());
				issueApplyDelay.setAuditOrg(org);
			}
		}
	}

	private void checkIssueApplyDelay(FourTeamsIssueApplyDelay issueApplyDelay) {
		if (issueApplyDelay == null || issueApplyDelay.getIssueStepId() == null) {
			throw new BusinessValidationException("参数错误");
		}
	}

	@Override
	public PageInfo<FourTeamsIssueApplyDelay> findIssueDelayList(
			Long issueStepId, Integer page, Integer rows, String sidx,
			String sord) {
		PageInfo<FourTeamsIssueApplyDelay> pageInfo = issueApplyDelayDao
				.findIssueDelayList(issueStepId, page, rows, sidx, sord);
		if (pageInfo == null || pageInfo.getResult() == null
				|| pageInfo.getResult().size() == 0) {
			return pageInfo;
		}
		for (FourTeamsIssueApplyDelay issueApplyDelay : pageInfo.getResult()) {
			if (issueApplyDelay.getApplyUser() != null) {
				issueApplyDelay.setApplyUser(permissionService
						.getSimpleUserById(issueApplyDelay.getApplyUser()
								.getId()));
			}
			if (issueApplyDelay.getAuditUser() != null) {
				issueApplyDelay.setAuditUser(permissionService
						.getSimpleUserById(issueApplyDelay.getAuditUser()
								.getId()));
			}
		}
		return pageInfo;
	}

	@Override
	public FourTeamsIssueApplyDelay getIssueApplyDelayByIssueStepIdAndDelayState(
			Long issueStepId) {
		return issueApplyDelayDao
				.getIssueApplyDelayByIssueStepIdAndDelayState(issueStepId);
	}

	@Override
	public FourTeamsIssueApplyDelay auditDelay(
			FourTeamsIssueApplyDelay issueApplyDelay) {
		resetPermitDelayWorkday(issueApplyDelay);
		issueApplyDelay.setAuditUser(ThreadVariable.getUser());
		issueProcessDao.updateDelayStateOrDelayDateByIssueStepId(
				issueApplyDelay.getIssueStepId(),
				issueApplyDelay.getAuditState(),
				issueApplyDelay.getPermitDelayWorkday());
		return issueApplyDelayDao.auditDelay(issueApplyDelay);
	}

	private void resetPermitDelayWorkday(
			FourTeamsIssueApplyDelay issueApplyDelay) {
		if (issueApplyDelay.getAuditState() == null
				|| issueApplyDelay.getAuditState() == FourTeamsIssueApplyDelayState.APPLY_PASSING) {
			issueApplyDelay.setPermitDelayWorkday(0);
		}
	}

	@Override
	public int getJurisdictionsAuditDelayCount(Long orgId) {
		if (null == orgId) {
			throw new BusinessValidationException("参数错误");
		}
		return issueDao.getJurisdictionsAuditDelayCount(orgId);
	}
}
