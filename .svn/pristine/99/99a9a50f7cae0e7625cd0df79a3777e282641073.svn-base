package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.mentalPatient.dao.SearchMentalPatientDao;
import com.tianque.baseInfo.mentalPatient.domain.MentalPatient;
import com.tianque.baseInfo.mentalPatient.domain.MentalPatientTypeCount;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchMentalPatientVo;

@Repository("searchMentalPatientDao")
public class SearchMentalPatientDaoImpl extends AbstractBaseDao implements
		SearchMentalPatientDao {

	@Override
	public PageInfo<MentalPatient> searchMentalPatient(
			SearchMentalPatientVo condition, int pageNum, int pageSize,
			String sortField, String order) {
		if (condition == null) {
			return emptyPage(pageSize);
		}
		condition.setSortField(sortField);
		condition.setOrder(order);

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchMentalPatient.countSearchMentalPatient", condition);
		@SuppressWarnings("unchecked")
		List<MentalPatient> list = getSqlMapClientTemplate().queryForList(
				"searchMentalPatient.searchMentalPatient", condition,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public List<MentalPatient> searchMentalPatientForExport(
			SearchMentalPatientVo condition, Integer page, Integer rows,
			String sortField, String order) {
		condition.setSortField(sortField);
		condition.setOrder(order);
		if (page == null) {
			return getSqlMapClientTemplate().queryForList(
					"searchMentalPatient.searchMentalPatient", condition);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"searchMentalPatient.searchMentalPatient", condition,
					(page - 1) * rows, rows);
		}
	}

	/*
	 * private Map getConditionMap(SearchMentalPatientVo condition, String
	 * sortField, String order) { Map<String, Object> map = new HashMap<String,
	 * Object>(); if (condition!=null) { map.put("orgInternalCode",
	 * condition.getOrgInternalCode()); map.put("genderId",
	 * condition.getGenderId()); map.put("name", condition.getName());
	 * map.put("idCardNo", condition.getIdCardNo()); map.put("startBirthday",
	 * condition.getStartBirthday()); map.put("endBirthday",
	 * condition.getEndBirthday()); map.put("dangerLevelId",
	 * condition.getDangerLevelId()); map.put("cureDepartment",
	 * condition.getCureDepartment()); map.put("nativeProvince",
	 * condition.getNativeProvince()); map.put("nativeCity",
	 * condition.getNativeCity()); map.put("nativeDistrict",
	 * condition.getNativeDistrict()); map.put("nativePlaceAddress",
	 * condition.getNativePlaceAddress());
	 * map.put("currentAddress",condition.getCurrentAddress());
	 * map.put("nation", condition.getNation()); map.put("politicalBackground",
	 * condition.getPoliticalBackground()); map.put("maritalState",
	 * condition.getMaritalState()); map.put("schooling",
	 * condition.getSchooling()); map.put("career", condition.getCareer());
	 * map.put("isTreat", condition.getIsTreat()); map.put("workUnit",
	 * condition.getWorkUnit()); map.put("startStature",
	 * condition.getStartStature()); map.put("endStature",
	 * condition.getEndStature()); map.put("faith", condition.getFaith());
	 * map.put("bloodType", condition.getBloodType()); map.put("email",
	 * condition.getEmail()); map.put("telephone", condition.getTelephone());
	 * map.put("mobileNumber", condition.getMobileNumber());
	 * map.put("isEmphasis",condition.getIsEmphasis()); if
	 * (StringUtil.isStringAvaliable(sortField)) { map.put("sortField",
	 * sortField); } map.put("order", order); } return map; }
	 */

	private PageInfo<MentalPatient> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List<MentalPatient> list) {
		PageInfo<MentalPatient> pageInfo = new PageInfo<MentalPatient>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private PageInfo<MentalPatient> emptyPage(int pageSize) {
		PageInfo<MentalPatient> pageInfo = new PageInfo<MentalPatient>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<MentalPatient>());
		return pageInfo;
	}

	public List findSuperiorVisitByNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode) {
		Map map = new HashMap();
		map.put("name", name);
		map.put("orgInternalCode", orgInternalCode);
		return getSqlMapClientTemplate()
				.queryForList(
						"searchMentalPatient.findMentalPatientsNameAndPinyinAndOrgInternalCode",
						map);
	}

	@Override
	public List<MentalPatientTypeCount> findMentalPatientCountList(
			String orgInternalCode) {
		if (null == orgInternalCode) {
			return null;
		}
		Map<String, String> query = new HashMap<String, String>();
		query.put("orgInternalCode", orgInternalCode);
		List<MentalPatientTypeCount> mentalPatientTypeCountList = (List<MentalPatientTypeCount>) getSqlMapClientTemplate()
				.queryForList("searchMentalPatient.findMentalPatientCountList",
						query);
		return mentalPatientTypeCountList;
	}

	@Override
	public List<MentalPatientTypeCount> findHelped(String orgInternalCode) {
		if (null == orgInternalCode) {
			return null;
		}
		Map<String, String> query = new HashMap<String, String>();
		query.put("orgInternalCode", orgInternalCode);
		List<MentalPatientTypeCount> mentalPatientTypeCountList = (List<MentalPatientTypeCount>) getSqlMapClientTemplate()
				.queryForList("searchMentalPatient.findHelped", query);
		return mentalPatientTypeCountList;
	}

	@Override
	public Long getCount(String orgInternalCode) {
		if (null == orgInternalCode) {
			return null;
		}
		return (Long) getSqlMapClientTemplate().queryForObject(
				"searchMentalPatient.getCount", orgInternalCode);
	}

	@Override
	public Integer getCounts(SearchMentalPatientVo searchMentalPatientVo) {
		// TODO Auto-generated method stub
		if (null == searchMentalPatientVo) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchMentalPatient.countSearchMentalPatient",
				searchMentalPatientVo);
	}

}
