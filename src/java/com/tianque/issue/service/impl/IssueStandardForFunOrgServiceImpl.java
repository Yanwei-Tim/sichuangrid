package com.tianque.issue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.issue.dao.IssueStandardForFunOrgDao;
import com.tianque.issue.domain.IssueStandardForFunOrg;
import com.tianque.issue.service.IssueStandardForFunOrgService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Service("issueStandardForFunOrgService")
public class IssueStandardForFunOrgServiceImpl implements
		IssueStandardForFunOrgService {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private IssueStandardForFunOrgDao issueStandardForFunOrgDao;

	@Override
	public IssueStandardForFunOrg getIssueStandardForFunOrgById(Long id) {
		try {
			if (id == null) {
				return new IssueStandardForFunOrg();
			} else {
				return issueStandardForFunOrgDao
						.getIssueStandardForFunOrgById(id);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("获取信息失败", e);
		}
	}

	@Override
	public IssueStandardForFunOrg addIssueStandardForFunOrg(
			IssueStandardForFunOrg issueStandardForFunOrg) {
		if (issueStandardForFunOrg == null)
			throw new BusinessValidationException("新增数据时发生错误");

		if (issueStandardForFunOrg.getRedLimitAccept() != null
				&& issueStandardForFunOrg.getYellowLimitAccept() != null) {
			if (issueStandardForFunOrg.getRedLimitAccept() <= issueStandardForFunOrg
					.getYellowLimitAccept()) {
				throw new BusinessValidationException("红牌受理时限需大于黄牌受理时限");
			}
		}
		if (issueStandardForFunOrg.getRedLimitHandle() != null
				&& issueStandardForFunOrg.getYellowLimitHandle() != null) {
			if (issueStandardForFunOrg.getRedLimitHandle() <= issueStandardForFunOrg
					.getYellowLimitHandle()) {
				throw new BusinessValidationException("红牌办理时限需大于黄牌办理时限");
			}
		}
		if (issueStandardForFunOrg.getRedLimitVerify() != null
				&& issueStandardForFunOrg.getYellowLimitVerify() != null) {
			if (issueStandardForFunOrg.getRedLimitVerify() <= issueStandardForFunOrg
					.getYellowLimitVerify()) {
				throw new BusinessValidationException("红牌验证时限需大于黄牌验证时限");
			}
		}
		Organization organization = organizationDubboService
				.getFullOrgById(ThreadVariable.getOrganization().getId());
		issueStandardForFunOrg.setUserLevel(organization.getOrgLevel().getId());
		return issueStandardForFunOrgDao
				.addIssueStandardForFunOrg(issueStandardForFunOrg);
	}

	@Override
	public IssueStandardForFunOrg updateIssueStandardForFunOrg(
			IssueStandardForFunOrg issueStandardForFunOrg) {
		if (issueStandardForFunOrg == null)
			throw new BusinessValidationException("更新数据时发生错误");
		if (issueStandardForFunOrg.getRedLimitAccept() != null
				&& issueStandardForFunOrg.getYellowLimitAccept() != null) {
			if (issueStandardForFunOrg.getRedLimitAccept() <= issueStandardForFunOrg
					.getYellowLimitAccept()) {
				throw new BusinessValidationException("红牌受理时限需大于黄牌受理时限");
			}
		}
		if (issueStandardForFunOrg.getRedLimitHandle() != null
				&& issueStandardForFunOrg.getYellowLimitHandle() != null) {
			if (issueStandardForFunOrg.getRedLimitHandle() <= issueStandardForFunOrg
					.getYellowLimitHandle()) {
				throw new BusinessValidationException("红牌办理时限需大于黄牌办理时限");
			}
		}
		if (issueStandardForFunOrg.getRedLimitVerify() != null
				&& issueStandardForFunOrg.getYellowLimitVerify() != null) {
			if (issueStandardForFunOrg.getRedLimitVerify() <= issueStandardForFunOrg
					.getYellowLimitVerify()) {
				throw new BusinessValidationException("红牌验证时限需大于黄牌验证时限");
			}
		}
		return issueStandardForFunOrgDao
				.updateIssueStandardForFunOrg(issueStandardForFunOrg);
	}

	@Override
	public PageInfo<IssueStandardForFunOrg> findIssueStandardForFunOrgsForList(
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
	public IssueStandardForFunOrg getIssueStandardForFunOrgByOrgIdAndItemId(
			Long orgId, Long itemId) {
		try {
			return issueStandardForFunOrgDao
					.getIssueStandardForFunOrgByOrgIdAndItemId(orgId, itemId);
		} catch (Exception e) {
			throw new ServiceValidationException("删除数据库异常", e);
		}
	}

	@Override
	public List<IssueStandardForFunOrg> findItemTypeByFunOrgId(Long funOrgId) {
		try {
			return issueStandardForFunOrgDao.findItemTypeByFunOrgId(funOrgId);
		} catch (Exception e) {
			throw new ServiceValidationException("查询数据库异常", e);
		}
	}

	@Override
	public boolean validateRepeatByOrgIdAndItemName(
			IssueStandardForFunOrg issueStandardForFunOrg) {
		return issueStandardForFunOrgDao
				.validateRepeatByOrgIdAndItemName(issueStandardForFunOrg);
	}
}
