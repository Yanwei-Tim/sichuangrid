package com.tianque.fourTeams.fourTeamsIssue.service.impl;

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
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueMessageRemindDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueMessageRemind;
import com.tianque.fourTeams.fourTeamsIssue.domain.vo.SearchFourTeamsIssueMessageRemindVo;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueMessageRemindService;

/**
 * 四支队伍事件类型越级短信提醒:业务逻辑层
 * 
 * @author
 * 
 */
@Repository("fourTeamsIssueMessageRemindService")
public class FourTeamsIssueMessageRemindServiceImpl
		extends
		BaseServiceImpl<FourTeamsIssueMessageRemind, SearchFourTeamsIssueMessageRemindVo>
		implements FourTeamsIssueMessageRemindService {

	@Autowired
	@Qualifier("FourTeamsIssueMessageRemindValidator")
	private DomainValidator<FourTeamsIssueMessageRemind> domainValidator;

	@Resource(name = "fourTeamsIssueMessageRemindDao")
	public void setBaseDao(FourTeamsIssueMessageRemindDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public FourTeamsIssueMessageRemind add(
			FourTeamsIssueMessageRemind fourTeamsissueMessageRemind) {
		fourTeamsIssueMessageRemindValidator(fourTeamsissueMessageRemind);
		try {
			fourTeamsissueMessageRemind = getBaseDao().add(
					fourTeamsissueMessageRemind);
			return fourTeamsissueMessageRemind;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类IssueMessageRemindServiceImpl的add方法出现异常，原因：",
					"新增事件类型越级短信提醒信息出现错误", e);
		}
	}

	@Override
	public FourTeamsIssueMessageRemind update(
			FourTeamsIssueMessageRemind fourTeamsIssueMessageRemind) {
		fourTeamsIssueMessageRemindValidator(fourTeamsIssueMessageRemind);
		try {
			fourTeamsIssueMessageRemind = getBaseDao().update(
					fourTeamsIssueMessageRemind);
			return fourTeamsIssueMessageRemind;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类IssueMessageRemindServiceImpl的update方法出现异常，原因：",
					"更新事件类型越级短信提醒信息出现错误", e);
		}
	}

	private void fourTeamsIssueMessageRemindValidator(
			FourTeamsIssueMessageRemind fourTeamsissueMessageRemind) {
		ValidateResult baseDataValidator = domainValidator
				.validate(fourTeamsissueMessageRemind);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public void deleteFourTeamsIssueMessageRemindBySkipRuleId(
			Long fourTeamsIssueSkipRuleId) {
		if (fourTeamsIssueSkipRuleId == null) {
			throw new BusinessValidationException("参数无效!");
		}
		((FourTeamsIssueMessageRemindDao) getBaseDao())
				.deleteFourTeamsIssueMessageRemindBySkipRuleId(fourTeamsIssueSkipRuleId);
	}

	@Override
	public List<FourTeamsIssueMessageRemind> findFourTeamsIssueMessageRemindListBySkipRuleId(
			Long fourTeamsIssueSkipRuleId) {
		if (fourTeamsIssueSkipRuleId == null) {
			throw new BusinessValidationException("参数无效!");
		}
		return ((FourTeamsIssueMessageRemindDao) getBaseDao())
				.findFourTeamsIssueMessageRemindListBySkipRuleId(fourTeamsIssueSkipRuleId);
	}

}
