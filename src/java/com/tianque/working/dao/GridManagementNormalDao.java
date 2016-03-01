package com.tianque.working.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.working.domain.GridManagementNormal;

public interface GridManagementNormalDao {
	GridManagementNormal getGridManagementNormalById(Long id);

	GridManagementNormal addGridManagementNormal(GridManagementNormal gridManagementNormal);

	void deleteGridManagementNormalById(Long id);

	GridManagementNormal updateGridManagementNormal(GridManagementNormal gridManagementNormal);

	PageInfo<GridManagementNormal> findGridManagementNormalsForPageByOrgIdAndDailyDirectoryId(
			String dailyDirectoryIds, Long orgId, Integer page, Integer rows, String sidx,
			String sord);
}
