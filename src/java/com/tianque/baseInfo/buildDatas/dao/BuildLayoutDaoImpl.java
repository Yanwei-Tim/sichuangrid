package com.tianque.baseInfo.buildDatas.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.buildDatas.domain.BuildLayout;
import com.tianque.baseInfo.buildDatas.domain.BuildLayoutCell;
import com.tianque.core.base.BaseDaoImpl;
import com.tianque.exception.base.BusinessValidationException;

@Repository("buildLayoutDao")
public class BuildLayoutDaoImpl extends BaseDaoImpl<BuildLayout, BuildLayout>
		implements BuildLayoutDao {

	@Override
	public BuildLayout addBuildLayout(BuildLayout buildLayout) {
		if (buildLayout == null)
			throw new BusinessValidationException("参数错误");
		Long id = (Long) getSqlMapClientTemplate().insert(
				"buildLayout.addBuildLayout", buildLayout);
		return getBuildLayoutById(id);
	}
	
	@Override
	public BuildLayout updateBuildLayout(BuildLayout buildLayout) {
		getSqlMapClientTemplate().update(
				"buildLayout.updateBuildLayout", buildLayout);
		return getBuildLayoutById(buildLayout.getId());
	}

	@Override
	public BuildLayout getBuildLayoutById(Long id) {
		if (id == null)
			throw new BusinessValidationException("参数错误");
		return (BuildLayout) getSqlMapClientTemplate().queryForObject(
				"buildLayout.getBuildLayoutById", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BuildLayout getBuildLayoutByBuildId(Long buildId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("buildId", buildId);
		return (BuildLayout) getSqlMapClientTemplate().queryForObject(
				"buildLayout.getBuildLayoutByBuildId", params);
	}

	@Override
	public void deleteBuildLayoutById(Long id) {
		getSqlMapClientTemplate().update(
				"buildLayout.deleteBuildLayoutById", id);
	}
	
	@Override
	public void deleteBuildLayoutByBuildId(Long buildId) {
		if (buildId == null)
			throw new BusinessValidationException("参数错误");
		getSqlMapClientTemplate().update(
				"buildLayout.deleteBuildLayoutByBuildId", buildId);
	}

	@Override
	public Long countKeyPersonInBuildingByBuilddatasidAndPersonType(
			Long builddatasid, String key, String shardCode) {
		if (builddatasid == null || key == null) {
			throw new BusinessValidationException("参数错误");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("populationtype", key);
		map.put("builddatasid", builddatasid);
		map.put("shardCode", shardCode);
		return (Long) getSqlMapClientTemplate()
				.queryForObject(
						"buildLayout.countKeyPersonInBuildingByBuilddatasidAndPersonType",
						map);
	}

	@Override
	public Boolean existKeyPersonInHouseByPersonTypeAndHouseId(
			String personType, Long houseId) {
		if (personType == null || houseId == null) {
			throw new BusinessValidationException("参数错误");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseid", houseId);
		map.put("populationtype", personType);
		Long number = (Long) getSqlMapClientTemplate().queryForObject(
				"buildLayout.existKeyPersonInHouseByPersonTypeAndHouseId", map);
		return number != null && number > 0;
	}

	@Override
	public List<Long> findBuildIds() {
		 return getSqlMapClientTemplate().queryForList("buildLayout.findBuildIds");
	}

	@Override
	public List<BuildLayoutCell> findBuildLayoutCellByBuildId(Long buildId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buildId", buildId);
		return getSqlMapClientTemplate().queryForList("buildLayout.findBuildLayoutCellByBuildId", map);
	}

}
