package com.tianque.newVillage.service;

import com.tianque.newVillage.domain.Infrastructure;

/**
 * @ClassName: InfrastructureService
 * @Description: 基础设施
 */
public interface InfrastructureService {
	/**
	 * 增加基础设施 数据
	 * 
	 * @param infrastructure
	 * @return Infrastructure
	 */
	public Infrastructure addInfrastructure(Infrastructure infrastructure);

	/**
	 * 根据id获得 基础设施数据
	 * 
	 * @param id
	 * @return IndustryDevelopment
	 */
	public Infrastructure getInfrastructureById(Long id);

	/**
	 * 根据id删除基础设施数据
	 * 
	 * @param id
	 */
	public void deleteInfrastructureById(String[] id);

	/**
	 * 修改基础设施数据
	 * 
	 * @param infrastructure
	 * @return Infrastructure
	 */
	public Infrastructure updateInfrastructure(Infrastructure infrastructure);

	/***
	 * 根据baseId年份、季度查询数据
	 */
	public Infrastructure getInfrastructureByBasicId(Long id);
}
