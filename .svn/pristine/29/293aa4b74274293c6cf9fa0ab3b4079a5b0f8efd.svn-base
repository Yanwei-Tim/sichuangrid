package com.tianque.xichang.working.poorPeople.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.xichang.working.domain.ReportGroupCount;
import com.tianque.xichang.working.poorPeople.dao.PoorPeopleDao;
import com.tianque.xichang.working.poorPeople.domain.PoorPeople;

/**
 * @ClassName: PoorPeopleDaoImpl
 * @Description: 三本台账-困难群众台账-DAO接口实现类
 * @author wangxiaohu wsmalltiger@163.com
 * @date Dec 24, 2013 11:25:38 AM
 */
@Repository("poorPeopleDao")
public class PoorPeopleDaoImpl extends AbstractBaseDao implements PoorPeopleDao {

	@Override
	public PageInfo findUndoPoorPeopleForList(PoorPeople poorPeople) {
		PageInfo<PoorPeople> pageInfo = new PageInfo<PoorPeople>();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"poorPeople.findUndoPoorPeopleForListCount", poorPeople);
		int pageCount = 0;
		if ((countNum % poorPeople.getRows()) == 0) {
			pageCount = countNum / poorPeople.getRows();
		} else {
			pageCount = countNum / poorPeople.getRows() + 1;
		}
		poorPeople.setPage(poorPeople.getPage() > pageCount ? pageCount
				: poorPeople.getPage());
		if (countNum > 0) {
			List<PoorPeople> list = getSqlMapClientTemplate().queryForList(
					"poorPeople.findUndoPoorPeopleForList", poorPeople,
					(poorPeople.getPage() - 1) * poorPeople.getRows(),
					poorPeople.getRows());
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<PoorPeople>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(poorPeople.getPage());
		pageInfo.setPerPageSize(poorPeople.getRows());
		return pageInfo;
	}

	@Override
	public PoorPeople addPoorPeople(PoorPeople poorPeople) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"poorPeople.addPoorPeople", poorPeople);
		return getPoorPeopleById(id);
	}

	@Override
	public void deletePoorPeople(String id) {
		getSqlMapClientTemplate().delete("poorPeople.deletePoorPeople", id);
	}

	@Override
	public PoorPeople getPoorPeopleById(Long id) {
		return (PoorPeople) getSqlMapClientTemplate().queryForObject(
				"poorPeople.getPoorPeopleById", id);
	}

	@Override
	public PoorPeople updatePoorPeople(PoorPeople poorPeople) {
		getSqlMapClientTemplate().update("poorPeople.updatePoorPeople",
				poorPeople);
		return getPoorPeopleById(poorPeople.getId());
	}

	@Override
	public List<PoorPeople> getPeopleAspirationsByIdCardNo(PoorPeople poorPeople) {
		return getSqlMapClientTemplate().queryForList(
				"poorPeople.getPeopleAspirationsByIdCardNo", poorPeople);
	}

	@Override
	public List<PoorPeople> getPeopleAspirationsBySerialNumber(
			PoorPeople poorPeople) {
		return getSqlMapClientTemplate().queryForList(
				"poorPeople.getPeopleAspirationsBySerialNumber", poorPeople);
	}

	@Override
	public List<ReportGroupCount> getReportGroupCount(Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate()
				.queryForList("poorPeople.getReportGroupCount", searchMap);
		return resultList;
	}

	@Override
	public List getUnfinishByMonth(Map searchMap) {
		List resultList = getSqlMapClientTemplate().queryForList(
				"poorPeople.getUnfinishByMonth", searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportByIsFinishAndItemcategory(
			Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate()
				.queryForList("poorPeople.getReportByIsFinishAndItemcategory",
						searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportByFinishtypeAndItemcategory(
			Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate()
				.queryForList(
						"poorPeople.getReportByFinishtypeAndItemcategory",
						searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportByReportToCityEndAndItemcategory(
			Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate()
				.queryForList(
						"poorPeople.getReportByReportToCityEndAndItemcategory",
						searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportByReportToTownEndAndItemcategory(
			Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate()
				.queryForList(
						"poorPeople.getReportByReportToTownEndAndItemcategory",
						searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportByVillageOrTownReportToCityAndItemcategory(
			Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate()
				.queryForList(
						"poorPeople.getReportByVillageOrTownReportToCityAndItemcategory",
						searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportByCreateOrgLevelAndItemcategory(
			Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate()
				.queryForList(
						"poorPeople.getReportByCreateOrgLevelAndItemcategory",
						searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportByFinishOrgLevelAndItemcategory(
			Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate()
				.queryForList(
						"poorPeople.getReportByFinishOrgLevelAndItemcategory",
						searchMap);
		return resultList;
	}

	@Override
	public List getReportToCityAndItemcategory(Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate()
				.queryForList("poorPeople.getReportToCityAndItemcategory",
						searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportToTownAndItemcategory(Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate()
				.queryForList("poorPeople.getReportToTownAndItemcategory",
						searchMap);
		return resultList;
	}

	@Override
	public void deletePoorPeopleByIds(String[] ids) {
		if (ids == null || ids.length == 0) {
			return;
		}
		getSqlMapClientTemplate().delete("poorPeople.deletePoorPeopleByIds",
				ids);
	}
}
