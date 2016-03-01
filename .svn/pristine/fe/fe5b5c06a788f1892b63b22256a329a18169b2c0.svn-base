package com.tianque.dao;

import java.util.Date;
import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.StatRegradedPoint;
import com.tianque.statRegrad.domain.StatRegradedPointsSearchVo;

public interface StatRegradedPointsDao {

	List<StatRegradedPoint> getSimpleStatRegradedPoints(Date startDate,
			Date endDate, Long targeOrgId, Long orgType, String sortField,
			String order);

	List<StatRegradedPoint> getFullStatRegradedPoints(Integer year,
			Integer month, Long parentOrgId, int orgType, String sortField,
			String order);

	StatRegradedPoint addStatRegradedPoint(StatRegradedPoint statRegradedPoint);

	StatRegradedPoint getStatRegradedPointById(Long id);

	PageInfo<StatRegradedPoint> getSimpleStatRegradedPointsForList(
			Date startDate, Date endDate, Long targeOrgId, int orgType,
			Integer pageNum, Integer pageSize, String sidx, String sord);
	
	void createRegradedPointStatTable(Integer year, Integer month,
			Date startDate, Date endDate,Long orgLevel);
	
	void dropRegradedPointStatTable(Integer year, Integer month);
	
	List<StatRegradedPoint> getSimpleStatRegradedPointsNew(
			List<StatRegradedPointsSearchVo> statRegradedPointsSearchVos);
	
	void deleteRegradedPointStatTableByOrgId(Integer year, Integer month,
			Long targeOrgId);
	
	void insertRegradedPointStatTableByOrgId(Integer year,
			Integer month, Long targeOrgId, Date startDate, Date endDate);
	
	void insertRegradedPointStatTableByOrgCode(Integer year, Integer month,
			String orgCode, Date startDate, Date endDate);
}
