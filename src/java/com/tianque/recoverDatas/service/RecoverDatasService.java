package com.tianque.recoverDatas.service;

import java.util.List;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.companyPlace.domain.CompanyPlace;
import com.tianque.core.vo.PageInfo;
import com.tianque.recoverDatas.domain.RecoverDatas;
import com.tianque.recoverDatas.vo.RecoverDatasVo;

public interface RecoverDatasService {

	public RecoverDatas addRecoverDatas(RecoverDatas recoverdatas);

	public PageInfo<RecoverDatas> findRecoverDatasForPage(
			RecoverDatasVo recoverdatasvo, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	public RecoverDatas getRecoverDataById(Long id);

	public void deleteRecoverDatasById(Long id);

	public void deleteRecoverDatasByIds(Long[] id);

	public void deleteActualPopulation(Countrymen Countrymen);

	public void recoverActualPopulation(Long id);

	public void deleteActualHouse(List<HouseInfo> actualHouse);

	public List<RecoverDatas> findRecoverdatainfosOneDay(int pageNum,
			int pageSize);

	public Integer countRecoverdatainfosOneDay();

	public void deleteCompanyPlace(CompanyPlace companyPlace);
}
