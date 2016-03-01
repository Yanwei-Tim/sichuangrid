package com.tianque.baseInfo.laborEmploymentUnit.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.actualCompany.domain.ActualCompany;
import com.tianque.baseInfo.laborEmploymentUnit.domain.LaborEmploymentUnit;
import com.tianque.baseInfo.laborEmploymentUnit.domain.SearchLaborEmploymentUnitVo;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;

@Repository("searchLaborEmploymentUnitDao")
public class SearchLaborEmploymentUnitDaoImpl extends AbstractBaseDao implements
		SearchLaborEmploymentUnitDao {

	@Override
	public PageInfo<LaborEmploymentUnit> findLaborEmploymentUnitByQueryCondition(
			SearchLaborEmploymentUnitVo searchLaborEmploymentUnitVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		searchLaborEmploymentUnitVo.setSortField(sidx);
		searchLaborEmploymentUnitVo.setOrder(sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchLaborEmploymentUnit.countLaborEmploymentUnits", searchLaborEmploymentUnitVo);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		List<ActualCompany> list = getSqlMapClientTemplate().queryForList(
				"searchLaborEmploymentUnit.searchLaborEmploymentUnits",
				searchLaborEmploymentUnitVo, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<LaborEmploymentUnit> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<LaborEmploymentUnit> pageInfo = new PageInfo<LaborEmploymentUnit>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}
}
