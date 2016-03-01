package com.tianque.working.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.working.domain.SeriesSecurity;

public interface SeriesSecurityDao {
	SeriesSecurity getSeriesSecurityById(Long id);

	SeriesSecurity addSeriesSecurity(SeriesSecurity seriesSecurity);

	void deleteSeriesSecurityById(Long id);

	SeriesSecurity updateSeriesSecurity(SeriesSecurity seriesSecurity);

	PageInfo<SeriesSecurity> findSeriesSecuritysForPageByOrgIdAndDailyDirectoryId(
			String dailyDirectoryIds, Long orgId, Integer page, Integer rows, String sidx,
			String sord);
}
