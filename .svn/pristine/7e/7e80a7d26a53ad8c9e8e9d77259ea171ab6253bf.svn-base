package com.tianque.plugin.dataManage.population.floatingPopulationTemp.dao;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;

@Repository("floatingPopulationTempDao")
public class FloatingPopulationTempDaoImpl extends AbstractBaseDao implements
		FloatingPopulationTempDao {
	//
	// @Override
	// public PageInfo<FloatingPopulationTemp> findGridPageByParameter(
	// String orgInternalCode, int pageNum, int pageSize,
	// String sortField, String order, Long searchType,
	// String currentAddress) {
	// Map<String, Object> query = new HashMap<String, Object>();
	// query.put("sortField", sortField);
	// query.put("order", order);
	// query.put("currentAddress", currentAddress);
	// query.put("orgInternalCode", orgInternalCode);
	// query.put("searchSate", searchType);
	// List list = getSqlMapClientTemplate().queryForList(
	// "floatingPopulationTemp.findGridPageByParameter", query,
	// (pageNum - 1) * pageSize, pageSize);
	// Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
	// "floatingPopulationTemp.countGridPageByParameter", query);
	// return createPageInfo(pageNum, pageSize, countNum, list);
	// }
	//
	// private PageInfo<FloatingPopulationTemp> createPageInfo(int pageNum,
	// int pageSize, Integer countNum, List list) {
	// PageInfo<FloatingPopulationTemp> pageInfo = new
	// PageInfo<FloatingPopulationTemp>();
	// pageInfo.setResult(list);
	// pageInfo.setTotalRowSize(countNum);
	// pageInfo.setCurrentPage(pageNum);
	// pageInfo.setPerPageSize(pageSize);
	// return pageInfo;
	// }
	//
	// @Override
	// public FloatingPopulationTemp addFloatingPopulationTemp(
	// FloatingPopulationTemp floatingPopulationTemp) {
	// Long id = (Long) getSqlMapClientTemplate().insert(
	// "floatingPopulationTemp.addFloatingPopulationTemp",
	// floatingPopulationTemp);
	// return getFloatingPopulationTempById(id);
	// }
	//
	// public FloatingPopulationTemp getFloatingPopulationTempById(Long id) {
	// return (FloatingPopulationTemp) getSqlMapClientTemplate()
	// .queryForObject(
	// "floatingPopulationTemp.getFloatingPopulationTempById",
	// id);
	// }
	//
	// @Override
	// public void deleteFloatingPopulationTempById(Long id) {
	// getSqlMapClientTemplate().delete(
	// "floatingPopulationTemp.deleteFloatingPopulationTempById", id);
	// }
	//
	// @Override
	// public List<FloatingPopulationTemp>
	// findFloatingPopulationTempsByIdcardAndOrgInternalCode(
	// List idCardNo, String orgInternalCode) {
	// if (!StringUtil.isStringAvaliable(orgInternalCode)) {
	// throw new BusinessValidationException("orgInternalCode为空");
	// }
	// Map<String, Object> map = new HashMap<String, Object>();
	//
	// map.put("idCardNo", idCardNo);
	// map.put("orgInternalCode", orgInternalCode);
	// return getSqlMapClientTemplate()
	// .queryForList(
	// "floatingPopulationTemp.findFloatingPopulationTempsByIdcardAndOrgInternalCode",
	// map);
	// }
	//
	// @Override
	// public void
	// updateFloatingPopulationTempInIdByIdCardAndIdListAndOrgInternalCode(
	// String idCardNo, Long id, List<Long> idList, String orgInternalCode) {
	// if (!StringUtil.isStringAvaliable(orgInternalCode)
	// || !StringUtil.isStringAvaliable(idCardNo) || id == null
	// || id.longValue() == 0 || idList == null) {
	// throw new BusinessValidationException("参数错误！");
	// }
	//
	// Map<String, Object> map = new HashMap<String, Object>();
	//
	// map.put("idCardNo", idCardNo);
	// map.put("id", id);
	// map.put("idList", idList);
	// map.put("orgInternalCode", orgInternalCode);
	//
	// getSqlMapClientTemplate()
	// .update("floatingPopulationTemp.updateFloatingPopulationTempInIdByIdCardAndIdList",
	// map);
	// }
	//
	// @Override
	// public FloatingPopulationTemp updateFloatingPopulationTempForClaimById(
	// Long id, Long orgId, String orgInternalCode, Long claimState,
	// Date claimDate, Long claimUserId, String claimUserName,
	// Long claimOrgId) {
	// validateParam(id, orgId, orgInternalCode, claimState, claimDate,
	// claimUserId, claimUserName, claimOrgId);
	//
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("id", id);
	// map.put("orgId", orgId);
	// map.put("orgInternalCode", orgInternalCode);
	// map.put("claimState", claimState);
	// map.put("claimDate", claimDate);
	// map.put("claimUserId", claimUserId);
	// map.put("claimUserName", claimUserName);
	// map.put("claimOrgId", claimOrgId);
	//
	// getSqlMapClientTemplate()
	// .update("floatingPopulationTemp.updateFloatingPopulationTempForCliamById",
	// map);
	//
	// return this.getFloatingPopulationTempById(id);
	// }
	//
	// private void validateParam(Long id, Long orgId, String orgInternalCode,
	// Long claimState, Date claimDate, Long claimUserId,
	// String claimUserName, Long claimOrgId) {
	// if (id == null || orgId == null
	// || !StringUtil.isStringAvaliable(orgInternalCode)
	// || claimState == null || claimDate == null
	// || claimUserId == null
	// || !StringUtil.isStringAvaliable(claimUserName)
	// || claimOrgId == null) {
	// throw new BusinessValidationException("参数错误！");
	// }
	// }
	//
	// @Override
	// public List<FloatingPopulationTemp> getFloatingPopulationTempByInId(
	// Long inId) {
	// return (List<FloatingPopulationTemp>) getSqlMapClientTemplate()
	// .queryForList(
	// "floatingPopulationTemp.getFloatingPopulationTempByInId",
	// inId);
	// }
	//
	// @Override
	// public void updateFloatingPopulationTempForUnClaimById(Long id, Long
	// orgId,
	// String orgInternalCode, Long claimState) {
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("id", id);
	// map.put("orgId", orgId);
	// map.put("orgInternalCode", orgInternalCode);
	// map.put("claimState", claimState);
	// getSqlMapClientTemplate()
	// .update("floatingPopulationTemp.updateFloatingPopulationTempForUnClaimById",
	// map);
	//
	// }
	//
	// public FloatingPopulationTemp updateFloatingPopulationTemp(
	// FloatingPopulationTemp floatingPopulationTemp) {
	// getSqlMapClientTemplate().update(
	// "floatingPopulationTemp.updateFloatingPopulationTemp",
	// floatingPopulationTemp);
	// return getFloatingPopulationTempById(floatingPopulationTemp.getId());
	// }
	//
	// public List<FloatingPopulationTemp>
	// findFloatingPopulationTempsByIdcardAndImportOrgId(
	// List idCardNo, Long oldOrganizationId) {
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("idCardNo", idCardNo);
	// map.put("oldOrganizationId", oldOrganizationId);
	// return getSqlMapClientTemplate()
	// .queryForList(
	// "floatingPopulationTemp.findFloatingPopulationTempsByIdcardAndImportOrgId",
	// map);
	// }
	//
	// public FloatingPopulationTemp updateFloatingPopulationTempSateById(Long
	// id,
	// Long repeatState) {
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("id", id);
	// map.put("repeatState", repeatState);
	// getSqlMapClientTemplate().update(
	// "floatingPopulationTemp.updateFloatingPopulationTempByOrgId",
	// map);
	// return getFloatingPopulationTempById(id);
	// }
}
