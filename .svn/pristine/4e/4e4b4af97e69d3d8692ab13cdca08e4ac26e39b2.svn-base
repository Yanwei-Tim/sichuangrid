package com.tianque.issue.dao;

import java.util.List;

import com.tianque.core.base.BaseDao;
import com.tianque.issue.domain.IssueSkiprule;
import com.tianque.issue.domain.vo.SearchIssueSkipruleVo;

/**
 * 事件类型越级规则设置:数据操作层接口
 * 
 * @author
 * @date 2013-11-22 14:51:42
 */
public interface IssueSkipruleDao extends
		BaseDao<IssueSkiprule, SearchIssueSkipruleVo> {

	public IssueSkiprule getIssueSkipruleByRules(IssueSkiprule issueSkiprule);
	
	public void updateIssueSkipRuleStatus(List<Long> ids,int status);

	public List<IssueSkiprule> findIssueSkiprulesByRules(
			IssueSkiprule issueSkiprule);
}
