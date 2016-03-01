package com.tianque.fourTeams.fourTeamsIssue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueStandardForFunOrgDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStandardForFunOrg;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueStandardForFunOrgService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Service("fourTeamsIssueStandardForFunOrgService")
public class FourTeamsIssueStandardForFunOrgServiceImpl implements
		FourTeamsIssueStandardForFunOrgService {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private FourTeamsIssueStandardForFunOrgDao issueStandardForFunOrgDao;

	@Override
	public FourTeamsIssueStandardForFunOrg getIssueStandardForFunOrgById(Long id) {
		try {
			if (id == null) {
				return new FourTeamsIssueStandardForFunOrg();
			} else {
				return issueStandardForFunOrgDao
						.getIssueStandardForFunOrgById(id);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("获取信息失败", e);
		}
	}

	@Override
	public FourTeamsIssueStandardForFunOrg addIssueStandardForFunOrg(
			FourTeamsIssueStandardForFunOrg issueStandardForFunOrg) {
		try {
			if (issueStandardForFunOrg == null) {
				return new FourTeamsIssueStandardForFunOrg();
			} else {
				Organization organization = organizationDubboService
						.getFullOrgById(ThreadVariable.getOrganization()
								.getId());
				issueStandardForFunOrg.setUserLevel(organization.getOrgLevel()
						.getId());
				return issueStandardForFunOrgDao
						.addIssueStandardForFunOrg(issueStandardForFunOrg);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("插入数据库异常", e);
		}
	}

	@Override
	public FourTeamsIssueStandardForFunOrg updateIssueStandardForFunOrg(
			FourTeamsIssueStandardForFunOrg issueStandardForFunOrg) {
		try {
			if (issueStandardForFunOrg == null) {
				return new FourTeamsIssueStandardForFunOrg();
			} else {
				return issueStandardForFunOrgDao
						.updateIssueStandardForFunOrg(issueStandardForFunOrg);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("更新数据库异常", e);
		}
	}

	@Override
	public PageInfo<FourTeamsIssueStandardForFunOrg> findIssueStandardForFunOrgsForList(
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		try {
			Organization organization = organizationDubboService
					.getFullOrgById(ThreadVariable.getOrganization().getId());

			return issueStandardForFunOrgDao
					.findIssueStandardForFunOrgsForList(pageNum, pageSize,
							sidx, sord, organization.getOrgLevel().getId());
		} catch (Exception e) {
			throw new ServiceValidationException("查询数据库异常", e);
		}
	}

	@Override
	public boolean deleteIssueStandardForFunOrgByIds(Long[] ids) {
		try {
			return issueStandardForFunOrgDao
					.deleteIssueStandardForFunOrgByIds(ids);
		} catch (Exception e) {
			throw new ServiceValidationException("删除数据库异常", e);
		}
	}

	@Override
	public FourTeamsIssueStandardForFunOrg getIssueStandardForFunOrgByOrgIdAndItemId(
			Long orgId, Long itemId) {
		try {
			return issueStandardForFunOrgDao
					.getIssueStandardForFunOrgByOrgIdAndItemId(orgId, itemId);
		} catch (Exception e) {
			throw new ServiceValidationException("删除数据库异常", e);
		}
	}

	@Override
	public List<FourTeamsIssueStandardForFunOrg> findItemTypeByFunOrgId(
			Long funOrgId) {
		try {
			return issueStandardForFunOrgDao.findItemTypeByFunOrgId(funOrgId);
		} catch (Exception e) {
			throw new ServiceValidationException("查询数据库异常", e);
		}
	}

	@Override
	public boolean validateRepeatByOrgIdAndItemName(
			FourTeamsIssueStandardForFunOrg issueStandardForFunOrg) {
		return issueStandardForFunOrgDao
				.validateRepeatByOrgIdAndItemName(issueStandardForFunOrg);
	}
}
