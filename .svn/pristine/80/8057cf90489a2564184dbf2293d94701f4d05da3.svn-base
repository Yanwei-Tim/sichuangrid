package com.tianque.baseInfo.unsettledPopulation.dao;

import java.util.List;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.unsettledPopulation.domain.UnsettledPopulation;
import com.tianque.core.vo.PageInfo;
import com.tianque.service.util.PopulationCatalog;

public interface UnsettledPopulationDao extends
		BaseInfoPopulationBaseDao<UnsettledPopulation, UnsettledPopulation> {

	public UnsettledPopulation addUnsettledPopulation(
			UnsettledPopulation unsettledPopulation);

	public PageInfo<UnsettledPopulation> findUnsettledPopulationsForPageByOrgInternalCode(
			Long logOut, Long isDeadth, String orgInternalCode,
			Integer pageNum, Integer pageSize, String sortField, String order);

	public UnsettledPopulation updateUnsettledPopulation(
			UnsettledPopulation unsettledPopulation);

	public UnsettledPopulation getUnsettledPopulationById(Long id);

	public int deleteUnsettledPopulationById(Long id);

	public UnsettledPopulation getUnsettledPopulationByIdCardNoAndOrganizationId(
			String idCardNo15, String idCardNo18, Long organizationId);

	public UnsettledPopulation getUnsettledPopulationByIdCard(
			List<String> idCardNoList, Long orgId);

	public List<UnsettledPopulation> getActualPopulationByOrgIdAndIdCardNoExcludePopulationId(
			Long excludePopulationId, Long orgId, String idCardNo15,
			String idCardNo18);

	public UnsettledPopulation findGisUnsettledPopulationById(Long personId);

	public void updateDeathAndLogoutStateById(Long id, Boolean death,
			Long logoutState);

	public void updateActualPopulationToHasHouseState(Long populationId,
			String address);

	public List<UnsettledPopulation> findAllBindingUnsettledPopulationByorgCodeForGis(
			PopulationCatalog catalog, String orgInternalCode);

	/**
	 * 修改未落户身份证号码
	 * 
	 * @param countrymen
	 * @param idCardNo
	 * @return
	 */
	public UnsettledPopulation changeUnsettledPopulationIdCardNo(
			Countrymen countrymen, String idCardNo);
}