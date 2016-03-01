package com.tianque.xichang.working.steadyWork.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.vo.PageInfo;
import com.tianque.core.vo.Pager;
import com.tianque.dao.impl.BaseDaoImpl;
import com.tianque.xichang.working.domain.ReportGroupCount;
import com.tianque.xichang.working.flow.constant.AccountType;
import com.tianque.xichang.working.steadyWork.dao.SteadyWorkDao;
import com.tianque.xichang.working.steadyWork.domain.PeopleInfo;
import com.tianque.xichang.working.steadyWork.domain.SteadyWork;

@Repository("steadyWorkDao")
public class SteadyWorkDaoImpl extends BaseDaoImpl<SteadyWork> implements
		SteadyWorkDao {

	@Override
	public SteadyWork addSteadyWork(SteadyWork steadyWork) {
		return add(steadyWork);
	}

	@Override
	public boolean deleteSteadyWorkByIds(List<Long> ids) {
		return delete(ids, "deleteSteadyWorkByIds");
	}

	@Override
	public SteadyWork getSteadyWorkById(Long id) {
		return get(id);
	}

	@Override
	public SteadyWork updateSteadyWork(SteadyWork steadyWork) {
		return update(steadyWork);
	}

	@Override
	public PageInfo<SteadyWork> findUndoSteadyWorkForPageByOrgInternalCode(
			SteadyWork steadyWork, Long orgId, Integer state, Pager pager) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("steadyWork", steadyWork);
		map.put("state", state);
		map.put("orgId", orgId);
		map.put("accountType", AccountType.STEADYWORK);
		return findPagerBySearchVo(map, pager,
				"UndoSteadyWorkForPageByOrgInternalCode");
	}

	@Override
	public PageInfo findSteadyWorkForPageByCondition(SteadyWork steadyWork,
			PeopleInfo peopleInfo, Integer state, Pager pager) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("steadyWork", steadyWork);
		map.put("peopleInfo", peopleInfo);
		map.put("state", state);
		map.put("accountType", AccountType.STEADYWORK);
		return findPageByMap(map, pager, "SteadyWorkForPageByCondition");
	}

	@Override
	public SteadyWork getSteadyWorkBySerialNumber(String serialNumber) {
		return (SteadyWork) getSqlMapClientTemplate().queryForObject(
				"steadyWork.getSteadyWorkBySerialNumber", serialNumber);
	}

	@Override
	public List<ReportGroupCount> getReportGroupCount(Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate()
				.queryForList("steadyWork.getReportGroupCount", searchMap);
		return resultList;
	}

	@Override
	public List getUnfinishByMonth(Map searchMap) {
		List resultList = getSqlMapClientTemplate().queryForList(
				"steadyWork.getUnfinishByMonth", searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportByIsFinishAndItemcategory(
			Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate()
				.queryForList("steadyWork.getReportByIsFinishAndItemcategory",
						searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportByFinishtypeAndItemcategory(
			Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate()
				.queryForList(
						"steadyWork.getReportByFinishtypeAndItemcategory",
						searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportByReportToCityEndAndItemcategory(
			Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate()
				.queryForList(
						"steadyWork.getReportByReportToCityEndAndItemcategory",
						searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportByReportToTownEndAndItemcategory(
			Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate()
				.queryForList(
						"steadyWork.getReportByReportToTownEndAndItemcategory",
						searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportByVillageOrTownReportToCityAndItemcategory(
			Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate()
				.queryForList(
						"steadyWork.getReportByVillageOrTownReportToCityAndItemcategory",
						searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportByCreateOrgLevelAndItemcategory(
			Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate()
				.queryForList(
						"steadyWork.getReportByCreateOrgLevelAndItemcategory",
						searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportByFinishOrgLevelAndItemcategory(
			Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate()
				.queryForList(
						"steadyWork.getReportByFinishOrgLevelAndItemcategory",
						searchMap);
		return resultList;
	}

	@Override
	public List getReportToCityAndItemcategory(Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate()
				.queryForList("steadyWork.getReportToCityAndItemcategory",
						searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportToTownAndItemcategory(Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate()
				.queryForList("steadyWork.getReportToTownAndItemcategory",
						searchMap);
		return resultList;
	}
}
