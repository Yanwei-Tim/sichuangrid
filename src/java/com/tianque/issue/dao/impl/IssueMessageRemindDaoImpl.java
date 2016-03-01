package com.tianque.issue.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.issue.dao.IssueMessageRemindDao;
import com.tianque.issue.domain.IssueMessageRemind;
import com.tianque.issue.domain.vo.SearchIssueMessageRemindVo;

/**
 * 事件类型越级短信提醒:数据操作层
 * 
 * @author
 * @date 2013-11-22 14:51:42
 */
@Repository("issueMessageRemindDao")
public class IssueMessageRemindDaoImpl extends
		BaseDaoImpl<IssueMessageRemind, SearchIssueMessageRemindVo> implements
		IssueMessageRemindDao {

	@Override
	public void deleteIssueMessageRemindBySkipRuleId(Long issueSkipRuleId) {
		getSqlMapClientTemplate().delete(
				"issueMessageRemind.deleteIssueMessageRemindBySkipRuleId",
				issueSkipRuleId);
	}

	@Override
	public List<IssueMessageRemind> findIssueMessageRemindListBySkipRuleId(
			Long issueSkipRuleId) {
		return getSqlMapClientTemplate().queryForList(
				"issueMessageRemind.findIssueMessageRemindListBySkipRuleId",
				issueSkipRuleId);
	}

}
