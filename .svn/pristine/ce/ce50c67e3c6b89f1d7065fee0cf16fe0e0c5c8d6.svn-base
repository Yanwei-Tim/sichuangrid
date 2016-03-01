package com.tianque.aidsPopulations.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.aidsPopulations.dao.SearchAidspopulationsDao;
import com.tianque.aidsPopulations.domain.Aidspopulations;
import com.tianque.aidsPopulations.domain.vo.SearchAidspopulationsVo;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;

@Repository("searchAidspopulationsDao")
public class SearchAidspopulationsDaoImpl extends AbstractBaseDao implements
		SearchAidspopulationsDao {

	@Override
	public List<Aidspopulations> searchAidspopulationsForExport(
			SearchAidspopulationsVo searchAidspopulationsVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		if (pageNum == null) {
			return getSqlMapClientTemplate().queryForList(
					"searchAidspopulations.searchAidspopulations",
					searchAidspopulationsVo);
		} else {
			return getSqlMapClientTemplate()
					.queryForList(
							"searchAidspopulations.searchAidspopulations",
							searchAidspopulationsVo, (pageNum - 1) * pageSize,
							pageSize);
		}
	}

	@Override
	public PageInfo<Aidspopulations> searchAidspopulations(
			SearchAidspopulationsVo searchAidspopulationsVo, Integer pageNum,
			Integer pageSize, String sortField, String order) {

		if (searchAidspopulationsVo == null) {
			return emptyPage(pageSize);
		}
		searchAidspopulationsVo.setSortField(sortField);
		searchAidspopulationsVo.setOrder(order);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchAidspopulations.countAidspopulations",
				searchAidspopulationsVo);
		@SuppressWarnings("unchecked")
		List<Aidspopulations> list = getSqlMapClientTemplate().queryForList(
				"searchAidspopulations.searchAidspopulations",
				searchAidspopulationsVo, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<Aidspopulations> emptyPage(int pageSize) {
		PageInfo<Aidspopulations> pageInfo = new PageInfo<Aidspopulations>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<Aidspopulations>());
		return pageInfo;
	}

	private PageInfo<Aidspopulations> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<Aidspopulations> pageInfo = new PageInfo<Aidspopulations>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public Integer getCount(SearchAidspopulationsVo searchAidspopulationsVo) {
		// TODO Auto-generated method stub
		if (null == searchAidspopulationsVo) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchAidspopulations.countAidspopulations",
				searchAidspopulationsVo);
	}
}
