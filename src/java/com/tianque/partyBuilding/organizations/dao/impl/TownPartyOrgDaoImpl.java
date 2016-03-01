package com.tianque.partyBuilding.organizations.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.partyBuilding.organizations.dao.TownPartyOrgDao;
import com.tianque.partyBuilding.organizations.domain.TownPartyOrg;
import com.tianque.partyBuilding.organizations.domain.vo.SearchTownPartyOrgVo;
import com.tianque.util.ParametersConvertUtil;

/**
 * 街道社区党组织表:数据操作层
 * 
 * @author
 * @date 2014-01-14 14:35:25
 */
@Repository("townPartyOrgDao")
public class TownPartyOrgDaoImpl extends
		BaseDaoImpl<TownPartyOrg, SearchTownPartyOrgVo> implements
		TownPartyOrgDao {

	@Override
	public Integer countByOrg(List<Long> orgIdsList) {
		if (orgIdsList == null || orgIdsList.size() == 0) {
			return 0;
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgIdsList",
					ParametersConvertUtil.convertToListString(orgIdsList));
			return (Integer) getSqlMapClientTemplate().queryForObject(
					"townPartyOrg.countTownPartyOrgByOrg", map);
		}
	}

	@Override
	public List<TownPartyOrg> findListBySearchVo(SearchTownPartyOrgVo searchVo) {
		return getSqlMapClientTemplate().queryForList(
				"townPartyOrg.findTownPartyOrgsBySearchVo", searchVo);
	}

	@Override
	public TownPartyOrg getByOrgIdAndName(Long orgId, String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("name", name);
		List<TownPartyOrg> list = getSqlMapClientTemplate().queryForList(
				"townPartyOrg.getByOrgIdAndName", map);
		return (list != null && list.size() > 0) ? list.get(0) : null;
	}

}
