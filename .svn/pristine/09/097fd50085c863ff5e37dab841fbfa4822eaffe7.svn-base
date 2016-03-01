package com.tianque.partyBuilding.baseInfos.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.partyBuilding.baseInfos.dao.BasicCaseDao;
import com.tianque.partyBuilding.baseInfos.domain.BasicCase;
import com.tianque.partyBuilding.baseInfos.domain.vo.SearchBasicCaseVo;

/**
 * 基本情况表:数据操作层
 * 
 * @author
 * @date 2014-01-14 15:32:52
 */
@Repository("basicCaseDao")
public class BasicCaseDaoImpl extends BaseDaoImpl<BasicCase, SearchBasicCaseVo> implements BasicCaseDao {

	@Override
	public BasicCase findBasicCaseByIdAndOrgId(Long id, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("orgId", orgId);
		BasicCase basicCase = (BasicCase) getSqlMapClientTemplate().queryForObject(
				"basicCase.findBasicCaseByIdAndOrgId", map);
		return basicCase;
	}

	@Override
	public BasicCase getByIdAndOrgIdAndType(Long id, Long orgId, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("orgId", orgId);
		map.put("baseInfoType", type);
		return (BasicCase) getSqlMapClientTemplate().queryForObject("basicCase.getByIdAndOrgIdAndType", map);
	}

}
