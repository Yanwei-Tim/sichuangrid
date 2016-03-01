package com.tianque.service;

import com.tianque.baseInfo.base.domain.ActualPopulation;

public interface ActualBaseInfoSyncService {

	public ActualPopulation updateActualPopulationBaseInfo(ActualPopulation actualPopulation);

	public void asynActualPopulation(ActualPopulation actualPopulation);

	public ActualPopulation getFullActualPopulationByCardNoAndOrgId(String idCardNo, Long orgId);
}
