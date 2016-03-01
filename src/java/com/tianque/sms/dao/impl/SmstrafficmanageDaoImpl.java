package com.tianque.sms.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.core.util.CalendarUtil;
import com.tianque.sms.dao.SmstrafficmanageDao;
import com.tianque.sms.domain.Smstrafficmanage;
import com.tianque.sms.domain.vo.SearchSmstrafficmanageVo;

/**
 * 流量管理:数据操作层
 * 
 * @author
 * @date 2013-07-02 15:29:10
 */
@Repository("smstrafficmanageDao")
public class SmstrafficmanageDaoImpl extends
		BaseDaoImpl<Smstrafficmanage, SearchSmstrafficmanageVo> implements
		SmstrafficmanageDao {

	@Override
	public Smstrafficmanage getSmstrafficmanagesByOrgId(Long orgId) {
		return (Smstrafficmanage) getSqlMapClientTemplate().queryForObject(
				"smstrafficmanage.getSmstrafficmanagesByOrgId", orgId);
	}

	@Override
	public List<Smstrafficmanage> findSmstrafficmanageByOrgId(String orgIds) {
		return getSqlMapClientTemplate().queryForList(
				"smstrafficmanage.findSmstrafficmanagesByOrgId", orgIds);
	}

	@Override
	public Smstrafficmanage getNowSmstrafficmanagesByOrgId(Long orgId) {
		return (Smstrafficmanage) getSqlMapClientTemplate().queryForObject(
				"smstrafficmanage.getNowSmstrafficmanagesByOrgId", orgId);
	}

	@Override
	public List<Smstrafficmanage> findOrgTraffic(Long orgId, Long sendStatus,
			Integer pageNum, Integer pageSize, String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sortField);
		map.put("order", order);
		map.put("orgId", orgId);
		map.put("sendStatus", sendStatus);
		return getSqlMapClientTemplate().queryForList(
				"smstrafficmanage.findOrgTraffic", map,
				(pageNum - 1) * pageSize, pageSize);
	}

	@Override
	public List<Smstrafficmanage> findParentOrgTraffic(String orgIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgIds", orgIds);
		map.put("startDate", CalendarUtil.today());
		map.put("endDate", CalendarUtil.getTomorrow());
		return getSqlMapClientTemplate().queryForList(
				"smstrafficmanage.findParentOrgTraffic", map);
	}

}
