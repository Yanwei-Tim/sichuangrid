package com.tianque.baseInfo.dangerousGoodsPractitioner.dao;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao;
import com.tianque.baseInfo.dangerousGoodsPractitioner.domain.DangerousGoodsPractitioner;
import com.tianque.core.vo.PageInfo;

public interface DangerousGoodsPractitionerDao extends
		BaseInfoPopulationBaseDao<DangerousGoodsPractitioner, DangerousGoodsPractitioner> {

	public DangerousGoodsPractitioner updateDangerousGoodsPractitionerEmphasis(
			DangerousGoodsPractitioner dangerousGoodsPractitioner);

	public DangerousGoodsPractitioner updateDangerousGoodsPractitionerEmphasisById(Long id,
			Long isEmphasis);

	public PageInfo<DangerousGoodsPractitioner> findDangerousGoodsPractitionersForPageByOrgInternalCode(
			String orgInternalCode, Integer page, Integer rows, String sidx, String sord,
			Long isEmphasis);

	public void updateDeathAndLogoutStateById(Long id, Boolean death, Long logoutState);
	
	public void updateTableUpdateDateById(Long id);

}
