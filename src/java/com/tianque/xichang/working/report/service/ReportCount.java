package com.tianque.xichang.working.report.service;

import java.util.Date;
import java.util.List;

import com.tianque.xichang.working.domain.ReportGroupCount;

public interface ReportCount {

	/** 获得按建表类型和统计类别分组的数据*/
	List<ReportGroupCount> getReportGroupCount(Long orgId, Date start, Date end);

	/** 获得按统计类别生成的月度办理中数据*/
	List getUnfinishByMonth(Long orgId, Date start, Date end);

	/** 获得按办结类型（程序性办结、实质性办结）和项目类型统计数据*/
	List<ReportGroupCount> getReportByFinishtypeAndItemcategory(Long orgId, Date start, Date end);
	List<ReportGroupCount> getReportByFinishtypeAndItemcategory(String orgCode, Date start, Date end);

	/** 获得按是否办结（已办结）和项目类型统计数据*/
	List<ReportGroupCount> getReportByIsFinishAndItemcategory(Long orgId, Date start, Date end);

	/** 获得按办结呈报部门（市）情况和项目类型统计数据*/
	List<ReportGroupCount> getReportByReportToCityEndAndItemcategory(Long orgId, Date start, Date end);
	
	/** 获得按办结呈报部门（乡镇）情况和项目类型统计数据*/
	List<ReportGroupCount> getReportByReportToTownEndAndItemcategory(Long orgId, Date start, Date end);

	/** 获得按呈报、流转乡镇并且乡镇已办结和项目类型统计数据*/
	List<ReportGroupCount> getReportByReportToTownEndWithIsFinishAndItemcategory(Long orgId, Date start, Date end, Boolean isFinish);

	/** 获得按（村、社区呈报乡镇 或 乡镇直接建账）呈报市台账办 和项目类型统计数据*/
	List<ReportGroupCount> getReportByVillageOrTownReportToCityAndItemcategory(
			Long orgId, Date start, Date end, Long reportToTown, Long reportToCity);

	/** 按照 建账层级（1 as 市县区，2 as 乡镇街道，3 as 村社区） 和 项目类型 查询*/
	List<ReportGroupCount> getReportByCreateOrgLevelAndItemcategory(String orgCode, Date start, Date end);
	
	/** 按照 办结层级（1 as 市县区，2 as 乡镇街道，3 as 村社区） 和 项目类型 查询*/
	List<ReportGroupCount> getReportByFinishOrgLevelAndItemcategory(String orgCode, Date start, Date end);

	/** 获得按呈报市台账办 和项目类型统计数据 */
	List<ReportGroupCount> getReportToCityAndItemcategory(Long orgId,Date start, Date end, Long reportToCityEnd);

	/** 获得按市(创建包含上报到市，然后流转)流转职能部门和行政部门 和项目类型统计数据 （不考虑流转后是否已办结） */
	List<ReportGroupCount> getReportToTownAndItemcategory(Long orgId,Date start, Date end, Long reportToTownEnd);
}
