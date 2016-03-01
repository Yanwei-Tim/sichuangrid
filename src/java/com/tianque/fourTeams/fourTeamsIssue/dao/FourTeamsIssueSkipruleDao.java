package com.tianque.fourTeams.fourTeamsIssue.dao;

import java.util.List;

import com.tianque.core.base.BaseDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueSkiprule;
import com.tianque.fourTeams.fourTeamsIssue.domain.vo.SearchFourTeamsIssueSkipruleVo;

/**
 * 事件类型越级规则设置:数据操作层接口
 * 
 * @author
 * @date 2013-11-22 14:51:42
 */
public interface FourTeamsIssueSkipruleDao extends
		BaseDao<FourTeamsIssueSkiprule, SearchFourTeamsIssueSkipruleVo> {

	public FourTeamsIssueSkiprule getFourTeamsIssueSkipruleByRules(
			FourTeamsIssueSkiprule fourTeamsissueSkiprule);

	public List<FourTeamsIssueSkiprule> findFourTeamsIssueSkiprulesByRules(
			FourTeamsIssueSkiprule fourTeamsissueSkiprule);
}
