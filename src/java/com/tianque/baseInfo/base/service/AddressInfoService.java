package com.tianque.baseInfo.base.service;

import java.util.List;

import com.tianque.baseInfo.base.domain.Countrymen;

public interface AddressInfoService {
	/**
	 * 根据ID获取地址信息
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public Countrymen get(Long id);

	/**
	 * 保存地址信息
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	public Countrymen add(Countrymen countrymen);

	/**
	 * 更新地址信息
	 * 
	 * @param entity
	 *            对象
	 */
	public Countrymen update(Countrymen countrymen);

	/**
	 * 根据ID删除地址信息
	 * 
	 * @param id
	 *            记录ID
	 */
	public void delete(Long id);

	public void updateAddressByPopulationTypeAndPopulationId(
			String populationType, Long populationId, String houseAddress);

	public List<Countrymen> getAddressInfoByIdForList(List<Long> houseInfoIds);

	/**
	 * @return 未找到org的地址数量
	 */
	public int[] updateAddressOrg(Long maxId);

	public void updateAddressOrg(String tableName);

}
