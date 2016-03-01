package com.tianque.baseInfo.positiveInfo.dao;

import java.util.List;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao;
import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchPositiveInfoVo;

public interface PositiveInfoDao extends
		BaseInfoPopulationBaseDao<PositiveInfo, SearchPositiveInfoVo> {

	/**
	 * 查看规正人员信息
	 * 
	 * @param positiveInfos
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<PositiveInfo> findPosiviteInfosForPage(
			PositiveInfo positiveInfo, int pageNum, int pageSize,
			String sortField, String order, Long isEmphasis);

	public void updateEmphasiseById(Long id, Long isEmphasis);

	public void updateDeathAndLogoutStateById(Long id, Boolean death,
			Long logoutState);

	public List<PositiveInfo> findPositiveInfoByDate(Long positiveTypeId,
			long time);

	public PageInfo<PositiveInfo> findPositiveInfosForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sortField, String order, Long isEmphasis);
	
	public void updateTableUpdateDateById(Long id);

}
