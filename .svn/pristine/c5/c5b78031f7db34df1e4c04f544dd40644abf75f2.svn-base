package com.tianque.issueAbutmentJoint.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.util.StringUtil;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.issueAbutmentJoint.dao.IssueJointAttachfileDAO;
import com.tianque.issueAbutmentJoint.domain.IssueJointAttachfile;
import com.tianque.issueAbutmentJoint.service.IssueJointAttachfileService;

/***
 * @Description:事件对接已办结附件附件业务层实现
 * @author zhangyouwei@hztianque.com
 * @date: 2015-3-5 上午09:52:44
 */
@Service("issueJointAttachfileService")
public class IssueJointAttachfileServiceImpl implements
		IssueJointAttachfileService {

	@Autowired
	private IssueJointAttachfileDAO issueJointAttachfileDAO;

	@Override
	public List<IssueJointAttachfile> queryIssueJointAttachfileByIssueIdForList(
			Long issueId, String moduleKey) {
		if (issueId == null || !StringUtil.isStringAvaliable(moduleKey)) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("issueId", issueId);
			map.put("moduleKey", moduleKey);
			List<IssueJointAttachfile> issueJointAttachfiles = issueJointAttachfileDAO
					.queryIssueJointAttachfileByIssueIdForList(map);
			return issueJointAttachfiles;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"IssueJointAttachfileServiceImpl类的queryIssueJointAttachfileByIssueIdForList方法错误",
					"根据对接事件查询对接事件附件错误", e);
		}
	}

	@Override
	public IssueJointAttachfile findIssueJointAttachfileById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {

			IssueJointAttachfile issueJointAttachfile = issueJointAttachfileDAO
					.getIssueJointAttachfileById(id);
			return issueJointAttachfile;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"IssueJointAttachfileServiceImpl类的findIssueJointAttachfileById方法错误",
					"根据id查询对接事件附件错误", e);
		}
	}

}
