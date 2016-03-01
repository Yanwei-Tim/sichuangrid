package com.tianque.issue.service;

import java.util.List;

import com.tianque.core.base.BaseService;
import com.tianque.domain.PropertyDict;
import com.tianque.issue.domain.IssueMessageRemind;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueSkiprule;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.domain.vo.SearchIssueSkipruleVo;

/**
 * 事件类型越级规则设置:业务逻辑层接口
 * 
 * @author
 * @date 2013-11-22 14:51:42
 */
public interface IssueSkipruleService extends
		BaseService<IssueSkiprule, SearchIssueSkipruleVo> {

	public IssueSkiprule getIssueSkipruleByRules(IssueSkiprule issueSkiprule);

	public List<IssueSkiprule> findIssueSkiprulesByRules(
			IssueSkiprule issueSkiprule);

	public IssueSkiprule addIssueSkiprule(IssueSkiprule issueSkiprule,
			List<IssueMessageRemind> issueMessageRemindList);

	public IssueSkiprule updateIssueSkiprule(IssueSkiprule issueSkiprule,
			List<IssueMessageRemind> issueMessageRemindList);

	public IssueSkiprule getIssueSkipruleById(Long id);

	/** 批量启用 */
	public void startIssueSkipRule(List<Long> idList);

	/** 批量停用 */
	public void stopIssueSkipRule(List<Long> idList);

	public IssueSkiprule getIssueSkipruleByIssue(IssueNew issueNew,
			IssueStep step);

	IssueSkiprule getIssueSkipruleByRules(IssueSkiprule issueSkiprule,
			Long orgId);

	/**
	 * 
	 * @param issueSkiprule
	 *            事件大类，事件小类，发生网格
	 * @return
	 */
	public List<PropertyDict> getUrgentLevelList(IssueSkiprule issueSkiprule);
}
