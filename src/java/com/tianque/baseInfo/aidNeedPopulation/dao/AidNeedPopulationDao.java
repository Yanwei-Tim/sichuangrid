package com.tianque.baseInfo.aidNeedPopulation.dao;

import com.tianque.baseInfo.aidNeedPopulation.domain.AidNeedPopulation;
import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchAidNeedPopulationVo;

public interface AidNeedPopulationDao extends
		BaseInfoPopulationBaseDao<AidNeedPopulation, SearchAidNeedPopulationVo> {

	public PageInfo<AidNeedPopulation> findAidNeedPopulationForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize, String sortField,
			String order, Long isEmphasis);

	public AidNeedPopulation getAidNeedPopulationByIdCardNoAndOrganizationId(String idCardNo15,
			String idCardNo18, Long organizationId);

	public void updateEmphasiseById(Long id, Long isEmphasis);

	/**
	 * 根据id和死亡状态修改死亡和注销的信息
	 * 
	 * @param id
	 * @param death
	 * @param logoutState
	 */
	public void updateDeathAndLogoutStateById(Long id, boolean death, Long logoutState);
	
	public void updateTableUpdateDateById(Long id);

}
