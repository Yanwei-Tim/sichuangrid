package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.positiveInfo.dao.SearchPositiveInfosDao;
import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchPositiveInfoVo;
import com.tianque.exception.base.BusinessValidationException;

@Repository("searchPositiveInfosDao")
public class SearchPositiveInfosDaoImpl extends AbstractBaseDao implements
		SearchPositiveInfosDao {

	@Override
	public PageInfo<PositiveInfo> searchAttentionPersonnel(
			SearchPositiveInfoVo searchPositiveInfosVo, int pageNum,
			int pageSize, String sortField, String order) {
		if (searchPositiveInfosVo == null)
			return emptyAttentionObjectPage(pageSize);
		searchPositiveInfosVo.setSortField(sortField);
		searchPositiveInfosVo.setOrder(order);
		// /Map<String, Object> map = new HashMap<String, Object>();
		// getValue(map, searchPositiveInfosVo);
		// map.put("attentionPopulationType",
		// searchPositiveInfosVo.getAttentionPopulationType());
		// map.put("hasServiceTeamMember",
		// searchPositiveInfosVo.getHasServiceTeamMember());
		// map.put("hasServiceTeamRecord",
		// searchPositiveInfosVo.getHasServiceTeamRecord());
		// map.put("sortField", sortField);
		// map.put("order", order);
		PageInfo<PositiveInfo> pageInfo = new PageInfo<PositiveInfo>();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchPositiveInfo.countSearchPositiveInfos",
				searchPositiveInfosVo);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		// pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<PositiveInfo> list = getSqlMapClientTemplate().queryForList(
					"searchPositiveInfo.searchPositiveInfos",
					searchPositiveInfosVo, (pageNum - 1) * pageSize, pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<PositiveInfo>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	public List<PositiveInfo> findPositiveInfos() {
		return (List<PositiveInfo>) getSqlMapClientTemplate().queryForList(
				"searchPositiveInfo.findPositiveInfos");
	}

	private PageInfo<PositiveInfo> emptyAttentionObjectPage(int pageSize) {
		PageInfo<PositiveInfo> pageInfo = new PageInfo<PositiveInfo>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<PositiveInfo>());
		return pageInfo;
	}

	public List<PositiveInfo> searchAttentionPersonnelForExport(
			SearchPositiveInfoVo condition, Integer page, Integer rows,
			String sortField, String order) {
		if (condition == null) {
			condition = new SearchPositiveInfoVo();
		}
		condition.setSortField(sortField);
		condition.setOrder(order);
		if (page == null) {
			return getSqlMapClientTemplate().queryForList(
					"searchPositiveInfo.searchPositiveInfos", condition);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"searchPositiveInfo.searchPositiveInfos", condition,
					(page - 1) * rows, rows);
		}
	}

	public void getValue(Map<String, Object> map,
			SearchPositiveInfoVo searchPositiveInfosVo) {
		if (StringUtil.isStringAvaliable(searchPositiveInfosVo.getIdCardNo())) {
			map.put("idCardNo", searchPositiveInfosVo.getIdCardNo());
		}
		if (StringUtil.isStringAvaliable(searchPositiveInfosVo.getName())) {
			map.put("name", searchPositiveInfosVo.getName());
		}
		if (searchPositiveInfosVo.getGender() != null
				&& searchPositiveInfosVo.getGender().getId() != null) {
			map.put("gender", searchPositiveInfosVo.getGender());
		}
		if (searchPositiveInfosVo.getSchooling() != null
				&& searchPositiveInfosVo.getSchooling().getId() != null) {
			map.put("schooling", searchPositiveInfosVo.getSchooling());
		}
		if (searchPositiveInfosVo.getIsRepeat() == 1) {
			map.put("isRepeat", searchPositiveInfosVo.getIsRepeat());
		}
		if (searchPositiveInfosVo.getBirthday() != null) {
			map.put("birthday", searchPositiveInfosVo.getBirthday());
		}
		if (searchPositiveInfosVo.getReleaseOrBackDate() != null) {
			map.put("releaseOrBackDate",
					searchPositiveInfosVo.getReleaseOrBackDate());
		}
		if (searchPositiveInfosVo.getEndBirthday() != null) {
			map.put("endbirthday", searchPositiveInfosVo.getEndBirthday());
		}
		if (searchPositiveInfosVo.getResettlementDate() != null) {
			map.put("resettlementDate",
					searchPositiveInfosVo.getResettlementDate());
		}
		if (StringUtil
				.isStringAvaliable(searchPositiveInfosVo.getHouseholdId())) {
			map.put("householdId", searchPositiveInfosVo.getHouseholdId());
		}
		if (StringUtil
				.isStringAvaliable(searchPositiveInfosVo.getNativePlace())) {
			map.put("nativePlace", searchPositiveInfosVo.getNativePlace());
		}
		if (StringUtil.isStringAvaliable(searchPositiveInfosVo
				.getLaborEduAddress())) {
			map.put("laborEduAddress",
					searchPositiveInfosVo.getLaborEduAddress());
		}
		if (StringUtil.isStringAvaliable(searchPositiveInfosVo
				.getImprisonmentDate())) {
			map.put("imprisonmentDate",
					searchPositiveInfosVo.getImprisonmentDate());
		}
		if (StringUtil.isStringAvaliable(searchPositiveInfosVo.getCaseReason())) {
			map.put("caseReason", searchPositiveInfosVo.getCaseReason());
		}
		if (searchPositiveInfosVo.getAgoProfession() != null
				&& searchPositiveInfosVo.getAgoProfession().getId() != null) {
			map.put("agoProfession", searchPositiveInfosVo.getAgoProfession());
		}
		if (StringUtil.isStringAvaliable(searchPositiveInfosVo
				.getCurrentlyAddress())) {
			map.put("currentlyAddress",
					searchPositiveInfosVo.getCurrentlyAddress());
		}
		if (StringUtil.isStringAvaliable(searchPositiveInfosVo
				.getNoResettlementReason())) {
			map.put("noResettlementReason",
					searchPositiveInfosVo.getNoResettlementReason());
		}
		if (StringUtil.isStringAvaliable(searchPositiveInfosVo
				.getOrgInternalCode())) {
			map.put("orgInternalCode",
					searchPositiveInfosVo.getOrgInternalCode());
		}
		if (StringUtil.isStringAvaliable(searchPositiveInfosVo.getTelephone())) {
			map.put("telephone", searchPositiveInfosVo.getTelephone());
		}
		if (StringUtil.isStringAvaliable(searchPositiveInfosVo
				.getMobileNumber())) {
			map.put("mobileNumber", searchPositiveInfosVo.getMobileNumber());
		}
		if (StringUtil.isStringAvaliable(searchPositiveInfosVo
				.getNativePoliceStation())) {
			map.put("nativePoliceStation",
					searchPositiveInfosVo.getNativePoliceStation());
		}
		if (StringUtil.isStringAvaliable(searchPositiveInfosVo
				.getNativePlaceAddress())) {
			map.put("nativePlaceAddress",
					searchPositiveInfosVo.getNativePlaceAddress());
		}
		if (StringUtil.isStringAvaliable(searchPositiveInfosVo
				.getHelpEducator())) {
			map.put("helpEducator", searchPositiveInfosVo.getHelpEducator());
		}
		if (StringUtil.isStringAvaliable(searchPositiveInfosVo
				.getEducatorTelephone())) {
			map.put("educatorTelephone",
					searchPositiveInfosVo.getEducatorTelephone());
		}
		if (StringUtil.isStringAvaliable(searchPositiveInfosVo
				.getEducatorMobileNumber())) {
			map.put("educatorMobileNumber",
					searchPositiveInfosVo.getEducatorMobileNumber());
		}
		if (StringUtil.isStringAvaliable(searchPositiveInfosVo.getProvince())) {
			map.put("province", searchPositiveInfosVo.getProvince());
		}
		if (StringUtil.isStringAvaliable(searchPositiveInfosVo.getCity())) {
			map.put("city", searchPositiveInfosVo.getCity());
		}
		if (StringUtil.isStringAvaliable(searchPositiveInfosVo.getDistrict())) {
			map.put("district", searchPositiveInfosVo.getDistrict());
		}
		if (searchPositiveInfosVo.getEndReleaseOrBackDate() != null) {
			map.put("endReleaseOrBackDate",
					searchPositiveInfosVo.getEndReleaseOrBackDate());
		}
		if (searchPositiveInfosVo.getEndResettlementDate() != null) {
			map.put("endResettlementDate",
					searchPositiveInfosVo.getEndResettlementDate());
		}
		if (searchPositiveInfosVo.getCrimeDate() != null) {
			map.put("crimeDate", searchPositiveInfosVo.getCrimeDate());
		}
		if (searchPositiveInfosVo.getEndCrimeDate() != null) {
			map.put("endCrimeDate", searchPositiveInfosVo.getEndCrimeDate());
		}

		if (searchPositiveInfosVo.getPositiveInfosType() != null
				&& searchPositiveInfosVo.getPositiveInfosType().getId() != null) {
			map.put("positiveInfoType",
					searchPositiveInfosVo.getPositiveInfosType());
		}
		if (StringUtil.isStringAvaliable(searchPositiveInfosVo.getWorkUnit())) {
			map.put("workUnit", searchPositiveInfosVo.getWorkUnit());
		}
		if (searchPositiveInfosVo.getCareer() != null
				&& searchPositiveInfosVo.getCareer().getId() != null) {
			map.put("career", searchPositiveInfosVo.getCareer());
		}

		// map.put("isCrime", searchPositiveInfosVo.getIsCrime());
		map.put("isEmphasis", searchPositiveInfosVo.getIsEmphasis());
		map.put("isDeath", searchPositiveInfosVo.getIsDeath());// 死亡状态
		map.put("fastSearchKeyWords",
				searchPositiveInfosVo.getFastSearchKeyWords());
	}

	public List<PositiveInfo> findPositiveInfoByNameAndPinyinAndOrgInternalCode(
			String name, String orgInternalCode) {
		Map map = new HashMap();
		map.put("name", name);
		map.put("orgInternalCode", orgInternalCode);
		return getSqlMapClientTemplate()
				.queryForList(
						"searchPositiveInfo.findPositiveInfoNameAndPinyinAndOrgInternalCode",
						map);
	}

	@Override
	public int getCountPositiveInfoByOrgInternalCodeAndMap(
			String orgInternalCode, Map<String, Object> map) {
		if (!StringUtil.isStringAvaliable(orgInternalCode)) {
			throw new BusinessValidationException("orgInternalCode不能为空");
		}
		map.put("orgInternalCode", orgInternalCode);
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchPositiveInfo.getCountPositiveInfoByOrgInternalCodeAndMap",
						map);
	}

	@Override
	public List<PositiveInfo> getPositiveInfoByOrgInternalCodeAndMap(
			String orgInternalCode, Map<String, Object> map) {
		return (List<PositiveInfo>) getSqlMapClientTemplate().queryForList(
				"searchPositiveInfo.getPositiveInfoByOrgInternalCodeAndMap",
				map);
	}

	@Override
	public List<PositiveInfo> getPositiveInfoByOrgInternalCodeAndMap(
			Map<String, Object> map) {
		return (List<PositiveInfo>) getSqlMapClientTemplate().queryForList(
				"searchPositiveInfo.getPositiveInfoByOrgInternalCodeAndMap",
				map);
	}

	@Override
	public PageInfo<PositiveInfo> searchFastPositiveInfo(
			SearchPositiveInfoVo searchPositiveInfosVo, int pageNum,
			int pageSize, String sortField, String order) {
		Map map = new HashMap();
		map.put("orgInternalCode", searchPositiveInfosVo.getOrgInternalCode());
		map.put("name", searchPositiveInfosVo.getName());
		map.put("idCardNo", searchPositiveInfosVo.getIdCardNo());
		map.put("isEmphasis", searchPositiveInfosVo.getIsEmphasis());
		map.put("sortField", sortField);
		map.put("order", order);
		PageInfo<PositiveInfo> pageInfo = new PageInfo<PositiveInfo>();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchPositiveInfo.countFastSearchPositiveInfo", map);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<PositiveInfo> list = getSqlMapClientTemplate().queryForList(
					"searchPositiveInfo.searchFastPositiveInfo", map,
					(pageNum - 1) * pageSize, pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<PositiveInfo>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public Integer getCount(SearchPositiveInfoVo searchPositiveInfoVo) {
		// TODO Auto-generated method stub
		if (null == searchPositiveInfoVo) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchPositiveInfo.countSearchPositiveInfos",
				searchPositiveInfoVo);

	}
}
