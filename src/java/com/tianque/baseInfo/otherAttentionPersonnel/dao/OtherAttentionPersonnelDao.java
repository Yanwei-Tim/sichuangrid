package com.tianque.baseInfo.otherAttentionPersonnel.dao;

import java.util.List;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao;
import com.tianque.baseInfo.otherAttentionPersonnel.domain.OtherAttentionPersonnel;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchOtherAttentionPersonnelVo;

public interface OtherAttentionPersonnelDao
		extends
		BaseInfoPopulationBaseDao<OtherAttentionPersonnel, SearchOtherAttentionPersonnelVo> {

	PageInfo<OtherAttentionPersonnel> findOtherAttentionPersonnelsForPageByOrgInternalCode(
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord, Long isEmphasis);

	OtherAttentionPersonnel getByIdCard(List<String> list, Long orgId);

	void updateDeathAndLogoutStateById(Long long1, boolean death,
			Long logoutState);

	OtherAttentionPersonnel updateOtherAttentionPersonnelEmphasis(
			OtherAttentionPersonnel otherAttentionPersonnel);
	
	public void updateTableUpdateDateById(Long id);
}
