package com.tianque.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PartyMemberInfo;
import com.tianque.domain.vo.SearchPartyMemberInfoVo;

/**
 * 本级党建党员信息业务接口。
 */
public interface PartyMemberInfoService {

	/**
	 * 添加党员信息
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
	 * 获取党组织下的所有党员信息
	 * 
	 * @param partyOrgId
	 * @return @
	 */
	public List<PartyMemberInfo> getPartyMemberInfoByPartyOrgId(Long partyOrgId);

	/**
	 * 修改党员基本信息
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
	 * 根据查询条件分页查询党员信息
	 * 
	 * @param orgId
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param isEmphasis
	 * @return PageInfo
	 */
	public PageInfo<PartyMemberInfo> findPartyMemberInfoForPageByOrgId(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord, Long isEmphasis);

	/**
	 * 批量删除党员信息
	 * 
	 * @param ids
	 *            党员信息ID
	 * @retur List
	 */
	public List<Long> deletePartyMemberInfoById(List<Long> ids);

	/**
	 * 获取人员基本信息
	 * 
	 * @param personIds
	 * @return
	 */
	public Boolean hasRelatePersons(List<Long> personIds);

	/**
	 * 判断身份证号码是否已经存在
	 * 
	 * @param orgId
	 * @param idCardNo
	 * @param exceptedId
	 * @return
	 */
	public Boolean hasDuplicatePartyMemberInfo(Long orgId, String idCardNo,
			Long exceptedId);

	/**
	 * 条件查询党员信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param searchPartyMemberInfoVo
	 * @return
	 */
	public PageInfo<PartyMemberInfo> searchPartyMemberInfos(Integer pageNum,
			Integer pageSize, SearchPartyMemberInfoVo searchPartyMemberInfoVo);

	/**
	 * 根据身份证号获取党员信息
	 * 
	 * @param idCardNo
	 * @param orgId
	 * @return
	 */
	public PartyMemberInfo getPartyMemberInfoIdCardNo(String idCardNo,
			Long orgId);

	/**
	 * 根据身份证号更新党员信息
	 * 
	 * @param idCardNo
	 * @param orgId
	 * @param partyMemberInfo
	 * @return
	 */
	public PartyMemberInfo updatePartyMemberInfoByIdCardNo(String idCardNo,
			Long orgId, PartyMemberInfo partyMemberInfo);

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
