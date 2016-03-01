package com.tianque.plugin.dataManage.population.householdStaffTemp.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.dataManage.population.householdStaffTemp.domain.HouseholdStaffTemp;

@Repository("householdStaffTempDao")
public class HouseholdStaffTempDaoImpl extends AbstractBaseDao implements HouseholdStaffTempDao {

	@Override
	public PageInfo<HouseholdStaffTemp> findHouseholdStaffTempForPage(String orgInternalCode,
			int pageNum, int pageSize, String sortField, String order, Long searchType,
			String currentAddress) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("sortField", sortField);
		query.put("order", order);
		query.put("currentAddress", currentAddress);
		query.put("orgInternalCode", orgInternalCode);
		query.put("searchSate", searchType);
		List list = getSqlMapClientTemplate().queryForList(
				"householdStaffTemp.findHouseholdStaffTempForPage", query,
				(pageNum - 1) * pageSize, pageSize);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"householdStaffTemp.countHouseholdStaffTempForPage", query);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<HouseholdStaffTemp> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<HouseholdStaffTemp> pageInfo = new PageInfo<HouseholdStaffTemp>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public HouseholdStaffTemp addHouseholdStaffTemp(HouseholdStaffTemp householdStaffTemp) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"householdStaffTemp.addHouseholdStaffTemp", householdStaffTemp);
		return getHouseholdStaffTempById(id);
	}

	@Override
	public List<HouseholdStaffTemp> findHouseholdStaffTempsByIdCardNoAndImportOrgId(List idCardNo,
			Long oldOrganizationId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idCardNo", idCardNo);
		map.put("oldOrganizationId", oldOrganizationId);
		return getSqlMapClientTemplate().queryForList(
				"householdStaffTemp.findHouseholdStaffTempsByIdCardNoAndImportOrgId", map);
	}

	@Override
	public HouseholdStaffTemp getHouseholdStaffTempById(Long id) {
		return (HouseholdStaffTemp) getSqlMapClientTemplate().queryForObject(
				"householdStaffTemp.getHouseholdStaffTempById", id);
	}

	@Override
	public HouseholdStaffTemp updateHouseholdStaffTempSateById(Long id, Long repeatState) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("repeatState", repeatState);
		getSqlMapClientTemplate()
				.update("householdStaffTemp.updateHouseholdStaffTempSateById", map);
		return getHouseholdStaffTempById(id);
	}

	@Override
	public void deleteHouseholdStaffTempByIds(Long id) {
		getSqlMapClientTemplate().delete("householdStaffTemp.deleteHouseholdStaffTempById", id);
	}

	@Override
	public List<HouseholdStaffTemp> findHouseholdStaffTempsByIdCardNoAndOrgInternalCode(
			List idCardNo, String orgInternalCode) {
		if (!StringUtil.isStringAvaliable(orgInternalCode)) {
			throw new BusinessValidationException("orgInternalCode为空");
		}
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("idCardNo", idCardNo);
		map.put("orgInternalCode", orgInternalCode);
		return getSqlMapClientTemplate().queryForList(
				"householdStaffTemp.findHouseholdStaffTempsByIdCardNoAndOrgInternalCode", map);
	}

	@Override
	public HouseholdStaffTemp updateHouseholdStaffTempForClaimById(Long id, Long orgId,
			String orgInternalCode, Long claimState, Date claimDate, Long claimUserId,
			String claimUserName, Long claimOrgId) {
		validateParam(id, orgId, orgInternalCode, claimState, claimDate, claimUserId,
				claimUserName, claimOrgId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("orgId", orgId);
		map.put("orgInternalCode", orgInternalCode);
		map.put("claimState", claimState);
		map.put("claimDate", claimDate);
		map.put("claimUserId", claimUserId);
		map.put("claimUserName", claimUserName);
		map.put("claimOrgId", claimOrgId);

		getSqlMapClientTemplate().update("householdStaffTemp.updateHouseholdStaffTempForClaimById",
				map);
		return getHouseholdStaffTempById(id);
	}

	private void validateParam(Long id, Long orgId, String orgInternalCode, Long claimState,
			Date claimDate, Long claimUserId, String claimUserName, Long claimOrgId) {
		if (id == null || orgId == null || !StringUtil.isStringAvaliable(orgInternalCode)
				|| claimState == null || claimDate == null || claimUserId == null
				|| !StringUtil.isStringAvaliable(claimUserName) || claimOrgId == null) {
			throw new BusinessValidationException("参数错误！");
		}
	}

	@Override
	public void updateHouseholdStaffTempInIdByIdCardNoAndIdListAndOrgInternalCode(String idCardNo,
			Long id, List<Long> idList, String orgInternalCode) {
		if (!StringUtil.isStringAvaliable(orgInternalCode)
				|| !StringUtil.isStringAvaliable(idCardNo) || id == null || id.longValue() == 0
				|| idList == null) {
			throw new BusinessValidationException("参数错误！");
		}

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("idCardNo", idCardNo);
		map.put("id", id);
		map.put("idList", idList);
		map.put("orgInternalCode", orgInternalCode);

		getSqlMapClientTemplate().update(
				"householdStaffTemp.updateHouseholdStaffTempInIdByIdCardNoAndIdList", map);
	}

	@Override
	public List<HouseholdStaffTemp> getHouseholdStaffTempByInId(Long inId) {
		return (List<HouseholdStaffTemp>) getSqlMapClientTemplate().queryForList(
				"householdStaffTemp.getHouseholdStaffTempByInId", inId);
	}

	@Override
	public void updateHouseholdStaffTempForUnClaimById(Long id, Long orgId, String orgInternalCode,
			Long claimState) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("orgId", orgId);
		map.put("orgInternalCode", orgInternalCode);
		map.put("claimState", claimState);
		getSqlMapClientTemplate().update(
				"householdStaffTemp.updateHouseholdStaffTempForUnClaimById", map);
	}

	@Override
	public HouseholdStaffTemp updateHouseholdStaffTemp(HouseholdStaffTemp householdStaffTemp) {
		getSqlMapClientTemplate().update("householdStaffTemp.updateHouseholdStaffTemp",
				householdStaffTemp);
		return getHouseholdStaffTempById(householdStaffTemp.getId());
	}

}