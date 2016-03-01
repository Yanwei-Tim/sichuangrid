package com.tianque.fourTeams.fourTeamsIssue.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueSkipruleDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueSkiprule;
import com.tianque.fourTeams.fourTeamsIssue.domain.vo.SearchFourTeamsIssueSkipruleVo;

/**
 * 四支队伍事件类型越级规则设置:数据操作层
 * 
 * @author
 * 
 */
@Repository("fourTeamsIssueSkipruleDao")
public class FourTeamsIssueSkipruleDaoImpl extends
		BaseDaoImpl<FourTeamsIssueSkiprule, SearchFourTeamsIssueSkipruleVo>
		implements FourTeamsIssueSkipruleDao {

	@Override
	public FourTeamsIssueSkiprule getFourTeamsIssueSkipruleByRules(
			FourTeamsIssueSkiprule fourTeamsIssueSkiprule) {
		if (fourTeamsIssueSkiprule == null
				|| fourTeamsIssueSkiprule.getOrgId() == null
				|| fourTeamsIssueSkiprule.getIssueTypeDomainId() == null
				|| fourTeamsIssueSkiprule.getIssueTypeId() == null
				|| fourTeamsIssueSkiprule.getIssueUrgentLevel() == null) {
			throw new BusinessValidationException("查询参数无效!");
		}
		return (FourTeamsIssueSkiprule) getSqlMapClientTemplate()
				.queryForObject(
						"fourTeamsIssueSkiprule.getFourTeamsIssueSkipruleByRules",
						fourTeamsIssueSkiprule);
	}

	@Override
	public List<FourTeamsIssueSkiprule> findFourTeamsIssueSkiprulesByRules(
			FourTeamsIssueSkiprule fourTeamsIssueSkiprule) {
		if (fourTeamsIssueSkiprule == null
				|| fourTeamsIssueSkiprule.getOrgId() == null
				|| fourTeamsIssueSkiprule.getIssueTypeDomainId() == null
				|| fourTeamsIssueSkiprule.getIssueTypeId() == null) {
			throw new BusinessValidationException("查询参数无效!");
		}
		return getSqlMapClientTemplate().queryForList(
				"fourTeamsIssueSkiprule.findFourTeamsIssueSkiprulesByRules",
				fourTeamsIssueSkiprule);
	}

}
