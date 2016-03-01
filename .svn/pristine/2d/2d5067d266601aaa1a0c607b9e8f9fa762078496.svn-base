package com.tianque.issueAbutmentJoint.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.issueAbutmentJoint.dao.IssueJointStepDAO;
import com.tianque.issueAbutmentJoint.domain.IssueJointStep;
import com.tianque.issueAbutmentJoint.service.IssueJointStepService;

/**
 * @Description:事件对接 步骤业务层实现类
 * @author zhangyouwei@hztian.com
 * @date: 2014-7-22 上午11:26:27
 */
@Service("issueJointStepService")
@Transactional
public class IssueJointStepServiceImpl implements IssueJointStepService {
	public final static Logger logger = LoggerFactory
			.getLogger(IssueJointStepServiceImpl.class);
	@Autowired
	IssueJointStepDAO issueJointStepDAO;

	@Override
	public IssueJointStep addIssueJointStepByIssueJointByImport(
			IssueJointStep issueJointStep) {
		if (issueJointStep == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			Long id = issueJointStepDAO
					.addIssueJointStepByIssueJointByImport(issueJointStep);
			IssueJointStep result = getIssueJointStepById(id);
			return result;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"IssueJointStepServiceImpl类addIssueJointStepByIssueJointByImport方法错误",
					"新增导入事件步骤错误", e);
		}
	}

	@Override
	public IssueJointStep getIssueJointStepById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			return issueJointStepDAO.getIssueJointStepById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"IssueJointStepServiceImpl类getIssueJointStepById方法错误",
					"查询导入事件步骤错误", e);
		}
	}

}
