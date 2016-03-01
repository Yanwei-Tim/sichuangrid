package com.tianque.plugin.statistics.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.dao.AbstractBaseDao;
import com.tianque.plugin.statistics.dao.GeneralSituationDao;
import com.tianque.plugin.statistics.domain.GeneralSituation;
import com.tianque.plugin.statistics.vo.GeneralStituationSearchVo;

@Repository("generalSituationDao")
public class GeneralSituationDaoImpl extends AbstractBaseDao implements GeneralSituationDao{

	@Override
	public void createGeneralSituationByDate(String statisticsTableName,Integer year,Integer month,Long orgType,Long orgLevel,String tableName,String basesicType,
					String detailType,String column,Date startDate,Date endDate) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("year",year );
		map.put("month", month);
		map.put("createUser", ThreadVariable.getUser().getUserName());
		map.put("createDate", CalendarUtil.now());
		map.put("basesicType", basesicType);
		map.put("detailType", detailType);
		map.put("tableName", tableName);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("orgType", orgType);
		map.put("orgLevel", orgLevel);
		map.put("column", column);
		map.put("statisticsTableName", statisticsTableName);
		getSqlMapClientTemplate().insert("generalSituation.createGeneralSituationByDate",map);
		
	}

	@Override
	public void deleteGeneralSituationByDate(String tableName) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tableName", tableName);
		getSqlMapClientTemplate().delete("generalSituation.deleteGeneralSituationByDate",map);
		
	}

	@Override
	public void createGeneralSituationOfMentalpatient(String statisticsTableName,Integer year,
			Integer month, Long orgType, Long orgLevel, String basesicType,
			Date startDate, Date endDate) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("year",year );
		map.put("month", month);
		map.put("createUser", ThreadVariable.getUser().getUserName());
		map.put("createDate", CalendarUtil.now());
		map.put("basesicType", basesicType);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("orgType", orgType);
		map.put("orgLevel", orgLevel);
		map.put("statisticsTableName", statisticsTableName);
		getSqlMapClientTemplate().insert("generalSituation.createGeneralSituationOfMentalpatient",map);
	}

	@Override
	public void createGeneralSituationByType(String statisticsTableName,Integer year, Integer month,
			Long orgType, Long orgLevel,String tableName, String basesicType, String detailType,
			String subType, String column, Date startDate, Date endDate) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("year",year );
		map.put("month", month);
		map.put("tableName", tableName);
		map.put("createUser", ThreadVariable.getUser().getUserName());
		map.put("createDate", CalendarUtil.now());
		map.put("basesicType", basesicType);
		map.put("detailType", detailType);	
		map.put("subType", subType);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("orgType", orgType);
		map.put("orgLevel", orgLevel);
		map.put("column", column);
		map.put("statisticsTableName", statisticsTableName);
		getSqlMapClientTemplate().insert("generalSituation.createGeneralSituationByType",map);
	}

	@Override
	public List<GeneralSituation> getStatisticInfoByConditions(
			GeneralStituationSearchVo generalStituationSearchVo) {
		return getSqlMapClientTemplate().queryForList("generalSituation.getStatisticInfoByConditions", generalStituationSearchVo);
	}


	@Override
	public List<GeneralSituation> findGeneralSituationListByBasesicType(
			Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList("generalSituation.findGeneralSituationByBasesicType", map);
	}

	@Override
	public List<GeneralSituation> findGeneralSituationListByDetailType(
			Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList("generalSituation.findGeneralSituationByDetailType", map);
	}

	@Override
	public List<GeneralSituation> findGeneralSituationListBySubType(
			Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList("generalSituation.findGeneralSituationBySubType", map);
	}

}
