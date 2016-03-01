package com.tianque.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PartyOrgActivity;
import com.tianque.domain.vo.SearchPartyOrgActivityVo;

/**
 * 本级党建党组织活动业务接口
 */
public interface PartyOrgActivityService {

	/**
	 * 添加组织活动信息
	 * 
	 * @param partyOrgActivity
	 *            党组织活动
	 * @return PartyOrgActivity 党组织活动
	 */
	public PartyOrgActivity addPartyOrgActivity(
			PartyOrgActivity partyOrgActivity);

	/**
	 * 根据ID获取党组织活动信息
	 * 
	 * @param id
	 *            组织活动信息ID
	 * @return PartyOrgActivity 组织活动信息
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
	 * 修改组织活动信息
	 * 
	 * @param partyOrgActivity
	 *            组织活动信息
	 * @return PartyOrgActivity 组织活动信息
	 */
	public PartyOrgActivity updatePartyOrgActivity(
			PartyOrgActivity partyOrgActivity);

	/**
	 * 根据ID删除组织活动信息
	 * 
	 * @param id
	 *            组织活动ID
	 */
	public void deletePartyOrgActivityById(Long id);

	/**
	 * 根据查询条件分页查询组织活动信息
	 * 
	 * @param orgId
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param yearActivity
	 * @return PageInfo
	 */
	public PageInfo<PartyOrgActivity> findPartyOrgActivityForPageByOrgId(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord, String yearActivity);

	/**
	 * 批量删除组织活动信息
	 * 
	 * @param ids
	 *            组织活动信息ID
	 * @retur List
	 */
	public List<Long> deletePartyPartyOrgActivityById(List<Long> ids);

	/**
	 * 条件查询组织活动信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param searchPartyOrgActivityVo
	 * @return
	 */
	public PageInfo<PartyOrgActivity> searchPartyOrgActivitys(Integer pageNum,
			Integer pageSize, SearchPartyOrgActivityVo searchPartyOrgActivityVo);

	/**
	 * 获取所有组织活动信息
	 * 
	 * @param searchPartyOrgActivityVo
	 * @return
	 */
	public List<PartyOrgActivity> searchAllPartyOrgActivitys(
			SearchPartyOrgActivityVo searchPartyOrgActivityVo);

	public Integer getCount(SearchPartyOrgActivityVo searchPartyOrgActivityVo);
}
