package com.tianque.fourTeams.fourTeamsIssue.service;

import java.util.List;

import com.tianque.core.base.BaseService;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueMessageRemind;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueSkiprule;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.vo.SearchFourTeamsIssueSkipruleVo;

/**
 * 四支队伍事件类型越级规则设置:业务逻辑层接口
 * 
 * @author
 * 
 */
public interface FourTeamsIssueSkipruleService extends
		BaseService<FourTeamsIssueSkiprule, SearchFourTeamsIssueSkipruleVo> {

	public FourTeamsIssueSkiprule getFourTeamsIssueSkipruleByRules(
			FourTeamsIssueSkiprule fourTeamsIssueSkiprule);

	public List<FourTeamsIssueSkiprule> findFourTeamsIssueSkiprulesByRules(
			FourTeamsIssueSkiprule fourTeamsIssueSkiprule);

	public FourTeamsIssueSkiprule addFourTeamsIssueSkiprule(
			FourTeamsIssueSkiprule fourTeamsIssueSkiprule,
			List<FourTeamsIssueMessageRemind> fourTeamsIssueMessageRemindList);

	public FourTeamsIssueSkiprule updateFourTeamsIssueSkiprule(
			FourTeamsIssueSkiprule fourTeamsIssueSkiprule,
			List<FourTeamsIssueMessageRemind> fourTeamsIssueMessageRemindList);

	public FourTeamsIssueSkiprule getFourTeamsIssueSkipruleById(Long id);

	public FourTeamsIssueSkiprule getFourTeamsIssueSkipruleByIssue(
			FourTeamsIssueNew issueNew);

	FourTeamsIssueSkiprule getFourTeamsIssueSkipruleByRules(
			FourTeamsIssueSkiprule fourTeamsIssueSkiprule, Long orgId);
}
