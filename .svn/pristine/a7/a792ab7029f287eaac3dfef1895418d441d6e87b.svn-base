package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.HospitalDao;
import com.tianque.domain.Hospital;
import com.tianque.domain.vo.SearchHospitalVo;
import com.tianque.jms.OperateMode;
import com.tianque.jms.msg.SiteMsg;
import com.tianque.jms.sender.ActiveMQMessageSender;

@Repository("hospitalDao")
public class HospitalDaoImpl extends AbstractBaseDao implements HospitalDao {
	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	@Override
	public Hospital addHospital(Hospital hospital) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"hospital.addHospital", hospital);
		return getHospitalById(id);
	}

	@Override
	public Hospital getHospitalById(Long id) {
		return (Hospital) getSqlMapClientTemplate().queryForObject(
				"hospital.getHospitalById", id);
	}

	@Override
	public void deleteHospitalById(Long id) {
//		SiteMsg msg = new SiteMsg(getHospitalById(id), OperateMode.DELETE);
		getSqlMapClientTemplate().delete("hospital.deleteHospitalById", id);
//		activeMQMessageSender.send(msg);
	}

	@Override
	public Hospital updateHospital(Hospital hospital) {
		getSqlMapClientTemplate().update("hospital.updateHospital", hospital);
		return getHospitalById(hospital.getId());
	}

	@Override
	public void updateEmphasiseById(Long id, Boolean isEmphasis,
			String logoutReason, Date logoutDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isEmphasis", isEmphasis);
		map.put("logOutReason", logoutReason);
		map.put("logOutTime", logoutDate);
		map.put("id", id);
		getSqlMapClientTemplate().update("hospital.updateEmphasiseById", map);
	}

	@Override
	public Hospital getHospitalByPlaceNameAndOrgId(String hospitalName, Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hospitalName", hospitalName);
		map.put("orgId", id);
		return (Hospital) getSqlMapClientTemplate().queryForObject(
				"hospital.getHospitalByMap", map);
	}

	@Override
	public PageInfo<Hospital> searchHospitalForPage(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchHospitalVo searchHospitalVo) {

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchHospital.countSearchHospital", searchHospitalVo);
		if (countNum == 0) {
			return createPageInfo(0, pageSize, 0, new ArrayList<Hospital>());
		}
		if (searchHospitalVo != null) {
			searchHospitalVo.setSortField(sortField);
			searchHospitalVo.setOrder(order);
		}
		int pageCount = countNum % pageSize == 0 ? countNum / pageSize
				: countNum / pageSize + 1;
		List<Hospital> list;
		if (pageNum != null) {
			pageNum = pageNum > pageCount ? pageCount : pageNum;
			list = getSqlMapClientTemplate().queryForList(
					"searchHospital.searchSearchHospital", searchHospitalVo,
					(pageNum - 1) * pageSize, pageSize);
		} else {
			pageNum = 1;
			list = getSqlMapClientTemplate().queryForList(
					"searchHospital.searchHospital", searchHospitalVo);
		}
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<Hospital> createPageInfo(Integer pageNum,
			Integer pageSize, Integer countNum, List<Hospital> list) {
		PageInfo<Hospital> pageInfo = new PageInfo<Hospital>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public Integer getCount(SearchHospitalVo searchHospitalVo) {
		if (null == searchHospitalVo) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchHospital.countHospital", searchHospitalVo);
	}

}
