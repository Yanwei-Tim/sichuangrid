package com.tianque.baseInfo.qPersonnel.dao;

import java.util.List;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao;
import com.tianque.baseInfo.qPersonnel.domain.QPersonnel;
import com.tianque.core.vo.PageInfo;

public interface QPersonnelDao extends
		BaseInfoPopulationBaseDao<QPersonnel, QPersonnel> {

	PageInfo<QPersonnel> findQPersonnelsForPageByOrgInternalCode(
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord, Long isEmphasis);

	QPersonnel getByIdCard(List<String> list, Long orgId);

	void updateDeathAndLogoutStateById(Long long1, boolean death,
			Long logoutState);

	QPersonnel updateQPersonnelEmphasis(QPersonnel fPersonnel);

}
