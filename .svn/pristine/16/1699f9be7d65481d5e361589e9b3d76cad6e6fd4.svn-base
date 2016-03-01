package com.tianque.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PartyOrgActivity;

/**
 * 下辖党建党组织活动业务接口
 */
public interface LowerPartyActivityService {
	/**
	 * 根据orgInternalCode查询下辖党建活动 分页信息
	 * 
	 * @param orgInternalCode
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param year
	 *            活动年份
	 * @return PageInfo
	 */
	public PageInfo<PartyOrgActivity> findPartyOrgActivityForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, String year);

}
