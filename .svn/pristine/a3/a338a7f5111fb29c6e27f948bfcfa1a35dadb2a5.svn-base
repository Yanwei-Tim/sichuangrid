package com.tianque.xichang.working.steadyWork.dao;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.core.vo.Pager;
import com.tianque.xichang.working.domain.ReportGroupCount;
import com.tianque.xichang.working.steadyWork.domain.PeopleInfo;
import com.tianque.xichang.working.steadyWork.domain.SteadyWork;

public interface SteadyWorkDao {

	SteadyWork addSteadyWork(SteadyWork steadyWork);

	SteadyWork updateSteadyWork(SteadyWork steadyWork);

	SteadyWork getSteadyWorkById(Long id);

	PageInfo<SteadyWork> findUndoSteadyWorkForPageByOrgInternalCode(
			SteadyWork steadyWork, Long orgId, Integer state, Pager pager);

	boolean deleteSteadyWorkByIds(List<Long> ids);

	/**
	 * 根据条件查询
	 * 
	 * @param steadyWork
	 * @param peopleInfo
	 * @param pager
	 * @return
	 */
	PageInfo findSteadyWorkForPageByCondition(SteadyWork steadyWork,
			PeopleInfo peopleInfo, Integer state, Pager pager);

	/**
	 * 根据编号得到稳定工作台账信息
	 * 
	 * @param steadyWork
	 * @return
	 */
	SteadyWork getSteadyWorkBySerialNumber(String serialNumber);

	public List<ReportGroupCount> getReportGroupCount(Map searchMap);
	
	public List getUnfinishByMonth(Map searchMap);
	
	public List<ReportGroupCount> getReportByFinishtypeAndItemcategory(Map searchMap);

	public List<ReportGroupCount> getReportByIsFinishAndItemcategory(Map searchMap);

	public List<ReportGroupCount> getReportByReportToCityEndAndItemcategory(Map searchMap);

	public List<ReportGroupCount> getReportByReportToTownEndAndItemcategory(Map searchMap);

	public List<ReportGroupCount> getReportByVillageOrTownReportToCityAndItemcategory(Map searchMap);

	public List<ReportGroupCount> getReportByCreateOrgLevelAndItemcategory(Map searchMap);

	public List<ReportGroupCount> getReportByFinishOrgLevelAndItemcategory(Map searchMap);
	
	public List getReportToCityAndItemcategory(Map searchMap);

	public List<ReportGroupCount> getReportToTownAndItemcategory(Map searchMap);
}
