package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.dao.StatisticsTendencyChartDao;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.service.StatisticsTendencyChartService;

@Transactional
@Service("statisticsTendencyChartService")
public class StatisticsTendencyChartServiceImpl extends AbstractBaseService
		implements StatisticsTendencyChartService {

	@Autowired
	StatisticsTendencyChartDao statisticsTendencyChartDao;

	@Override
	public void statisticsMonthMessage() {
		try {
			List<String> importantsPersonneNameList = BaseInfoTables
					.getImportantPersonalKeys();
			List<String> importantPlaceList = BaseInfoTables
					.getImportantPlaceKeys();
			for (int i = 0; i < importantsPersonneNameList.size(); i++) {
				statisticsTendencyChartDao.statisticsMonthTendencyChart(
						BaseInfoTables.getTypeValue(importantsPersonneNameList
								.get(i)), null);
			}
			for (int i = 0; i < importantPlaceList.size(); i++) {
				if (importantPlaceList.get(i).equals(
						BaseInfoTables.FIRESAFETYKEY_KEY)
						|| importantPlaceList.get(i).equals(
								BaseInfoTables.SAFETYPRODUCTIONKEY_KEY)
						|| importantPlaceList.get(i).equals(
								BaseInfoTables.SECURITYKEY_KEY)) {
					statisticsTendencyChartDao.statisticsMonthTendencyChart(
							"enterprises", BaseInfoTables
									.getTypeValue(importantPlaceList.get(i)));
				} else {
					statisticsTendencyChartDao.statisticsMonthTendencyChart(
							BaseInfoTables.getTypeValue(importantPlaceList
									.get(i)), null);
				}
			}
			statisticsTendencyChartDao.statisticsMonthTendencyChart(
					"enterprises", BaseInfoTables
							.getTypeValue(BaseInfoTables.ENTERPRISEKEY_KEY));
			statisticsTendencyChartDao.statisticsMonthTendencyChart(
					BaseInfoTables
							.getTypeValue(BaseInfoTables.LETTINGHOUSE_KEY),
					null);
			statisticsTendencyChartDao
					.statisticsMonthTendencyChart(
							BaseInfoTables
									.getTypeValue(BaseInfoTables.NEWSOCIETYFEDERATION_KEY),
							null);
		} catch (Exception e) {
			logger.error("类StatisticsTendencyChartServiceImpl的statisticsMonthMessage方法出现异常，原因：", e);
			throw new ServiceValidationException("自动统计趋势图数据job出现异常", e);
		}

	}
}
