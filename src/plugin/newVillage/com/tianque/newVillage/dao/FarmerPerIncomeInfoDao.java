package com.tianque.newVillage.dao;

import com.tianque.newVillage.domain.FarmerPerIncomeInfo;

/**
 * @ClassName: FarmerPerIncomeInfoDao
 * @Description: 农民人均收入信息DAO
 * @author yangfan
 * @date 2015年10月9日
 */

public interface FarmerPerIncomeInfoDao {
	/**
	 * 增加农民人均收入信息 数据
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
	 * 根据id删除农民人均收入信息
	 * 
	 * @param id
	 */
	public void deleteFarmerPerIncomeInfoById(String[] id);

	/**
	 * 修改农民人均收入信息
	 * 
	 * @param farmerPerIncomeInfo
	 * @return FarmerPerIncomeInfo
	 */
	public FarmerPerIncomeInfo updateFarmerPerIncomeInfo(
			FarmerPerIncomeInfo farmerPerIncomeInfo);

	/***
	 * 根据basicId和year quarter查询数据
	 */
	public FarmerPerIncomeInfo getFarmerPerIncomeInfoByBasicId(Long basicId);
}
