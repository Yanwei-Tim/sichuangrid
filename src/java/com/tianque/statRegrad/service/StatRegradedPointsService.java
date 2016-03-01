package com.tianque.statRegrad.service;

import java.util.List;

import com.tianque.domain.PropertyDict;
import com.tianque.domain.StatRegradedPoint;

public interface StatRegradedPointsService {

	List<StatRegradedPoint> findStatRegradedPoints(
			List<StatRegradedPoint> points, Integer year, Integer month,
			PropertyDict reoprtDateType, Long targeOrgId, int internalId,
			String sidx, String sord);

}
