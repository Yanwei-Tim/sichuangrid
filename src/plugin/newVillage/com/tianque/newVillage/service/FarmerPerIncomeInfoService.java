package com.tianque.newVillage.service;

import com.tianque.newVillage.domain.FarmerPerIncomeInfo;

/**
 * @ClassName: FarmerPerIncomeInfoService
 * @Description: 农民人均收入
 */
public interface FarmerPerIncomeInfoService {
	/**
	 * 增加 农民人均收入信息
	 * 
	 * @param farmerPerIncomeInfo
	 * @return FarmerPerIncomeInfo
	 */
	public FarmerPerIncomeInfo addFarmerPerIncomeInfo(
			FarmerPerIncomeInfo farmerPerIncomeInfo);

	/**
	 * 根据id获得 农民人均收入信息
	 * 
	 * @param id
	 * @return FarmerPerIncomeInfo
	 */
	public FarmerPerIncomeInfo getFarmerPerIncomeInfoById(Long id);

	/**
	 * 根据id删除 农民人均收入信息
	 * 
	 * @param id
	 */
	public void deleteFarmerPerIncomeInfoById(String[] id);

	/**
	 * 修改 农民人均收入信息数据
	 * 
	 * @param FarmerPerIncomeInfo
	 * @return FarmerPerIncomeInfo
	 */
	public FarmerPerIncomeInfo updateFarmerPerIncomeInfo(
			FarmerPerIncomeInfo farmerPerIncomeInfo);

	/***
	 * 根据baseId年份、季度查询数据
	 */
	public FarmerPerIncomeInfo getFarmerPerIncomeInfoByBasicId(Long id);
}
