package com.tianque.gis.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.gis.domain.vo.LocationVo;

public interface SearchLocationService {

	PageInfo<LocationVo> findLocationsByOrgId(Long orgId, Integer page, Integer rows, String sidx,
			String sord);

	PageInfo<LocationVo> findLocationsByOrgIdAndQueryStr(Long orgId, String queryStr, Integer page,
			Integer rows, String sidx, String sord);
}
