package com.tianque.integrativeQuery.population.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.integrativeQuery.population.domain.PopulationBaseQueryVo;
import com.tianque.integrativeQuery.population.domain.PopulationQueryVo;

public interface PopulationIntegrativeQueryDao {

	PageInfo<Countrymen> searchPopulationByIntegrativeCondition(
			PopulationQueryVo populationQueryVo, String actualPersonType,
			String[] attentionPopulationTypes, String[] actualTypes, PropertyDict gender,
			Date birthdayStrart, Date birthdayEnd, Boolean hasHouse, String orgCode,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	List<Countrymen> searchPopulationByIntegrativeConditionForExport(
			PopulationQueryVo populationQueryVo, String acutalPopulation,
			String[] attentionPopulationTypes, String[] actualTypesByPermission,
			PropertyDict gender, Date birthdayStrart, Date birthdayEnd, Boolean hasHouse,
			String orgCode, Integer pageNum, Integer pageSize, String sidx, String sord);

	PageInfo queryPopulationForWorkBench(String searchText, String orgCode, Integer page,
			Integer rows, String sidx, String sord);

	/**
	 * 根据信息和表名的集合查询人口
	 * 
	 * @param populationBaseQueryVo
	 * @param tables
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<Countrymen> findPopulationsByBaseQueryVoAndTypes(
			PopulationBaseQueryVo populationBaseQueryVo,
			List<Map<String, String>> typeTableMapList, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	/**
	 * 根据populationId和表名的集合查询人口
	 * 
	 * @param populationId
	 * @param tableName
	 * @return
	 */
	Countrymen findPopulationsByPopulationIdAndType(Long populationId, String tableName);

}
