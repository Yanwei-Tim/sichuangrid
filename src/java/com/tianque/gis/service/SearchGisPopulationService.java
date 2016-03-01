package com.tianque.gis.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.gis.domain.vo.PopulationVo;

public interface SearchGisPopulationService {

	public PageInfo<PopulationVo> findPopulationsByOrgId(Long orgId, Integer page, Integer rows,
			String sidx, String sord);

	public List<PopulationVo> findPopulationByPersonId(Long personId);

	public PageInfo<PopulationVo> getFurtherStepGisPopulationInfoByPersonType(Long orgId,
			String personType, String queryStr, Integer page, Integer rows, String sidx, String sord);

	public PageInfo<PopulationVo> getFurtherStepGisPopulationInfoByPersonTypeList(Long orgId,
			List<String> personType, String queryStr, Integer page, Integer rows, String sidx,
			String sord);

	public List<PopulationVo> findGisPopulationByOrgid(final Long orgid);

	public PopulationVo shiftGisPopulationById(final Long id);
}
