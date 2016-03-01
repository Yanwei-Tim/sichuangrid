package com.tianque.plugin.analysisNew.service;

import com.tianque.plugin.analysisNew.domain.HighchartColumnVo;

public interface TendencyChartNewService {
	
	public HighchartColumnVo findTendencyChart(String typeTableName, Long orgId);

	public HighchartColumnVo findTendencyChartForPositiveinfo(String type,
			Long orgId);

	public HighchartColumnVo findTendencyChartForActualPopulation(Long orgId);
}
