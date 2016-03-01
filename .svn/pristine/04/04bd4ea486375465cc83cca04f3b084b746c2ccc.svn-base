package com.tianque.plugin.dataManage.population.unsettledPopulationTemp.dao;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.dataManage.population.unsettledPopulationTemp.domain.UnsettledPopulationTemp;

@Repository("unsettledPopulationTempDao")
public class UnsettledPopulationTempDaoImpl extends AbstractBaseDao implements
		UnsettledPopulationTempDao {

	@Override
	public UnsettledPopulationTemp getTempById(Long id) {
		return (UnsettledPopulationTemp) getSqlMapClientTemplate().queryForObject(
				"unsettledPopulationTemp.getUnsettledPopulationTempById", id);
	}

	@Override
	public void updateTemp(UnsettledPopulationTemp temp) {
		getSqlMapClientTemplate().update("unsettledPopulationTemp.updateUnsettledPopulationTemp",
				temp);
	}
	// @Override
	// public PageInfo<UnsettledPopulationTemp>
	// findUnsettledPopulationTempForPage(
	// String orgInternalCode, int pageNum, int pageSize,
	// String sortField, String order, Long searchType,
	// String currentAddress) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public UnsettledPopulationTemp addUnsettledPopulationTemp(
	// UnsettledPopulationTemp unsettledPopulationTemp) {
	// Long id = (Long) getSqlMapClientTemplate().insert(
	// "unsettledPopulationTemp.addUnsettledPopulationTemp",
	// unsettledPopulationTemp);
	// return getUnsettledPopulationTempById(id);
	// }
	//
	// @Override
	// public UnsettledPopulationTemp getUnsettledPopulationTempById(Long id) {
	// return (UnsettledPopulationTemp) getSqlMapClientTemplate()
	// .queryForObject(
	// "unsettledPopulationTemp.getUnsettledPopulationTempById",
	// id);
	// }
	//
	// @Override
	// public UnsettledPopulationTemp updateUnsettledPopulationTempSateById(
	// Long id, Long repeat) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public List<UnsettledPopulationTemp>
	// findUnsettledPopulationTempsByIdCardNoAndImportOrgId(
	// List idCardNo, Long oldOrganizationId) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public void deleteUnsettledPopulationTempByIds(Long id) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public List<UnsettledPopulationTemp>
	// findUnsettledPopulationTempsByIdCardNoAndOrgInternalCode(
	// List<String> paseIdcardList, String orgInternalCode) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public void
	// updateUnsettledPopulationTempInIdByIdCardNoAndIdListAndOrgInternalCode(
	// String idCardNo, Long id, List<Long> idList, String orgInternalCode) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public UnsettledPopulationTemp updateUnsettledPopulationTempForClaimById(
	// Long id, Long orgId, String orgInternalCode, Long claim, Date now,
	// Long id2, String name, Long id3) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public List<UnsettledPopulationTemp> getUnsettledPopulationTempByInId(
	// Long inId) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public void updateUnsettledPopulationTempForUnClaimById(Long id,
	// Long orgId, String orgInternalCode, Long unClaim) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public UnsettledPopulationTemp updateUnsettledPopulationTemp(
	// UnsettledPopulationTemp population) {
	// // TODO Auto-generated method stub
	// return null;
	// }

}
