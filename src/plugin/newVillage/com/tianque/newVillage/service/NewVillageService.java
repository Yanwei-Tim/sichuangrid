package com.tianque.newVillage.service;

import com.tianque.newVillage.domain.NewVillage;

/**
 * @ClassName: NewVillageService
 * @Description: 新农村建设
 */
public interface NewVillageService {
	/**
	 * 增加新农村建设 数据
	 * 
	 * @param newVillage
	 * @return NewVillage
	 */
	public NewVillage addNewVillage(NewVillage newVillage);

	/**
	 * 根据id获得 新农村建设数据
	 * 
	 * @param id
	 * @return NewVillage
	 */
	public NewVillage getNewVillageById(Long id);

	/**
	 * 根据id删除新农村建设数据
	 * 
	 * @param id
	 */
	public void deleteNewVillageById(Long id);

	/**
	 * 修改新农村建设数据
	 * 
	 * @param newVillage
	 * @return NewVillage
	 */
	public NewVillage updateNewVillage(NewVillage newVillage);

	/***
	 * 根据baseId年份、季度查询数据
	 */
	public NewVillage getNewVillageByBasicId(Long id);
}
