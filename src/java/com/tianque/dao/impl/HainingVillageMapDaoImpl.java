package com.tianque.dao.impl;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.HainingVillageMapDao;
import com.tianque.domain.HainingVillageMap;

@Repository("hainingVillageMapDao")
public class HainingVillageMapDaoImpl extends AbstractBaseDao implements HainingVillageMapDao {

	@Override
	public HainingVillageMap getHainingVillageMapByDepartmentNo(String departmentNo) {

		HainingVillageMap result = null;
		Object o = this.getSqlMapClientTemplate().queryForObject(
				"hainingVillageMap.getHainingVillageMapByDepartmentNo", departmentNo);
		if (o != null) {
			result = (HainingVillageMap) o;
		}
		return result;
	}

	@Override
	public HainingVillageMap addHainingVillageMap(HainingVillageMap hainingVillageMap) {
		this.getSqlMapClientTemplate().insert("hainingVillageMap.addHainingVillageMap",
				hainingVillageMap);
		return this.getHainingVillageMapByDepartmentNo(hainingVillageMap.getDepartmentNo());
	}

	@Override
	public HainingVillageMap updateHainingVillageMap(HainingVillageMap hainingVillageMap) {
		this.getSqlMapClientTemplate().insert("hainingVillageMap.updateHainingVillageMap",
				hainingVillageMap);
		return this.getHainingVillageMapByDepartmentNo(hainingVillageMap.getDepartmentNo());
	}

	@Override
	public HainingVillageMap deleteImg(String departmentNo) {
		this.getSqlMapClientTemplate().update("hainingVillageMap.deleteImgUrl", departmentNo);
		return this.getHainingVillageMapByDepartmentNo(departmentNo);
	}

}
