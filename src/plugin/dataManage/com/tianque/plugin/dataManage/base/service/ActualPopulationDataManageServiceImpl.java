package com.tianque.plugin.dataManage.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.unitils.database.annotations.Transactional;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.service.BaseInfoService;
import com.tianque.core.base.BaseDomain;
import com.tianque.plugin.dataManage.TargetDataVo;
import com.tianque.plugin.dataManage.base.ReflectionUtil;
import com.tianque.plugin.dataManage.util.Constants.ClaimHasRepeatActualPopu;
import com.tianque.service.ActualPopulationMutexService;

@Component("actualPopulationDataManageService")
@Transactional
public class ActualPopulationDataManageServiceImpl<T extends ActualPopulation> extends
		AbstractDataManageService {
	@Autowired
	private ActualPopulationMutexService actualPopulationMutexService;
	@Autowired
	private BaseInfoService baseInfoService;

	@Override
	public TargetDataVo getTargetDataVo(Object population) throws Exception {
		ActualPopulation actualtion = (ActualPopulation) ReflectionUtil
				.executePopulationHasDuplicateMethod(population);
		String idCardNo = (String) ReflectionUtil.executeTargetGetMethod(population, "getIdCardNo");
		TargetDataVo vo = new TargetDataVo();
		boolean hasRepeat = actualPopulationMutexService
				.isActualPopuationByOrgIdAndIdCardNoAndExcludeActualPopulationType(
						(Long) ReflectionUtil.executeTargetGetMethod(population, "getId"),
						ReflectionUtil.getTargetOrganization(population).getId(), idCardNo,
						(population.getClass().getSimpleName()).substring(0, 1).toLowerCase());
		if (actualtion == null && hasRepeat) {
			vo.setHasRepeatActualPopu(ClaimHasRepeatActualPopu.EXISTEDFOROTHERPEOPLE);
			return vo;
		} else {
			if (null != actualtion) {
				vo.setLogout(actualtion.getLogOut());
				vo.setId(actualtion.getId());
				return vo;
			} else {
				Countrymen temp = baseInfoService.getBaseInfoByIdCardNo(idCardNo);
				if (temp != null && temp.isDeath()) {
					vo.setHasRepeatActualPopu(ClaimHasRepeatActualPopu.DEATHFOROTHERORG);
					return vo;
				}
			}
		}
		return null;
	}

	@Override
	protected void asynchronousProcess(BaseDomain temp) {

	}

	@Override
	public void validCanClaim(BaseDomain population) {

	}

}
