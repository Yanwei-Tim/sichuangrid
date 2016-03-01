package com.tianque.baseInfo.elderlyPeople.dao;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao;
import com.tianque.baseInfo.elderlyPeople.domain.ElderlyPeople;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchElderlyPeopleVo;

public interface ElderlyPeopleDao extends
		BaseInfoPopulationBaseDao<ElderlyPeople, SearchElderlyPeopleVo> {

	public PageInfo<ElderlyPeople> findElderlyPeopleForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, Long isEmphasis);

	public ElderlyPeople getElderlyPeopleByIdCardNoAndOrganizationId(
			String idCardNo, Long organizationId, String shardCode);

	public void updateDeathAndLogoutStateById(Long long1, Boolean death,
			Long logoutState);

	public Long saveElderlyPeopleForJob(ElderlyPeople elderlyPeople);
	
	public void updateTableUpdateDateById(Long id);
}
