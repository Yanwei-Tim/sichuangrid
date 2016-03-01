package com.tianque.baseInfo.unsettledPopulation.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.unsettledPopulation.domain.UnsettledPopulation;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.BaseUnsettledPopulationSearchCondition;

@Repository("searchUnsettledPopulationDao")
@SuppressWarnings("unchecked")
public class SearchUnsettledPopulationDaoImpl extends AbstractBaseDao implements
		SearchUnsettledPopulationDao {
	private static Logger logger = LoggerFactory
			.getLogger(SearchUnsettledPopulationDaoImpl.class);

	@Override
	public PageInfo<UnsettledPopulation> searchUnsettledPopulation(
			BaseUnsettledPopulationSearchCondition condition, int pageNum,
			int pageSize, String sortField, String order) {
		if (condition == null)
			return emptyUnsettledPopulationPage(pageSize);

		PageInfo<UnsettledPopulation> pageInfo = new PageInfo<UnsettledPopulation>();
		Integer countNum = 0;
		try {
			countNum = (Integer) getSqlMapClientTemplate().queryForObject(
					"searchUnsettledPopulation.countSearchUnsettledPopulation",
					getConditionMap(condition, sortField, order));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		// pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<UnsettledPopulation> list = getSqlMapClientTemplate()
					.queryForList(
							"searchUnsettledPopulation.searchUnsettledPopulation",
							getConditionMap(condition, sortField, order),
							(pageNum - 1) * pageSize, pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<UnsettledPopulation>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private Map getConditionMap(
			BaseUnsettledPopulationSearchCondition condition, String sortField,
			String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (condition == null) {
			return map;
		}
		map.put("orgInternalCode", condition.getOrgInternalCode());
		map.put("gender", condition.getGender());
		map.put("name", condition.getName());
		map.put("usedName", condition.getUsedName());
		map.put("idCardNo", condition.getIdCardNo());
		map.put("birthdayEnd", condition.getBirthdayEnd());
		map.put("birthdayStart", condition.getBirthdayStart());
		map.put("currentAddress", condition.getCurrentAddress());
		map.put("mobileNumber", condition.getMobileNumber());
		map.put("telephone", condition.getTelephone());
		map.put("certificateType", condition.getCertificateType());
		map.put("certificateNo", condition.getCertificateNo());
		map.put("unSettedReason", condition.getUnSettedReason());
		map.put("unsettedTimeStart", condition.getUnsettedTimeStart());
		map.put("unsettedTimeEnd", condition.getUnsettedTimeEnd());
		map.put("nativePlaceAddress", condition.getNativePlaceAddress());
		map.put("workUnit", condition.getWorkUnit());
		map.put("maritalState", condition.getMaritalState());
		map.put("stature", condition.getStature());
		map.put("nation", condition.getNation());
		map.put("faith", condition.getFaith());
		map.put("schooling", condition.getSchooling());
		map.put("bloodType", condition.getBloodType());
		map.put("email", condition.getEmail());
		map.put("remark", condition.getRemark());
		map.put("province", condition.getProvince());
		map.put("city", condition.getCity());
		map.put("district", condition.getDistrict());
		map.put("politicalBackground", condition.getPoliticalBackground());
		map.put("healthState", condition.getHealthState());
		map.put("isDeath", condition.getIsDeath());
		map.put("logOut", condition.getLogOut());
		map.put("career", condition.getCareer());
		map.put("sortField", sortField);
		map.put("order", order);
		return map;
	}

	@Override
	public PageInfo<UnsettledPopulation> fastSearchemptyUnsettledPopulationPage(
			BaseUnsettledPopulationSearchCondition condition, int pageNum,
			int pageSize, String sortField, String order) {
		if (condition == null) {
			return emptyPage(pageSize);
		}
		Map<String, Object> map = getFastConditionMap(condition, sortField,
				order);
		Integer countNum = 0;
		List<UnsettledPopulation> list = null;

		countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchUnsettledPopulation.countFastSearchUnsettledPopulation",
				map);
		list = getSqlMapClientTemplate().queryForList(
				"searchUnsettledPopulation.searchFastUnsettledPopulation", map,
				(pageNum - 1) * pageSize, pageSize);

		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<UnsettledPopulation> emptyPage(int pageSize) {
		PageInfo<UnsettledPopulation> pageInfo = new PageInfo<UnsettledPopulation>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<UnsettledPopulation>());
		return pageInfo;
	}

	private Map getFastConditionMap(
			BaseUnsettledPopulationSearchCondition condition, String sortField,
			String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (condition == null) {
			return map;
		}
		map.put("isDeath", condition.getIsDeath());
		map.put("logOut", condition.getLogOut());
		map.put("orgInternalCode", condition.getOrgInternalCode());
		map.put("name", condition.getName());
		map.put("idCardNo", condition.getIdCardNo());
		map.put("fastSearchKeyWords", condition.getFastSearchKeyWords());// 姓名、身份证
		if (!StringUtil.isStringAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);
		return map;
	}

	private PageInfo<UnsettledPopulation> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List<UnsettledPopulation> list) {
		PageInfo<UnsettledPopulation> pageInfo = new PageInfo<UnsettledPopulation>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private PageInfo<UnsettledPopulation> emptyUnsettledPopulationPage(
			int pageSize) {
		PageInfo<UnsettledPopulation> pageInfo = new PageInfo<UnsettledPopulation>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<UnsettledPopulation>());
		return pageInfo;
	}

	@Override
	public PageInfo<UnsettledPopulation> findUnsettledPopulationForPageByOrgId(
			BaseUnsettledPopulationSearchCondition unsettledPopulationCondition,
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		Map<String, Object> map = getSearchMap(unsettledPopulationCondition,
				orgId, sidx, sord);

		return createPageInfo(
				pageNum,
				pageSize,
				(Integer) getSqlMapClientTemplate().queryForObject(
						"searchUnsettledPopulation.countUnsettledPopulation",
						map),
				getSqlMapClientTemplate().queryForList(
						"searchUnsettledPopulation.findUnsettledPopulation",
						map, (pageNum - 1) * pageSize, pageSize));
	}

	private Map<String, Object> getSearchMap(
			BaseUnsettledPopulationSearchCondition unsettledPopulationCondition,
			Long orgId, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("sortField", sidx);
		map.put("order", sord);
		if (unsettledPopulationCondition != null) {
			if (unsettledPopulationCondition.getName() != null
					&& !"".equals(unsettledPopulationCondition.getName().trim())) {
				map.put("name", unsettledPopulationCondition.getName());
			}
			if (unsettledPopulationCondition.getOrgInternalCode() != null
					&& !"".equals(unsettledPopulationCondition
							.getOrgInternalCode().trim())) {
				map.put("orgInternalCode",
						unsettledPopulationCondition.getOrgInternalCode());
			}
			if (unsettledPopulationCondition.getIdCardNo() != null
					&& !"".equals(unsettledPopulationCondition.getIdCardNo()
							.trim())) {
				map.put("idCardNo", unsettledPopulationCondition.getIdCardNo());
			}
			if (unsettledPopulationCondition.getGender() != null) {
				map.put("gender", unsettledPopulationCondition.getGender());
			}
			if (unsettledPopulationCondition.getTelephone() != null
					&& !"".equals(unsettledPopulationCondition.getTelephone()
							.trim())) {
				map.put("telephone",
						unsettledPopulationCondition.getTelephone());
			}
			if (unsettledPopulationCondition.getMobileNumber() != null
					&& !"".equals(unsettledPopulationCondition
							.getMobileNumber().trim())) {
				map.put("mobileNumber",
						unsettledPopulationCondition.getMobileNumber());
			}
			if (unsettledPopulationCondition.getSchooling() != null) {
				map.put("schooling",
						unsettledPopulationCondition.getSchooling());
			}
			if (unsettledPopulationCondition.getCurrentAddressType() != null) {
				map.put("currentAddressType",
						unsettledPopulationCondition.getCurrentAddressType());
			}
			if (unsettledPopulationCondition.getProvince() != null
					&& !"".equals(unsettledPopulationCondition.getProvince())) {
				map.put("province", unsettledPopulationCondition.getProvince());
			}
			if (unsettledPopulationCondition.getCity() != null
					&& !"".equals(unsettledPopulationCondition.getCity())) {
				map.put("city", unsettledPopulationCondition.getCity());
			}
			if (unsettledPopulationCondition.getDistrict() != null
					&& !"".equals(unsettledPopulationCondition.getDistrict())) {
				map.put("district", unsettledPopulationCondition.getDistrict());
			}
			if (unsettledPopulationCondition.getCurrentAddress() != null
					&& !"".equals(unsettledPopulationCondition
							.getCurrentAddress().trim())) {
				map.put("currentAddress",
						unsettledPopulationCondition.getCurrentAddress());
			}
			if (unsettledPopulationCondition.getNativePlaceAddress() != null
					&& !"".equals(unsettledPopulationCondition
							.getNativePlaceAddress().trim())) {
				map.put("nativePlaceAddress",
						unsettledPopulationCondition.getNativePlaceAddress());
			}
			if (unsettledPopulationCondition.getIsDeath() != null) {
				map.put("isDeath", unsettledPopulationCondition.getIsDeath());
			}

			if (unsettledPopulationCondition.getLogOut() != null) {
				map.put("logOut", unsettledPopulationCondition.getLogOut());
			}

			if (unsettledPopulationCondition.getWorkUnit() != null
					&& !"".equals(unsettledPopulationCondition.getWorkUnit()
							.trim())) {
				map.put("workUnit", unsettledPopulationCondition.getWorkUnit());
			}
			if (unsettledPopulationCondition.getCertificateType() != null) {
				map.put("certificateType",
						unsettledPopulationCondition.getCertificateType());
			}
			if (unsettledPopulationCondition.getCertificateNo() != null) {
				map.put("certificateNo",
						unsettledPopulationCondition.getCertificateNo());
			}
			if (unsettledPopulationCondition.getUnSettedReason() != null) {
				map.put("unSettedReason",
						unsettledPopulationCondition.getUnSettedReason());
			}
			if (unsettledPopulationCondition.getUnsettedTimeStart() != null) {
				map.put("unsettedTimeStart",
						unsettledPopulationCondition.getUnsettedTimeStart());
			}
			if (unsettledPopulationCondition.getUnsettedTimeEnd() != null) {
				map.put("unsettedTimeEnd",
						unsettledPopulationCondition.getUnsettedTimeEnd());
			}
			if (unsettledPopulationCondition.getBirthdayStart() != null) {
				map.put("birthdayStart",
						unsettledPopulationCondition.getBirthdayStart());
			}
			if (unsettledPopulationCondition.getBirthdayEnd() != null) {
				map.put("birthdayEnd",
						unsettledPopulationCondition.getBirthdayEnd());
			}
			if (unsettledPopulationCondition.getOtherAddress() != null
					&& !"".equals(unsettledPopulationCondition
							.getOtherAddress().trim())) {
				map.put("otherAddress",
						unsettledPopulationCondition.getOtherAddress());
			}
			if (unsettledPopulationCondition.getUsedName() != null
					&& !"".equals(unsettledPopulationCondition.getUsedName()
							.trim())) {
				map.put("usedName", unsettledPopulationCondition.getUsedName());
			}
			if (unsettledPopulationCondition.getCareer() != null) {
				map.put("career", unsettledPopulationCondition.getCareer());
			}
			if (unsettledPopulationCondition.getPoliticalBackground() != null) {
				map.put("politicalBackground",
						unsettledPopulationCondition.getPoliticalBackground());
			}
			if (unsettledPopulationCondition.getNativePlaceAddress() != null) {
				map.put("nativePlaceAddress",
						unsettledPopulationCondition.getNativePlaceAddress());
			}
			if (unsettledPopulationCondition.getMaritalState() != null) {
				map.put("maritalState",
						unsettledPopulationCondition.getMaritalState());
			}
			if (unsettledPopulationCondition.getNation() != null) {
				map.put("nation", unsettledPopulationCondition.getNation());
			}
			if (unsettledPopulationCondition.getFaith() != null) {
				map.put("faith", unsettledPopulationCondition.getFaith());
			}
			if (unsettledPopulationCondition.getBloodType() != null) {
				map.put("bloodType",
						unsettledPopulationCondition.getBloodType());
			}
			if (unsettledPopulationCondition.getStature() != null) {
				map.put("stature", unsettledPopulationCondition.getStature());
			}
			if (unsettledPopulationCondition.getEmail() != null
					&& !"".equals(unsettledPopulationCondition.getEmail()
							.trim())) {
				map.put("email", unsettledPopulationCondition.getEmail());
			}
			if (unsettledPopulationCondition.getFastSearchKeyWords() != null) {
				map.put("fastSearchKeyWords",
						unsettledPopulationCondition.getFastSearchKeyWords());
			}
		} else {
			map.put("isDeath", 0);
		}
		return map;
	}

	@Override
	public List<UnsettledPopulation> findUnsettledPopulationList(
			BaseUnsettledPopulationSearchCondition unsettledPopulationCondition,
			Long orgId, String sidx, String sord) {
		Map<String, Object> map = getSearchMap(unsettledPopulationCondition,
				orgId, sidx, sord);
		return getSqlMapClientTemplate().queryForList(
				"searchUnsettledPopulation.searchUnsettledPopulation", map);
	}

	@Override
	public PageInfo<UnsettledPopulation> findUnsettledPopulationByorgCodeForGis(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("sortField", sidx);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchUnsettledPopulation.countUnsettledPopulation", map);
		List<UnsettledPopulation> list = getSqlMapClientTemplate()
				.queryForList(
						"searchUnsettledPopulation.fingUnsettledPopulationByorgCodeForGis",
						map, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public Integer getCount(BaseUnsettledPopulationSearchCondition condition) {
		// TODO Auto-generated method stub
		if (null == condition) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchUnsettledPopulation.countSearchUnsettledPopulation",
				getConditionMap(condition, null, null));
	}
}
