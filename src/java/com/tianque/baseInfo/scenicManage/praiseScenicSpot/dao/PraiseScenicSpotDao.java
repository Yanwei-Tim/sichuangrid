package com.tianque.baseInfo.scenicManage.praiseScenicSpot.dao;

import com.tianque.baseInfo.scenicManage.praiseScenicSpot.domain.PraiseScenicSpot;
import com.tianque.core.vo.PageInfo;

public interface PraiseScenicSpotDao {

	public PageInfo<PraiseScenicSpot> findPraiseScenicSpotList(
			PraiseScenicSpot praiseScenicSpot, Integer page, Integer rows,
			String sidx, String sord);

	public PraiseScenicSpot addPraiseScenicSpot(
			PraiseScenicSpot praiseScenicSpot);

	public PraiseScenicSpot getPraiseScenicSpotById(Long id);
}
