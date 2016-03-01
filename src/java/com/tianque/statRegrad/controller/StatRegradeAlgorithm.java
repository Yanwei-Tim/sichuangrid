package com.tianque.statRegrad.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.domain.PropertyDict;
import com.tianque.domain.StatRegradedPoint;
import com.tianque.statRegrad.service.DefalutStatRegradedPointsService;
import com.tianque.statRegrad.service.StatRegradedPointsService;
import com.tianque.statRegrad.util.RegradedPointUtil;

@Component("statRegradeAlgorithm")
public class StatRegradeAlgorithm {
	private final static Logger logger = LoggerFactory
			.getLogger(StatRegradeAlgorithm.class);
	@Autowired
	private Map<String, StatRegradedPointsService> statRegradedPointsServices;
	
	@Autowired
	private DefalutStatRegradedPointsService defalutStatRegradedPointsService;

	public List<StatRegradedPoint> findStatRegradedPoints(Integer year,
			Integer month, PropertyDict reoprtDateType, Long targeOrgId,
			int internalId, String sidx, String sord) {
 		List<StatRegradedPoint> points = new ArrayList<StatRegradedPoint>();
		try {
			StatRegradedPointsService statRegradedPointsService = null;
			points = defalutStatRegradedPointsService.findStatRegradedPoints(
					points, year, month, reoprtDateType, targeOrgId,
					internalId, sidx, sord);
			String[] beanNames = RegradedPointUtil.getAlgorithmService();
			if (beanNames.length > 0) {
				for (String beanName : beanNames) {
					statRegradedPointsService = statRegradedPointsServices
							.get(beanName);
					points = statRegradedPointsService.findStatRegradedPoints(
							points, year, month, reoprtDateType, targeOrgId,
							internalId, sidx, sord);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return points;
	}
	
	public void createRegradedPointStatTable(Integer year, Integer month){
		defalutStatRegradedPointsService.createRegradedPointStatTable(year, month);
	}
}
