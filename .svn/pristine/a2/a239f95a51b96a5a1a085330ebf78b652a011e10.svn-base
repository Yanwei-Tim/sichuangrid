/**
 * 
 */
package com.tianque.serviceList.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.serviceList.domain.PyramidSalesManage;

/**
 * @作者:彭乐
 * @功能: 
 * @时间:2015-11-27 上午10:55:54
 * @邮箱:pengle@hztianque.com
 */
public interface PyramidSalesManageService {
	/**
	 * 保存
	 */
	public PyramidSalesManage addPyramidSalesManage(PyramidSalesManage info);

	/**
	 * 查询列表
	 */
	public PageInfo<PyramidSalesManage> getPyramidSalesManageListByQuery(PyramidSalesManage pyramidSalesManage,
			Integer page, Integer rows, String sidx, String sord);

	/**
	 * 更新信息
	 * 
	 * @param companyBaseInfo
	 * @return
	 * @throws Exception
	 */
	public PyramidSalesManage updatePyramidSalesManage(PyramidSalesManage pyramidSalesManage);

	/**
	 * 批量删除信息
	 * 
	 * @param ids
	 */
	public void deletePyramidSalesManageByIds(String ids);

	/**
	 * 获取主表信息
	 * 
	 * @param id
	 * @return
	 */
	public PyramidSalesManage getPyramidSalesManageById(Long id);
	/**
	 * 签收
	 */
	public PyramidSalesManage signPyramidSalesManage(PyramidSalesManage pyramidSalesManage);
	/**
	 * 回复
	 */
	public PyramidSalesManage replyPyramidSalesManage(PyramidSalesManage pyramidSalesManage);
}
