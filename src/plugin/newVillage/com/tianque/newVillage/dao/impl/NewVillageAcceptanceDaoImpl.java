package com.tianque.newVillage.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.dao.AbstractBaseDao;
import com.tianque.newVillage.dao.NewVillageAcceptanceDao;
import com.tianque.newVillage.vo.NewVillageAssessmentVo;

@Repository("newVillageAcceptanceDao")
public class NewVillageAcceptanceDaoImpl extends AbstractBaseDao implements NewVillageAcceptanceDao{

	@Override
	public List<NewVillageAssessmentVo> findCumulativeNewVillageBasic(
			Integer year) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("year", year);
		return getSqlMapClientTemplate().queryForList("newVillageAcceptance.findCumulativeNewVillageBasic",map);
	}

	@Override
	public List<NewVillageAssessmentVo> findcurrentNewVillageBasic(Integer year) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("year", year);
		return getSqlMapClientTemplate().queryForList("newVillageAcceptance.findcurrentNewVillageBasic",map);
	}

}
