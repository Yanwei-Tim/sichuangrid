package com.tianque.xichang.working.peopleAspiration.dao;

import java.util.List;
import java.util.Map;

import com.tianque.core.base.BaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.xichang.working.domain.ReportGroupCount;
import com.tianque.xichang.working.peopleAspiration.domain.PeopleAspirations;
import com.tianque.xichang.working.peopleAspiration.domain.vo.SearchPeopleAspirationsVo;

/**
 * 民生诉求表:数据操作层接口
 * 
 * @author
 * @date 2013-12-23 17:57:24
 */
public interface PeopleAspirationsDao extends
		BaseDao<PeopleAspirations, SearchPeopleAspirationsVo> {

	/**
	 * 根据身份证得到民生诉求台账信息
	 * 
	 * @param idCardNo
	 * @return
	 */
	PeopleAspirations getPeopleAspirationsByIdCardNo(String idCardNo);

	/**
	 * 根据编号得到民生诉求台账信息
	 * 
	 * @param serialNumber
	 * @return
	 */
	PeopleAspirations getPeopleAspirationsBySerialNumber(String serialNumber);

	/**
	 * 已办：根据SearchVo进行查询(提供分页、查找、排序功能)
	 * 
	 * @param searchVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo findDonePagerBySearchVo(SearchPeopleAspirationsVo searchVo,
			Integer page, Integer rows, String sidx, String sord);

	public List<ReportGroupCount> getReportGroupCount(Map searchMap);

	public List getUnfinishByMonth(Map searchMap);

	public List<ReportGroupCount> getReportByFinishtypeAndItemcategory(
			Map searchMap);

	public List<ReportGroupCount> getReportByIsFinishAndItemcategory(
			Map searchMap);

	public List<ReportGroupCount> getReportByReportToCityEndAndItemcategory(
			Map searchMap);

	public List<ReportGroupCount> getReportByReportToTownEndAndItemcategory(
			Map searchMap);

	public List<ReportGroupCount> getReportByVillageOrTownReportToCityAndItemcategory(
			Map searchMap);

	public List<ReportGroupCount> getReportByCreateOrgLevelAndItemcategory(
			Map searchMap);

	public List<ReportGroupCount> getReportByFinishOrgLevelAndItemcategory(
			Map searchMap);

	public List getReportToCityAndItemcategory(Map searchMap);

	public List<ReportGroupCount> getReportToTownAndItemcategory(Map searchMap);

	public PeopleAspirations updatePeopleAspirations(
			PeopleAspirations peopleAspirations);
}
