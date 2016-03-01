package com.tianque.plugin.dataManage.population.superiorVisitTemp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.domain.PropertyDict;
import com.tianque.plugin.dataManage.base.dao.AbstractDataManageDaoImp;
import com.tianque.plugin.dataManage.population.superiorVisitTemp.domain.SuperiorVisitTemp;

@Repository("superiorVisitTempDao")
public class SuperiorVisitTempDaoImpl extends AbstractDataManageDaoImp<SuperiorVisitTemp> implements
		SuperiorVisitTempDao {

	@Override
	public void updateTempBusiness(SuperiorVisitTemp temp) {

		getSqlMapClientTemplate().delete("superiorVisitTemp.deleteVisitTypeTempById", temp.getId());
		addSuperiorVisitTemp(temp, temp.getId());
		getSqlMapClientTemplate().update("superiorVisitTemp.updateSuperiorVisitTempBusiness", temp);

	}

	@Override
	public Long addTemp(SuperiorVisitTemp superiorVisitTemp) {
		Long id = (Long) getSqlMapClientTemplate().insert("superiorVisitTemp.addSuperiorVisitTemp",
				superiorVisitTemp);
		addSuperiorVisitTemp(superiorVisitTemp, id);
		return id;

	}

	@Override
	public void deleteTempById(Class t, Long id) {
		getSqlMapClientTemplate().delete("superiorVisitTemp.deleteVisitTypeTempById", id);
		getSqlMapClientTemplate().delete("superiorVisitTemp.deleteSuperiorVisitTempById", id);
	}

	@Override
	public SuperiorVisitTemp getTempById(Class t, Long id) {
		SuperiorVisitTemp superiorVisitTemp = (SuperiorVisitTemp) getSqlMapClientTemplate()
				.queryForObject("superiorVisitTemp.getSuperiorVisitTempById", id);
		List<PropertyDict> list = (List<PropertyDict>) getSqlMapClientTemplate().queryForList(
				"superiorVisitTemp.findVisitTypesTempById", id);
		String typeName = getVisitType(superiorVisitTemp.getVisitType()) + "：";
		for (int i = 0; i < list.size(); i++) {
			typeName += list.get(i).getDisplayName() + ",";
		}
		superiorVisitTemp.setVisitTypes(list);
		superiorVisitTemp.setTypeName(typeName.substring(0, typeName.length() - 1));
		return superiorVisitTemp;

	}

	private String getVisitType(Long id) {
		if (id == null) {
			return "";
		}
		if (id == 1) {
			return "正常访";
		} else if (id == 0) {
			return "异常访";
		}
		return "";
	}

	private void addSuperiorVisitTemp(SuperiorVisitTemp superiorVisitTemp, Long id) {
		if (superiorVisitTemp.getVisitTypes() == null) {
			return;
		}
		for (PropertyDict pro : superiorVisitTemp.getVisitTypes()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("superiorVisitId", superiorVisitTemp.getId());
			map.put("superiorVisitType", pro.getId());
			getSqlMapClientTemplate().insert("superiorVisitTemp.addSuperiorVisitTypeTemp", map);
		}

	}

}
