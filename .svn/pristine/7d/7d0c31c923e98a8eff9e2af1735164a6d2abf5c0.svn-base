package com.tianque.gis.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.gis.domain.vo.ActualUnitVo;
import com.tianque.gis.domain.vo.GisCountNumVo;

public interface SearchGisActualUnitService {
	public List<GisCountNumVo> countActualHouseByOrgId(Long org);

	public PageInfo<ActualUnitVo> searchActualUnitforGisByorgIdAndQueryStr(String queryStr,
			Long orgId, Integer page, Integer rows, String sidx, String sord);

	public ActualUnitVo getActualUnitDatialInfoByUnitId(Long unitId, Long orgId);

	public PageInfo<ActualUnitVo> searchKeyUnitListSearchByOrgId(Long orgId, Integer page,
			Integer rows, String sidx, String sord);

	public List<ActualUnitVo> listAllBindingActualUnitVo(final Long orgId);
}
