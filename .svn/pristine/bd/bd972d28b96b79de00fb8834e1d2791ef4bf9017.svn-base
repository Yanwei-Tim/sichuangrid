package com.tianque.newVillage.dao;

import java.util.List;

import com.tianque.newVillage.domain.BasicYearInfo;

/**
 * @Description: 基本信息DAO
 * @author yangfan
 * @date 2015年10月12日
 */

public interface BasicYearInfoDao {
	/**
	 * 增加基本信息 数据
	 * 
	 * @param basicYearInfo
	 * @return BasicYearInfo
	 */
	public BasicYearInfo addBasicYearInfo(BasicYearInfo basicYearInfo);

	/**
	 * 根据id获得 基本信息数据
	 * 
	 * @param id
	 * @return BasicYearInfo
	 */
	public BasicYearInfo getBasicYearInfoById(Long id);

	/**
	 * 根据id删除基本信息数据
	 * 
	 * @param id
	 */
	public void deleteBasicYearInfoById(String[] id);

	/**
	 * 修改基本信息数据
	 * 
	 * @param basicYearInfo
	 * @return BasicYearInfo
	 */
	public BasicYearInfo updateBasicYearInfo(BasicYearInfo basicYearInfo);

	/***
	 * 根据basicId和year quarter查询数据
	 */
	public BasicYearInfo getBasicYearInfoByBasicId(Long basicId);

	/**
	 * 根据年份year查出 基本信息
	 */
	public List<BasicYearInfo> getBasicYearInfoByYearAndOrgId(Integer year,
			Long orgId);
}
