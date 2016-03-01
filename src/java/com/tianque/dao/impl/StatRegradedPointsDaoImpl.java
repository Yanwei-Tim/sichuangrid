package com.tianque.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.StatRegradedPointsDao;
import com.tianque.domain.StatRegradedPoint;
import com.tianque.statRegrad.domain.StatRegradedPointsSearchVo;

@Repository("statRegradedPointsDao")
public class StatRegradedPointsDaoImpl extends AbstractBaseDao implements
		StatRegradedPointsDao {

	@Override
	public List<StatRegradedPoint> getSimpleStatRegradedPoints(Date startDate,
			Date endDate, Long targeOrgId, Long orgType, String sortField,
			String order) {
		Map map = new HashMap();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("targeOrgId", targeOrgId);
		map.put("orgType", orgType);
		if (sortField != null) {
			map.put("sortField", sortField);
			map.put("order", order);
		}

		return getSqlMapClientTemplate().queryForList(
				"statRegradedPoints.getSimpleStatRegradedPoints", map);
	}

	@Override
	public List<StatRegradedPoint> getFullStatRegradedPoints(Integer year,
			Integer month, Long parentOrgId, int orgType, String sortField,
			String order) {
		Map query = new HashMap();
		query.put("year", year);
		query.put("month", month);
		query.put("parentOrgId", parentOrgId);
		query.put("orgType", orgType);
		if (sortField != null) {
			query.put("sortField", sortField);
			query.put("order", order);
		}
		return getSqlMapClientTemplate().queryForList(
				"statRegradedPoints.getFullStatRegradedPoints", query);
	}

	@Override
	public StatRegradedPoint addStatRegradedPoint(
			StatRegradedPoint statRegradedPoint) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"statRegradedPoints.addStatRegradedPoint", statRegradedPoint);
		return getStatRegradedPointById(id);
	}

	@Override
	public StatRegradedPoint getStatRegradedPointById(Long id) {
		return (StatRegradedPoint) getSqlMapClientTemplate().queryForObject(
				"statRegradedPoints.getStatRegradedPointById", id);
	}

	@Override
	public PageInfo<StatRegradedPoint> getSimpleStatRegradedPointsForList(
			Date startDate, Date endDate, Long targeOrgId, int orgType,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("targeOrgId", targeOrgId);
		map.put("orgType", orgType);
		if (null != sidx && !"".equals(sidx)) {
			map.put("sortField", sidx);
			map.put("order", sord);
		}
		return new PageInfo<StatRegradedPoint>(
				pageNum,
				pageSize,
				(Integer) getSqlMapClientTemplate()
						.queryForObject(
								"statRegradedPoints.countSimpleStatRegradedPoints",
								map), getSqlMapClientTemplate().queryForList(
						"statRegradedPoints.getSimpleStatRegradedPoints", map,
						(pageNum - 1) * pageSize, pageSize));
	}
	
	@Override
	public void createRegradedPointStatTable(Integer year, Integer month,
			Date startDate, Date endDate, Long orgLevel) {
		Map map = new HashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		map.put("year", year);
		map.put("month", month);
		map.put("startDate", sdf.format(startDate));
		map.put("endDate", sdf.format(endDate));
		map.put("orgLevel", orgLevel);
		getSqlMapClientTemplate().update("statRegradedPoints.createRegradedPointStatTable", map);
	}
	
	@Override
	public void dropRegradedPointStatTable(Integer year, Integer month) {
		Map map = new HashMap();
		map.put("year", year);
		map.put("month", month);
		getSqlMapClientTemplate().update("statRegradedPoints.dropRegradedPointStatTable", map);
	}
	
	@Override
	public List<StatRegradedPoint> getSimpleStatRegradedPointsNew(
			List<StatRegradedPointsSearchVo> statRegradedPointsSearchVos) {
		Map map = new HashMap();
		map.put("list", statRegradedPointsSearchVos);
		return getSqlMapClientTemplate().queryForList(
				"statRegradedPoints.getSimpleStatRegradedPointsNew", map);
	}
	
	@Override
	public void deleteRegradedPointStatTableByOrgId(Integer year, Integer month,
			Long targeOrgId){
		Map map = new HashMap();
		map.put("year", year);
		map.put("month", month);
		map.put("targeOrgId", targeOrgId);
		getSqlMapClientTemplate().delete(
				"statRegradedPoints.deleteRegradedPointStatTableByOrgId", map);
	}
	
	@Override
	public void insertRegradedPointStatTableByOrgId(Integer year,
			Integer month, Long targeOrgId, Date startDate, Date endDate) {
		Map map = new HashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		map.put("year", year);
		map.put("month", month);
		map.put("startDate", sdf.format(startDate));
		map.put("endDate", sdf.format(endDate));
		map.put("targeOrgId", targeOrgId);
		getSqlMapClientTemplate().insert(
				"statRegradedPoints.insertRegradedPointStatTableByOrgId", map);
	}
	
	@Override
	public void insertRegradedPointStatTableByOrgCode(Integer year,
			Integer month, String orgCode, Date startDate, Date endDate) {
		Map map = new HashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		map.put("year", year);
		map.put("month", month);
		map.put("startDate", sdf.format(startDate));
		map.put("endDate", sdf.format(endDate));
		map.put("orgCode", orgCode);
		getSqlMapClientTemplate()
				.insert("statRegradedPoints.insertRegradedPointStatTableByOrgCode",
						map);
	}

}
