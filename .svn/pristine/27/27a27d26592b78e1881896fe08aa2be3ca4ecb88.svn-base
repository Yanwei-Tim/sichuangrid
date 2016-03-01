package com.tianque.xichang.working.peopleAspiration.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.xichang.working.domain.ReportGroupCount;
import com.tianque.xichang.working.peopleAspiration.dao.PeopleAspirationsDao;
import com.tianque.xichang.working.peopleAspiration.domain.PeopleAspirations;
import com.tianque.xichang.working.peopleAspiration.domain.vo.SearchPeopleAspirationsVo;

/**
 * 民生诉求表:数据操作层
 * 
 * @author
 * @date 2013-12-23 17:57:24
 */
@Repository("peopleAspirationsDao")
public class PeopleAspirationsDaoImpl extends
		BaseDaoImpl<PeopleAspirations, SearchPeopleAspirationsVo> implements PeopleAspirationsDao {

	@Override
	public PeopleAspirations getPeopleAspirationsByIdCardNo(String idCardNo) {
		if (idCardNo == null) {
			throw new BusinessValidationException("参数错误");
		}
		return (PeopleAspirations) getSqlMapClientTemplate().queryForObject(
				"peopleAspirations.getPeopleAspirationsByIdCardNo", idCardNo);
	}

	@Override
	public PeopleAspirations getPeopleAspirationsBySerialNumber(String serialNumber) {
		if (serialNumber == null) {
			throw new BusinessValidationException("参数错误");
		}
		return (PeopleAspirations) getSqlMapClientTemplate().queryForObject(
				"peopleAspirations.getPeopleAspirationsBySerialNumber", serialNumber);
	}

	@Override
	public PageInfo findDonePagerBySearchVo(SearchPeopleAspirationsVo searchVo, Integer page,
			Integer rows, String sidx, String sord) {
		searchVo.setSortField(sidx);
		searchVo.setOrder(sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"peopleAspirations.countDonePeopleAspirationssBySearchVo", searchVo);
		List<T> resultList = getSqlMapClientTemplate().queryForList(
				"peopleAspirations.findDonePeopleAspirationssBySearchVo", searchVo,
				(page - 1) * rows, rows);
		return new PageInfo<T>(page, rows, countNum, resultList);
	}

	@Override
	public List<ReportGroupCount> getReportGroupCount(Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate().queryForList(
				"peopleAspirations.getReportGroupCount", searchMap);
		return resultList;
	}

	@Override
	public List getUnfinishByMonth(Map searchMap) {
		List resultList = getSqlMapClientTemplate().queryForList(
				"peopleAspirations.getUnfinishByMonth", searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportByIsFinishAndItemcategory(Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate().queryForList(
				"peopleAspirations.getReportByIsFinishAndItemcategory", searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportByFinishtypeAndItemcategory(Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate().queryForList(
				"peopleAspirations.getReportByFinishtypeAndItemcategory", searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportByReportToCityEndAndItemcategory(Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate().queryForList(
				"peopleAspirations.getReportByReportToCityEndAndItemcategory", searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportByReportToTownEndAndItemcategory(Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate().queryForList(
				"peopleAspirations.getReportByReportToTownEndAndItemcategory", searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportByVillageOrTownReportToCityAndItemcategory(Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate().queryForList(
				"peopleAspirations.getReportByVillageOrTownReportToCityAndItemcategory", searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportByCreateOrgLevelAndItemcategory(Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate().queryForList(
				"peopleAspirations.getReportByCreateOrgLevelAndItemcategory", searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportByFinishOrgLevelAndItemcategory(Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate().queryForList(
				"peopleAspirations.getReportByFinishOrgLevelAndItemcategory", searchMap);
		return resultList;
	}

	@Override
	public List getReportToCityAndItemcategory(Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate().queryForList(
				"peopleAspirations.getReportToCityAndItemcategory", searchMap);
		return resultList;
	}

	@Override
	public List<ReportGroupCount> getReportToTownAndItemcategory(Map searchMap) {
		List<ReportGroupCount> resultList = getSqlMapClientTemplate().queryForList(
				"peopleAspirations.getReportToTownAndItemcategory", searchMap);
		return resultList;
	}

	@Override
	public PeopleAspirations updatePeopleAspirations(PeopleAspirations peopleAspirations) {
		getSqlMapClientTemplate().update("peopleAspirations.updatePeopleAspirations",
				peopleAspirations);
		return get(peopleAspirations.getId());
	}
}
