package com.tianque.integrativeQuery.population.service;

import java.util.Date;
import java.util.List;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.integrativeQuery.population.domain.PopulationBaseQueryVo;
import com.tianque.integrativeQuery.population.domain.PopulationQueryVo;

public interface PopulationIntegrativeQueryServie {
	/**
	 * 综合查询 取人员的list
	 * 
	 * @param populationQueryVo
	 * @param actualPersonType
	 * @param sex
	 * @param birthdayStrart
	 * @param birthdayEnd
	 * @param hasHouse
	 * @param orgCode
	 * @return
	 */
	PageInfo<Countrymen> searchPopulationByIntegrativeCondition(
			PopulationQueryVo populationQueryVo, String actualPersonType,
			String[] attentionPopulationTypes, PropertyDict gender, Date birthdayStrart,
			Date birthdayEnd, Boolean hasHouse, String orgCode, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	List<Countrymen> searchPopulationByIntegrativeConditionForExport(
			PopulationQueryVo populationQueryVo, String actualPersonType,
			String[] attentionPopulationTypes, PropertyDict gender, Date birthdayStrart,
			Date birthdayEnd, Boolean hasHouse, String orgCode, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	public String[][] getExportPopertyArray();

	PageInfo queryPopulationForWorkBench(String searchText, String orgCode, Integer page,
			Integer rows, String sidx, String sord);

	/**
	 * 根据人员的基本信息和人员的类型集合查询人口信息
	 * 
	 * @param populationBaseQueryVo
	 * @param populationTypes
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<Countrymen> findPopulationsByBaseQueryVoAndTypes(
			PopulationBaseQueryVo populationBaseQueryVo, String[] populationTypes, Integer pageNum,
			Integer pageSize, String sidx, String sord);

}
