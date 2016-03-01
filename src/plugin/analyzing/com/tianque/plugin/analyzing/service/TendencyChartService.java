package com.tianque.plugin.analyzing.service;

import com.tianque.plugin.analyzing.domain.HighchartColumnVo;

public interface TendencyChartService {
	public HighchartColumnVo findTendencyChart(String typeTableName, String orgInternalCode);

	public HighchartColumnVo findTendencyChartForPositiveinfo(String type, String orgInternalCode);

	public HighchartColumnVo findTendencyChartForActualPopulation(String orgInternalCode);
}
