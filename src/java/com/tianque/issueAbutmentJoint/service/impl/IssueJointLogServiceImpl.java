package com.tianque.issueAbutmentJoint.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.issueAbutmentJoint.dao.IssueJointLogDAO;
import com.tianque.issueAbutmentJoint.domain.IssueJointLog;
import com.tianque.issueAbutmentJoint.service.IssueJointLogService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * @Description: 事件对接 日志业务层实现类
 * @author zhangyouwei@hztian.com
 * @date: 2014-7-22 上午11:25:18
 */
@Service("issueJointLogService")
@Transactional
public class IssueJointLogServiceImpl implements IssueJointLogService {
	public final static Logger logger = LoggerFactory
			.getLogger(IssueJointLogServiceImpl.class);
	@Autowired
	private IssueJointLogDAO issueJointLogDAO;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public IssueJointLog addIssueJointLogByIssueJointByImport(
			IssueJointLog issueJointLog) {
		if (issueJointLog == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			Long id = issueJointLogDAO
					.addIssueJointLogByIssueJointByImport(issueJointLog);
			return this.getIssueJointLogById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"IssueJointLogServiceImpl类的addIssueJointLogByIssueJointByImport方法错误",
					"新增对接事件日志错误", e);
		}
	}

	@Override
	public List<IssueJointLog> queryIssueJointLogByIssueJointIdForList(
			Long issueJointId) {
		if (issueJointId == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			List<IssueJointLog> issueJointLogs = issueJointLogDAO
					.queryIssueJointLogByIssueJointIdForList(issueJointId);
			fillIssueJointLogDealOrg(issueJointLogs);
			return issueJointLogs;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"IssueJointLogServiceImpl类的queryIssueJointLogByIssueJointIdForList方法错误",
					"根据对接事件查询对接事件日志错误", e);
		}
	}

	/**
	 * 填充处理部门信息【因为新增和办结都是同一部门所以只查一次】
	 * 
	 * @param issueJointLogs
	 */
	private void fillIssueJointLogDealOrg(List<IssueJointLog> issueJointLogs) {
		if (issueJointLogs != null && issueJointLogs.size() > 0) {
			Organization organization = organizationDubboService
					.getSimpleOrgById(issueJointLogs.get(0).getDealOrg()
							.getId());
			for (IssueJointLog issueJointLog : issueJointLogs) {
				issueJointLog.setDealOrg(organization);
			}
		}

	}

	@Override
	public IssueJointLog getIssueJointLogById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			return issueJointLogDAO.getIssueJointLogById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"IssueJointLogServiceImpl类的getIssueJointLogById方法错误",
					"根据对接事件日志Id查询对接事件日志错误", e);
		}
	}

}
