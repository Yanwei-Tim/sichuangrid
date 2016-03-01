package com.tianque.issue.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.issue.dao.IssueSkipruleDao;
import com.tianque.issue.domain.IssueSkiprule;
import com.tianque.issue.domain.vo.SearchIssueSkipruleVo;

/**
 * 事件类型越级规则设置:数据操作层
 * 
 * @author
 * @date 2013-11-22 14:51:42
 */
@Repository("issueSkipruleDao")
public class IssueSkipruleDaoImpl extends
		BaseDaoImpl<IssueSkiprule, SearchIssueSkipruleVo> implements
		IssueSkipruleDao {

	@Override
	public IssueSkiprule getIssueSkipruleByRules(IssueSkiprule issueSkiprule) {
		if (issueSkiprule == null || issueSkiprule.getOrgId() == null
				|| issueSkiprule.getIssueTypeDomainId() == null
				|| issueSkiprule.getIssueTypeId() == null
				|| issueSkiprule.getIssueUrgentLevel() == null) {
			throw new BusinessValidationException("查询参数无效!");
		}
		return (IssueSkiprule) getSqlMapClientTemplate().queryForObject(
				"issueSkiprule.getIssueSkipruleByRules", issueSkiprule);
	}

	@Override
	public List<IssueSkiprule> findIssueSkiprulesByRules(
			IssueSkiprule issueSkiprule) {
		if (issueSkiprule == null || issueSkiprule.getOrgId() == null
				|| issueSkiprule.getIssueTypeDomainId() == null
				|| issueSkiprule.getIssueTypeId() == null) {
			throw new BusinessValidationException("查询参数无效!");
		}
		return getSqlMapClientTemplate().queryForList(
				"issueSkiprule.findIssueSkiprulesByRules", issueSkiprule);
	}

	public void updateIssueSkipRuleStatus(List<Long> ids, int status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("status", status);
		getSqlMapClientTemplate().update(
				"issueSkiprule.updateIssueSkipruleStatus", map);
	}
}
