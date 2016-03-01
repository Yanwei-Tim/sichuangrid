package com.tianque.baseInfo.mPersonnel.dao;

import java.util.List;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao;
import com.tianque.baseInfo.mPersonnel.domain.MPersonnel;
import com.tianque.core.vo.PageInfo;

public interface MPersonnelDao extends
		BaseInfoPopulationBaseDao<MPersonnel, MPersonnel> {

	PageInfo<MPersonnel> findMPersonnelsForPageByOrgInternalCode(
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord, Long isEmphasis);

	MPersonnel getByIdCard(List<String> list, Long orgId);

	void updateDeathAndLogoutStateById(Long long1, boolean death,
			Long logoutState);

	MPersonnel updateMPersonnelEmphasis(MPersonnel mPersonnel);

}
