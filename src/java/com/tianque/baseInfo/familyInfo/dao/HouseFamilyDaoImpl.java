package com.tianque.baseInfo.familyInfo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.familyInfo.domain.HouseFamily;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.CensusRegisterFamily;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.SearchFamilyHouseVo;

@Service("houseFamilyDao")
@Transactional
public class HouseFamilyDaoImpl extends AbstractBaseDao implements
		HouseFamilyDao {

	@Override
	public PageInfo<HouseFamily> findHouseFamilyByOrgId(
			Organization organization, Integer pageNum, Integer pageSize,
			String sidx, String sord, String shardCode, Long headOfHouseHoldId) {
		Map<String, Object> query = initQueryCondition(headOfHouseHoldId,
				organization, sidx, sord);
		query.put("shardCode", shardCode);
		return findData(query, pageNum, pageSize);
	}

	@Override
	public PageInfo<HouseFamily> findHouseFamilyByOrgIdAndSearchCondition(
			SearchFamilyHouseVo searchFamilyHouseVo, Organization organization,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String shardCode, Long headOfHouseHoldId) {
		Map<String, Object> query = initQueryCondition(headOfHouseHoldId,
				organization, sidx, sord);
		query.put("familyHouseAccountNumber",
				searchFamilyHouseVo.getFamilyHouseAccountNumber());
		query.put("familyHouseIdCardNo",
				searchFamilyHouseVo.getFamilyHouseIdCardNo());
		query.put("familyHouseName", searchFamilyHouseVo.getFamilyHouseName());
		query.put("shardCode", shardCode);
		return findDataByOrgIdAndSearchCondition(query, pageNum, pageSize);
	}

	private PageInfo<HouseFamily> findData(Map<String, Object> query,
			Integer pageNum, Integer pageSize) {

		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"houseFamily.countfamilyHouses", query);
		pageNum = getPageNumByFamilyHouse(pageNum, pageSize, count);
		List<HouseFamily> familyList = new ArrayList<HouseFamily>();
		if (pageNum > 0) {
			familyList = getSqlMapClientTemplate().queryForList(
					"houseFamily.findFamilyHouseByOrgId", query,
					(pageNum - 1) * pageSize, pageSize);
		}
		return getPagInfoOfHouseFamily(pageNum, pageSize, count, familyList);
	}

	private PageInfo<HouseFamily> findDataByOrgIdAndSearchCondition(
			Map<String, Object> query, Integer pageNum, Integer pageSize) {

		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"houseFamily.countfamilyHouses", query);
		pageNum = getPageNumByFamilyHouse(pageNum, pageSize, count);
		List<HouseFamily> familyList = new ArrayList<HouseFamily>();
		if (pageNum > 0) {
			familyList = getSqlMapClientTemplate().queryForList(
					"houseFamily.findFamilyHouseByOrgIdAndSearchCondition",
					query, (pageNum - 1) * pageSize, pageSize);
		}
		return getPagInfoOfHouseFamily(pageNum, pageSize, count, familyList);
	}

	private Map<String, Object> initQueryCondition(Long headOfHouseHoldId,
			Organization organization, String sidx, String sord) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orginternalcode", organization.getOrgInternalCode());
		query.put("headOfHouseHoldId", headOfHouseHoldId);
		query.put("sortField", sidx);
		query.put("order", sord);
		return query;
	}

	private PageInfo<HouseFamily> getPagInfoOfHouseFamily(Integer pageNum,
			Integer pageSize, Integer countFloatingPopulation,
			List<HouseFamily> list) {
		PageInfo<HouseFamily> pageInfo = new PageInfo<HouseFamily>();
		pageInfo.setResult(list);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setTotalRowSize(countFloatingPopulation);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private Integer getPageNumByFamilyHouse(Integer pageNum, Integer pageSize,
			Integer countFamilyHouse) {
		int pageCount = 0;
		if ((countFamilyHouse % pageSize) == 0) {
			pageCount = countFamilyHouse / pageSize;
		} else {
			pageCount = countFamilyHouse / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		return pageNum;
	}

	@Override
	public PageInfo<HouseFamily> findHouseFamilyByOrgIdAndMinuteSearchCondition(
			SearchFamilyHouseVo searchFamilyHouseVo, Organization organization,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String shardCode, Long headOfHouseHoldId) {
		Map<String, Object> query = initQueryCondition(headOfHouseHoldId,
				organization, sidx, sord);
		query.put("familyHouseAccountNumber",
				searchFamilyHouseVo.getFamilyHouseAccountNumber());
		query.put("familyHouseIdCardNo",
				searchFamilyHouseVo.getFamilyHouseIdCardNo());

		query.put("familyHouseName", searchFamilyHouseVo.getFamilyHouseName());
		query.put("headOfFamilyHouseName",
				searchFamilyHouseVo.getHeadOfFamilyHouseName());
		query.put("familyHouseAddress",
				searchFamilyHouseVo.getFamilyHouseAddress());
		query.put("familyHouseMemberName",
				searchFamilyHouseVo.getFamilyHouseMemberName());
		query.put("familyHouseMemberIdCardNo",
				searchFamilyHouseVo.getFamilyHouseMemberIdCardNo());
		query.put("memberNum", searchFamilyHouseVo.getMemberNum());
		query.put("fastSearchKeyWords",
				searchFamilyHouseVo.getFastSearchKeyWords());
		query.put("shardCode", shardCode);
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"houseFamily.countFamilyHouseByOrgIdAndMinuteSearchCondition",
				query);
		pageNum = getPageNumByFamilyHouse(pageNum, pageSize, count);
		List<HouseFamily> familyList = getSqlMapClientTemplate().queryForList(
				"houseFamily.findFamilyHouseByOrgIdAndMinuteSearchCondition",
				query, (pageNum - 1) * pageSize, pageSize);

		return getPagInfoOfHouseFamily(pageNum, pageSize, count, familyList);
	}

	@Override
	public HouseFamily findHouseFamilyByIdAndOrgId(Long familyId) {
		CensusRegisterFamily censusRegisterFamily = (CensusRegisterFamily) getSqlMapClientTemplate()
				.queryForObject("houseFamily.findFamilyHouseByIdAndOrgId",
						familyId);
		// List<PropertyDict> houseMarchTypeList = (List<PropertyDict>)
		// getSqlMapClientTemplate()
		// .queryForList("householdStaff.getHouseMarchType", familyId);
		HouseFamily houseFamily = new HouseFamily();
		StringBuffer sb = new StringBuffer();
		if (null != censusRegisterFamily
				&& censusRegisterFamily.getProvince() != null) {
			sb.append(censusRegisterFamily.getProvince());
			sb.append(censusRegisterFamily.getCity());
			sb.append(censusRegisterFamily.getDistrict());
			houseFamily.setNativeLocation(sb.toString());
		}
		if (null != censusRegisterFamily
				&& null != censusRegisterFamily.getOrganization()) {
			houseFamily.setOrganization(censusRegisterFamily.getOrganization());
		}
		houseFamily.setCensusRegisterFamily(censusRegisterFamily);
		return houseFamily;
	}

	@Override
	public List<HouseFamily> findFamilyMemberInforById(Long familyId,
			String ShardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("familyId", familyId);
		map.put("ShardCode", ShardCode);
		List<HouseFamily> houseFamilyList = (List<HouseFamily>) getSqlMapClientTemplate()
				.queryForList("houseFamily.findFamilyMemberInforById", map);
		return houseFamilyList;
	}

	@Override
	public void deleteHouseFamilyById(long id) {
		getSqlMapClientTemplate().delete("houseFamily.deleteHouseFamilyById",
				id);
	}

	@Override
	public void changeAccountNumber(String newAccNo, Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountNumber", newAccNo);
		map.put("id", id);
		getSqlMapClientTemplate()
				.update("houseFamily.changeAccountNumber", map);
	}

	@Override
	public PageInfo findHouseFamilyMembersByOrgIdAndId(Long id, Long orgId,
			Integer page, Integer rows, String sidx, String sord,
			String ShardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("familyId", id);
		map.put("ShardCode", ShardCode);
		int count = (Integer) getSqlMapClientTemplate().queryForObject(
				"houseFamily.countFamilyMemberInforById", map);
		List<HouseFamily> list = getSqlMapClientTemplate().queryForList(
				"houseFamily.findFamilyMemberInforById", map,
				(page - 1) * rows, rows);
		return createPageInfo(page, rows, count, list);
	}

	private PageInfo<HouseFamily> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<HouseFamily> pageInfo = new PageInfo<HouseFamily>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public void changeHouseHold(HouseholdStaff newHouseholdStaff, Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idCardNo", newHouseholdStaff.getIdCardNo());
		map.put("houseHoldName", newHouseholdStaff.getName());
		map.put("id", id);
		getSqlMapClientTemplate().update("houseFamily.changeHouseHold", map);
	}

	@Override
	public void cleanFamilyHead(String orgInternalCode, String idCardNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("idCardNo", idCardNo);
		getSqlMapClientTemplate().update("houseFamily.cleanFamilyHead", map);
	}

	@Override
	public boolean existAccountNumber(String newAccNo, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountNumber", newAccNo);
		map.put("orgId", orgId);
		int count = (Integer) getSqlMapClientTemplate().queryForObject(
				"houseFamily.existAccountNumber", map);
		return count > 0 ? true : false;
	}

	@Override
	public int haveRepatCardOrNo(String card) {
		int count = (Integer) getSqlMapClientTemplate().queryForObject(
				"householdStaff.haveRepatCardOrNo", card);
		return count;
	}

	@Override
	public List<Long> getHouseMarchType(Long familyId) {
		return getSqlMapClientTemplate().queryForList(
				"householdStaff.getHouseMarchType", familyId);
	}
}
