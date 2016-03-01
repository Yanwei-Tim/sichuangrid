package com.tianque.issue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.issue.dao.IssueRelatedPeopleDao;
import com.tianque.issue.domain.IssueRelatedPeople;
import com.tianque.issue.service.IssueRelatedPeopleService;

@Transactional
@Service("issueRelatedPeopleService")
public class IssueRelatedPeopleServiceImpl implements IssueRelatedPeopleService {

	@Autowired
	private IssueRelatedPeopleDao issueRelatedPeopleDao;

	@Override
	public void addIssueRelatedPeople(IssueRelatedPeople issueRelatedPeople) {
		validatorIssueRelatedPeople(issueRelatedPeople);
		issueRelatedPeopleDao.addIssueRelatedPeople(issueRelatedPeople);
	}

	@Override
	public void deleteIssueRelatedPeopleByIssueId(Long issueId) {
		if (null == issueId) {
			throw new BusinessValidationException("参数错误");
		}
		issueRelatedPeopleDao.deleteIssueRelatedPeopleByIssueId(issueId);

	}

	@Override
	public List<IssueRelatedPeople> findIssueRelatedPeopleByIssueId(Long issueId) {
		if (null == issueId) {
			throw new BusinessValidationException("参数错误");
		}
		return issueRelatedPeopleDao.findIssueRelatedPeopleByIssueId(issueId);
	}

	private void validatorIssueRelatedPeople(
			IssueRelatedPeople issueRelatedPeople) {
		if (null == issueRelatedPeople) {
			throw new BusinessValidationException("参数错误");
		}
		if (null == issueRelatedPeople.getIssue()
				|| null == issueRelatedPeople.getIssue().getId()) {
			throw new BusinessValidationException("事件ID不能为空");
		}
		if (null == issueRelatedPeople.getName()) {
			throw new BusinessValidationException("姓名不能为空");
		}
		if (null == issueRelatedPeople.getTelephone()) {
			throw new BusinessValidationException("联系电话不能为空");
		}
	}

}
