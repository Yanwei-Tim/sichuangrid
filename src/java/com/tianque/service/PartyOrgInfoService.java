package com.tianque.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PartyOrgInfo;
import com.tianque.domain.vo.SearchPartyOrgInfoVo;

/**
 * 本级党建党组织信息
 */
public interface PartyOrgInfoService {

	/**
	 * 新增党组织信息
	 * 
	 * @param partyOrgInfo
	 *            partyOrgInfo
	 */
	public void addPartyOrgInfo(PartyOrgInfo partyOrgInfo);

	/**
	 * 根据ID获取党组织信息
	 * 
	 * @param id
	 *            党组织信息ID
	 * @return PartyOrgInfo 党组织信息
	 */
	public PartyOrgInfo getPartyOrgInfoById(Long id);

	/**
	 * 根据党支部名称获取党组织信息
	 * 
	 * @param partyBranchName
	 *            党支部名称
	 * @return PartyOrgInfo 党组织信息
	 */
	public PartyOrgInfo getPartyOrgInfoByNameAndOrgId(String partyBranchName,
			Long orgId);

	/**
	 * 根据网格ID获取党组织信息
	 * 
	 * @param orgId
	 *            所属网格
	 * @return PartyOrgInfo 党组织信息
	 */
	public PartyOrgInfo getPartyOrgInfoByOrgId(Long orgId);

	/**
	 * 判断党组织是否存在
	 * 
	 * @param orgId
	 *            所属网格
	 * @param id
	 *            党组织信息ID
	 * @return PartyOrgInfo 党组织信息
	 */
	public boolean isExistPartyOrgInfoByIdAndOrgId(Long orgId, Long id);

	/**
	 * 修改党组织信息
	 * 
	 * @param partyOrgInfo
	 *            党组织信息
	 * @param oldPartyOrg
	 *            同时需要修改对应的党员关联组织表里面的组织的名称
	 */
	public void updatePartyOrgInfo(PartyOrgInfo partyOrgInfo, String oldPartyOrg);

	/**
	 * 条件查询党组织信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param searchPartyOrgInfoVo
	 * @return
	 */
	public PageInfo<PartyOrgInfo> searchPartyOrgInfos(Integer pageNum,
			Integer pageSize, SearchPartyOrgInfoVo searchPartyOrgInfoVo);

	/**
	 * 获取所有党组织信息
	 * 
	 * @param searchOptimalObjectVo
	 * @return
	 */
	public List<PartyOrgInfo> searchAllPartyOrgInfos(
			SearchPartyOrgInfoVo searchPartyOrgInfoVo);

	/**
	 * 根据查询条件分页查询党组织信息
	 * 
	 * @param orgId
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param isEmphasis
	 * @return PageInfo
	 */
	public PageInfo<PartyOrgInfo> findPartyOrgInfoForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord);

	public Integer getCount(SearchPartyOrgInfoVo searchPartyOrgInfoVo);
}
