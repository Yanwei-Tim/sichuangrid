package com.tianque.baseInfo.scenicManage.scenicSpot.service;

import java.util.List;

import com.tianque.baseInfo.scenicManage.scenicSpot.domain.ScenicSpot;
import com.tianque.core.vo.PageInfo;

public interface ScenicSpotService {

	public ScenicSpot addScenicSpot(ScenicSpot scenicSpot);

	public ScenicSpot getScenicSpotById(Long id);

	public PageInfo<ScenicSpot> findScenicSpotList(ScenicSpot scenicSpot,
			Integer page, Integer rows, String sidx, String sord,
			Boolean isEmphasis);

	public ScenicSpot findScenicSpotByNameAndOrgId(String name, Long orgId);

	public List findScenicSpotByNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode);

	public ScenicSpot updateScenicSpot(ScenicSpot scenicSpot);

	public ScenicSpot updateScenicSpotByName(String name, Long id,
			ScenicSpot domain);

	public void updateEmphasiseByIds(Long[] ids, ScenicSpot domain);

	public boolean deleteScenicSpotById(Long id);

	public List<Long> deleteScenicSpotByIds(List<Long> ids);

	public List<Long> deleteScenicSpotByIds(Long[] ids);

	public ScenicSpot hasDuplicateScenicSpot(Long orgId, String name);

	public boolean hasDuplicateScenicSpotName(Long ownerOrgId, String name,
			Long exceptedId);

	public ScenicSpot updateScenicSpotByEmphasis(ScenicSpot domain);

}
