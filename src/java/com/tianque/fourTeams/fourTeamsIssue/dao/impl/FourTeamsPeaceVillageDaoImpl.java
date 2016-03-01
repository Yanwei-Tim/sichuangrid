package com.tianque.fourTeams.fourTeamsIssue.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsPeaceVillageDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsPeaceVillage;
import com.tianque.fourTeams.fourTeamsIssue.domain.vo.SearchFourTeamsPeaceVillageVo;

/**
 * 村社区平安三联创:数据操作层
 * 
 * @author
 * @date 2014-01-04 10:45:52
 */
@Repository("fourTeamsPeaceVillageDao")
public class FourTeamsPeaceVillageDaoImpl extends
		BaseDaoImpl<FourTeamsPeaceVillage, SearchFourTeamsPeaceVillageVo>
		implements FourTeamsPeaceVillageDao {

	@Override
	public List<FourTeamsPeaceVillage> getPeaceVillageListBySearchVo(
			SearchFourTeamsPeaceVillageVo searchPeaceVillageVo) {
		if (searchPeaceVillageVo == null
				|| searchPeaceVillageVo.getOrganization() == null
				|| searchPeaceVillageVo.getOrganization().getId() == null
				|| searchPeaceVillageVo.getYear() == null
				|| searchPeaceVillageVo.getMonth() == null) {
			throw new BusinessValidationException("查询参数无效!");
		}
		return getSqlMapClientTemplate().queryForList(
				"peaceVillage.getPeaceVillageListBySearchVo",
				searchPeaceVillageVo);
	}

	@Override
	public FourTeamsPeaceVillage getPeaceVillageByOrgAndYearMonth(
			FourTeamsPeaceVillage peaceVillage) {

		return (FourTeamsPeaceVillage) getSqlMapClientTemplate()
				.queryForObject(
						"peaceVillage.getPeaceVillageByOrgAndYearMonth",
						peaceVillage);
	}
}
