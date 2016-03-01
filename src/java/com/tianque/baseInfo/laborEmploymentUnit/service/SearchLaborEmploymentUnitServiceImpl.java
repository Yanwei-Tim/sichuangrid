package com.tianque.baseInfo.laborEmploymentUnit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.laborEmploymentUnit.dao.SearchLaborEmploymentUnitDao;
import com.tianque.baseInfo.laborEmploymentUnit.domain.LaborEmploymentUnit;
import com.tianque.baseInfo.laborEmploymentUnit.domain.SearchLaborEmploymentUnitVo;
import com.tianque.core.vo.PageInfo;

@Service("searchLaborEmploymentUnitService")
@Transactional
public class SearchLaborEmploymentUnitServiceImpl implements SearchLaborEmploymentUnitService {

	@Autowired
	private SearchLaborEmploymentUnitDao searchLaborEmploymentUnitDao;

	@Override
	public PageInfo<LaborEmploymentUnit> findLaborEmploymentUnitByQueryCondition(
			SearchLaborEmploymentUnitVo searchLaborEmploymentUnitVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		return searchLaborEmploymentUnitDao.findLaborEmploymentUnitByQueryCondition(
				searchLaborEmploymentUnitVo, pageNum, pageSize, sidx, sord);
	}

}
