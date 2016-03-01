package com.tianque.gis.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.domain.BuildingData;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.gis.dao.BuildingDataDao;

@Repository("buildingDataDao")
public class BuildingDataDaoImpl extends AbstractBaseDao implements
		BuildingDataDao {
	@Override
	public BuildingData addBuildingData(BuildingData buildingData) {
		if (!valdateNotNull(buildingData)) {
			throw new BusinessValidationException("必填字段不能为空 ，请检查");
		}
		Long id = (Long) getSqlMapClientTemplate().insert(
				"buildingData.addBuildingData", buildingData);
		return getBuildingDataById(id);
	}

	@Override
	public void deleteBuildingDataById(Long id) {
		if (null == id || id < 1L) {
			throw new BusinessValidationException("删除方法中id为空,请检查...");
		}
		getSqlMapClientTemplate().delete("buildingData.deleteBuildingDataById",
				id);
	}

	@Override
	public BuildingData getBuildingDataById(Long id) {
		if (null == id || id < 1L) {
			throw new BusinessValidationException("查找方法中id为空,请检查...");
		}
		return (BuildingData) getSqlMapClientTemplate().queryForObject(
				"buildingData.getBuildingDataById", id);
	}

	@Override
	public BuildingData getBuildingDataByBuildingId(Long buildingId) {
		return (BuildingData) getSqlMapClientTemplate().queryForObject(
				"buildingData.getBuildingDataByBuildingId", buildingId);
	}

	@Override
	public BuildingData updateBuildingData(BuildingData buildingData) {
		if (null == buildingData || buildingData.getId() == null) {
			throw new BusinessValidationException("修改方法中id为空,请检查...");
		}
		if (!valdateNotNull(buildingData)) {
			throw new BusinessValidationException("必填字段不能为空 ，请检查");
		}
		getSqlMapClientTemplate().update("buildingData.updateBuildingData",
				buildingData);
		return getBuildingDataById(buildingData.getId());
	}

	private boolean valdateNotNull(BuildingData buildingData) {
		if (null == buildingData || null == buildingData.getBuildingId()) {
			return false;
		}
		if (null == buildingData.getOrganization()
				|| null == buildingData.getOrganization().getId()) {
			return false;
		}
		if (null == buildingData || null == buildingData.getOrgInternalCode()
				|| "".equals(buildingData.getOrgInternalCode().trim())) {
			return false;
		}
		if (null == buildingData || null == buildingData.getCenterX()) {
			return false;
		}
		if (null == buildingData || null == buildingData.getCenterY()) {
			return false;
		}
		return true;
	}

	@Override
	public void deleteBuildingDataByIds(List<Long> personIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idList", personIds);
		getSqlMapClientTemplate().delete("buildingData.deleteBuildingDataById",
				map);

	}
}
