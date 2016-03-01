package com.tianque.recoverDatas.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.recoverDatas.domain.RecoverDatas;
import com.tianque.recoverDatas.vo.RecoverDatasVo;

public interface RecoverDatasDao {

	public RecoverDatas addRecoverDatas(RecoverDatas recoverDatas);

	public RecoverDatas getRecoverDatasById(Long id);

	public PageInfo<RecoverDatas> findRecoverDatasForPage(
			RecoverDatasVo recoverDatasVo, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	public void deleteRecoverDatasById(Long id);

	public List<RecoverDatas> findRecoverDatasForRecover(
			RecoverDatasVo recoverDatasVo);
	
	public List<RecoverDatas> findRecoverdatainfosOneDay(
			int pageNum, int pageSize);

	public Integer countRecoverdatainfosOneDay();
}
