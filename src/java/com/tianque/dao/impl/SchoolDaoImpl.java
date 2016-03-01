package com.tianque.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SchoolDao;
import com.tianque.domain.School;
import com.tianque.jms.OperateMode;
import com.tianque.jms.msg.SiteMsg;
import com.tianque.jms.sender.ActiveMQMessageSender;

@Repository("schoolDao")
public class SchoolDaoImpl extends AbstractBaseDao implements SchoolDao {

	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	@SuppressWarnings("unchecked")
	private PageInfo<School> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<School> pageInfo = new PageInfo<School>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<School> finallSchoolList(School school, Integer pageNum,
			Integer pageSize, String sortField, String order, Long isEmphasis) {

		if (isStrotFieldAvaliable(sortField)) {
			school.setSortField(sortField);
		}
		school.setOrder(order);
		/*
		 * Map<String, Object> map = new HashMap<String, Object>(); if
		 * (isStrotFieldAvaliable(sortField)) { map.put("sortField", sortField);
		 * } map.put("order", order); map.put("chineseName",
		 * school.getChineseName()); map.put("address", school.getAddress()); if
		 * (school.getType() != null) { map.put("type",
		 * school.getType().getId()); } if (school.getKind() != null) {
		 * map.put("kind", school.getKind().getId()); }
		 * map.put("orgInternalCode", school.getOrgInternalCode());
		 * map.put("englishName", school.getEnglishName()); map.put("webSite",
		 * school.getWebSite()); map.put("president", school.getPresident());
		 * map.put("fax", school.getFax()); map.put("email", school.getEmail());
		 * map.put("personLiable", school.getPersonLiable());
		 * map.put("personLiableTelephone", school.getPersonLiableTelephone());
		 * map.put("personLiableMobileNumber", school
		 * .getPersonLiableMobileNumber()); map.put("minAtSchoolHeadcount",
		 * school.getMinAtSchoolHeadcount()); map.put("maxAtSchoolHeadcount",
		 * school.getMaxAtSchoolHeadcount()); map.put("isEmphasis", isEmphasis);
		 */

		school.setIsLogOut(isEmphasis);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"school.countSchool", school);
		List<School> list = getSqlMapClientTemplate().queryForList(
				"school.findSchoolList", school, (pageNum - 1) * pageSize,
				pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public School addSchool(School school) {
		Long id = (Long) getSqlMapClientTemplate().insert("school.addSchool",
				school);
		return getSimpleSchoolById(id);
	}

	@Override
	public void deleteSchoolById(Long id) {
		// 更改f
//		SiteMsg msg = new SiteMsg(getSimpleSchoolById(id), OperateMode.DELETE);
		getSqlMapClientTemplate().delete("school.deleteSchoolById", id);
//		activeMQMessageSender.send(msg);
	}

	@Override
	public School updateSchool(School school) {
		getSqlMapClientTemplate().update("school.updateSchool", school);
		return getSimpleSchoolById(school.getId());
	}

	@Override
	public School getSimpleSchoolById(Long id) {
		return (School) getSqlMapClientTemplate().queryForObject(
				"school.getSimpleSchoolById", id);
	}

	@Override
	public School findSchoolByChineseNameAndOrgId(String chineseName, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("chineseName", chineseName);
		map.put("orgId", orgId);
		return (School) getSqlMapClientTemplate().queryForObject(
				"school.findSchoolByChineseNameAndOrgId", map);
	}

	@Override
	public List<School> searchSchoolForExport(School school, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		if (isStrotFieldAvaliable(sortField)) {
			school.setSortField(sortField);
		}
		school.setOrder(order);
		if (school.getIsEmphasis() != null) {
			school.setIsLogOut(school.getIsEmphasis() ? 1l : 0l);
		}
		/*
		 * Map<String, Object> map = new HashMap<String, Object>(); if
		 * (isStrotFieldAvaliable(sortField)) { map.put("sortField", sortField);
		 * } map.put("order", order); map.put("isEmphasis",
		 * school.getIsEmphasis()); map.put("chineseName",
		 * school.getChineseName()); map.put("address", school.getAddress());
		 * map.put("type", school.getType().getId()); map.put("kind",
		 * school.getKind().getId()); map.put("orgInternalCode",
		 * school.getOrgInternalCode()); map.put("englishName",
		 * school.getEnglishName()); map.put("webSite", school.getWebSite());
		 * map.put("president", school.getPresident()); map.put("fax",
		 * school.getFax()); map.put("email", school.getEmail());
		 * map.put("personLiable", school.getPersonLiable());
		 * map.put("personLiableTelephone", school.getPersonLiableTelephone());
		 * map.put("personLiableMobileNumber",
		 * school.getPersonLiableMobileNumber());
		 * map.put("minAtSchoolHeadcount", school.getMinAtSchoolHeadcount());
		 * map.put("maxAtSchoolHeadcount", school.getMaxAtSchoolHeadcount());
		 * map.put("attentionExtentId", school.getAttentionExtent());
		 * map.put("remark", school.getRemark());
		 */

		if (pageNum == null) {
			return getSqlMapClientTemplate().queryForList(
					"school.findSchoolList", school);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"school.findSchoolList", school, (pageNum - 1) * pageSize,
					pageSize);
		}
	}

	public List findSchoolByNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode) {
		Map map = new HashMap();
		map.put("name", name);
		map.put("orgInternalCode", orgInternalCode);
		return getSqlMapClientTemplate().queryForList(
				"school.findSchoolNameAndPinyinAndOrgInternalCode", map);
	}

	@Override
	public School getSchoolByName(String chineseName, Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", chineseName);
		map.put("id", id);
		School domain = (School) getSqlMapClientTemplate().queryForObject(
				"school.getSchoolByName", map);
		return domain;
	}

	@Override
	public void updateEmphasiseById(Long id, Long isEmphasis,
			String logoutReason, Date logoutDate) {
		Map<String, Object> map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", isEmphasis);
		map.put("logOutReason", logoutReason);
		map.put("logOutTime", logoutDate);
		getSqlMapClientTemplate().update("school.updateEmphasiseById", map);

	}

	@Override
	public Integer getCount(School school) {
		if (school.getIsEmphasis() != null) {
			school.setIsLogOut(school.getIsEmphasis() ? 1L : 0L);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"school.countSchool", school);
	}

	@Override
	public List<String> autoCompleteSchoolName(String schoolName) {
		return getSqlMapClientTemplate().queryForList(
				"school.autoCompleteSchoolName", schoolName);
	}

}
