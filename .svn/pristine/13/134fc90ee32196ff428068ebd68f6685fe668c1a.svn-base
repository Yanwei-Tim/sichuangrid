package com.tianque.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PartyOrgActivity;
import com.tianque.domain.vo.SearchPartyOrgActivityVo;

/**
 * 党组织活动数据库操作接口。
 */
public interface PartyOrgActivityDao {

	/**
	 * 新增党组织活动信息
	 * 
	 * @param partyOrgActivity
	 *            partyOrgActivity
	 * @return partyOrgActivity partyOrgActivity
	 */
	public PartyOrgActivity addPartyOrgActivity(
			PartyOrgActivity partyOrgActivity);

	/**
	 * 根据ID获取党组织活动信息
	 * 
	 * @param id
	 *            党组织活动信息ID
	 * @return PartyOrgActivity 党组织活动信息
	 */
	public PartyOrgActivity getPartyOrgActivityById(Long id);

	/**
	 * 获取党组织下的所有党组织活动记录信息
	 * 
	 * @param partyOrgId
	 * @return @
	 */
	public List<PartyOrgActivity> getPartyOrgActivityByPartyOrgId(
			Long partyOrgId);

	/**
	 * 修改党组织活动信息
	 * 
	 * @param partyOrgActivity
	 *            党组织活动信息
	 * @return partyOrgActivity 党组织活动信息
	 */
	public PartyOrgActivity updatePartyOrgActivity(
			PartyOrgActivity partyOrgActivity);

	/**
	 * 根据ID删除党组织活动信息
	 * 
	 * @param id
	 *            党组织活动信息ID
	 */
	public void deletePartyOrgActivityById(Long id);

	/**
	 * 根据查询条件分页查询党组织活动信息
	 * 
	 * @param queryStraitenedResident
	 *            党组织活动信息查询条件对象
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<PartyOrgActivity> findPartyOrgActivityForPageByOrgId(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord, String yearActivity);

	/**
	 * 根据查询条件高级查询
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param searchPartyOrgActivityVo
	 * @return
	 */
	public PageInfo<PartyOrgActivity> searchPartyOrgActivitys(Integer pageNum,
			Integer pageSize, SearchPartyOrgActivityVo searchPartyOrgActivityVo);

	/**
	 * 获取所有党组织活动信息
	 * 
	 * @param searchPartyOrgActivityVo
	 * @return
	 */
	public List<PartyOrgActivity> searchAllPartyOrgActivitys(
			SearchPartyOrgActivityVo searchPartyOrgActivityVo);

	public Integer getCount(SearchPartyOrgActivityVo searchPartyOrgActivityVo);
}
