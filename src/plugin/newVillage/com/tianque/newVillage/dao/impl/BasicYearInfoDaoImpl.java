package com.tianque.newVillage.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.newVillage.dao.BasicYearInfoDao;
import com.tianque.newVillage.domain.BasicYearInfo;

/**
 * @author yangfan
 * @date 2015年10月9日
 */
@Repository("basicYearInfoDao")
public class BasicYearInfoDaoImpl extends AbstractBaseDao implements
		BasicYearInfoDao {

	@Override
	public BasicYearInfo addBasicYearInfo(BasicYearInfo basicYearInfo) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"basicYearInfo.addBasicYearInfo", basicYearInfo);
		return getBasicYearInfoById(id);
	}

	@Override
	public BasicYearInfo getBasicYearInfoById(Long id) {
		return (BasicYearInfo) getSqlMapClientTemplate().queryForObject(
				"basicYearInfo.getBasicYearInfo", id);
	}

	@Override
	public void deleteBasicYearInfoById(String[] id) {
		getSqlMapClientTemplate().delete("basicYearInfo.deleteBasicYearInfo",
				id);
	}

	@Override
	public BasicYearInfo updateBasicYearInfo(BasicYearInfo basicYearInfo) {
		getSqlMapClientTemplate().update("basicYearInfo.updateBasicYearInfo",
				basicYearInfo);
		return getBasicYearInfoById(basicYearInfo.getId());
	}

	@Override
	public BasicYearInfo getBasicYearInfoByBasicId(Long basicId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("basicId", basicId);
		return (BasicYearInfo) getSqlMapClientTemplate().queryForObject(
				"basicYearInfo.getBasicYearInfoByBasicId", map);
	}

	@Override
	public List<BasicYearInfo> getBasicYearInfoByYearAndOrgId(Integer year,
			Long orgId) {
		Map map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("orgId", orgId);
		return getSqlMapClientTemplate().queryForList(
				"basicYearInfo.getBasicYearInfoByYearAndOrgId", map);
	}

}
