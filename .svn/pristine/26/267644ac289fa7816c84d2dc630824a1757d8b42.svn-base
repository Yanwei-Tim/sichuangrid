package com.tianque.xichang.working.poorPeople.dao;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.xichang.working.domain.ReportGroupCount;
import com.tianque.xichang.working.poorPeople.domain.PoorPeople;

/**
 * @ClassName: PoorPeopleDao
 * @Description: 三本台账-困难群众台账-DAO接口
 * @author wangxiaohu wsmalltiger@163.com
 * @date Dec 24, 2013 11:23:45 AM
 */
public interface PoorPeopleDao {

	PageInfo findUndoPoorPeopleForList(PoorPeople poorPeople);

	PoorPeople addPoorPeople(PoorPeople poorPeople);

	PoorPeople updatePoorPeople(PoorPeople poorPeople);

	PoorPeople getPoorPeopleById(Long id);

	void deletePoorPeople(String id);

	/** 根据ID批量删除困难群众台账信息 */
	public void deletePoorPeopleByIds(String[] ids);

	List<PoorPeople> getPeopleAspirationsByIdCardNo(PoorPeople poorPeople);

	List<PoorPeople> getPeopleAspirationsBySerialNumber(PoorPeople poorPeople);

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
