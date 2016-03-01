package com.tianque.working.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.working.dao.SeriesSecurityDao;
import com.tianque.working.domain.SeriesSecurity;

@Repository("seriesSecurityDao")
public class SeriesSecurityDaoImpl extends AbstractBaseDao implements SeriesSecurityDao {

	@Override
	public SeriesSecurity getSeriesSecurityById(Long id) {
		return (SeriesSecurity) getSqlMapClientTemplate().queryForObject(
				"seriesSecurity.getSeriesSecurity", id);
	}

	@Override
	public SeriesSecurity addSeriesSecurity(SeriesSecurity seriesSecurity) {
		Long id = (Long) getSqlMapClientTemplate().insert("seriesSecurity.addSeriesSecurity",
				seriesSecurity);
		return getSeriesSecurityById(id);
	}

	@Override
	public void deleteSeriesSecurityById(Long id) {
		getSqlMapClientTemplate().delete("seriesSecurity.deleteSeriesSecurity", id);
	}

	@Override
	public SeriesSecurity updateSeriesSecurity(SeriesSecurity seriesSecurity) {
		getSqlMapClientTemplate().update("seriesSecurity.updateSeriesSecurity", seriesSecurity);
		return getSeriesSecurityById(seriesSecurity.getId());
	}

	@Override
	public PageInfo<SeriesSecurity> findSeriesSecuritysForPageByOrgIdAndDailyDirectoryId(
			String dailyDirectoryIds, Long orgId, Integer page, Integer rows, String sidx,
			String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dailyDirectoryId", dailyDirectoryIds);
		map.put("sortField", sidx);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"seriesSecurity.countForPageByOrgId", map);

		List<SeriesSecurity> resultList = getSqlMapClientTemplate().queryForList(
				"seriesSecurity.findForPageByOrgId", map, (page - 1) * rows, rows);

		return new PageInfo<SeriesSecurity>(page, rows, countNum, resultList);
	}

}
