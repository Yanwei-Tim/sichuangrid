package com.tianque.partyBuilding.baseInfos.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.partyBuilding.baseInfos.dao.DistrictBasiccaseDao;
import com.tianque.partyBuilding.baseInfos.domain.DistrictBasiccase;
import com.tianque.partyBuilding.baseInfos.domain.vo.SearchDistrictBasiccaseVo;

/**
 * 社区基本情况表:数据操作层
 * 
 * @author
 * @date 2014-01-14 15:24:54
 */
@Repository("districtBasiccaseDao")
public class DistrictBasiccaseDaoImpl extends
		BaseDaoImpl<DistrictBasiccase, SearchDistrictBasiccaseVo> implements
		DistrictBasiccaseDao {

	@Override
	public DistrictBasiccase findDistrictBasiccaseByIdAndOrgId(Long id,
			Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("orgId", orgId);
		DistrictBasiccase districtBasiccase = (DistrictBasiccase) getSqlMapClientTemplate()
				.queryForObject(
						"districtBasiccase.findDistrictBasiccaseByIdAndOrgId",
						map);
		return districtBasiccase;
	}

}
