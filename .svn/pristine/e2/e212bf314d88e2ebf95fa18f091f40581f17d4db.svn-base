package com.tianque.fourTeams.fourTeamsIssue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueRelatedPeopleDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueRelatedPeople;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueRelatedPeopleService;

@Transactional
@Service("fourTeamsIssueRelatedPeopleService")
public class FourTeamsIssueRelatedPeopleServiceImpl implements
		FourTeamsIssueRelatedPeopleService {

	@Autowired
	private FourTeamsIssueRelatedPeopleDao issueRelatedPeopleDao;

	@Override
	public void addIssueRelatedPeople(
			FourTeamsIssueRelatedPeople issueRelatedPeople) {
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
	public List<FourTeamsIssueRelatedPeople> findIssueRelatedPeopleByIssueId(
			Long issueId) {
		if (null == issueId) {
			throw new BusinessValidationException("参数错误");
		}
		return issueRelatedPeopleDao.findIssueRelatedPeopleByIssueId(issueId);
	}

	private void validatorIssueRelatedPeople(
			FourTeamsIssueRelatedPeople issueRelatedPeople) {
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
