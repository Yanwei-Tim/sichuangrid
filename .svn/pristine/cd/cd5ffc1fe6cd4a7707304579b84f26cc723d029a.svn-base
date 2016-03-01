package com.tianque.plugin.dataManage.population.overseaPersonnelTemp.dao;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.dataManage.population.overseaPersonnelTemp.domain.OverseaPersonnelTemp;

@Repository("overseaPersonnelTempDao")
public class OverseaPersonnelTempDaoImpl extends AbstractBaseDao implements OverseaPersonnelTempDao {

	@Override
	public OverseaPersonnelTemp getTempById(Long id) {
		return (OverseaPersonnelTemp) getSqlMapClientTemplate().queryForObject(
				"overseaPersonnelTemp.getOverseaPersonnelTempById", id);
	}

	@Override
	public void updateTemp(OverseaPersonnelTemp temp) {
		getSqlMapClientTemplate().update("overseaPersonnelTemp.updateOverseaPersonnelTemp", temp);
	}
}
