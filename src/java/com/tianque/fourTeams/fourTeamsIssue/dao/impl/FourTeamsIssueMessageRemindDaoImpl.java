package com.tianque.fourTeams.fourTeamsIssue.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueMessageRemindDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueMessageRemind;
import com.tianque.fourTeams.fourTeamsIssue.domain.vo.SearchFourTeamsIssueMessageRemindVo;

/**
 * 四支队伍事件类型越级短信提醒:数据操作层
 * 
 * @author
 * 
 */
@Repository("fourTeamsIssueMessageRemindDao")
public class FourTeamsIssueMessageRemindDaoImpl
		extends
		BaseDaoImpl<FourTeamsIssueMessageRemind, SearchFourTeamsIssueMessageRemindVo>
		implements FourTeamsIssueMessageRemindDao {

	@Override
	public void deleteFourTeamsIssueMessageRemindBySkipRuleId(
			Long fourTeamsIssueSkipRuleId) {
		getSqlMapClientTemplate()
				.delete(
						"fourTeamsIssueMessageRemind.deleteFourTeamsIssueMessageRemindBySkipRuleId",
						fourTeamsIssueSkipRuleId);
	}

	@Override
	public List<FourTeamsIssueMessageRemind> findFourTeamsIssueMessageRemindListBySkipRuleId(
			Long fourTeamsIssueSkipRuleId) {
		return getSqlMapClientTemplate()
				.queryForList(
						"fourTeamsIssueMessageRemind.findFourTeamsIssueMessageRemindListBySkipRuleId",
						fourTeamsIssueSkipRuleId);
	}

}
