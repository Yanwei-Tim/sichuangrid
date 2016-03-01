package com.tianque.fourTeams.fourTeamsIssue.dao;

import java.util.List;

import com.tianque.core.base.BaseDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueMessageRemind;
import com.tianque.fourTeams.fourTeamsIssue.domain.vo.SearchFourTeamsIssueMessageRemindVo;

/**
 * 四支队伍事件类型越级短信提醒:数据操作层接口
 * 
 * @author
 * 
 */
public interface FourTeamsIssueMessageRemindDao
		extends
		BaseDao<FourTeamsIssueMessageRemind, SearchFourTeamsIssueMessageRemindVo> {

	public void deleteFourTeamsIssueMessageRemindBySkipRuleId(
			Long fourTeamsIssueSkipRuleId);

	public List<FourTeamsIssueMessageRemind> findFourTeamsIssueMessageRemindListBySkipRuleId(
			Long fourTeamsIssueSkipRuleId);
}
