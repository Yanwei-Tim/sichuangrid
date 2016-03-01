package com.tianque.baseInfo.houseTrack.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.houseTrack.domain.HouseTracks;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;

@Repository("houseTracksDao")
public class HouseTracksDaoImpl extends AbstractBaseDao implements
		HouseTracksDao {

	public PageInfo<HouseTracks> findHouseTracksByHouseId(Integer houseId,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseId", houseId);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"houseTracks.countHouseTracksByHouseId", map);
		if (sidx != null && !"".equals(sidx.trim())) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		List<HouseTracks> list = getSqlMapClientTemplate().queryForList(
				"houseTracks.findHouseTracksByHouseId", map,
				(pageNum - 1) * pageSize, pageSize);
		return new PageInfo<HouseTracks>(pageNum, pageSize, countNum, list);
	}

	public HouseTracks addHouseTracks(HouseTracks houseTracks) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"houseTracks.addHouseTracks", houseTracks);
		return getHouseTracksById(id);
	}

	private HouseTracks getHouseTracksById(Long id) {
		return (HouseTracks) this.getSqlMapClientTemplate().queryForObject(
				"houseTracks.getHouseTracksById", id);
	}

}
