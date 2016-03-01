package com.tianque.dao;

import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PartyOrgActivity;

/**
 * 下辖党组织活动数据库操作接口。
 */
public interface LowerPartyActivityDao {

	/**
	 * 根据条件查询组织活动的分页信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param map
	 *        map中key yearActivity 活动年份
	 *        orgInternalCode
	 * @return
	 */
	public PageInfo<PartyOrgActivity> findPartyOrgActivityForPageByOrgId(Integer pageNum,
			Integer pageSize, String sidx, String sord, Map<String, Object> map);

}
