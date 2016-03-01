package com.tianque.newVillage.service;

import com.tianque.newVillage.domain.IndustryDevelopment;

/**
 * @ClassName: IndustryDevelopmentService
 * @Description: 产业发展
 */
public interface IndustryDevelopmentService {
	/**
	 * 增加产业发展 数据
	 * 
	 * @param industryDevelopment
	 * @return IndustryDevelopment
	 */
	public IndustryDevelopment addIndustryDevelopment(
			IndustryDevelopment industryDevelopment);

	/**
	 * 根据id获得 产业发展数据
	 * 
	 * @param id
	 * @return IndustryDevelopment
	 */
	public IndustryDevelopment getIndustryDevelopmentById(Long id);

	/**
	 * 根据id删除产业发展数据
	 * 
	 * @param id
	 */
	public void deleteIndustryDevelopmentById(String[] id);

	/**
	 * 修改产业发展数据
	 * 
	 * @param industryDevelopment
	 * @return IndustryDevelopment
	 */
	public IndustryDevelopment updateIndustryDevelopment(
			IndustryDevelopment industryDevelopment);

	/***
	 * 根据baseId年份、季度查询数据
	 */
	public IndustryDevelopment getIndustryDevelopmentByBasicId(Long id);
}
