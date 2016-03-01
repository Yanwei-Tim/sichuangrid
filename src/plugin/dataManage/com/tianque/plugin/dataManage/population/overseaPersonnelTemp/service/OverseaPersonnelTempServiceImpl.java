package com.tianque.plugin.dataManage.population.overseaPersonnelTemp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.overseaPersonnel.domain.OverseaPersonnel;
import com.tianque.plugin.dataManage.TargetDataVo;
import com.tianque.plugin.dataManage.base.ReflectionUtil;
import com.tianque.plugin.dataManage.base.service.ActualPopulationDataManageServiceImpl;
import com.tianque.plugin.dataManage.population.overseaPersonnelTemp.dao.OverseaPersonnelTempDao;
import com.tianque.plugin.dataManage.population.overseaPersonnelTemp.domain.OverseaPersonnelTemp;

@Service("overseaPersonnelTempService")
@Transactional
public class OverseaPersonnelTempServiceImpl extends ActualPopulationDataManageServiceImpl
		implements OverseaPersonnelTempService {

	@Autowired
	private OverseaPersonnelTempDao overseaPersonnelTempDao;

	public OverseaPersonnelTemp getTempById(Long id) {
		return (OverseaPersonnelTemp) overseaPersonnelTempDao.getTempById(id);
	}

	@Override
	public OverseaPersonnelTemp updateTemp(OverseaPersonnelTemp temp) {
		overseaPersonnelTempDao.updateTemp(temp);
		return getTempById(temp.getId());
	}

	@Override
	public TargetDataVo getTargetDataVo(Object population) throws Exception {
		OverseaPersonnel actualtion = (OverseaPersonnel) ReflectionUtil
				.executeOverseaPersonnelHasDuplicateMethod(population);
		if (null != actualtion) {
			TargetDataVo vo = new TargetDataVo();
			vo.setLogout(actualtion.getLogOut());
			vo.setId(actualtion.getId());
			return vo;
		}
		return null;
	}

}
