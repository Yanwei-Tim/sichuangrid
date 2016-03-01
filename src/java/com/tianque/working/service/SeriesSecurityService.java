package com.tianque.working.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.working.domain.SeriesSecurity;

public interface SeriesSecurityService {
	public SeriesSecurity getSeriesSecurityById(Long id);

	public SeriesSecurity addSeriesSecurity(SeriesSecurity seriesSecurity);

	public void deleteSeriesSecurityById(Long id);

	public SeriesSecurity updateSeriesSecurity(SeriesSecurity seriesSecurity);

	public PageInfo<SeriesSecurity> findSeriesSecuritysForPageByOrgIdAndDailyDirectoryId(
			Long dailyDirectoryId, Long orgId, Integer page, Integer rows, String sidx, String sord);

	public Long getDailyDirectoryType();
}