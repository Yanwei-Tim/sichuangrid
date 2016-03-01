package com.tianque.baseInfo.fPersonnel.dao;

import java.util.List;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao;
import com.tianque.baseInfo.fPersonnel.domain.FPersonnel;
import com.tianque.core.vo.PageInfo;

public interface FPersonnelDao extends
		BaseInfoPopulationBaseDao<FPersonnel, FPersonnel> {

	PageInfo<FPersonnel> findFPersonnelsForPageByOrgInternalCode(
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord, Long isEmphasis);

	FPersonnel getByIdCard(List<String> list, Long orgId);

	void updateDeathAndLogoutStateById(Long long1, boolean death,
			Long logoutState);

	FPersonnel updateFPersonnelEmphasis(FPersonnel fPersonnel);

}
