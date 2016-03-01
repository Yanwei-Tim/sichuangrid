package com.tianque.issue.service;

import java.util.List;

import com.tianque.core.base.BaseService;
import com.tianque.issue.domain.IssueMessageRemind;
import com.tianque.issue.domain.vo.SearchIssueMessageRemindVo;

/**
 * 事件类型越级短信提醒:业务逻辑层接口
 * 
 * @author
 * @date 2013-11-22 14:51:42
 */
public interface IssueMessageRemindService extends
		BaseService<IssueMessageRemind, SearchIssueMessageRemindVo> {

	public void deleteIssueMessageRemindBySkipRuleId(Long issueSkipRuleId);

	public List<IssueMessageRemind> findIssueMessageRemindListBySkipRuleId(
			Long issueSkipRuleId);

}
