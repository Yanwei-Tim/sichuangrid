package com.tianque.working.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.working.domain.GridManagementNormal;

public interface GridManagementNormalService {
	public GridManagementNormal getGridManagementNormalById(Long id);

	public GridManagementNormal addGridManagementNormal(GridManagementNormal gridManagementNormal);

	public void deleteGridManagementNormalById(Long id);

	public GridManagementNormal updateGridManagementNormal(GridManagementNormal gridManagementNormal);

	public PageInfo<GridManagementNormal> findGridManagementNormalsForPageByOrgIdAndDailyDirectoryId(
			Long dailyDirectoryId, Long orgId, Integer page, Integer rows, String sidx, String sord);

	public Long getDailyDirectoryType();
}