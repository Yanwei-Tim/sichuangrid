/**
 * 
 */
package com.tianque.serviceList.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.serviceList.domain.BusinessManage;

/**
 * @作者:彭乐
 * @功能: 
 * @时间:2015-11-27 上午10:55:54
 * @邮箱:pengle@hztianque.com
 */
public interface BusinessManageService {
	/**
	 * 保存
	 */
	public BusinessManage addBusinessManage(BusinessManage info);

	/**
	 * 查询列表
	 */
	public PageInfo<BusinessManage> getBusinessManageListByQuery(BusinessManage businessManage,
			Integer page, Integer rows, String sidx, String sord);

	/**
	 * 更新信息
	 * 
	 * @param companyBaseInfo
	 * @return
	 * @throws Exception
	 */
	public BusinessManage updateBusinessManage(BusinessManage businessManage);

	/**
	 * 批量删除信息
	 * 
	 * @param ids
	 */
	public void deleteBusinessManageByIds(String ids);

	/**
	 * 获取主表信息
	 * 
	 * @param id
	 * @return
	 */
	public BusinessManage getBusinessManageById(Long id);
	/**
	 * 签收
	 */
	public BusinessManage signBusinessManage(BusinessManage businessManage);
	/**
	 * 回复
	 */
	public BusinessManage replyBusinessManage(BusinessManage businessManage);
}
