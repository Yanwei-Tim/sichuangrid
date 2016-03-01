package com.tianque.baseInfo.laborEmploymentUnit.service;

import com.tianque.baseInfo.laborEmploymentUnit.domain.LaborEmploymentUnit;
import com.tianque.baseInfo.laborEmploymentUnit.domain.SearchLaborEmploymentUnitVo;
import com.tianque.core.vo.PageInfo;

public interface SearchLaborEmploymentUnitService {

	public PageInfo<LaborEmploymentUnit> findLaborEmploymentUnitByQueryCondition(
			SearchLaborEmploymentUnitVo searchLaborEmploymentUnitVo, Integer page, Integer rows,
			String sidx, String sord);

}
