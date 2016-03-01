package com.tianque.recoverDatas.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.recoverDatas.domain.RecoverDatas;
import com.tianque.recoverDatas.vo.RecoverDatasVo;

@Repository("recoverDatasDao")
public class RecoverDatasDaoImpl extends AbstractBaseDao implements
		RecoverDatasDao {

	@Override
	public RecoverDatas addRecoverDatas(RecoverDatas recoverDatas) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"recoverdatas.addDatasInfo", recoverDatas);
		return getRecoverDatasById(id);
	}

	@Override
	public RecoverDatas getRecoverDatasById(Long id) {
		return (RecoverDatas) getSqlMapClientTemplate().queryForObject(
				"recoverdatas.getDatasInfoById", id);
	}

	@Override
	public List<RecoverDatas> findRecoverDatasForRecover(
			RecoverDatasVo recoverDatasVo) {
		return getSqlMapClientTemplate().queryForList(
				"recoverdatas.findDatasInfo", recoverDatasVo);
	}

	@Override
	public PageInfo<RecoverDatas> findRecoverDatasForPage(
			RecoverDatasVo recoverDatasVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		recoverDatasVo.setOrder(sord);
		recoverDatasVo.setSortField(sidx);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"recoverdatas.countDatasInfo", recoverDatasVo);
		List<RecoverDatas> recoverDatasList = getSqlMapClientTemplate()
				.queryForList("recoverdatas.findDatasInfo", recoverDatasVo,
						(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, recoverDatasList);
	}

	@Override
	public void deleteRecoverDatasById(Long id) {
		getSqlMapClientTemplate()
				.delete("recoverdatas.deleteDatasInfoById", id);
	}

	private PageInfo<RecoverDatas> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<RecoverDatas> pageInfo = new PageInfo<RecoverDatas>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}
	
	@Override
	public List<RecoverDatas> findRecoverdatainfosOneDay(
			int pageNum, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startRow", pageNum * pageSize);
		map.put("endRow", (pageNum + 1) * pageSize);
		return (List<RecoverDatas>) getSqlMapClientTemplate().queryForList(
				"recoverdatas.findRecoverdatainfosOneDay", map);
	}

	@Override
	public Integer countRecoverdatainfosOneDay() {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"recoverdatas.countRecoverdatainfosOneDay");
	}
}
