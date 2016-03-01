package com.tianque.baseInfo.scenicManage.scenicSpot.dao;

import java.util.List;

import com.tianque.baseInfo.scenicManage.scenicSpot.domain.ScenicSpot;
import com.tianque.baseInfo.scenicManage.scenicSpot.vo.SearchScenicSpotVo;
import com.tianque.core.vo.PageInfo;

public interface SearchScenicSpotDao {

	public PageInfo<ScenicSpot> findScenicSpotListForSearch(
			SearchScenicSpotVo scenicSpotVo, Integer page, Integer rows, String sidx,
			String sord);

	public List findScenicSpotByNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode);

}