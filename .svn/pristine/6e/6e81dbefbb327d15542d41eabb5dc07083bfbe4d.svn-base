package com.tianque.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PartyOrgInfo;
import com.tianque.domain.vo.SearchPartyOrgInfoVo;

/**
 * 党组织信息数据库操作接口。
 */
public interface PartyOrgInfoDao {

	/**
	 * 新增党组织信息
	 * 
	 * @param partyOrgInfo
	 *            partyOrgInfo
	 * @return partyOrgInfo partyOrgInfo
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
	 * 根据所属网格ID获取党组织信息
	 * 
	 * @param orgId
	 *            所属网格
	 * @param id
	 *            党组织信息ID
	 * @return PartyOrgInfo 党组织信息
	 */
	public PartyOrgInfo getPartyOrgInfoByIdAndOrgId(Long orgId, Long id);

	/**
	 * 修改党组织信息
	 * 
	 * @param partyOrgInfo
	 *            党组织信息
	 */
	public void updatePartyOrgInfo(PartyOrgInfo partyOrgInfo);

	/**
	 * 根据查询条件分页查询党组织信息
	 * 
	 * @param queryStraitenedResident
	 *            党组织信息查询条件对象
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<PartyOrgInfo> findPartyOrgInfoForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord);

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

	public Integer getCount(SearchPartyOrgInfoVo searchPartyOrgInfoVo);
}
