package com.tianque.fourTeams.fourTeamsIssue.service;

import java.util.List;

import com.tianque.core.base.BaseService;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueMessageRemind;
import com.tianque.fourTeams.fourTeamsIssue.domain.vo.SearchFourTeamsIssueMessageRemindVo;

/**
 * 事件类型越级短信提醒:业务逻辑层接口
 * 
 * @author
 * 
 */
public interface FourTeamsIssueMessageRemindService
		extends
		BaseService<FourTeamsIssueMessageRemind, SearchFourTeamsIssueMessageRemindVo> {

	public void deleteFourTeamsIssueMessageRemindBySkipRuleId(
			Long fourTeamsissueSkipRuleId);

	public List<FourTeamsIssueMessageRemind> findFourTeamsIssueMessageRemindListBySkipRuleId(
			Long fourTeamsissueSkipRuleId);

}
