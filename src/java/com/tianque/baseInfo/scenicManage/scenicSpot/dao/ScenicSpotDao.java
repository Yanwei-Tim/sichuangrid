package com.tianque.baseInfo.scenicManage.scenicSpot.dao;

import java.util.Date;
import java.util.List;

import com.tianque.baseInfo.scenicManage.scenicSpot.domain.ScenicSpot;
import com.tianque.core.vo.PageInfo;

public interface ScenicSpotDao {
	public PageInfo<ScenicSpot> findScenicSpotList(ScenicSpot scenicSpot,
			Integer page, Integer rows, String sidx, String sord,
			Boolean isEmphasis);

	public ScenicSpot addScenicSpot(ScenicSpot scenicSpot);

	public ScenicSpot updateScenicSpot(ScenicSpot scenicSpot);

	public void deleteScenicSpotById(Long id);

	public ScenicSpot getSimpleScenicSpotById(Long id);

	public ScenicSpot findScenicSpotByNameAndOrgId(String name, Long orgId);

	public List<ScenicSpot> searchScenicSpotForExport(ScenicSpot scenicSpot,
			Integer page, Integer rows, String sidx, String sord);

	public List findScenicSpotByNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode);

	public ScenicSpot getScenicSpotByName(String name, Long orgId);

	public void updateEmphasiseById(Long id, Boolean isEmphasis,
			String logoutReason, Date logoutDate);

	public Integer getCount(ScenicSpot scenicSpot);

}