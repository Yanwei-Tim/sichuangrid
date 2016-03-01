package com.tianque.plugin.analysisNew.service;

import java.util.List;

import com.tianque.plugin.analysisNew.domain.HighchartColumnVo;
import com.tianque.plugin.analysisNew.domain.PersonnelAreaDataVo;

public interface StatisticsPlaceNewService {

	List<PersonnelAreaDataVo> getImportantPlaceAreaDataByOrgId(Long orgId);

	HighchartColumnVo getImportantPlaceColumnByOrgId(Long orgId);

	List<Object[]> getImportantPlacePieByOrgId(Long orgId);

	/**
	 * 类型分布图，历史数据
	 * 
	 * @param orgId
	 * @param typeTableName
	 * @param year
	 * @param month
	 * @return
	 */
	public HighchartColumnVo getImportantPlaceCountFromHistory(Long orgId,
			String typeTableName, int year, int month);

	public int getImportantPlaceCountByOrgId(Long orgId);
}
