package com.tianque.partyBuilding.organizations.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.partyBuilding.organizations.dao.PartyresourceDao;
import com.tianque.partyBuilding.organizations.domain.Partyresource;
import com.tianque.partyBuilding.organizations.domain.vo.SearchPartyresourceVo;
import com.tianque.util.ParametersConvertUtil;

/**
 * 区域内主要党组织资源:数据操作层
 * 
 * @author
 * @date 2014-01-15 09:22:20
 */
@Repository("partyresourceDao")
public class PartyresourceDaoImpl extends
		BaseDaoImpl<Partyresource, SearchPartyresourceVo> implements
		PartyresourceDao {

	@Override
	public Integer getTownAndVillagePartyResourceNum(List<Long> orgIdList) {
		if(orgIdList==null || orgIdList.size()==0){
			return 0;
		}
		Map map = new HashMap<String, Object>();
		map.put("orgIdsList", ParametersConvertUtil.convertToListString(orgIdList));
		Integer result = (Integer) getSqlMapClientTemplate().queryForObject(
				"partyresource.getTownAndVillagePartyResourceNum", map);
		return result == null ? 0 : result;
	}
}
