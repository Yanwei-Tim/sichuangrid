package com.tianque.partyBuilding.organizations.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.partyBuilding.organizations.dao.NewPartyOrgDao;
import com.tianque.partyBuilding.organizations.domain.NewPartyOrg;
import com.tianque.partyBuilding.organizations.domain.vo.SearchNewPartyOrgVo;
import com.tianque.util.ParametersConvertUtil;

/**
 * 两新组织党组织表:数据操作层
 * 
 * @author
 * @date 2014-01-14 15:44:09
 */
@Repository("newPartyOrgDao")
public class NewPartyOrgDaoImpl extends BaseDaoImpl<NewPartyOrg, SearchNewPartyOrgVo> implements NewPartyOrgDao {

	@Override
	public Integer countByOrg(List<Long> orgIdList) {
		if(orgIdList==null || orgIdList.size()==0){
			return 0;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgIdsList", ParametersConvertUtil.convertToListString(orgIdList));
		return (Integer) getSqlMapClientTemplate().queryForObject("newPartyOrg.countByOrg", map);
	}

	@Override
	public List<NewPartyOrg> findListBySearchVo(SearchNewPartyOrgVo searchVo) {
		return getSqlMapClientTemplate().queryForList("newPartyOrg.findNewPartyOrgsBySearchVo", searchVo);
	}

	@Override
	public NewPartyOrg getByOrgIdAndName(Long orgId, String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("name", name);
		List<NewPartyOrg> list = getSqlMapClientTemplate().queryForList("newPartyOrg.getByOrgIdAndName", map);
		return (list!=null && list.size()>0)?list.get(0):null;
	}

}
