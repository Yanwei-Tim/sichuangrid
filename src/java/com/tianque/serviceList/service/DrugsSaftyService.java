/**
 * 
 */
package com.tianque.serviceList.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.serviceList.domain.DrugsSafty;

/**
 * @作者:彭乐
 * @功能: 
 * @时间:2015-11-27 上午10:55:54
 * @邮箱:pengle@hztianque.com
 */
public interface DrugsSaftyService {
	/**
	 * 保存
	 */
	public DrugsSafty addDrugsSafty(DrugsSafty info);

	/**
	 * 查询列表
	 */
	public PageInfo<DrugsSafty> getDrugsSaftyListByQuery(DrugsSafty drugsSafty,
			Integer page, Integer rows, String sidx, String sord);

	/**
	 * 更新信息
	 * 
	 * @param companyBaseInfo
	 * @return
	 * @throws Exception
	 */
	public DrugsSafty updateDrugsSafty(DrugsSafty drugsSafty);

	/**
	 * 批量删除信息
	 * 
	 * @param ids
	 */
	public void deleteDrugsSaftyByIds(String ids);

	/**
	 * 获取主表信息
	 * 
	 * @param id
	 * @return
	 */
	public DrugsSafty getDrugsSaftyById(Long id);
	/**
	 * 签收
	 */
	public DrugsSafty signDrugsSafty(DrugsSafty drugsSafty);
	/**
	 * 回复
	 */
	public DrugsSafty replyDrugsSafty(DrugsSafty drugsSafty);
}
