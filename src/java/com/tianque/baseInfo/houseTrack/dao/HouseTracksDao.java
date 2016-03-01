package com.tianque.baseInfo.houseTrack.dao;

import com.tianque.baseInfo.houseTrack.domain.HouseTracks;
import com.tianque.core.vo.PageInfo;

public interface HouseTracksDao {

	PageInfo<HouseTracks> findHouseTracksByHouseId(Integer houseId,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	HouseTracks addHouseTracks(HouseTracks houseTracks);

}
