package com.tianque.openLayersMap.positioningTrajectory.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.positioningTrajectory.domian.PositioningTrajectory;
import com.tianque.openLayersMap.positioningTrajectory.domian.vo.SearchPositioningTrajectoryVo;

@Repository("positioningTrajectoryDao")
public class PositioningTrajectoryDaoImpl extends AbstractBaseDao implements
		PositioningTrajectoryDao {

	@Override
	public PositioningTrajectory addPositioningTrajectory(
			PositioningTrajectory positioningTrajectory) {
		if (positioningTrajectory == null)
			throw new BusinessValidationException("参数错误");
		Long id = (Long) getSqlMapClientTemplate().insert(
				"positioningTrajectory.addPositioningTrajectory", positioningTrajectory);
		return getPositioningTrajectoryById(id);
	}

	@Override
	public PositioningTrajectory getPositioningTrajectoryById(Long id) {
		return (PositioningTrajectory) getSqlMapClientTemplate().queryForObject(
				"positioningTrajectory.getPositioningTrajectoryById", id);
	}

	@Override
	public void deletePositioningTrajectoryByDeviceNumber(String deviceNumber) {
		getSqlMapClientTemplate().delete(
				"positioningTrajectory.deletePositioningTrajectoryByDeviceNumber", deviceNumber);
	}

	@Override
	public PageInfo<PositioningTrajectory> findPositioningTrajectoryByDeviceNumberAndLocateDate(
			SearchPositioningTrajectoryVo searchPositioningTrajectoryVo, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		Map<String, Object> query = new HashMap<String, Object>();

		query.put("locateDateStart", searchPositioningTrajectoryVo.getLocateDateStart());
		query.put("locateDateEnd", searchPositioningTrajectoryVo.getLocateDateEnd());
		query.put("deviceNumber", searchPositioningTrajectoryVo.getDeviceNumber());
		query.put("userName", searchPositioningTrajectoryVo.getUserName());
		query.put("sortField", sortField);
		query.put("order", order);
		Integer countNum = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"positioningTrajectory.countSearchPositioningTrajectoryByDeviceNumberAndLocateDate",
						query);
		List<PositioningTrajectory> resultList = getSqlMapClientTemplate().queryForList(
				"positioningTrajectory.searchPositioningTrajectoryByDeviceNumberAndLocateDate",
				query, (pageNum - 1) * pageSize, pageSize);

		return new PageInfo<PositioningTrajectory>(pageNum, pageSize, countNum, resultList);
	}

	@Override
	public void updateLonlatById(Long id, String centerLon, String centerLat,
			String centerLon2, String centerLat2) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("centerLon", centerLon);
		map.put("centerLat", centerLat);
		map.put("centerLon2", centerLon2);
		map.put("centerLat2", centerLat2);
		getSqlMapClientTemplate().update(
				"positioningTrajectory.updateLonlatById", map);

	}

}
