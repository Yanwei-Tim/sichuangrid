package com.tianque.newVillage.service;

import com.tianque.newVillage.domain.CommonServiceInfo;

/**
 * @ClassName: CommonServiceInfoService
 * @Description: 公共服务
 */
public interface CommonServiceInfoService {
	/**
	 * 增加公共服务 数据
	 * 
	 * @param commonServiceInfo
	 * @return CommonServiceInfo
	 */
	public CommonServiceInfo addCommonServiceInfo(
			CommonServiceInfo commonServiceInfo);

	/**
	 * 根据id获得 公共服务数据
	 * 
	 * @param id
	 * @return CommonServiceInfo
	 */
	public CommonServiceInfo getCommonServiceInfoById(Long id);

	/**
	 * 根据id删除公共服务数据
	 * 
	 * @param id
	 */
	public void deleteCommonServiceInfoById(String[] id);

	/**
	 * 修改公共服务数据
	 * 
	 * @param commonServiceInfo
	 * @return CommonServiceInfo
	 */
	public CommonServiceInfo updateCommonServiceInfo(
			CommonServiceInfo commonServiceInfo);

	/***
	 * 根据baseId年份、季度查询数据
	 */
	public CommonServiceInfo getCommonServiceInfoByBasicId(Long id);
}
