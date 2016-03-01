package com.tianque.working.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.working.dao.GridManagementNormalDao;
import com.tianque.working.domain.GridManagementNormal;

@Repository("gridManagementNormalDao")
public class GridManagementNormalDaoImpl extends AbstractBaseDao implements GridManagementNormalDao {

	@Override
	public GridManagementNormal getGridManagementNormalById(Long id) {
		return (GridManagementNormal) getSqlMapClientTemplate().queryForObject(
				"gridManagementNormal.getGridManagementNormal", id);
	}

	@Override
	public GridManagementNormal addGridManagementNormal(GridManagementNormal gridManagementNormal) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"gridManagementNormal.addGridManagementNormal", gridManagementNormal);
		return getGridManagementNormalById(id);
	}

	@Override
	public void deleteGridManagementNormalById(Long id) {
		getSqlMapClientTemplate().delete("gridManagementNormal.deleteGridManagementNormal", id);
	}

	@Override
	public GridManagementNormal updateGridManagementNormal(GridManagementNormal gridManagementNormal) {
		getSqlMapClientTemplate().update("gridManagementNormal.updateGridManagementNormal",
				gridManagementNormal);
		return getGridManagementNormalById(gridManagementNormal.getId());
	}

	@Override
	public PageInfo<GridManagementNormal> findGridManagementNormalsForPageByOrgIdAndDailyDirectoryId(
			String dailyDirectoryIds, Long orgId, Integer page, Integer rows, String sidx,
			String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dailyDirectoryId", dailyDirectoryIds);
		map.put("sortField", sidx);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"gridManagementNormal.countForPageByOrgId", map);

		List<GridManagementNormal> resultList = getSqlMapClientTemplate().queryForList(
				"gridManagementNormal.findForPageByOrgId", map, (page - 1) * rows, rows);

		return new PageInfo<GridManagementNormal>(page, rows, countNum, resultList);
	}

}
