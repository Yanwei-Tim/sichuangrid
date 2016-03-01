package com.tianque.baseInfo.goodSamaritan.dao;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao;
import com.tianque.baseInfo.goodSamaritan.domain.GoodSamaritan;
import com.tianque.core.vo.PageInfo;

public interface GoodSamaritanDao extends
		BaseInfoPopulationBaseDao<GoodSamaritan, GoodSamaritan> {
	PageInfo<GoodSamaritan> findGoodSamaritansForPageByOrgInternalCode(
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord, Long isEmphasis);

	// GoodSamaritan getByIdCard(List<String> list, Long orgId);
	//
	// void updateDeathAndLogoutStateById(Long long1, boolean death,
	// Long logoutState);
	//
	// GoodSamaritan updateGoodSamaritanEmphasis(GoodSamaritan goodSamaritan);
}
