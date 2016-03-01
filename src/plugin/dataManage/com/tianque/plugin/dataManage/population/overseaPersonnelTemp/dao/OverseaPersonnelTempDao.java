package com.tianque.plugin.dataManage.population.overseaPersonnelTemp.dao;

import com.tianque.plugin.dataManage.population.overseaPersonnelTemp.domain.OverseaPersonnelTemp;

public interface OverseaPersonnelTempDao {

	public OverseaPersonnelTemp getTempById(Long id);

	public void updateTemp(OverseaPersonnelTemp temp);

}
