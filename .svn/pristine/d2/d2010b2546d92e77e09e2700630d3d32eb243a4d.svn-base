package com.tianque.newVillage.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.newVillage.dao.FarmerPerIncomeInfoDao;
import com.tianque.newVillage.domain.FarmerPerIncomeInfo;

/**
 * @author yangfan
 * @date 2015年10月9日
 */
@Repository("farmerPerIncomeInfoDao")
public class FarmerPerIncomeInfoDaoImpl extends AbstractBaseDao implements
		FarmerPerIncomeInfoDao {

	@Override
	public FarmerPerIncomeInfo addFarmerPerIncomeInfo(
			FarmerPerIncomeInfo farmerPerIncomeInfo) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"farmerPerIncomeInfo.addFarmerPerIncomeInfo",
				farmerPerIncomeInfo);
		return getFarmerPerIncomeInfoById(id);
	}

	@Override
	public FarmerPerIncomeInfo getFarmerPerIncomeInfoById(Long id) {
		return (FarmerPerIncomeInfo) getSqlMapClientTemplate().queryForObject(
				"farmerPerIncomeInfo.getFarmerPerIncomeInfo", id);
	}

	@Override
	public void deleteFarmerPerIncomeInfoById(String[] id) {
		getSqlMapClientTemplate().delete(
				"farmerPerIncomeInfo.deleteFarmerPerIncomeInfo", id);
	}

	@Override
	public FarmerPerIncomeInfo updateFarmerPerIncomeInfo(
			FarmerPerIncomeInfo farmerPerIncomeInfo) {
		getSqlMapClientTemplate().update(
				"farmerPerIncomeInfo.updateFarmerPerIncomeInfo",
				farmerPerIncomeInfo);
		return getFarmerPerIncomeInfoById(farmerPerIncomeInfo.getId());
	}

	@Override
	public FarmerPerIncomeInfo getFarmerPerIncomeInfoByBasicId(Long basicId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("basicId", basicId);
		return (FarmerPerIncomeInfo) getSqlMapClientTemplate().queryForObject(
				"farmerPerIncomeInfo.getFarmerPerIncomeInfoByBasicId", map);
	}

}
