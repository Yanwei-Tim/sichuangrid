package com.tianque.baseInfo.scenicManage.scenicSpot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.scenicManage.scenicSpot.dao.SearchScenicSpotDao;
import com.tianque.baseInfo.scenicManage.scenicSpot.domain.ScenicSpot;
import com.tianque.baseInfo.scenicManage.scenicSpot.vo.SearchScenicSpotVo;
import com.tianque.core.vo.PageInfo;

@Repository("searchScenicSpotService")
public class SearchScenicSpotServiceImpl implements SearchScenicSpotService {

	@Autowired
	private SearchScenicSpotDao searchScenicSpotDao;

	@Override
	public PageInfo<ScenicSpot> findScenicSpotListForSearch(
			SearchScenicSpotVo scenicSpotVo, Integer page, Integer rows, String sidx,
			String sord) {
		return searchScenicSpotDao.findScenicSpotListForSearch(scenicSpotVo,
				page, rows, sidx, sord);
	}

	@Override
	public List findScenicSpotByNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode) {
		return searchScenicSpotDao
				.findScenicSpotByNameAndPinyinAndOrgInternalCode(name,
						orgInternalCode);
	}

}
