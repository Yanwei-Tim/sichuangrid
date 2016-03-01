package com.tianque.baseInfo.nurturesWomen.dao;

import java.util.List;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao;
import com.tianque.baseInfo.nurturesWomen.domain.NurturesWomen;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchNurturesWomenVo;

public interface NurturesWomenDao extends
		BaseInfoPopulationBaseDao<NurturesWomen, SearchNurturesWomenVo> {

	public PageInfo<NurturesWomen> findNurturesWomenForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sortField, String order, Long isEmphasis);

	public NurturesWomen getNurturesWomenByIdCardNoAndOrganizationId(
			String idCardNo, Long orgId, String shardCode);

	public PageInfo<NurturesWomen> searchNurturesWomen(Integer pageNum,
			Integer pageSize, String sidx, String sord,
			SearchNurturesWomenVo searchNurturesWomenVo);

	public List<NurturesWomen> searchAllNurturesWomen(String sidx, String sord,
			SearchNurturesWomenVo searchNurturesWomenVo);

	public void updateDeathAndLogoutStateById(Long long1, Boolean death,
			Long logoutState);

	public Integer getCount(SearchNurturesWomenVo searchNurturesWomenVo);
	
	public Long saveNurturesWomenForJob(NurturesWomen nurturesWomen);
	
	public void updateTableUpdateDateById(Long id);
}
