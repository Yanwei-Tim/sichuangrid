package com.tianque.statRegrad.service;

import java.util.Date;
import java.util.List;

import com.tianque.domain.PropertyDict;
import com.tianque.domain.StatRegradedPoint;

public interface DefalutStatRegradedPointsService {
	
	List<StatRegradedPoint> findStatRegradedPoints(
			List<StatRegradedPoint> points, Integer year, Integer month,
			PropertyDict reportDateType, Long targeOrgId, int internalId,
			String sidx, String sord);
	
	void createRegradedPointStatTable(Integer year, Integer month);
	
	void refreshRegradedPointStatTableByOrgId(Date applicationDate, Long targeOrgId);
}
