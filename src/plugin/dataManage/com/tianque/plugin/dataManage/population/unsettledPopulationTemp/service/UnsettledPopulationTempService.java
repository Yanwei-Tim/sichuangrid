package com.tianque.plugin.dataManage.population.unsettledPopulationTemp.service;

import com.tianque.plugin.dataManage.population.unsettledPopulationTemp.domain.UnsettledPopulationTemp;

public interface UnsettledPopulationTempService {
	/** 根据id和类型获取对象 */
	public UnsettledPopulationTemp getTempById(Long id);

	/** 修改人员基础信息 */
	public UnsettledPopulationTemp updateTemp(UnsettledPopulationTemp temp);

	// /**
	// * 根据网格内置编码分页查询未落户人员
	// */
	// PageInfo<UnsettledPopulationTemp> findUnsettledPopulationTempForPage(
	// Long orgId, Integer page, Integer rows, String sidx, String sord,
	// Long searchType, String currentAddress);
	//
	// /**
	// * 导入未落户人员
	// */
	// UnsettledPopulationTemp addUnsettledPopulationTemp(
	// UnsettledPopulationTemp domain);
	//
	// /**
	// * 根据id获取未落户人员
	// */
	// UnsettledPopulationTemp getUnsettledPopulationTempById(Long id);
	//
	// /**
	// * 根据id删除未落户人员
	// */
	// void deleteUnsettledPopulationTempByIds(Long[] analyzePopulationIds);
	//
	// /**
	// * 认领未落户人员
	// */
	// UnsettledPopulationTempResultListVo claimHouseholdStaffs(
	// String populationIds, Long id);
	//
	// /**
	// * 撤销认领未落户人员
	// */
	// void updateUnsettledPopulationTempForUnClaim(String populationIds);
	//
	// /**
	// * 修改并认领未落户人员
	// */
	// UnsettledPopulationTemp updateUnsettledPopulationTemp(
	// UnsettledPopulationTemp population);
	//
	// /**
	// * 认领、修改重复数据
	// */
	// UnsettledPopulationTemp
	// claimUpdateUnsettledPopulationTempAndHouseholdStaff(
	// HouseholdStaff householdStaff, Long id);

}