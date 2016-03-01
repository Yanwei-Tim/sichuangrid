package com.tianque.partyBuilding.organsParty.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.domain.PropertyDict;
import com.tianque.partyBuilding.organsParty.dao.OrgansPartyDao;
import com.tianque.partyBuilding.organsParty.domain.OrgansParty;
import com.tianque.partyBuilding.organsParty.domain.vo.SearchOrgansPartyVo;

/**
 * 机关党组织表:数据操作层
 * 
 * @author
 * @date 2014-02-10 16:00:06
 */
@Repository("organsPartyDao")
public class OrgansPartyDaoImpl extends
		BaseDaoImpl<OrgansParty, SearchOrgansPartyVo> implements OrgansPartyDao {

	@Override
	public Integer getOrgansPartyCountByOrgIdAndSuperiorAndType(Long orgId,
			Long superior, PropertyDict type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("superior", superior);
		map.put("type", type);
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"organsParty.findOrgansPartysByOrgIdAndSuperiorAndType", map);
		return count == null ? 0 : count;
	}

	@Override
	public Long getOrgansPartyByName(String name, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("name", name);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"organsParty.getOrgansPartyByName", map);
	}

}
