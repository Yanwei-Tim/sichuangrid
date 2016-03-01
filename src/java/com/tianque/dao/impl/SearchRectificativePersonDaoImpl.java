package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.rectificativePerson.dao.SearchRectificativePersonDao;
import com.tianque.baseInfo.rectificativePerson.domain.RectificativePerson;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchRectificativePersonVo;

@Repository("searchRectificativePersonDao")
public class SearchRectificativePersonDaoImpl extends AbstractBaseDao implements
		SearchRectificativePersonDao {
	@Override
	public PageInfo<RectificativePerson> searchRectificativePerson(
			SearchRectificativePersonVo condition, int pageNum, int pageSize,
			String sortField, String order) {
		if (condition == null) {
			return emptyPage(pageSize);
		}
		condition.setSortField(sortField);
		condition.setOrder(order);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchRectificativePerson.countSearchRectificativePerson",
				condition);
		@SuppressWarnings("unchecked")
		List<RectificativePerson> list = getSqlMapClientTemplate()
				.queryForList(
						"searchRectificativePerson.searchRectificativePerson",
						condition, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public List<RectificativePerson> searchRectificativePersonForExport(
			SearchRectificativePersonVo condition, Integer page, Integer rows,
			String sortField, String order) {
		condition.setSortField(sortField);
		condition.setOrder(order);
		if (page == null) {
			return getSqlMapClientTemplate().queryForList(
					"searchRectificativePerson.searchRectificativePerson",
					condition);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"searchRectificativePerson.searchRectificativePerson",
					condition, (page - 1) * rows, rows);
		}
	}

	private Map getFastConditionMap(SearchRectificativePersonVo condition,
			String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (condition == null) {
			return map;
		}
		map.put("orgInternalCode", condition.getOrgInternalCode());
		map.put("name", condition.getName());
		map.put("idCardNo", condition.getIdCardNo());
		map.put("isEmphasis", condition.getIsEmphasis());
		if (!StringUtil.isStringAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);
		return map;
	}

	private Map getConditionMap(SearchRectificativePersonVo condition,
			String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (condition == null) {
			return map;
		}
		map.put("orgInternalCode", condition.getOrgInternalCode());
		map.put("genderId", condition.getGenderId());
		map.put("name", condition.getName());
		map.put("idCardNo", condition.getIdCardNo());
		map.put("executeTypeId", condition.getExecuteTypeId());
		map.put("currentAddress", condition.getCurrentAddress());
		map.put("familyAddress", condition.getFamilyAddress());
		map.put("penaltyTerm", condition.getPenaltyTerm());
		map.put("recentSituation", condition.getRecentSituation());
		map.put("helpEducator", condition.getHelpEducator());
		map.put("educatorTelephone", condition.getEducatorTelephone());
		map.put("educatorMobileNumber", condition.getEducatorMobileNumber());
		map.put("nativePlaceAddress", condition.getNativePlaceAddress());
		map.put("nativePoliceStation", condition.getNativePoliceStation());
		map.put("province", condition.getProvince());
		map.put("city", condition.getCity());
		map.put("district", condition.getDistrict());
		map.put("telephone", condition.getTelephone());
		map.put("mobileNumber", condition.getMobileNumber());
		map.put("startBirthday", condition.getStartBirthday());
		map.put("endBirthday", condition.getEndBirthday());
		map.put("rectifyStartDate", condition.getRectifyStartDate());
		map.put("rectifyEndDate", condition.getRectifyEndDate());
		map.put("isEmphasis", condition.getIsEmphasis());
		map.put("sortField", sortField);
		map.put("order", order);
		map.put("fastSearchKeyWords", condition.getFastSearchKeyWords());// 姓名、身份证
		map.put("isDeath", condition.getIsDeath());// 姓名、身份证
		return map;
	}

	private PageInfo<RectificativePerson> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List<RectificativePerson> list) {
		PageInfo<RectificativePerson> pageInfo = new PageInfo<RectificativePerson>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private PageInfo<RectificativePerson> emptyPage(int pageSize) {
		PageInfo<RectificativePerson> pageInfo = new PageInfo<RectificativePerson>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<RectificativePerson>());
		return pageInfo;
	}

	public List findRectificativePersonNameAndPinyinAndOrgInternalCode(
			String name, String orgInternalCode) {
		Map map = new HashMap();
		map.put("name", name);
		map.put("orgInternalCode", orgInternalCode);
		return getSqlMapClientTemplate()
				.queryForList(
						"searchRectificativePerson.findRectificativePersonAndPinyinAndOrgInternalCode",
						map);
	}

	@Override
	public List<RectificativePerson> searchRectificativePerson(Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("endDate", endDate);
		return getSqlMapClientTemplate()
				.queryForList(
						"searchRectificativePerson.findRectificativePersonEndDate",
						map);
	}

	@Override
	public Long getHelpedCount(String orgInternalCode, Long executeType) {
		if (null == orgInternalCode) {
			return null;
		}
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgInternalCode", orgInternalCode);
		query.put("type", executeType);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"searchRectificativePerson.getHelpedCount", query);
	}

	@Override
	public Long getCount(String orgInternalCode) {
		if (null == orgInternalCode) {
			return null;
		}
		return (null == (Long) getSqlMapClientTemplate().queryForObject(
				"searchRectificativePerson.getCount", orgInternalCode)) ? 0L
				: (Long) getSqlMapClientTemplate().queryForObject(
						"searchRectificativePerson.getCount", orgInternalCode);
	}

	@Override
	public Long getExecuteTypeCount(String orgInternalCode, Long type) {
		if (null == orgInternalCode) {
			return null;
		}
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgInternalCode", orgInternalCode);
		query.put("type", type);
		return (null == (Long) getSqlMapClientTemplate().queryForObject(
				"searchRectificativePerson.getExecuteTypeCount", query)) ? 0L
				: (Long) getSqlMapClientTemplate().queryForObject(
						"searchRectificativePerson.getExecuteTypeCount", query);
	}

	@Override
	public Long getNotHelpedCount(String orgInternalCode, Long executeType) {
		if (null == orgInternalCode) {
			return null;
		}
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgInternalCode", orgInternalCode);
		query.put("type", executeType);
		return (null == (Long) getSqlMapClientTemplate().queryForObject(
				"searchRectificativePerson.getNotHelpedCount", query)) ? 0L
				: (Long) getSqlMapClientTemplate().queryForObject(
						"searchRectificativePerson.getNotHelpedCount", query);
	}

	@Override
	public Integer getCounts(SearchRectificativePersonVo personVo) {
		// TODO Auto-generated method stub
		if (null == personVo) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchRectificativePerson.countSearchRectificativePerson",
				personVo);
	}
}
