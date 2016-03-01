package com.tianque.resourcePool.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.core.util.ThreadVariable;
import com.tianque.resourcePool.domain.DirectorySetting;
import com.tianque.resourcePool.vo.SearchDirectorySetting;

@Repository("directorySettingDao")
public class DirectorySettingDaoImpl extends
		BaseDaoImpl<DirectorySetting, SearchDirectorySetting> implements
		DirectorySettingDao {

	@Override
	public List<DirectorySetting> findDirectorySettingForList(Long userId) {

		return getSqlMapClientTemplate().queryForList(
				"directorySetting.findDirectorySettingForList", userId);
	}

	@Override
	public List<DirectorySetting> findchildByparentId(Long parentId, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", parentId);
		map.put("userId", userId);
		map.put("sortField", "indexId");
		map.put("order", "asc");

		return getSqlMapClientTemplate().queryForList(
				"directorySetting.findchildByparentId", map);
	}

	@Override
	public List<DirectorySetting> findTrunkDirectoryByUserId(Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("trunkDirectory", true);
		map.put("sortField", "indexId");
		map.put("order", "asc");
		return getSqlMapClientTemplate().queryForList(
				"directorySetting.findTrunkDirectoryByUserId", map);
	}

	@Override
	public List<DirectorySetting> findDirectorySettingByParentId(Long parentId) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", parentId);
		map.put("sortField", "indexid");
		map.put("order", "asc");
		return getSqlMapClientTemplate().queryForList(
				"directorySetting.findTrunkDirectoryByUserId", map);
	}

	@Override
	public List<DirectorySetting> findDirectorySettingByDirectoryType(
			int directoryType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("directoryType", directoryType);
		map.put("trunkDirectory", true);
		map.put("sortField", "indexId");
		map.put("order", "asc");
		return getSqlMapClientTemplate().queryForList(
				"directorySetting.findTrunkDirectoryByUserId", map);
	}

	@Override
	public DirectorySetting findDirectorySettingById(Long id) {
		return (DirectorySetting) getSqlMapClientTemplate().queryForObject(
				"directorySetting.getDirectorySettingById", id);
	}

	@Override
	public Integer getMaxIndexId(Long parentId) {
		Integer indexId = (Integer) getSqlMapClientTemplate().queryForObject(
				"directorySetting.getMaxIndexId", parentId);
		return indexId != null ? indexId : 0;
	}

	@Override
	public Integer getCountByResourcePoolType(Long resourcePoolType) {

		return (Integer) getSqlMapClientTemplate()
				.queryForObject("directorySetting.getCountByResourcePoolType",
						resourcePoolType);
	}

	@Override
	public Long getDirectoryIdByparentIdAndindexId(Integer indexId,
			Long parentId, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("indexId", indexId);
		map.put("parentId", parentId);
		map.put("userId", userId);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"directorySetting.getDirectoryIdByparentIdAndindexId", map);
	}

	@Override
	public Long getMinIdByParentIdAndLargerThanIndexId(Integer indexId,
			Long parentId, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("indexId", indexId);
		map.put("parentId", parentId);
		map.put("userId", userId);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"directorySetting.getMinIdByparentIdAndindexId", map);
	}

	@Override
	public List<DirectorySetting> searchDirectorySetting(String value) {
		List<DirectorySetting> directorySettings = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchText", value);
		map.put("userId", ThreadVariable.getUser().getId());

		directorySettings = getSqlMapClientTemplate().queryForList(
				"directorySetting.searchDirectorySetting", map);
		return directorySettings;
	}

	@Override
	public void updateMove(Integer indexId, Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("indexId", indexId);
		map.put("id", id);
		getSqlMapClientTemplate().update("directorySetting.updateMove", map);
	}

	@Override
	public List<DirectorySetting> findDirectorySettingByIdArray(
			List<String> array) {
		return getSqlMapClientTemplate().queryForList(
				"directorySetting.findDirectorySettingByIdArray", array);
	}

}
