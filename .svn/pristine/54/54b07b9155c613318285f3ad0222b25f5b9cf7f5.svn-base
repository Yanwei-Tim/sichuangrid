package com.tianque.plugin.dataManage.population.unsettledPopulationTemp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.plugin.dataManage.population.unsettledPopulationTemp.dao.UnsettledPopulationTempDao;
import com.tianque.plugin.dataManage.population.unsettledPopulationTemp.domain.UnsettledPopulationTemp;
import com.tianque.service.impl.LogableService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("unsettledPopulationTempService")
@Transactional
public class UnsettledPopulationTempServiceImpl extends LogableService
		implements UnsettledPopulationTempService {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private UnsettledPopulationTempDao unsettledPopulationTempDao;

	public UnsettledPopulationTemp getTempById(Long id) {
		return (UnsettledPopulationTemp) unsettledPopulationTempDao
				.getTempById(id);
	}

	@Override
	public UnsettledPopulationTemp updateTemp(UnsettledPopulationTemp temp) {
		unsettledPopulationTempDao.updateTemp(temp);
		return getTempById(temp.getId());
	}

	// @Override
	// public PageInfo<UnsettledPopulationTemp>
	// findUnsettledPopulationTempForPage(
	// Long orgId, Integer page, Integer rows, String sidx, String sord,
	// Long searchType, String currentAddress) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public UnsettledPopulationTemp addUnsettledPopulationTemp(
	// UnsettledPopulationTemp domain) {
	// autoFillOrganizationInternalCode(domain);
	// // if (domain.getClaimState() == null) {
	// // // domain.setClaimState(DepartmentImportSate.UN_CLAIM);
	// // }
	// return unsettledPopulationTempDao.addUnsettledPopulationTemp(domain);
	// }
	//
	// @Override
	// public UnsettledPopulationTemp getUnsettledPopulationTempById(Long id) {
	// return unsettledPopulationTempDao.getUnsettledPopulationTempById(id);
	// }
	//
	// @Override
	// public void deleteUnsettledPopulationTempByIds(Long[]
	// analyzePopulationIds) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public UnsettledPopulationTempResultListVo claimHouseholdStaffs(
	// String populationIds, Long id) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public void updateUnsettledPopulationTempForUnClaim(String populationIds)
	// {
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
	//
	// @Override
	// public UnsettledPopulationTemp
	// claimUpdateUnsettledPopulationTempAndHouseholdStaff(
	// HouseholdStaff householdStaff, Long id) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// private void autoFillOrganizationInternalCode(UnsettledPopulationTemp
	// domain) {
	// Organization org = organizationService.getSimpleOrgById(domain
	// .getOrganization().getId());
	// if (org == null) {
	// throw new BusinessValidationException("找不到指定的网格");
	// }
	// domain.setOrgInternalCode(org.getOrgInternalCode());
	// }

}
