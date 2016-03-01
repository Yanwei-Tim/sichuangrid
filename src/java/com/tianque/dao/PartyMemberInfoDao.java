package com.tianque.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PartyMemberInfo;
import com.tianque.domain.vo.SearchPartyMemberInfoVo;

/**
 * 本级党建党员信息数据库操作接口。
 */
public interface PartyMemberInfoDao {

	/**
	 * 新增党员信息
	 * 
	 * @param partyMemberInfo
	 *            党员信息
	 * @return PartyMemberInfo 党员信息
	 */
	public PartyMemberInfo addPartyMemberInfo(PartyMemberInfo partyMemberInfo);

	/**
	 * 根据ID获取党员信息
	 * 
	 * @param id
	 *            党员信息ID
	 * @return PartyMemberInfo 党员信息
	 */
	public PartyMemberInfo getPartyMemberInfoById(Long id);

	/**
	 * 根据党组织下的所有党员信息
	 * 
	 * @param partyOrgId
	 * @return @
	 */
	public List<PartyMemberInfo> getPartyMemberInfoByPartyOrgId(Long partyOrgId);

	/**
	 * 判断身份证信息是否存在
	 * 
	 * @param id
	 * @param idCard
	 * @return
	 */
	public PartyMemberInfo getPartyMemberInfoByIdAndIdCardAndOrgId(Long id,
			List<String> idCardNoList, Long orgId);

	/**
	 * 修改党员信息
	 * 
	 * @param partyMemberInfo
	 *            党员信息
	 * @return PartyMemberInfo 党员信息
	 */
	public PartyMemberInfo updatePartyMemberInfo(PartyMemberInfo partyMemberInfo);

	/**
	 * 根据ID删除党员信息
	 * 
	 * @param id
	 *            党员信息ID
	 */
	public void deletePartyMemberInfoById(Long id);

	/**
	 * 根据身份证和所属网格获取党员信息
	 * 
	 * @param idCardNoList
	 *            身份证信息集合
	 * @param orgId
	 *            所属网格
	 * @return PartyMemberInfo 党员信息
	 */
	public PartyMemberInfo getPartyMemberInfoByOrgIdAndCardNo(
			List<String> idCardNoList, Long orgId);

	/**
	 * 根据查询条件分页查询党员信息
	 * 
	 * @param queryStraitenedResident
	 *            党员信息查询条件对象
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<PartyMemberInfo> findPartyMemberInfoForPageByOrgId(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord, Long isEmphasis);

	/**
	 * 根据查询条件高级查询
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param searchPartyMemberInfoVo
	 * @return
	 */
	public PageInfo<PartyMemberInfo> searchPartyMemberInfos(Integer pageNum,
			Integer pageSize, SearchPartyMemberInfoVo searchPartyMemberInfoVo);

	/**
	 * 获取所有党员信息
	 * 
	 * @param searchPartyMemberInfoVo
	 * @return
	 */
	public List<PartyMemberInfo> searchAllPartyMemberInfos(
			SearchPartyMemberInfoVo searchPartyMemberInfoVo);

	public Integer getCount(SearchPartyMemberInfoVo searchPartyMemberInfoVo);
}
