package com.tianque.issue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.issue.dao.IssueMessageRemindDao;
import com.tianque.issue.domain.IssueMessageRemind;
import com.tianque.issue.domain.vo.SearchIssueMessageRemindVo;
import com.tianque.issue.service.IssueMessageRemindService;

/**
 * 事件类型越级短信提醒:业务逻辑层
 * 
 * @author
 * @date 2013-11-22 14:51:42
 */
@Repository("issueMessageRemindService")
public class IssueMessageRemindServiceImpl extends
		BaseServiceImpl<IssueMessageRemind, SearchIssueMessageRemindVo>
		implements IssueMessageRemindService {

	@Autowired
	@Qualifier("IssueMessageRemindValidator")
	private DomainValidator<IssueMessageRemind> domainValidator;

	@Resource(name = "issueMessageRemindDao")
	public void setBaseDao(IssueMessageRemindDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public IssueMessageRemind add(IssueMessageRemind issueMessageRemind) {
		issueMessageRemindValidator(issueMessageRemind);
		try {
			issueMessageRemind = getBaseDao().add(issueMessageRemind);
			return issueMessageRemind;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类IssueMessageRemindServiceImpl的add方法出现异常，原因：",
					"新增事件类型越级短信提醒信息出现错误", e);
		}
	}

	@Override
	public IssueMessageRemind update(IssueMessageRemind issueMessageRemind) {
		issueMessageRemindValidator(issueMessageRemind);
		try {
			issueMessageRemind = getBaseDao().update(issueMessageRemind);
			return issueMessageRemind;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类IssueMessageRemindServiceImpl的update方法出现异常，原因：",
					"更新事件类型越级短信提醒信息出现错误", e);
		}
	}

	private void issueMessageRemindValidator(
			IssueMessageRemind issueMessageRemind) {
		ValidateResult baseDataValidator = domainValidator
				.validate(issueMessageRemind);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public void deleteIssueMessageRemindBySkipRuleId(Long issueSkipRuleId) {
		if (issueSkipRuleId == null) {
			throw new BusinessValidationException("参数无效!");
		}
		((IssueMessageRemindDao) getBaseDao())
				.deleteIssueMessageRemindBySkipRuleId(issueSkipRuleId);
	}

	@Override
	public List<IssueMessageRemind> findIssueMessageRemindListBySkipRuleId(
			Long issueSkipRuleId) {
		if (issueSkipRuleId == null) {
			throw new BusinessValidationException("参数无效!");
		}
		return ((IssueMessageRemindDao) getBaseDao())
				.findIssueMessageRemindListBySkipRuleId(issueSkipRuleId);
	}

}
