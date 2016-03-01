package com.tianque.fourTeams.fourTeamsIssue.service;

import java.util.List;

import com.tianque.core.base.BaseService;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsPeaceVillage;
import com.tianque.fourTeams.fourTeamsIssue.domain.vo.SearchFourTeamsPeaceVillageVo;

/**
 * 村社区平安三联创:业务逻辑层接口
 * 
 * @author
 * @date 2014-01-04 10:45:52
 */
public interface FourTeamsPeaceVillageService extends
		BaseService<FourTeamsPeaceVillage, SearchFourTeamsPeaceVillageVo> {

	public List<FourTeamsPeaceVillage> getPeaceVillageListBySearchVo(
			SearchFourTeamsPeaceVillageVo searchPeaceVillageVo);

	public void savePeaceVillage(List<FourTeamsPeaceVillage> peaceVillageList);

	public FourTeamsPeaceVillage getPeaceVillageByOrgAndYearMonth(
			FourTeamsPeaceVillage peaceVillage);

}
