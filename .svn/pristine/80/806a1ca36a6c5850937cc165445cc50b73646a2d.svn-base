package com.tianque.plugin.dataManage.population.idleYouthTemp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.domain.PropertyDict;
import com.tianque.plugin.dataManage.base.dao.AbstractDataManageDaoImp;
import com.tianque.plugin.dataManage.population.idleYouthTemp.domain.IdleYouthTemp;

@Repository("idleYouthTempDao")
public class IdleYouthTempDaoImpl extends AbstractDataManageDaoImp<IdleYouthTemp> {
	@Override
	public Long addTemp(IdleYouthTemp populationTemp) {

		Long id = (Long) getSqlMapClientTemplate().insert("idleYouthTemp.addIdleYouthTemp",
				populationTemp);
		buildStaffTypes(populationTemp, id);
		return id;
	}

	private void buildStaffTypes(IdleYouthTemp populationTemp, Long id) {
		if (null != populationTemp.getStaffType() && populationTemp.getStaffType().size() > 0) {
			Map<String, Object> map;
			for (PropertyDict dict : populationTemp.getStaffType()) {
				map = new HashMap<String, Object>();
				map.put("id", id);
				map.put("staffId", dict.getId());
				getSqlMapClientTemplate().insert("idleYouthTemp.addStaffType", map);
			}

		}
	}

	@Override
	public void updateTempBusiness(IdleYouthTemp temp) {

		regrantStaffTypeTempIds(temp);
		getSqlMapClientTemplate().update("idleYouthTemp.updateIdleYouthTempBusiness", temp);

	}

	public void regrantStaffTypeTempIds(IdleYouthTemp temp) {
		getSqlMapClientTemplate().delete("idleYouthTemp.deleteStaffTypes", temp.getId());
		buildStaffTypes(temp, temp.getId());
	}

	@Override
	public void deleteTempById(Class t, Long id) {
		getSqlMapClientTemplate().delete("idleYouthTemp.deleteStaffTypes", id);
		getSqlMapClientTemplate().delete("idleYouthTemp.deleteIdleYouthTempById", id);
	}

	@Override
	public IdleYouthTemp getTempById(Class t, Long id) {
		IdleYouthTemp idleYouthTemp = (IdleYouthTemp) getSqlMapClientTemplate().queryForObject(
				"idleYouthTemp.getIdleYouthTempById", id);
		List<PropertyDict> list = (List<PropertyDict>) getSqlMapClientTemplate().queryForList(
				"idleYouthTemp.loadStaffType", id);
		idleYouthTemp.setStaffType(list);
		return idleYouthTemp;

	}
}
