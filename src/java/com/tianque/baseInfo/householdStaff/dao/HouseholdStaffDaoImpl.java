package com.tianque.baseInfo.householdStaff.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDaoImpl;
import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.baseInfo.householdStaff.commLog.CommonLog;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.baseInfo.utils.CustomDateUtil;
import com.tianque.controller.vo.HouseholdStaffVo;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.SearchHouseInfoVo;
import com.tianque.jms.OperateMode;
import com.tianque.plugin.tqSearch.syncSolrIndex.SyncPopulationSolrIndexForMQ;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.shard.util.IdConversionShardUtil;

@Repository("householdStaffDao")
@SuppressWarnings("unchecked")
public class HouseholdStaffDaoImpl extends
		BaseInfoPopulationBaseDaoImpl<HouseholdStaff, SearchHouseInfoVo>
		implements HouseholdStaffDao {
	@Autowired
	private SyncPopulationSolrIndexForMQ syncPopulationSolrIndexForMQ;

	@Override
	public HouseholdStaff getHouseholdStaffById(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();

		String shardCode = IdConversionShardUtil.getShardCodeById(id);
		map.put("id", id);
		map.put("shardCode", shardCode);
		return (HouseholdStaff) getSqlMapClientTemplate().queryForObject(
				"householdStaff.getHouseholdStaffById", map);
	}

	@Override
	public List<HouseholdStaff> findHouseholdStaffList(
			HouseholdStaffVo householdStaffVo, Long orgId, String sidx,
			String sord, String shardCode) {
		Map<String, Object> map = getSearchMap(householdStaffVo, orgId, sidx,
				sord);
		map.put("shardCode", shardCode);
		return getSqlMapClientTemplate().queryForList(
				"householdStaff.findHouseholdStaffList", map);
	}

	@Override
	public List<HouseholdStaff> findHouseholdIdByFamilyId(Long[] id,
			String shardCode) {
		if (id == null || id.length == 0) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("shardCode", shardCode);
		return getSqlMapClientTemplate().queryForList(
				"householdStaff.findHouseholdIdByFamilyId", map);
	}

	@Override
	public List<HouseholdStaff> findHouseholdStaffByAccountNumberAndorgIdAndInternalId(
			String accountNumber, Organization org, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountNumber", accountNumber);
		map.put("orgId", org.getId());
		map.put("orgInternalCode", org.getOrgInternalCode());
		map.put("shardCode", shardCode);
		return getSqlMapClientTemplate()
				.queryForList(
						"householdStaff.findHouseholdStaffByAccountNumberAndorgIdAndInternalId",
						map);
	}

	@Override
	public List<HouseholdStaff> findHouseholdStaffByAccountNumberAndOrgId(
			String accountNumber, Long orgId, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountNumber", accountNumber);
		map.put("orgId", orgId);
		map.put("shardCode", shardCode);
		return getSqlMapClientTemplate()
				.queryForList(
						"householdStaff.findHouseholdStaffByAccountNumberAndOrgId",
						map);
	}

	@Override
	public void updateAccountNumberByFamilyIdAndOrgId(String accountNumber,
			Long orgId, Long familyId, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("accountNumber", accountNumber);
		map.put("familyId", familyId);
		map.put("shardCode", shardCode);
		getSqlMapClientTemplate().update(
				"householdStaff.updateAccountNumberByFamilyIdAndOrgId", map);

	}

	@Override
	public PageInfo<HouseholdStaff> findHouseholdStaffForPageByOrgInternalCode(
			HouseholdStaffVo householdStaffVo, String orgInternalCode,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String shardCode) {
		Map<String, Object> map = getNewSearchMap(householdStaffVo,
				orgInternalCode, sidx, sord, shardCode);
		map.put("shardCode", shardCode);
		return createPageInfo(
				pageNum,
				pageSize,
				(Integer) getSqlMapClientTemplate().queryForObject(
						"householdStaff.countHouseholdStaff", map),
				getSqlMapClientTemplate().queryForList(
						"householdStaff.findHouseholdStaff", map,
						(pageNum - 1) * pageSize, pageSize));
	}

	@Override
	public PageInfo<HouseholdStaff> fastFindHouseholdStaffForPageByOrgInternalCode(
			HouseholdStaffVo householdStaffVo, String orgInternalCode,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String shardCode) {
		Map<String, Object> map = getNewSearchMap(householdStaffVo,
				orgInternalCode, sidx, sord, shardCode);
		map.put("shardCode", shardCode);
		return createPageInfo(
				pageNum,
				pageSize,
				(Integer) getSqlMapClientTemplate().queryForObject(
						"householdStaff.countFastFindHouseholdStaff", map),
				getSqlMapClientTemplate().queryForList(
						"householdStaff.fastFindHouseholdStaff", map,
						(pageNum - 1) * pageSize, pageSize));
	}

	@Override
	public PageInfo<HouseholdStaff> findHouseholdStaffByOrgIdDefaultListTest(
			HouseholdStaffVo householdStaffVo, String orgInternalCode,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			CommonLog commonLog, String shardCode) {
		long start = System.currentTimeMillis();
		Map<String, Object> map = getNewSearchMap(householdStaffVo,
				orgInternalCode, sidx, sord, shardCode);
		map.put("shardCode", shardCode);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"householdStaff.countHouseholdStaffDefaultList", map);
		map.put("startRow", (pageNum - 1) * pageSize);
		map.put("endRow", pageNum * pageSize);
		List<HouseholdStaff> list = getSqlMapClientTemplate().queryForList(
				"householdStaff.findHouseholdStaffDefaultList", map);
		commonLog.setDaoTime(System.currentTimeMillis() - start);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public PageInfo<HouseholdStaff> findHouseholdStaffByOrgIdDefaultList(
			HouseholdStaffVo householdStaffVo, String orgInternalCode,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String shardCode) {
		Map<String, Object> map = getNewSearchMap(householdStaffVo,
				orgInternalCode, sidx, sord, shardCode);
		map.put("shardCode", shardCode);
		long startTime = System.currentTimeMillis();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"householdStaff.countHouseholdStaffDefaultList", map);
		logger.error("列表count时间:" + (System.currentTimeMillis() - startTime));
		startTime = System.currentTimeMillis();
		map.put("startRow", (pageNum - 1) * pageSize);
		map.put("endRow", pageNum * pageSize);
		List<HouseholdStaff> list = getSqlMapClientTemplate().queryForList(
				"householdStaff.findHouseholdStaffDefaultList", map);
		logger.error("列表数据查询:" + (System.currentTimeMillis() - startTime));
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public PageInfo<HouseholdStaff> findHouseholdStaffForPageByOrgInternalCodeAndId(
			HouseholdStaffVo householdStaffVo, String orgInternalCode, Long id,
			String houseHold, Integer pageNum, Integer pageSize, String sidx,
			String sord, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("familyId", id);
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("shardCode", shardCode);
		if (houseHold != null) {
			map.put("houseHold", houseHold);
			return createPageInfo(
					pageNum,
					pageSize,
					(Integer) getSqlMapClientTemplate()
							.queryForObject(
									"householdStaff.countHouseholdStaffByOrgInternalCodeAndIdExceptHead",
									map),
					getSqlMapClientTemplate()
							.queryForList(
									"householdStaff.findHouseholdStaffByOrgInternalCodeAndIdExceptHead",
									map, (pageNum - 1) * pageSize, pageSize));
		}

		return createPageInfo(
				pageNum,
				pageSize,
				(Integer) getSqlMapClientTemplate()
						.queryForObject(
								"householdStaff.countHouseholdStaffByOrgInternalCodeAndId",
								map),
				getSqlMapClientTemplate()
						.queryForList(
								"householdStaff.findHouseholdStaffByOrgInternalCodeAndId",
								map, (pageNum - 1) * pageSize, pageSize));
	}

	@Override
	public List<HouseholdStaff> getActualPopulationByOrgIdAndIdCardNoExcludePopulationId(
			Long excludePopulationId, Long orgId, String idCardNo15,
			String idCardNo18) {
		Map<String, Object> map = new HashMap<String, Object>();
		String shardCode = IdConversionShardUtil
				.getShardCodeById(excludePopulationId);
		map.put("id", excludePopulationId);
		map.put("orgId", orgId);
		map.put("idCardNo15", idCardNo15);
		map.put("idCardNo18", idCardNo18);
		map.put("shardCode", shardCode);
		return getSqlMapClientTemplate()
				.queryForList(
						"householdStaff.getActualPopulationByOrgIdAndIdCardNoExcludePopulationId",
						map);
	}

	@Override
	public List<HouseholdStaff> getActualPopulationByOrgIdAndIdCardNoExcludePopulationIdIncludeLogout(
			Long excludePopulationId, Long orgId, String idCardNo15,
			String idCardNo18) {
		Map<String, Object> map = new HashMap<String, Object>();
		String shardCode = IdConversionShardUtil
				.getShardCodeById(excludePopulationId);
		map.put("id", excludePopulationId);
		map.put("orgId", orgId);
		map.put("idCardNo15", idCardNo15);
		map.put("idCardNo18", idCardNo18);
		map.put("shardCode", shardCode);
		return getSqlMapClientTemplate()
				.queryForList(
						"householdStaff.getActualPopulationByOrgIdAndIdCardNoExcludePopulationIdIncludeLogout",
						map);
	}

	private Map<String, Object> getSearchMap(HouseholdStaffVo householdStaffVo,
			Long orgId, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", householdStaffVo.getOrgInternalCode());
		map.put("sortField", sidx);
		map.put("order", sord);
		if (householdStaffVo != null) {
			if (householdStaffVo.getName() != null
					&& !"".equals(householdStaffVo.getName().trim())) {
				map.put("name", householdStaffVo.getName());
			}
			if (householdStaffVo.getIdCardNo() != null
					&& !"".equals(householdStaffVo.getIdCardNo().trim())) {
				map.put("idCardNo", householdStaffVo.getIdCardNo());
			}
			if (householdStaffVo.getGender() != null) {
				map.put("gender", householdStaffVo.getGender());
			}
			if (householdStaffVo.getTelephone() != null
					&& !"".equals(householdStaffVo.getTelephone().trim())) {
				map.put("telephone", householdStaffVo.getTelephone());
			}
			if (householdStaffVo.getMobileNumber() != null
					&& !"".equals(householdStaffVo.getMobileNumber().trim())) {
				map.put("mobileNumber", householdStaffVo.getMobileNumber());
			}
			if (householdStaffVo.getSchooling() != null) {
				map.put("schooling", householdStaffVo.getSchooling());
			}
			if (householdStaffVo.getProvince() != null
					&& !"".equals(householdStaffVo.getProvince().trim())) {
				map.put("province", householdStaffVo.getProvince());
			}
			if (householdStaffVo.getCity() != null
					&& !"".equals(householdStaffVo.getCity().trim())) {
				map.put("city", householdStaffVo.getCity());
			}
			if (householdStaffVo.getDistrict() != null
					&& !"".equals(householdStaffVo.getDistrict().trim())) {
				map.put("district", householdStaffVo.getDistrict());
			}
			if (householdStaffVo.getCurrentAddress() != null
					&& !"".equals(householdStaffVo.getCurrentAddress().trim())) {
				map.put("currentAddress", householdStaffVo.getCurrentAddress());
			}
			if (householdStaffVo.getNativePlaceAddress() != null
					&& !"".equals(householdStaffVo.getNativePlaceAddress()
							.trim())) {
				map.put("nativePlaceAddress",
						householdStaffVo.getNativePlaceAddress());
			}
			if (householdStaffVo.getIsDeath() != null) {
				map.put("isDeath", householdStaffVo.getIsDeath());
			}
			if (householdStaffVo.getWorkUnit() != null
					&& !"".equals(householdStaffVo.getWorkUnit().trim())) {
				map.put("workUnit", householdStaffVo.getWorkUnit());
			}
			if (householdStaffVo.getResidentStatus() != null) {
				map.put("residentStatus", householdStaffVo.getResidentStatus());
			}
			if (householdStaffVo.getOutGone() != null) {
				map.put("outGone", householdStaffVo.getOutGone());
			}
			if (householdStaffVo.getBirthday() != null
					&& !"".equals(householdStaffVo.getBirthday().trim())) {
				map.put("birthday", householdStaffVo.getBirthday());
			}
			if (householdStaffVo.getEndBirthday() != null
					&& !"".equals(householdStaffVo.getEndBirthday().trim())) {
				map.put("endBirthday", householdStaffVo.getEndBirthday());
			}
			if (householdStaffVo.getAccountNumber() != null
					&& !"".equals(householdStaffVo.getAccountNumber().trim())) {
				map.put("accountNumber", householdStaffVo.getAccountNumber());
			}
			if (householdStaffVo.getAddress() != null
					&& !"".equals(householdStaffVo.getAddress().trim())) {
				map.put("address", householdStaffVo.getAddress());
			}
			if (householdStaffVo.getOtherAddress() != null
					&& !"".equals(householdStaffVo.getOtherAddress().trim())) {
				map.put("otherAddress", householdStaffVo.getOtherAddress());
			}
			if (householdStaffVo.getFormerName() != null
					&& !"".equals(householdStaffVo.getFormerName().trim())) {
				map.put("formerName", householdStaffVo.getFormerName());
			}
			if (householdStaffVo.getCareer() != null) {
				map.put("career", householdStaffVo.getCareer());
			}
			if (householdStaffVo.getPoliticalBackground() != null) {
				map.put("politicalBackground",
						householdStaffVo.getPoliticalBackground());
			}
			if (householdStaffVo.getMaritalState() != null) {
				map.put("maritalState", householdStaffVo.getMaritalState());
			}
			if (householdStaffVo.getResidenceType() != null) {
				map.put("residenceType", householdStaffVo.getResidenceType());
			}
			if (householdStaffVo.getNation() != null) {
				map.put("nation", householdStaffVo.getNation());
			}
			if (householdStaffVo.getFaith() != null) {
				map.put("faith", householdStaffVo.getFaith());
			}
			if (householdStaffVo.getHealthState() != null) {
				map.put("healthState", householdStaffVo.getHealthState());
			}
			if (householdStaffVo.getBloodType() != null) {
				map.put("bloodType", householdStaffVo.getBloodType());
			}
			if (householdStaffVo.getEmail() != null
					&& !"".equals(householdStaffVo.getEmail().trim())) {
				map.put("email", householdStaffVo.getEmail());
			}
			if (householdStaffVo.getSchool() != null
					&& !"".equals(householdStaffVo.getSchool().trim())) {
				map.put("school", householdStaffVo.getSchool());
			}// 新增的查询条件
			if (householdStaffVo.getOutGoneBoolean() != null) {
				map.put("outGoneBoolean", householdStaffVo.getOutGoneBoolean());
			}
			if (householdStaffVo.getOutReasonsId() != null) {
				map.put("outReasonsId", householdStaffVo.getOutReasonsId());
			}
			if (householdStaffVo.getOutProvince() != null
					&& !"".equals(householdStaffVo.getOutProvince().trim())) {
				map.put("outProvince", householdStaffVo.getOutProvince());
			}
			if (householdStaffVo.getOutCity() != null
					&& !"".equals(householdStaffVo.getOutCity().trim())) {
				map.put("outCity", householdStaffVo.getOutCity());
			}
			if (householdStaffVo.getOutDistrict() != null
					&& !"".equals(householdStaffVo.getOutDistrict().trim())) {
				map.put("outDistrict", householdStaffVo.getOutDistrict());
			}
			if (householdStaffVo.getLogoutDateStart() != null) {
				map.put("logoutDateStart",
						householdStaffVo.getLogoutDateStart());
			}
			if (householdStaffVo.getLogoutDateEnd() != null) {
				map.put("logoutDateEnd", householdStaffVo.getLogoutDateEnd());
			}
			if (householdStaffVo.getLogoutReason() != null
					&& !"".equals(householdStaffVo.getLogoutReason().trim())) {
				map.put("logoutReason", householdStaffVo.getLogoutReason());
			}
		}
		return map;
	}

	private Map<String, Object> getNewSearchMap(
			HouseholdStaffVo householdStaffVo, String orgInternalCode,
			String sidx, String sord, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("shardCode", shardCode);
		map.put("sortField", sidx);
		map.put("order", sord);
		if (householdStaffVo != null) {
			if (householdStaffVo.getName() != null
					&& !"".equals(householdStaffVo.getName().trim())) {
				map.put("name", householdStaffVo.getName());
			}
			if (householdStaffVo.getUsedName() != null
					&& !"".equals(householdStaffVo.getUsedName().trim())) {
				map.put("usedName", householdStaffVo.getUsedName());
			}
			if (householdStaffVo.getIdCardNo() != null
					&& !"".equals(householdStaffVo.getIdCardNo().trim())) {
				map.put("idCardNo", householdStaffVo.getIdCardNo());
			}
			if (householdStaffVo.getGender() != null) {
				map.put("gender", householdStaffVo.getGender());
			}
			if (householdStaffVo.getTelephone() != null
					&& !"".equals(householdStaffVo.getTelephone().trim())) {
				map.put("telephone", householdStaffVo.getTelephone());
			}
			if (householdStaffVo.getMobileNumber() != null
					&& !"".equals(householdStaffVo.getMobileNumber().trim())) {
				map.put("mobileNumber", householdStaffVo.getMobileNumber());
			}
			if (householdStaffVo.getSchooling() != null) {
				map.put("schooling", householdStaffVo.getSchooling());
			}
			if (householdStaffVo.getProvince() != null
					&& !"".equals(householdStaffVo.getProvince().trim())) {
				map.put("province", householdStaffVo.getProvince());
			}
			if (householdStaffVo.getCity() != null
					&& !"".equals(householdStaffVo.getCity().trim())) {
				map.put("city", householdStaffVo.getCity());
			}
			if (householdStaffVo.getDistrict() != null
					&& !"".equals(householdStaffVo.getDistrict().trim())) {
				map.put("district", householdStaffVo.getDistrict());
			}
			if (householdStaffVo.getCurrentAddress() != null
					&& !"".equals(householdStaffVo.getCurrentAddress().trim())) {
				map.put("currentAddress", householdStaffVo.getCurrentAddress());
			}
			if (householdStaffVo.getNativePlaceAddress() != null
					&& !"".equals(householdStaffVo.getNativePlaceAddress()
							.trim())) {
				map.put("nativePlaceAddress",
						householdStaffVo.getNativePlaceAddress());
			}
			if (householdStaffVo.getIsDeath() != null) {
				map.put("isDeath", householdStaffVo.getIsDeath());
			}
			if (householdStaffVo.getLogout() != null) {
				map.put("logOut", householdStaffVo.getLogout());
			}
			if (householdStaffVo.getWorkUnit() != null
					&& !"".equals(householdStaffVo.getWorkUnit().trim())) {
				map.put("workUnit", householdStaffVo.getWorkUnit());
			}
			if (householdStaffVo.getResidentStatus() != null) {
				map.put("residentStatus", householdStaffVo.getResidentStatus());
			}
			if (householdStaffVo.getOutGone() != null) {
				map.put("outGone", householdStaffVo.getOutGone());
			}
			if (householdStaffVo.getBirthday() != null
					&& !"".equals(householdStaffVo.getBirthday().trim())) {
				map.put("birthday", householdStaffVo.getBirthday());
			}
			if (householdStaffVo.getEndBirthday() != null
					&& !"".equals(householdStaffVo.getEndBirthday().trim())) {
				map.put("endBirthday", householdStaffVo.getEndBirthday());
			}
			if (householdStaffVo.getAccountNumber() != null
					&& !"".equals(householdStaffVo.getAccountNumber().trim())) {
				map.put("accountNumber", householdStaffVo.getAccountNumber());
			}
			if (householdStaffVo.getAddress() != null
					&& !"".equals(householdStaffVo.getAddress().trim())) {
				map.put("address", householdStaffVo.getAddress());
			}
			if (householdStaffVo.getOtherAddress() != null
					&& !"".equals(householdStaffVo.getOtherAddress().trim())) {
				map.put("otherAddress", householdStaffVo.getOtherAddress());
			}
			if (householdStaffVo.getFormerName() != null
					&& !"".equals(householdStaffVo.getFormerName().trim())) {
				map.put("formerName", householdStaffVo.getFormerName());
			}
			if (householdStaffVo.getCareer() != null) {
				map.put("career", householdStaffVo.getCareer());
			}
			if (householdStaffVo.getPoliticalBackground() != null) {
				map.put("politicalBackground",
						householdStaffVo.getPoliticalBackground());
			}
			if (householdStaffVo.getMaritalState() != null) {
				map.put("maritalState", householdStaffVo.getMaritalState());
			}
			if (householdStaffVo.getResidenceType() != null) {
				map.put("residenceType", householdStaffVo.getResidenceType());
			}
			if (householdStaffVo.getNation() != null) {
				map.put("nation", householdStaffVo.getNation());
			}
			if (householdStaffVo.getFaith() != null) {
				map.put("faith", householdStaffVo.getFaith());
			}
			if (householdStaffVo.getHealthState() != null) {
				map.put("healthState", householdStaffVo.getHealthState());
			}
			if (householdStaffVo.getBloodType() != null) {
				map.put("bloodType", householdStaffVo.getBloodType());
			}
			if (householdStaffVo.getEmail() != null
					&& !"".equals(householdStaffVo.getEmail().trim())) {
				map.put("email", householdStaffVo.getEmail());
			}
			if (householdStaffVo.getSchool() != null
					&& !"".equals(householdStaffVo.getSchool().trim())) {
				map.put("school", householdStaffVo.getSchool());
			}
			if (householdStaffVo.getFastSearchKeyWords() != null
					&& !"".equals(householdStaffVo.getFastSearchKeyWords()
							.trim())) {
				map.put("fastSearchKeyWords",
						householdStaffVo.getFastSearchKeyWords());
			}
			// 新增的查询条件
			if (householdStaffVo.getOutGoneBoolean() != null) {
				map.put("outGoneBoolean", householdStaffVo.getOutGoneBoolean());
			}
			if (householdStaffVo.getOutReasonsId() != null) {
				map.put("outReasonsId", householdStaffVo.getOutReasonsId());
			}
			if (householdStaffVo.getOutProvince() != null
					&& !"".equals(householdStaffVo.getOutProvince().trim())) {
				map.put("outProvince", householdStaffVo.getOutProvince());
			}
			if (householdStaffVo.getOutCity() != null
					&& !"".equals(householdStaffVo.getOutCity().trim())) {
				map.put("outCity", householdStaffVo.getOutCity());
			}
			if (householdStaffVo.getOutDistrict() != null
					&& !"".equals(householdStaffVo.getOutDistrict().trim())) {
				map.put("outDistrict", householdStaffVo.getOutDistrict());
			}
			if (householdStaffVo.getLogoutDateStart() != null) {
				map.put("logoutDateStart",
						householdStaffVo.getLogoutDateStart());
			}
			if (householdStaffVo.getLogoutDateEnd() != null) {
				map.put("logoutDateEnd", householdStaffVo.getLogoutDateEnd());
			}
			if (householdStaffVo.getLogoutReason() != null
					&& !"".equals(householdStaffVo.getLogoutReason().trim())) {
				map.put("logoutReason", householdStaffVo.getLogoutReason());
			}
			if (householdStaffVo.getConditionType() != null
					&& !"".equals(householdStaffVo.getConditionType().trim())) {
				map.put("conditionType", householdStaffVo.getConditionType());
			}

		}
		return map;
	}

	private PageInfo<HouseholdStaff> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List<HouseholdStaff> list) {
		PageInfo<HouseholdStaff> pageInfo = new PageInfo<HouseholdStaff>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public List<HouseholdStaff> findGisHouseHoldStaffById(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		String shardCode = IdConversionShardUtil.getShardCodeById(id);
		map.put("shardCode", shardCode);
		return getSqlMapClientTemplate().queryForList(
				"householdStaff.findGisHouseHoldStaffById", map);

	}

	@Override
	public PageInfo<HouseholdStaff> getFurtherStepGisPopulationInfoByPersonType(
			String orgInternalCode, String personType, Integer page,
			Integer rows, String sidx, String sord, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(orgInternalCode, "orgInternalCode");
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("populationType", personType);
		map.put("shardCode", shardCode);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"householdStaff.countFurtherStepGisByPopulationType", map);
		List<HouseholdStaff> list = getSqlMapClientTemplate().queryForList(
				"householdStaff.findFurtherStepGisByPopulationType", map,
				(page - 1) * rows, rows);
		return createPageInfo(page, rows, countNum, list);
	}

	@Override
	public PageInfo<HouseholdStaff> getFurtherStepGisPopulationInfoByPersonType(
			String orgInternalCode, List personType, Integer page,
			Integer rows, String sidx, String sord, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(orgInternalCode, "orgInternalCode");
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("populationType", personType);
		map.put("shardCode", shardCode);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"householdStaff.countFurtherStepGisByPopulationTypeList", map);
		List<HouseholdStaff> list = getSqlMapClientTemplate().queryForList(
				"householdStaff.findFurtherStepGisByPopulationTypeList", map,
				(page - 1) * rows, rows);
		return createPageInfo(page, rows, countNum, list);
	}

	@Override
	public PageInfo<HouseholdStaff> findHouseholdStaffByorgCodeForGis(
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("shardCode", shardCode);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"householdStaff.countHouseholdStaff", map);
		List<HouseholdStaff> list = getSqlMapClientTemplate().queryForList(
				"householdStaff.findHouseholdStaffByorgCodeForGis", map,
				(page - 1) * rows, rows);
		return createPageInfo(page, rows, countNum, list);
	}

	@Override
	public void updateActualPopulationToHasHouseState(Long populationId,
			String address, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", populationId);
		map.put("isHaveHouse", true);
		map.put("noHouseReason", "");
		map.put("currentAddress", address);
		map.put("shardCode", shardCode);
		getSqlMapClientTemplate().update(
				"householdStaff.updateActualPopulationToHasHouseState", map);
	}

	@Override
	public List<HouseholdStaff> findAllBindingHouseholdStaffByorgCodeForGis(
			PopulationCatalog catalog, String orgInternalCode, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("personType", catalog.getParentCatalog());
		map.put("populationType", catalog.getCatalog());
		map.put("defaultLivingHouse", Boolean.TRUE);
		map.put("shardCode", shardCode);
		return getSqlMapClientTemplate().queryForList(
				"householdStaff.findAllBindingHouseholdStaffByorgCodeForGis",
				map);
	}


	@Override
	public List<HouseholdStaff> findhouseholdStaffWhenIsOldFetchHouseIdForMark(
			int pageSize, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("startRow", pageNum * pageSize);
		map.put("endRow", pageSize);
		// map.put("currentDate", CustomDateUitl.dateBeforeNowDate(0));
		map.put("whenOldDate", CustomDateUtil.dateBeforeNowDate(60));
		map.put("shardCode", shardCode);
		return (List<HouseholdStaff>) getSqlMapClientTemplate()
				.queryForList(
						"householdStaff.findhouseholdStaffWhenIsOldFetchHouseIdForMark",
						map);
	}

	@Override
	public Integer countHouseholdStaffWhenIsOldFetchHouseId(String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shardCode", shardCode);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"householdStaff.countHouseholdStaffWhenIsOldFetchHouseId", map);
	}

	@Override
	public Integer countHouseholdStaffWhenIsOldFetchHouseIdForMark(
			String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentDate", CustomDateUtil.dateBeforeNowDate(0));
		map.put("whenOldDate", CustomDateUtil.dateBeforeNowDate(60));
		map.put("shardCode", shardCode);
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"householdStaff.countHouseholdStaffWhenIsOldFetchHouseIdForMark",
						map);
	}


	@Override
	public List<HouseholdStaff> findhouseholdStaffWhenIsNurturesWomenForMark(
			int pageSize, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("endRow", pageSize);
		map.put("minDate", CustomDateUtil.dateBeforeNowDate(15));
		map.put("maxDate", CustomDateUtil.dateBeforeNowDate(49));
		map.put("shardCode", shardCode);
		return (List<HouseholdStaff>) getSqlMapClientTemplate().queryForList(
				"householdStaff.findhouseholdStaffWhenIsNurturesWomenForMark",
				map);
	}

	@Override
	public Integer countHouseholdStaffWhenIsNurturesWomenForMark(
			String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("minDate", CustomDateUtil.dateBeforeNowDate(15));
		map.put("maxDate", CustomDateUtil.dateBeforeNowDate(49));
		map.put("shardCode", shardCode);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"householdStaff.countHouseholdStaffWhenIsNurturesWomenForMark",
				map);
	}

	@Override
	public void updateDeathAndLogoutStateById(Long id, Boolean death,
			Long logoutState, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("logout", logoutState);
		map.put("death", death);
		map.put("shardCode", shardCode);
		getSqlMapClientTemplate().update(
				"householdStaff.updateDeathAndLogoutStateById", map);
	}

	@Override
	public void updateActualPopulationToHasHouseState(Long populationId,
			HouseInfo houseInfo, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", populationId);
		map.put("isHaveHouse", true);
		map.put("noHouseReason", "");
		map.put("currentAddress", houseInfo.getAddress());
		map.put("community", houseInfo.getCommunity());
		map.put("block", houseInfo.getBlock());
		map.put("unit", houseInfo.getUnit());
		map.put("room", houseInfo.getRoom());
		map.put("shardCode", shardCode);
		// map.put("addressType", houseInfo.getAddressType().getId());

		getSqlMapClientTemplate().update(
				"householdStaff.updateActualPopulationToHasHouseState", map);

	}

	@Override
	public void updateFloatingPopulationByDeleteHousePopulationRela(
			Long populationId, Boolean isHaveHouse, String noHouseReason,
			String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", populationId);
		map.put("isHaveHouse", isHaveHouse);
		map.put("noHouseReason", noHouseReason);
		map.put("shardCode", shardCode);
		getSqlMapClientTemplate().update(
				"householdStaff.updateActualPopulationToHasHouseState", map);

	}

	@Override
	public List<HouseholdStaff> findHouseholdStaffsExceptHeadByFamilyId(
			Long familyId, String houseHold, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("familyId", familyId);
		map.put("houseHold", houseHold);
		map.put("shardCode", shardCode);
		return getSqlMapClientTemplate().queryForList(
				"householdStaff.findHouseholdStaffsExceptHeadByFamilyId", map);
	}

	@Override
	public void changeOldHouseHold(HouseholdStaff oldHouseHold, Long id,
			String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idCardNo", oldHouseHold.getIdCardNo());
		map.put("relationShipWithHead", oldHouseHold.getRelationShipWithHead()
				.getId());
		map.put("shardCode", shardCode);
		getSqlMapClientTemplate().update("householdStaff.changeOldHouseHold",
				map);
	}

	@Override
	public void changeNewHouseHold(HouseholdStaff newHouseHold, Long id,
			String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", newHouseHold.getId());
		map.put("relationShipWithHead", newHouseHold.getRelationShipWithHead()
				.getId());
		map.put("shardCode", shardCode);
		getSqlMapClientTemplate().update("householdStaff.changeNewHouseHold",
				map);
	}

	@Override
	public void addFamilyMemberByIdCardNo(String orgCode, Long familyId,
			String idCardNo, Long relationshipWithHeadId, String accountNumber,
			String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgCode", orgCode);
		map.put("familyId", familyId);
		map.put("idCardNo", idCardNo);
		map.put("accountNumber", accountNumber);
		map.put("relationshipWithHeadId", relationshipWithHeadId);
		map.put("shardCode", shardCode);
		getSqlMapClientTemplate().update(
				"householdStaff.addFamilyMemberByIdCardNo", map);

	}

	@Override
	public HouseholdStaff getByOrgInternalCodeAndIdCardNo(
			String orgInternalCode, String idCardNo, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idCardNo", idCardNo);
		map.put("orgInternalCode", orgInternalCode);
		map.put("shardCode", shardCode);
		return (HouseholdStaff) getSqlMapClientTemplate().queryForObject(
				"householdStaff.getByOrgInternalCodeAndIdCardNo", map);
	}

	@Override
	public void moveHouseMember(String orgInternalCode, String idCardNo,
			Long relationshipWithHeadId, String accountNumber, Long familyId,
			String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("idCardNo", idCardNo);
		map.put("relationshipWithHeadId", relationshipWithHeadId);
		map.put("accountNumber", accountNumber);
		map.put("familyId", familyId);
		map.put("shardCode", shardCode);
		getSqlMapClientTemplate().update("householdStaff.moveHouseMember", map);
	}

	@Override
	public HouseholdStaff getHouseholdStaffByIdAndBusinessType(Long id,
			String type, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("populationId", id);
		map.put("populationType", type);
		map.put("actualType", BaseInfoTables.HOUSEHOLDSTAFF_KEY);
		map.put("shardCode", shardCode);
		return (HouseholdStaff) getSqlMapClientTemplate().queryForObject(
				"householdStaff.getHouseholdStaffByIdAndBusinessType", map);
	}

	@Override
	public void updateLogOutPopulationById(LogoutDetail logoutDetail, Long id,
			String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("logOut", logoutDetail.getLogout());
		map.put("logoutDetail", logoutDetail);
		map.put("shardCode", shardCode);
		getSqlMapClientTemplate().update(
				"householdStaff.updateLogOutPopulationById", map);
	}

	@Override
	public void setRelationShipWithHeadNull(Long id,
			Long relationShipWithHeadId, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("relationShipWithHeadId", relationShipWithHeadId);
		map.put("shardCode", shardCode);
		getSqlMapClientTemplate().update(
				"householdStaff.setRelationShipWithHeadNull", map);
	}

	@Override
	public void deleteHouseHoldStaff(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		String shardCode = IdConversionShardUtil.getShardCodeById(id);
		map.put("id", id);
		map.put("shardCode", shardCode);
		getSqlMapClientTemplate().delete(
				"householdStaff.deleteHouseholdStaffById", map);
		syncPopulationSolrIndexForMQ.sendPopulationSolrIndexForMQ(id,
				BaseInfoTables.HOUSEHOLDSTAFF_KEY, OperateMode.DELETE);
	}

	@Override
	public void updateGroupFamily(Map map) {
		getSqlMapClientTemplate().update(
				"groupFamily.updateGroupFamilyForMove", map);
	}

	@Override
	public Integer getCount(HouseholdStaffVo householdStaffVo, String shardCode) {
		if (null == householdStaffVo) {
			return null;
		}
		Map<String, Object> map = getNewSearchMap(householdStaffVo,
				householdStaffVo.getOrgInternalCode(), null, null, shardCode);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"householdStaff.countHouseholdStaff", map);
	}

	@Override
	public HouseholdStaff getHouseholdStaffByBaseInfoId(Long baseInfoId,
			Long orgId, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("baseInfoId", baseInfoId);
		map.put("orgId", orgId);
		map.put("shardCode", shardCode);
		return (HouseholdStaff) getSqlMapClientTemplate().queryForObject(
				"householdStaff.getHouseholdStaffByBaseInfoIdAndOrgId", map);
	}

	@Override
	public List<HouseholdStaff> getHouseholdStaffByBaseInfoId(Long baseInfoId,
			String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("baseInfoId", baseInfoId);
		map.put("shardCode", shardCode);
		return (List<HouseholdStaff>) getSqlMapClientTemplate().queryForList(
				"householdStaff.getHouseholdStaffByBaseInfoId", map);
	}

	/*** 没地方调用 */
	@Override
	public void updateBaseHouseInfoAndRemark(HouseholdStaff population) {
		getSqlMapClientTemplate().update(
				"householdStaff.updateBaseHouseInfoAndRemark", population);
	}

	@Override
	public void deleteHouseholdStaffHouseFamilyInfo(Long houseFamilyId,
			String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseFamilyId", houseFamilyId);
		map.put("shardCode", shardCode);
		getSqlMapClientTemplate().update(
				"householdStaff.deleteHouseholdStaffHouseFamilyInfo", map);
	}

	@Override
	public void updateBirthdayAndGender(Long baseInfoId, Date birthday,
			Long genderId, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("baseInfoId", baseInfoId);
		map.put("birthday", birthday);
		map.put("genderId", genderId);
		map.put("shardCode", shardCode);
		getSqlMapClientTemplate().update(
				"householdStaff.updateBirthdayAndGender", map);
	}

	@Override
	public Long findAllcount() {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"householdStaff.findAllcount");
	}

	@Override
	public List<HouseholdStaff> findAllHouseholdStaffList(int start, int end) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		return getSqlMapClientTemplate().queryForList(
				"householdStaff.findAllHouseholdStaffList", map);
	}

	public HouseholdStaff addHouseholdStaff(HouseholdStaff householdStaff) {
		Long id = (Long) getSqlMapClientTemplate().queryForObject(
				"householdStaff.getId");
		id = IdConversionShardUtil.IdAdditionalShard(id,
				householdStaff.getShardCode());
		householdStaff.setId(id);
		getSqlMapClientTemplate().insert("householdStaff.addHouseholdStaff",
				householdStaff);
		return getHouseholdStaffById(id);
	}

	@Override
	public HouseholdStaff updateHouseholdStaff(HouseholdStaff householdStaff) {
		getSqlMapClientTemplate().insert("householdStaff.updateHouseholdStaff",
				householdStaff);
		return getHouseholdStaffById(householdStaff.getId());
	}

	public HouseholdStaff updateBusiness(HouseholdStaff householdStaff) {
		String shardCode = IdConversionShardUtil
				.getShardCodeById(householdStaff.getId());
		householdStaff.setShardCode(shardCode);
		getSqlMapClientTemplate().update("householdStaff.updateBusiness",
				householdStaff);
		return getHouseholdStaffById(householdStaff.getId());
	}

	@Override
	public List<HouseholdStaff> findHouseholdStaffByIds(List<Long> ids,
			String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("shardCode", shardCode);
		return getSqlMapClientTemplate().queryForList(
				"householdStaff.findHouseholdStaffByIds", map);
	}

	@Override
	public void updateTableUpdateDateById(Long id) {
		super.updateTableUpdateDateById("householdStaffs", id);
	}

	@Override
	public List<Organization> findOrgByAddress(Long addressId, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("addressId", addressId);
		map.put("shardCode", shardCode);
		return getSqlMapClientTemplate().queryForList(
				"householdStaff.findOrgByAddress", map);
	}

	@Override
	public List<Long> findBaseinfoIdByAccountNumber(String shardCode,
			String accountNumber) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shardCode", shardCode);
		map.put("accountNumber", accountNumber);
		return getSqlMapClientTemplate().queryForList(
				"householdStaff.findBaseinfoIdByAccountNumber", map);
	}

}
