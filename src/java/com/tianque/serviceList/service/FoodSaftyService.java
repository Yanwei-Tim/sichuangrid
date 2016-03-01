/**
 * 
 */
package com.tianque.serviceList.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.serviceList.domain.FoodSafty;

/**
 * @作者:彭乐
 * @功能: 
 * @时间:2015-11-27 上午10:55:54
 * @邮箱:pengle@hztianque.com
 */
public interface FoodSaftyService {
	/**
	 * 保存
	 */
	public FoodSafty addFoodSafty(FoodSafty info);

	/**
	 * 查询列表
	 */
	public PageInfo<FoodSafty> getFoodSaftyListByQuery(FoodSafty foodSafty,
			Integer page, Integer rows, String sidx, String sord);

	/**
	 * 更新信息
	 * 
	 * @param companyBaseInfo
	 * @return
	 * @throws Exception
	 */
	public FoodSafty updateFoodSafty(FoodSafty foodSafty);

	/**
	 * 批量删除信息
	 * 
	 * @param ids
	 */
	public void deleteFoodSaftyByIds(String ids);

	/**
	 * 获取主表信息
	 * 
	 * @param id
	 * @return
	 */
	public FoodSafty getFoodSaftyById(Long id);
	/**
	 * 签收
	 */
	public FoodSafty signFoodSafty(FoodSafty foodSafty);
	/**
	 * 回复
	 */
	public FoodSafty replyFoodSafty(FoodSafty foodSafty);
}
